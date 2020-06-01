package com.ago.custom.resolveproperties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @ClassName:NoComponentResolveProperties
 * @Describe:
 * @Data:2020/5/2613:46
 * @Author:Ago
 * @Version 1.0
 */
@Data
@PropertySource("classpath:custom.properties")
@Component
public class ResolvePropertiesResource {

    @Value("${email}")
    private String email;
}
