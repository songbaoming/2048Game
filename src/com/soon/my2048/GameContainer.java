package com.soon.my2048;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class GameContainer extends RelativeLayout {

	
	public GameContainer(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public GameContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public GameContainer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh){
		super.onSizeChanged(w, h, oldw, oldh);
		int width = Math.min(w, h) - 40;
		Log.d("123", "gameContainer: " + w + "*" + h);
		FrameLayout container = (FrameLayout)findViewById(R.id.game_container);
		LayoutParams lp = (LayoutParams)container.getLayoutParams();
		lp.addRule(ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
		lp.width = lp.height = width;
		lp.setMargins(20, 0, 20, 20);
		container.setLayoutParams(lp);
		container.requestLayout();
	}
}
