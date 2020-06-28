package com.ago.custom;

import sun.plugin.javascript.navig.Link;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName:Test
 * @Describe:
 * @Data:2020/6/2415:53
 * @Author:Ago
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) {

        Map<String,String> map = new HashMap(2);
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");
        map.put("5", "5");
//        map.put("1", 1);
//        map.put("2", 2);
//        map.put("3", 3);
//        map.put("4", 4);
//        map.put("5", 5);

        for (Object key : map.keySet()) {
            System.out.println(map.get(key));
        }
    }
}
