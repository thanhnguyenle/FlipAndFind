package view_user;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import constants.ImageButton;
import constants.ImageUser;
import model_user.IUserObservable;
import model_user.IUserObserver;
import model_user.ListUsers;
import model_user.User;
import view_menuScreen.IObservableView;
import view_menuScreen.IObserverView;
import view_support.ButtonImage;
import view_support.DrawTitle;

public class UserInform extends JFrame implements IUserObserver, IObserverView {
	private ListUsers listUsers;
	private User user;
	private ButtonImage diAvatar;
	private JPanel jpInformBasic, jpInformInGame;
	private JLabel jl, jlScore, jlLevel, jlRank;
	private JTextField jtfName, jtfAge;
	private JComboBox<String> jcbLanguages, jcbCountries, jcbUsers;
	private JButton jbChange, jbDefault;
	// private String[] dataLanguage, dataCountry;
	private JButton jbSave, jbRemove, jbReturn, jbCreate;

	public UserInform(User user, IUserObservable userData, IObservableView viewData) {
		// TODO Auto-generated constructor stub
		this.user = user;
		userData.registerObserver(this);
		viewData.registerObserver(this);
		createData();

		setSize(500, 550);
		display();
		// for frame
		setLocationRelativeTo(null);
		setVisible(false);
	}

	private void createData() {
		// lang
		String[] dl = { "VietNam", "American", "China", "Japan", "Singapoo", "Korea" };
		// dataLanguage = dl.clone();
		jcbLanguages = new JComboBox<String>(dl);

		// country
		String[] dc = { "VietNam", "American", "China", "Japan", "Korea" };
		// dataCountry = dc.clone();
		jcbCountries = new JComboBox<String>(dc);

		// list user
		jcbUsers = new JComboBox<String>();
	}

	@Override
	public void update(ListUsers listUsers, int level) {
		// TODO Auto-generated method stub
		this.listUsers = listUsers;
		if (listUsers.getUsers().size() != 0) {
				jcbUsers.removeAllItems();
			for (User user : listUsers.getUsers()) {
				jcbUsers.addItem(user.getUserName());
			}
		}
	}

