
//Title:        Wordnet Interface
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Bashir Eghbali
//Company:      UCSD
//Description:  An interface to Wordnet to get some of Wordnet's outputs in a parsable format.
//Includes accessor functions for:
//synonyms
//antonyms
//holonyms         <not implemented>
//hierarchical meronyms    <not implemented>
//type recognition(e.g verb, noun)
//hypernyms          <not implemented>
//hierarchical hyponym   <not implemented>

package WordnetInterface;
import java.io.*;
import java.util.*;


public class WordnetInterface
{
  private static String OS;                           //for OS recognition
  private static final String UNIX = "UNIX";
  private static final String SUNOS = "SUNOS";
  private static final String WINDOWS = "WINDOWS";
  private static String FILENAME = "wntemp.txt";      //default filename to
                                                      //write WordNet output to
  /*****************************************************************
   * Ctor:        WordnetInterface
   * Description: Initializes the static variable OS to the right
   *              operating system of the running platform
   * Side Effects: sets the static variable OS
   *****************************************************************/
  public WordnetInterface()
  {
  if(System.getProperties().getProperty("os.name").toUpperCase().indexOf(UNIX) != -1)
    OS = UNIX;
  if(System.getProperties().getProperty("os.name").toUpperCase().indexOf(SUNOS) != -1)
    OS = SUNOS;
  else if (System.getProperties().getProperty("os.name").toUpperCase().indexOf(WINDOWS) != -1)
    OS = WINDOWS;
  else
    System.out.println("error: " + System.getProperties().getProperty("os.name") + " is an unsupported system.");
  }

  /*****************************************************************
   * Function:    synonym
   * Parameters:  word->the word to find the synonyms for
   *              type->the type or Part of Speach(POS) of word
   * Returns:     A string array consisting of all synonyms of all
   *              senses for word
   * Description: This function executes Wordnet with appropriate
   *              arguments to find the synonyms for the word specified
   *              for its given type. It then returns a string array of
   *              all sysnonyms to word from all senses.
   * Side Effects:none
   * calls:       WordnetInterface.WordnetInterface.VectorToString()
   *****************************************************************/
  public String[] synonym(String word, String type)
  {
    BufferedReader wntemp;
    Vector Synonyms;                            //vector of synonyms
    String line;
    String synonym = " -syns";
    boolean new_sense = false;

    Synonyms = new Vector(10,5);
    //changes the argument to wordnet according to the POS
    if(type.compareToIgnoreCase(POS.NOUN) == 0)
      synonym += "n";
    if(type.compareToIgnoreCase(POS.VERB) == 0)
      synonym += "v";
    if(type.compareToIgnoreCase(POS.ADJ) == 0)
      synonym += "a";
    if(type.compareToIgnoreCase(POS.ADV) == 0)
      synonym += "r";

    //gets the information from Wordnet and write it to file
    this.writeToTempFile(word+synonym, null);

     try
     {
        wntemp = new BufferedReader(new FileReader(FILENAME));

        //reads output of wordnet from file until the end-of-file reached
        while((line = wntemp.readLine()) != null)
        {
          //if it finds the word(heading) "sense" it will start parsing it
          if(line.trim().startsWith("Sense"))
          {
            new_sense = true;
            continue;
          }
          if(new_sense)
          {
            //parses the synonyms into tokens and adds to vector
             while(line != null && !(line.trim().startsWith("Sense")))
             {
                StringTokenizer token = new StringTokenizer(line,", =>");
                while(token.hasMoreTokens())
                  Synonyms.add(token.nextToken());
                line = wntemp.readLine();
              }
              new_sense = false;
          }

        }
        wntemp.close();
        this.deleteFile(FILENAME);       //removes the temp file
      }
     catch(FileNotFoundException fnfe)
     {
        System.out.println("an error occured: WordnetInterface -> WordnetInterface ->synonym(): " + fnfe.getMessage());
     }
     catch(IOException ioe)
     {
       System.out.println("an error occured: WordnetInterface -> WordnetInterface ->synonym(): " + ioe.getMessage());
     }

    //returns a string array of strings(synonyms)
    return this.VectorToString(Synonyms);
  }

