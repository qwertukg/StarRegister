package app.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint

@Configuration
@EnableWebSecurity
open class Security(disableDefaults: Boolean = false) : WebSecurityConfigurerAdapter(disableDefaults) {
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").hasRole("USER")
                .and().httpBasic().authenticationEntryPoint(getEntryPoint())
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    open fun getEntryPoint(): BasicAuthenticationEntryPoint  {
        val entryPoint = BasicAuthenticationEntryPoint()
        entryPoint.realmName = "realm"
        return entryPoint
    }

    @Autowired
    open fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("123")
                .roles("USER")
    }
}