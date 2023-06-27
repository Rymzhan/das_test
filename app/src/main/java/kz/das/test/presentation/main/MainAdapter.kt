package kz.das.test.presentation.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kz.das.test.R
import kz.das.test.data.model.MainResponse

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var notificationList = mutableListOf<MainResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = notificationList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(notificationList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<MainResponse>) {
        notificationList.clear()
        notificationList.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageView: ImageView = itemView.findViewById(R.id.circle_image_view)
        private val title: TextView = itemView.findViewById(R.id.main_text)

        fun bind(data: MainResponse) {

            title.text = data.name

            val circularProgressDrawable =
                CircularProgressDrawable(itemView.context).apply {
                    strokeWidth = 5f
                    centerRadius = 30f
                    start()
                }
            Glide.with(itemView.context)
                .load(data.avatar)
                .apply(
                    RequestOptions().placeholder(circularProgressDrawable)
                        .error(R.drawable.ic_error)
                )
                .into(imageView)
        }
    }
}