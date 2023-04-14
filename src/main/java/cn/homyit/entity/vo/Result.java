package cn.homyit.entity.vo;

import cn.homyit.constant.EncodingConstant;
import cn.homyit.enums.ResultCodeEnums;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServletResponse;

/**
 * @program: teach
 * @description: 通用返回类型
 * @author: Charon
 * @create: 2023-04-14 13:59
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result {

    private Integer code;
    private String desc;
    private Object data;
    private Boolean success;

    public Result(Integer code, String desc, Boolean success) {
        this.code = code;
        this.desc = desc;
        this.success = success;
    }

    public static Result success() {
        return new Result(ResultCodeEnums.SUCCESS.getCode(),
                ResultCodeEnums.SUCCESS.getDesc(),
                true);
    }

    public static Result success(Object data) {
        return new Result(ResultCodeEnums.SUCCESS.getCode(),
                ResultCodeEnums.SUCCESS.getDesc(), data,
                true);
    }

    public static Result error(ResultCodeEnums resultCodeEnums) {
        return new Result(resultCodeEnums.getCode(),
                resultCodeEnums.getDesc(),
                false);
    }

}
