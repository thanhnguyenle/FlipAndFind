package model_user;

public class User {
	private String userName = "";
	private int age = 15;
	private String languages = "";
	private String countries = "";
	private int rank = 0;
	private int level = 0;
	private int score = 0;
	private String description = "";
	private String pathAvatar = "";

	

	public User(String userName, int age, String languages, String countries, int rank, int level, int score,
			String description) {
		super();
		this.userName = userName;
		this.age = age;
		this.languages = languages;
		this.countries = countries;
		this.rank = rank;
		this.level = level;
		this.score = score;
		this.description = description;
	}

	// Constructors full
	public User(String userName, String description) {
		this(userName,15,"","", 0, 0, 0, description);
		// map is achievements with key is level and value is score

	}

	public User(String userName) {
		this(userName,15,"","", 0, 0, 0, "");
	}

	// Constructors empty
	public User() {
		// TODO Auto-generated constructor stub
		this("", 15,"","",0, 0, 0, "");
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public void setCountries(String countries) {
		this.countries = countries;
	}

	public int getAge() {
		return age;
	}

	public String getLanguages() {
		return languages;
	}

	public String getCountries() {
		return countries;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPathAvatar() {
		return pathAvatar;
	}

	public void setPathAvatar(String pathAvatar) {
		this.pathAvatar = pathAvatar;
	}

}
