package com.example.views

import kotlinx.css.*
import kotlinx.css.Float
import kotlinx.css.properties.TextDecoration

fun CSSBuilder.chatStylesheet() {

    body {
        fontFamily = "'Poppins', sans-serif"
        display = Display.flex
        flexDirection = FlexDirection.column
        justifyContent = JustifyContent.center
        alignItems = Align.center
        height = LinearDimension("100vh")
        fontSize = LinearDimension("4vh")
        backgroundColor = Color("#222222")
        overflow = Overflow.hidden
    }
    rule(".form_group") {
        position = Position.relative
        padding = "15px 0 0"
        marginTop = LinearDimension("10px")
        width = LinearDimension("50%")
    }
    rule(".form__field") {
        fontFamily = "inherit"
        fontSize = LinearDimension("5vh")
        width = LinearDimension("50vw")
        border = "0"
        borderBottom = "2px solid #9b9b9b"
        outline = Outline.none
        color = Color.white
        padding = "7px 0"
        background = "transparent"
        marginBottom = LinearDimension("10vh")
    }
    rule(".form__field::placeholder") { fontSize = LinearDimension("5vh") }
    rule("a") {
        color = Color.blueViolet
        textDecoration = TextDecoration.none
        fontSize = LinearDimension("2.5vw")
    }
    rule(".messages_container") {
        width = LinearDimension("80vw")
        height = LinearDimension("70vh")
        color = Color.lightGray
        border = "solid white"
        borderRadius = LinearDimension("30px")
        fontSize = LinearDimension("3vw")
        overflow = Overflow.hidden
    }
    rule(".messages_list") {
        overflowX = Overflow.hidden
        overflowY = Overflow.scroll
        height = LinearDimension("80%")
    }
    rule(".chatbox") {
        height = LinearDimension("18%")
        justifyContent = JustifyContent.left
        padding = "10px"
        borderTop = "solid white"
    }
    rule("#msg_text") {
        width = LinearDimension("80%")
        height = LinearDimension("70%")
        display = Display.inlineBlock
        float = Float.left
        margin = "0 0 0 0"
        borderBottom = "0"
        fontSize = LinearDimension("3vw")
    }
    rule(".submit_btn") {
        fontFamily = "'Poppins', sans-serif"
        fontSize = LinearDimension("4vh")
        width = LinearDimension("20%")
        height = LinearDimension("70%")
        borderRadius = LinearDimension("10px")
        backgroundColor = Color.blueViolet
        display = Display.inlineBlock
        float = Float.left
    }
    rule(".message") {
        width = LinearDimension("inherit")
        height = LinearDimension.auto
        position = Position.relative
        display = Display.flex
        alignItems = Align.flexStart
        flexDirection = FlexDirection.column
        borderBottom = "1px solid white"
        minHeight = LinearDimension("20%")
        paddingLeft = LinearDimension("5vw")
        paddingRight = LinearDimension("5vw")
        paddingTop = LinearDimension("1vw")
        wordBreak = WordBreak.breakWord
    }
    rule(".info") {
        position = Position.relative
        width = LinearDimension("90%")
        display = Display.flex
        flexDirection = FlexDirection.row
        fontSize = LinearDimension("4vh")
    }
    rule(".msg_from") {
        fontWeight = FontWeight.bold
        color = Color.blueViolet
    }
    rule(".msg_time") {
        position = Position.absolute
        right = LinearDimension("0")
        color = Color.gray

    }
    rule(".msg_text") {
        bottom = LinearDimension("1vw")
        marginTop = LinearDimension("1vw")
        fontSize = LinearDimension("4vh")
    }
    rule("#chat_id") { fontSize = LinearDimension("2.5vw") }

    media("(max-width: 600px)") {
        rule(".info, .msg_text, .submit_btn") { fontSize = LinearDimension("4vw") }
        rule(".form__field, .form__field::placeholder, a") { fontSize = LinearDimension("5vw") }
        rule("#chat_id") { fontSize = LinearDimension("3vw") }
        rule(".messages_container") { height = LinearDimension("80vh")}
    }
}