	//
	public void display() {
		JPanel jpBody = new JPanel();
		jpBody.setLayout(new BoxLayout(jpBody, BoxLayout.Y_AXIS));

		// title
		jl = new JLabel("INFORMATION USER");
		jl.setAlignmentX(CENTER_ALIGNMENT);
		jl.setPreferredSize(new Dimension(200, 50));
		jl.setFont(new Font("Arial", Font.BOLD, 20));
		jpBody.add(jl);

		// ABOVE
		JPanel jpAbove = new JPanel(new GridLayout(1, 2));
		// initial dimension
		Dimension dimForButton = new Dimension(90, 30);
		Dimension dimForAvatar = new Dimension(200, 200);
		// avatar

		JPanel jpAvatarPanel = new JPanel();
		jpAvatarPanel.setLayout(new BoxLayout(jpAvatarPanel, BoxLayout.Y_AXIS));

		// jpAvatar
		JPanel jpAvatar = new JPanel(new GridLayout(1, 1));
		diAvatar = new ButtonImage(new ImageIcon(ImageUser.AVATAR[1]).getImage());
		jpAvatar.add(diAvatar);
		jpAvatar.setPreferredSize(dimForAvatar);
		jpAvatar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

		// jp handle avatar
		JPanel jpHandleAvatar = new JPanel();
		jbChange = new JButton("Change");
		jbChange.setPreferredSize(dimForButton);
		jpHandleAvatar.add(jbChange);

		jbDefault = new JButton("Default");
		jbDefault.setPreferredSize(dimForButton);
		jpHandleAvatar.add(jbDefault);

		// add to jpAvatarPanel
		jpAvatarPanel.add(jpAvatar);
		jpAvatarPanel.add(jpHandleAvatar);

		// inform
		// initial dimmension
		Dimension dimForLabel = new Dimension(60, 30);
		Dimension dimForText = new Dimension(150, 30);

		jpInformBasic = new JPanel();
		jpInformBasic.setLayout(new BoxLayout(this.jpInformBasic, BoxLayout.Y_AXIS));

		// name in inform
		JPanel jpUsers = new JPanel();
		jpUsers.add(jl = new JLabel("User:"));
		jl.setPreferredSize(dimForLabel);
		jpUsers.add(jcbUsers);
		jcbUsers.setPreferredSize(dimForText);

		JPanel jpName = new JPanel(new FlowLayout());
		jpName.add(jl = new JLabel("Name:"));
		jl.setPreferredSize(dimForLabel);
		jpName.add(jtfName = new JTextField(15));
		jtfName.setPreferredSize(dimForText);

		// age in inform
		JPanel jpAge = new JPanel(new FlowLayout());
		jpAge.add(jl = new JLabel("Age:"));
		jl.setPreferredSize(dimForLabel);
		jpAge.add(jtfAge = new JTextField(15));
		jtfAge.setPreferredSize(dimForText);

		// lang in inform
		JPanel jpLanguage = new JPanel();
		jpLanguage.add(jl = new JLabel("Language:"));
		jl.setPreferredSize(dimForLabel);
		jpLanguage.add(jcbLanguages);
		jcbLanguages.setPreferredSize(dimForText);

		// lang in inform
		JPanel jpCountry = new JPanel();
		jpCountry.add(jl = new JLabel("Country:"));
		jl.setPreferredSize(dimForLabel);
		jpCountry.add(jcbCountries);
		jcbCountries.setPreferredSize(dimForText);

		// add to inform
		jpInformBasic.add(jpUsers);
		jpInformBasic.add(jpName);
		jpInformBasic.add(jpAge);
		jpInformBasic.add(jpLanguage);
		jpInformBasic.add(jpCountry);

		// add to jpAbove
		jpAbove.add(jpAvatarPanel);
		jpAbove.add(jpInformBasic);

		// UNDER
		JPanel jpUnder = new JPanel();
		// jpUnder.
		jpInformInGame = new JPanel();
		jpInformInGame.setLayout(new GridLayout(1, 3, 10, 10));
		jpInformInGame.setPreferredSize(new Dimension(getWidth(), 100));

		// format text
		int sizeText = 22;
		int sizeIndex = 20;

		// label
		jlScore = new JLabel("0");
		JPanel jpScore = informIndexPanel(jlScore, "SCORE", sizeText, sizeIndex);

		jlLevel = new JLabel("0");
		JPanel jpLevel = informIndexPanel(jlLevel, "LEVEL", sizeText, sizeIndex);

		jlRank = new JLabel("0");
		JPanel jpRank = informIndexPanel(jlRank, "RANK", sizeText, sizeIndex);

		// add to jpInformInGame
		jpInformInGame.add(new JPanel());
		jpInformInGame.add(jpScore);
		jpInformInGame.add(jpLevel);
		jpInformInGame.add(jpRank);
		jpInformInGame.add(new JPanel());

		// function
		Dimension dimButton = new Dimension(50, 30);
		JPanel jpFunction = new JPanel(new GridLayout(1, 3, 15, 150));
		jpFunction.add(jbSave = new JButton("Save"));
		jbSave.setPreferredSize(dimButton);
		jpFunction.add(jbCreate = new JButton("Create"));
		jbCreate.setPreferredSize(dimButton);
		jpFunction.add(jbRemove = new JButton("Remove"));
		jbRemove.setPreferredSize(dimButton);
		jpFunction.add(jbReturn = new JButton("Return"));
		jbReturn.setPreferredSize(dimButton);

		// add to jpUnder
		jpUnder.add(jpInformInGame);

		// add to jp
		jpBody.add(jpAbove);
		jpBody.add(jpUnder);
		jpBody.add(jpFunction);
		// add to jframe
		add(jpBody);
	}

