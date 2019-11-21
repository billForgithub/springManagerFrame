package com.github.zuihou.authority.service.defaults;

/**
 * 初始化系统
 * <p>
 * 初始化规则：
 * zuihou-authority-server/src/main/resources/sql 路径存放8个sql文件 (每个库对应一个文件)
 * zuihou_base.sql            # 基础库：权限、消息，短信，邮件，文件等
 * data_zuihou_base.sql       # 基础库数据： 如初始用户，初始角色，初始菜单
 *
 * @author tangyh
 * @date 2019/10/25
 */
public interface InitSystemService {
    /**
     * 初始化系统
     *
     * @param tenant
     */
    void init(String tenant);

    /**
     * 初始化数据库
     *
     * @param tenant
     */
    void initDatabases(String tenant);

    /**
     * 初始化表
     *
     * @param tenant
     */
    void initTables(String tenant);

    /**
     * 初始化数据
     *
     * @param tenant
     */
    void initData(String tenant);
}
