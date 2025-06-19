package com.interview.flatexdegiro;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class SchuurPile {
  
  public static final int RETAIL_PRICE = 10;

  private ArrayList<Integer> fluts = new ArrayList<>();
  private ArrayList<Integer> maxProfitCounts = new ArrayList<>();
  private int maxProfit = 0;
  private boolean calculated = false;
  
  public SchuurPile(String rawPile) {
    StringTokenizer flutParser = new StringTokenizer(rawPile);
    while (flutParser.hasMoreTokens()) {
      fluts.add(Integer.parseInt(flutParser.nextToken()));
    }
  }
  
  public int getMaxProfit() {
    if (!calculated) {
      calculate();
    }
    return maxProfit;
  }
  
  public List<Integer> getMaxProfitCounts() {
    if (!calculated) {
      calculate();
    }
    return maxProfitCounts;
  }
  
  private void calculate() {
    int profitSoFar = 0;
    for (int i = 0; i < fluts.size(); i++) {
      profitSoFar = profitSoFar - fluts.get(i) + RETAIL_PRICE;
      if (profitSoFar == maxProfit) {
        maxProfitCounts.add(i + 1);
      } 
      if (profitSoFar > maxProfit) {
        maxProfitCounts.clear();
        maxProfit = profitSoFar;
        maxProfitCounts.add(i + 1);
      }
    }
  }

}
