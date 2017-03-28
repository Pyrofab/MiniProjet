package es.esy.ladysnake.miniprojet;

import es.esy.ladysnake.gui.Interface;
import es.esy.ladysnake.gui.CommandListener;
import es.esy.ladysnake.gui.DragNDrop;
import java.util.ArrayList;
import java.util.HashMap;

public class MiniProjet implements CommandListener{
  public static Flotte flotte;
  public static Personnel personnel;
  public static HashMap<String, String> doc;
  private static Matrice matrix = new Matrice();
  static DragNDrop drag;
  public static final String[]
    allCommandes = {"help", "clear", "exit", "print", "afficherFlotte", "afficherPersonnel", "ajouterVaisseau", "ajouterPersonnel", "modifierEquipage", "DragAndDrop", "addList", "infiltrerAgent"};

  static{
	  doc = new HashMap<String, String>();
	  doc.put("help", "Affiche toutes les commandes disponibles");
	  doc.put("clear", "Efface la zone de texte");
	  doc.put("exit", "Quitte le programme");
	  doc.put("print", "Affiche les arguments, un par ligne");
	  doc.put("afficherFlotte", "Affiche l'etat de votre flotte");
	  doc.put("afficherPersonnel", "Affiche l'etat de votre personnel");
	  doc.put("ajouterVaisseau", "Ajoute un vaisseau a votre flotte.\nusage: ajouterVaisseau <nom> <type>");
	  doc.put("ajouterPersonnel", "Ajoute un membre a votre personnel.\nusage: ajouterPersonnel [operateur|libere] <nom> <sexe> <grade>");
	  doc.put("modifierEquipage", "Ajoute un membre de votre personnel a l'equipage du vaisseau, ou le supprime si il y est deja.\nusage: modifierEquipage <nomVaisseau> <nomMembre>");
  }

  public MiniProjet(){}

  public static void main(String[] args) {
  	 Interface.addCommandListener(new MiniProjet());
     flotte = new Flotte();
  	 personnel = new Personnel();
     Interface.openInterface("Matrix Simulator mk3", "Confirmer", "Entrer une commande");
     Libere m = new Libere("Neo", 'm', "Lieutenant");
	   personnel.ajouterMembre(m);
     Vaisseau v = new Vaisseau("Nebuchadnezzar", "Transport");
     v.ajouterMembre(m);
     flotte.ajouterVaisseau(v);
  }

  @Override
  public void commandEntered(ArrayList<String> s) {
    Interface.setStatus("Processing command");
    switch(s.get(0)){
      case "help": for(String str : allCommandes) log(str + ": " + doc.get(str) + "\n"); break;
      case "clear": Interface.clear(); break;
      case "exit": System.exit(0); break;
  	  case "print": for(String args : s.subList(1, s.size())) log(args); break;
      case "afficherFlotte": log(flotte.toString()); break;
  	  case "afficherPersonnel": log(personnel.toString()); break;
  	  case "ajouterVaisseau": if(s.size() == 3) {flotte.ajouterVaisseau(new Vaisseau(s.get(1), s.get(2))); log("Vaisseau ajoute !");} else log(doc.get("ajouterVaisseau")); break;
      case "ajouterPersonnel":
    		if(s.size() == 4) {
    			personnel.ajouterMembre(new Membre(s.get(1), s.get(2).charAt(0), s.get(3))); log("Membre ajoute");
    		} else if(s.size() == 5) {
    			switch(s.get(1)) {
    				case "libere": personnel.ajouterMembre(new Libere(s.get(2), s.get(3).charAt(0), s.get(4))); log("Membre libere ajoute !"); break;
    				case "operateur": personnel.ajouterMembre(new Operateur(s.get(2), s.get(3).charAt(0), s.get(4))); log("Operateur ajoute !"); break;
    				default: log(doc.get("ajouterPersonnel"));
    			}
    		} else
    			log(doc.get("ajouterPersonnel"));
  		break;
  	  case "modifierEquipage":
      	if(s.size() == 3) {
        	if(flotte.getByName(s.get(1)) != null){
      			if(personnel.getByName(s.get(2)) != null){
      				if(!flotte.getByName(s.get(1)).supprimerMembre(s.get(2)))
      					flotte.getByName(s.get(1)).ajouterMembre(personnel.getByName(s.get(2)));
      			} else log(s.get(2) + ": cette personne ne fait pas partie de votre personnel."); break;
      		} else log(s.get(1) + ": ce vaisseau ne fait pas partie de votre flotte.");
        } else log(doc.get("modifierEquipage"));
      break;
      case "DragAndDrop": drag = new DragNDrop(flotte.getByName(s.get(1)).getEquipage()); break;
      case "addList": drag.addList(flotte.getByName(s.get(1)).getEquipage()); break;
      case "infiltrerAgent":
        if(s.size() > 1) {
          if(personnel.getByName(s.get(1)) != null && personnel.getByName(s.get(1)) instanceof Libere) {
            matrix.infiltrerAgent((Libere)personnel.getByName(s.get(1)));
          }
        } break;
  	  default: log(s.get(0) + " : commande non reconnue. Tapez 'help' pour avoir une liste des commandes disponibles");
    }
    Interface.setStatus("Idle");
  }

  @Override
  public String commandTabbed(String commande) {
    for (String s : allCommandes)
      if (s.contains(commande))
        return s + ";";
    return commande;
  }

  public static void log(String s) {
    Interface.addLogLine(s);
    System.out.println(s);
  }
}
