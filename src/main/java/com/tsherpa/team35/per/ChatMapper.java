package com.tsherpa.team35.per;

import com.tsherpa.team35.entity.ChatRoomVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatMapper {

    @Select("select * from chatRoomView")
    List<ChatRoomVO> getChatRoomList();

}