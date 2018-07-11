"use strict";

var ws = null;

function connect() {
    ws = new WebSocket('ws://localhost:8080/name');
    ws.onmessage = function (data) {
        showGreeting(data.data);
    };
    setConnected(true);
}

function disconnect() {
    if (ws != null){
        ws.close();
    }
    setConnected(false);
    console.log("Disconnected");
}

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
}

function sendName() {
    ws.send(JSON.stringify({'name': $("#name").val(), 'type': "test"}));
}

function showGreeting(message) {
    $("#greeting").append(" " + message + " ");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#sendname" ).click(function () { sendName(); });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
});