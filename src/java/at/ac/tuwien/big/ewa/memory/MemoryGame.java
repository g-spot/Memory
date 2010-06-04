/**
 * <copyright>
 *
 * Copyright (c) 2010 http://www.big.tuwien.ac.at
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * </copyright>
 */

package at.ac.tuwien.big.ewa.memory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import at.ac.tuwien.big.ewa.memory.MemoryCard.CardState;

/**
 * Class representing a memory game.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class MemoryGame {

	/**
	 * Enumeration describing the possible state of a {@link MemoryGame}.
	 * 
	 * The states change following the sequence below:
	 * 
	 * <ul>
	 * <li>
	 * <code>INIT</code>: Game is in initial state.<br/>
	 * The player may now select one card to uncover.</li>
	 * <li>
	 * <code>CARD1</code>: One card is uncovered in the current turn.<br/>
	 * The player may now select another card to uncover, but not the same card
	 * that is already uncovered.</li>
	 * <li>
	 * <code>CARD2_MATCH</code> and <code>CARD2_UNMATCH</code> : Two cards are
	 * uncovered in the current turn.<br/>
	 * The uncovered pair is either a match, or an unmatch. If it is a match,
	 * the cards remain uncovered. If not they are covered again. The user may
	 * now select a new first card to uncover which moves the game to state
	 * <code>CARD1</code> again as long as there are still uncovered pairs. If
	 * there are no uncovered pairs left, the game switches to state
	 * <code>GAME_OVER</code></li>
	 * <li>
	 * <code>GAME_OVER</code>: The game is over, all cards are uncovered.<br/>
	 * The game is over. No cards can be uncovered any more since every card is
	 * already uncovered.</li>
	 * </ul>
	 * 
	 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
	 * 
	 */
	public enum GameState {
		/**
		 * Initial state for each turn. No card is uncovered.
		 */
		NO_CARD,
		/**
		 * if the first card has been uncovered.
		 */
		CARD1,
		/**
		 * if the second card has been uncovered and it is a match.
		 */
		CARD2_MATCH,
		/**
		 * if the second card has been uncovered and it is an unmatch.
		 */
		CARD2_UNMATCH,
		/**
		 * If the game is over.
		 */
		GAME_OVER
	}

	/**
	 * The current {@link GameState}.
	 */
	private GameState currentState = GameState.NO_CARD;

	/**
	 * A table with memory cards.
	 */
	private MemoryCard[][] table;

	/**
	 * Collection of all covered card pairs in this game.
	 */
	private Collection<MemoryCardPair> coveredPairs = new HashSet<MemoryCardPair>();

	/**
	 * Collection of all uncovered card pairs in this game.
	 */
	private Collection<MemoryCardPair> uncoveredPairs = new HashSet<MemoryCardPair>();

	/**
	 * The players playing in this game.
	 */
	private List<MemoryPlayer> players;

	/**
	 * The current player.
	 */

	private MemoryPlayer currentPlayer;

	/**
	 * Saves the time spent by a player
	 */
	private Map<MemoryPlayer, Long> spentTimePlayerMap = new HashMap<MemoryPlayer, Long>();

	/**
	 * Current card 1. Might be <code>null</code> depending on the state of this
	 * game.
	 */
	private MemoryCard currentCard1 = null;

	/**
	 * Current card 2. Might be <code>null</code> depending on the state of this
	 * game.
	 */
	private MemoryCard currentCard2 = null;

	/**
	 * Starting time of the current player.
	 */
	private long currentTimeStart = System.currentTimeMillis();

	/**
	 * Constructs a new {@link MemoryGame} for the specified
	 * <code>players</code> and the specified <code>cardIds</code>.
	 * 
	 * For each id in <code>cardIds</code>, two cards are created and added to
	 * the table. Consequently, there are <code>cardIds.size() * 2</code> cards
	 * in this game. The order of the {@link MemoryPlayer}s as well as the order
	 * of the cards is randomized.
	 * 
	 * @param players
	 *            {@link MemoryPlayer}s playing this game.
	 * @param cardIds
	 *            ids to create cards for.
	 */
	public MemoryGame(List<MemoryPlayer> players, List<String> cardIds) {
                System.out.println("MemoryGame() with " + players.size() + " players");
		// guard illegal arguments
		if (players.size() < 1) {
			throw new IllegalArgumentException(
					"At least one player is necessary to play.");
		}
		if (cardIds.size() < 1) {
			throw new IllegalArgumentException(
					"At least one card id is necessary to play.");
		}
		// initialize this game
		initializePlayers(players);
		initializeTable(cardIds);
	}

	/**
	 * Initializes the table for the specified <code>cardIds</code>.
	 * 
	 * @param cardIds
	 *            to create table for.
	 */
	private void initializeTable(List<String> cardIds) {
		// create list of cards
		List<MemoryCard> cards = new ArrayList<MemoryCard>();
		for (String cardId : cardIds) {
			// create the two cards with the same id
			MemoryCard card1 = new MemoryCard(cardId);
			MemoryCard card2 = new MemoryCard(cardId);
			// add them to working list
			cards.add(card1);
			cards.add(card2);
			// add them to list of pairs
			coveredPairs.add(new MemoryCardPair(card1, card2));
		}

		// shuffle working list
		Collections.shuffle(cards);

		// calculate matrix size
		double squareRoot = Math.sqrt(cards.size());
		int columnsCount = (int) Math.round(squareRoot);
		int rowCount = (int) Math.ceil(squareRoot);

		// initialize matrix
		Iterator<MemoryCard> iterator = cards.iterator();
		table = new MemoryCard[rowCount][columnsCount];
		for (int row = 0; row < rowCount; row++) {
			for (int column = 0; column < columnsCount; column++) {
				if (iterator.hasNext()) {
					MemoryCard card = iterator.next();
					card.setColumn(column);
					card.setRow(row);
					table[row][column] = card;
				}
			}
		}
	}

	/**
	 * Initializes the list of players.
	 * 
	 * @param players
	 *            to initialize list with.
	 */
	private void initializePlayers(List<MemoryPlayer> players) {
                System.out.println("INIT PLAYERS " + players.size());
		this.players = new ArrayList<MemoryPlayer>();
		this.players.addAll(players);
		Collections.shuffle(this.players);
		this.currentPlayer = this.players.get(0);
                System.out.println("SUCCESS: " + this.currentPlayer.getName());

		// initialize time table
		for (MemoryPlayer player : this.players) {
			this.spentTimePlayerMap.put(player, 0l);
		}

		// set start time
		this.currentTimeStart = System.currentTimeMillis();
	}

	/**
	 * Returns the current state of this game.
	 * 
	 * @return the current state.
	 */
	public GameState getCurrentState() {
		return currentState;
	}

	/**
	 * The table of {@link MemoryCard}s. It might contain positions with no
	 * cards (<code>null</code> values) at the end.
	 * 
	 * <p>
	 * <b>Caution:</b> Do not modify this array! This might break the
	 * functionality of this class.
	 * </p>
	 * 
	 * @return the table of this game.
	 */
	public MemoryCard[][] getTable() {
		return table;
	}

	/**
	 * The current player.
	 * 
	 * @return the current player.
	 */
	public MemoryPlayer getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Returns all players playing this game.
	 * 
	 * @return all the players.
	 */
	public List<MemoryPlayer> getPlayers() {
            return Collections.unmodifiableList(players);
	}

        public void addPlayer(MemoryPlayer player)
        {
            players.add(player);
            this.spentTimePlayerMap.put(player, 0l);
        }

	/**
	 * Returns the currently uncovered card 1. Might be null, if no card is
	 * uncovered in this turn.
	 * 
	 * @return the currently uncovered card1.
	 */
	public MemoryCard getCurrentCard1() {
		return currentCard1;
	}

	/**
	 * Returns the currently uncovered card 2. Might be null, if no card is
	 * uncovered in this turn.
	 * 
	 * @return the currently uncovered card2.
	 */
	public MemoryCard getCurrentCard2() {
		return currentCard2;
	}

	/**
	 * Returns the card in the specified position.
	 * 
	 * @param columnCardIndex
	 *            column card index of card in question.
	 * @param rowCardIndex
	 *            row card index of card in question.
	 * @return the {@link MemoryCard} or <code>null</code> if there is no card
	 *         at this position.
	 */
	public MemoryCard getCardByIndex(int rowCardIndex, int columnCardIndex) {
		try {
			return table[rowCardIndex][columnCardIndex];
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * Specifies whether this game is over or not.
	 * 
	 * @return <code>true</code> if this game is over, <code>false</code>
	 *         otherwise.
	 */
	public boolean isGameOver() {
		return this.currentState.equals(GameState.GAME_OVER);
	}

	/**
	 * Specifies whether the specified <code>player</code> may now uncover the
	 * specified <code>card</code>.
	 * 
	 * @param player
	 *            in question.
	 * @param columnCardIndex
	 *            column card index of card in question.
	 * @param rowCardIndex
	 *            row card index of card in question.
	 * @return <code>true</code> if is allowed in the current state,
	 *         <code>false</code> otherwise.
	 */
	public boolean mayUncoverCard(MemoryPlayer player, int columnCardIndex,
			int rowCardIndex) {
		MemoryCard card = getCardByIndex(rowCardIndex, columnCardIndex);
		if (card != null) {
			return mayUncoverCard(player, card);
		} else {
			return false;
		}
	}

	/**
	 * Specifies whether the specified <code>player</code> may act now.
	 * 
	 * @param player
	 *            in question.
	 * @return <code>true</code> if player may act, <code>false</code>
	 *         otherwise.
	 */
	public boolean mayAct(MemoryPlayer player) {
		if (currentPlayer.equals(player)) {
			return true;
		} else {
			// it's not the player's turn
			return false;
		}
	}

	/**
	 * Specifies whether the specified <code>card</code> may be uncovered now.
	 * 
	 * @param card
	 *            in question.
	 * @return <code>true</code> if <code>card</code> may be uncovered,
	 *         <code>false</code> otherwise.
	 */
	public boolean mayUncoverCard(MemoryCard card) {
		// we can't uncover if...
		if (isGameOver()) {
			// game is over
			return false;
		} else if (card.getCurrentState().equals(CardState.UNCOVERED)) {
			if (!GameState.CARD2_MATCH.equals(currentState)
					&& !GameState.CARD2_UNMATCH.equals(currentState)) {
				// card is already uncovered and we are not in CARD2 state
				return false;
			}
		}

		return true;
	}

	/**
	 * Specifies whether the specified <code>player</code> may now uncover the
	 * specified <code>card</code>.
	 * 
	 * @param player
	 *            in question.
	 * @param card
	 *            in question.
	 * @return <code>true</code> if is allowed in the current state,
	 *         <code>false</code> otherwise.
	 */
	public boolean mayUncoverCard(MemoryPlayer player, MemoryCard card) {
		// check player
		if (!mayAct(player)) {
			// it's not the player's turn
			return false;
		} else
		// check card
		if (!mayUncoverCard(card)) {
			// card may not be uncovered in the current state
			return false;
		} else {
			// everything is ok
			return true;
		}
	}

	/**
	 * Call this method to specify that <code>player</code> uncovers
	 * <code>card</code>.
	 * 
	 * @param player
	 *            uncovering the card.
	 * @param card
	 *            card to be uncovered.
	 * @throws IllegalArgumentException
	 *             if <code>player</code> may not uncover <code>card</code>.
	 */
	public void uncover(MemoryPlayer player, MemoryCard card)
			throws IllegalArgumentException {
		// guard forbidden uncover action
		if (!mayUncoverCard(player, card)) {
			throw new IllegalArgumentException(
					"It is either not the turn of the specified user"
							+ " or the card must not be uncovered in the"
							+ " current state.");
		}

		// handle uncover action according to the current state
		switch (this.currentState) {

		case NO_CARD:
			// uncover first card and move to state CARD1
			_uncover(player, card);
			this.currentCard1 = card;
			currentState = GameState.CARD1;
			break;

		case CARD1:
			// uncover second card, check for pair, move to next player, and
			// move to state CARD2 or to GAME_OVER
			_uncover(player, card);
			this.currentCard2 = card;
			boolean hadMatch = compareCurrentCards();
			//moveToNextPlayer();
			if (coveredPairs.size() > 0) {
				if (hadMatch) {
					currentState = GameState.CARD2_MATCH;
				} else {
					currentState = GameState.CARD2_UNMATCH;
                                        moveToNextPlayer();
				}
			} else {
				currentState = GameState.GAME_OVER;
			}
			break;

		case CARD2_MATCH:
			// reset variables for next iteration
			currentCard1 = null;
			currentCard2 = null;
			hadMatch = false;
			// reset state to NO_CARD
			currentState = GameState.NO_CARD;
			uncover(player, card);
			break;

		case CARD2_UNMATCH:
			// cover both cards if we had no match
			currentCard1.cover();
			currentCard2.cover();
			// reset variables for next iteration
			currentCard1 = null;
			currentCard2 = null;
			hadMatch = false;
			// reset state to NO_CARD
			currentState = GameState.NO_CARD;
			uncover(player, card);
			break;

		}
	}

	/**
	 * Returns the spent time in milliseconds of the specified
	 * <code>player</code>.
	 * 
	 * @param player
	 *            to get spent time for.
	 * @return spent time in milliseconds.
	 */
	public long getSpentTime(MemoryPlayer player) {
		return spentTimePlayerMap.get(player);
	}
	
	/**
	 * Returns all currently covered pairs.
	 * @return all currently covered pairs.
	 */
	public Collection<MemoryCardPair> getCoveredPairs() {
		return Collections.unmodifiableCollection(coveredPairs);
	}
	
	/**
	 * Returns the number of currently covered pairs.
	 * @return the number of currently covered pairs.
	 */
	public int getCoveredPairsCount() {
		return getCoveredPairs().size();
	}

	/**
	 * Changes current player to the next one.
	 */
	private void moveToNextPlayer() {
		// calculate and add spent time
		long spentTime = System.currentTimeMillis() - this.currentTimeStart;
		spentTimePlayerMap.put(currentPlayer, spentTimePlayerMap
				.get(currentPlayer)
				+ spentTime);

		// select next player
		int i = players.indexOf(currentPlayer);
		if ((i + 1) >= players.size()) {
			i = 0;
		} else {
			i = i + 1;
		}
		currentPlayer = players.get(i);
		currentTimeStart = System.currentTimeMillis();
	}

	/**
	 * Checks for a pair and saves pair.
	 * 
	 * @return <code>true</code> if both cards are a match, <code>false</code>
	 *         otherwise.
	 */
	private boolean compareCurrentCards() {
		// guard illegal call of this method.
		if (this.currentCard1 == null || this.currentCard2 == null
				|| this.currentPlayer == null) {
			throw new IllegalStateException(
					"Cannot check for pairs if one card is null.");
		}

		// save uncovered pair to current user
		this.currentPlayer.addUncoveredPair(currentCard1, currentCard2);

		// check if we have a match
		if (currentCard1.isMatch(currentCard2)) {
			moveToUncoveredPair();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Moves the currently matching pair of cards from the local covered list to
	 * the uncovered list.
	 */
	private void moveToUncoveredPair() {
		MemoryCardPair currentPair = null;
		for (MemoryCardPair pair : coveredPairs) {
			if (pair.equals(currentCard1, currentCard2)) {
				currentPair = pair;
				break;
			}
		}
		coveredPairs.remove(currentPair);
		uncoveredPairs.add(currentPair);
	}

	/**
	 * Executes the uncover action for the specified <code>player</code> and the
	 * specified <code>card</code>.
	 * 
	 * @param player
	 *            to perform the uncover action.
	 * @param card
	 *            card to be uncovered.
	 */
	private void _uncover(MemoryPlayer player, MemoryCard card) {
		card.uncover();
	}

}
