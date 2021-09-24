package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JProgressBar;
import javax.swing.Timer;

import model_level.LevelData;
import view_menuScreen.IMainScreenView;
import view_menuScreen.PlayGameScreen;
import view_support.LimitTimePanel;

public class ActionLimitTime {
	private LevelData levelData;
	private Timer timer;
	private int limitTime = 0;
	private LimitTimePanel ltp;
	private JProgressBar jpb;
	private int timeRun = 0;
	private double value = 0;
	// view
	private IMainScreenView mainMenu;
	private PlayGameScreen pgs;

	public ActionLimitTime(LimitTimePanel ltp, LevelData levelData, IMainScreenView mainMenu) {
		super();
		this.ltp = ltp;
		this.levelData = levelData;
		this.mainMenu = mainMenu;

		// initial
		jpb = ltp.getJpb();
		timer = createTimer();
		limitTime = levelData.getTimeLimit();
		timeRun = limitTime;

		pgs = (PlayGameScreen) mainMenu.listScreenMenuItems().getScreen("Play Game");
	}

	public void update() {
		ltp.getDtSecond().setTitle((--timeRun <= 0 ? 0 : timeRun) + "");
		value = jpb.getValue();
		// run time
		if (value >= 100) {
			// value when run end
			gameOver();
			return;
		}
		if (limitTime != 0) {
			value += Math.floor(100.0 / Double.valueOf(limitTime));
			jpb.setValue((int) value);
		} else
			gameOver();

	}

	// game over
	public void gameOver() {
		//turn off timer
		timer.stop();
		
		//turn off list card
		pgs.getListCardsView().getThisPanel().setVisible(false);
		
		//open you lose panel
		pgs.setLose(true);
		pgs.getYld().setVisible(true);
	}

	public ActionListener actionProgressBar() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				update();
			}
		};
	}

	public Timer createTimer() {
		//set delay 1000ms
		return new Timer(1000, actionProgressBar());
	}

	public JProgressBar getJpb() {
		return jpb;
	}

	public Timer getTimer() {
		return timer;
	}

	public int getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(int limitTime) {
		this.limitTime = limitTime;
	}

	public int getTimeRun() {
		return timeRun;
	}

	public void setTimeRun(int timeRun) {
		this.timeRun = timeRun;
	}

}
