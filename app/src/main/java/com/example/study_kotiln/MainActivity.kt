package com.example.study_kotiln

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import com.beust.klaxon.Klaxon
import kotlinx.android.synthetic.main.activity_main.*
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var NameList: List<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // https://www.usaco-pg.com/2017/05/01/kotlin-android-intent/
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Matsuno Github"

        val button: Button = findViewById(R.id.button) as Button
        button.text = "GITHUB FETCH"
        button.setOnClickListener{
            onClickFetch().let{
                val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, NameList)
                reposView.adapter = adapter
            }
        }

        val button2: Button = findViewById(R.id.button2) as Button
        button.text = "View List"
        button2.setOnClickListener(this)
    }

    fun onClickFetch () =  GlobalScope.launch(Dispatchers.Main)  {
        val url = "https://api.github.com/users/sadayuki-matsuno/repos"
        async(Dispatchers.Default) {Fuel.get(url).response()}.await().let {
            when (it.third) {
                is Result.Success -> {
                    // レスポンスボディを表示
                    val repos = Klaxon().parseArray<Repo>(String(it.second.data))
                    if (repos != null) {
                        NameList =  repos.map{it.name}
                    }
                }
                is Result.Failure -> {
                    println(it.first)
                    println("通信に失敗しました。")
                }
            }
        }
    }

    fun fetchAndListRepos (url: String) {
        println("非同期処理Start")
        Fuel.get(url).response { request, response, result ->
            when (result) {
                is Result.Success -> {
                    // レスポンスボディを表示
                    val repos = Klaxon().parseArray<Repo>(String(response.data))
                    println("非同期処理の結果：")
                    println(Klaxon().toJsonString(repos))
                    if (repos != null) {
                        val nameList =  repos.map{it.name}
                        println(nameList)
                        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameList)
                        reposView.adapter = adapter
                    }
                }
                is Result.Failure -> {
                    println(request)
                    println("通信に失敗しました。")
                }
            }
        }
        println("非同期処理End")
    }


    override fun onClick(v: View?) {
        Toast.makeText(this, "Tapped", Toast.LENGTH_LONG).show()
        val items = listOf("Kotlin","Android","iOS","Swift","Java")
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items)
        reposView.adapter = adapter
    }
}