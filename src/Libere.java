package es.esy.ladysnake.miniprojet.main;

public class Libere extends Personne {
  private int nbTransferts;

  public Libere(String n, char s, String g) {
    super(n, s, g);
	  nbTransferts = 0;
  }

  public String toString() {
	  return super.toString() + " (membre libere)";
  }

  public int transfert() {
    if (Matrice.getPos(this) == null) {
      // pas dans la matrice
      if (this.vaisseau.isSecured()) {
        // vaisseau sécurisé
        if(!Matrice.infiltrer(this))
          return 0;
        this.nbTransferts++;
        return 1;
      } else {
        // vaisseau non sécurisé
        return 69;
      }
    } else {
      // dans la matrice
      Matrice.exfiltrer(this);
      this.nbTransferts++;
      return -1;
    }
  }
}
