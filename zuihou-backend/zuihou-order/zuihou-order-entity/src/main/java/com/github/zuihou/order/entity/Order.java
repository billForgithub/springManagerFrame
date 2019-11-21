package com.github.zuihou.order.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.base.entity.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/**
 * <p>
 * 实体类
 * 订单
 * </p>
 *
 * @author zuihou
 * @since 2019-08-13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("m_order")
@ApiModel(value = "Order", description = "订单")
public class Order extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @Length(max = 255, message = "名称长度不能超过255")
    @TableField("name")
    private String name;

    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    @Length(max = 255, message = "编号长度不能超过255")
    @TableField("code")
    private String code;


    @Builder
    public Order(Long id, LocalDateTime createTime, Long createUser, LocalDateTime updateTime, Long updateUser,
                 String name, String code) {
        this.id = id;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.name = name;
        this.code = code;
    }

}
