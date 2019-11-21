package com.github.zuihou.authority.dao.auth;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.zuihou.authority.dto.auth.ResourceQueryDTO;
import com.github.zuihou.authority.entity.auth.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 资源
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
@Repository
public interface ResourceMapper extends BaseMapper<Resource> {
    /**
     * 查询 拥有的资源
     *
     * @param resource
     * @return
     */
    List<Resource> findVisibleResource(ResourceQueryDTO resource);

    /**
     * 根据唯一索引 保存或修改资源
     *
     * @param resource
     * @return
     */
    int saveOrUpdateUnique(Resource resource);

    List<Long> findMenuIdByResourceId(@Param("resourceIdList") List<Long> resourceIdList);
}
