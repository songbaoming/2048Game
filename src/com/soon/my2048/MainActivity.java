package com.soon.my2048;

import android.app.Activity;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	public MainActivity(){
		mainActivity = this;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		root = (LinearLayout)findViewById(R.id.container);
		root.setBackgroundColor(0xfffaf8ef);
		
		scoreTextView = (TextView)findViewById(R.id.cur_score);
		bestScoreTextView = (TextView)findViewById(R.id.best_score);
		gameView = (GameView)findViewById(R.id.game_view);
		animLayer = (AnimLayer)findViewById(R.id.anim_view);
		
		TextView button = (TextView)findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gameView.restartGame();
			}
		});
		
		getBestScore();
		showBestScore();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onDestroy(){
		saveBestScore();
		super.onDestroy();
	}
	
	public void clearScore(){
		score = 0;
		showScore();
	}
	
	public void showScore(){
		scoreTextView.setText("" + score);
	}
	
	public void addScore(int s){
		score += s;
		showScore();
		
		if(score > bestScore){
			bestScore = score;
			showBestScore();
		}
	}
	
	public void getBestScore(){
		bestScore = getPreferences(MODE_PRIVATE).getInt(KEY_BEST_SCORE_STRING, 0);
	}
	
	public void showBestScore(){
		bestScoreTextView.setText("" + bestScore);
	}
	
	public void saveBestScore(){
		Editor e = getPreferences(MODE_PRIVATE).edit();
		e.putInt(KEY_BEST_SCORE_STRING, bestScore);
		e.commit();
	}
	
	public static MainActivity getMainActivity(){
		return mainActivity;
	}
	
	public AnimLayer getAnimLayer(){
		return animLayer;
	}
	
	public GameView getGameView(){
		return gameView;
	}
	
	private int score;
	private int bestScore;
	private TextView scoreTextView;
	private TextView bestScoreTextView;
	private GameView gameView;
	private AnimLayer animLayer;
	private LinearLayout root;
	
	private static MainActivity mainActivity = null;
	public static final String KEY_BEST_SCORE_STRING = "bestScore";
}
