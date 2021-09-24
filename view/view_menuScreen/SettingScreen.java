package view_menuScreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.Border;

import constants.ImageButton;
import model_listCards.IObsevableModel;
import model_user.IUserObservable;
import view_support.ButtonImage;

public class SettingScreen extends ScreenMenuItemAbstract implements IScreenMenuItem, IDisplayScreen {
	private static ScreenMenuItemAbstract uniqueInstance;
	private JScrollBar jsbSound;
	private JPanel pnlSettingBox, pnlTittle, pnlSound, pnlResume, pnlTutorial, pnlExit;
	private JPanel[] arrOptionPnl = { pnlResume, pnlTutorial, pnlExit };
	private JButton btnResume, btnTutorial, btnExit;

	private JButton[] arrOptionBtn = { btnResume, btnTutorial, btnExit };
	private JLabel lbTittle = new JLabel("SETTING"), jlSound, jlMusic, jlVolume;
	private ButtonImage diSound, diMusic;
	private ButtonImage[] miniBtn = { diSound, diMusic };

	private SettingScreen(IObservableView screenData, IObsevableModel modelData, IUserObservable userData) {
		super(screenData, modelData, userData);
		// TODO Auto-generated constructor stub
		display();
	}

	// Singleton pattern
	public static ScreenMenuItemAbstract getInstance(IObservableView screenData, IObsevableModel modelData,
			IUserObservable userData) {
		if (uniqueInstance == null) {
			uniqueInstance = new SettingScreen(screenData, modelData, userData);
		}
		return uniqueInstance;
	}

	@Override
	public JPanel getScreen() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public String nameScreen() {
		// TODO Auto-generated method stub
		return "Setting";
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub

		pnlSettingBox = new JPanel(null);

		pnlTittle = new JPanel();
		pnlSettingBox.add(lbTittle);
		lbTittle.setHorizontalAlignment(JLabel.CENTER);
		lbTittle.setForeground(Color.white);
		lbTittle.setFont(new Font("Serif", Font.PLAIN, 28));
		Border blackline = BorderFactory.createLineBorder(Color.white);
		lbTittle.setBorder(blackline);
		lbTittle.setBackground(Color.blue);
		lbTittle.setOpaque(true);

		/*
		 * tao pnl chua 2 button nho: btnMusic &� btnSound
		 */
		pnlSound = new JPanel(null);
		diSound = new ButtonImage(ImageButton.MUSIC);
		diMusic = new ButtonImage(ImageButton.MUSIC);
		pnlSound.add(diSound);
		pnlSound.add(diMusic);

		jlSound = new JLabel("Sound");
		jlSound.setFont(new Font("Arial", Font.BOLD, 18));
		jlMusic = new JLabel("Music");
		jlMusic.setFont(new Font("Arial", Font.BOLD, 18));
		pnlSound.add(jlSound);
		pnlSound.add(jlMusic);

		// jcombo box
		jsbSound = new JScrollBar(JScrollBar.HORIZONTAL, 20, 15, 0, 50);
		pnlSettingBox.add(jsbSound);

		jlVolume = new JLabel("Volume -/+");
		jlVolume.setFont(new Font("Arial", Font.BOLD, 18));
		pnlSettingBox.add(jlVolume);

//		tao va add cac button trong menu setting
		String[] option = { "User Setting", "About", "Return" };
		for (int i = 0; i < arrOptionBtn.length; i++) {
			arrOptionPnl[i] = new JPanel();
			arrOptionBtn[i] = new JButton(option[i]);
			arrOptionPnl[i].add(arrOptionBtn[i]);
			arrOptionBtn[i].setActionCommand(option[i]);
			pnlSettingBox.add(arrOptionBtn[i]);
		}
		pnlSettingBox.setBackground(Color.white);
		pnlSettingBox.setBorder(blackline);
		pnlSound.setBackground(Color.white);
		pnlSettingBox.add(pnlSound);
		// add to setting
		add(pnlSettingBox);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		int insert = 70;
		int h = this.getHeight();
		int w = this.getWidth();

		pnlSettingBox.setBounds((w - 400) / 2, (h - 500) / 2, 400, 480);

		jlSound.setBounds(70, 0, 100, 50 - 20);
		diSound.setBounds(70, 0 + 30, 50, 50);

		jlMusic.setBounds(pnlSound.getWidth() - 70 - 50, 0, 100, 50 - 20);
		diMusic.setBounds(pnlSound.getWidth() - 70 - 50, 0 + 30, 50, 50);

		lbTittle.setBounds(0, 0, pnlSettingBox.getWidth(), 70);
		pnlSound.setBounds(20, 80, pnlSettingBox.getWidth() - 40, 80);

		// jsb
		jlVolume.setBounds(20, 190, pnlSettingBox.getWidth() - 40, 20);
		jsbSound.setBounds(20, 210, pnlSettingBox.getWidth() - 40, 20);

		for (int i = 0; i < arrOptionPnl.length; i++) {
			arrOptionBtn[i].setBounds(50, 200 + (i + 1) * insert, pnlSettingBox.getWidth() - 100, 50);
		}

		repaint();
	}

}
