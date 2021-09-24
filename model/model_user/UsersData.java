package model_user;

import java.util.LinkedList;
import java.util.List;

public class UsersData implements IUserObservable{
	private List<IUserObserver> observers ;
	private ListUsers listUsers;
	private int level;
	
	public UsersData() {
		super();
		observers = new LinkedList<>();
	}

	public ListUsers getListUsers() {
		return listUsers;
	}

	@Override
	public void registerObserver(IUserObserver o) {
		// TODO Auto-generated method stub
		observers.add(o);
	}

	@Override
	public void unregisterObserver(IUserObserver o) {
		// TODO Auto-generated method stub
		observers.remove(o);
	}

	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stub
		for(int i = 0;i<observers.size();i++) {
			IUserObserver observer = (IUserObserver) observers.get(i);
			observer.update(listUsers,level);
		}
	}
	
	public void dataUsersChange() {
		notifyObserver();
	}
	
	public void setUsersData(ListUsers newListUsers,int level) {
		this.listUsers =newListUsers;
		this.level = level;
		dataUsersChange();
	}
	
}
