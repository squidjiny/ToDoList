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
                .description("Todolist 기능을 실험할 수 있는 스웨거.<br> " +
                        "테스트 계정:<br>" +
                                "ROLE_USER 계정: userid = 1, username = usertest, password = usertest<br>"+
                                "ROLE_ADMIN 계정: userid = 2, username = admintest, password = admintest<br>"+
                        "토큰 발급 방법:<br>"+
                        "1. signincontroller에서 로그인하고 싶은 역할의 유저의 username과 password를 입력한다.<br>"+
                        "2. responsebody의 token을 복사한다.<br>"+
                        "3. 오른쪽 상단의 Authorize를 클릭 후 토큰을 넣어서 로그인한다."
                        )
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
