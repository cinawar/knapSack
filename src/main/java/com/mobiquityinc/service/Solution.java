package com.mobiquityinc.service;

import java.util.List;

import com.mobiquityinc.models.Item;

public interface Solution {
	public String solve(List<Item> itemsList, int maxWeight, int capacity);
}
