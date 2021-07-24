package dh.sos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DrawerAdapter(private val dataSet: Array<String>, val callback: DrawerAdapterCallback):
    RecyclerView.Adapter<DrawerAdapter.ViewHolder>() {

    interface DrawerAdapterCallback {
        fun onDrawerItemClick (value: String)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val textView: TextView

        init {
            textView = view.findViewById(R.id.txtDrawerItem)
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
           callback.onDrawerItemClick(textView.text.toString())
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_drawer, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = dataSet[position]
    }

    override fun getItemCount() = dataSet.size
}
