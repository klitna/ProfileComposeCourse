package com.example.profilecardlayout

data class User(val name: String, val status: Boolean, val drawableId: Int)

val userProfileList = arrayListOf<User>(
    User(name="John Doe", status = true, drawableId=R.drawable.ic_cat_letuce),
    User(name="Anna Johns", status = false, drawableId=R.drawable.ic_cat_letuce),
    User(name="Dan Brown", status = false, drawableId=R.drawable.ic_cat_letuce),
    User(name="Scooby Doo", status = true, drawableId=R.drawable.ic_cat_letuce),
    User(name="Sasha Stonbelg", status = true, drawableId=R.drawable.ic_cat_letuce),
    User(name="Ivan Netrebko", status = true, drawableId=R.drawable.ic_cat_letuce),
    User(name="Dodge Winest", status = true, drawableId=R.drawable.ic_cat_letuce),
    User(name="Hanna Lypko", status = true, drawableId=R.drawable.ic_cat_letuce),
    User(name="Pedro Lopez", status = true, drawableId=R.drawable.ic_cat_letuce),
    User(name="Steven Koln", status = true, drawableId=R.drawable.ic_cat_letuce),
    User(name="Lea Winston", status = true, drawableId=R.drawable.ic_cat_letuce),
    User(name="Shawn Richards", status = true, drawableId=R.drawable.ic_cat_letuce),
    User(name="Andrzej Duda", status = true, drawableId=R.drawable.ic_cat_letuce),
    User(name="Gans Mleko", status = true, drawableId=R.drawable.ic_cat_letuce),
    User(name="Ilona Stans", status = true, drawableId=R.drawable.ic_cat_letuce),
    User(name="Bogdan Stupka", status = true, drawableId=R.drawable.ic_cat_letuce),
    User(name="Helen Green", status = true, drawableId=R.drawable.ic_cat_letuce),
    User(name="Anonymous Anonymous", status = true, drawableId=R.drawable.ic_cat_letuce),
    User(name="Willy Gunt", status = true, drawableId=R.drawable.ic_cat_letuce)
)