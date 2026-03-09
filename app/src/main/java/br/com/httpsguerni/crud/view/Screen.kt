package br.com.httpsguerni.crud.view

sealed class Screen(val route: String) {
    object Insert: Screen("insert_screen")
    object Select: Screen("select_screen")
    object Update: Screen("update_screen")
    object Delete: Screen("delete_screen")
}