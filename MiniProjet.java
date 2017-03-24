public class MiniProjet {
  public static Flotte flotte;

  public static void main {
    flotte = new Flotte();
    Interface.openInterface();
    Libere m = new Libere("Neo", "m", "Lieutenant");
    Vaisseau v = new Vaisseau("Nebuchadnezzar", "Transport");
    v.ajouterMembre(m);
    flotte.ajouterVaisseau(v);
  }

  public static void commandEntered(String s) {

  }

  public static void log(String s) {
    Interface.addLogLine(s);
    System.out.println(s);
  }
}
