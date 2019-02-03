package com.example.study_kotiln

import android.R
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_main.*

class Fetch  {
    // context -> https://stackoverflow.com/questions/50171998/how-to-use-this-and-context-in-other-class-android-kotlin
    fun fetch(context: Context, reposView: ListView, url: String) {
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
                        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, nameList)
                        reposView.adapter = adapter
                    }
                }
                is Result.Failure -> {
                    println(request)
                    println("通信に失敗しました。")
                }
            }
        }
        // val (request, response, result ) = Fuel.get(url).response()
        // println(request)
        // println(response)
        // when (result) {
        //     is Result.Success -> {
        //         // レスポンスボディを表示
        //         val repos = Klaxon().parseArray<Repo>(String(response.data))
        //         println("非同期処理の結果：")
        //         println(Klaxon().toJsonString(repos))
        //         return repos
        //     }
        //     is Result.Failure -> {
        //         println(request)
        //         println("通信に失敗しました。")
        //         return null
        //     }
        //     else -> {
        //         println(result)
        //         return null
        //     }
    }
}
