package com.ago.custom;

import com.ago.custom.schedule.schedule.CronTaskRegistrar;
import com.ago.custom.resolveproperties.PropertiesBean;
import com.ago.custom.resolveproperties.ResolvePropertiesResource;
import com.ago.custom.zframeextends.ApplicationContextCustom;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Map;


@EnableAsync
@SpringBootApplication()
@MapperScan(basePackages = {"com.ago.custom.business.*.dao", "com.ago.custom.testdboperation"})
@ConfigurationPropertiesScan({"com.ago.custom.resolveproperties"})
public class CustomDemoApplication implements InitializingBean {

    @Autowired
    private PropertiesBean custom;

//    @Autowired
//    private ResolvePropertiesResource resolve;

    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;

    public static void main(String[] args) {
        SpringApplication.run(CustomDemoApplication.class, args);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(custom.toString());

//        System.out.println(resolve.toString());
    }


}
