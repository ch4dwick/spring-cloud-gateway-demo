package gateway.filters;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>The GatewayFilterFactory suffix is a coding standard.</p>
 * <p>i.e. JwtAuthGatewayFilterFactory is spring.cloud.gateway.routes[0].filters[0]=JwtAuth</p>
 * @author Chad
 *
 */
@Component
@Slf4j
public class JwtAuthGatewayFilterFactory extends AbstractGatewayFilterFactory<JwtAuthGatewayFilterFactory.Config> {
    
    public JwtAuthGatewayFilterFactory() {
        super(Config.class);
    }
    
    /**
     * <p>The sequence of fields in the config</p>
     * 
     * <p>i.e. spring.cloud.gateway.routes[0].filters[0]=JwtAuth=token,false</p>
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("headerKey", "isEnabled");
    }

    /**
     * This demo assumes you want to authenticate a token stored in the header.
     */
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String token = request.getHeaders().getFirst(config.getHeaderKey());
            
            // Simple validation. Ideally, this is where you add logic for your filter.
            if (config.isEnabled() && token == null) {
                log.warn("Remote: {}", exchange.getRequest().getRemoteAddress().getHostName());
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
            return chain.filter(exchange);
        };
    }
    
    /**
     * As declared in the method shortcutFieldOrder() above, the sequence of fields maps the config fields below.
     * This sample Config class configures the header name to check when apply() is executed. I also included an isEnabled
     * field for a use case we had that let us temporarily disable the filter. I know this looks redundant but just wanted
     * to show an example of having two values in a Config. 
     * @author Chad
     *
     */
    @Validated
    public static class Config {
        @NotEmpty
        private String headerKey;
        
        // Because this currently only supports string.
        private String isEnabled = "true";

        public String getHeaderKey() {
            return headerKey;
        }

        public Config setHeaderKey(String headerKey) {
            this.headerKey = headerKey;
            return this;
        }

        public boolean isEnabled() {
            return isEnabled.equalsIgnoreCase("true");
        }

        public Config setIsEnabled(String isEnabled) {
            this.isEnabled = isEnabled;
            return this;
        }
    }
}