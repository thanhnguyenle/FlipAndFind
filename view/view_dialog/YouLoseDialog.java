package view_dialog;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import constants.ImageDialog;
import view_support.ButtonImage;

public class YouLoseDialog extends JFrame implements IDialogGame {
	private ButtonImage diLogo;
	private ButtonImage diYoulose;
	private ButtonImage diReturn;
	private ButtonImage diHome;
	private JPanel jp;
	private final int sizeButton = 50;

	public YouLoseDialog() {
		// TODO Auto-generated constructor stub
		setLayout(null);
		// setBackground(new Color(173, 173, 173, 55));
		createComponentInDialog();

		// for frame
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setSize(600, 300);
		setVisible(true);
	}

	@Override
	public void createComponentInDialog() {
		// TODO Auto-generated method stub
		// LOGO
		jp = new JPanel();
		jp.setLayout(null);
		jp.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5, true));
		add(jp);

		diLogo = new ButtonImage(ImageDialog.LOGO2);
		jp.add(diLogo);

		// YOU lose
		diYoulose = new ButtonImage(ImageDialog.YOULOSE);
		jp.add(diYoulose);

		// NEXT
		diHome = new ButtonImage(ImageDialog.HOME);
		jp.add(diHome);

		// Return
		diReturn = new ButtonImage(ImageDialog.RETURN);

		jp.add(diReturn);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		int w = getWidth() / 2;
		int h = getHeight() / 2;
		int highLight = 10;
		jp.setBounds(0, 0, getWidth() - 15, getHeight());

		diLogo.setBounds(0, 0, (int) (jp.getWidth() * (1 / 3.0)), (int) (jp.getHeight() * (4 / 6.0)));

		diYoulose.setBounds((int) (jp.getWidth() * (1 / 3.0)), 0, (int) (jp.getWidth() * (2 / 3.0)),
				(int) (jp.getHeight() * (4 / 6.0)));

		// BUTTON RETURN
		if (!diReturn.isHover())
			diReturn.setBounds((int) (jp.getWidth() * (1 / 3.0)) - sizeButton / 2,
					(int) (jp.getHeight() * (4 / 6.0)) , sizeButton, sizeButton);
		else
			diReturn.setBounds((int) (jp.getWidth() * (1 / 3.0)) - sizeButton / 2 - highLight / 2,
					(int) (jp.getHeight() * (4 / 6.0))  - highLight / 2, sizeButton + highLight,
					sizeButton + highLight);

		// BUTTON HOME
		if (!diHome.isHover())
			diHome.setBounds((int) (jp.getWidth() * (2 / 3.0)) - sizeButton / 2,
					(int) (jp.getHeight() * (4 / 6.0)) , sizeButton, sizeButton);
		else
			diHome.setBounds((int) (jp.getWidth() * (2 / 3.0)) - sizeButton / 2 - highLight / 2,
					(int) (jp.getHeight() * (4 / 6.0))  - highLight / 2, sizeButton + highLight,
					sizeButton + highLight);
	}

	@Override
	public JFrame getThisPanel() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public ButtonImage getDiHome() {
		// TODO Auto-generated method stub
		return diHome;
	}

	public ButtonImage getDiReturn() {
		return diReturn;
	}

}