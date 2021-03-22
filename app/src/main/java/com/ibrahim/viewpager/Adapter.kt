package com.ibrahim.viewpager

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide

class Adapter : PagerAdapter() {

    private lateinit var vPagerModel: List<ViewPagerModel>
    private lateinit var layoutInflater: LayoutInflater
    private lateinit var context: Context

    fun Adapter(models: List<ViewPagerModel>, context: Context) {
        this.vPagerModel = models
        this.context = context
    }

    override fun getCount(): Int {
        return  vPagerModel.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view.equals(`object`)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(context)
        val view: View = layoutInflater.inflate(R.layout.viewpageritem, container, false)

        var imageView: ImageView
        var title: TextView
        var content: TextView

        imageView = view.findViewById(R.id.imageview)
        title = view.findViewById(R.id.title)
        content = view.findViewById(R.id.content)

        Glide.with(context).load(vPagerModel.get(position).image).into(imageView)
        title.text = vPagerModel.get(position).title
        content.text = vPagerModel.get(position).content

        view.setOnClickListener {
            val intent = Intent(context, Details::class.java)
            intent.putExtra("detail", vPagerModel.get(position).content)
            context.startActivity(intent)
        }

        container.addView(view, 0)
        return view
    }

}