package com.example.study_kotiln

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button) as Button
        button.setOnClickListener{
            val url = "https://api.github.com/users/sadayuki-matsuno/repos"
            val f = Fetch()
            f.fetch(url)

            // 非同期処理
//            "https://api.github.com/users/sadayuki-matsuno/repos/".httpGet().response { request, response, result ->
//                when (result) {
//                    is Result.Success -> {
//                        // レスポンスボディを表示
//                        println("非同期処理の結果：" + String(response.data))
//                    }
//                    is Result.Failure -> {
//                        println(request)
//                        println("通信に失敗しました。")
//                    }
//                }
//            }
//            // 同期処理
//            val triple = "https://api.github.com/users/sadayuki-matsuno/repos".httpGet().response()
//            // レスポンスボディを表示
//            println("同期処理の結果：" + triple)
            Toast.makeText(this, "aaaaaa", Toast.LENGTH_LONG).show()
        }

        val button2: Button = findViewById(R.id.button2) as Button
        button2.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        Toast.makeText(this, "Tapped", Toast.LENGTH_LONG).show()
    }
}