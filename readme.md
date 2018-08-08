# Bachelorproject 2018 - Poker

## Bestandteile:
- 52 Karten (von 2 - Ass)
- Chips
- Small- + Big-Blind (doppelt so groß wie Small-Blind)

## Spielablauf:
### 1. Runde - Flop:
- Zuerst ist der Spieler, der links vom Big Blind sitzt, an der Reihe.
- Dieser hat die Möglichkeit zu *callen, raisen, oder folden*
- Reihum tätigen alle Spieler ihre Einsätze, bis diese komplett ausgeglichen sind, also alle Spieler, die noch im Spiel bleiben wollen, den gleichen Einsatz erbracht haben.
- Anschließend werden drei offene Karten in die Tischmitte gelegt (**Flop**)

### 2. Runde - Turn:
- Die Einsätze aller Spieler, werden in die Tischmitte gelegt (**Pot**)
- Nachdem der Flop offen ausgelegt wurde, folgt eine weitere Setzrunde (alle Spieler die einen *Fold* gespielt haben werden ignoriert)
- Wenn alle Spieler entweder den Höchsteinsatz beglichen oder einen *Fold* gespielt haben, wird die 4. Karte in die Mitte gelegt (**Turn**)

### 3. Runde - River:
- Nach dem *Turn* kommt es zu einer weiteren Setzrunde.
- Sind alle Einsätze ausgeglichen und immer noch zwei oder mehr Spieler im Spiel, wird die 5. Karte in die Mitte gelegt (**River**)

### 4. Runde - Showdown:
- Nach dem *River* kommt es zu einer weiteren Setzrunde.
- Sind nach dieser noch mehr als 1 Spieler im Spiel, kommt es zum *Showdown*.
- Alle verbliebenden Spieler müssen reihum ihre Hand, also ihre zwei verdeckten Karten aufdecken und das beste Poker-Blatt gewinnt.
- Der Gewinner erhät den gesamten Pot (bei einem Unentschieden, wird der Pot unter allen Spielern mit der gleichen Hand aufgeteilt)

### Ende des Spiels:
#### Alle Spieler steigen aus:
Falls ein Spieler in irgendeiner Spielrunde einen Bet oder einen Raise gesetzt hat und diesen kein anderer Spieler begleichen kann/will, gewinnt dieser Spieler den Pot

#### All-In:
Falls ein Spieler *All-In* geht, gibt er alle seine verbliebenden Chips in die Mitte. Sollte ein Spieler mitgehen und keine Möglichkeit für *Bets, Calls und Raises* mehr bestehen, werden sofort die Hände der beteiligten Spieler aufgedeckt und anschließend die verbliebenden Karten in der Mitte umgedreht.

#### Showdown:
Wenn bis zur letzten Spielrunde mindestens zwei Spieler im Spiel sind und alle Einsätze ausgeglichen sind, werden die Blätter der Spieler offen auf den Tisch gelegt und die beste Hand gewinnt.



## Spielregeln:
- Alle Spieler erhalten 2 verdeckte Karten auf die Hand
- Außerdem werden 5 verdeckte Karten in die Tischmitte gelegt

### Handlungsoptionen in der Setzrunde:
- **Check**: Falls bislang noch kein Einsatz in einer Runde plaziert wurde. Sie geben zu ihrem linkenn Nachbarn weiter, ohne etwas zu setzen.

- **Bet**: Wenn noch kein anderer Spieler in der Runde einen Einsatz getätigt hat, kann ein Bet plaziert werden. Die Bet muss dabei mindestens so hoch sein wie der Big Blind!

- **Call**: Hat ein anderer Spieler bereits eine *Bet* getätigt besteht die Möglichkeit zu callen. Das bedeutet, dass der Einsatz des Gegenspielers beglichen wird.

- **Raise**: Wenn ein anderer Spieler bereits eine *Bet* plaziert hat und man diese überbieten möchte. Dieser muss mindestens das Doppelte der ursprünglichen *Bet* betragen.

