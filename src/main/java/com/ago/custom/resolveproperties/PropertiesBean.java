package com.ago.custom.resolveproperties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName:PropertiesBean
 * @Describe:
 * @Data:2020/5/2613:41
 * @Author:Ago
 * @Version 1.0
 */
@Component
@ConfigurationProperties(prefix="custom")
@Data
public class PropertiesBean {

    private String name;

    private String age;
}
