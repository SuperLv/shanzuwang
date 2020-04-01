package com.shanzuwang.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shanzuwang.config.annotation.AccessIntercept;
import com.shanzuwang.config.annotation.UserAnnotationMethodArgumentResolver;
import com.shanzuwang.config.properties.HttpConfigProperties;
import com.shanzuwang.interceptor.BaseInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author hsx
 * create at 2020-01-30
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({HttpConfigProperties.class})
public class WebConfig   implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private HttpConfigProperties httpConfigProperties;

    /**
     * webmvc配置
     * @return
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {

        //配置加载拦截器
        return new WebMvcConfigurer() {

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                Map<String, BaseInterceptor> interceptors=applicationContext.getBeansOfType(BaseInterceptor.class);
                List<BaseInterceptor> interceptorList = new ArrayList<>(interceptors.values());
                interceptorList.sort(Comparator.comparingInt(BaseInterceptor::getOrder));
                interceptorList.forEach(interceptor -> registry.addInterceptor(interceptor));
//                registry.addInterceptor(new AccessIntercept()).addPathPatterns("/**");
            }

            @Override
            public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
                WebMvcConfigurer.super.addArgumentResolvers(argumentResolvers);
                argumentResolvers.add(new UserAnnotationMethodArgumentResolver());
            }
        };
    }

    /**
     * 自定义配置restTemplate
     *
     * @param clientHttpRequestFactory http请求工厂
     * @return RestTemplate
     */
    @Bean
    public RestTemplate restTemplate(@Qualifier("clientHttpRequestFactory") ClientHttpRequestFactory
                                             clientHttpRequestFactory) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(clientHttpRequestFactory);
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        if(log.isDebugEnabled()){
            log.debug("--初始化restTemplate完成");
        }
        return restTemplate;
    }

    /**
     * 配置http请求工厂
     *
     * @return http请求工厂
     */
    @Bean(name = "clientHttpRequestFactory")
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        // 长连接保持20秒,如有其它请求可以复用该链接
        PoolingHttpClientConnectionManager pollingConnectionManager = new PoolingHttpClientConnectionManager
                (httpConfigProperties.getTimeToLive(), TimeUnit.SECONDS);
        // 总连接数
        pollingConnectionManager.setMaxTotal(httpConfigProperties.getMaxTotal());
        // 同路由的并发数
        pollingConnectionManager.setDefaultMaxPerRoute(httpConfigProperties.getMaxPerRoute());
        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setConnectionManager(pollingConnectionManager);
        // 重试次数，默认是3次，没有开启
        //httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(2, true))
        // 保持长连接配置，需要在头添加Keep-Alive
        httpClientBuilder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
        headers.add(new BasicHeader("Connection", "Keep-Alive"));
        httpClientBuilder.setDefaultHeaders(headers);
        HttpClient httpClient = httpClientBuilder.build();
        // httpClient连接配置，底层是配置RequestConfig
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory
                (httpClient);
        // 连接超时时间
        clientHttpRequestFactory.setConnectTimeout(httpConfigProperties.getConnectTimeout());
        // 数据读取超时时间
        clientHttpRequestFactory.setReadTimeout(httpConfigProperties.getReadTimeout());
        // 连接不够用的等待时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的
        clientHttpRequestFactory.setConnectionRequestTimeout(httpConfigProperties.getConnectionRequestTimeout());
        clientHttpRequestFactory.setBufferRequestBody(httpConfigProperties.isBufferRequestBody());
        return clientHttpRequestFactory;
    }


    @Bean
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder){
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        /** 所有对象都将按如下的规则进行序列化
         * Include.Include.ALWAYS 默认
         * Include.NON_DEFAULT 属性为默认值的不序列化
         * Include.NON_EMPTY 属性为 空（""） 或者为 NULL 都不序列化，即不返回该字段
         * Include.NON_NULL 属性为NULL 不序列化,就是为null的字段不参加序列化 **/
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        /** 将null值转为""(需要 objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS)才行)
        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>()
        {
            @Override
            public void serialize(Object o, JsonGenerator jsonGenerator,
                                  SerializerProvider serializerProvider)
                    throws IOException, JsonProcessingException
            {
                if(o instanceof String || o instanceof Number){
                    jsonGenerator.writeString("");
                }else if (o instanceof Collection){
                    jsonGenerator.writeString("[]");
                }else if (o instanceof Map) {
                    jsonGenerator.writeString("{}");
                }else {
                    jsonGenerator.writeString("{}");
                }
            }
        });**/
        return objectMapper;
    }


    /**
     * 配置国际化messageSource
     * @return
     */
//    @Bean("messageSource")
//    public MessageSource messageSource() {
//        ReloadableResourceBundleMessageSource messageSource =
//                new ReloadableResourceBundleMessageSource();
//        messageSource.setBasename("classpath:message");
//        messageSource.setCacheSeconds(300);
//        return messageSource;
//    }

    /**
     * 配置validator工厂使用国际化资源
     * @return
     */
//    @Bean
//    public ValidatorFactory validatorFactory(MessageSource messageSource) {
//        LocalValidatorFactoryBean validatorFactory=new LocalValidatorFactoryBean();
//        validatorFactory.setProviderClass(HibernateValidator.class);
//        validatorFactory.setValidationMessageSource(messageSource);
//        return validatorFactory;
//    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    @Autowired
    public void setHttpConfigProperties(HttpConfigProperties httpConfigProperties) {
        this.httpConfigProperties = httpConfigProperties;
    }
}
