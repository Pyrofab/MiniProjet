package es.esy.ladysnake.miniprojet.main;

public abstract class Personne {
  private String nom;
  private char sexe;
  private String grade;
  protected Vaisseau vaisseau;

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

  public void setVaisseau(Vaisseau v) {
    this.vaisseau = v;
  }
}
