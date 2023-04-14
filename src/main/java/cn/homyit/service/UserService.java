package cn.homyit.service;

import cn.homyit.entity.User;
import cn.homyit.entity.dto.LoginUserDto;
import cn.homyit.entity.vo.Result;

public interface UserService {
    User login(LoginUserDto loginUserDto);

    Result register(LoginUserDto loginUserDto);
}
