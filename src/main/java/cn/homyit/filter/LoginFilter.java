package cn.homyit.filter;

import cn.homyit.entity.vo.Result;
import cn.homyit.enums.ResultCodeEnums;
import cn.homyit.utils.WebUtils;
import cn.hutool.core.collection.CollectionUtil;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        //记录下需要不需要拦截的接口
        Set<String> noFilter = CollectionUtil.newHashSet("/user/login", "/user/register");
        //获取当前资源的访问路径
        final String url = req.getRequestURL().toString();
        //判断下url是否包含不要拦截的路径
        for (String s : noFilter) {
            //如果是。直接放行
            if (url.contains(s)) {
                chain.doFilter(request, response);
                return;
            }
        }

        //1. 判断session中是否有user
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");
        //2. 判断user是否为null
        if (user != null) {
            //登录过了，直接放行
            chain.doFilter(request, response);
        } else {
            //没有登录，提醒用户登录状态过期
            WebUtils.returnResponse((HttpServletResponse) response,
                    Result.error(ResultCodeEnums.LOGIN_STATUS_EXPIRED));
        }
    }
}
