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

function connect() {
    var socket = new SockJS('/poker');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        sendRegistration();
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/game', function (gameinformation) {
            showGameInformation(JSON.parse(gameinformation.body));
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

/*function sendRaise() {
    stompClient.send("/app/gameAction/raise", {},
        JSON.stringify({'value': $("#raise-amount").val()}));
}*/

function sendAction(type, value) {
    stompClient.send("/app/game/action", {},
        JSON.stringify({'type': type, 'value': value}));
}

function sendRegistration() {
    stompClient.send("/app/sessionConnect", {},
        JSON.stringify({'table': "foo", 'player': "test"}));
}

function showGameInformation(gameinformation){
    $("#gamestate").append("<tr><td>" + gameinformation + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect").click(function () { connect(); });
    $( "#disconnect").click(function () { disconnect(); });

    $( "#call").click(function () { sendAction("call", 0); });
    $( "#check").click(function () { sendAction("check", 0); });
    $( "#fold").click(function () { sendAction("fold", 0); });
    $( "#allin").click(function () { sendAction("all-in", 0); });
    $( "#raise" ).click(function () { sendAction("raise", $("#raise-amount").val()); });
});