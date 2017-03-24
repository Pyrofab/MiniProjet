public class Membre {
  private String nom;
  private char sexe;
  private String grade;

  public Membre(String n, char s, String g) {
    this.nom = n;
    this.sexe = s;
    this.grade = g;
  }

  public String toString() {
    return "nom:" + this.nom + " sexe:" + this.sexe + " grade:" + this.grade;
  }
}
