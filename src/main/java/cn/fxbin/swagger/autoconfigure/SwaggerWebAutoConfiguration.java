package cn.fxbin.swagger.autoconfigure;

import cn.fxbin.swagger.SwaggerProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger.configuration.SwaggerCommonConfiguration;
import springfox.documentation.swagger2.configuration.Swagger2WebMvcConfiguration;
import springfox.documentation.swagger2.web.Swagger2ControllerWebMvc;

import static cn.fxbin.swagger.SwaggerProperties.SPRING_SWAGGER_PREFIX;

/**
 * SwaggerWebAutoConfiguration
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/3/31 18:54
 */
@Configuration(
        proxyBeanMethods = false
)
@EnableConfigurationProperties(SwaggerProperties.class)
@ConditionalOnWebApplication(type = Type.SERVLET)
@ConditionalOnClass({SwaggerCommonConfiguration.class, Swagger2WebMvcConfiguration.class, Swagger2ControllerWebMvc.class})
@ConditionalOnProperty(prefix = SPRING_SWAGGER_PREFIX, name = "enabled", havingValue = "true")
public class SwaggerWebAutoConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
