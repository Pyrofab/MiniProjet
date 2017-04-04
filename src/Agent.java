package es.esy.ladysnake.miniprojet.main;

import java.util.Random;
import java.util.ArrayList;

/**
 * Classe décrivant un agent au service des machines
 */
public class Agent {
  private int lvl;
  public final int id;
  private static ArrayList<Agent> machineForces = new ArrayList<Agent>();

  /**
   * Constructeur de la classe Agent
   * Crée un agent avec un niveau aléatoire et un id unique
   */
  public Agent() {
    Random rand = new Random();
    lvl = rand.nextInt(5);
    machineForces.add(this);
    this.id = machineForces.indexOf(this);
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
   * @return une représentation de cet agent sous forme de String
   */
  public String toString() {
    return "Agent #" + this.id + " niveau " + this.lvl;
  }
}
