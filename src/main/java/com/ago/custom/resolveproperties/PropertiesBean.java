package com.ago.custom.resolveproperties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * @ClassName:PropertiesBean
 * @Describe:
 * @Data:2020/5/2613:41
 * @Author:Ago
 * @Version 1.0
 */
@ConstructorBinding
@ConfigurationProperties(prefix ="custom")
@Getter
public class PropertiesBean {

    private final String name;

    private  String age;

    private final InnerClass innerClass;

    public PropertiesBean(String name,InnerClass innerClass) {
        this.name = name;
//        this.age = age;
        this.innerClass = innerClass;
    }


    @Getter
    public static class InnerClass{
        private String sex;

        private String des;

        public InnerClass(String sex , String des){
            this.sex = sex;
            this.des = des;
        }
        public String toString(){
            return this.sex + this.des;
        }
    }

    public String toString(){

        return "name"+this.getName()+"\n"+"age"+this.getAge()+"\n"+"sex"+this.innerClass.getSex()+"des"+this.innerClass.getDes();
    }
}
