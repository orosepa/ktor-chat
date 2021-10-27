package com.example.views

import kotlinx.html.*

fun HTML.mainView() {
    head {
        title { +"Chat" }
        link(rel = "stylesheet", href = "/styles.css", type = "text/css")
    }
    body {
        div(classes = "form__group field") {
            form(method = FormMethod.post) {
                input(type = InputType.text, name = "chatId", classes = "form__field") {
                    autoComplete = false
                    placeholder = "Enter chat id"
                }
            }
            a(href = "/create") { +"or click here to create a new chat" }
        }
    }
}

fun HTML.creationView() {
    head {
        title { +"Chat" }
        link(rel = "stylesheet", href = "/styles.css", type = "text/css")
    }
    body {
        div(classes = "form__group field") {
            form(method = FormMethod.post) {
                input(type = InputType.text, name = "chatName", classes = "form__field") {
                    autoComplete = false
                    placeholder = "Enter chat name"
                }
            }
        }
    }
}

fun HTML.loginView() {
    head {
        title { +"Chat" }
        link(rel = "stylesheet", href = "/styles.css", type = "text/css")
    }
    body {
        div(classes = "form__group field") {
            form(method = FormMethod.post) {
                input(type = InputType.text, name = "name", classes = "form__field") {
                    autoComplete = false
                    placeholder = "Enter your name"
                }
            }
        }
    }
}