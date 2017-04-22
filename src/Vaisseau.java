package es.esy.ladysnake.miniprojet.main;

import java.util.ArrayList;

/**
 * Classe décrivant un vaisseau de Sion
 */
public class Vaisseau {
  private String nom, type;
  private Personne[] equipage;

  /**
   * Cree un vaisseau quelconque, caractérisé par son id
   */
  public Vaisseau() {
    this("Vaisseau" + Flotte.size(), "Transport");
  }

  /**
   * Crée un vaisseau avec les caractéristiques passées en paramètre
   * @param n le nom de ce vaisseau
   * @param t le type de ce vaisseau
   */
  public Vaisseau(String n, String t) {
    this.nom = n;
    this.type = t;
    equipage = new Personne[5];
  }

  /**
   * Ajoute un membre de Sion à l'équipage de ce vaisseau.
   * @param m la personne à ajouter à l'équipage
   * @return true si le membre a été ajouté à l'équipage, false sinon.
   */
  public boolean ajouterMembre(Personne m) {
    if(m.vaisseau != null)
      return false;
    for(int i = 0; i < equipage.length; i++){
      if(equipage[i] == m)
        return false;
      if(equipage[i] == null){
        m.setVaisseau(this);
        equipage[i] = m;
        return true;
      }
    }
    return false;
  }

  /**
   * Supprime un membre de l'équipage.
   * @param i la place du membre dans l'équipage de ce vaisseau.
   * @return true si le membre a été supprimé, false s'il n'existe pas.
   */
  public boolean supprimerMembre(int i) {
    if(this.equipage[i] == null)
      return false;
    this.equipage[i].setVaisseau(null);
    this.equipage[i] = null;
    return true;
  }

  /**
   * Supprime un membre de l'équipage.
   * @param n le nom du membre d'équipage
   * @return true si le membre a été supprimé, false s'il n'existe pas.
   */
  public boolean supprimerMembre(String n) {
    for(int i = 0; i < equipage.length; i++) {
      if(equipage[i] != null && equipage[i].getNom().equals(n)){
        this.equipage[i].setVaisseau(null);
        this.equipage[i] = null;
        return true;
      }
    }
    return false;
  }

  /**
   * Indique si un vaisseau est sécurisé
   * @return true si ce vaisseau possède au moins un opérateur
   */
  public boolean isSecured() {
    for (Personne m : equipage){
      if(m != null && m instanceof Operateur)
        return true;
      }
    return false;
  }

  /**
   * @return l'équipage de ce vaisseau sous forme de tableau
   */
  public Personne[] getEquipage(){
    return this.equipage;
  }

  public String afficherEquipage() {
    String res = "";
    for(int i = 0; i < equipage.length; i++) {
      if(equipage[i] != null){
        equipage[i].setVaisseau(null);
        res += equipage[i].toString() + "; ";
      }
    }
    return res;
  }

  /**
   * @return le nom de ce vaisseau
   */
  public String getNom() {
	  return this.nom;
  }

  @Override
  public String toString() {
    return this.nom + "(" + this.type + ")" + "\nEquipage:\n" + afficherEquipage() + "\nisSecured: " + isSecured() + "\n";
  }
}
