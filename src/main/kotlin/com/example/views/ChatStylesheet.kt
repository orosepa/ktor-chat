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
        minHeight = LinearDimension("100vh")
        fontSize = LinearDimension("1.5rem")
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
        fontSize = LinearDimension("150%")
        width = LinearDimension("50vw")
        border = "0"
        borderBottom = "2px solid #9b9b9b"
        outline = Outline.none
        color = Color.white
        padding = "7px 0"
        background = "transparent"
        marginBottom = LinearDimension("10vh")
    }
    rule(".form__field::placeholder") { fontSize = LinearDimension("100%") }
    rule("a") {
        color = Color.blueViolet
        textDecoration = TextDecoration.none
        fontSize = LinearDimension("100%")
    }
    rule(".messages_container") {
        width = LinearDimension("60vw")
        height = LinearDimension("40vw")
        color = Color.lightGray
        border = "solid white"
        borderRadius = LinearDimension("30px")
        fontSize = LinearDimension("1.2rem")
        overflow = Overflow.hidden
    }
    rule(".messages_list") {
        overflowX = Overflow.hidden
        overflowY = Overflow.scroll
        height = LinearDimension("80%")
    }
    rule(".chatbox") {
        height = LinearDimension("18%")
        fontSize = LinearDimension("1.2rem")
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
    }
    rule(".submit_btn") {
        fontFamily = "'Poppins', sans-serif"
        fontSize = LinearDimension("1.2rem")
        width = LinearDimension("20%")
        height = LinearDimension("70%")
        borderRadius = LinearDimension("10px")
        backgroundColor = Color.blueViolet
        display = Display.inlineBlock
        float = Float.left
    }
    rule(".message") {
        width = LinearDimension("inherit")
        position = Position.relative
        display = Display.flex
        alignItems = Align.flexStart
        flexDirection = FlexDirection.column
        borderBottom = "1px solid white"
        minHeight = LinearDimension("20%")
        paddingLeft = LinearDimension("5vw")
        paddingRight = LinearDimension("5vw")
        paddingTop = LinearDimension("1vw")
        wordWrap = WordWrap.breakWord
    }
    rule(".info") {
        position = Position.relative
        width = LinearDimension("50vw")
        height = LinearDimension("1.5vw")
        display = Display.flex
        flexDirection = FlexDirection.row
    }
    rule(".msg_from") {
        fontWeight = FontWeight.bold
    }
    rule(".msg_time") {
        position = Position.absolute
        right = LinearDimension("0")
        alignSelf = Align.flexEnd
        color = Color.gray

    }
    rule(".msg_text") {
        position = Position.absolute
        display = Display.block
        bottom = LinearDimension("1vw")
        marginTop = LinearDimension("1")
        fontSize = LinearDimension("1rem")

    }
}
