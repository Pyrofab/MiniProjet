package es.esy.ladysnake.miniprojet;

import es.esy.ladysnake.gui.Interface;
import java.util.ArrayList;

public class MiniProjet {
  public static Flotte flotte;
  public static String[]
    allCommandes = {"help", "clear", "exit", "AfficherFlotte"},
    doc = {"1", "2", "3", "4"};


  public static void main(String[] args) {
    flotte = new Flotte();
    Interface.openInterface("Matrix Simulator mk3", "Commander", "Entrer une commande");
    Libere m = new Libere("Neo", 'm', "Lieutenant");
    Vaisseau v = new Vaisseau("Nebuchadnezzar", "Transport");
    v.ajouterMembre(m);
    flotte.ajouterVaisseau(v);
  }

  public static void commandEntered(String[] s) {
    switch(s[0]){
      case "help": log("lol"); break;
      case "clear": log("mdr"); break;
      case "exit": log("wesh"); break;
      case "AfficherFlotte": log(flotte.toString());
      default: log("Commande non reconnue");
    }
  }

  public static void log(String s) {
    Interface.addLogLine(s);
    System.out.println(s);
  }
}
