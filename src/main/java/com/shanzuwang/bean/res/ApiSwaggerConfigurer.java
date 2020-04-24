package com.shanzuwang.bean.res;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger 配置文件
 *
 * Created by Floki on 2017/8/10.
 */
@Configuration
@EnableSwagger2
public class ApiSwaggerConfigurer extends WebMvcConfigurerAdapter {

    /**
     * 注册swagger-ui.html
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        /*registry.addResourceHandler("/webjars*")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");*/
    }

    @Bean
    public Docket createSystemManageRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("用户管理")
                .apiInfo(apiInfo("包含客户列表 等系统自带都涵盖了"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.shanzuwang.web.user"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket productManageRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("产品管理")
                .apiInfo(apiInfo("闪租网产品管理服务提供的 RestFul APIs"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.shanzuwang.web.product"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket extraManageRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("通用管理")
                .apiInfo(apiInfo("闪租网后台管理系统 通用分类管理"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.shanzuwang.web.extra"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket websiteManageRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("网站管理")
                .apiInfo(apiInfo("闪租网后台管理系统 网站管理 轮播图等"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.shanzuwang.web.website"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(String description) {// 创建API的基本信息，这些信息会在Swagger UI中进行显示
        return new ApiInfoBuilder()
                .title("使用 Swagger2 构建的 RestFul APIs")// API 标题
                .description(description)// API描述
                .contact(new Contact("Floki", "", "1466181575@qq.com")) //联系人
                .version("1.0")// 版本号
                .build();
    }
}
