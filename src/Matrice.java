package es.esy.ladysnake.miniprojet.main;

import java.util.Random;
import java.awt.Point;

/**
 * La matrice
 * Note : Il n'est pas possible de créer des instances de cette classe
 */
public class Matrice {
  private static Object[][] matrice = new Object[10][10];

  /**
   * Indique si la matrice est pleine
   * @return vrai si la matrix est pleine
   */
  public static boolean isFull() {
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
  public static Object get(int x, int y) {
    return matrice[x][y];
  }

  /**
   * Permet d'insérer un agent ou un membre libéré dans la matrice
   * @param p la personne à infiltrer
   * @return true si l'insertion a été effectuée avec succès
   */

  public static boolean infiltrer(Object p) throws IllegalArgumentException {
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

  public static boolean exfiltrer(Object p) {
    if(matrice[getPos(p).x][getPos(p).y] == null)
      return false;
    matrice[getPos(p).x][getPos(p).y] = null;
    return true;
  }




  /**
   * Permet de connaître la position d'une personne dans la matrice
   * @param ag la personne à rechercher
   * @return les coordonnees de la personne sous forme de point, ou null si la personne n'est pas présente.
   */
  public static Point getPos (Object ag) {
    for (int i = 0; i < 10; i++)
      for (int j = 0; j < 10; j++)
        if (matrice[i][j] == ag)
          return new Point(i, j);
    return null;
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
