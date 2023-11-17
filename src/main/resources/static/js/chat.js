function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#chatting").html("");
}

function connect() {
    var socket = new SockJS('/ws-stomp');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        //console.log('Connected: ' + frame);
        loadChat(chatList)  //저장된 채팅 불러오기

        stompClient.subscribe('/chat/getChat/'+roomId, function (chatListVO) {
            showChat(JSON.parse(chatListVO.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

//html 에서 입력값, roomId 를 받아서 Controller 로 전달
function sendChat() {
    stompClient.send("/chat/send/"+roomId, {},
        JSON.stringify({
            'message' : $("#message").val()
        }));
}

//저장된 채팅 불러오기
function loadChat(chatList){
    if(chatList != null) {
        for(chat in chatList) {
            $("#chatting").append(
                "<tr><td>" + "[" + chatList[chat].userName + "]" + chatList[chat].message + "</td></tr>"
            );
        }
    }
}

//보낸 채팅 보기
function showChat(chatListVO) {
    $("#chatting").append(
        "<tr><td>" + "[" + chatListVO.userName + "]" + chatListVO.message + "</td></tr>"
    );
    $("#message").val("");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    //$( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendChat(); });
});

//창 키면 바로 연결
window.onload = function (){
    connect();
}

window.BeforeUnloadEvent = function (){
    disconnect();
}