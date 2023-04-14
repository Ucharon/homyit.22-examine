package cn.homyit.utils;

import cn.homyit.constant.EncodingConstant;
import cn.homyit.entity.vo.Result;
import cn.hutool.json.JSONUtil;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @program: teach
 * @description: 请求解析工具类
 * @author: Charon
 * @create: 2023-04-14 14:18
 **/
public class WebUtils {

    /**
     * 将request中的json字符串转换为javabean
     * @param request 请求
     * @param type 需要转换成对应的类型
     * @param <T> 泛型，表示javabean对应的类
     * @return javabean对象
     * @throws IOException
     */
    public static <T> T requestJsonToBean(HttpServletRequest request, Class<T> type) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return JSONUtil.toBean(sb.toString(), type);
    }

    /**
     * 返回为json字符串
     * @param response 相应
     * @param result none
     */
    @SneakyThrows
    public static void returnResponse(HttpServletResponse response, Result result) {
        response.setCharacterEncoding(EncodingConstant.UTF_8);
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(result));
    }
}
