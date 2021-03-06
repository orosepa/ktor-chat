<#-- @ftlvariable name="entries" type="kotlin.collections.List<com.example.models.Message>" -->
<#-- @ftlvariable name="chat_name" type="String" -->
<#-- @ftlvariable name="chat_id" type="String" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1" name="viewport" />
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
<p id="chat_id">Your chat UUID is ${chat_id}</p>
<script  src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"> </script>
<script type="text/javascript" src="/static/app.js"> </script>
</body>
</html>