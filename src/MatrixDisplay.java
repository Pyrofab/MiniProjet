package es.esy.ladysnake.miniprojet.display;

import es.esy.ladysnake.miniprojet.main.Matrice;
import es.esy.ladysnake.gui.Interface;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MatrixDisplay extends JPanel implements Runnable, ActionListener {
  private Matrice matrix;
  private JButton exit;
  private boolean shouldRun;
  private int minY;

  public MatrixDisplay(Matrice mat) {
    this.matrix = mat;
    this.exit = new JButton("exit");
    this.add(exit);
    exit.addActionListener(this);
  }

  @Override
  public void run() {
    shouldRun = true;
    while (shouldRun) {
      // System.out.print("l");
      this.repaint();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    shouldRun = false;
    Interface.switchPanel();
  }

  @Override
  public void paintComponent(Graphics g) {
    g.clearRect(0,0,this.getWidth(), this.getHeight());
    g.setColor(Color.black);
    g.fillRect(0, exit.getHeight(), this.getWidth(), this.getHeight());
    g.setColor(Color.green);
    for(int i = 0; i < this.getWidth(); i+=this.getWidth()/10) {
      g.drawLine(i, exit.getHeight(), i, this.getHeight());
    }
    int index = 0;
    for (int j = exit.getHeight(); j < this.getHeight(); j += (this.getHeight() - exit.getHeight())/10) {
      g.drawLine(0, j, this.getWidth(), j);
    }
    for (int x = 1; x < 10; x++){
      for (int y = 1; y < 10; y++) {
        g.drawString(y + "", 10, exit.getHeight() + (y+1)*(this.getHeight() - exit.getHeight())/10 - 20);
      }
    }
  }
}
