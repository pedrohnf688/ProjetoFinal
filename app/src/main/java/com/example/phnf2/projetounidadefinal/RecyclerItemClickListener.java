package com.example.phnf2.projetounidadefinal;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    OnItemClickListener mListener;
    GestureDetector mGestureDetector;


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public RecyclerItemClickListener(Context context, final RecyclerView view , OnItemClickListener listener) {
        mListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                super.onSingleTapUp(e);
                View childView = view.findChildViewUnder(e.getX(), e.getY());
                if (childView != null && mListener != null ) {
                    mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
                }
                return true;
            }
            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);

                View childView = view.findChildViewUnder(e.getX(), e.getY());
                if(childView != null && mListener != null){
                    mListener.onItemLongClick(childView,view.getChildAdapterPosition(childView));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}