  /*****************************************************************
   * Function:    antonym
   * Parameters:  word->the word to find the antonyms for
   *              type->the type or Part of Speach(POS) of word
   * Returns:     A string array consisting of all antonyms of all
   *              senses for word
   * Description: This function executes Wordnet with appropriate
   *              arguments to find the antonyms for the word specified
   *              for its given type. It then returns a string array of
   *              all antonyms to word from all senses.
   * Side Effects:none
   * calls:       WordnetInterface.WordnetInterface.VectorToString()
   *****************************************************************/
  public String[] antonym(String word, String type)
  {
    BufferedReader wntemp;
    Vector Antonyms;                            //vector of antonyms
    String line,sToken;
    String antonym = " -ants";
    boolean new_sense = false;

    Antonyms = new Vector(10,5);
    //changes the argument to wordnet according to the POS
    if(type.compareToIgnoreCase(POS.NOUN) == 0)
      antonym += "n";
    if(type.compareToIgnoreCase(POS.VERB) == 0)
      antonym += "v";
    if(type.compareToIgnoreCase(POS.ADJ) == 0)
      antonym += "a";
    if(type.compareToIgnoreCase(POS.ADV) == 0)
      antonym += "r";

    //gets the information from Wordnet and write it to file
    this.writeToTempFile(word+antonym, null);

      try
     {
        wntemp = new BufferedReader(new FileReader(FILENAME));

        //reads output of wordnet from file until the end-of-file reached
        while((line = wntemp.readLine()) != null)
        {
          //if it finds the word(heading) "sense" it will start parsing it
          if(line.trim().startsWith("Sense"))
          {
            new_sense = true;
            continue;
          }
          if(new_sense)
          {
            //parses the synonyms into tokens and adds to vector
             while(line != null && !(line.trim().startsWith("Sense"))
                    && !(line.trim().startsWith("INDIRECT")))
             {
                StringTokenizer token = new StringTokenizer(line,", =>");
                while(token.hasMoreTokens())
                {
                   sToken = token.nextToken();
                   //skips the word itself and if mentioned in ()s
                   if(sToken.compareToIgnoreCase(word) != 0
                      && sToken.indexOf('(')== -1
                      && sToken.indexOf(')') == -1)
                      {
                        if(sToken.startsWith("-"))   //if - left from arrow
                          Antonyms.add(sToken.substring(1));
                        else
                          Antonyms.add(sToken);
                       }
                }
                line = wntemp.readLine();
              }
              new_sense = false;
          }

        }
        wntemp.close();
        this.deleteFile(FILENAME);       //removes the temp file
      }
     catch(FileNotFoundException fnfe)
     {
        System.out.println("an error occured: WordnetInterface -> WordnetInterface ->antonym(): " + fnfe.getMessage());
     }
     catch(IOException ioe)
     {
       System.out.println("an error occured: WordnetInterface -> WordnetInterface ->antonym(): " + ioe.getMessage());
     }

    //returns a string array of strings(synonyms)

    return this.VectorToString(Antonyms);
  }

  /******************************************************************
   * Function:      VectorToString
   * Paramters:     vector->the vector to be converted into an array
   *                of strings
   * Returns:       A String array representative of vector.
   * Description:   This function converts each element of the vector
   *                into a String object using the toString() method
   *                and returns an array of these strings.
   * Side Effects:  none
   ******************************************************************/
  public static String[] VectorToString(Vector vector)
  {
    String []sArray = new String[vector.size()];
    for(int i = 0; i < vector.size(); i++)
      sArray[i] = vector.elementAt(i).toString();

   return sArray;
   }

  /******************************************************************
   * Function:      whatIs
   * Paramters:     word ->the word to detemine the Part Of Speech for
   * Returns:       A POS object that has its POS boolean array set to
   *                right boolean values according to what parts of
   *                speech the word is.
   * Description:   This function calls Wordnet with the right command
   *                for the given word and reads the Wordnet output
   *                from file and if there are information available
   *                for any POS then it will create a POS object with
   *                its internal POS array having 'true' values for the
   *                POS index that information was found for. The POS's
   *                internal array has size 4. first one is an indicative
   *                of POS.NOUN, second one POS.VERB, third one POS.ADJ
   *                and fourth one POS.ADV.
   * Side Effects:  none
   ******************************************************************/
   public POS whatIs(String word)
   {
      BufferedReader wntemp;
      String line;
      POS pos = new POS();

      //gets the information from Wordnet and write it to file
      this.writeToTempFile(word, null);

      try
      {
        wntemp = new BufferedReader(new FileReader(FILENAME));

        //reads output of wordnet from file until the end-of-file reached
        while((line = wntemp.readLine()) != null)
        {
            if(line.trim().startsWith("Information available"))
            {
                if(line.indexOf("noun " + word) != -1)
                    pos.setNoun(true);
                else if(line.indexOf("verb " + word) != -1)
                    pos.setVerb(true);
                else if(line.indexOf("adj " + word) != -1)
                    pos.setAdj(true);
                else if(line.indexOf("adv " + word) != -1)
                    pos.setAdv(true);
             }
         }
         wntemp.close();
         this.deleteFile(FILENAME);         //removes the temp file
      }
     catch(FileNotFoundException fnfe)
     {
        System.out.println("an error occured: WordnetInterface -> WordnetInterface ->whatIs(): " + fnfe.getMessage());
     }
     catch(IOException ioe)
     {
       System.out.println("an error occured: WordnetInterface -> WordnetInterface ->whatIs(): " + ioe.getMessage());
     }

     return pos;
   }

