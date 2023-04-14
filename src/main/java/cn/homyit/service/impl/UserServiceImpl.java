package cn.homyit.service.impl;

import cn.homyit.entity.User;
import cn.homyit.entity.dto.LoginUserDto;
import cn.homyit.entity.vo.Result;
import cn.homyit.enums.ResultCodeEnums;
import cn.homyit.mapper.UserMapper;
import cn.homyit.service.UserService;
import cn.homyit.utils.MybatisUtil;
import cn.hutool.crypto.digest.DigestUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @program: teach
 * @description:
 * @author: Charon
 * @create: 2023-04-14 10:46
 **/
public class UserServiceImpl implements UserService {

    @Override
    public User login(LoginUserDto loginUserDto) {
        UserMapper userMapper = MybatisUtil.getMapper(UserMapper.class);
        User user = userMapper.getByUserName(loginUserDto.getUserName());
        if (user == null || !user.getPassword().equals(DigestUtil.md5Hex(loginUserDto.getPassword()))) {
            return null;
        }
        return user;
    }

    @Override
    public Result register(LoginUserDto loginUserDto) {
        //首先查询是否有相同用户名
        UserMapper userMapper = MybatisUtil.getMapper(UserMapper.class);
        User user = userMapper.getByUserName(loginUserDto.getUserName());
        //如果有，则返回失败
        if (user != null) {
            return Result.error(ResultCodeEnums.DUPLICATE_USER_NAME);
        }
        //否则，插入该用户，表示成功注册
        //先将密码加密
        loginUserDto.setPassword(DigestUtil.md5Hex(loginUserDto.getPassword()));
        userMapper.saveUser(loginUserDto.getUserName(), loginUserDto.getPassword());

        return Result.success();
    }
}
