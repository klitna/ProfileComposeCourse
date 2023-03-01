package com.example.profilecardlayout

data class User(val id: Int, val name: String, val status: Boolean, val drawableId: String)

val userProfileList = arrayListOf<User>(
    User(id = 0, name="John Doe", status = true, drawableId="https://robohash.org/"),
    User(id = 1, name="Anna Johns", status = false, drawableId="https://robohash.org/"),
    User(id = 2, name="Dan Brown", status = false, drawableId="https://robohash.org/"),
    User(id = 3, name="Scooby Doo", status = true, drawableId="https://robohash.org/"),
    User(id = 4, name="Sasha Stonbelg", status = true, drawableId="https://robohash.org/"),
    User(id = 5, name="Ivan Netrebko", status = true, drawableId="https://robohash.org/"),
    User(id = 6, name="Dodge Winest", status = true, drawableId="https://robohash.org/"),
    User(id = 7, name="Hanna Lypko", status = true, drawableId="https://robohash.org/"),
    User(id = 8, name="Pedro Lopez", status = true, drawableId="https://robohash.org/"),
    User(id = 9, name="Steven Koln", status = true, drawableId="https://robohash.org/"),
    User(id = 10, name="Lea Winston", status = true, drawableId="https://robohash.org/"),
    User(id = 11, name="Shawn Richards", status = true, drawableId="https://robohash.org/"),
    User(id = 12, name="Andrzej Duda", status = true, drawableId="https://robohash.org/"),
    User(id = 13, name="Gans Mleko", status = true, drawableId="https://robohash.org/"),
    User(id = 14, name="Ilona Stans", status = true, drawableId="https://robohash.org/"),
    User(id = 15, name="Bogdan Stupka", status = true, drawableId="https://robohash.org/"),
    User(id = 16, name="Helen Green", status = true, drawableId="https://robohash.org/"),
    User(id = 17, name="Anonymous Anonymous", status = true, drawableId="https://robohash.org/"),
    User(id = 18, name="Willy Gunt", status = true, drawableId="https://robohash.org/")
)