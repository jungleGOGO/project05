package com.tsherpa.team35.per;

import com.tsherpa.team35.entity.User;
import com.tsherpa.team35.entity.UserVO;
import com.tsherpa.team35.util.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserMapper {

    User getUserByLoginId(@Param("loginId") String loginId);
    UserVO findUserListByLoginId(@Param("loginId") String loginId);
    int userInsert(@Param("param") User param);
    List<User> userList(Page page);
    int getCount(Page page);
}