package es.esy.ladysnake.miniprojet.main;

import java.util.ArrayList;

/**
 * A class describing Sion's fleet
 */
public class Flotte{
  private static ArrayList<Vaisseau> flotte = new ArrayList<Vaisseau>(1);

  public static void ajouterVaisseau(Vaisseau v) {
    flotte.add(v);
  }

  public static Vaisseau getByName(String name) {
	for (Vaisseau v : flotte)
		if (v.getNom().equals(name))
			return v;
	return null;
  }

  public static int size() {
    return flotte.size();
  }

  public static String afficher(){
    String res = "Votre flotte:\n";
    for(Vaisseau v : flotte){
      res += v.toString() + "\n";
    }
    return res;
  }
}
