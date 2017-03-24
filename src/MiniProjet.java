package es.esy.ladysnake.miniprojet;

import es.esy.ladysnake.gui.Interface;
import es.esy.ladysnake.gui.CommandListener;
import java.util.ArrayList;

public class MiniProjet implements CommandListener{
  public static Flotte flotte;
  public static String[]
    allCommandes = {"help", "clear", "exit", "print", "AfficherFlotte"},
    doc = {"1", "2", "3", "4"};
	
  public MiniProjet(){}

  public static void main(String[] args) {
	Interface.addCommandListener(new MiniProjet());
    flotte = new Flotte();
    Interface.openInterface("Matrix Simulator mk3", "Commander", "Entrer une commande");
    Libere m = new Libere("Neo", 'm', "Lieutenant");
    Vaisseau v = new Vaisseau("Nebuchadnezzar", "Transport");
    v.ajouterMembre(m);
    flotte.ajouterVaisseau(v);
  }

  @Override
  public void commandEntered(ArrayList<String> s) {
    switch(s.get(0)){
      case "help": for(String str : allCommandes) log(str); break;
      case "clear": Interface.clear(); break;
      case "exit": System.exit(0); break;
	  case "print": for(String args : s.subList(1, s.size())) log(args); break; 
      case "AfficherFlotte": log(flotte.toString()); break;
      default: log(s.get(0) + " : commande non reconnue. Tapez 'help' pour avoir une liste des commandes disponibles");
    }
  }

  public static void log(String s) {
    Interface.addLogLine(s);
    System.out.println(s);
  }
}
