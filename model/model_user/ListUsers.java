package model_user;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;

public class ListUsers {
	private List<User> users;

	public ListUsers() {
		// TODO Auto-generated constructor stub
		this(new ArrayList<>());
	}

	public ListUsers(List<User> users) {
		super();
		this.users = users;
	}

	public boolean addUser(User user) {
		return users.add(user);
	}

	public void removeUser(User user) {
		users.remove(user);
	}

	public void clear() {
		users.clear();
	}

	// sort by score
	public List<User> sortByScore() {
		List<User> listUsers = new ArrayList<>();
		listUsers.addAll(users);
		listUsers.sort(new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				// TODO Auto-generated method stub
				return o2.getScore() - o1.getScore();
			}
		});
		return listUsers;
	}

	// sort by rank
	public List<User> sortByRank() {
		List<User> listUsers = new ArrayList<>();
		listUsers.addAll(users);
		listUsers.sort(new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				// TODO Auto-generated method stub
				return o2.getRank() - o1.getRank();
			}
		});
		return listUsers;
	}

	// sort by level
	public List<User> sortByLevel() {
		List<User> listUsers = new ArrayList<>();
		listUsers.addAll(users);
		listUsers.sort(new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				// TODO Auto-generated method stub
				return o2.getLevel() - o1.getLevel();
			}
		});
		return listUsers;
	}

	public List<User> getUsers() {
		return users;
	}

	public User getUser(String name) {
		for (User user : getUsers()) {
			if (user.getUserName().equals(name)) {
				return user;
			}
		}
		return null;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
