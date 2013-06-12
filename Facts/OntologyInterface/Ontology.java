
//Title:        The Ontology Interface
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Bashir Eghbali
//Company:      UCSD
//Description:  This is the interface with the Ontology to get the database of extracted words
package Facts.OntologyInterface;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class Ontology
{
    public static String hostname;
    public static int port;
    public static Socket socket;
    public static PrintWriter socketOut;
    public static BufferedReader socketIn;

  public Ontology()
  {
      hostname = "24.0.128.141";
      port = 12221;
      socket = null;
      socketOut = null;
      socketIn = null;
  }

  public Ontology(String host, int inport)
  {
      this();
      if(host != null)
        hostname = host;
      if(inport >= 0)
        port = inport;
  }

  public boolean connect()
  {
  //Create socket connection
   try
   {
     socket = new Socket(hostname, port);
     socketOut = new PrintWriter(socket.getOutputStream(),true);
     socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
   }
   catch (UnknownHostException e)
   {
     System.out.println("Unknown host: "+ hostname);
     return false;
   }
   catch  (IOException e)
   {
     System.out.println("No I/O");
     return false;
   }
   return true;
  }

  public void write(String message)
  {
      socketOut.print(message);
  }

  public String read()
  {
    String line;

    try
    {
      line = socketIn.readLine();
      System.out.println("Text received: " + line);
   }
   catch (IOException e)
   {
     System.out.println("Read from socket: " + hostname + "on port: "+port+" failed");
     return null;
   }
   return line;
  }

  public void close()
  {
      try
      {
        socketOut.close();
        socketIn.close();
        socket.close();
      }
      catch(IOException ioe)
      {
          System.out.println("an I/O error occured: OntologyInterface->Ontology->close(): " + ioe.getMessage());
      }
  }

}