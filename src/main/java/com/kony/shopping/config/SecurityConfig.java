package com.kony.shopping.config;


import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {


//    @Bean
//    @Order(1)
//    SecurityFilterChain asSecurityFilterChain(HttpSecurity http) throws Exception {
//        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
//        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
//                .authorizationEndpoint(
//                        a-> a.authenticationProvider()
//                ).oidc(
//                        Customizer.withDefaults()
//                );
//        return http.build();
//    }

//    @Bean
//    Customizer<List<AuthenticationProvider>> providerCustomizer(){
//        return providers ->{
//            for (AuthenticationProvider p: providers){
//                if (p instanceof OAuth2AuthorizationCodeRequestAuthenticationProvider x){
//                    x.setAuthenticationValidator();
//                }
//            }
//        }
//    }



}
