"use strict";

var stompClient = null;
var ws = null;
var uuid= null;

$(function() {
    var url_string = window.location.href;
    console.log(url);
    var url = new URL(url_string);
    uuid = url.searchParams.get("uuid");
    console.log(uuid);

    connectStomp();
    connectWebsocket();
});

function connectStomp() {
    var socket = new SockJS('/poker');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/message', function (notification) {
            console.log(notification);
        });
    });

}

function connectWebsocket() {
    ws = new WebSocket('ws://localhost:8080/notification');
    console.log("Connection created");
    ws.onmessage = function (notification) {
        console.log(notification);
        showGamestate(notification);
    };
}

function disconnect() {
    if (stompClient !== null){
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function sendRegistration(uuid) {
    console.log("Send Registration");
    ws.send(JSON.stringify({'message-type': "registration", 'token': uuid}));
}

function sendStompRegistration(uuid) {
    stompClient.send("/app/sessionConnect", {},
        JSON.stringify({'token': uuid}));
}

function sendAction(type, value) {
    stompClient.send("/app/game/action", {},
        JSON.stringify({'type': type, 'value': value}));
}

function startGame(type) {
    stompClient.send("/app/game/startgame", {},
        JSON.stringify({'type': type}));
}

function showGamestate(gameinformation){
    var json = JSON.parse(gameinformation.data);

    if (json.hasOwnProperty("m_amount")){
        var currentChipCount = parseInt($("#chipCount").val());
        if (isNaN(currentChipCount)){
            $("#chipCount").val(json.m_amount);
        } else {
            var updatedChipCount = currentChipCount + json.m_amount;
            $("#chipCount").val(updatedChipCount);
        }
    }

    else if (json.hasOwnProperty("m_card") && json.hasOwnProperty("m_destination")){
        var card = json.m_card;
        var cardString = card.m_value + " of " + card.m_suit;

        if (json.m_destination === "table" && json.hasOwnProperty("m_type")){
            if (json.m_type === "flop"){
                var flopCards = $("#flopCard").val();
                $("#flopCard").val(flopCards + cardString);
            }  else if (json.m_type === "turn"){
                $("#turnCard").val(cardString);
            } else if (json.m_type === "river"){
                $("#riverCard").val(cardString);
            }
        } else {
            if ($("#firstHandCard").val() === ""){
                $("#firstHandCard").val(cardString);
            } else if ($("#secondHandCard").val() === ""){
                $("#secondHandCard").val(cardString);3
            }
        }
    }

    else if (json.hasOwnProperty("m_text")){
        alert(json.m_text);
    }

    else {
        $("#gamestate").append("<tr><td>" + json + "</td></tr>");
    }


}

function showErrorMessage(error){

}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#startgame").click(function () { startGame("startgame"); });
    $( "#connect").click(function () { sendRegistration(uuid); });
    $( "#connectStomp").click(function () { sendStompRegistration(uuid); });

    $( "#call").click(function () { sendAction("call", 0); });
    $( "#check").click(function () { sendAction("check", 0); });
    $( "#fold").click(function () { sendAction("fold", 0); });
    $( "#allin").click(function () { sendAction("all-in", 0); });
    $( "#raise" ).click(function () { sendAction("raise", $("#raise-amount").val()); });
});