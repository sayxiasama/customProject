package com.ago.custom.testdboperation;

import org.apache.ibatis.annotations.Insert;

/**
 * @ClassName:TestDbOperation
 * @Describe:
 * @Data:2020/6/510:15
 * @Author:Ago
 * @Version 1.0
 */
public interface TestDbOperation {

    @Insert("insert into user (account,password , name , age , email) values ('zhangsan','123','张三','22','ago@outlook.com')")
    void AsyncSave();
}
