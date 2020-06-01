package com.ago.custom.business.demo.dao;

import com.ago.custom.business.demo.bean.vo.DemoVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName:DemoDao
 * @Describe:
 * @Data:2020/5/2615:01
 * @Author:Ago
 * @Version 1.0
 */
public interface DemoDao {

    @Select("select code , device_key as deviceKey from t_device where del_flag = 0")
    List<DemoVo> selectVo();
}
