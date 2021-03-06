package ink.chevro.dto.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  14:28 2020-08-18
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "角色")
public class RoleDTO {

    @ApiModelProperty(value = "角色键",name = "roleKey")
    private String key;

    @ApiModelProperty(value = "角色名", name = "roleName")
    private String name;

    @ApiModelProperty(value = "描述", name = "description")
    private String description;

    @ApiModelProperty(value = "权限菜单", name = "routes")
    private List<MenuDTO> routes;
}
