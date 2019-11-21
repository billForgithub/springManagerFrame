package com.github.zuihou.common.constant;

/**
 * 业务常量
 *
 * @author zuihou
 * @date 2019/08/06
 */
public interface BizConstant {
    /**
     * 初始化的租户管理员角色
     */
    Long INIT_ROLE_ID = 100L;

    /**
     * 演示用的组织ID
     */
    Long DEMO_ORG_ID = 101L;
    /**
     * 演示用的岗位ID
     */
    Long DEMO_STATION_ID = 101L;

    /**
     * 默认MD5密码：123456
     */
    String DEF_PASSWORD = "e10adc3949ba59abbe56e057f20f883e";

    /**
     * 默认的定时任务组
     */
    String DEF_JOB_GROUP_NAME = "zuihou-jobs-server";
    /**
     * 短信发送处理器
     */
    String SMS_SEND_JOB_HANDLER = "smsSendJobHandler";


}
