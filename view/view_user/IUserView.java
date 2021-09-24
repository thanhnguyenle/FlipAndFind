package view_user;

import java.util.Map;

public interface IUserView {
	public String getUserName();
	
	public void setUserName(String userName) ;
	
	public int getRank() ;
	
	public void setRank(int rank) ;
	
	public int getLevel() ;
	
	public void setLevel(int level) ;
	
	public int getScore() ;
	
	public void setScore(int score);
	
	public String getDescription() ;
	
	public void setDescription(String description) ;
	
	public Map<Integer, Integer> getAchievements() ;
}
