package com.yaans.vending.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/*
* https://victorydntmd.tistory.com/341
* http://springfox.github.io/springfox/javadoc/2.9.2/springfox/documentation/spring/web/plugins/Docket.html
* */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private String title;
    private String version;

    public Docket api() {
        title = "Vending Machine v1.0";
        version = "1.0";

        return new Docket(DocumentationType.SWAGGER_2)
        .useDefaultResponseMessages(false)
//        .groupName("")
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.yaans.vending"))
        .paths(PathSelectors.ant("/controller/**"))
        .build()
        .apiInfo(apiInfo(title, version));
    }
    private ApiInfo apiInfo(String title, String version) {
        return new ApiInfo(title,
                "vending machine api",
                version,
                "termsOfServiceUrl@Nothing",
                new Contact("yaans", "github.com/koo-ys", "blank@email.com"),
                "no license",
                "blank",
                new ArrayList<>()
        );
    }
}
