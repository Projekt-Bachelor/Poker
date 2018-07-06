"use strict";

$(function() {
    var url_string = window.location.href;
    console.log(url);
    var url = new URL(url_string);
    var uuid = url.searchParams.get("uuid");
    console.log(uuid);

    connect(uuid);
});

var stompClient = null;

function setConnected(connected){
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#gamestate").show();
    } else {
        $("#gamestate").hide();
    }
    $("#gamestate").html("");
}

function connect(uuid) {
    var socket = new SockJS('/poker');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        sendRegistration(uuid);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/user/queue/gamestate', function (gameinformation) {
            showGameInformation(JSON.parse(gameinformation.body));
        });
        stompClient.subscribe('/app/queue/error', function (error) {
            showErrorMessage(JSON.parse(error.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null){
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendAction(type, value) {
    stompClient.send("/app/game/action", {},
        JSON.stringify({'type': type, 'value': value}));
}

function sendRegistration(uuid) {
    stompClient.send("/app/sessionConnect", {},
        JSON.stringify({'uuid': uuid}));
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

    $( "#call").click(function () { sendAction("call", 0); });
    $( "#check").click(function () { sendAction("check", 0); });
    $( "#fold").click(function () { sendAction("fold", 0); });
    $( "#allin").click(function () { sendAction("all-in", 0); });
    $( "#raise" ).click(function () { sendAction("raise", $("#raise-amount").val()); });
});