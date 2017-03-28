package es.esy.ladysnake.miniprojet;

import java.awt.Point;
import java.util.Random;

public class Agent extends Personne {
  private Point pos;
  private int lvl;

  public Agent(String name, char sexe, String grade) {
    super(name, sexe, grade);
    Random rand = new Random();
    int x = rand.nextInt(9), y = rand.nextInt(9);
    if (!MiniProjet.matrix.isFull()){
      while (MiniProjet.matrix.get(x, y) != null) {
        x = rand.nextInt(9);
        y = rand.nextInt(9);
      }
      pos = new Point(x, y);
    }
    lvl = rand.nextInt(5);
  }
}
