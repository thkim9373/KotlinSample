package com.hoony.kotlinsample.memo.list


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.memo.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_memo_list.*

/**
 * A simple [Fragment] subclass.
 */
class MemoListFragment : Fragment() {

    private lateinit var listAdapter: MemoListAdapter
    private var viewModel: ListViewModel? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = activity!!.application!!.let {
            ViewModelProvider(
                activity!!.viewModelStore,
                ViewModelProvider.AndroidViewModelFactory(it)
            ).get(ListViewModel::class.java)
        }

        viewModel!!.let { it ->
            it.memoLiveData.value?.let {
                listAdapter = MemoListAdapter(it)
                memoListView.layoutManager =
                    LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
                memoListView.adapter = listAdapter
                listAdapter.itemClickListener = {
                    val intent = Intent(activity, DetailActivity::class.java)
                    intent.putExtra("MEMO_ID", it)
                    startActivity(intent)
                }
            }
            it.memoLiveData.observe(this,
                Observer {
                    listAdapter.notifyDataSetChanged()
                }
            )
        }
    }

    override fun onResume() {
        super.onResume()
        listAdapter.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_memo_list, container, false)
    }


}
