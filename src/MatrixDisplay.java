package es.esy.ladysnake.miniprojet.display;

import es.esy.ladysnake.miniprojet.main.MiniProjet;
import es.esy.ladysnake.miniprojet.main.Matrice;
import es.esy.ladysnake.miniprojet.main.Libere;
import es.esy.ladysnake.miniprojet.main.Agent;
import es.esy.ladysnake.gui.Interface;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe utilisee pour afficher la matrice
 */
public class MatrixDisplay extends JPanel implements Runnable, ActionListener {
  private JButton exit;
  private boolean shouldRun;
  private int minY;

  public MatrixDisplay() {
    this.exit = new JButton("exit");
    this.add(exit);
    exit.addActionListener(this);
    shouldRun = false;
  }

  /**
   * affiche une représentation de la matrice sous forme de texte
   */
  public static void textMatrix() {
    String res = " ________________________________________________\n|   |    |    |    |    |    |    |    |    |    |\n|   | 1  | 2  | 3  | 4  | 5  | 6  | 7  | 8  | 9  |\n|___|____|____|____|____|____|____|____|____|____|\n|   |    |    |    |    |    |    |    |    |    |\n";
    for (int i = 0; i < 10; i++) {
      if (i < 9) {
        res += "| "+i+" | xx | xx | xx | xx | xx | xx | xx | xx | xx |\n|___|____|____|____|____|____|____|____|____|____|\n|   |    |    |    |    |    |    |    |    |    |\n";
      } else {
        res += "| "+i+" | xx | xx | xx | xx | xx | xx | xx | xx | xx |\n|___|____|____|____|____|____|____|____|____|____|\n";
      }
    }
    System.out.println(res);
  }

  public void relancer() {
    shouldRun = true;
  }

  public void arreter() {
    shouldRun = false;
  }

  @Override
  public void run() {
    while (true) {
      while (shouldRun) {
        System.out.print("l");
        // try {
          this.repaint();
        // } catch (Exception e) {
        //   e.printStackTrace();
        //   actionPerformed(null);
        // }
        try {
          Thread.sleep(16);
        } catch (InterruptedException e) {   }
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    shouldRun = false;
    Interface.swapPanel();
  }

  @Override
  public void paintComponent(Graphics g) {
    g.clearRect(0,0,this.getWidth(), this.getHeight());
    g.setColor(Color.black);
    g.fillRect(0, exit.getHeight() + 10, this.getWidth(), this.getHeight());
    g.setColor(Color.green);
    for(int i = 0; i < this.getWidth(); i+=this.getWidth()/11) {
      g.drawLine(i, exit.getHeight() + 10, i, this.getHeight());
    }
    int index = 0;
    for (int j = exit.getHeight() + 10; j < this.getHeight(); j += (this.getHeight() - exit.getHeight() - 10)/11) {
      g.drawLine(0, j, this.getWidth(), j);
    }
    for (int x = 0; x < 10; x++) {
      g.drawString(x + "", 20 + (x+1) * (this.getWidth())/11, (this.getHeight() - exit.getHeight() - 10)/11);
      for (int y = 0; y < 10; y++) {
        g.drawString(y + "", 10, exit.getHeight() + 10 + (y+2)*(this.getHeight() - exit.getHeight() - 10)/11 - 20);
        // g.drawString((10*(x-1) + y) +"", 20 + x * (this.getWidth())/10, exit.getHeight() + 10 + (y+1)*(this.getHeight() - exit.getHeight() - 10)/10 - 20);
        g.drawString((Matrice.get(x, y) == null) ? "" : (Matrice.get(x,y) instanceof Libere ? ((Libere)Matrice.get(x, y)).getNom() : "A" + ((Agent)Matrice.get(x, y)).getLvl()), 20 + (x+1) * (this.getWidth())/11, exit.getHeight() + 10 + (y+2)*(this.getHeight() - exit.getHeight() - 10)/11 - 20);
      }
    }
  }
}
