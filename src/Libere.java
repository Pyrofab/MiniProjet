package es.esy.ladysnake.miniprojet.main;

public class Libere extends Personne {
  private int nbTransferts;
  private boolean infecte, infiltre;

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
    infecte = false;
    infiltre = false;
  }

  @Override
  public String toString() {
	  return super.toString() + " (membre libere" + ((infiltre) ? " infiltre" : "") + ((infecte) ? " infecte!" : "") + ")";
  }

  /**
   * Tente d'effectuer un transfert de ce membre dans la matrice
   * Si il n'est pas déjà dans la matrice, l'infiltre, sinon l'exfiltre.
   * @return un code correspondant au déroulement du transfert : 1 si l'agent a ete infiltré, 0 si l'infiltration a echoué pour une raison inconnue, 69 si le vaisseau n'est pas securisé lors de l'infiltration, -1 si le membre a ete exfiltré.
   */
  public int transfert() {
    if (Matrice.getPos(this) == null) {
      // Ce membre est à l'extérieur de la matrice
      if (this.vaisseau != null && this.vaisseau.isSecured()) {
        // Son vaisseau est sécurisé
        if(!Matrice.infiltrer(this))
          return 0;   //l'infiltration a échoué
        this.nbTransferts++;
        this.infiltre = true;
        this.infecte = Matrice.testInfection(this);
        System.out.println(this.infecte);
        return 1;     //l'infiltration a réussi
      } else {
        return 69;        //son vaisseau n'est pas sécurisé
      }
    } else {
      // Ce membre est déjà dans la matrice
      if(this.infecte)
        return -69;       //l'extraction a échoué
      Matrice.exfiltrer(this);
      this.nbTransferts++;
      infiltre = false;
      return -1;
    }
  }

  public boolean estInfecte() {
    return this.infecte;
  }

  public int getNbTransferts() {
    return this.nbTransferts;
  }


}
