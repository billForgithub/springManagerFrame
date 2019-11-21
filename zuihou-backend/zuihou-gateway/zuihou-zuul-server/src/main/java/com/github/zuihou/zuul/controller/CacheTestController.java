package com.github.zuihou.zuul.controller;

import java.time.LocalDateTime;

import com.github.zuihou.authority.entity.auth.User;
import com.github.zuihou.authority.enumeration.auth.Sex;
import com.github.zuihou.base.R;
import com.github.zuihou.common.constant.CacheKey;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通用 控制器
 *
 * @author zuihou
 * @date 2019/07/25
 */
@Slf4j
@RestController
@Api(value = "abcde", tags = "abcde")
public class CacheTestController {

    @Autowired
    CacheChannel cache;
    @Autowired
    CacTest cacTest;

    @GetMapping("/set")
    public R<Object> set(String region, String key) {
//        CacheChannel cache = J2Cache.getChannel();
        cache.set(region, key, User.builder().id(Long.MAX_VALUE).account(key).name("她最好").sex(Sex.M).createTime(LocalDateTime.now()).build());

        return R.success(region + key);
    }

    @GetMapping("/get")
    public R<Object> get(String region, String key) {
//        CacheChannel cache = J2Cache.getChannel();
        int check = cache.check(region, key);
        log.info("check={}", check);
//        if (check > 0) {
        CacheObject cacheObject = cache.get(region, key);
        System.out.println(cacheObject);
        return R.success(cacheObject);
//        }
//        return R.success(check);
    }

    @GetMapping("/evict")
    public R<Object> evict(String region, String key) {
//        CacheChannel cache = J2Cache.getChannel();
        cache.evict(region, key);

        return R.success(region + key);
    }

    @GetMapping("/test/get")
    public R<Object> testGet(Long id) {

        User menu = cacTest.getMenu("123");
        System.out.println(menu);
        return R.success(menu);
    }

    @GetMapping("/test/delete")
    public R<Object> testDelete(Long id) {
        cacTest.delete("123");
        return R.success(true);
    }

    @Cacheable(value = "test", key = "#id")
    public User get(Long id) {
        log.info("id={}", id);
        return User.builder()
                .account("zuihou")
                .id(id)
                .build();
    }

    @CacheEvict(value = "test", key = "#id")
    public void delete(Long id) {
        log.info("id={}", id);
    }

    @Cacheable(value = "get2", key = "#id")
    public User get2(Long id) {
        log.info("id={}", id);
        return User.builder()
                .account("zuihou")
                .id(id)
                .build();
    }
}

@Component
@Slf4j
class CacTest {
    @Cacheable(value = CacheKey.REGISTER_USER, key = "#name")
    public User getMenu(String name) {
        log.info("name={}", name);
        return User.builder()
                .account(name).name("张三李四!@#$%^&*()_123")
                .id(6079967614237410571L)
                .sex(Sex.M)
                .createTime(LocalDateTime.now())
                .build();
    }

    @CacheEvict(value = CacheKey.REGISTER_USER, key = "#name")
    public void delete(String name) {
        log.info("id={}", name);
    }

    @Cacheable(value = "zuihou")
    public User zuihou() {
        return User.builder()
                .account("zuihou").name("张三李四!@#$%^&*()_123")
                .id(6079967614237410571L)
                .sex(Sex.M)
                .createTime(LocalDateTime.now())
                .build();
    }

    @Cacheable(value = "test", key = "1")
    public User test() {
        return User.builder()
                .account("zuihou").name("张三李四!@#$%^&*()_123")
                .id(6079967614237410571L)
                .sex(Sex.M)
                .createTime(LocalDateTime.now())
                .build();
    }
}
