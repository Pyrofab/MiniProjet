package es.esy.ladysnake.miniprojet.main;

import es.esy.ladysnake.gui.CommandListener;
import es.esy.ladysnake.gui.Interface;
import es.esy.ladysnake.miniprojet.display.MatrixDisplay;
import es.esy.ladysnake.miniprojet.display.CommandExecuter;
import java.util.List;

public class MiniProjet {
  public static final MatrixDisplay matrixDisp;

  static {
    matrixDisp = new MatrixDisplay();
    Thread disp = new Thread(matrixDisp);
    disp.setName("AffichageMatrice");
    disp.start();
  }

  public static void main(String[] args) {
  	 Interface.addCommandListener(new CommandExecuter());
     Interface.openInterface("Matrix Simulator mk3", "Confirmer", "Commande:");
     log("==============================================================");
     log("Bienvenue dans la matrice !");
     log("Ecrivez 'help' pour avoir une liste des commandes disponibles");
     log("==============================================================");
     autoPlay();
  }

  public static void autoPlay() {
    Libere m = new Libere("Neo", 'm', "Lieutenant");
    Vaisseau v = new Vaisseau("Nebuchadnezzar", "Hovercraft");
    v.ajouterMembre(m);
    v.ajouterMembre(new Operateur("op1", 'f', "Sergeant"));
    Flotte.ajouterVaisseau(v);
    Vaisseau v2 = new Vaisseau("Goliath", "PL-47");
    v2.ajouterMembre(new Libere("Morpheus", 'm', "Commandant"));
    Flotte.ajouterVaisseau(v2);
    // commandMortaugui();
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
