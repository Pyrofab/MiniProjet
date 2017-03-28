package es.esy.ladysnake.miniprojet;

import java.util.Random;

public class Matrice {
  private Libere[][] matrice;

  public Matrice() {
    this.matrice = new Libere[10][10];
  }

  public void infiltrerAgent(Libere ag) {
    Random rand = new Random();
    int x, y;
    for (int i = 0; ; i++) {
      if (matrice[x=rand.nextInt(9)][y=rand.nextInt(9)] == null || i > 200) break;
    }
    matrice[x][y] = ag;

  }
}
