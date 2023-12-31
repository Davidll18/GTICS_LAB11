package edu.pucp.gtics.lab11_gtics_20232.config;


import edu.pucp.gtics.lab11_gtics_20232.entity.User;
import edu.pucp.gtics.lab11_gtics_20232.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
@Configuration
public class WebSecurityConfig  {

    final UserRepository userRepository;
    final DataSource dataSource;

    public WebSecurityConfig(UserRepository userRepository, DataSource dataSource) {
        this.userRepository = userRepository;
        this.dataSource = dataSource;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/openLoginWindow")
                .loginProcessingUrl("/processLogin")
                .usernameParameter("yourUsername")
                .passwordParameter("yourPassword")
                .successHandler((request, response, authentication) -> {

                    DefaultSavedRequest defaultSavedRequest =
                            (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");

                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", userRepository.findByCorreo(authentication.getName()));

                    if(defaultSavedRequest != null){
                        String targetURl = defaultSavedRequest.getRequestURL();
                        new DefaultRedirectStrategy().sendRedirect(request,response,targetURl);
                    }else{
                        String rol = "";
                        for(GrantedAuthority role : authentication.getAuthorities()){
                            rol = role.getAuthority();
                            break;
                        }
                        System.out.println(rol);
                        if (rol.equals("USER")) {
                            response.sendRedirect("/gameshop4/juegos/");
                        } else {
                            response.sendRedirect("/gameshop4/distribuidoras/");
                        }

                    }


                });
                //.defaultSuccessUrl("/juegos/lista",true);

        http.authorizeHttpRequests()
                .antMatchers("/juegos","/juegos/**").hasAnyAuthority("ADMIN","USER")
                .antMatchers("/distribuidoras","/distribuidora/**").hasAnyAuthority("ADMIN")
                .antMatchers("/usuarios","/usuarios/**").hasAnyAuthority("ADMIN", "USER")
                .anyRequest().permitAll();

        return http.build();
    }


    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        String sqlAuth = "SELECT correo, password, enabled FROM usuarios WHERE correo = ?";
        String sqlAuto = "SELECT correo, autorizacion FROM usuarios WHERE correo = ? and enabled = 1";
        users.setUsersByUsernameQuery(sqlAuth);
        users.setAuthoritiesByUsernameQuery(sqlAuto);
        return users;
    }
}