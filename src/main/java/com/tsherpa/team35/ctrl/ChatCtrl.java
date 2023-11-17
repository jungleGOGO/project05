package com.tsherpa.team35.ctrl;

import com.tsherpa.team35.biz.ChatService;
import com.tsherpa.team35.biz.MarketService;
import com.tsherpa.team35.biz.RequestService;
import com.tsherpa.team35.biz.UserService;
import com.tsherpa.team35.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatCtrl {

    @Autowired
    private final ChatService chatService;

    @Autowired
    private UserService userService;

    @Autowired
    private MarketService marketService;

    @Autowired
    private RequestService requestService;

    @GetMapping("/chat")
    public String chatHome(@RequestParam(value = "productNo", required = false) int productNo, @RequestParam("productTable") String productTable, Principal principal) throws Exception {
        String path = "redirect:/";

        if(principal != null) {
            
            path += "chat/";
            String sid = principal.getName();
            String id = "";
            
            // 상품에 판매자 정보 가져오기
            if(productTable.equals("market")) {
                DetailVO market = marketService.detailVOList(productNo);
                id = market.getLoginId();
            } else if (productTable.equals("request")) {
                Request request = requestService.requestDetail(productNo);
                id = request.getLoginId();
            }

            if(id.equals(sid)){
                // 로그인한 아이디가 판매자 아이디가 같을 때
                path += "myChatList?productId=" + productNo + "&productTable=" + productTable;
            } else {
                // 로그인한 아이디가 판매자 아이디가 아닐 때
                ChatRoomVO chatRoomVO = chatService.chatRoomAllList(productNo, productTable, sid);
                Long roomId;
                if(chatRoomVO == null) {
                    ChatRoom chatRoom = new ChatRoom();
                    chatRoom.setProductId(productNo);
                    chatRoom.setProductTable(productTable);
                    chatRoom.setBuyerId(sid);
                    ChatRoomVO room = chatService.createChatRoom(chatRoom);
                    roomId = room.getRoomId();
                } else {
                    roomId = chatRoomVO.getRoomId();
                }
                path += "getChat/" + roomId;
            }
        } else {
            if(productTable.equals("market")) {
                path += "market/marketList";
            } else if (productTable.equals("request")) {
                path += "request/reqList";
            }
        }
        return path;
    }

    @GetMapping("/myChatList")
    public String roomList(@RequestParam("productId") int productId, @RequestParam("productTable") String productTable, Model model, Principal principal) throws Exception {
        if(principal != null) {
            List<ChatRoomVO> roomList = chatService.chatRoomAllListByProduct(productId, productTable);
            model.addAttribute("roomList", roomList);
            return "chat/list";
        } else {
            String path = "redirect:/";
            if (productTable.equals("market")) {
                path += "market/marketList";
            } else if (productTable.equals("request")) {
                path += "request/reqList";
            }
            return path;
        }
    }

    @GetMapping("/getChat/{roomId}")
    public String joinRoom(@PathVariable(required = false) Long roomId, Model model, Principal principal) throws Exception {

        String sid = principal != null ? principal.getName() : "";
        ChatRoomVO chatRoomVO = chatService.getRoom(roomId);

        if(principal != null) {
            String id = "";

            if(chatRoomVO.getProductTable().equals("market")) {
                Market market = new Market();
                market.setLoginId("kim");

                id = market.getLoginId();

            } else if (chatRoomVO.getProductTable().equals("request")) {
                Request request = new Request();
                request.setLoginId("kim");

                id = request.getLoginId();
            }

            if(chatRoomVO != null && (id.equals(sid) || chatRoomVO.getBuyerId().equals(sid))) {
                List<ChatListVO> chatList = chatService.getChat(roomId);
                model.addAttribute("roomId", roomId);
                model.addAttribute("chatList", chatList);
                return "chat/get";
            }
        }

        String path = "redirect:/";
        if (chatRoomVO.getProductTable().equals("market")) {
            path += "market/marketList";
        } else if (chatRoomVO.getProductTable().equals("request")) {
            path += "request/reqList";
        }
        return path;

    }

    @MessageMapping("/{roomId}")
    @SendTo("/chat/getChat/{roomId}")
    public ChatListVO chatInsert(@DestinationVariable Long roomId, ChatListVO message, Principal principal) throws Exception {

        String sid = principal.getName();

        ChatList chatList = new ChatList();
        chatList.setRoomId(roomId);
        chatList.setMessage(message.getMessage());
        chatList.setSenderId(sid);
        int cnt = chatService.insertChatList(chatList);

        return chatService.getChatLast(roomId);
    }

}