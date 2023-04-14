package cn.homyit.entity.dto;

import lombok.Data;

/**
 * @program: teach
 * @description:
 * @author: Charon
 * @create: 2023-04-14 10:35
 **/
@Data
public class LoginUserDto {

    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
}
