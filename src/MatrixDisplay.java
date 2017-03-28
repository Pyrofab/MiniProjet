package es.esy.ladysnake.miniprojet.display;

import es.esy.ladysnake.miniprojet.main.Matrice;
import javax.swing.JPanel;
import java.awt.Graphics;

public class MatrixDisplay extends JPanel {
  private Matrice matrix;

  public MatrixDisplay(Matrice mat) {
    this.matrix = mat;
  }

  @Override
  public void paintComponent(Graphics g) {
    g.drawRect(0,0,20,20);
  }
}
