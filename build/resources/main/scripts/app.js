$(function(){
    let socket = null;
    const msgBox = $("#chatbox #msg_text");
    const messages = $("#list_msg");
    $("#chatbox").submit(function(){
        if (!msgBox.val()) return false;
        if (!socket) {
            alert("Error: There is no socket connection.");
            return false;
        }
        socket.send(msgBox.val());
        msgBox.val("");
        return false;
    });
    if (!window["WebSocket"]) {
        alert("Error: Your browser does not support web  sockets.")
    } else {
        socket = new WebSocket("ws://localhost:8080/webs");
        socket.onclose = function() {
            alert("Connection has been closed.");
        }
        socket.onmessage = function(e) {
            let msg = JSON.parse(e.data)
            messages.append(
                $("<div class='message'>").append(
                    $("<div class='info'>").append(
                        $("<span class='msg_from'>").text(msg[0])
                    ).append($("<span class='msg_time'>").text(msg[1]))
                ).append($("<span class='msg_text'>").text(msg[2]))
            )
            messages[0].scrollTop = messages[0].scrollHeight
        }
    }
});