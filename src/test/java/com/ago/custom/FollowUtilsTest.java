package com.ago.custom;

import com.ago.custom.redisrelation.utils.FollowUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

/**
 * @ClassName:FollowUtisTest
 * @Describe:
 * @Data:2020/6/115:43
 * @Author:Ago
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FollowUtilsTest {


    @Test
    public void test(){
        for(int i = 1 ; i< 11 ; i++){
            FollowUtils.addOrRelease(String.valueOf(i), String.valueOf(10 - i));
            if(i == 5){
                break;
            }
        }
    }


    @Test
    public void getFinds(){
        Set<String> fans = FollowUtils.findFans(String.valueOf(1));
        for (String fan : fans) {
            System.out.println("fans----:"+fan);
        }

        Set<String> follows = FollowUtils.findFollows(String.valueOf(1));
        for (String follow : follows) {
            System.out.println("follow----:"+follow);

        }
    }
}
