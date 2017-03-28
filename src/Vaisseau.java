package es.esy.ladysnake.miniprojet.main;

import java.util.ArrayList;

public class Vaisseau {
  private String nom;
  private String type;
  private Personne[] equipage;

  public Vaisseau(String n, String t) {
    this.nom = n;
    this.type = t;
    equipage = new Personne[5];
  }

  public boolean ajouterMembre(Personne m) {
    for(int i = 0; i < equipage.length; i++){
      if(equipage[i] == null){
        equipage[i] = m;
        return true;
      }
    }
    return false;
  }

  public boolean isSecured() {
    for (Personne m : equipage){
      if(m != null && m instanceof Operateur)
        return true;
      if(m != null)
        System.out.println(m.getClass().toString());
      }
    return false;
  }

  public Personne[] getEquipage(){
    return this.equipage;
  }

  public String afficherEquipage() {
    String res = "";
    for(int i = 0; i < equipage.length; i++) {
      if(equipage[i] != null)
        res += equipage[i].toString() + " ";
    }
    return res;
  }

  public void supprimerMembre(int i) {
    this.equipage[i] = null;
  }

  public boolean supprimerMembre(String n) {
    for(int i = 0; i < equipage.length; i++) {
      if(equipage[i] != null && equipage[i].getNom().equals(n)){
        equipage[i] = null;
		    return true;
      }
    }
	return false;
  }

  public String getNom(){
	  return this.nom;
  }

  public String toString() {
    return this.nom + "(" + this.type + ")" + "\nEquipage:\n" + afficherEquipage() + "\nisSecured: " + isSecured() + "\n";
  }
}
