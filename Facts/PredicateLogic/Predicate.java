
//Title:        Predicate
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Bashir Eghbali
//Company:      UCSD
//Description:  class for presenting predicates
package Facts.PredicateLogic;

public class Predicate
{

  private String name;
  private String object;
  private String subject;

  public Predicate()
  {
    name = "";
    object = "";
    subject = "";
  }

  public Predicate(String inName, String inObject)
  {
      this();
      name = inName;
      object = inObject;
  }

  public Predicate(String inName, String inObject, String inSubject)
  {
      this();
      name = inName;
      object = inObject;
      subject = inSubject;
  }
  /*****************************************************************
   * Function:      toString
   * Parameters:    none
   * returns:       A string representation of this predicate
   * Description:   returns a string representation of what this
   *                predicate and its one/two operands represent
   *****************************************************************/
  public String toString()
  {
      return object + " " + name + " " + subject;
  }

  /*****************************************************************
   * Function:      equals
   * Parameters:    other ->the other Predicate object to compare to
   * returns:       true if the other Predicate has the same name,
   *                otherwise false
   * Description:   if the other predicate has the same name(predicate)
   *                then it will return true, otherwise false.
   *****************************************************************/
  public boolean equals(Predicate other)
  {
    if(this.name == other.name)
      return true;
    else
      return false;
  }

  public String getName()
  {
    return name;
  }

  public String getObject()
  {
    return object;
  }

  public String getSubject()
  {
    return subject;
  }

}