package com.ago.custom;

import com.ago.custom.schedule.schedule.CronTaskRegistrar;
import com.ago.custom.resolveproperties.PropertiesBean;
import com.ago.custom.resolveproperties.ResolvePropertiesResource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@SpringBootApplication
@MapperScan(basePackages = {"com.ago.custom.business.*.dao", "com.ago.custom.testdboperation"})
public class CustomDemoApplication implements InitializingBean {

    @Autowired
    private PropertiesBean custom;

    @Autowired
    private ResolvePropertiesResource resolve;

    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;

    public static void main(String[] args) {
        SpringApplication.run(CustomDemoApplication.class, args);
        /*SpringApplication springApplication = new SpringApplication();
        springApplication.addInitializers(new ApplicationContextCustom());
        springApplication.run(args);*/
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(custom.toString());

        System.out.println(resolve.toString());
    }


}
