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

/**
 * Representing a card in a {@link MemoryGame}.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class MemoryCard {

	/**
	 * Enumeration describing the possible state of a {@link MemoryGame}.
	 * 
	 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
	 * 
	 */
	public enum CardState {
		COVERED, UNCOVERED
	}

	/**
	 * The current {@link CardState} of this card.
	 */
	private CardState currentState = CardState.COVERED;

	/**
	 * The id of this card.
	 */
	private String id;

	/**
	 * Counter counting the times this card has been uncovered.
	 */
	private int uncoverCount = 0;

	/**
	 * the column index of this card.
	 */
	private int column = -1;

	/**
	 * the row index of this card.
	 */
	private int row = -1;

	/**
	 * Constructor setting the id of the card.
	 * 
	 * @param id
	 *            to set.
	 */
	protected MemoryCard(String id) {
		this.id = id;
	}

	/**
	 * Returns the current {@link CardState}.
	 * 
	 * @return the current state.
	 */
	public CardState getCurrentState() {
		return currentState;
	}

	/**
	 * Returns the id of this card.
	 * 
	 * @return the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the counter counting the times this card has been uncovered.
	 * 
	 * @return the uncover count.
	 */
	public int getUncoverCount() {
		return uncoverCount;
	}

	/**
	 * Uncovers this card.
	 */
	protected void uncover() {
		this.uncoverCount++;
		this.currentState = CardState.UNCOVERED;
	}

	/**
	 * Covers this card.
	 */
	protected void cover() {
		this.currentState = CardState.COVERED;
	}

	/**
	 * Specifies whether this card is a match to the specified <code>card</code>
	 * .
	 * 
	 * @param card
	 *            to check if this card is a match.
	 * @return <code>true</code> if this card is a match, <code>false</code>
	 *         otherwise.
	 */
	public boolean isMatch(MemoryCard card) {
		return this.getId().equals(card.getId());
	}

	/**
	 * Returns the column index if currently set. If the index is not set
	 * <code>-1</code> is returned.
	 * 
	 * @return the column index of this card in a table.
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * @param column
	 *            the column to set
	 */
	protected void setColumn(int column) {
		this.column = column;
	}

	/**
	 * Returns the row index if currently set. If the index is not set
	 * <code>-1</code> is returned.
	 * 
	 * @return the row index of this card in a table.
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row
	 *            the row to set
	 */
	protected void setRow(int row) {
		this.row = row;
	}

}
