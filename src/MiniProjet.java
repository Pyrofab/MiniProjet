package es.esy.ladysnake.miniprojet.main;

import es.esy.ladysnake.gui.CommandListener;
import es.esy.ladysnake.gui.Interface;
import es.esy.ladysnake.miniprojet.display.MatrixDisplay;
import es.esy.ladysnake.miniprojet.display.CommandExecuter;
import es.esy.ladysnake.miniprojet.display.FancyInterface;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class MiniProjet {
  public static final MatrixDisplay matrixDisp;
  public static final FancyInterface f = new FancyInterface();

  static {
    matrixDisp = new MatrixDisplay();
  }

  public static void main(String[] args) {
    CommandExecuter ce = new CommandExecuter();
  	 Interface.addCommandListener(ce);
     Interface.openInterface("Matrix Simulator mk3", "Confirmer", "Commande:");
     log("==============================================================");
     log("Bienvenue dans la matrice !");
     log("Ecrivez 'help' pour avoir une liste des commandes disponibles");
     log("==============================================================");
     autoPlay();
     ArrayList<String> commande = new ArrayList<String>();
     Scanner sc = new Scanner(System.in);
     String str;
     Scanner sc2;
     while(true) {
       System.out.println("Entrez votre commande ici ou dans la zone de l'interface dediee:");
       commande.clear();
       str = sc.nextLine();
       if(str.length() <= 0) continue;
       sc2 = new Scanner(str);
       while(sc2.hasNext())
          commande.add(sc2.next());
       ce.commandEntered(commande);
       sc2.close();
     }
  }

  /**
   * Effectue quelques opérations démontrant les diverses fonctionnalités du programme
   */
  public static void autoPlay() {
    Libere m = new Libere("Neo", 'm', "Lieutenant");
    Vaisseau v = new Vaisseau("Nebuchadnezzar", "Hovercraft");
    v.ajouterMembre(m);
    v.ajouterMembre(new Operateur("op1", 'f', "Sergeant"));
    Flotte.ajouterVaisseau(v);
    Vaisseau v2 = new Vaisseau("Goliath", "PL-47");
    v2.ajouterMembre(new Libere("Morpheus", 'm', "Commandant"));
    Flotte.ajouterVaisseau(v2);
    new Libere("Redpill", 'f', "Caporal");
    CommandExecuter.commandMortaugui();
    try {
      Thread.sleep(500);
      Matrice.infiltrer(new Agent());
      Thread.sleep(1000);
      m.transfert();
      Thread.sleep(1000);
      matrixDisp.shouldRun = false;
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void log (List args) {
    for (Object arg : args)
      log(arg);
  }

  /**
   * Affiche l'objet passé en paramètre dans l'interface et dans la console
   * @param s l'objet à afficher
   */
  public static void log (Object s) {
    Interface.addLogLine(s.toString());
    System.out.println(s);
  }
}
