package cn.homyit.web.user;

import cn.homyit.entity.dto.LoginUserDto;
import cn.homyit.entity.vo.Result;
import cn.homyit.service.UserService;
import cn.homyit.service.impl.UserServiceImpl;
import cn.homyit.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/user/register", name = "用户注册接口")
public class RegisterServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginUserDto loginUserDto = WebUtils.requestJsonToBean(request, LoginUserDto.class);
        Result result = userService.register(loginUserDto);
        WebUtils.returnResponse(response, result);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
