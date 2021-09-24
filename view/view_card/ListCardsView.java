package view_card;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import constants.ImageCard;
import model_level.LevelData;
import model_listCards.ICardAdapter;
import model_listCards.IListCardsAdapter;
import model_listCards.IObserverModel;
import model_listCards.IObsevableModel;
import model_listCards.ModelDataListCard;

public class ListCardsView extends JPanel implements IListCardsView, IObserverModel {
	private IObsevableModel modelData;
	private int numCards;
	private IListCardsAdapter listCards;
	private List<ICardView> listCardsView;
	private ICardView cardView;
	private int column, row;

	public ListCardsView(IObsevableModel modelData) {
		super();
		modelData.registerObserver(this);
		listCardsView = new ArrayList<>();
		setLayout(null);
	}

	public int getNumCards() {
		return listCards.getNumCards();
	}

	// create list cards view
	public void createListCardsView() {
		// tam thoi de the nay da
		setNumCell();
		listCardsView.clear();
		for (int i = 0; i < listCards.getNumCards() * 2; i++) {
			ICardAdapter card = listCards.getListCards().get(i);
			listCardsView.add(cardView = new CardView(card, ImageCard.FRONTCARD[card.getIdCard()]));

			// set position in panel
			cardView.getThisCardJPanel().setBounds((int) (5 + (i % column) * (getWidth() / column)),
					(int) (5 + (i / column) * (getHeight() / row)), (int) (getWidth() / column) - 10,
					(int) (getHeight() / row) - 10);

			// add to panel
			add(cardView.getThisCardJPanel());
		}
	}

	public IListCardsAdapter getIListCards() {
		return listCards;
	}

	public void setListCards(IListCardsAdapter listCards) {
		this.listCards = listCards;
	}

	@Override
	public ICardView[] getCard(int id) {
		// TODO Auto-generated method stub
		ICardView[] iCardView = new ICardView[2];
		int[] position = listCards.getPositonOfIdCard(id);

		iCardView[0] = listCardsView.get(position[0]);
		iCardView[1] = listCardsView.get(position[1]);

		return iCardView;
	}

	@Override
	public int[] getPositonOfIdCard(int id) {
		// TODO Auto-generated method stub
		int[] position = listCards.getPositonOfIdCard(id);
		return position;
	}

	@Override
	public void shuffle() {
		// TODO Auto-generated method stub
		listCards.shuffle();
		removeAllCards();
		listCardsView.clear();
		createListCardsView();
		repaint();
	}

	@Override
	public List<ICardView> getListCards() {
		// TODO Auto-generated method stub
		return listCardsView;
	}

	@Override
	public void setNumCell(int row, int column) {
		// TODO Auto-generated method stub
		this.row = row;
		this.column = column;
	}

	@Override
	public void setNumCell() {
		// TODO Auto-generated method stub
		// compete
		int numCell = numCards * 2;
		if (numCards == 1) {
			this.row = 1;
			this.column = 2;
		} else if (numCards == 2) {
			this.row = 2;
			this.column = 2;
		} else if (this.numCards == 3) {
			this.row = 2;
			this.column = 3;
		} else if (this.numCards > 3) {
			int k = 6;
			while (k != 0) {
				if (numCell % k == 0) {
					this.column = k;
					this.row = numCell / k;
					break;
				} else
					k--;
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);

		// set position in panel
		int width = (int) (this.getWidth() / column) - 10;
		int height = (int) (this.getHeight() / row) - 10;
		for (int i = 0; i < listCardsView.size(); i++) {
			JPanel jp = listCardsView.get(i).getThisCardJPanel();
			jp.setBounds((int) (5 + (i % column) * (this.getWidth() / column)),
					(int) (5 + (i / column) * (this.getHeight() / row)), width,
					height);
		}
	}

	@Override
	public void update(IListCardsAdapter listCards) {
		// TODO Auto-generated method stub
		this.listCards = listCards;
		numCards = listCards.getNumCards();
		removeAllCards();
		setNumCell();
		createListCardsView();
		repaint();
	}

	@Override
	public void setFaceDownAll() {
		// TODO Auto-generated method stub
		for (ICardView cv : listCardsView) {
			cv.getThisCardJPanel().setEnabled(true);
			cv.getCard().setFaceDown();
			cv.getCard().setHidden(false);
			cv.getThisCardJPanel().repaint();
			
		}
		repaint();
	}

	@Override
	public void repaintListCards() {
		// TODO Auto-generated method stub
		for (ICardView cv : listCardsView) {
			cv.getThisCardJPanel().repaint();
		}
		repaint();
	}

	@Override
	public JPanel getThisPanel() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void removeAllCards() {
		// TODO Auto-generated method stub
		for (ICardView card : listCardsView) {
			remove(card.getThisCardJPanel());
		}
	}

}
