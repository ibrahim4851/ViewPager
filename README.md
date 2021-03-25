# ViewPager
  In this Kotlin project we are getting image URL's and showing them in a ViewPager with Volley request and using following libraries:
  
  **Volley:**
  
  `implementation 'com.android.volley:volley:1.2.0'`
  
  **Glide:**
  
  `implementation 'com.github.bumptech.glide:glide:4.12.0'`
  
  `annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0'`

First of all, we need to add ViewPager on our MainActivity:

```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <androidx.viewpager.widget.ViewPager
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/viewPager"/>

</LinearLayout>
```

After that, we are gonna design our ViewPager item.
Create a XML file in layout directory and add these codes:

```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical">

    <ImageView
        android:id="@+id/imageview"
        android:layout_width="match_parent"
        android:layout_height="250dp" />

    <TextView
        android:id="@+id/title"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textSize="25dp" />

    <TextView
        android:id="@+id/content"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textSize="17dp" />

</LinearLayout>
```

Now, we will need a data class to convert the data we receive through json into objects.
I named my class ViewPagerModel which includes one line of code:

`data class ViewPagerModel(var title: String, var content: String, var image: String)`

 Then, we will need an adapter for our ViewPager. Create your adapter and implement PagerAdapter:

 `class Adapter: PagerAdapter {...}`

We are using 3 variables here:

```
var vPagerModel: List<ViewPagerModel>
lateinit var layoutInflater: LayoutInflater
var context: Context
```

After adding these on the top of adapter, our IDE will want us to implement these override methods:

```
    override fun getCount(): Int {
        return  vPagerModel.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view.equals(`object`)
    }
```

After doing this we add one override method too which you can automatically add by typing 'instantiateItem'. In this function, we are initializing our LayoutInflater, View, items in our viewpageritem.xml file and setting them texts and of course adding the images into imageview with using Glide:

```
override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(context)
        val view: View = layoutInflater.inflate(R.layout.viewpageritem, container, false)

        val imageView: ImageView
        val title: TextView
        val content: TextView

        imageView = view.findViewById(R.id.imageview)
        title = view.findViewById(R.id.title)
        content = view.findViewById(R.id.content)

        Glide.with(context).load(vPagerModel.get(position).image).into(imageView)
        title.text = vPagerModel.get(position).title
        content.text = vPagerModel.get(position).content

        view.setOnClickListener {
            val intent = Intent(context, Details::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("detail", vPagerModel.get(position).content)
            context.startActivity(intent)
        }

        container.addView(view, 0)
        return view
    }
```

And, the final override method in adapter:

```
override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
```

##MainActivity

In our MainActivity we will need 3 variables:
```
private lateinit var viewPager : ViewPager
private lateinit var adapter: Adapter
private lateinit var model: MutableList<ViewPagerModel>
```