	private JPanel informIndexPanel(JLabel jlabel, String name, int sizeText, int sizeIndex) {
		JPanel jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		jp.add(jl = new JLabel(name));
		jl.setAlignmentX(CENTER_ALIGNMENT);
		jl.setFont(new Font("Arial", Font.BOLD, sizeText));
		jp.add(jlabel);
		jlabel.setFont(new Font("Arial", Font.PLAIN, sizeIndex));
		jlabel.setAlignmentX(CENTER_ALIGNMENT);
		jlabel.setAlignmentY(CENTER_ALIGNMENT);
		jp.setBackground(Color.cyan);
		return jp;
	}

	public JComboBox<String> getJcbUsers() {
		return jcbUsers;
	}

	@Override
	public void update(User user, int nowScore, int nowLevel) {
		// TODO Auto-generated method stub
		this.user = user;
		jlScore.setText(user.getScore() + "");
		jlLevel.setText(user.getLevel() + "");
		jlRank.setText(user.getRank() + "");
		jtfName.setText(user.getUserName());
		jtfAge.setText(user.getAge() + "");
		
		// update language\
		int indexLang = indexOfLanguages(user.getLanguages().toLowerCase());
		if (indexLang == -1) {
			jcbLanguages.addItem(user.getLanguages());
		} else {
			jcbLanguages.setSelectedIndex(indexLang);
		}
		
		// update country
		int indexCountry = indexOfCountries(user.getCountries().toLowerCase());
		if (indexCountry == -1) {
			jcbCountries.addItem(user.getCountries());
		} else {
			jcbCountries.setSelectedIndex(indexCountry);
		}

		for (int i = 0; jcbUsers.getItemCount() > i; i++)
			if (jcbUsers.getItemAt(i).toLowerCase().equals(user.getUserName()))
				jcbUsers.setSelectedIndex(i);
	}

	public int indexOfLanguages(String languages) {
		for (int i = 0; jcbLanguages.getItemCount() > i; i++)
			if (jcbLanguages.getItemAt(i).toLowerCase().equals(languages))
				return i;
		return -1;
	}

	public int indexOfCountries(String country) {
		for (int i = 0; jcbCountries.getItemCount() > i; i++)
			if (jcbCountries.getItemAt(i).toLowerCase().equals(country))
				return i;
		return -1;
	}

	public int indexOfUsers(String user) {
		for (int i = 0; jcbUsers.getItemCount() > i; i++)
			if (jcbUsers.getItemAt(i).toLowerCase().equals(user))
				return i;
		return -1;
	}

	public JButton getJbCreate() {
		return jbCreate;
	}

	public JButton getJbSave() {
		return jbSave;
	}

	public JButton getJbRemove() {
		return jbRemove;
	}

	public JButton getJbReturn() {
		return jbReturn;
	}

	public ListUsers getListUsers() {
		return listUsers;
	}

	public User getUser() {
		return user;
	}

	public ButtonImage getDiAvatar() {
		return diAvatar;
	}

	public JTextField getJtfName() {
		return jtfName;
	}

	public JTextField getJtfAge() {
		return jtfAge;
	}

	public JComboBox<String> getJcbLanguages() {
		return jcbLanguages;
	}

	public JComboBox<String> getJcbCountries() {
		return jcbCountries;
	}

	public JButton getJbChange() {
		return jbChange;
	}

	public JButton getJbDefault() {
		return jbDefault;
	}

	public JLabel getJlScore() {
		return jlScore;
	}

	public void setJlScore(JLabel jlScore) {
		this.jlScore = jlScore;
	}

	public JLabel getJlLevel() {
		return jlLevel;
	}

	public void setJlLevel(JLabel jlLevel) {
		this.jlLevel = jlLevel;
	}

	public JLabel getJlRank() {
		return jlRank;
	}

	public void setJlRank(JLabel jlRank) {
		this.jlRank = jlRank;
	}

}
