package es.esy.ladysnake.miniprojet.main;

import java.util.Random;
import java.awt.Point;

public class Matrice {
  private Personne[][] matrice;

  public Matrice() {
    this.matrice = new Personne[10][10];
  }

  /**
   * Indique si la matrice est pleine
   * @return vrai si la matrix est pleine
   */
  public boolean isFull() {
    for (int i = 0; i < 10; i++)
      for (int j = 0; j < 10; j++)
        if (matrice[i][j] == null)
          return false;
    return true;
  }

  /**
   * Permet d'obtenir le contenu de la matrice aux coordonnees indiquees
   * @param x l'abcisse de la personne
   * @param y l'ordonnée de la personne
   * @return la personne aux coordonnees indiquees
   */
  public Personne get(int x, int y) {
    return matrice[x][y];
  }

  /**
   * Permet d'insérer un agent ou un membre libéré dans la matrice
   * @param ag la personne à infiltrer
   * @return true si l'insertion a été effectuée avec succès
   */
  public boolean infiltrer(Personne p) throws IllegalArgumentException {
    if(!(p instanceof Agent || p instanceof Libere))
      throw new IllegalArgumentException();

    Random rand = new Random();
    int x, y;
    if (!isFull()) {
      do {
        x = rand.nextInt(9);
        y = rand.nextInt(9);
      } while (matrice[x][y] != null);
      matrice[x][y] = p;
      return true;
    }
    return false;
  }

  public void exfiltrer(Personne p) {
    matrice[getPos(p).x][getPos(p).y] = null;
  }




  /**
   * Permet de connaître la position d'une personne dans la matrice
   * @param ag la personne à rechercher
   * @return les coordonnees de la personne sous forme de point, ou null si la personne n'est pas présente.
   */
  public Point getPos (Personne ag) {
    for (int i = 0; i < 10; i++)
      for (int j = 0; j < 10; j++)
        if (matrice[i][j] == ag)
          return new Point(i, j);
    return null;
  }

  /**
   * renvoie une représentation d'une matrice sous forme de String
   * @return la représentation de cette matrice
   */
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
