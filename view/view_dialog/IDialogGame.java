package view_dialog;

import javax.swing.JFrame;

import view_support.ButtonImage;

public interface IDialogGame {
	public ButtonImage getDiHome();

	public ButtonImage getDiReturn();

	public JFrame getThisPanel();

	public void createComponentInDialog();
}
