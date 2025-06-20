package com.interview.flatexdegiro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.TreeSet;

public class FlutTrade {
  
  public static String calculateFlutCounts(String input) {
    StringBuilder sb = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new StringReader(input));) {
      try {
        while (true) {
          int schuurCount = Integer.parseInt(reader.readLine());
          if (schuurCount == 0) {
            break;
          }

          ArrayList<SchuurPile> schuurs = fetchSchuurs(reader, schuurCount);
          int maxProfit = getMaxProfit(schuurs);
          TreeSet<Integer> allFlutCounts = getCombinedSchuurCounts(schuurs);
          printResults(sb, schuurs, maxProfit, allFlutCounts);
        }
        
      } catch (NumberFormatException nfe) {
        return ("Invalid number format");
      }
    } catch (Exception e) {
      e.printStackTrace();
      return "Something went wrong";
    }
    return sb.toString();
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
  
  public static void printResults(StringBuilder sb, ArrayList<SchuurPile> schuurs, int maxProfit, TreeSet<Integer> allFlutCounts) {
    sb.append("schuurs ").append(schuurs.size()).append("<br/>");
    sb.append("Maximum profit is ").append(maxProfit).append(".").append("<br/>");
    sb.append("Number of fluts to buy:");
    allFlutCounts.forEach(count -> sb.append(" ").append(count));
    sb.append("<br/>");
  }
}
