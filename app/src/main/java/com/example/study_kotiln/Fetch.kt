package com.example.study_kotiln

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result

class Fetch {
    fun fetch(url: String) {
        Fuel.get(url).response { request, response, result ->
            println(result)
            when (result) {
                is Result.Success -> {
                    // レスポンスボディを表示
                    println("非同期処理の結果：" + String(response.data))
                }
                is Result.Failure -> {
                    println(request)
                    println("通信に失敗しました。")
                }
            }
        }
    }
}