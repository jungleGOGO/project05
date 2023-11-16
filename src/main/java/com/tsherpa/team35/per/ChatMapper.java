package com.tsherpa.team35.per;

import com.tsherpa.team35.entity.ChatList;
import com.tsherpa.team35.entity.ChatListVO;
import com.tsherpa.team35.entity.ChatRoom;
import com.tsherpa.team35.entity.ChatRoomVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ChatMapper {

    @Select("select * from chatRoomView where productId = #{productId} and productTable = #{productTable} and buyerId = #{buyerId}")
    ChatRoomVO chatRoomAllList(int productId, String productTable, String buyerId) throws Exception;

    @Select("select * from chatRoomView where productId = #{productId} and productTable = #{productTable}")
    List<ChatRoomVO> chatRoomAllListByProduct(int productId, String productTable) throws Exception;

    @Select("select * from chatRoomView where buyerId = #{buyerId}")
    List<ChatRoomVO> chatRoomAllListByBuyerId(String buyerId) throws Exception;

    @Select("select * from chatRoomView where roomId = #{roomId}")
    ChatRoomVO getRoom(Long roomId) throws Exception;

    @Select("select * from chatRoomView order by regDate desc limit 1")
    ChatRoomVO getChatRoomLast() throws Exception;

    @Insert("insert into chatRoom values(default, #{productId}, #{productTable}, #{buyerId}, #{sellerId}, default)")
    void createChatRoom(ChatRoom chatRoom) throws Exception;

    @Select("select * from chatListView where roomId = #{roomId}")
    List<ChatListVO> getChat(Long roomId) throws Exception;

    @Select("select * from chatListView where roomId = #{roomId} order by sendDate desc limit 1")
    ChatListVO getChatLast(Long roomId) throws Exception;

    @Insert("insert into chatList values(default, #{senderId}, default, #{message}, default, #{roomId})")
    int insertChatList(ChatList chatList) throws Exception;

    @Update("update chatList set readYn = true where chatId = #{chatId}")
    int updateChatRead(Long chatId) throws Exception;

}