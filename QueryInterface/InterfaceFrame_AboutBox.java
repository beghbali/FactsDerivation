
//Title:      Query Interface to Biological Database
//Version:    
//Copyright:  Copyright (c) 1999
//Author:     Bashir Eghbali
//Company:    UCSD
//Description:This is the query interface for the user to enter query words for search in the biological database
package QueryInterface;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class InterfaceFrame_AboutBox extends JDialog implements ActionListener
{

  JPanel panel1 = new JPanel();
  JPanel panel2 = new JPanel();
  JPanel insetsPanel1 = new JPanel();
  JPanel insetsPanel2 = new JPanel();
  JPanel insetsPanel3 = new JPanel();
  JButton button1 = new JButton();
  JLabel imageControl1 = new JLabel();
  ImageIcon imageIcon;
  JLabel label1 = new JLabel();
  JLabel label2 = new JLabel();
  JLabel label3 = new JLabel();
  JLabel label4 = new JLabel();
  JLabel label5 = new JLabel();
  GridLayout gridLayout1 = new GridLayout();
  String product = "Query Interface to Biological Database";
  String version = "Version 1.0";
  String authors = "Authors: Bashir Eghbali, John White, Denise Kashirs";
  String copyright = "Copyright (c) 1999";
  String comments = "This is the query interface for the user to enter query words for search in the biological database. The results of the search are then displayed in the appropriate format.";
  public InterfaceFrame_AboutBox(Frame parent)
  {
    super(parent);
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try
    {
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    //imageControl1.setIcon(imageIcon);
    pack();
  }

  private void jbInit() throws Exception
  {
    //imageIcon = new ImageIcon(getClass().getResource("your image name goes here"));
    this.setTitle("About");
    setResizable(false);
    panel1.setLayout(new BorderLayout());
    panel2.setLayout(new BorderLayout());
    insetsPanel1.setLayout(new FlowLayout());
    insetsPanel2.setLayout(new FlowLayout());
    insetsPanel2.setBorder(new EmptyBorder(10, 10, 10, 10));
    gridLayout1.setRows(4);
    gridLayout1.setColumns(1);
    label1.setText(product);
    label2.setText(version);
    label3.setText(authors);
    label4.setText(copyright);
    label5.setText(comments);
    insetsPanel3.setLayout(gridLayout1);
    insetsPanel3.setBorder(new EmptyBorder(10, 60, 10, 10));
    button1.setText("OK");
    button1.addActionListener(this);
    insetsPanel2.add(imageControl1, null);
    panel2.add(insetsPanel2, BorderLayout.WEST);
    this.getContentPane().add(panel1, null);
    insetsPanel3.add(label1, null);
    insetsPanel3.add(label2, null);
    insetsPanel3.add(label3, null);
    insetsPanel3.add(label4, null);
    panel2.add(insetsPanel3, BorderLayout.CENTER);
    insetsPanel1.add(button1, null);
    panel1.add(insetsPanel1, BorderLayout.SOUTH);
    panel1.add(panel2, BorderLayout.NORTH);
  }

  protected void processWindowEvent(WindowEvent e)
  {
    if(e.getID() == WindowEvent.WINDOW_CLOSING)
    {
      cancel();
    }
    super.processWindowEvent(e);
  }

  void cancel()
  {
    dispose();
  }

  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource() == button1)
    {
      cancel();
    }
  }
}