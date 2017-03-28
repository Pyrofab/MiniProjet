package es.esy.ladysnake.miniprojet;

import java.util.ArrayList;

/**
 * A class describing Sion's people
 */
public class Personnel{
  private ArrayList<Personne> gens;

  public Personnel() {
    gens = new ArrayList<Personne>(1);
  }

  public void ajouterMembre(Personne m) {
    gens.add(m);
  }

  public Personne getByName(String name) {
	for (Personne m : gens)
		if (m.getNom().equals(name))
			return m;
	return null;
  }

  public String toString(){
    String res = "Votre personnel:\n";
    for(Personne m : gens){
      res += m.toString() + "\n";
    }
    return res;
  }
}
