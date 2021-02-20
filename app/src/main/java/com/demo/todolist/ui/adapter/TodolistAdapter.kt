package com.demo.todolist.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MotionEventCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.demo.todolist.R
import com.demo.todolist.databinding.TodoListLayoutBinding
import com.demo.todolist.helper.ItemTouchHelperAdapter
import com.demo.todolist.modules.Todolist
import com.demo.todolist.utils.ItemTouchHelperViewHolder
import java.util.*


class TodolistAdapter() : RecyclerView.Adapter<TodolistAdapter.TodolistHolder>() ,
    ItemTouchHelperAdapter {

    /**
     * Listener for manual initiation of a drag.
     */
    interface OnStartDragListener {
        /**
         * Called when a view is requesting a start of a drag.
         *
         * @param viewHolder The holder of the view to drag.
         */
        fun onStartDrag(viewHolder: RecyclerView.ViewHolder?)
    }

    private val mDragStartListener: OnStartDragListener? = null


    var list: List<Todolist> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class TodolistHolder(todolistBindingImpl: TodoListLayoutBinding) :
        RecyclerView.ViewHolder(todolistBindingImpl.getRoot()), ItemTouchHelperViewHolder {
        var todolistholdebinding: TodoListLayoutBinding

        init {
            this.todolistholdebinding = todolistBindingImpl
        }

        fun bind(obj: Todolist?) {
            todolistholdebinding.setVariable(BR.todolist, obj)
            todolistholdebinding.executePendingBindings()
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);

        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0);

        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TodolistHolder {

        val todolistBindingImpl: TodoListLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(
                parent.context
            ), R.layout.todo_list_layout, parent, false
        )
        return TodolistHolder(todolistBindingImpl)

    }

    override fun onBindViewHolder(holder: TodolistHolder, position: Int) {

        var todolist: Todolist = list.get(position)
        holder.bind(todolist)

//        holder.itemView.setOnTouchListener(object : View.OnTouchListener {
//            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
//                    mDragStartListener?.onStartDrag(holder);
//                }
//                return false;
//            }
//
//
//        })
    }





    override fun getItemCount(): Int {
        return list.size
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(list, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    override fun onItemDismiss(position: Int) {
//        list
        notifyItemRemoved(position);
    }
}