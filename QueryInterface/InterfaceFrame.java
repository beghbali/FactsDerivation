
//Title:      Query Interface to Biological Database
//Version:
//Copyright:  Copyright (c) 1999
//Author:     Bashir Eghbali
//Company:    UCSD
//Description:This is the query interface for the user to enter query words for search in the biological database
package QueryInterface;

import java.util.StringTokenizer;
import java.util.NoSuchElementException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// import com.borland.jbcl.layout.*;

public class InterfaceFrame extends JFrame
{
  protected String query = "";
  protected String [] queryArray;

  JPanel jPanel1 = new JPanel(new BorderLayout());
  JPanel jPanel2 = new JPanel(new BorderLayout());
  JMenuBar menuBar1 = new JMenuBar();
  JMenu menuFile = new JMenu();
  JMenuItem menuFilePrint = new JMenuItem();
  JMenuItem menuFileExit = new JMenuItem();
  JMenuItem menuFileAbout = new JMenuItem();
  JLabel jLabel1 = new JLabel("Please Enter Queries Seperated By Commas:",JLabel.CENTER);
  JLabel jLabel2 = new JLabel("Search ",JLabel.CENTER);
  ImageIcon image1;
  ImageIcon image2;
  ImageIcon image3;
  JLabel statusBar = new JLabel();

  JPanel jPanel3 = new JPanel();
  JPanel jPanel4 = new JPanel();
  JButton jButton1 = new JButton();
  GridLayout gridLayout1 = new GridLayout();
  JTextField jTextField1 = new JTextField();
  GridBagLayout gridBagLayout1 = new GridBagLayout();

  //Construct the frame
  public InterfaceFrame()
  {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try
    {
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  //Component initialization
  private void jbInit() throws Exception
  {
    jLabel1.setFont(new Font("Helvetica",Font.BOLD,12));
    jLabel2.setFont(new Font("Helvetica",Font.ITALIC,12));
    jLabel2.setForeground(SystemColor.desktop);

    image1 = new ImageIcon(QueryInterface.InterfaceFrame.class.getResource("print.jpg"));
    image2 = new ImageIcon(QueryInterface.InterfaceFrame.class.getResource("about.jpg"));
    image3 = new ImageIcon(QueryInterface.InterfaceFrame.class.getResource("exit.jpg"));
    this.getContentPane().setLayout(new BorderLayout());
    this.setSize(new Dimension(400, 300));
    this.setTitle("Query Interface to Biological Database");
    statusBar.setText("Please Enter query search words to Begin!");
    menuFilePrint.setIcon(image1);
    menuFileAbout.setIcon(image2);
    menuFileExit.setIcon(image3);
    menuFile.setText("File");
    menuFilePrint.setText("Print");
    menuFilePrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.ALT_MASK));
    menuFilePrint.setMnemonic(KeyEvent.VK_P);
    menuFileAbout.setText("About");
    menuFileAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.ALT_MASK));
    menuFileAbout.setMnemonic(KeyEvent.VK_A);

    menuFileAbout.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        fileAbout_actionPerformed(e);
      }
    });
    menuFileExit.setText("Exit");
    menuFileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.ALT_MASK));
    menuFileExit.setMnemonic(KeyEvent.VK_E);

    menuFileExit.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        fileExit_actionPerformed(e);
      }
    });


    jPanel3.setLayout(gridBagLayout1);
    jPanel4.setBorder(BorderFactory.createLineBorder(Color.black));
    jPanel4.setLayout(gridLayout1);
    jButton1.setFont(new java.awt.Font("Dialog", 1, 12));
    jButton1.setText("SEARCH");
    jButton1.setMnemonic(KeyEvent.VK_ENTER);
    jButton1.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        search_actionPerformed(e);
      }
    });
    menuFile.add(menuFilePrint);
    menuFile.add(menuFileAbout);
    menuFile.add(menuFileExit);
    menuBar1.add(menuFile);
    this.setJMenuBar(menuBar1);
    this.getContentPane().add(jPanel1, BorderLayout.NORTH);
    this.getContentPane().add(jPanel2, BorderLayout.WEST);
    jPanel2.add(jLabel2, BorderLayout.WEST);
    this.getContentPane().add(jPanel3, BorderLayout.CENTER);
    jPanel3.add(jLabel1, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanel3.add(jTextField1, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.SOUTHWEST, GridBagConstraints.HORIZONTAL, new Insets(47, 5, 62, 9), 190, 0));
    this.getContentPane().add(statusBar, BorderLayout.SOUTH);
    this.getContentPane().add(jPanel4, BorderLayout.EAST);
    jPanel4.add(jButton1, null);
    jPanel3.add(jTextField1, null);

  }


  //File | About action performed
  public void fileAbout_actionPerformed(ActionEvent e)
  {
    InterfaceFrame_AboutBox dlg = new InterfaceFrame_AboutBox(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.setVisible(true);
  }

  //File | Exit action performed
  public void fileExit_actionPerformed(ActionEvent e)
  {
    System.exit(0);
  }

  //Overridden so we can exit on System Close
  protected void processWindowEvent(WindowEvent e)
  {
    super.processWindowEvent(e);
    if(e.getID() == WindowEvent.WINDOW_CLOSING)
    {
      fileExit_actionPerformed(null);
    }
  }

  public void search_actionPerformed(ActionEvent e)
  {
    boolean parsed = false;

    query = jTextField1.getText();
    query = query.toLowerCase();

    StringTokenizer queries = new StringTokenizer(query,",");
    queryArray = new String[queries.countTokens()];
    
    for(int i =0; !parsed; i++)
    {
      try
      {
          queryArray[i] = queries.nextToken();
          queryArray[i].trim();
      }

      catch(NoSuchElementException nsee)
      {
        parsed = true;
      }
    }
   Search searchFrame = new Search(this,queryArray);

  }
}
