
//Title:        Grammatical and Derived Facts
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Bashir Eghbali
//Company:      UCSD
//Description:  For Organizing Grammatical Facts and Deriving new facts
package Facts;
import Facts.PredicateLogic.*;
import java.io.*;

public class PresentFacts
{

  public PresentFacts()
  {
  }
  /****************************************************************
   * Function:      Print
   * Parameters:    pList ->list of predicates to print to file
   *                filename ->filename of the file to print to
   * Description:   outputs all the predicates in pList to file
   * Side Effect:   modifies the file associated with filename
   *****************************************************************/
  public void Print(Predicate[] pList, String filename)
  {
    PrintWriter gOut = null;
    try
    {
      gOut = new PrintWriter(new FileOutputStream(filename));
    }
    catch(FileNotFoundException e)
    {
      System.out.println("an error occured: " + e.getMessage());
    }

    for(int i = 0; i < pList.length; i++)
      gOut.println(pList[i].toString());
  }
  public static void main(String[] args)
  {
    Predicate []list;
    String []VERBS = {"cause","produce","associate","reduce"};
    String filename = "\\output\\grammaticalFacts.txt";

    PresentFacts presentFacts = new PresentFacts();
    DerivedFacts derivedFacts = new DerivedFacts(VERBS);
    GrammaticalFacts grammaticalFacts = new GrammaticalFacts();
    //gets all grammatical facts from the ontology database
    list = grammaticalFacts.makePredicateList();
    //prints grammatical facts to filename
    presentFacts.Print(list, filename);
    
    presentFacts.invokedStandalone = true;
  }
  private boolean invokedStandalone = false;
}