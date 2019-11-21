package com.xxl.job.admin.dao;

import java.util.List;

import com.xxl.job.admin.core.model.XxlJobInfo;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * job info
 *
 * @author xuxueli 2016-1-12 18:03:45
 */
//@Mapper
@Repository
public interface XxlJobInfoDao {

    public List<XxlJobInfo> pageList(@Param("offset") Integer offset,
                                     @Param("pagesize") Integer pagesize,
                                     @Param("jobGroup") Integer jobGroup,
                                     @Param("jobDesc") String jobDesc,
                                     @Param("executorHandler") String executorHandler,
                                     @Param("type") Integer type);

    public int pageListCount(@Param("offset") Integer offset,
                             @Param("pagesize") Integer pagesize,
                             @Param("jobGroup") Integer jobGroup,
                             @Param("jobDesc") String jobDesc,
                             @Param("executorHandler") String executorHandler,
                             @Param("type") Integer type);

    public int save(XxlJobInfo info);

    public XxlJobInfo loadById(@Param("id") Integer id);

    public int update(XxlJobInfo item);

    public int delete(@Param("id") Integer id);

    public List<XxlJobInfo> getJobsByGroup(@Param("jobGroup") Integer jobGroup);

    public int findAllCount();

}
