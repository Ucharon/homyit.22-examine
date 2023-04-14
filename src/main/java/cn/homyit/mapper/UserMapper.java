package cn.homyit.mapper;


import cn.homyit.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Select("select * from teach.`tb_user` where user_name = #{userName}")
    User getByUserName(String userName);

    List<User> listUser();

    @Insert("insert into teach.tb_user(user_name, password) values (#{userName}, #{password})")
    void saveUser(@Param("userName") String userName, @Param("password") String password);
}
