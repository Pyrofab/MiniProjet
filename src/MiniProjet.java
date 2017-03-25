package es.esy.ladysnake.miniprojet;

import es.esy.ladysnake.gui.Interface;
import es.esy.ladysnake.gui.CommandListener;
import java.util.ArrayList;
import java.util.HashMap;

public class MiniProjet implements CommandListener{
  public static Flotte flotte;
  public static ArrayList<Membre> personnel;
  public static HashMap<String, String> doc;
  public static final String[]
    allCommandes = {"help", "clear", "exit", "print", "afficherFlotte", "afficherPersonnel", "ajouterVaisseau", "ajouterPersonnel"};
	
  static{
	  doc = new HashMap<String, String>();
	  doc.put("help", "Affiche toutes les commandes disponibles");
	  doc.put("clear", "Efface la zone de texte");
	  doc.put("exit", "Quitte le programme");
	  doc.put("print", "Affiche les arguments, un par ligne");
	  doc.put("afficherFlotte", "Affiche l'état de votre flotte");
	  doc.put("afficherPersonnel", "Affiche l'état de votre personnel");
	  doc.put("ajouterVaisseau", "Ajoute un vaisseau a votre flotte.\nusage: ajouterVaisseau nom type");
	  doc.put("ajouterPersonnel", "Ajoute un membre generique a votre personnel.\nusage: ajouterPersonnel [operateur|libere] nom sexe grade");
  }
	
  public MiniProjet(){}

  public static void main(String[] args) {
	Interface.addCommandListener(new MiniProjet());
    flotte = new Flotte();
	personnel = new ArrayList<Membre>();
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
      case "afficherFlotte": log(flotte.toString()); break;
	  case "afficherPersonnel": for(Membre m : personnel) log(m.toString()); break;
	  case "ajouterVaisseau": if(s.size() == 3) flotte.ajouterVaisseau(new Vaisseau(s.get(1), s.get(2))); else log(doc.get("ajouterMembre")); break;
      case "ajouterPersonnel": 
		if(s.size() == 4) {
			new Membre(s.get(1), s.get(2).charAt(0), s.get(3)); log("Membre ajoute");
		} else if(s.size() == 5) {
			switch(s.get(1)) {
				case "libere": new Libere(s.get(2), s.get(3).charAt(0), s.get(4)); log("Membre libere ajoute !"); break;
				case "operateur": new Operateur(s.get(2), s.get(3).charAt(0), s.get(4)); log("Operateur ajoute !"); break;
				default: log(doc.get("ajouterPersonnel"));
			}
		} else
			log(doc.get("ajouterPersonnel")); 
		break;
	  default: log(s.get(0) + " : commande non reconnue. Tapez 'help' pour avoir une liste des commandes disponibles");
    }
  }

  public static void log(String s) {
    Interface.addLogLine(s);
    System.out.println(s);
  }
}
