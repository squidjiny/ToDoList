package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import javax.naming.Reference;
import java.util.Arrays;
import java.util.List;

import static com.sun.xml.bind.v2.model.core.PropertyKind.REFERENCE;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                .paths(PathSelectors.any()) // /api로 시작하는 경로만 스캔
                .build()
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()));
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Todolist 스웨거")
                .description("Todolist 기능을 실험할 수 있는 스웨거.\n" +
                        "UserController: 회원가입/회원정보수정/회원탈퇴/회원조회\n"+
                        "TodoController: 투두 생성/검색/수정. 중요한 일/오늘 할 일 조회\n")
                .version("3.0")
                .build();

    }

    private ApiKey apiKey() {
        return new ApiKey("X-AUTH-TOKEN", "X-AUTH-TOKEN", "header");
    }

    private SecurityContext securityContext() {
        return springfox.documentation
                .spi.service.contexts
                .SecurityContext
                .builder()
                .securityReferences(defaultAuth())
                .operationSelector(operationContext -> true)
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("X-AUTH-TOKEN", authorizationScopes));
    }

//    private ApiKey securityScheme(){
//        String targetHeader = "Authorization";
//        return new ApiKey(REFERENCE, targetHeader, "header");
//    }

//    private HttpAuthenticationScheme bearerAuthSecurityScheme(){
//        return HttpAuthenticationScheme.JWT_BEARER_BUILDER.name(REFERENCE).build();
//    }
}
