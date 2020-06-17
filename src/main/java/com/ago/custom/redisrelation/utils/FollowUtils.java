package com.ago.custom.redisrelation.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName:FollowUtils
 * @Describe:
 * @Data:2020/6/114:45
 * @Author:Ago
 * @Version 1.0
 */
public class FollowUtils {

    private static final String FOLLOWING = "FOLLOWING_"; //关注前缀
    private static final String FANS = "FANS_";//粉丝前缀
    private static final String COMMON_KEY = "COMMON_FOLLOWING"; //公共关注

    private static RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextHolder.getBean("redisTemplate", RedisTemplate.class);


    //关注Or取消关注 (0.取消关注 1.关注)
    public static int addOrRelease(String userId, String followingId) {
        if (userId == null || followingId == null) {
            return 0;
        }

        int followFlag = 0;
        String followingKey = FOLLOWING + userId;
        String fansKey = FANS + followingId;
        if (redisTemplate.opsForZSet().rank(followingKey, followingId) == null) {
            redisTemplate.opsForZSet().add(followingKey, followingId, System.currentTimeMillis());
            redisTemplate.opsForZSet().add(fansKey, userId, System.currentTimeMillis());
            followFlag = 1;
        } else {
            redisTemplate.opsForZSet().remove(followingKey, followingId);
            redisTemplate.opsForZSet().remove(fansKey, fansKey);
        }
        return followFlag;
    }

    /*判断当前用户与其他用户的关系
     0.无关系 1.自己 2.当前用户关联了otherId 3.otherId关注了当前用户 4.互关
    */
    public static int checkRelations(String userId, String otherId) {
        if (userId == null || otherId == null) {
            return 0;
        }

        if (userId.equals(otherId)) {
            return 1;
        }
        int relationFlag = 0;
        String followingKey = FOLLOWING + userId;
        if (redisTemplate.opsForZSet().rank(followingKey, otherId) != null) {
            relationFlag = 2;
        }
        String fansKey = FANS + userId;
        if (redisTemplate.opsForZSet().rank(fansKey, otherId) != null) {
            relationFlag = 3;
        }

        if (redisTemplate.opsForZSet().rank(followingKey, otherId) != null && redisTemplate.opsForZSet().rank(fansKey, otherId) != null) {
            relationFlag = 4;
        }

        return relationFlag;
    }

    //查询当前用户关注的名单
    public static Set<String> findFollows(String userId) {
        return getOrDefault(FOLLOWING + userId);
    }

    //查询当前用户粉丝
    public static Set<String> findFans(String userId) {
        return getOrDefault(FANS + userId);
    }

    //查询指定两个id的共同关注
    public static Set<String> findCommonFollow(String userId, String otherUserId) {
        if (userId == null || otherUserId == null) {
            return new HashSet<>();
        }

        String commonKey = COMMON_KEY + userId + "_" + otherUserId;
        redisTemplate.opsForZSet().intersectAndStore(commonKey, FOLLOWING + userId, FOLLOWING + otherUserId);
        Set result = redisTemplate.opsForZSet().reverseRange(commonKey, 0, -1);
        redisTemplate.opsForZSet().remove(commonKey);
        return result;
    }


    public static Set<String> getOrDefault(String key) {
        Set result = redisTemplate.opsForZSet().reverseRange(key, 0, -1);
        return result == null ? new HashSet<String>() : result;
    }
}
