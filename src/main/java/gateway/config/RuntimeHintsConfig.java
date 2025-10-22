package gateway.config;

import org.springframework.aot.hint.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;

@ImportRuntimeHints(RuntimeHintsConfig.SsmsRuntimeHintsRegistrar.class)
@Configuration
class RuntimeHintsConfig {

    static class SsmsRuntimeHintsRegistrar implements RuntimeHintsRegistrar {

        @Override
        public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
            // hints.reflection()
            //      .registerType(TypeReference.of("com.github.benmanes.caffeine.cache.SSMSA"),
            //                     MemberCategory.PUBLIC_FIELDS,MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_PUBLIC_METHODS);
        }
    }
}