package com.example.study_kotiln

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class Fetch {
    // context -> https://stackoverflow.com/questions/50171998/how-to-use-this-and-context-in-other-class-android-kotlin
    fun fetchGithubRepos (context: Context?, reposView: ListView ) =  GlobalScope.launch(Dispatchers.Main)  {
        println("start fetch repos")
        val url = "https://api.github.com/users/sadayuki-matsuno/repos"
        async(Dispatchers.Default) {Fuel.get(url).response()}.await().let {
            when (it.third) {
                is Result.Success -> {
                    // レスポンスボディを表示
                    val repos = Klaxon().parseArray<Repo>(String(it.second.data))
                    if (repos != null) {
                        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, repos.map{it.name})
                        reposView.adapter = adapter
                        reposView.setOnItemClickListener { parent, view, position, id ->
                            Toast.makeText(context, repos[position].fullName, Toast.LENGTH_LONG).show()
                        }

                    }
                }
                is Result.Failure -> {
                    println(it.first)
                    println("通信に失敗しました。")
                }
            }
        }
        println("end fetch repos")
    }
}
