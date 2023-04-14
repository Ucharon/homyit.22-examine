package cn.homyit.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @program: teach
 * @description: 返回信息枚举类
 * @author: Charon
 * @create: 2023-04-14 14:01
 **/

@Getter
@AllArgsConstructor
@ToString
public enum ResultCodeEnums {

    SUCCESS(200, "成功"),

    /**
     * 用户相关
     */
    LOGIN_ERROR(600, "用户名或密码错误"),
    LOGIN_STATUS_EXPIRED(601, "登录状态过期"),
    DUPLICATE_USER_NAME(602, "用户名重复")
    ;


    private Integer code;
    private String desc;

    public static void main(String[] args) {
        System.out.println("ResultCodeEnums.SUCCESS = " + ResultCodeEnums.SUCCESS);
    }
}
