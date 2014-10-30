package com.example.flashcard_reminder;

import java.util.ArrayList;

import android.app.Application;

public class AppContext extends Application {

	private ArrayList<BundleOfCards> thousand;
	
	private ArrayList<ArrayList<BundleOfCards>> hundred;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		thousand = new ArrayList<BundleOfCards>();
		
		thousand.add(new BundleOfCards(0, "1-ая тысяча слов", new int[]{0, 0, 0, 0, 0, 0})); 
		thousand.add(new BundleOfCards(1, "2-ая тысяча слов", new int[]{0, 0, 0, 0, 0, 0})); 
		thousand.add(new BundleOfCards(2, "3-ая тысяча слов", new int[]{0, 0, 0, 0, 0, 0})); 
		
		hundred = new ArrayList<ArrayList<BundleOfCards>>();
		
		ArrayList<BundleOfCards> l1 = new ArrayList<BundleOfCards>();
		l1.add(new BundleOfCards(0, "01-ая сотня слов", new int[]{0, 0, 0, 0, 0, 0})); 
		l1.add(new BundleOfCards(1, "02-ая сотня слов", new int[]{0, 0, 0, 0, 0, 0})); 
		l1.add(new BundleOfCards(2, "03-ья сотня слов", new int[]{0, 0, 0, 0, 0, 0})); 
		l1.add(new BundleOfCards(3, "04-ая сотня слов", new int[]{0, 0, 0, 0, 0, 0})); 
		l1.add(new BundleOfCards(4, "05-ая сотня слов", new int[]{0, 0, 0, 0, 0, 0})); 
		l1.add(new BundleOfCards(5, "06-ая сотня слов", new int[]{0, 0, 0, 0, 0, 0})); 
		l1.add(new BundleOfCards(6, "07-ая сотня слов", new int[]{0, 0, 0, 0, 0, 0})); 
		l1.add(new BundleOfCards(7, "08-ая сотня слов", new int[]{0, 0, 0, 0, 0, 0})); 
		l1.add(new BundleOfCards(8, "09-ая сотня слов", new int[]{0, 0, 0, 0, 0, 0})); 
		l1.add(new BundleOfCards(9, "10-ая сотня слов", new int[]{0, 0, 0, 0, 0, 0}));
		
		ArrayList<BundleOfCards> l2 = new ArrayList<BundleOfCards>();
		l2.add(new BundleOfCards(10, "11-ая сотня слов", new int[]{0, 0, 0, 0, 0, 0})); 
		l2.add(new BundleOfCards(11, "12-ая сотня слов", new int[]{0, 0, 0, 0, 0, 0})); 
		l2.add(new BundleOfCards(12, "13-ая сотня слов", new int[]{0, 0, 0, 0, 0, 0})); 
		l2.add(new BundleOfCards(13, "14-ая сотня слов", new int[]{0, 0, 0, 0, 0, 0})); 
		l2.add(new BundleOfCards(14, "15-ая сотня слов", new int[]{0, 0, 0, 0, 0, 0})); 
		l2.add(new BundleOfCards(15, "16-ая сотня слов", new int[]{0, 0, 0, 0, 0, 0})); 
		l2.add(new BundleOfCards(16, "17-ая сотня слов", new int[]{0, 0, 0, 0, 0, 0})); 
		l2.add(new BundleOfCards(17, "18-ая сотня слов", new int[]{0, 0, 0, 0, 0, 0})); 
		l2.add(new BundleOfCards(18, "19-ая сотня слов", new int[]{0, 0, 0, 0, 0, 0})); 
		l2.add(new BundleOfCards(19, "20-ая сотня слов", new int[]{0, 0, 0, 0, 0, 0}));
		
		ArrayList<BundleOfCards> l3 = new ArrayList<BundleOfCards>();
		l3.add(new BundleOfCards(20, "21-ая сотня слов", new int[]{0, 0, 0, 0, 0, 0})); 
		l3.add(new BundleOfCards(21, "22-ая сотня слов", new int[]{0, 0, 0, 0, 0, 0})); 
		l3.add(new BundleOfCards(22, "23-ья сотня слов", new int[]{0, 0, 0, 0, 0, 0})); 
		l3.add(new BundleOfCards(23, "24-ая сотня слов", new int[]{0, 0, 0, 0, 0, 0})); 
		l3.add(new BundleOfCards(24, "25-ая сотня слов", new int[]{0, 0, 0, 0, 0, 0})); 
		l3.add(new BundleOfCards(25, "26-ая сотня слов", new int[]{0, 0, 0, 0, 0, 0})); 
		l3.add(new BundleOfCards(26, "27-ая сотня слов", new int[]{0, 0, 0, 0, 0, 0})); 
		l3.add(new BundleOfCards(27, "28-ая сотня слов", new int[]{0, 0, 0, 0, 0, 0})); 
		l3.add(new BundleOfCards(28, "29-ая сотня слов", new int[]{0, 0, 0, 0, 0, 0})); 
		l3.add(new BundleOfCards(29, "30-ая сотня слов", new int[]{0, 0, 0, 0, 0, 0}));
		
		hundred.add(l1);
		hundred.add(l2);
		hundred.add(l3);
	}

	public ArrayList<BundleOfCards> getThousand() {
		return thousand;
	}
	
	public ArrayList<BundleOfCards> getHundred(int index) {
		return hundred.get(index);
	}
}
