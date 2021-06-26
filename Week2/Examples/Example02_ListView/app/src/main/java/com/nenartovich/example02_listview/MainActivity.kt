package com.nenartovich.example02_listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.recipe_list_view)
        val recipeList = Recipe.getRecipesFromFile("recipes.json", this)

        val adapter = RecipeAdapter(this, recipeList)
        listView.adapter = adapter

        val context = this
        listView.setOnItemClickListener { p0, p1, position, p3 ->
            val selectedRecipe = recipeList[position]
            val detailIntent = RecipeDetailActivity.newIntent(context, selectedRecipe)
            startActivity(detailIntent)
        }
    }
}