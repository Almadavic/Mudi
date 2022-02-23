
package br.com.alura.mvc.mudi;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired 
	private DataSource dataSource; // banco de dados

	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	                  http
	                .authorizeRequests()
	                .antMatchers("/home/**") // não precisa estar logado para acessar isso
	                .permitAll() // todos são permitidos
	                .anyRequest()
	                .authenticated()  // O restante, para fazer qualquer coisa, precisa estar logado(autenticado)
	                .and()
	                .formLogin(form -> form
	                        .loginPage("/login") // preciso criar um controller para redirecionar para uma pagina /login
	                        .defaultSuccessUrl("/usuario/pedido", true) // quando eu fizer o login, vou para essa pagina
	                        .permitAll()
	                )
	                .logout(logout -> {logout 
	                		.logoutUrl("/logout") // isso faz com que eu encerre meu login
	                		.logoutSuccessUrl("/home"); // Quando eu deslogar, vai para essa pagina
	        
	    }).csrf().disable(); // Faz com que eu possa fazer ações, como cadastrar pedido.
	 }
	 
	 @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // Isso codifica minha senha(segurança)
		
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(encoder);
		
		// Esse método faz com que eu me autentique com os users do banco de dados (login e senha) 
	}
	
	
}

