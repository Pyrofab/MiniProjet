package es.esy.ladysnake.miniprojet.main;

import java.util.ArrayList;

/**
 * A class describing Sion's fleet
 */
public class Flotte{
  private ArrayList<Vaisseau> flotte;

  public Flotte() {
    flotte = new ArrayList<Vaisseau>(1);
  }

  public void ajouterVaisseau(Vaisseau v) {
    flotte.add(v);
  }

  public Vaisseau getByName(String name) {
	for (Vaisseau v : flotte)
		if (v.getNom().equals(name))
			return v;
	return null;
  }

  public String toString(){
    String res = "Votre flotte:\n";
    for(Vaisseau v : flotte){
      res += v.toString() + "\n";
    }
    return res;
  }
}
