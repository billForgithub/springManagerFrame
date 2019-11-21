package com.github.zuihou.authority.controller.common;


import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.common.AreaSaveDTO;
import com.github.zuihou.authority.dto.common.AreaUpdateDTO;
import com.github.zuihou.authority.entity.common.Area;
import com.github.zuihou.authority.service.common.AreaService;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.dozer.DozerUtils;
import com.github.zuihou.log.annotation.SysLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 地区表
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/area")
@Api(value = "Area", tags = "地区表")
public class AreaController extends BaseController {

    @Autowired
    private AreaService areaService;
    @Autowired
    private DozerUtils dozer;

    /**
     * 分页查询地区表
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询地区表", notes = "分页查询地区表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("分页查询地区表")
    public R<IPage<Area>> page(Area data) {
        IPage<Area> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<Area> query = Wraps.lbQ(data);
        areaService.page(page, query);
        return success(page);
    }

    /**
     * 查询地区表
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询地区表", notes = "查询地区表")
    @GetMapping("/{id}")
    @SysLog("查询地区表")
    public R<Area> get(@PathVariable Long id) {
        return success(areaService.getById(id));
    }

    /**
     * 新增地区表
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增地区表", notes = "新增地区表不为空的字段")
    @PostMapping
    @SysLog("新增地区表")
    public R<Area> save(@RequestBody @Validated AreaSaveDTO data) {
        Area area = dozer.map(data, Area.class);
        areaService.save(area);
        return success(area);
    }

    /**
     * 修改地区表
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改地区表", notes = "修改地区表不为空的字段")
    @PutMapping
    @SysLog("修改地区表")
    public R<Area> update(@RequestBody @Validated(SuperEntity.Update.class) AreaUpdateDTO data) {
        Area area = dozer.map(data, Area.class);
        areaService.updateById(area);
        return success(area);
    }

    /**
     * 删除地区表
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除地区表", notes = "根据id物理删除地区表")
    @DeleteMapping(value = "/{id}")
    @SysLog("删除地区表")
    public R<Boolean> delete(@PathVariable Long id) {
        areaService.removeById(id);
        return success(true);
    }

    /**
     * 级联查询地区
     *
     * @param data 级联查询地区
     * @return 查询结果
     */
    @ApiOperation(value = "级联查询地区", notes = "级联查询地区")
    @GetMapping
    @SysLog("级联查询地区")
    public R<List<Area>> list(Area data) {
        LbqWrapper<Area> query = Wraps.lbQ(data).orderByAsc(Area::getSortValue);
        return success(areaService.list(query));
    }
}
