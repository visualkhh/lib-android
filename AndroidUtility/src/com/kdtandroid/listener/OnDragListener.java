package com.kdtandroid.listener;

import android.view.View;

public interface OnDragListener{
	int LeftDrag = 1;
	int RightDrag = 2;
	public boolean onDrag(View view,int actionMode);
}