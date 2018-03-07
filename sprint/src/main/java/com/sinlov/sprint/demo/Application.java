package com.sinlov.sprint.demo;

import com.google.common.base.Predicate;
import com.sinlov.sprint.demo.conf.RouterConf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// SpringBootApplication = Configuration + ComponentScan + EnableAutoConfiguration
//@Configuration
//@ComponentScan
//@EnableAutoConfiguration

//@EnableWebMvc
@SpringBootApplication
@EnableSwagger2
@ComponentScan({
        "com.sinlov.sprint.demo.domain",
        "com.sinlov.sprint.demo.hello",
})
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
        System.out.println("Let's inspect the beans provided by Spring Boot:");
//        String[] beanNames = ctx.getBeanDefinitionNames();
//        Arrays.sort(beanNames);
//        for (String beanName : beanNames) {
//            System.out.println(beanName);
//        }
    }

    @Bean
    public Docket testApi() {
        // appInfo
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title(RouterConf.TAG)
                .description("sinlov demo")
                .build();
        // path format
        String pathFormat = String.format("/%s.*", RouterConf.TAG);
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Test")
                .apiInfo(apiInfo)
                .select()
                .paths(
                        PathSelectors.regex(pathFormat)
                ).build();
    }
}