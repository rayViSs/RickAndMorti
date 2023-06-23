package com.example.rickmortyapi


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.rickmortyapi.ui.main.characters.CharactersFragment
import com.example.rickmortyapi.ui.main.locations.LocationsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomView = findViewById<BottomNavigationView>(R.id.bottomView)

        bottomView.setOnNavigationItemSelectedListener {
            val fragment = when (it.title) {
                "locations" -> LocationsFragment()
                "characters" -> CharactersFragment()
                else -> return@setOnNavigationItemSelectedListener false
            }

            supportFragmentManager.commit {
                replace(R.id.container, fragment)
            }

            return@setOnNavigationItemSelectedListener true
        }

        bottomView.menu.add("characters")
        bottomView.menu.add("locations")

        supportFragmentManager.commit {replace(R.id.container, CharactersFragment())}

//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, CharactersPagedListFragment.newInstance())
//                .commitNow()
//        }
    }
}
