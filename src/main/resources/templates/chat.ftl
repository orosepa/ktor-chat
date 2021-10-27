<#-- @ftlvariable name="entries" type="kotlin.collections.List<com.example.models.Message>" -->
<#-- @ftlvariable name="chat_name" type="String" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <head>
        <title>Chat</title>
        <link rel="stylesheet" href="/styles.css" type="text/css">
        <style>
            input { display: block; }
            ul    { list-style: none; }
        </style>

    </head>
<body>
<h2 style="color: gray">${chat_name}</h2>
<div class="messages_container">
    <div class="messages_list" id="list_msg">
        <#list entries as msg>
            <div class="message">
                <div class="info">
                    <span class="msg_from">${msg.from}</span>
                    <span class="msg_time">
                        ${msg.getFormattedTime()}
                    </span>
                </div>
                <span class="msg_text">${msg.text}</span>
            </div>
        </#list>
    </div>
    <form id="chatbox" class="chatbox">
        <input type="text" class="form__field" id="msg_text" placeholder="Enter your message" autocomplete="off"/>
        <input type="submit" value="Send" class="submit_btn"/>
    </form>
</div>
<p id="chatId" class="info"></p>
<script  src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"> </script>
<script>
    $(function(){
        let socket = null;
        const msgBox = $("#chatbox #msg_text");
        const messages = $("#list_msg");
        $("#chatId").innerHTML = window.location.pathname.split("/")[2]
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
</script>
</body>
</html>