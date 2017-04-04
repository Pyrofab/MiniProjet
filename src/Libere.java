package es.esy.ladysnake.miniprojet.main;

public class Libere extends Personne {
  private int nbTransferts;

  /**
   * Constructeur de la classe Libere
   * Cree un membre libéré n'ayant aucun transfert à son actif
   * @param n le nom de cette Personne
   * @param s le sexe de cette Personne
   * @param g le grade de cette Personne
   */
  public Libere(String n, char s, String g) {
    super(n, s, g);
	  nbTransferts = 0;
  }

  /**
   * @return une représentation de ce membre libéré sous forme de String
   */
  public String toString() {
	  return super.toString() + " (membre libere)";
  }

  /**
   * Tente d'effectuer un transfert de ce membre dans la matrice
   * Si il n'est pas déjà dans la matrice, l'infiltre, sinon l'exfiltre.
   * @return un code correspondant au déroulement du transfert : 1 si l'agent a ete infiltré, 0 si l'infiltration a echoué pour une raison inconnue, 69 si le vaisseau n'est pas securisé lors de l'infiltration, -1 si le membre a ete exfiltré.
   */
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
