package com.nenartovich.example02_listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class RecipeAdapter(private val context: Context,
                    private val dataSource: ArrayList<Recipe>) : BaseAdapter() {

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int = dataSource.size

    override fun getItem(position: Int): Any = dataSource[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.list_item_recipe, parent, false)

        val titleTextView: TextView = rowView.findViewById(R.id.recipe_list_title)
        val subtitleTextView: TextView = rowView.findViewById(R.id.recipe_list_subtitle)
        val detailTextView: TextView = rowView.findViewById(R.id.recipe_list_detail)
        val thumbnailImageView: ImageView = rowView.findViewById(R.id.recipe_list_thumbnail)

        val recipe = getItem(position) as Recipe

        titleTextView.text = recipe.title
        subtitleTextView.text = recipe.description
        detailTextView.text = recipe.label

        Picasso.with(context).load(recipe.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView)

        return rowView
    }
}