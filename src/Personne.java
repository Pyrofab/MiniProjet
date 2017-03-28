package es.esy.ladysnake.miniprojet;

public class Personne {
  private String nom;
  private char sexe;
  private String grade;

  public Personne (String n, char s, String g) {
    this.nom = n;
    this.sexe = s;
    this.grade = g;
  }

  public String getNom(){
    return this.nom;
  }

  public String toString() {
    return "nom:" + this.nom + " sexe:" + this.sexe + " grade:" + this.grade;
  }
}
