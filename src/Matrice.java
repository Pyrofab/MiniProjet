import java.util.Random;

public class Matrice {
  private Libere[][] matrice;

  public Matrice() {
    this.matrice = new Libere[10][10];
  }

  public boolean infiltrerAgent(Libere ag) {
    Random rand = new Random();
    while (true) {
      if (matrice[x=rand.nextInt(9)][y=rand.nextInt(9)] == NULL) break;
    }


  }
}
