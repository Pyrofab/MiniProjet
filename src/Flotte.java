package es.esy.ladysnake.miniprojet.main;

import java.util.ArrayList;

/**
 * Une classe décrivant la flotte de Sion
 */
public class Flotte{
  private static ArrayList<Vaisseau> flotte = new ArrayList<Vaisseau>();

  /**
   * Ajoute un Vaisseau à cette flotte.
   * @param v le Vaisseau à ajouter.
   */
  public static void ajouterVaisseau(Vaisseau v) {
    flotte.add(v);
  }

  /**
   * @param name le nom du vaisseau à récupérer.
   * @return Le vaisseau de la flotte portant ce nom.
   */
  public static Vaisseau getByName(String name) {
	for (Vaisseau v : flotte)
		if (v.getNom().equals(name))
			return v;
	return null;
  }

  /**
   * @return le nombre de vaisseaux dans la flotte
   */
  public static int size() {
    return flotte.size();
  }

  /**
   * @return une représentation de votre flotte sous forme de String
   */
  public static String afficher(){
    String res = "Votre flotte:\n";
    for(Vaisseau v : flotte){
      res += v.toString() + "\n";
    }
    return res;
  }
}
