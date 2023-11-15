package com.tsherpa.team35.ctrl;

import com.tsherpa.team35.biz.ChatService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/chat")
public class ChatRoomCtrl {

    @Autowired
    private ChatService chatService;

}