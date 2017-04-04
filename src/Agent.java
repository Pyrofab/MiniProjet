package es.esy.ladysnake.miniprojet.main;

import java.util.Random;
import java.util.ArrayList;

public class Agent {
  private int lvl;
  public final int id;
  private static ArrayList<Agent> machineForces = new ArrayList<Agent>();

  public static Agent get(int id) {
    if(id < machineForces.size())
      return machineForces.get(id);
    return null;
  }

  public static String afficherForces() {
    String ret = "";
    for (Agent ag : machineForces)
      ret += ag + "\n";
    return ret;
  }

  public Agent() {
    Random rand = new Random();
    lvl = rand.nextInt(5);
    machineForces.add(this);
    this.id = machineForces.indexOf(this);
  }

  public int getLvl() {
    return this.lvl;
  }

  public String toString() {
    return "Agent #" + this.id + " niveau " + this.lvl;
  }
}
