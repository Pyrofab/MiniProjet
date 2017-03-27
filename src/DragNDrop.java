package es.esy.ladysnake.gui;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

public class DragNDrop extends JFrame {

  private JList<Object> liste1, liste2;
  private JPanel panel;

  public DragNDrop(Object[] equipage){
    panel = new JPanel();
    liste1 = new JList<Object>(equipage);
    liste1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    liste1.setLayoutOrientation(JList.VERTICAL);
    liste1.setVisibleRowCount(-1);
    liste1.setDragEnabled(true);
    liste1.setDropMode(DropMode.INSERT);
    panel.add(liste1);
    this.add(panel);
    this.setSize(400,200);
    this.setResizable(true);
    this.setTitle("DragNDrop");
    this.setVisible(true);
  }

  public void addList(Object[] equipage) {
    liste2 = new JList<Object>(equipage);
    liste2.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    liste2.setLayoutOrientation(JList.VERTICAL);
    liste2.setVisibleRowCount(-1);
    panel.add(liste2);
  }
}
