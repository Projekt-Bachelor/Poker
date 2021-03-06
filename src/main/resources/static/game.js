"use strict";

var stompClient = null;
var uuid = null;

var defaultValue = "";

$(function() {
    var url_string = window.location.href;
    console.log(url);
    var url = new URL(url_string);
    uuid = url.searchParams.get("uuid");
    console.log(uuid);

    connectStomp(uuid);
});

function connectStomp(uuid) {
    var endpoint = '/message/' + uuid.toString();
    console.log(endpoint);

    var socket = new SockJS('/poker');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe(endpoint, function (notification) {
            console.log(notification);
            showGamestate(notification);
        });
    });
}

function disconnect() {
    if (stompClient !== null){
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function sendStompRegistration(uuid) {
    stompClient.send("/app/sessionConnect", {},
        JSON.stringify({'token': uuid}));
}

function sendAction(type, value) {
    stompClient.send("/app/game/action", {},
        JSON.stringify({'type': type, 'value': value}));
}

function sendGameControls(type) {
    stompClient.send("/app/game/controls", {},
        JSON.stringify({'type': type}));
}

function leave() {
    console.log("redirect!");
    document.location.replace("/hub")
}

function showGamestate(gameinformation){
    var json = JSON.parse(gameinformation.body);

    if (json.hasOwnProperty("amount")){
        var currentChipCount = parseInt($("#chipCount").val());
        if (isNaN(currentChipCount)){
            $("#chipCount").val(json.amount);
        } else {
            var updatedChipCount = currentChipCount + json.amount;
            $("#chipCount").val(updatedChipCount);
        }
    }

    else if (json.hasOwnProperty("card") && json.hasOwnProperty("destination")){
        var card = json.card;
        var cardString = card.value + " of " + card.stringSuit;

        if (json.destination === "table" && json.hasOwnProperty("type")){
            if (json.type === "flop"){
                var flopCards = $("#flopCard").val();
                $("#flopCard").val(flopCards + cardString + '\n');
            }  else if (json.type === "turn"){
                $("#turnCard").val(cardString);
            } else if (json.type === "river"){
                $("#riverCard").val(cardString);
            }
        } else {
            if ($("#firstHandCard").val() === ""){
                $("#firstHandCard").val(cardString);
            } else if ($("#secondHandCard").val() === ""){
                $("#secondHandCard").val(cardString);
            }
        }
    }

    else if (json.hasOwnProperty("notification")){
        if (json.notification === "newround") {
            $("#flopCard").val(defaultValue);
            $("#turnCard").val(defaultValue);
            $("#riverCard").val(defaultValue);
            $("#firstHandCard").val(defaultValue);
            $("#secondHandCard").val(defaultValue);
        } else if (json.notification === "tofewplayers") {
            alert("Es sind zu wenig Spieler im Spiel. Du wirst automatisch umgeleitet!");
            setTimeout(function () {
                leave();
            }, 5000);
        } else {
            alert(json.notification);
        }
    }

    else if (json.hasOwnProperty("text")){
        $("#gamestate").append("<tr><td>" + json.text + "</td></tr>");
    }


}

function showErrorMessage(error){

}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#startgame").click(function () { sendGameControls("startgame"); });
    $( "#leave").click(function () { sendGameControls("leave"); leave(); });
    $( "#connectStomp").click(function () { sendStompRegistration(uuid); });

    $( "#call").click(function () { sendAction("call", 0); });
    $( "#check").click(function () { sendAction("check", 0); });
    $( "#fold").click(function () { sendAction("fold", 0); });
    $( "#allin").click(function () { sendAction("all-in", 0); });
    $( "#raise" ).click(function () { sendAction("raise", $("#raise-amount").val()); });
});