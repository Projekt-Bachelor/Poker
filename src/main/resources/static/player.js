let stompClient = null;

let successResponse = 100;


function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#tableContainer").show();
    }
    else {
        $("#tableContainer").hide();
    }
    $("#greetings").html("");
}

function requestJSON(url, callback) {
    fetch(url)
        .then(response => response.json())
        .then(function (data) {
            callback(data)
        }).catch(error => console.error('Could not fetch url: ', error))
}

function connect() {
    var socket = new SockJS('/poker');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/tables', function (response) {
            validateResponse(JSON.parse(response.body));
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

function sendName() {
    stompClient.send("/app/createPlayer", {},
        JSON.stringify({'name': $("#name").val()}));
}

function getTables() {
    requestJSON("/tables/list", showTables())
}

function showTables(tables) {
    tables.forEach(display)
}

function showTable(tableName) {
    $("#tables").append("<tr><td>" + tableName + "</td></tr>");
}

function validateResponse(response){
    //TODO - Abfrage auf Success-Meldung implementieren
    getTables();
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});