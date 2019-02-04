package com.example.study_kotiln

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_repo_list.*

class RepoListFragment : Fragment(), View.OnClickListener {
    var mContext: Context? = null

    companion object {
        fun createInstance(mc: Context): RepoListFragment {
            // インスタンス？　MainActivityで生成時に呼ばれている関数
            val tmpDetailFragment = RepoListFragment()
            tmpDetailFragment.mContext = mc
            return tmpDetailFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_repo_list, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.text = "GITHUB FETCH"
        button.setOnClickListener {
            val f = Fetch()
            f.fetchGithubRepos(mContext, reposView)
        }

        button2.text = "View List"
        button2.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        Toast.makeText(mContext, "Tapped", Toast.LENGTH_LONG).show()
        val items = listOf("Kotlin", "Android", "iOS", "Swift", "Java")
        val adapter = ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, items)
        reposView.adapter = adapter
        reposView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(mContext, RepoDetailActivity::class.java)
            intent.putExtra("LANG_NAME", items[position])
            startActivity(intent)
        }
    }
}