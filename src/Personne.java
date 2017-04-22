package es.esy.ladysnake.miniprojet.main;

import java.util.ArrayList;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * Classe décrivant un membre de Sion
 */
public abstract class Personne {
  private String nom;
  private char sexe;
  private String grade;
  protected Vaisseau vaisseau;
  private static ArrayList<Personne> gens = new ArrayList<Personne>();
  private static final JList<Personne> jgens;
  private static ListModel<Personne> model;

  static {
    model = new DefaultListModel<Personne>()
    {
      public boolean isCellEditable(int row)
      {
        return false;//This causes all cells to be not editable
      }
    };
    jgens = new JList<Personne>(model);
  }

  public static JList getPersonnelView() {
    return jgens;
  }

  /**
   * Constructeur de la classe Personne
   * @param n le nom de la Personne
   * @param s le sexe de la Personne
   * @param g le grade de la Personne
   */
  public Personne (String n, char s, String g) {
    this.nom = n;
    this.sexe = s;
    this.grade = g;
    gens.add(this);
    ((DefaultListModel<Personne>)model).addElement(this);
  }

  /**
   * Fonction permettant d'acceder a un membre de Sion a partir de son nom
   * @param name le nom de la personne
   * @return la personne portant ce nom, ou null si il n'en existe pas
   */
  public static Personne getByName(String name) {
  for (Personne m : gens)
    if (m.getNom().equals(name))
      return m;
  return null;
  }

  /**
   * @return une représentation de votre personnel sous forme de String
   */
  public static String afficher(){
    String res = "Votre personnel:\n";
    for(Personne m : gens){
      res += m.toString() + "\n";
    }
    return res;
  }

  public static ArrayList<Personne> getAll() {
    return gens;
  }

  /**
   * Accesseur permettant d'accéder au nom d'une personne
   * @return son nom
   */
  public String getNom(){
    return this.nom;
  }

  /**
   * Assigne un vaisseau à ce membre de Sion.
   * @param v le vaisseau dont l'équipage comprend ce membre
   */
  public void setVaisseau(Vaisseau v) {
    this.vaisseau = v;
  }

  @Override
  public String toString() {
    return "nom:" + this.nom + " sexe:" + this.sexe + " grade:" + this.grade;
  }
}
