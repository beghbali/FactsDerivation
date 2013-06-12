
//Title:        Word Tree Node structure
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Bashir Eghbali
//Company:      UCSD
//Description:  Node structures for the word tree representation
package wordStatistics;

public class Word
{
    private String word;
    private int wCount;
    protected Word left;
    protected Word right;

  public Word()
  {
    word = "";
    wCount = 0;
    left = null;
    right = null;
  }
  public Word(String new_word)
  {
    word = new_word;
    wCount = 0;
    left = null;
    right = null;
  }

  public String valueOf()
  {
    return word;
  }

  public int getCount()
  {
    return wCount;
  }

  public int wCompareTo(Word other)
  {
    if(this.valueOf().compareTo(other.valueOf()) == 0)
      return 0;
    else if(this.valueOf().compareTo(other.valueOf()) > 0)
      return 1;
    else
      return -1;
  }

  public static Word lookUpWord(String word, Word current)
  {
    if(current == null)
      return null;
    if(word.compareTo(current.valueOf()) < 0)
      lookUpWord(word, current.left);
    if(word.compareTo(current.valueOf()) > 0)
      lookUpWord(word, current.right);
    if(word.compareTo(current.valueOf()) == 0)
      return current;
    return null;
  }
  public static Word insert(Word new_Word, Word root)
  {
    Word current, previous;

    if(root == null)
      root = new Word("Z");

    current = root;
    previous = root;

    while(current != null)
    {
      if(new_Word.wCompareTo(current) > 0)
      {
         previous = current;
         current = previous.right;
      }
      else if(new_Word.wCompareTo(current) < 0)
      {
        previous = current;
        current = previous.left;
      }
     
    }
    if(new_Word.wCompareTo(previous) < 0)
      previous.left = new_Word;
    else
      previous.right = new_Word;

    return new_Word;
  }
  public void incrementCount()
  {
    wCount++;
  }
}