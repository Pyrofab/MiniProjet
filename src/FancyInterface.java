package es.esy.ladysnake.miniprojet.display;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import es.esy.ladysnake.miniprojet.main.Matrice;
import es.esy.ladysnake.miniprojet.main.Personne;
import es.esy.ladysnake.miniprojet.main.Agent;
import es.esy.ladysnake.miniprojet.main.Flotte;

public class FancyInterface extends JFrame {
  private JPanel matrix;
  private JTabbedPane tabs;
  private JButton b1, b2, b3;

  public FancyInterface() {
    super("Matrix Simulator mk3");
    tabs = new JTabbedPane();
    matrix = new JPanel();
    matrix.setLayout(new BorderLayout());
    JTable matrixTable = Matrice.getMatrixView();
    matrixTable.setFillsViewportHeight(true);
    matrixTable.setRowSelectionAllowed(false);
    matrixTable.setColumnSelectionAllowed(false);
    matrix.add(matrixTable);
    tabs.addTab("Matrix", null, matrix, "Affiche la matrice.");
    tabs.addTab("Personnel", null, Personne.getPersonnelView(), "Afficher le personnel");
    tabs.addTab("Flotte", null, Flotte.getFlotteView(), "Affiche votre flotte");
    tabs.addTab("Machines", null, Agent.getPersonnelView(), "Afficher les forces des machines");
    this.setContentPane(tabs);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.pack();
    this.setLocationRelativeTo(null);
    this.setVisible(true);
  }
}
