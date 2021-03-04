package com.holo.nexushelper.util;

import javax.accessibility.AccessibleContext;
import javax.swing.*;

import com.holo.nexushelper.reference.Wrapper;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Connect {
  private static final String SERVER_HOST = "78.46.68.235";
  private static final int SERVER_PORT = 4010;
  private static Socket clientSocket;
  private static Scanner inMessage;
  private static PrintWriter outMessage;

  public Connect()
  {
    try {
      clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
      inMessage = new Scanner(clientSocket.getInputStream());
      outMessage = new PrintWriter(clientSocket.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    
    new Thread("ngriefchat") {
		@Override
		public void run() {
			getMessage();
		}
    	
    }.start();
  }
 
  public static void sendMessage(String command)
  {
	  outMessage.println(command);
	  outMessage.flush();
  }
  
  private static void getMessage()
  {
	  try 
	  {
		  while (true)
		  {
			  if (inMessage.hasNext()) 
			  {
				  String message = inMessage.nextLine();
				  Wrapper.INSTANCE.player().sendMessage((ITextComponent)new TextComponentTranslation(message));
			  }
		  }
	  }
	  catch (Exception e) 
	  {
	  }
  }
}
