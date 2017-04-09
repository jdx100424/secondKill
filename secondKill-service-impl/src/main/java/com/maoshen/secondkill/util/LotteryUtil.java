package com.maoshen.secondkill.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class LotteryUtil<E> {
	private Random random = new Random(); 
		
	private static final int BASE = 10000000;

	private List<LotteryItem> lotteryItems;

	private LotteryUtil(){
		lotteryItems=new ArrayList<>();
	}

	public static <E> LotteryUtil<E> newInstance() {
		return new LotteryUtil<E>();
	}

	/**
	 * 
	 * @param item
	 * @param probability
	 */
	public LotteryUtil<E> add(E item, double probability) {
		if (probability > 1) {
			probability = 1;
		}
		if (probability < 0) {
			probability = 0;
		}
		this.lotteryItems.add(new LotteryItem(item, probability));
		return this;
	}

	public E lottery() {
		if (lotteryItems.size() <= 0) {
			return null;
		}
		int i = random.nextInt(lotteryItems.size());
		LotteryUtil<E>.LotteryItem lotteryItem = lotteryItems.get(i);
		if (isHit(lotteryItem)) {
			return lotteryItem.getItem();
		}

		// Collections.shuffle(lotteryItems);
		//
		// for(LotteryItem i:lotteryItems){
		// if(isHit(i)){
		// return i.getItem();
		// }
		// }

		return null;
	}

	private boolean isHit(LotteryItem lotteryItem) {
		double probability = lotteryItem.getProbability();
		int nextInt = random.nextInt(BASE);
		if (nextInt < probability * BASE) {
			return true;
		}
		return false;
	}

	private class LotteryItem {
		private E item;

		private double probability;

		public LotteryItem(E item, double probability) {
			this.item = item;
			this.probability = probability;
		}

		public E getItem() {
			return item;
		}

		public double getProbability() {
			return probability;
		}
	}

	public static void main(String[] args) {
		Map<String, Integer> m = new HashMap<>();
		for (int i = 0; i < 10000; i++) {
			String lottery = LotteryUtil.<String>newInstance().add("手机", 0.8).add("洗衣机", 0.1).add("红包4", 0.2)
					.add("红包5", 0.2).lottery();
			if (lottery != null) {
				Integer integer = m.get(lottery);
				if (integer != null) {
					m.put(lottery, ++integer);
				} else {
					m.put(lottery, 1);
				}
			}
		}
		int sum = 0;
		for (Map.Entry<String, Integer> e : m.entrySet()) {
			System.out.println(e.getKey() + ":" + e.getValue());
			sum += e.getValue();
		}
		System.out.println("sum:" + sum);
	}
}
