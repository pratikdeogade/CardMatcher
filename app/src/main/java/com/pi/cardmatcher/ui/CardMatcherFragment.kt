package com.pi.cardmatcher.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.pi.cardmatcher.base.BaseFragment
import com.pi.cardmatcher.utils.RepositoryResult
import com.pi.cardmatcher.data.model.CardMatcherModel
import com.pi.cardmatcher.R
import kotlinx.android.synthetic.main.shadi_card_matcher_fragment.*
import java.util.*
import javax.inject.Inject

class CardMatcherFragment : BaseFragment(), CardMatcherAdapter.ShadiCardMatcherItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val shadiCardMatcherViewModel by viewModels<CardMatcherViewModel> { viewModelFactory }
    lateinit var shadiCardMatcherAdapter: CardMatcherAdapter
    lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.shadi_card_matcher_fragment, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        shadiCardMatcherAdapter = CardMatcherAdapter(ArrayList(), mContext, this)
        view_pager.adapter = shadiCardMatcherAdapter

        shadiCardMatcherViewModel.users.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            when (it.status) {
                RepositoryResult.Status.SUCCESS -> {
                    it.data?.let {
                        progress_loader.visibility = GONE
                        view_pager.visibility = VISIBLE
                        shadiCardMatcherAdapter.setItems(it)
                    }
                }
                RepositoryResult.Status.LOADING -> {
                    progress_loader.visibility = GONE
                    view_pager.visibility = VISIBLE
                }
                RepositoryResult.Status.ERROR
                -> {
                    progress_loader.visibility = GONE
                }
            }
        })
    }


    override fun onAcceptClick(shadiCardMatcherModel: CardMatcherModel) {
        shadiCardMatcherViewModel.onAccept(shadiCardMatcherModel)
    }

    override fun onRejectClick(shadiCardMatcherModel: CardMatcherModel) {
        shadiCardMatcherViewModel.onReject(shadiCardMatcherModel)
    }

    companion object {

        fun newInstance(): CardMatcherFragment {
            return CardMatcherFragment()
        }
    }
}