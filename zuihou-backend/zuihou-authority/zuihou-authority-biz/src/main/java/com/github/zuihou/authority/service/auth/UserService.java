package com.github.zuihou.authority.service.auth;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.zuihou.authority.entity.auth.User;

/**
 * <p>
 * 业务接口
 * 账号
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户id 查询数据范围
     *
     * @param userId 用户id
     * @return
     */
    Map<String, Object> getDataScopeById(Long userId);

    /**
     * 根据角色id 和 账号或名称 查询角色关联的用户
     * <p>
     * 注意，该接口只返回 id，账号，姓名，手机，性别
     *
     * @param roleId  角色id
     * @param keyword 账号或名称
     * @return
     */
    List<User> findUserByRoleId(Long roleId, String keyword);

    /**
     * 检测账号是否存在
     *
     * @param account
     * @return
     */
    boolean check(String account);

    /**
     * 修改输错密码的次数
     * @param id
     */
    void updatePasswordErrorNumById(Long id);

    /**
     * 根据账号查询用户
     * @param account
     * @return
     */
    User getByAccount(String account);

    /**
     * 修改用户最后一次登录 时间
     *
     * @param account
     */
    void updateLoginTime(String account);

    /**
     * 保存
     *
     * @param user
     * @return
     */
    User saveUser(User user);

    /**
     * 重置密码
     *
     * @param ids
     * @return
     */
    boolean reset(List<Long> ids);

    /**
     * 修改
     *
     * @param user
     * @return
     */
    User updateUser(User user);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    boolean remove(List<Long> ids);
}
