package com.luazevedo.emprestimoBancarioII.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
@EnableWebMvc  //troquei antes usava @EnableSwagger2 e dava erro, esse funcionou!
public class SwaggerConfig {

    @Bean
    public Docket apiDocSwagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}

/* Essa versão é uma gambiarra, o padrão top é o de cima sempre!
@Bean
    public Docket apiDocSwagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.luazevedo.emprestimoBancarioII"))
                .paths(PathSelectors.any())
                .build();
    }
}
 */