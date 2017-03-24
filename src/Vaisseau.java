package es.esy.ladysnake.miniprojet;

import java.util.ArrayList;

public class Vaisseau {
  private String nom;
  private String type;
  private ArrayList<Membre> equipage;

  public Vaisseau(String n, String t) {
    this.nom = n;
    this.type = t;
    equipage = new ArrayList<Membre>();
  }

  public void ajouterMembre(Membre m) {
    this.equipage.add(m);
  }

  public String afficherEquipage() {
    String res = "";
    for(Membre m : equipage) {
      res += m.toString();
    }
    return res;
  }

  public void supprimerMembre(int i) {
    this.equipage.remove(i);
  }

  public void supprimerMembre(String n) {
    for(Membre m : equipage) {
      if(m.getNom() == n)
        equipage.remove(m);
    }
  }

  public String toString() {
    return this.nom + "(" + this.type + ")" + "\nEquipage:\n" + afficherEquipage() + "\n";
  }
}
