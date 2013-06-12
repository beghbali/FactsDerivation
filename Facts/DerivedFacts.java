
//Title:        Derived Facts
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Bashir Eghbali
//Company:      UCSD
//Description:  For Organizing and Deriving new facts

package Facts;
import Facts.OntologyInterface.*;
import WordnetInterface.*;
import Facts.PredicateLogic.*;
import java.util.*;

public class DerivedFacts
{
    private static String[] relations;      //fixed relations to observe
    private Predicate[] dPredicates;        //derived predicates

  public DerivedFacts()
  {
      relations = null;
  }

  public DerivedFacts(String[] verbs)
  {
      relations = verbs;
  }
  //derives facts
  public Predicate[][] derive(Predicate[] gPredicates) //gPredicates: grammatical predicates
  {
      Predicate [][]database;
      database =  new Predicate[relations.length][];

      for(int i = 0; i < relations.length; i++)
      {
        database[i] = findPredicate(gPredicates, relations[i]);
        database[i] = HypoSyllogism(database[i]);
      }

     return database;
  }
  //find the predicates that have the relation pName in list
  public Predicate[] findPredicate(Predicate[] list, String pName)
  {
      Vector pPredicates = new Vector(5,3);
      WordnetInterface wn = new WordnetInterface();
      String []synonyms = wn.synonym(pName, POS.VERB);
      for(int i = 0; i < list.length; i++)
      {
        for(int j = 0; j < synonyms.length; j++)
        {
          if(list[i].getName() == pName ||
             list[i].getName() == synonyms[j])
          {
            pPredicates.add(list[i]);
          }
        }
      }

      return VectorToPredicate(pPredicates);
  }
  //converts Vector to Predicate[]
  public Predicate[] VectorToPredicate(Vector vector)
  {
    Predicate []predicate = new Predicate[vector.size()];

    for(int i = 0; i < vector.size(); i++)
        predicate[i] = (Predicate)vector.elementAt(i);

    return predicate;
  }

  public Predicate[] HypoSyllogism(Predicate[] list)
  {
    Vector newlist;
    //recursively loop on object of each predicate with the rest of
    //the predicates in list if anything returned add it to a vector
    //if not it comes back to the previous level of recursion and goes
    //to the next predicate. At the end returns calling VectorToPredicate
    //the new predicate list
    return null;
  }


}