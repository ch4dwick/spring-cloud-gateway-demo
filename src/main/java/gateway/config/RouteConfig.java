package gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

//@Configuration
public class RouteConfig {
    
//    protected ScorpionAuthenticationConfig authConfig;
    
//    @Autowired
//    public RouteConfig(ScorpionAuthenticationConfig authConfig) {
//        this.authConfig = authConfig;
//    }
    
    /**
     * Scorpion Routes
     * @param builder
     * @return
     */
//    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                
                // Code version of the Gateway filter in the application.yml file keeping this around for reference.
//                .route("remote01",
//                        r -> r.path("/remote01/**").filters(
//                                f -> f
//                                // Apply the JWT Authentication
//                                .filter(new JwtAuthGatewayFilterFactory()
//                                        .apply(c -> c.setHeaderKey(authConfig.getJwt().getHeaderKey()).setIsEnabled("true")))
//                                // Strip the service name because we don't need it on the other end.
//                                .filter(new StripPrefixGatewayFilterFactory()
//                                        .apply(c -> c.setParts(1)))
//                                )
//                        .uri("lb://remote01"))
                .build();
    }
}
