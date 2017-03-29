package es.esy.ladysnake.miniprojet.main;

import java.util.Random;
import java.awt.Point;

public class Matrice {
  private Personne[][] matrice;

  public Matrice() {
    this.matrice = new Personne[10][10];
  }

  public boolean isFull() {
    return false;
  }

  public Personne get(int x, int y) {
    return matrice[x][y];
  }

  public boolean infiltrer(Infiltrable ag) {
    if (matrice[ag.getPos().x][ag.getPos().y] == null) {
      matrice[ag.getPos().x][ag.getPos().y] = ag;
      return true;
    }
    return false;
  }

  public String toString() {
    String res = " ________________________________________________\n|   |    |    |    |    |    |    |    |    |    |\n|   | 1  | 2  | 3  | 4  | 5  | 6  | 7  | 8  | 9  |\n|___|____|____|____|____|____|____|____|____|____|\n|   |    |    |    |    |    |    |    |    |    |\n";
    for (int i = 1; i <= 9; i++) {
      if (i < 9) {
        res += "| "+i+" | xx | xx | xx | xx | xx | xx | xx | xx | xx |\n|___|____|____|____|____|____|____|____|____|____|\n|   |    |    |    |    |    |    |    |    |    |\n";
      } else {
        res += "| "+i+" | xx | xx | xx | xx | xx | xx | xx | xx | xx |\n|___|____|____|____|____|____|____|____|____|____|\n";
      }
    }
    return res;
  }
}


/*
 ________________________________________________\n
|   |    |    |    |    |    |    |    |    |    |\n
|   | 1  | 2  | 3  | 4  | 5  | 6  | 7  | 8  | 9  |\n
|___|____|____|____|____|____|____|____|____|____|\n
|   |    |    |    |    |    |    |    |    |    |\n
| i | x  | x  | x  | x  | x  | x  | x  | x  | x  |\n
|___|____|____|____|____|____|____|____|____|____|\n
|   |    |    |    |    |    |    |    |    |    |\n
| i | x  | x  | x  | x  | x  | x  | x  | x  | x  |\n
|___|____|____|____|____|____|____|____|____|____|\n

*/
