
//Title:        Word Statistical Analyzer
//Version:      
//Copyright:    Copyright (c) 1999
//Author:       Bashir Eghbali
//Company:      UCSD
//Description:  Analyzes the statistical word occurances in the given file of abstracts
package wordStatistics;
import java.util.*;
import java.io.*;
import javax.swing.*;

public class wordStatistics
{
  protected static final int UI_SIZE = 100;
  protected static final int PRINT_WORDS = 0;
  protected static final int PRINT_COUNT = 1;
  protected static final int PRINT_WORDS_COUNT = 2;
  protected static final int MAX_WORD_LENGTH = 35;

  private boolean invokedStandalone = false;
  String [] UI;

  public wordStatistics()
  {
    UI = new String[100];
  }
  public wordStatistics(int size)
  {
    UI = new String[size];
  }

  public static void main(String[] args)
  {
    Word root = new Word("Z");
    wordStatistics statistics = new wordStatistics(UI_SIZE);
    statistics.makeBST("proteins.txt", root);
    statistics.writeUI("UI.txt", UI_SIZE);
    statistics.writeWords("Words.txt", root);
    statistics.writeWordsCount("WordsCount.txt", root);
    statistics.writeBoth("Both.txt", root);
    statistics.invokedStandalone = true;
    JOptionPane.showMessageDialog(null,"Done");
  }

  public void makeBST(String filename, Word root)
  {
    String line, stoken="";
    int uiCounter = 0,sum = 0;
    Word current;
    StringTokenizer token;
    boolean found = false;

    root = new Word("Z");
   try
   {
      BufferedReader inputfile = new BufferedReader(new FileReader(filename));
      while((line = inputfile.readLine()) != null)
      {
        if(line.trim().indexOf("UI") != -1)
        {
          UI[uiCounter]=line;
          uiCounter++;
        }
        else
        {
          token = new StringTokenizer(line," .,\t\n");
          sum+=token.countTokens();

          while(token.hasMoreTokens())
          {
            stoken = token.nextToken();
            current = Word.lookUpWord(stoken,root);
            

            if(current != null)
            {      System.out.println("not null");
               current.incrementCount();

            }
            else
            {
              Word temp;
              temp = Word.insert(new Word(stoken),root);
            } 
          }
         }
        }
     inputfile.close();
     
   }
   catch(Exception e)
   {
      System.out.println("an error occured in makeBST(): " + e.getMessage());
   }
  }

  public void writeUI(String filename, int uisize)
  {
    try
    {
      PrintWriter outputfile = new PrintWriter(new FileOutputStream(filename));
      for(int i = 0; i < uisize; i++)
        outputfile.println(UI[i]);
      outputfile.close();
    }
    catch(Exception e)
    {
    System.out.println("an error occured in writeUI(): " + e.getMessage());
    }
  }

  public Word Print(PrintWriter outputfile, int mode, Word root)
  {
    if(root == null)
      return null;

    if(mode == PRINT_WORDS)
      outputfile.println(root.valueOf());
    if(mode == PRINT_COUNT)
      outputfile.println(root.getCount());
    if(mode == PRINT_WORDS_COUNT)
    {
      outputfile.println(root.valueOf());
      int spaces = MAX_WORD_LENGTH - root.valueOf().length();
      for(int i = 0; i < spaces; i++)
        outputfile.println(" ");
      outputfile.println("\t\t" + root.getCount());
    }

    root = Print(outputfile, mode, root.left);

    root = Print(outputfile, mode, root.right);

    return root;
  }
  public void writeWords(String filename, Word root)
  {

    try
    {
      PrintWriter outputfile = new PrintWriter(new FileOutputStream(filename));
      Print(outputfile, 0, root);
      outputfile.close();
    }
    catch(Exception e)
    {
    System.out.println("an error occured in writeWords(): " + e.getMessage());
    }
  }

  public void writeWordsCount(String filename, Word root)
  {
    try
    {
      PrintWriter outputfile = new PrintWriter(new FileOutputStream(filename));
      Print(outputfile, 1, root);
      outputfile.close();
    }
    catch(Exception e)
    {
    System.out.println("an error occured in writeWordsCount(): " + e.getMessage());
    }
  }
  public void writeBoth(String filename, Word root)
  {
  try
    {
      PrintWriter outputfile = new PrintWriter(new FileOutputStream(filename));
      Print(outputfile, 2, root);
      outputfile.close();
    }
    catch(Exception e)
    {
    System.out.println("an error occured in writeBoth(): " + e.getMessage());
    }
  }

}
