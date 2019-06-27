package com.mobiquityinc.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

import org.apache.commons.math3.util.CombinatoricsUtils;

import com.mobiquityinc.models.Item;

public class SolutionImpl implements Solution {

	/**
	 * @param itemsList->
	 *            file line * @param maxWeight-> all list from file
	 * @return String of index list with comma seperated or -
	 */
	public String solve(List<Item> itemsList, int maxWeight, int capacity) {

		Map<String, Double> calculatedList = new HashMap<String, Double>();
		Map<String, Integer> pricemap = new HashMap<String, Integer>();

		for (int i = 1; i < itemsList.size(); i++) {
			ArrayList<int[]> combinationList = generate(itemsList.size(), i);
			for (int k = 0; k < combinationList.size(); k++) {
				int[] element = combinationList.get(k);
				Double totalWeight = 0.0;
				Integer totalPrice = 0;
				for (int e = 0; e < element.length; e++) {
					totalWeight = totalWeight + itemsList.get(element[e]).getWeight();
					totalPrice = totalPrice + itemsList.get(element[e]).getPrice();
				}
				if (totalWeight <= maxWeight && totalWeight <= capacity) {
					pricemap.put(Arrays.toString(combinationList.get(k)), (totalPrice));
					calculatedList.put(Arrays.toString(combinationList.get(k)), totalWeight + totalPrice);
				}
			}
		}

		try {
			Entry<String, Double> maxEntryWeight = calculatedList.entrySet().stream().max(Map.Entry.comparingByValue())
					.get();
			Entry<String, Integer> maxEntryPrice = pricemap.entrySet().stream().max(Map.Entry.comparingByValue()).get();

			String[] arrOfStr = null;

			if (maxEntryWeight.getKey().equals(maxEntryPrice.getKey()))
				arrOfStr = maxEntryWeight.getKey().replace("[", "").replace("]", "").split(",", 2);
			else
				arrOfStr = maxEntryPrice.getKey().replace("[", "").replace("]", "").split(",", 2);

			return commaSeperate(arrOfStr);
		} catch (NoSuchElementException e) {
			return "-";
		}

	}

	/**
	 * @param n->
	 *            number of items in list , r combination strength
	 * @return ArrayList of All Combinations
	 */
	private static ArrayList<int[]> generate(int n, int r) {
		Iterator<int[]> iterator = CombinatoricsUtils.combinationsIterator(n, r);
		ArrayList<int[]> numbers = new ArrayList<int[]>();
		while (iterator.hasNext()) {
			final int[] combination = iterator.next();
			numbers.addAll(new ArrayList<int[]>(Arrays.asList(combination)));
			// System.out.println(Arrays.asList(combination));
		}
		return numbers;
	}
	/**
	 * @param arrOfStr-> index numbers of founded items ex [1,2]
	 * @return String with comma
	 */
	private String commaSeperate(String[] arrOfStr) {
		int appendComma = 0;
		StringBuilder returnList = new StringBuilder();

		for (String a : arrOfStr) {
			Integer valIndex = (Integer.valueOf(a.trim()) + 1);
			returnList.append(valIndex.toString());
			appendComma++;
			if (appendComma < arrOfStr.length)
				returnList.append(",");
		}
		return returnList.toString();
	}
}
