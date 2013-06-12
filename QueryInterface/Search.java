
//Title:        Search utility for searching the Biological Database
//Version:
//Copyright:    Bashir Eghbali, John White, Denise Kashirs Copyright (c) 1999
//Author:       Bashir Eghbali
//Company:      UCSD
//Description:  This is the search utility that searches for the query words in the biological database
package QueryInterface;

import javax.swing.*;
import java.awt.*;

public class Search
{
  private static String [] Query;
  JLabel statusBar = new JLabel();

  public Search()
  {
    statusBar.setText("Please wait, searching...");

  }

  public Search(JFrame frame, String [] queryArray)
  {
    this();
    frame.getContentPane().removeAll();
    frame.repaint();
    frame.getContentPane().add(statusBar,BorderLayout.CENTER);

    Query = queryArray;
  }
}