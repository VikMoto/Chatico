import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties.Registration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizationContext;
import org.springframework.security.oauth2.client.OAuth2AuthorizationFailureHandler;
import org.springframework.security.oauth2.client.OAuth2AuthorizationSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.DefaultServerOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.function.Function;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(OAuth2ClientProperties.class)
public class FacebookAuthConfig extends WebSecurityConfigurerAdapter {
    private final OAuth2ClientProperties oAuth2ClientProperties;

    @Autowired
    public FacebookAuthConfig(OAuth2ClientProperties oAuth2ClientProperties) {
        this.oAuth2ClientProperties = oAuth2ClientProperties;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login/facebook").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .loginPage("/login/facebook")
                .authorizationEndpoint()
                .baseUri("/login/facebook")
                .authorizationRequestResolver(facebookAuthorizationRequestResolver())
                .and()
                .redirectionEndpoint()
                .baseUri("/login/oauth2/code/facebook")
                .and()
                .userInfoEndpoint()
                .userService(facebookOAuth2UserService());
    }

    @Bean
    public ServerOAuth2AuthorizationRequestResolver facebookAuthorizationRequestResolver() {
        return new DefaultServerOAuth2AuthorizationRequestResolver(
                clientRegistrationRepository(),
                authorizationRequest -> {
                    OAuth2AuthorizationRequest.Builder builder = OAuth2AuthorizationRequest.from(authorizationRequest);
                    builder.authorizationUri("https://www.facebook.com/v13.0/dialog/oauth");
                    builder.tokenUri("https://graph.facebook.com/v13.0/oauth/access_token");
                    builder.redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}");
                    builder.clientId(oAuth2ClientProperties.getRegistration().get("facebook").getClientId());
                    builder.clientSecret(oAuth2ClientProperties.getRegistration().get("facebook").getClientSecret());
                    builder.scopes("email", "public_profile");
                    return builder.build();
                },
                authorizationRequest -> {
                    OAuth2AuthorizationRequest.Builder builder = OAuth2AuthorizationRequest.from(authorizationRequest);
                    builder.authorizationUri("https://www.facebook.com/v13.0/dialog/oauth");
                    builder.tokenUri("https://graph.facebook.com/v13.0/oauth/access_token");
                    builder.redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}");
                    builder.clientId(oAuth2ClientProperties.getRegistration().get("facebook").getClientId());
                    builder.clientSecret(oAuth2ClientProperties.getRegistration().get("facebook").getClientSecret());
                    builder.scopes("email", "public_profile");
                    return builder.build();
                }
        );
    }

    @Bean
    public WebClient facebookWebClient(ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientRepository authorizedClientRepository) {
        ServerOAuth2AuthorizedClientExchangeFilterFunction oauth2 = new ServerOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationRepository, authorizedClientRepository);
        oauth2.setDefaultClientRegistrationId("facebook");
        return WebClient.builder()
                .filter(oauth2)
                .build();
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService(ClientRegistrationRepository clientRegistrationRepository) {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
    }

    @Bean
    public OAuth2AuthorizedClientRepository authorizedClientRepository(OAuth2AuthorizedClientService authorizedClientService) {
        return new AuthenticatedPrincipalOAuth2AuthorizedClientRepository(authorizedClientService);
    }

    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientRepository authorizedClientRepository) {
        OAuth2AuthorizedClientProvider authorizedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder()
                .authorizationCode()
                .refreshToken()
                .clientCredentials()
                .password()
                .build();

        DefaultOAuth2AuthorizedClientManager authorizedClientManager = new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository, authorizedClientRepository);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

        return authorizedClientManager;
    }

    @Bean
    public FacebookOAuth2UserService facebookOAuth2UserService() {
        return new FacebookOAuth2UserService();
    }
}
