/*
 * Copyright 2014 EsmerilProgramming.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.esmerilprogramming.mterm.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

import org.esmerilprogramming.mterm.gui.MessageDialog;
import org.esmerilprogramming.mterm.util.MtermUtil;

/**
 * TabAction class.
 *
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
@SuppressWarnings("serial")
public class TabAction extends BaseAction {

  private static final String NEW_LINE = "\n";
  
  public TabAction(JTextArea textArea) {
    this.textArea = textArea;
  }

  public void actionPerformed(ActionEvent ev) {
    try {

      String command = getCommand();
      String commands = "";
      Object[] registeredCommands = aesh.getRegisteredCommands().toArray();

      List<String> filteredCommands = new ArrayList<>();
      if (!command.trim().isEmpty()) {
        for (Object o : registeredCommands) {
          if (o.toString().startsWith(command)) {
            filteredCommands.add(o.toString());
          }
        }
      } else {
        for (Object o : registeredCommands) {
          filteredCommands.add(o.toString());
        }
      }

      int count = 1;
      for (String s : filteredCommands) {
        commands += s + "\u0009";
        if (count % 6 == 0) {
          commands += NEW_LINE;
        }
        count++;
      }
      System.out.print(NEW_LINE);
      System.out.print(commands);
      System.out.print(NEW_LINE);
      System.out.print(MtermUtil.INSTANCE.getPs1());
    } catch (Exception e) {
      new MessageDialog().error(e.getMessage());
    }
  }

}
