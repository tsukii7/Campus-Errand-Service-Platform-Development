package com.example.ordertaker.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@OpenAPIDefinition(info = @Info(title = "CS304 Campus Errand Service Platform Documentation ",version = "v1.0",description = "Api Documentation for CS304 Project by 1114 group"),
        externalDocs = @ExternalDocumentation(description = "Github Repo",url = "https://github.com/sustech-cs304/team-project-1114"))
@Configuration
public class SwaggerConfig {


    private ApiInfo apiInfo() {
        Contact contact = new
                Contact("CS304_1114_group", "https://github.com/sustech-cs304/team-project-1114", "12012338@mail.sustech.edu.cn");

        return new ApiInfo(
                "CS304 Campus Errand Service Platform Documentation",
                "Api Documentation for CS304 Project",
                "1.0",
                "https://github.com/sustech-cs304/team-project-1114",contact,
                "Apache 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
    }

    @Bean
    public Docket docket(Environment environment) {
        Profiles profiles = Profiles.of("dev", "pro");
        boolean flag = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag)
                .select()

//                .apis(RequestHandlerSelectors.basePackage("com.example.ordertaker.controller"))
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }


}
