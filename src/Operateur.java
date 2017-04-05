package es.esy.ladysnake.miniprojet.main;

/**
 * Classe décrivant un opérateur de sentation
 */
public class Operateur extends Personne {
  /**
   * Crée une Personne avec le status d'opérateur
   * @param n le nom de cette Personne
   * @param s le sexe de cette Personne
   * @param g le grade de cette Personne
   */
  public Operateur(String n, char s, String g) {
    super(n, s, g);
  }

  @Override
  public String toString(){
	  return super.toString() + " (operateur)";
  }
}
