package com.tsherpa.team35.biz;

import com.tsherpa.team35.entity.ChatRoomVO;
import com.tsherpa.team35.per.ChatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatMapper chatMapper;

    @Override
    public List<ChatRoomVO> getChatRoomList() {
        return chatMapper.getChatRoomList();
    }

}