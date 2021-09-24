package view_support;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class LimitTimePanel extends JPanel {
	private DrawTitle dtSecond;
	private JProgressBar jpb;

	public LimitTimePanel() {
		super();
		setLayout(null);
		jpb = new JProgressBar();
		add(jpb);

		dtSecond = new DrawTitle("START",true);
		dtSecond.setFont(new Font("Algerian", Font.PLAIN, 20));
		add(dtSecond);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		jpb.setBounds(5, 1, (int) (9 * getWidth() / 10.0) - 5, getHeight()-1 );
		dtSecond.setBounds((int) (9 * getWidth() / 10.0) - 5, 1, (int) (getWidth() / 10.0) - 5, getHeight()-1 );

	}

	public DrawTitle getDtSecond() {
		return dtSecond;
	}

	public JProgressBar getJpb() {
		return jpb;
	}

}
