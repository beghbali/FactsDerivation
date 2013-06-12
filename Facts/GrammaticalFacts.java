
//Title:        Grammatical Facts
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Bashir Eghbali
//Company:      UCSD
//Description:  For Organizing Grammatical Facts and Deriving new facts
package Facts;
import Facts.PredicateLogic.*;
import Facts.OntologyInterface.*;
import java.util.StringTokenizer;

public class GrammaticalFacts
{

  public GrammaticalFacts()
  {
  }

  public Predicate[] makePredicateList()
  {
      String message = "";
      String response = "no response";
      String [] pelements;
      StringTokenizer predicates,elements;
      Predicate []predicate;
      int i = 0, j;

      Ontology ontology = new Ontology();
      ontology.connect();
      message = "load ontology";
      ontology.write(message);
      message = "list where pos is verb";
      ontology.write(message);
      response = ontology.read();
      message = "close";
      ontology.write(message);
      ontology.close();

      pelements = new String[3];
      predicates = new StringTokenizer(response);
      predicate = new Predicate[predicates.countTokens()];

      while(predicates.hasMoreTokens())
      {
        elements = new StringTokenizer(predicates.nextToken(":"));
        j = 0;
        while(elements.hasMoreTokens())
        {
          pelements[j] = elements.nextToken(" |'");
          j++;
        }
        predicate[i] = new Predicate(pelements[0],pelements[1],pelements[2]);
        i++;
      }

      return predicate;
  }

}