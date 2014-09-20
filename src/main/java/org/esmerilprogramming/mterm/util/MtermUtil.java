package org.esmerilprogramming.mterm.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.esmerilprogramming.mterm.gui.MessageDialog;

public class MtermUtil {

  public static String createPromptString() {
    String ps1 = "[" + System.getProperty("user.name") + "@";
    try {
      ps1 += InetAddress.getLocalHost().getHostName().split("\\.")[0] + " ~]$ ";
    } catch (UnknownHostException e) {
      new MessageDialog().error(e.getMessage());
    }
    return ps1;
  }
  
}
