package com.interview.flatexdegiro;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlutController {

    @RequestMapping(value = "/")
    String input() {
      return "<html>" 
          + "<body>" 
          + "Flut input data:"
          + "<br/>"
          + "<textArea id=\"inputText1\" name=\"inputText\" form=\"calcForm\"></textarea>"
          + "<br/>"
          + "<form action=\"/calc\" id = \"calcForm\" method=\"post\">"
          + "<input type=\"submit\" id=\"submit\" value=\"Submit\"/>"
          + "</form>"
          + "</body>" 
          + "</html>"; 
    }

    @PostMapping(
        value = "/calc",
        params = {"inputText"})
    String calc(@RequestParam String inputText) {
        return FlutTrade.calculateFlutCounts(inputText);
    }
}
