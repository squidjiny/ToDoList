package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

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
                .apiInfo(apiInfo());
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
}