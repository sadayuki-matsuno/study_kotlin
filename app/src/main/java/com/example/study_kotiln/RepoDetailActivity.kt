package com.example.study_kotiln

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

class RepoDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_detail)

        // https://www.usaco-pg.com/2017/05/01/kotlin-android-intent/
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Matsuno Github"

        val text = intent.getStringExtra("LANG_NAME")
        println(text)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }
}
