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
 * Representing a pair of {@link MemoryCard}s in a {@link MemoryGame}.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class MemoryCardPair {

	/**
	 * First card.
	 */
	private MemoryCard card1;

	/**
	 * Second card.
	 */
	private MemoryCard card2;

	/**
	 * Specifies whether this pair is a valid match.
	 */
	private boolean isMatch = false;

	/**
	 * Constructor providing both cards to create this pair of cards.
	 * 
	 * @param card1
	 *            first card.
	 * @param card2
	 *            second card.
	 */
	protected MemoryCardPair(MemoryCard card1, MemoryCard card2) {
		super();
		this.card1 = card1;
		this.card2 = card2;
		isMatch = card1.isMatch(card2);
	}

	/**
	 * Specifies whether this pair of cards if a valid match.
	 * 
	 * @return <code>true</code> if this pair is a match, <code>false</code>
	 *         otherwise.
	 */
	public boolean isMatch() {
		return isMatch;
	}

	/**
	 * The first card this pair consists of.
	 * 
	 * @return the card1
	 */
	public MemoryCard getCard1() {
		return card1;
	}

	/**
	 * The second card this pair consists of.
	 * 
	 * @return the card2
	 */
	public MemoryCard getCard2() {
		return card2;
	}

	/**
	 * Specified whether this pair matches the specified cards.
	 * 
	 * @param card1
	 *            card 1.
	 * @param card2
	 *            card 2.
	 * @return <code>true</code> if the pair is equal to the specified cards,
	 *         <code>false</code> otherwise.
	 */
	public boolean equals(MemoryCard card1, MemoryCard card2) {
		if ((card1.equals(this.card1) && card2.equals(this.card2))
				|| (card2.equals(this.card1) && card1.equals(this.card2))) {
			return true;
		} else {
			return false;
		}
	}
}
