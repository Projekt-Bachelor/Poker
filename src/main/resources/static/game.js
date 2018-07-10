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
        //setConnected(true);
        //sendRegistration(uuid);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/user/queue/notify', function (notification) {
            console.log(gameinformation);
            showGameInformation(JSON.parse(notification.body));
        });
    });
}

function connectWebsocket() {
    ws = new WebSocket('ws://localhost:8080/notification');
    console.log("Connection created");
    ws.onmessage = function (notification) {
        console.log(JSON.parse(notification.data));
    };
}

function disconnect() {
    if (stompClient !== null){
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function sendAction(type, value) {
    stompClient.send("/app/game/action", {},
        JSON.stringify({'type': type, 'value': value}));
}

function sendRegistration(uuid) {
    ws.send(JSON.stringify({'message-type': "registration", 'token': uuid}));
}

function startGame(type) {
    stompClient.send("/app/game/startgame", {},
        JSON.stringify({'type': type}));
}

function showGameInformation(gameinformation){
    $("#gamestate").append("<tr><td>" + gameinformation + "</td></tr>");
}

function showErrorMessage(error){

}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#startgame").click(function () { startGame("startgame"); });
    $( "#connect").click(function () { sendRegistration(uuid); });

    $( "#call").click(function () { sendAction("call", 0); });
    $( "#check").click(function () { sendAction("check", 0); });
    $( "#fold").click(function () { sendAction("fold", 0); });
    $( "#allin").click(function () { sendAction("all-in", 0); });
    $( "#raise" ).click(function () { sendAction("raise", $("#raise-amount").val()); });
});