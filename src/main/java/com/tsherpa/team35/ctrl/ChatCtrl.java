package com.tsherpa.team35.ctrl;

import com.tsherpa.team35.biz.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;
import java.security.Principal;

@Component
@ServerEndpoint(value = "/socket")
public class ChatCtrl {

    @Autowired
    private ChatService chatService;

    @OnOpen // socket 연결 시
    public void onOpen(Principal principal) {
        //sessionList.add(session);
    }

}