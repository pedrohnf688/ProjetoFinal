package com.example.phnf2.projetounidadefinal.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerOrdenhaClickListener implements RecyclerView.OnItemTouchListener {

    RecyclerItemClickListener.OnItemClickListener mListener3;
    GestureDetector mGestureDetector3;

    public interface OnOrdenhaClickListener {
        void onOrdenhaClick(View view, int position);
        void onOrdenhaLongClick(View view, int position);
    }

    public RecyclerOrdenhaClickListener(Context context, final RecyclerView view , RecyclerItemClickListener.OnItemClickListener listener) {
        mListener3 = listener;
        mGestureDetector3 = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                super.onSingleTapUp(e);
                View childView = view.findChildViewUnder(e.getX(), e.getY());
                if (childView != null && mListener3 != null ) {
                    mListener3.onItemClick(childView, view.getChildAdapterPosition(childView));
                }
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);

                View childView = view.findChildViewUnder(e.getX(), e.getY());
                if(childView != null && mListener3 != null){
                    mListener3.onItemLongClick(childView,view.getChildAdapterPosition(childView));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        mGestureDetector3.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
