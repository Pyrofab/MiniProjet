package es.esy.ladysnake.miniprojet.main;

import es.esy.ladysnake.gui.Interface;
import es.esy.ladysnake.gui.CommandListener;
import es.esy.ladysnake.gui.DragNDrop;
import es.esy.ladysnake.miniprojet.display.MatrixDisplay;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class MiniProjet implements CommandListener {
  public static Flotte flotte;
  public static Personnel personnel;
  public static HashMap<String, String> doc;
  private static MatrixDisplay disp = new MatrixDisplay();
  private static DragNDrop drag;
  public static final String[]
    allCommandes = {"help", "clear", "exit", "print", "afficherFlotte", "afficherPersonnel", "ajouterVaisseau", "ajouterPersonnel", "modifierEquipage", "DragAndDrop", "addList", "infiltrerAgent"};

  static {
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

  public MiniProjet() {}

  public static void main(String[] args) {
  	 Interface.addCommandListener(new MiniProjet());
     flotte = new Flotte();
  	 personnel = new Personnel();
     Interface.openInterface("Matrix Simulator mk3", "Confirmer", "Commande:");
     Libere m = new Libere("Neo", 'm', "Lieutenant");
	   personnel.ajouterMembre(m);
     Vaisseau v = new Vaisseau("Nebuchadnezzar", "Transport");
     v.ajouterMembre(m);
     flotte.ajouterVaisseau(v);
     mortaugui();
  }

  @Override
  public void commandEntered(ArrayList<String> s) {
    Interface.setStatus("Processing command");
    switch(s.get(0)) {
      case "help": help(); break;
      case "clear": Interface.clear(); break;
      case "exit": System.exit(0); break;
  	  case "print": log(s.subList(1, s.size())); break;
      case "afficherFlotte": log(flotte.toString()); break;
  	  case "afficherPersonnel": log(personnel.toString()); break;
  	  case "ajouterVaisseau":
          if (s.size() == 3) {
            flotte.ajouterVaisseau(new Vaisseau(s.get(1), s.get(2)));
            log("Vaisseau ajoute !");
          } else
            log(doc.get("ajouterVaisseau"));
        break;
      case "ajouterPersonnel": if (s.size() == 5) ajouterPersonnel(s.subList(1, s.size())); else	log(doc.get("ajouterPersonnel"));	break;
  	  case "modifierEquipage": if(s.size() == 3) modifierEquipage(s.subList(1, s.size())); else log(doc.get("modifierEquipage")); break;
      case "DragAndDrop": drag = new DragNDrop(flotte.getByName(s.get(1)).getEquipage()); break;
      case "addList": drag.addList(flotte.getByName(s.get(1)).getEquipage()); break;
      case "infiltrerAgent":
          if(s.size() > 1) {
            if(personnel.getByName(s.get(1)) != null && personnel.getByName(s.get(1)) instanceof Libere) {
              Matrice.infiltrer((Libere)personnel.getByName(s.get(1)));
            }
          }
        break;
      case "afficherMatrice": System.out.println(Matrice.afficherMatrice()); mortaugui(); break;
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

  public static void help() {
    for(String str : allCommandes)
      log(str + ": " + doc.get(str) + "\n");
  }

  public static void ajouterPersonnel (List<String> args) {
    switch (args.get(0)) {
      case "libere": personnel.ajouterMembre(new Libere(args.get(1), args.get(2).charAt(0), args.get(3))); log("Membre libere ajoute !"); break;
      case "operateur": personnel.ajouterMembre(new Operateur(args.get(1), args.get(2).charAt(0), args.get(3))); log("Operateur ajoute !"); break;
      default: log(doc.get("ajouterPersonnel"));
    }
  }

  public static void modifierEquipage (List<String> args) {
    if(flotte.getByName(args.get(0)) != null){
      if(personnel.getByName(args.get(1)) != null){
        if(!flotte.getByName(args.get(0)).supprimerMembre(args.get(1)))
          flotte.getByName(args.get(0)).ajouterMembre(personnel.getByName(args.get(1)));
      } else log(args.get(1) + ": cette personne ne fait pas partie de votre personnel.");
    } else log(args.get(0) + ": ce vaisseau ne fait pas partie de votre flotte.");
  }

  public static void mortaugui() {
    Interface.switchPanel(disp);
    (new Thread(disp)).start();
  }

  public static void log (List<String> args) {
    for (String arg : args)
      log(arg);
  }

  public static void log (String s) {
    Interface.addLogLine(s);
    System.out.println(s);
  }
}
