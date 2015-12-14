package com.soon.my2048;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class GameView extends LinearLayout {

	public GameView(Context context) {
		super(context);
		// 初始化游戏界面
		Init();
	}
	
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// 初始化游戏界面
		Init();
	}
	
	private void Init(){
		setOrientation(LinearLayout.VERTICAL);
		setBackgroundColor(0xffbbada0);
		//addCards(Config.CARD_WIDTH, Config.CARD_WIDTH);
		
		setOnTouchListener(new OnTouchListener() {
			private float x0, y0, offsetX, offsetY;
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					x0 = event.getX();
					y0 = event.getY();
					break;
				case MotionEvent.ACTION_UP:
					offsetX = event.getX() - x0;
					offsetY = event.getY() - y0;
					// 根据x和y方向上的移动距离判断是划动是横向的还是竖向的
					if(Math.abs(offsetX) > Math.abs(offsetY)){
						if(offsetX < -8){
							moveLeft();
						}else if(offsetX > 8){
							moveRight();
						}
					}else{
						if(offsetY < -8){
							moveUp();
						}else if(offsetY > 8){
							moveDown();
						}
					}
					break;
				default:
					break;
				}
				return true;
			}
		});
	}

	private void moveLeft(){
		boolean merge = false;
		
		for(int y = 0; y < Config.LINES; ++y){
			for(int x = 0; x < Config.LINES - 1; ++x){
				for(int x1 = x + 1; x1 < Config.LINES; ++x1){
					if(cardMap[x1][y].getNum() > 0){
						if(cardMap[x][y].getNum() <= 0){
							MainActivity.getMainActivity().getAnimLayer().createTranslateAnim(cardMap[x1][y], cardMap[x][y], x1, y, x, y);
							
							cardMap[x][y].setNum(cardMap[x1][y].getNum());
							cardMap[x1][y].setNum(0);
							--x;
							merge = true;
						}else if(cardMap[x][y].equals(cardMap[x1][y])){
							MainActivity.getMainActivity().getAnimLayer().createTranslateAnim(cardMap[x1][y], cardMap[x][y], x1, y, x, y);
							
							cardMap[x][y].setNum(cardMap[x1][y].getNum() * 2);
							cardMap[x1][y].setNum(0);
							
							MainActivity.getMainActivity().addScore(cardMap[x][y].getNum());
							merge = true;
						}
						break;
					}
				}
			}
		}
		if(merge){
			addRandomNum();
			checkGameOver();
		}
	}
	
	private void moveRight(){
		boolean merge = false;
		
		for(int y = 0; y < Config.LINES; ++y){
			for(int x = Config.LINES - 1; x > 0; --x){
				for(int x1 = x - 1; x1 >= 0; --x1){
					if(cardMap[x1][y].getNum() > 0){
						if(cardMap[x][y].getNum() <= 0){
							MainActivity.getMainActivity().getAnimLayer().createTranslateAnim(cardMap[x1][y], cardMap[x][y], x1, y, x, y);
							
							cardMap[x][y].setNum(cardMap[x1][y].getNum());
							cardMap[x1][y].setNum(0);
							++x;
							merge = true;
						}else if(cardMap[x][y].equals(cardMap[x1][y])){
							MainActivity.getMainActivity().getAnimLayer().createTranslateAnim(cardMap[x1][y], cardMap[x][y], x1, y, x, y);
							
							cardMap[x][y].setNum(cardMap[x1][y].getNum() * 2);
							cardMap[x1][y].setNum(0);
							
							MainActivity.getMainActivity().addScore(cardMap[x][y].getNum());
							merge = true;
						}
						break;
					}
				}
			}
		}
		if(merge){
			addRandomNum();
			checkGameOver();
		}
	}
	
	private void moveUp(){
		boolean merge = false;
		
		for(int x = 0; x < Config.LINES; ++x){
			for(int y = 0; y < Config.LINES - 1; ++y){
				for(int y1 = y + 1; y1 < Config.LINES; ++y1){
					if(cardMap[x][y1].getNum() > 0){
						if(cardMap[x][y].getNum() <= 0){
							MainActivity.getMainActivity().getAnimLayer().createTranslateAnim(cardMap[x][y1], cardMap[x][y], x, y1, x, y);
							
							cardMap[x][y].setNum(cardMap[x][y1].getNum());
							cardMap[x][y1].setNum(0);
							--y;
							merge = true;
						}else if(cardMap[x][y].equals(cardMap[x][y1])){
							MainActivity.getMainActivity().getAnimLayer().createTranslateAnim(cardMap[x][y1], cardMap[x][y], x, y1, x, y);
							
							cardMap[x][y].setNum(cardMap[x][y1].getNum() * 2);
							cardMap[x][y1].setNum(0);
							
							MainActivity.getMainActivity().addScore(cardMap[x][y].getNum());
							merge = true;
						}
						break;
					}
				}
			}
		}
		if(merge){
			addRandomNum();
			checkGameOver();
		}
	}
	
	private void moveDown(){
		boolean merge = false;
		
		for(int x = 0; x < Config.LINES; ++x){
			for(int y = Config.LINES - 1; y > 0; --y){
				for(int y1 = y - 1; y1 >= 0; --y1){
					if(cardMap[x][y1].getNum() > 0){
						if(cardMap[x][y].getNum() <= 0){
							MainActivity.getMainActivity().getAnimLayer().createTranslateAnim(cardMap[x][y1], cardMap[x][y], x, y1, x, y);
							
							cardMap[x][y].setNum(cardMap[x][y1].getNum());
							cardMap[x][y1].setNum(0);
							++y;
							merge = true;
						}else if(cardMap[x][y].equals(cardMap[x][y1])){
							MainActivity.getMainActivity().getAnimLayer().createTranslateAnim(cardMap[x][y1], cardMap[x][y], x, y1, x, y);
							
							cardMap[x][y].setNum(cardMap[x][y1].getNum() * 2);
							cardMap[x][y1].setNum(0);
							
							MainActivity.getMainActivity().addScore(cardMap[x][y].getNum());
							merge = true;
						}
						break;
					}
				}
			}
		}
		if(merge){
			addRandomNum();
			checkGameOver();
		}
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh){
		super.onSizeChanged(w, h, oldw, oldh);
		
		Config.CARD_WIDTH = (Math.min(w, h) - Config.BORDER_WIDTH) / Config.LINES;
		Log.d("123", "gameView: " + h + " card width: " + Config.CARD_WIDTH);

		clearCards();
		addCards(Config.CARD_WIDTH, Config.CARD_WIDTH);
		restartGame();

	}
	
	private void addCards(int cardWidth, int cardHeight){
		Card c;
		LinearLayout linearLayout;
		LinearLayout.LayoutParams lp;
		
		Log.d("123", "andCards: " + cardHeight);
		for(int y = 0; y < Config.LINES; ++y){
			linearLayout = new LinearLayout(getContext());
			lp = new LayoutParams(-1, cardHeight);
			addView(linearLayout, lp);
			linearLayout.requestLayout();
			linearArray[y] = linearLayout;
			
			for(int x = 0; x < Config.LINES; ++x){
				c = new Card(getContext());
				linearLayout.addView(c, cardWidth, cardHeight);
				c.requestLayout();
				cardMap[x][y] = c;
			}
		}
	}
	
	private void clearCards(){
		for(int x = 0; x < Config.LINES; ++x){
			if(linearArray[x] != null)
				linearArray[x].removeAllViews();
		}
		removeAllViews();
	}
	
	public void restartGame(){
		// 重置分数和所有卡片的值
		MainActivity mainActivity = MainActivity.getMainActivity();
		mainActivity.clearScore();
		
		for(int x = 0; x < Config.LINES; ++x){
			for(int y = 0; y < Config.LINES; ++y){
				cardMap[x][y].setNum(0);
			}
		}
		
		// 添加两个随机卡片
		addRandomNum();
		addRandomNum();
	}
	
	private void addRandomNum(){
		pointList.clear();
		
		for(int x = 0; x < Config.LINES; ++x){
			for(int y = 0; y < Config.LINES; ++y){
				if(cardMap[x][y].getNum() <= 0){
					pointList.add(new Point(x, y));
				}
			}
		}
		if(pointList.size() > 0){
			Point p = pointList.remove((int)(Math.random() * pointList.size()));
			cardMap[p.x][p.y].setNum(Math.random() < 0.9 ? 2 : 4);
			
			MainActivity.getMainActivity().getAnimLayer().createScaleAnim(cardMap[p.x][p.y]);
		}
	}
	
	private void checkGameOver(){
		boolean over = true;
		
		CHECK:
			for(int y = 0; y < Config.LINES; ++y){
				for(int x = 0; x <Config.LINES; ++x){
					if(cardMap[x][y].getNum() == 0 ||
							(x > 0 && cardMap[x][y].equals(cardMap[x-1][y])) ||
							(x < Config.LINES - 1 && cardMap[x][y].equals(cardMap[x+1][y])) ||
							(y > 0 && cardMap[x][y].equals(cardMap[x][y-1])) ||
							(y < Config.LINES - 1 && cardMap[x][y].equals(cardMap[x][y+1]))){
						over = false;
						break CHECK;
					}
				}
			}
		if(over){
			Builder builder = new AlertDialog.Builder(getContext());
			builder.setTitle("提示");
			builder.setMessage("游戏结束");
			builder.setPositiveButton("重新开始", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					restartGame();
				}
			});
			builder.show();
		}
	}
	
	private List<Point> pointList = new ArrayList<>();
	private Card[][] cardMap = new Card[Config.LINES][Config.LINES];
	private LinearLayout linearArray[] = new LinearLayout[Config.LINES];
}
