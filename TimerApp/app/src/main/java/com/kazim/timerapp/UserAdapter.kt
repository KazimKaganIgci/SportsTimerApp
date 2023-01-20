package com.kazim.timerapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import javax.security.auth.callback.Callback

class UserAdapter: RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var list= mutableListOf<User>()
    private var delete : ((User) -> Unit)? =null
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val hareketadi:TextView =itemView.findViewById(R.id.adtext)
        val harekettime:TextView =itemView.findViewById(R.id.hareketsayisitext)
        val delete:ImageView =itemView.findViewById(R.id.delete_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user =list[position]
        holder.hareketadi.text ="${user.hareketadi} hareketini ${user.harekettekrar} adet yapılacak"
        holder.harekettime.text="${user.hareketsaniye} saniye"
        holder.delete.setOnClickListener{
            delete?.invoke(user)
        }
    }

    override fun getItemCount(): Int=list.size


    fun setData(data:List<User>){
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()


    }
    fun veriTemizle(){
        list.clear()
        notifyDataSetChanged()


    }
    fun setOnActionEditListener(callback:(User)->Unit){
         this.delete =callback

    }
}