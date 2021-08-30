package com.kienht.bubble_picker

import android.content.res.TypedArray
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.kienht.bubblepicker.BubblePickerListener
import com.kienht.bubblepicker.adapter.BubblePickerAdapter
import com.kienht.bubblepicker.model.BubbleGradient
import com.kienht.bubblepicker.model.PickerItem
import kotlinx.android.synthetic.main.sync_activity.*

/**
 * @author kienht
 * @since 20/07/2018
 */
class SyncActivity : AppCompatActivity() {

    lateinit var images: TypedArray
    lateinit var colors: TypedArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sync_activity)

        val titles = resources.getStringArray(R.array.countries)
        colors = resources.obtainTypedArray(R.array.colors)
        images = resources.obtainTypedArray(R.array.images)

        picker.adapter = object : BubblePickerAdapter {
            override val totalCount = titles.size

            override fun getItem(position: Int): PickerItem {
                return PickerItem().apply {
                    title = titles[position]
//                    gradient = BubbleGradient(colors.getColor((position * 2) % 8, 0),
//                            colors.getColor((position * 2) % 8 + 1, 0), BubbleGradient.VERTICAL)
//                    imgUrl = "http://sohanews.sohacdn.com/2018/4/11/hat9-1523392964439195574255.jpg"
                    color = colors.getColor((position * 2) % 8, 0)
                    imgDrawable = ContextCompat.getDrawable(this@SyncActivity, images.getResourceId(position, 0))
                }
            }
        }
        picker.maxSelectedCount = 5
        picker.isAlwaysSelected = false
        picker.centerImmediately = true
        picker.bubbleSize = 10
        picker.listener = object : BubblePickerListener {
            override fun onBubbleDeselected(item: PickerItem) {
                toast("Unselected: " + item.title!!)
            }

            override fun onBubbleSelected(item: PickerItem) {
                toast("Selected: " + item.title!!)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        picker.onResume()
    }

    override fun onPause() {
        super.onPause()
        picker.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        colors.resources
        images.resources
    }

    private fun toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

}