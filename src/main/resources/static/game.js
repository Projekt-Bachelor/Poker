var stompClient = null;

function setConnected(connected){
    $("#connect").prop("disabled", connected);
    $("disconnect").prop("disabled", !connected);
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
    stompClient.connect({table: "foo", player: "test"}, function (frame) {
        setConnected(true);
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

function sendRaise() {
    stompClient.send("/app/gameAction/raise", {},
        JSON.stringify({'value': $("#raise-amount").val()}));
}

function showGameInformation(gameinformation){
    $("#gamestate").append("<tr><td>" + gameinformation + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#raise" ).click(function () { sendRaise(); });
    $( "#connect").click(function () { connect(); });
    $( "#disconnect").click(function () { disconnect(); });
});