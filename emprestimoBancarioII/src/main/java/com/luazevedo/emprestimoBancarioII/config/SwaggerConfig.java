package com.luazevedo.emprestimoBancarioII.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
//@EnableWebMvc//@EnableWebMvc  //troquei antes usava @EnableSwagger2 e dava erro, esse funcionou!
public class SwaggerConfig {

    @Bean
    public OpenAPI apiDocSwagger(){
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo(){
        return new Info()
                .title("API de Empréstimos Bancários - Lu Azevedo")
                .description("Documentação da API para gerenciamente de empréstimos bancários.")
                .version("1.0.0")
                .contact(new Contact()
                        .name("Luciene Azevedo")
                        .url("http://www/luazevedo.com")
                        .email("contato@luazevedo.com"));
    }
//    @Bean
//    public OpenAPI apiDocSwagger() {
//        return new OpenAPI().components(new Components()).info(apiInfo());
//    }
//    // Método para definir o título, descrição e informações da API no Swagger UI
//    private Info apiInfo() {
//        return new Info()
//                .title("API de Empréstimos Bancários - Lu Azevedo")  // Título personalizado
//                .description("Documentação da API para gerenciamento de empréstimos bancários.")  // Descrição
//                .version("1.0.0")  // Versão da API
//                .contact(new Contact());  // Informações de contato "Luciene Azevedo", "www.luazevedo.com", "contato@lluazevedo.com"
//    }
}

