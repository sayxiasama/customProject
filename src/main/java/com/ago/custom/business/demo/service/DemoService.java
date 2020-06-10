package com.ago.custom.business.demo.service;

import com.ago.custom.business.demo.bean.vo.DemoVo;
import com.ago.custom.business.demo.dao.DemoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName:DemoService
 * @Describe:
 * @Data:2020/5/2615:04
 * @Author:Ago
 * @Version 1.0
 */
@Service
public class DemoService {

    @Autowired
    private DemoDao dao;

    public List<DemoVo> selectVo() {
        return dao.selectVo();
    }
}
