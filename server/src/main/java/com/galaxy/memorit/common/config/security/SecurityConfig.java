package com.galaxy.memorit.common.config.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;
import java.util.List;

import com.galaxy.memorit.common.authtemp.exception.RestAuthenticationEntryPoint;
import com.galaxy.memorit.common.authtemp.handler.OAuth2AuthenticationFailureHandler;
import com.galaxy.memorit.common.authtemp.handler.OAuth2AuthenticationSuccessHandler;
import com.galaxy.memorit.common.authtemp.handler.TokenAccessDeniedHandler;
import com.galaxy.memorit.common.authtemp.repository.OAuth2CookieRepository;
import com.galaxy.memorit.common.authtemp.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/ignore1", "ignore2");
    }

    //    @Bean
    //    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //        http.csrf().disable();
    //        http
    //            .authorizeHttpRequests((authz) -> authz
    //                //.anyRequest().authenticated()
    //                .anyRequest().permitAll()
    //            )
    //            .httpBasic(withDefaults());
    //        return http.build();
    //    }

    private final TokenAccessDeniedHandler tokenAccessDeniedHandler;
    private final OAuth2CookieRepository oAuth2CookieRepository;
    private final CustomOAuth2UserService oAuth2UserService;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .cors(withDefaults())
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .logout().disable()
            .exceptionHandling()
            .authenticationEntryPoint(new RestAuthenticationEntryPoint())
            .accessDeniedHandler(tokenAccessDeniedHandler)
            .and()
            .oauth2Login()
            .authorizationEndpoint()
            .baseUri("/oauth2/authorization")
            .authorizationRequestRepository(oAuth2CookieRepository)
            .and()
            .redirectionEndpoint()
            .baseUri("/*/oauth2/code/*")
            .and()
            .userInfoEndpoint()
            .userService(oAuth2UserService)
            .and()
            .successHandler(oAuth2AuthenticationSuccessHandler)
            .failureHandler(oAuth2AuthenticationFailureHandler);

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    //
    //    private final CorsProperties corsProperties;
    //    private final AppProperties appProperties;
    //    private final AuthTokenProvider tokenProvider;
    //    private final CustomUserDetailsService userDetailsService;
    //    private final CustomOAuth2UserService oAuth2UserService;
    //    private final TokenAccessDeniedHandler tokenAccessDeniedHandler;
    //    private final UserRefreshTokenRepository userRefreshTokenRepository;
    //
    //
    //    // UserDetailsService 설정
    //
    //    @Override
    //    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //        auth.userDetailsService(userDetailsService)
    //            .passwordEncoder(passwordEncoder());
    //    }
    //
    //    @Override
    //    protected void configure(HttpSecurity http) throws Exception {
    //        http
    //            .cors()
    //            .and()
    //            .sessionManagement()
    //            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    //            .and()
    //            .csrf().disable()
    //            .formLogin().disable()
    //            .httpBasic().disable()
    //            .exceptionHandling()
    //            .authenticationEntryPoint(new RestAuthenticationEntryPoint())
    //            .accessDeniedHandler(tokenAccessDeniedHandler)
    //            .and()
    //            .authorizeRequests()
    //            .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
    //            .antMatchers("/api/**").hasAnyAuthority(RoleType.USER.getCode())
    //            .antMatchers("/api/**/admin/**").hasAnyAuthority(RoleType.ADMIN.getCode())
    //            .anyRequest().authenticated()
    //            .and()
    //            .oauth2Login()
    //            .authorizationEndpoint()
    //            .baseUri("/oauth2/authorization")
    //            .authorizationRequestRepository(oAuth2AuthorizationRequestBasedOnCookieRepository())
    //            .and()
    //            .redirectionEndpoint()
    //            .baseUri("/*/oauth2/code/*")
    //            .and()
    //            .userInfoEndpoint()
    //            .userService(oAuth2UserService)
    //            .and()
    //            .successHandler(oAuth2AuthenticationSuccessHandler())
    //            .failureHandler(oAuth2AuthenticationFailureHandler());
    //
    //        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    //    }
    //
    //
    //    // auth 매니저 설정
    //
    //    @Override
    //    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    //    protected AuthenticationManager authenticationManager() throws Exception {
    //        return super.authenticationManager();
    //    }
    //
    //
    //    // security 설정 시, 사용할 인코더 설정
    //
    //    @Bean
    //    public BCryptPasswordEncoder passwordEncoder() {
    //        return new BCryptPasswordEncoder();
    //    }
    //
    //
    //    // 토큰 필터 설정
    //
    //    @Bean
    //    public TokenAuthenticationFilter tokenAuthenticationFilter() {
    //        return new TokenAuthenticationFilter(tokenProvider);
    //    }
    //
    //
    //    // 쿠키 기반 인가 Repository
    //    // 인가 응답을 연계 하고 검증할 때 사용.
    //
    //    @Bean
    //    public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
    //        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
    //    }
    //
    //
    //    // Oauth 인증 성공 핸들러
    //
    //    @Bean
    //    public OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
    //        return new OAuth2AuthenticationSuccessHandler(
    //            tokenProvider,
    //            appProperties,
    //            userRefreshTokenRepository,
    //            oAuth2AuthorizationRequestBasedOnCookieRepository()
    //        );
    //    }
    //
    //
    //    // Oauth 인증 실패 핸들러
    //
    //    @Bean
    //    public OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler() {
    //        return new OAuth2AuthenticationFailureHandler(oAuth2AuthorizationRequestBasedOnCookieRepository());
    //    }
    //
    //
    //    // Cors 설정
    //
    //    @Bean
    //    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
    //        UrlBasedCorsConfigurationSource corsConfigSource = new UrlBasedCorsConfigurationSource();
    //
    //        CorsConfiguration corsConfig = new CorsConfiguration();
    //        corsConfig.setAllowedHeaders(Arrays.asList(corsProperties.getAllowedHeaders().split(",")));
    //        corsConfig.setAllowedMethods(Arrays.asList(corsProperties.getAllowedMethods().split(",")));
    //        corsConfig.setAllowedOrigins(Arrays.asList(corsProperties.getAllowedOrigins().split(",")));
    //        corsConfig.setAllowCredentials(true);
    //        corsConfig.setMaxAge(corsConfig.getMaxAge());
    //
    //        corsConfigSource.registerCorsConfiguration("/**", corsConfig);
    //        return corsConfigSource;
    //    }





}
