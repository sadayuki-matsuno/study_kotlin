package com.example.study_kotiln

import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result

class Person (val name: String, var age: Int = 23)

class Fetch {
    fun fetch(url: String) {
        Fuel.get(url).response { request, response, result ->
            println(result)
            when (result) {
                is Result.Success -> {
                    // レスポンスボディを表示
                    println(String(response.data))
                    val result = Klaxon().parseArray<Repo>(String(response.data))
                    println("非同期処理の結果：")
                    println(Klaxon().toJsonString(result))
                }
                is Result.Failure -> {
                    println(request)
                    println("通信に失敗しました。")
                }
            }
        }
    }
}
