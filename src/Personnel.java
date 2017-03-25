package es.esy.ladysnake.miniprojet;

import java.util.ArrayList;

/**
 * A class describing Sion's people
 */
public class Personnel{
  private ArrayList<Membre> gens;

  public Personnel() {
    gens = new ArrayList<Membre>(1);
  }

  public void ajouterMembre(Membre m) {
    gens.add(m);
  }
  
  public Membre getByName(String name) {
	for (Membre m : gens)
		if (m.getNom().equals(name))
			return m;
	return null;
  }

  public String toString(){
    String res = "Votre personnel:\n";
    for(Membre m : gens){
      res += m.toString() + "\n";
    }
    return res;
  }
}
