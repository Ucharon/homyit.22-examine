package cn.homyit.entity;

import lombok.Data;

/**
 * @TableName tb_user
 */
@Data
public class User {

    /**
     * 用户id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 班级
     */
    private Integer age;
    /**
     * 邮箱
     */
    private String email;

}
