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
import java.util.Collections;
import java.util.List;

/**
 * Class representing a player playing in a {@link MemoryGame}.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class MemoryPlayer {

	/**
	 * The name of this user.
	 */
	private String name;

	/**
	 * Pairs this player has uncovered.
	 */
	private List<MemoryCardPair> uncoveredPairs = new ArrayList<MemoryCardPair>();

	/**
	 * Initializes a {@link MemoryPlayer} with the specified <code>name</code>.
	 * 
	 * @param name
	 *            to set.
	 */
	public MemoryPlayer(String name) {
		super();
		this.name = name;
	}

	/**
	 * Returns the name of this player.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the pairs uncovered by this player.
	 * 
	 * @return the uncovered pairs
	 */
	public List<MemoryCardPair> getUncoveredPairs() {
		return Collections.unmodifiableList(uncoveredPairs);
	}

	/**
	 * Returns the count of pairs uncovered by this player.
	 * 
	 * @return the count of pairs.
	 */
	public int getAllUncoveredPairsCount() {
		return uncoveredPairs.size();
	}

	/**
	 * Returns the count of matching pairs uncovered by this player.
	 * 
	 * @return the count of matching pairs.
	 */
	public int getUncoveredMatchingPairsCount() {
		int count = 0;
		for (MemoryCardPair pair : uncoveredPairs) {
			if (pair.isMatch()) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Returns the count of unmatching pairs uncovered by this player.
	 * 
	 * @return the count of unmatching pairs.
	 */
	public int getUncoveredUnmatchingPairsCount() {
		int count = 0;
		for (MemoryCardPair pair : uncoveredPairs) {
			if (!pair.isMatch()) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Adds a pair this player has uncovered.
	 * 
	 * @param card1
	 *            first card of the pair.
	 * @param card2
	 *            second card of the pair.
	 */
	protected void addUncoveredPair(MemoryCard card1, MemoryCard card2) {
		this.uncoveredPairs.add(new MemoryCardPair(card1, card2));
	}

}
