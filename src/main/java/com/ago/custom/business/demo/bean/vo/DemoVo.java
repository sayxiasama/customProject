package com.ago.custom.business.demo.bean.vo;

import com.ago.custom.business.utils.ConventUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName:DemoVo
 * @Describe:
 * @Data:2020/5/2615:00
 * @Author:Ago
 * @Version 1.0
 */
@Data
public class DemoVo implements Serializable {

    private String code;

    private String deviceKey;

    private String dateStr;

//    public DemoVo() {
//    }

    public DemoVo(String code, String deviceKey) {
        this.code = code;
        this.deviceKey = deviceKey;
    }
}


