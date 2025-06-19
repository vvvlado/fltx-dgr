package com.interview.flatexdegiro;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeSet;

public class FlutTrade {
  
 
  public static void main(String args[]) {

    try (FileInputStream is = new FileInputStream("input.txt")) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(is));
      try {
        while (true) {
          int schuurCount = Integer.parseInt(reader.readLine());
          if (schuurCount == 0) {
            break;
          }

          ArrayList<SchuurPile> schuurs = fetchSchuurs(reader, schuurCount);
          int maxProfit = getMaxProfit(schuurs);
          TreeSet<Integer> allFlutCounts = getCombinedSchuurCounts(schuurs);
          
          printResults(schuurs, maxProfit, allFlutCounts);
        }
        
      } catch (NumberFormatException nfe) {
        System.out.println("Invalid number format");
      }
    } catch (Exception e) {
      System.out.println("Something went wrong");
      e.printStackTrace();
    }
  }
  
  public static TreeSet<Integer> getCombinedSchuurCounts(ArrayList<SchuurPile> schuurs) {
    TreeSet<Integer> allFlutCounts = new TreeSet<Integer>();
    allFlutCounts.addAll(schuurs.get(0).getMaxProfitCounts());
    for (int i = 1; i < schuurs.size(); i++) {
      TreeSet<Integer> nextAllFlutCounts = new TreeSet<>();
      SchuurPile pile = schuurs.get(i);
      allFlutCounts.stream().forEach(j -> pile.getMaxProfitCounts().forEach(k -> nextAllFlutCounts.add(j + k)));
      allFlutCounts = nextAllFlutCounts;
    }
    return allFlutCounts;
  }
  
  public static ArrayList<SchuurPile> fetchSchuurs(BufferedReader reader, int schuurCount) throws IOException {
    ArrayList<SchuurPile> schuurs = new ArrayList<SchuurPile>();
    for (int i = 0; i < schuurCount; i++) {
      schuurs.add(new SchuurPile(reader.readLine()));
    }
    return schuurs;
  }

  public static int getMaxProfit(ArrayList<SchuurPile> schuurs) {
    int maxProfit = 0;
    for (SchuurPile pile : schuurs) {
      maxProfit += pile.getMaxProfit();
    }
    return maxProfit;
  }
  
  public static void printResults(ArrayList<SchuurPile> schuurs, int maxProfit, TreeSet<Integer> allFlutCounts) {
    System.out.println("schuurs " + schuurs.size());
    System.out.println("Maximum profit is " + maxProfit + ".");
    System.out.print("Number of fluts to buy:");
    allFlutCounts.forEach(count -> System.out.print(" " + count));
    System.out.println();
  }

}
