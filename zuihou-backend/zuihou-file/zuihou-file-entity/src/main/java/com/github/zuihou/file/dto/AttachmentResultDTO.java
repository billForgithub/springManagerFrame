package com.github.zuihou.file.dto;

import java.util.List;

import com.github.zuihou.file.entity.Attachment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 附件返回实体
 *
 * @author zuihou
 * @date 2018/12/12
 */
@Data
@ApiModel(value = "AttachmentResult", description = "附件列表")
public class AttachmentResultDTO {
    @ApiModelProperty(value = "业务id")
    private String bizId;
    @ApiModelProperty(value = "业务类型")
    private String bizType;
    @ApiModelProperty(value = "附件列表")
    private List<Attachment> list;
}



