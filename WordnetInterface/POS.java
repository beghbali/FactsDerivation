
//Title:        Wordnet Interface
//Version:      
//Copyright:    Copyright (c) 1999
//Author:       Bashir Eghbali
//Company:      UCSD
//Description:  Define the "Part Of Speech" constants for use in other classes
//              such as NOUN, VERB, ADJ

package WordnetInterface;

public class POS
{
  public static final String NOUN = "noun";
  public static final String VERB = "verb";
  public static final String ADJ = "adj";
  public static final String ADV = "adv";
  private boolean[] POS;                   //will contain POS for a certain word
  
  /*****************************************************************
   * Ctor:        POS
   * Parameters:  none
   * Description: initializes the POS array and sets its elements to
   *              false(no POS associated with the object)
   * Side Effect: sets the global variable POS
   *****************************************************************/
  public POS()
  {
      POS = new boolean[4];
      POS[0] = POS[1] = POS[2] = POS[3] = false;
  }

  /*****************************************************************
   * Ctor:        POS
   * Parameters:  noun ->if true sets the first element of POS array
   *              to 1, else to 0.
   *              verb ->if true sets the second element of POS array
   *              to 1, else to 0.
   *              adj ->if true sets the third element of POS array
   *              to 1, else to 0.
   *              adv ->if true sets the fourth element of POS array
   *              to 1, else to 0.
   * Description: initializes the POS array and sets its elements to
   *              the arguments noun,verb,adj,adv respectively
   * Side Effect: sets the global variable POS
   *****************************************************************/
  public POS(boolean noun, boolean verb, boolean adj, boolean adv)
  {
      POS = new boolean[4];
      POS[0] = noun;
      POS[1] = verb;
      POS[2] = adj;
      POS[3] = adv;
  }
  /*****************************************************************
   * Function:      getPOS
   * Parameters:    none
   * Returns:       the POS array associated with this object(a
   *                boolean array).
   * Description:   accessor function. Returns the POS array.
   * Side Effects:  none
   *****************************************************************/
  public boolean[] getPOS()
  {
      return this.POS;
  }
  /*****************************************************************
   * Function:      isNoun
   * Parameters:    none
   * Returns:       true if the first entry of the POS array is set
   *                to true(noun associated with the word), else false
   * Description:   returns true if the noun association element of
   *                the POS array(first element) is set to true
   * Side Effects:  none
   *****************************************************************/
  public boolean isNoun()
  {
    if(this.POS[0])
      return true;
    else
      return false;
  }

  /*****************************************************************
   * Function:      isVerb
   * Parameters:    none
   * Returns:       true if the second entry of the POS array is set
   *                to true(verb associated with the word), else false
   * Description:   returns true if the verb association element of
   *                the POS array(second element) is set to true
   * Side Effects:  none
   *****************************************************************/
  public boolean isVerb()
  {
      if(this.POS[1])
        return true;
      else
        return false;
  }

  /*****************************************************************
   * Function:      isAdj
   * Parameters:    none
   * Returns:       true if the third entry of the POS array is set
   *                to true(adjective associated with the word), else
   *                false
   * Description:   returns true if the adjective association element
   *                of the POS array(third element) is set to true
   * Side Effects:  none
   *****************************************************************/
  public boolean isAdj()
  {
    if(this.POS[2])
      return true;
    else
      return false;
  }

  /*****************************************************************
   * Function:      isAdv
   * Parameters:    none
   * Returns:       true if the fourth entry of the POS array is set
   *                to true(adverb associated with the word), else
   *                false
   * Description:   returns true if the adverb association element
   *                of the POS array(fourth element) is set to true
   * Side Effects:  none
   *****************************************************************/
  public boolean isAdv()
  {
    if(this.POS[3])
      return true;
    else
      return false;
  }

  /*****************************************************************
   * Function:      setNoun
   * Parameters:    association -> association of the noun entry of
   *                the POS array.
   * Returns:       nothing
   * Description:   sets the association value of the noun entry
   *                (first entry) in the POS array
   * Side Effects:  sets first element of POS array
   *****************************************************************/
  public void setNoun(boolean association)
  {
    this.POS[0] = association;
  }

  /*****************************************************************
   * Function:      setVerb
   * Parameters:    association -> association of the verb entry of
   *                the POS array.
   * Returns:       nothing
   * Description:   sets the association value of the verb entry
   *                (second entry) in the POS array
   * Side Effects:  sets second element of POS array
   *****************************************************************/
  public void setVerb(boolean association)
  {
    this.POS[1] = association;
  }

  /*****************************************************************
   * Function:      setAdj
   * Parameters:    association -> association of the adjective entry
   *                of the POS array.
   * Returns:       nothing
   * Description:   sets the association value of the adjective entry
   *                (third entry) in the POS array
   * Side Effects:  sets third element of POS array
   *****************************************************************/
  public void setAdj(boolean association)
  {
    this.POS[2] = association;
  }

  /*****************************************************************
   * Function:      setAdv
   * Parameters:    association -> association of the adverb entry
   *                of the POS array.
   * Returns:       nothing
   * Description:   sets the association value of the adverb entry
   *                (fourth entry) in the POS array
   * Side Effects:  sets fourth element of POS array
   *****************************************************************/
  public void setAdv(boolean association)
  {
    this.POS[3] = association;
  }

  /******************************************************************
   * Function:     toString
   * Parameters:   none
   * Returns:      A string representation of this POS object
   * Description:  returns a formatted string version of all the
   *               elements of the POS array and their associative POS
   * Side Effects:  none
   ******************************************************************/
  public String toString()
  {
    return this.NOUN+":"+this.isNoun()+"\n"+
           this.VERB+":"+this.isVerb()+"\n"+
           this.ADJ+":"+this.isAdj()+"\n"+
           this.ADV+":"+this.isAdv()+"\n";
  }

  /******************************************************************
   * Function:     equals
   * Parameters:   other ->the POS object to compare this object to
   * Returns:      true if other equals this object
   * Description:  overwrites the toString method inherited from class
   *               Object. It returns true if all the entries of POS
   *               array of other match the respective entries in this
   *               object's POS array
   * Side Effects:  none
   ******************************************************************/
  public boolean equals(POS other)
  {
    if(this.isNoun() == other.isNoun()
    && this.isVerb() == other.isVerb()
    && this.isAdj() == other.isAdj()
    && this.isAdv() == other.isAdv())
        return true;
    else
      return false;
  }
}