  /******************************************************************
   * Function:      writeToTempFile
   * Paramters:     wnCommand->the command argument to call Wordnet
   *                with
   *                filename->the filename to write the Wordnet output
   *                to
   * Returns:       nothing
   * Description:   This function calls Wordnet with wnCommand and
   *                redirects the output to the given filename if
   *                filename is not given, assumes the default FILENAME
   *                it also formats the call to Wordnet differenty,
   *                depending on the operating system(UNIX and Windows
   *                supported).
   * Side Effects:  modifies filename or if not given FILENAME
   ******************************************************************/
  protected void writeToTempFile(String wnCommand, String filename)
  {
      Process command = null;

      if(filename != null)
        FILENAME = filename;

        try
      {
        Runtime runtime = Runtime.getRuntime();

        //calls wordnet using system commands depending on the platform
        //assumes /bin/sh to be a pathname for a shell
        if(OS == UNIX || OS == SUNOS)
        {
          String []unixcmd = {"/bin/sh","wn",wnCommand,">",FILENAME};
          command = runtime.exec(unixcmd);
        }
        if(OS == WINDOWS)
        {
          String cmd = "command.com /c wn " + wnCommand + " > " + FILENAME;
          command = runtime.exec(cmd);
        }

        command.waitFor();

         //checks to makesure the command was executed successfully
        if(command.exitValue() != 0)
          System.exit(1);
        command.destroy();
      }
     catch(IOException ioe)
     {
       System.out.println("an error occured: WordnetInterface -> WordnetInterface ->synonym() -> writeToTempFile(): " + ioe.getMessage());
     }
     catch(InterruptedException ie)
     {
       System.out.println("an error occured: WordnetInterface -> WordnetInterface ->synonym() -> writeToTempFile(): " + ie.getMessage());
     }
  }

  /******************************************************************
   * Function:      deleteFile
   * Parameters:    filename ->name of the file to delete
   * Returns:       true if the file associated with filename was
   *                deleted successfully, else returns false
   * Description:   deletes the file associated with filename and
   *                returns true if this operation was successful and
   *                false otherwise. Inteded for deleting temp files.
   * Side Effects:  deletes the file associated with filename
   ******************************************************************/
  protected boolean deleteFile(String filename)
  {
      File f = new File(filename);
      return f.delete();
  }

  /******************************************************************
   * Function:     toString
   * Parameters:   none
   * Returns:      A string representation of this WordnetInterface
   *               object
   * Description:  returns a formatted string description of the
   *               class name, description, Operating System
   *               detected and default temp file used by the class
   * Side Effects: none
   ******************************************************************/
  public String toString()
  {
      return  "\nClass: WordnetInterface\n"+
              "Description: A Java interface with Wordnet to " +
              "retreive information like: synonyms, antonyms, "+
              "POS(Part Of Speech) in a parsable format." +
              "\nDefault temp file: " + FILENAME +
              "\nConfigured for: " + OS + "\nAuthor: Bashir Eghbali\n";
  }

  //examples of how to use these functions
 /* public static void main(String argv[])
  {

    WordnetInterface  wn = new WordnetInterface();
    //find synonyms for "DNA"
    String []DNA_Syns = wn.synonym("DNA", POS.NOUN);
    //find antonyms for "cellular"
    String []cellular_Anto = wn.antonym("cellular",POS.ADJ);
    //print out the result nicely
    System.out.println("DNA: ");
    for(int i = 0; i < DNA_Syns.length; i++)
      System.out.println("\t"+DNA_Syns[i]);
    System.out.println("\n");
    System.out.println("cellular: ");
    for(int i = 0; i < cellular_Anto.length; i++)
      System.out.println("\t"+cellular_Anto[i]);
    System.out.println();
    //find what POSs are associated with "phagocytic"
    //you can just assign the returned POS from whatIs to a local var
    //and call the isNoun(),isVerb(), isAdj() or isAdv() of it so
    //you won't call whatIs() multiple times
    System.out.println("isNoun(phagocytic) = "+wn.whatIs("phagocytic").isNoun());
    System.out.println("isVerb(phagocytic) = "+wn.whatIs("phagocytic").isVerb());
    System.out.println("isAdj(phagocytic) = " +wn.whatIs("phagocytic").isAdj());
    System.out.println("isAdv(phagocytic) = " +wn.whatIs("phagocytic").isAdv());
    System.out.println(wn.toString());
    JOptionPane.showMessageDialog(null, "done");
  }            */
}