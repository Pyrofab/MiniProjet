package es.esy.ladysnake.miniprojet.main;

import java.awt.Point;
import java.util.Random;

public class Infiltrable extends Personne {
  private Point pos;

  public Infiltrable(String name, char sexe, String grade) {
    super(name, sexe, grade);
    Random rand = new Random();
    int x = rand.nextInt(9), y = rand.nextInt(9);
    if (!MiniProjet.matrix.isFull()){
      do {
        x = rand.nextInt(9);
        y = rand.nextInt(9);
      } while (!MiniProjet.matrix.infiltrer(this));
      pos = new Point(x, y);
    }
  }

  public Point getPos() {
    return this.pos;
  }

  public String toString() {
    return super.toString();
  }
}
