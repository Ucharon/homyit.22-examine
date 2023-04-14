package cn.homyit.web.user;

import cn.homyit.entity.User;
import cn.homyit.entity.dto.LoginUserDto;
import cn.homyit.entity.vo.Result;
import cn.homyit.enums.ResultCodeEnums;
import cn.homyit.service.UserService;
import cn.homyit.service.impl.UserServiceImpl;
import cn.homyit.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/user/login", name = "用户登录接口")
public class LoginServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取登录信息
        LoginUserDto loginUserDto = WebUtils.requestJsonToBean(request, LoginUserDto.class);
        //执行登录业务
        User user = userService.login(loginUserDto);
        //如果用户为空的话，则表示查找失败
        if (user == null) {
            WebUtils.returnResponse(response, Result.error(ResultCodeEnums.LOGIN_ERROR));
            return;
        }
        //服务端记录登录状态
        //将登录成功后的user对象，存储到session中
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        WebUtils.returnResponse(response, Result.success());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
