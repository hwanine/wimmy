package com.example.wimmy


import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wimmy.Adapter.RecyclerAdapterForder
import com.example.wimmy.db.MediaStore_Dao
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.wimmy.db.PhotoViewModel
import com.example.wimmy.db.thumbnailData
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.main_activity.view.*

/**
 * A simple [Fragment] subclass.
 */
class TagFragment(v: AppBarLayout) : Fragment() {
    private var recyclerAdapter : RecyclerAdapterForder?= null
    var bottomNavigationView: BottomNavigationView? = null
    private var thumbnailList = listOf<thumbnailData>()
    private var mLastClickTime: Long = 0
    val ab = v
    private var thisview: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle? ): View? {
        ab.main_toolbar.visibility = View.VISIBLE
        ab.setExpanded(true,true)

        val view : View = inflater.inflate(R.layout.fragment_tag, container, false)
        val vm = ViewModelProviders.of(this).get(PhotoViewModel::class.java)
        thumbnailList = vm.getTagDir()

        thisview = inflater.inflate(R.layout.fragment_name, container, false)
        thumbnailList = MediaStore_Dao.getNameDir(thisview?.context)

        setView(thisview)
        return thisview
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                203 -> {
                    if(data!!.getIntExtra("delete_check", 0) == 1) {
                        val vm = ViewModelProviders.of(this).get(PhotoViewModel::class.java)
                        thumbnailList = vm.getTagDir()
                        setView(thisview)
                    }
                }
            }
        }
    }
    private fun setView(view : View?) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.tagRecycleView)
        recyclerAdapter =
            RecyclerAdapterForder(activity, thumbnailList)
            {thumbnailData ->
                if(SystemClock.elapsedRealtime() - mLastClickTime > 1000) {
                    val intent = Intent(activity, Main_PhotoView::class.java)
                    intent.putExtra("tag_name", thumbnailData.data)
                    startActivityForResult(intent, 203)
                }
                mLastClickTime = SystemClock.elapsedRealtime()
            }
        recyclerView?.adapter = recyclerAdapter

        val lm = GridLayoutManager(MainActivity(), 3)
        recyclerView?.layoutManager = lm
    }

    private fun setPhotoSize(view : View, row : Int, padding : Int) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.tagRecycleView)
        recyclerView.viewTreeObserver.addOnGlobalLayoutListener( object : ViewTreeObserver.OnGlobalLayoutListener {
            @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
            override fun onGlobalLayout() {
                val width = recyclerView.width
                val size = width / row - 2 * padding
                recyclerAdapter!!.setPhotoSize(size, padding)
                recyclerView.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }
}
/*
    inner class Scroll : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int){
            bottomNavigationView = view!!.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            if (dy > 0 && bottomNavigationView!!.isShown()) {
                bottomNavigationView!!.setVisibility(View.GONE);
            } else if (dy < 0 ) {
                bottomNavigationView!!.setVisibility(View.VISIBLE);
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
        }
    }
}
 */
