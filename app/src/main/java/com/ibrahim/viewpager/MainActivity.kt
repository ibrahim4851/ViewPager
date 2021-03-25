package com.ibrahim.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager : ViewPager
    private lateinit var adapter: Adapter
    private lateinit var model: MutableList<ViewPagerModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model = ArrayList<ViewPagerModel>()
        viewPager = findViewById(R.id.viewPager)
        adapter = Adapter(model as ArrayList<ViewPagerModel>, applicationContext)
        viewPager.adapter = adapter
        otherRequest()
    }


    fun otherRequest() {
        val mQueue: RequestQueue = Volley.newRequestQueue(this)
        val url = "https://raw.githubusercontent.com/ibrahim4851/ViewPager/master/data.json"
        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val jsonArray = response.getJSONArray("sample")
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val description = jsonObject.getString("description")
                    val title = jsonObject.getString("title")
                    val imageUrl = jsonObject.getString("image-url")
                    val imageModel = ViewPagerModel(title, description, imageUrl)
                    model.add(imageModel)
                }
                Log.i("modellist", model.toString())
                adapter.notifyDataSetChanged()
            })
        { error -> error.printStackTrace() }
        mQueue.add(request)
    }

}