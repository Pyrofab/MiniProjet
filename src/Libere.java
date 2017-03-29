package es.esy.ladysnake.miniprojet.main;

public class Libere extends Infiltrable {
  private int nbTransferts;

  public Libere(String n, char s, String g) {
    super(n, s, g);
	nbTransferts = 0;
  }

  public String toString() {
	  return super.toString() + " (membre libere)";
  }
}
