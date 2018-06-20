// erzwingnt sauberen JavaScript Code
"use strict";

let stompClient = null;

// wenn HTML Dokument komplett geladen wurde
$(function() {

    // führe einen Ajax Request aus
    jQuery.ajax( "/tables/list" )

        // wenn HTTP 200, dann führe Funktion aus
        .done(function(i) {

            // es kommt ein Array und für jedes Element
            i.forEach( function(n) {

                // Spiele-DOM-Objekt holen
                $("#spiele").append(
                    // li-Objekt anhängen
                    $("<li/>").append(
                        // in li ein a-Objekt anhängen
                        $("<a/>")
                            // dort Text rein setzen
                            .text(n)
                            // Klasse hinzufügen
                            .addClass("spieljoin")
                            // href Attribut setzen
                            .attr("href", "#" )
                            // Data-Attribut setzen
                            .attr("data-name", n)
                    )
                )}
            );
        })

        // wenn HTTP != 200
        .fail(function(i) {
            console.log(i);
            // Fehlermeldung
            alert("Fehler");
        });

        // binde an alle .spieljoin Objekte
        $("body").on("click", ".spieljoin", function() {

            // führe Ajax Request aus und hole Daten aus dem Data-Attribut des a-Objekts und dem Input-Feld
            jQuery.ajax( "/table/join/" + jQuery(this).data("name") + "/" + $("#name").val()  )
                .done(function(i) {
                    console.log(i);
                })
                .fail(function(i) {
                    console.log(i);
                })
        });

});

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