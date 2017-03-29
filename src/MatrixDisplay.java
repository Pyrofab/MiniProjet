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
      System.out.print("l");
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
    g.setColor(Color.white);
    g.fillRect(0,0,this.getWidth(), this.getHeight());
    g.setColor(Color.red);
    g.fillRect(0,0,200,200);
  }
}
