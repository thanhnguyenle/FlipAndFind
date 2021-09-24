package view_dialog;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import constants.ImageDialog;
import view_support.ButtonImage;
import view_support.DrawTitle;

public class YouWinDialog extends JFrame implements IDialogGame {
	private ButtonImage diLogo;
	private ButtonImage diYouWin;
	private ButtonImage diNext;
	private ButtonImage diReturn;
	private ButtonImage diHome;
	private DrawTitle dtQuestion;
	private final int sizeButton = 50;
	private JPanel jp;
	private boolean isNext;

	public YouWinDialog() {
		// TODO Auto-generated constructor stub
		// set this panel
	    setLayout(null);
		// setBackground(new Color(173, 173, 173, 55));
		createComponentInDialog();

		// for frame
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setSize(600, 300);
		setVisible(false);

	}

	public void createComponentInDialog() {
		jp = new JPanel();
		jp.setLayout(null);
		int w = getWidth() / 4;
		int h = getHeight() / 4;
		jp.setBorder(BorderFactory.createLineBorder(Color.RED, 5, true));
		add(jp);

		// LOGO
		diLogo = new ButtonImage(ImageDialog.LOGO);
		jp.add(diLogo);

		// YOU Win
		diYouWin = new ButtonImage(ImageDialog.YOUWIN);
		jp.add(diYouWin);

		// NEXT
		diNext = new ButtonImage(ImageDialog.NEXT);
		jp.add(diNext);

		// HOME
		diHome = new ButtonImage(ImageDialog.HOME);
		jp.add(diHome);

		// Return
		diReturn = new ButtonImage(ImageDialog.RETURN);
		jp.add(diReturn);
	}

	public ButtonImage getDiHome() {
		return diHome;
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		int w = getWidth() / 2;
		int h = getHeight() / 2;
		int highLight = 10;
		jp.setBounds(0,0,getWidth()-15,getHeight());
		diLogo.setBounds(0, 0, (int) (jp.getWidth() * (1 / 3.0)), (int) (jp.getHeight() * (4 / 6.0)));
		diYouWin.setBounds((int) (jp.getWidth() * (1 / 3.0)), 0, (int) (jp.getWidth() * (2 / 3.0)),
				(int) (jp.getHeight() * (4 / 6.0)));

		// BUTTON NEXT
		if (!diNext.isHover())
			diNext.setBounds((int) (jp.getWidth() * (3 / 4.0)) - sizeButton / 2,
					(int) (jp.getHeight() * (4 / 6.0)) , sizeButton, sizeButton);
		else
			diNext.setBounds((int) (jp.getWidth() * (3 / 4.0)) - sizeButton / 2 - highLight / 2,
					(int) (jp.getHeight() * (4 / 6.0)) - highLight / 2, sizeButton + highLight,
					sizeButton + highLight);

		// BUTTON RETURN
		if (!diReturn.isHover())
			diReturn.setBounds((int) (jp.getWidth() * (1 / 4.0)) - sizeButton / 2,
					(int) (jp.getHeight() * (4 / 6.0)) , sizeButton, sizeButton);
		else
			diReturn.setBounds((int) (jp.getWidth() * (1 / 4.0)) - sizeButton / 2 - highLight / 2,
					(int) (jp.getHeight() * (4 / 6.0))  - highLight / 2, sizeButton + highLight,
					sizeButton + highLight);

		// BUTTON HOME
		if (!diHome.isHover())
			diHome.setBounds((int) (jp.getWidth() * (1 / 2.0)) - sizeButton / 2,
					(int) (jp.getHeight() * (4 / 6.0)) , sizeButton, sizeButton);
		else
			diHome.setBounds((int) (jp.getWidth() * (1 / 2.0)) - sizeButton / 2 - highLight / 2,
					(int) (jp.getHeight() * (4 / 6.0))  - highLight / 2, sizeButton + highLight,
					sizeButton + highLight);
	}

	public ButtonImage getDiNext() {
		return diNext;
	}

	public ButtonImage getDiReturn() {
		return diReturn;
	}

	public boolean isNext() {
		return isNext;
	}

	public void setNext(boolean isNext) {
		this.isNext = isNext;
	}

	@Override
	public JFrame getThisPanel() {
		// TODO Auto-generated method stub
		return this;
	}

}
