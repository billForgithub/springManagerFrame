package com.github.zuihou.file.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.file.domain.FileStatisticsDO;
import com.github.zuihou.file.dto.FileOverviewDTO;
import com.github.zuihou.file.dto.FileStatisticsAllDTO;
import com.github.zuihou.file.service.FileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 文件用户大小表 前端控制器
 * </p>
 *
 * @author luosh
 * @since 2019-05-07
 */
@Slf4j
@RestController
@RequestMapping("/statistics")
@Api(value = "Statistics", tags = "统计接口")
public class StatisticsController extends BaseController {

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "云盘首页数据概览", notes = "云盘首页数据概览")
    @GetMapping(value = "/overview")
    public R<FileOverviewDTO> overview() {
        return success(fileService.findOverview(getUserId(), null, null));
    }

    @ApiOperation(value = "按照类型，统计各种类型的 大小和数量", notes = "按照类型，统计当前登录人各种类型的大小和数量")
    @GetMapping(value = "/type")
    public R<List<FileStatisticsDO>> findAllByDataType() {
        return success(fileService.findAllByDataType(getUserId()));
    }

    @ApiOperation(value = "云盘首页个人文件下载数量排行", notes = "云盘首页个人文件下载数量排行")
    @GetMapping(value = "/downTop20")
    public R<List<FileStatisticsDO>> downTop20() {
        return success(fileService.downTop20(getUserId()));
    }

    @ApiOperation(value = "按照时间统计各种类型的文件的数量和大小", notes = "按照时间统计各种类型的文件的数量和大小 不指定时间，默认查询一个月")
    @GetMapping(value = "")
    public R<FileStatisticsAllDTO> findNumAndSizeToTypeByDate(@RequestParam(value = "startTime", required = false) LocalDateTime startTime,
                                                              @RequestParam(value = "endTime", required = false) LocalDateTime endTime) {
        return success(fileService.findNumAndSizeToTypeByDate(getUserId(), startTime, endTime));
    }

    @ApiOperation(value = "按照时间统计下载数量", notes = "按照时间统计下载数量 不指定时间，默认查询一个月")
    @GetMapping(value = "/down")
    public R<FileStatisticsAllDTO> findDownSizeByDate(@RequestParam(value = "startTime", required = false) LocalDateTime startTime,
                                                      @RequestParam(value = "endTime", required = false) LocalDateTime endTime) {
        Long userId = getUserId();
        return success(fileService.findDownSizeByDate(userId, startTime, endTime));
    }


}
