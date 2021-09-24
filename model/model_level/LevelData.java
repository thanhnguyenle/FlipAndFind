package model_level;

import model_listCards.IListCardsAdapter;

public class LevelData {
	private int level;
	private int timeLimit = 0;
	private ILevel normal,high,advance,expert;
	public LevelData(int level) {
		super();
		this.level = level;
		 normal = new LowLevel();
		 high = new HighLevel();
		 advance = new AdvanceLevel();
		 expert = new ExpertLevel();
	}

	public LevelData() {
		this(1);
	}

	public int getTimeLimit() {
		return timeLimit;
	}

	// get list card by level - use FACTORY PATTERN
	public IListCardsAdapter getListCardsLevel() {
		IListCardsAdapter listCards = null;
		if (level <= 5) {
			listCards = normal.createListCardsLevel(level);
			timeLimit = normal.getLimitTime();
		} else if (level <= 10) {
			listCards = high.createListCardsLevel(level);
			timeLimit = high.getLimitTime();
		} else if (level <= 20) {
			listCards = advance.createListCardsLevel(level);
			timeLimit = advance.getLimitTime();
		} else if (level <= 40) {
			listCards = expert.createListCardsLevel(level);
			timeLimit = expert.getLimitTime();
		}
		return listCards;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
