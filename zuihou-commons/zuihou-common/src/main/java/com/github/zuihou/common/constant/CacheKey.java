package com.github.zuihou.common.constant;

import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.utils.StrPool;

import cn.hutool.core.util.StrUtil;

/**
 * 用于同一管理和生成缓存的key， 避免多个项目使用的key重复
 * <p>
 * 使用@Cacheable时， 其value值一定要在此处指定
 *
 * @author zuihou
 * @date 2019/08/06
 */
public interface CacheKey {
    // 权限系统缓存 start

    /**
     * 验证码 前缀
     * 完整key: captcha:{key} -> str
     */
    String CAPTCHA = "captcha";


    /**
     * 菜单 前缀
     * 完整key: menu:{menuId} -> obj
     */
    String MENU = "menu";

    /**
     * 资源 前缀
     * 完整key: resource:{resourceId} -> obj
     */
    String RESOURCE = "resource";

    /**
     * 角色 前缀
     * 完整key: role:{roleId}
     */
    String ROLE = "role";
    /**
     * 角色拥有那些菜单 前缀
     * 完整key: role_menu:{ROLE_ID} -> [MENU_ID, MENU_ID, ...]
     */
    String ROLE_MENU = "role_menu";
    /**
     * 角色拥有那些资源 前缀
     * 完整key: role_resource:{ROLE_ID} -> [RESOURCE_ID, ...]
     */
    String ROLE_RESOURCE = "role_resource";
    /**
     * 角色拥有那些组织 前缀
     * 完整key: role_org:{ROLE_ID} -> [ORG_ID, ...]
     */
    String ROLE_ORG = "role_org";

    /**
     * 用户拥有那些角色 前缀
     * 完整key: user_role:{USER_ID} -> [ROLE_ID, ...]
     */
    String USER_ROLE = "user_role";
    /**
     * 用户拥有的菜单 前缀
     * 完整key: user_menu:{userId} -> [MENU_ID, MENU_ID, ...]
     */
    String USER_MENU = "user_menu";
    /**
     * 用户拥有的资源 前缀
     * 完整key: user_resource:{userId} -> [RESOURCE_ID, ...]
     */
    String USER_RESOURCE = "user_resource";

    /**
     * 登录总次数
     * login_log_total:{TENANT} -> Long
     */
    String LOGIN_LOG_TOTAL = "login_log_total";
    /**
     * 今日登录总次数
     * login_log_today:{TENANT}:{today} -> Long
     */
    String LOGIN_LOG_TODAY = "login_log_today";
    /**
     * 今日登录总ip
     * login_log_todayip:{TENANT}:{today} -> Map
     */
    String LOGIN_LOG_TODAY_IP = "login_log_todayip";
    /**
     * 最近10访问记录
     * login_log_tenday:{TENANT}:{today}:{account} -> Map
     */
    String LOGIN_LOG_TEN_DAY = "login_log_tenday";
    /**
     * 登录总次数
     * login_log_browser:{TENANT} -> Map
     */
    String LOGIN_LOG_BROWSER = "login_log_browser";
    /**
     * 登录总次数
     * login_log_system{TENANT} -> Map
     */
    String LOGIN_LOG_SYSTEM = "login_log_system";


    // 权限系统缓存 end


    // 消息服务缓存 start
    /**
     * 用户注册 前缀
     * 完整key: register:{注册类型}:{手机号}
     */
    String REGISTER_USER = "register";
    // 消息服务缓存 end

    /**
     * 构建key
     *
     * @param args
     * @return
     */
    static String buildTenantKey(Object... args) {
        if (args.length > 0) {
            return StrUtil.join(StrPool.COLON, BaseContextHandler.getTenant(), args);
        } else {
            return BaseContextHandler.getTenant();
        }
    }

    /**
     * 构建没有租户信息的key
     *
     * @param args
     * @return
     */
    static String buildKey(Object... args) {
        if (args.length == 1) {
            return String.valueOf(args[0]);
        } else if (args.length > 0) {
            return StrUtil.join(StrPool.COLON, args);
        } else {
            return "";
        }
    }
}
