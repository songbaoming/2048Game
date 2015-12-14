package com.soon.my2048;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

public class AnimLayer extends FrameLayout {

	public AnimLayer(Context context) {
		super(context);
		
	}

	public AnimLayer(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}
	
	public AnimLayer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
	}
	
	// 收缩动画
	public void createScaleAnim(Card c){
		ScaleAnimation sa = new ScaleAnimation(0.1f, 1, 0.1f, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		sa.setDuration(100);
		c.setAnimation(null);
		c.getLabel().setAnimation(sa);
	}
	
	// 移动动画
	public void createTranslateAnim(final Card from, final Card to, int x0, int y0, int x1, int y1){
		final Card c = from.clone();
		
		LayoutParams lp = new LayoutParams(Config.CARD_WIDTH, Config.CARD_WIDTH);
		lp.leftMargin = x0 * Config.CARD_WIDTH;
		lp.topMargin = y0 *Config.CARD_WIDTH;
		addView(c, lp);
		
		if(to.getNum() <= 0){
			to.getLabel().setVisibility(View.INVISIBLE);
		}
		TranslateAnimation ta = new TranslateAnimation(0, Config.CARD_WIDTH * (x1 - x0), 0, Config.CARD_WIDTH * (y1 - y0));
		ta.setDuration(100);
		ta.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				to.getLabel().setVisibility(View.VISIBLE);
				removeView(c);
			}
		});
		c.startAnimation(ta);
	}
}
