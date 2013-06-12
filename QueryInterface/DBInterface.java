
//Title:      Query Interface to Biological Database
//Version:    
//Copyright:  Bashir Eghbali, John White, Denise Kashirs Copyright(c) 1999
//Author:     Bashir Eghbali
//Company:    UCSD
//Description:This is the query interface for the user to enter query words for search in the biological database
package QueryInterface;

import javax.swing.UIManager;
import java.awt.*;
import javax.swing.*;



public class DBInterface
{

  boolean packFrame = false;

  //Construct the application
  public DBInterface()
  {
    InterfaceFrame frame = new InterfaceFrame();
    //Validate frames that have preset sizes
    //Pack frames that have useful preferred size info, e.g. from their layout
    if (packFrame)
      frame.pack();
    else
      frame.validate();

    //Center the window
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height)
      frameSize.height = screenSize.height;
    if (frameSize.width > screenSize.width)
      frameSize.width = screenSize.width;
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    frame.setVisible(true);

  }

  //Main method
  public static void main(String[] args)
  {
    try
    {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch(Exception e)
    {
      try
      {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      }
      catch(Exception esys)
      {
         JOptionPane.showMessageDialog(null, "An Error occured while determining the Look-and-Feel Class Name" + esys.getMessage(), "Look-and Feel initialization Error", JOptionPane.ERROR_MESSAGE);
      }
    }

    new DBInterface();
    
  }
}
