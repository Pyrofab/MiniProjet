import java.util.ArrayList;

public class Flotte{
  private ArrayList<Vaisseau> flotte;

  public Flotte() {
    flotte = new ArrayList<Vaisseau>(1);
  }

  public void ajouterVaisseau(Vaisseau v) {
    flotte.add(v);
  }

  public String toString(){
    String res = "Flotte:";
    for(Vaisseau v : flotte){
      res += v.toString() + "\n";
    }
    return res;
  }
}
