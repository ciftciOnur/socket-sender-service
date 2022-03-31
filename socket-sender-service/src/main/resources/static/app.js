var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
	var jsonResponse = '{"id": "Peter", "age": 22, "country": "United States"}';
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/get-proof', function (response) {
            showResponse(JSON.parse(response.body));
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
    stompClient.send("/app/proof-request", {}, JSON.stringify({'message': $("#id").val(),'otpKey': $("#otp").val(),'timeInterval': $("#time").val()}));
}

function showResponse(message) {
    $("#receivedMessage").append("<tr><td>" + printValues(message) + "</td></tr>");
}

function printValues(obj) {
    for(var k in obj) {
        if(obj[k] instanceof Object) {
            printValues(obj[k]);
        } else {
        	return JSON.stringify(obj);
            //document.write(obj[k] + "<br>");
        };
    }
};

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});