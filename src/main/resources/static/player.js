// erzwingnt sauberen JavaScript Code
"use strict";

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
                });
        });

});

$(function () {
    $( "#createTable").click(function () {
        jQuery.ajax("/table/create/" + $("#tablename").val() + "/" + $("#name").val() )
            .done(function (i) {
                console.log(i);
            })
            .fail(function (i) {
                console.log(i);
            });
    });
});


