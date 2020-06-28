package com.ago.custom.quartz.propertiesUtil;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurablePropertyResolver;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySources;
import org.springframework.core.env.PropertySourcesPropertyResolver;

import java.util.Map;

/**
 * @ClassName:ProperHandlerBean
 * @Describe:
 * @Data:2020/6/1810:23
 * @Author:Ago
 * @Version 1.0
 */
public class ProperHandlerHandler extends PropertySourcesPlaceholderConfigurer {

    private static Map<String, String> propertyMap;

}
