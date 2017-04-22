package es.esy.ladysnake.miniprojet.main;

import java.util.Random;
import java.awt.Point;
import javax.swing.JTable;
import javax.swing.table.*;

/**
 * La matrice.
 * Note : Il n'est pas possible de créer des instances de cette classe.
 */
public class Matrice {
  public static final int MAX_X = 10, MAX_Y = 10;
  private static Object[][] matrice = new Object[MAX_X][MAX_Y];
  private static TableModel model;
  private static JTable jmatrice;

  static {

    Object[] columnNames = new Integer[MAX_X];
    for (int i = 0; i < MAX_X; i++)
      columnNames[i] = i;

    model = new DefaultTableModel(matrice, columnNames)
    {
      public boolean isCellEditable(int row, int column)
      {
        return false;
      }

      public Object getValueAt(int row, int col) {
        if(matrice[row][col] instanceof Libere) {
          Libere l = ((Libere)matrice[row][col]);
          return ((l.estInfecte() ? "m" : "M") + " (" + l.getNom() + ")");
        }
        if(matrice[row][col] instanceof Agent) {
          Agent a = ((Agent)matrice[row][col]);
          return "A" + a.getLvl();
        }
        return "";
      }
    };
    jmatrice = new JTable(model);
  }
  /**
   * Crée une représentation de la matrice
   * @return la JTable représentant la matrice
   */
  public static JTable getMatrixView() {
    return jmatrice;
  }

  /**
   * Indique si la matrice est pleine
   * @return vrai si la matrix est pleine
   */
  public static boolean isFull() {
    for (int i = 0; i < MAX_X; i++)
      for (int j = 0; j < MAX_Y; j++)
        if (matrice[i][j] == null)
          return false;
    return true;
  }

  /**
   * Permet d'obtenir le contenu de la matrice aux coordonnées indiquées
   * @param x l'abcisse de la personne
   * @param y l'ordonnée de la personne
   * @return la personne aux coordonnées indiquées
   */
  public static Object get(int x, int y) {
    return matrice[x][y];
  }

  /**
   * Permet d'insérer un agent ou un membre liberé dans la matrice
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
      //jmatrice.setValueAt(p, x, y);
      return true;
    }
    return false;
  }

  /**
   * Permet de sortir un agent ou un membre libéré de la matrice
   * @param p la personne à exfiltrer
   * @return true si l'arrachage a été effectué avec succès
   */
  public static boolean exfiltrer(Object p) {
    if(matrice[getPos(p).x][getPos(p).y] == null)
      return false;
    matrice[getPos(p).x][getPos(p).y] = null;
    //jmatrice.setValueAt(null, getPos(p).x, getPos(p).y);
    return true;
  }

  /**
   * calcule la distance entre un membre et un agent
   * @param m le membre libéré
   * @param a l'agent
   * @return la distance entre les deux
   */
  public static double distanceAgent(Libere m, Agent a) {
    return Math.sqrt(Math.pow((getPos(m).x - getPos(a).x),2) + Math.pow((getPos(m).y - getPos(a).y), 2));
  }

  /**
   * détermine l'agent le plus proche du membre libéré
   * @param m le membre libéré pour lequel la vérification est effectuée
   */
  public static Agent agentPlusProche(Libere m) {
    Agent plusProche = null;
    for (int x = 0; x < matrice.length; x++)
      for (int y = 0; y < matrice.length; y++)
        if(matrice[x][y] instanceof Agent)
          if(plusProche == null || distanceAgent(m, (Agent)matrice[x][y]) < distanceAgent(m, plusProche))
            plusProche = (Agent)matrice[x][y];
    // System.out.println(plusProche);
    return plusProche;
  }

  /**
   * Test si le libéré passé en paramètre est infecté par un agent proche
   * @param l le membre libéré à tester
   * @return si l'infection a eu lieu
   */
  public static boolean testInfection(Libere l) {
    if(agentPlusProche(l) == null)
      return false;
    // System.out.println((Matrice.agentPlusProche(l).getLvl() / distanceAgent(l, agentPlusProche(l))) > l.nbTransferts());
    if ((agentPlusProche(l).getLvl() / distanceAgent(l, agentPlusProche(l))) > l.getNbTransferts())
      return true;
    agentPlusProche(l).sap();
    return false;
  }

  /**
   * Permet de connaître la position d'une personne dans la matrice
   * @param ag la personne à rechercher
   * @return les coordonnées de la personne sous forme de point, ou null si la personne n'est pas présente.
   */
  public static Point getPos (Object ag) {
    for (int i = 0; i < MAX_X; i++)
      for (int j = 0; j < MAX_Y; j++)
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
