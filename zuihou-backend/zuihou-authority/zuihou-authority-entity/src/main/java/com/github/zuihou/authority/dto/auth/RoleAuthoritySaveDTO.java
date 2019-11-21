package com.github.zuihou.authority.dto.auth;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 实体类
 * 角色的资源
 * </p>
 *
 * @author zuihou
 * @since 2019-07-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "RoleAuthoritySaveDTO", description = "角色的资源")
public class RoleAuthoritySaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     * #c_auth_menu
     */
    @ApiModelProperty(value = "资源ID")
    private List<Long> menuIdList;

    /**
     * 资源id
     * #c_auth_resource
     */
    private List<Long> resourceIdList;

    /**
     * 角色id
     * #c_auth_role
     */
    @ApiModelProperty(value = "角色id")
    @NotNull(message = "角色id不能为空")
    private Long roleId;

}
