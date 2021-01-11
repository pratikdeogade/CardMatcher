package com.pi.cardmatcher.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pi.cardmatcher.data.model.CardMatcherModel
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pi.cardmatcher.R

class CardMatcherAdapter(
    private val shadiCardMatcherModels: MutableList<CardMatcherModel>,
    private val context: Context,
    private val shadiCardMatcherItemClickListener: ShadiCardMatcherItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.shadi_card_matcher_list_row, parent, false)
        return CardMatcherViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val shadiCardMatcherViewHolder = holder as CardMatcherViewHolder
        val shadiMatchesModel = shadiCardMatcherModels[position]

        shadiCardMatcherViewHolder.acceptButton.setOnClickListener {
            shadiCardMatcherItemClickListener.onAcceptClick(shadiMatchesModel)
        }
        shadiCardMatcherViewHolder.rejectButton.setOnClickListener {
            shadiCardMatcherItemClickListener.onRejectClick(shadiMatchesModel)
        }
        shadiCardMatcherViewHolder.bind(shadiMatchesModel)
    }

    override fun getItemCount(): Int {
        return shadiCardMatcherModels.size
    }

    class CardMatcherViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var name: TextView
        var age: TextView
        var image: ImageView
        var acceptButton: FloatingActionButton
        var rejectButton: FloatingActionButton
        var buttonContainer: LinearLayout
        var selectedConatiner: LinearLayout
        var selectedText: TextView

        init {
            name = itemView.findViewById(R.id.name)
            age = itemView.findViewById(R.id.age)
            image = itemView.findViewById(R.id.image)
            acceptButton = itemView.findViewById(R.id.accept_button)
            rejectButton = itemView.findViewById(R.id.reject_buttuon)
            buttonContainer = itemView.findViewById(R.id.button_container)
            selectedConatiner = itemView.findViewById(R.id.selected_container)
            selectedText = itemView.findViewById(R.id.selected_text)
        }

        fun bind(shadiCardMatcherModel: CardMatcherModel) {
            name.text = String.format(
                "%s %s",
                shadiCardMatcherModel.name.first,
                shadiCardMatcherModel.name.last
            )
            age.text = shadiCardMatcherModel.dob.age
            Glide.with(itemView.context).load(shadiCardMatcherModel.picture.large).into(image)
            if (shadiCardMatcherModel.isAccept || shadiCardMatcherModel.isReject) {
                buttonContainer.visibility = View.GONE
                selectedConatiner.visibility = View.VISIBLE
                if (shadiCardMatcherModel.isAccept) {
                    selectedText.setTextColor(ContextCompat.getColor(itemView.context, R.color.accepted))
                    selectedText.text = itemView.context.resources.getString(R.string.accept_text)
                } else if (shadiCardMatcherModel.isReject) {
                    selectedText.setTextColor(ContextCompat.getColor(itemView.context, R.color.rejected))
                    selectedText.text = itemView.context.resources.getString(R.string.rejected_text)
                }
            } else {
                buttonContainer.visibility = View.VISIBLE
                selectedConatiner.visibility = View.GONE
            }
        }
    }

    fun setItems(tempShadiCardMatcherModels: List<CardMatcherModel>) {
        shadiCardMatcherModels.clear()
        shadiCardMatcherModels.addAll(tempShadiCardMatcherModels)
        notifyDataSetChanged()
    }

    interface ShadiCardMatcherItemClickListener {
        fun onAcceptClick(shadiCardMatcherModel: CardMatcherModel);
        fun onRejectClick(shadiCardMatcherModel: CardMatcherModel);
    }
}
