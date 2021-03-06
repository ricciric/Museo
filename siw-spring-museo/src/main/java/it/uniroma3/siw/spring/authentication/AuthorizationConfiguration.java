package it.uniroma3.siw.spring.authentication;

import static it.uniroma3.siw.spring.model.Credenziali.RUOLO_ADMIN;

import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class AuthorizationConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	DataSource datasource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		// authorization paragraph: qui definiamo chi può accedere a cosa
		.authorizeRequests()
		// chiunque (autenticato o no) può accedere alle pagine index, login, register, ai css e alle immagini
		.antMatchers(HttpMethod.GET, "/", "/index", "/login", "/registrazione", "/css/**", "/images/**").permitAll()
		// chiunque (autenticato o no) può mandare richieste POST al punto di accesso per login e register 
		.antMatchers(HttpMethod.POST, "/login", "/registrazione").permitAll()
		// solo gli utenti autenticati con ruolo ADMIN possono accedere a risorse con path /admin/**
		.antMatchers(HttpMethod.GET, "/admin/**").hasAnyAuthority(RUOLO_ADMIN)
		.antMatchers(HttpMethod.POST, "/admin/**").hasAnyAuthority(RUOLO_ADMIN)
		// tutti gli utenti autenticati possono accere alle pagine rimanenti 
		.anyRequest().authenticated()

		// login paragraph: qui definiamo come è gestita l'autenticazione
		// usiamo il protocollo formlogin 
		.and().formLogin()
		// la pagina di login si trova a /login
		// NOTA: Spring gestisce il post di login automaticamente
		.loginPage("/login")
		// se il login ha successo, si viene rediretti al path /default
		.defaultSuccessUrl("/default")

		// logout paragraph: qui definiamo il logout
		.and().logout()
		// il logout è attivato con una richiesta GET a "/logout"
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		// in caso di successo, si viene reindirizzati alla /index page
		.logoutSuccessUrl("/")        
		.invalidateHttpSession(true)
		.clearAuthentication(true).permitAll();
	}

	/*Per avere Username e Password*/
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		//use the autowired datasource to access the saved credentials
		.dataSource(this.datasource)
		//retrieve username and ruolo
		.authoritiesByUsernameQuery("SELECT username, ruolo FROM credenziali WHERE username=?")
		//retrieve username, password and a boolean flag specifying whether the user is enabled or not (always enabled in our case)
		.usersByUsernameQuery("SELECT username, password, 1 as enabled FROM credenziali WHERE username=?");
	}
	
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
       
    }
}
