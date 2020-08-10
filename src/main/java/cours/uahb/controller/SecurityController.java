//package cours.uahb.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//public class SecurityController extends WebSecurityConfigurerAdapter
//{
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    /**
//     * Methode de stockage de user
//     * @param auth
//     * @throws Exception
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception
//    {
//        //Use default user with generated password   avec super
////        super.configure(auth);
//        /**
//         * USER in memory without encryption
//         */
////        auth.inMemoryAuthentication().withUser("thera").password("{noop}passer1234").roles("USER","ADMIN");
////        auth.inMemoryAuthentication().withUser("master").password("{noop}passer1234").roles("USER");
//
//        /**
//         * USER in memory with BCRYPT encoder
//         */
//        auth.inMemoryAuthentication().withUser("thera").password(passwordEncoder.encode("passer1234")).roles("USER","ADMIN");
//        auth.inMemoryAuthentication().withUser("master").password(passwordEncoder.encode("passer1234")).roles("USER");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception
//    {
//
//        //Je definis une page pour le login instead of default page login
//        //http.formLogin().loginPage("/login");
//
//        /**
//         * Je demande à utiliser un formulaire pour m'authentifier
//         * Je veux le formulaire par default pour l'auth
//         */
//        http.formLogin();
//
//
//        //Authentification basic
//        //http.httpBasic();
//
//        //Toutes les requetes necessitent une authentification
//        http.authorizeRequests().anyRequest().authenticated();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder()
//    {
//        return new BCryptPasswordEncoder();
//    }
//}
