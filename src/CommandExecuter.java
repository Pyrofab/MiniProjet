package es.esy.ladysnake.miniprojet.display;

import es.esy.ladysnake.gui.Interface;
import es.esy.ladysnake.gui.CommandListener;
import es.esy.ladysnake.miniprojet.main.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class CommandExecuter implements CommandListener {
  public static final String[] allCommandes = {"help", "clear", "exit", "print", "autoPlay", "afficherFlotte", "afficherPersonnel", "afficherMachines", "afficherMatrice", "ajouterVaisseau", "ajouterPersonnel", "modifierEquipage", "transfert"};
  public static HashMap<String, String> doc;

  static {
    doc = new HashMap<String, String>();
	  doc.put("help", "Affiche les commandes disponibles et leur utilisation.\nusage: help [commande]");
	  doc.put("clear", "Efface la zone de texte");
	  doc.put("exit", "Quitte le programme");
	  doc.put("print", "Affiche les arguments, un par ligne");
    doc.put("autoPlay", "Lance la matrice en mode automatique.");
	  doc.put("afficherFlotte", "Affiche l'etat de votre flotte");
	  doc.put("afficherPersonnel", "Affiche l'etat de votre personnel");
    doc.put("afficherMachines", "Affiche tous les agents possedes par l'ennemi");
    doc.put("afficherMatrice", "Affiche la matrice sous forme graphique");
	  doc.put("ajouterVaisseau", "Ajoute un vaisseau a votre flotte.\nusage: ajouterVaisseau <nom> <type>");
	  doc.put("ajouterPersonnel", "Ajoute un membre a votre personnel.\nusage: ajouterPersonnel (operateur|libere) <nom> <sexe> <grade>");
	  doc.put("modifierEquipage", "Ajoute un membre de votre personnel a l'equipage du vaisseau, ou le supprime si il y est deja.\nusage: modifierEquipage <nomVaisseau> <nomMembre>");
    doc.put("transfert", "Infiltre un membre de votre personnel ou un agent dans la matrice, ou l'exfiltre si il y est deja.\nusage: transfert <nom_du_libere|uid_de_agent>");
  }

  @Override
  public void commandEntered(ArrayList<String> s) {
    Interface.setStatus("Processing command");
    switch(s.get(0)) {
      case "help": if(s.size() == 1) commandHelp(); else commandHelp(s.get(1)); break;
      case "clear": Interface.clear(); break;
      case "exit": System.exit(0); break;
  	  case "print": MiniProjet.log(s.subList(1, s.size())); break;
      case "autoPlay": MiniProjet.autoPlay();
      case "afficherFlotte": MiniProjet.log(Flotte.afficher()); break;
  	  case "afficherPersonnel": MiniProjet.log(Personne.afficher()); break;
  	  case "ajouterVaisseau": if (s.size() == 3) commandAjouterVaisseau(s.subList(1, s.size())); else MiniProjet.log(doc.get("ajouterVaisseau")); break;
      case "ajouterPersonnel": if (s.size() == 5) commandAjouterPersonnel(s.subList(1, s.size())); else	MiniProjet.log(doc.get("ajouterPersonnel"));	break;
      case "ajouterAgent": new Agent(); break;
      case "modifierEquipage": if(s.size() == 3) commandModifierEquipage(s.subList(1, s.size())); else MiniProjet.log(doc.get("modifierEquipage")); break;
      case "transfert": if(s.size() == 2) commandTransfert(s.get(1)); else MiniProjet.log(doc.get("transfert")); break;
      case "afficherMatrice": MatrixDisplay.textMatrix(); commandMortaugui(); break;
      case "afficherMachines": MiniProjet.log(Agent.afficherForces()); break;
  	  default: MiniProjet.log(s.get(0) + " : commande non reconnue. Tapez 'help' pour avoir une liste des commandes disponibles");
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

  public static void commandHelp() {
    for(String str : allCommandes)
      MiniProjet.log(str + ": " + doc.get(str) + "\n");
    MiniProjet.log("(astuce: vous pouvez presser la touche 'ctrl' pour autocompleter votre commande !)");
  }

  public static void commandHelp(String arg) {
    if(doc.get(arg) == null)
      commandHelp();
    else MiniProjet.log(arg + ": " + doc.get(arg) + "\n");
  }

  public static void commandAjouterVaisseau (List <String> args) {
    if(Flotte.getByName(args.get(0)) == null) {
      MiniProjet.log("Un vaisseau portant le meme nom existe deja.");
      return;
    }
    Flotte.ajouterVaisseau(new Vaisseau(args.get(1), args.get(2)));
    MiniProjet.log("Vaisseau ajoute !");
  }

  public static void commandAjouterPersonnel (List<String> args) {
    switch (args.get(0)) {
      case "libere": new Libere(args.get(1), args.get(2).charAt(0), args.get(3)); MiniProjet.log("Membre libere ajoute !"); break;
      case "operateur": new Operateur(args.get(1), args.get(2).charAt(0), args.get(3)); MiniProjet.log("Operateur ajoute !"); break;
      default: MiniProjet.log(doc.get("ajouterPersonnel"));
    }
  }

  public static void commandModifierEquipage (List<String> args) {
    if(Flotte.getByName(args.get(0)) != null){
      if(Personne.getByName(args.get(1)) != null){
        if(!Flotte.getByName(args.get(0)).supprimerMembre(args.get(1)))
          Flotte.getByName(args.get(0)).ajouterMembre(Personne.getByName(args.get(1)));
      } else MiniProjet.log(args.get(1) + ": cette personne ne fait pas partie de votre personnel.");
    } else MiniProjet.log(args.get(0) + ": ce vaisseau ne fait pas partie de votre flotte.");
  }

  public static void commandMortaugui() {
    Interface.swapPanel(MiniProjet.matrixDisp);
    MiniProjet.matrixDisp.relancer();
  }

  public static void commandTransfert(String arg) {
    if(Personne.getByName(arg) != null && Personne.getByName(arg) instanceof Libere) {
      int ret = ((Libere)Personne.getByName(arg)).transfert();
      if (ret == 1)
        MiniProjet.log("Votre membre libere a ete infiltre.");
      else if (ret == -1)
        MiniProjet.log("Votre membre libere a ete exfiltre.");
      else if (ret == 69)
        MiniProjet.log("Le vaisseau n'est pas securise ! Ajouter un operateur avant d'infiltrer son equipage.");
      else
        MiniProjet.log("L'operation a echoue. La matrice est peut-etre pleine.");
    } else {
      int id;
      try {
        id = Integer.parseInt(arg);
      } catch (NumberFormatException e) {
        MiniProjet.log(arg + ": cette personne n'est pas un membre libere.");
        return;
      }
      if(Agent.get(id) != null) {
        if(Matrice.getPos(Agent.get(id)) == null)
          MiniProjet.log((Matrice.infiltrer(Agent.get(id))) ? "L'agent a ete infiltre avec succes" : "L'operation a echoue. La matrice est peut-etre pleine.");
        else
          MiniProjet.log((Matrice.exfiltrer(Agent.get(id))) ? "L'agent a ete exfiltre avec succes" : "L'operation a echoue.");
      } else MiniProjet.log(arg + ": il n'existe pas d'agent avec cet id");
    }
  }

}
