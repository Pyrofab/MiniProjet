package es.esy.ladysnake.miniprojet.main;

import java.util.ArrayList;

public abstract class Personne {
  private String nom;
  private char sexe;
  private String grade;
  protected Vaisseau vaisseau;
  private static ArrayList<Personne> gens = new ArrayList<Personne>();

  public Personne (String n, char s, String g) {
    this.nom = n;
    this.sexe = s;
    this.grade = g;
    gens.add(this);
  }

  public static Personne getByName(String name) {
  for (Personne m : gens)
    if (m.getNom().equals(name))
      return m;
  return null;
  }

  public static String afficher(){
    String res = "Votre personnel:\n";
    for(Personne m : gens){
      res += m.toString() + "\n";
    }
    return res;
  }

  public String getNom(){
    return this.nom;
  }

  public String toString() {
    return "nom:" + this.nom + " sexe:" + this.sexe + " grade:" + this.grade;
  }

  public void setVaisseau(Vaisseau v) {
    this.vaisseau = v;
  }
}
