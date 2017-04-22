package es.esy.ladysnake.miniprojet.main;

import java.util.Random;
import java.util.ArrayList;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * Classe décrivant un agent au service des machines
 */
public class Agent {
  private int lvl;
  public final int id;
  private static ArrayList<Agent> machineForces = new ArrayList<Agent>();
  private static final JList<Agent> jmachines;
  private static ListModel<Agent> model;

  static {
    model = new DefaultListModel<Agent>()
    {
      public boolean isCellEditable(int row)
      {
        return false;//This causes all cells to be not editable
      }
    };
    jmachines = new JList<Agent>(model);
  }

  /**
   * @return la représentation graphique de l'intégralité des agents
   */
  public static JList getPersonnelView() {
    return jmachines;
  }

  /**
   * Constructeur de la classe Agent
   * Crée un agent avec un niveau aléatoire et un id unique
   */
  public Agent() {
    Random rand = new Random();
    lvl = rand.nextInt(5);
    machineForces.add(this);
    this.id = machineForces.indexOf(this);
    ((DefaultListModel<Agent>)model).addElement(this);
  }

  /**
   * Permet de récuperer un agent grâce a son id
   * @param id l'id de l'agent a récuperer
   * @return l'agent ou null si aucun agent avec cet id n'existe
   */
  public static Agent get(int id) {
    if(id < machineForces.size())
      return machineForces.get(id);
    return null;
  }

  /**
   * @return La totalité des forces des machines
   */
  public static ArrayList<Agent> getAll() {
    return machineForces;
  }

  /**
   * @return une représentation de l'intégralité des forces des machines sous forme de String
   */
  public static String afficherForces() {
    String ret = "";
    for (Agent ag : machineForces)
      ret += ag + "\n";
    return ret;
  }

  /**
   * accesseur permettant de connaître le niveau d'un agent
   * @return le niveau de cet agent
   */
  public int getLvl() {
    return this.lvl;
  }

  /**
   * Divise par 2 le niveau de l'agent.
   */
  public void sap() {
    this.lvl /= 2;
  }

  @Override
  public String toString() {
    return "Agent #" + this.id + " niveau " + this.lvl;
  }
}
