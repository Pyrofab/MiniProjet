package es.esy.ladysnake.miniprojet;

import es.esy.ladysnake.gui.Interface;

public class MiniProjet {
  public static Flotte flotte;

  public static void main(String[] args) {
    flotte = new Flotte();
    Interface.openInterface("Matrix Simulator mk3", "Commander", "Entrer une commande");
    Libere m = new Libere("Neo", 'm', "Lieutenant");
    Vaisseau v = new Vaisseau("Nebuchadnezzar", "Transport");
    v.ajouterMembre(m);
    flotte.ajouterVaisseau(v);
  }

  public static void commandEntered(String s) {
    if(s.contains("Afficher") && s.contains("flotte"))
      log(flotte.toString());
  }

  public static void log(String s) {
    Interface.addLogLine(s);
    System.out.println(s);
  }
}
