package com.example.study_kotiln

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // https://www.usaco-pg.com/2017/05/01/kotlin-android-intent/
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Matsuno Github"

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.repoListContainer, RepoListFragment.createInstance(this) )
            transaction.commit()
        }
    }
}