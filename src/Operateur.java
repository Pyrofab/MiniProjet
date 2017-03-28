package es.esy.ladysnake.miniprojet;

public class Operateur extends Personne {
  public Operateur(String n, char s, String g) {
    super(n, s, g);
  }

  public String toString(){
	  return super.toString() + " (operateur)";
  }
}
