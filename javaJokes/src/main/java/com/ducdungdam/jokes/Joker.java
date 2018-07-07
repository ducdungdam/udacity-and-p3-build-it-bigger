package com.ducdungdam.jokes;

import java.util.Random;

public class Joker {
  String[] jokeList = {
      "The best joke is that this joke is coming from the Joker. Funny uh?",
      "I'm such a bad joke teller. So just imagine some joke here. Joke enough?",
      "Once upon a time [fill in your joke]",
      "One day I met somebody, who was really... [end of joke]"
  };

  public String getJoke() {

    return jokeList[new Random().nextInt(jokeList.length)];
  }
}
