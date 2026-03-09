package br.com.httpsguerni.crud

import NavigationBar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.com.httpsguerni.crud.ui.theme.CrudTheme

val TAG = "CRUD"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CrudTheme {
                NavigationBar(this)
            }
        }
    }
}
