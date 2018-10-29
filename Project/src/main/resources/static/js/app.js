var stompClient = null;

function setConnected(connected, toId, userId) {

    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").show();
    }
    $("#greetings").html("");

    var req = new XMLHttpRequest();
    req.open('POST', '/msg+' + toId + '+' + userId, true);
    req.onload = () => {
        var s = JSON.parse(req.response);
        for (var i = 0; i < s.messages.length; i++) {
            if (userId == s.messages[i].from_id)
                $("#greetings").append("<tr><td style='background-color: #e8f5fc'>" + s.messages[i].text + "</td></tr>");
            else
                $("#greetings").append("<tr><td style='background-color: #ebebeb'>" + s.messages[i].text + "</td></tr>");
        }
    }
    req.send();
}

function connect(toId, userId, chatName, avatarUrl) {


    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true, toId, userId);

        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content, userId, toId);
        });
    });
    document.getElementById('send').setAttribute( "onClick", "javascript: sendName(" + toId + ", "+ userId + ")");
    document.getElementById('chatName').innerHTML = chatName;
    document.getElementById('avatarUrl').src = avatarUrl;
    document.getElementById('avatarUrl').style.display = '';



}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);

}

function sendName(toId, userId) {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val() + "№" + toId + "|" + userId}));
    document.getElementById('name').value = '';

}

function showGreeting(message, userId, chatUserId) {

    if(userId == message.substring(message.indexOf("№") + 1, message.indexOf("|")) && chatUserId == message.substring(message.indexOf("|") + 1, message.length)) {
        $("#greetings").append("<tr><td style='background-color: #ebebeb'>" + message.substring(0, message.indexOf("№")) + "</td></tr>");
    }
    if(userId == message.substring(message.indexOf("|") + 1, message.length)) {
        $("#greetings").append("<tr><td style='background-color: #e8f5fc'>" + message.substring(0, message.indexOf("№")) + "</td></tr>");
    }
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });

});