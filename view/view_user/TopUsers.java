package view_user;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import model_user.IUserObservable;
import model_user.IUserObserver;
import model_user.ListUsers;
import model_user.User;

public class TopUsers extends JPanel implements IUserObserver {
	private ListUsers listUsers;
	private int value;
	private JTable jtTop;
	private Map<String, Integer> map;
	private final Object[] columnTittle = {"Rank", "Username", "Score"};
	private JScrollPane scrollPane;
	private Object[][] data;
	private int level = 1;
	private Point point;
	public TopUsers(IUserObservable usersData) {
		// TODO Auto-generated constructor stub
		usersData.registerObserver(this);

		// create and action JScrollBar
		if (listUsers != null) {
			createListLabelUsers();
			createJScrollPane();
		}
	}

//create and action JScrollBar
	public void createJScrollPane() {
		// dung JTable de hien thi user va diem cua user
		jtTop = new JTable(data, columnTittle);
		scrollPane = new JScrollPane(jtTop);
		add(scrollPane);
		for (int i = 0; i <data.length; i++) {
			jtTop.setRowHeight(i, 30);
		}
	}

	// create list label user
	public void createListLabelUsers() {
//		lay ra key(user) va diem cua user luu vao mang data
		data = new String[listUsers.getUsers().size()][3];
		int index = 0;
		for (User user : listUsers.getUsers()) {
			String name = user.getUserName();
			Integer score = user.getScore();
			data[index][1] = name;
			data[index][2] = score + "";
			data[index][0] = index+1+"";
			index++;
		}
		
	}

	@Override
	public void update(ListUsers listUsers, int level) {
		// TODO Auto-generated method stub
		this.listUsers = listUsers;
		this.level = level;
		if (jtTop != null && scrollPane != null) {
			removeAll();
		}
		createListLabelUsers();
		createJScrollPane();
		scrollPane.repaint();
		
	}

	public void removeAll() {
		remove(jtTop);
		remove(scrollPane);
	}

	// Draw
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		scrollPane.setBounds(0, 0, getWidth(), getHeight());
		//error repaint
		//jtTop.setBounds(0, 0, getWidth(), getHeight());
	}

	public JTable getJtTop() {
		return jtTop;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}


}
