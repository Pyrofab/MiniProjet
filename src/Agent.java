package es.esy.ladysnake.miniprojet.main;

import java.util.Random;

public class Agent extends Infiltrable {
  private int lvl;

  public Agent(String name, char sexe, String grade) {
    super(name, sexe, grade);
    Random rand = new Random();
    lvl = rand.nextInt(5);
  }
}