- **Fold**: Bei einem *Fold* steigt man aus der aktuellen Runde aus und man muss seine Karten **verdeckt** abgeben. Ein *Fold* kann erst nach dem Setzen von Small- und Big-Blind erfolgen

### Kartenkombinationen:
Kartenkombinationen sind absteigend sortiert:
**A** : Ass; **K** : König; **Q** : Dame; **J** : Bube

- **Royal Flush**: A-K-Q-J-10 *Alle eine Farbe*
- **Straight Flush**: 7-6-5-4-3 *Alle eine Farbe*
- **Vier Gleiche**: 4 Karten desselben Ranges z.B. vier Buben
- **Full House**: Kombination aus 3 Karten desselben Ranges und einem Paar eines anderen Ranges (z.B. Q-Q-Q-2-2)
- **Flush**: 5 Karten einer Farbe (z.B. K-Q-9-6-3)
- **Straße**: 5 aufeinander folgende Karten. Jede Straße enthält entweder eine 5 oder eine 10! (z.B. 7-6-5-4-3)
- **Drei Gleiche**: 3 Karten desselben Ranges z.B. drei Asse
- **Zwei Paare**: 2 Karten eines Ranges und zwei weiteren Karten eines anderen Ranges. (z.B. zwei Buben und zwei Achten)
- **Ein Paar**: 2 Karten desselben Ranges. (z.B. zwei Damen)
- **Hohe Karte**: Das Blatt mit der höchsten Karte gewinnt

## Allgemeiner Programmablauf:
Der user landet zunächst auf der *index.html* Seite. Dort hat er zunächst die Möglichkeit einen Spielernamem und einen Namen des Spieltisches einzugeben und somit einen neuen Spieltisch mit sich als *owner* zu erstellen.

Gleichzeitig wird bei jedem **refresh** ein Rest-Call an den Server geschickt, der daraufhin eine Liste aller erstellten Tische zurückgibt. Mit der Eingabe eines Usernames, kann der user auf einen der verfügbaren Tischnamen klicken und joint dann automatisch diesem Tisch.

Anschließend wird man bei beiden Möglichkeiten auf die *game.html* umgeleitet. Gleichzeitig wird bei diesem Vorgang eine einzigartige UUID als URL-Parameter übermittelt.
Sobald die HTML-Seite fertig geladen ist, wird zunächst eine Websocketverbindung (mit Stomp) zum Server hergestellt. Bei diesem Vorgang wird außerdem der einzigartige Endpoint (*/message/ + UUID*) eingerichtet (Subsribtion).

Innerhalb des UIs hat man zunächst die Auswahl zwischen zwei Buttons **Start Game** und **Leave Game**. Bei beiden Möglichkeiten wird ein *CGameControl*-Objekt mittels Stomp-Message an den Server übermittelt. Beide Nachrichten unterscheiden sich nur im Attribut *type* des *CGameControl*-Objektes (**startgame** bei Start Game und **leave** bei Leave Game). Bei der **Leave Game** Aktion wird zusetzlich noch der redirect auf die *hub.html* durchgeführt.

```javascript
$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#startgame").click(function () { sendGameControls("startgame"); });
    $( "#leave").click(function () { sendGameControls("leave"); leave(); });
});

function sendGameControls(type) {
    stompClient.send("/app/game/controls", {},
        JSON.stringify({'type': type}));
}

function leave() {
    document.location.replace("/hub")
}
```



## Package-Erklärungen:
### Tokens:
Die Klasse **ETokens** kümmert sich um die Verwaltung der Tokens. Ein Token besteht aus einer einmaligen UUID, dem Spieltisch, dem Spielernamen und einem Timestamp, der auf die Erzeugung des Tokens zeigt (wird benötigt um nichtbenutzte Token nach einer gewissen Zeit wieder zu löschen).
