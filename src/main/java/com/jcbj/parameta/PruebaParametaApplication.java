package com.jcbj.parameta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class PruebaParametaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PruebaParametaApplication.class, args);
    }
    
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("API Gestion Empleados")
                .description("Esta es una API-REST que permite gestionar "
                        + "Empleados mediante las operaciones basicas de CRUD.")
                .contact(new springfox.documentation.service.Contact("Juan Bermúdez","","correo@mail.com"))
                .license("JCBJ License")
                .version("1.0")
                .build();
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("JCBJ-api-gestion-empleados")
                .apiInfo(apiInfo())
                .select().
                apis(RequestHandlerSelectors.basePackage("com.jcbj.parameta"))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag("API Empleados", "Todos los metodos relacionados con la gestión de empleados"));
    }

}
