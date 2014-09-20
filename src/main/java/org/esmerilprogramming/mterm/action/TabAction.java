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

import javax.swing.AbstractAction;
import javax.swing.JTextArea;

import org.esmerilprogramming.mterm.gui.MessageDialog;
import org.esmerilprogramming.mterm.handler.AeshHandler;
import org.esmerilprogramming.mterm.util.MtermUtil;

/**
 * TabAction class.
 *
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
@SuppressWarnings("serial")
public class TabAction extends AbstractAction {

  private AeshHandler aesh;
  private JTextArea textArea;

  public TabAction(JTextArea textArea, AeshHandler aesh) {
    this.textArea = textArea;
    this.aesh = aesh;
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
          commands += "\n";
        }
        count++;
      }
      System.out.print("\n");
      System.out.print(commands);
      System.out.print("\n");
      System.out.print(MtermUtil.createPromptString());
    } catch (Exception e) {
      new MessageDialog().error(e.getMessage());
    }
  }

  private String getCommand() {
    String command = "";
    try {
      int lineOffset = textArea.getLineOfOffset(textArea.getCaretPosition());
      int lineStart = textArea.getLineStartOffset(lineOffset);
      int lineEnd = textArea.getLineEndOffset(lineOffset);
      command = textArea.getText(lineStart, (lineEnd - lineStart));
      command = command.substring(MtermUtil.createPromptString().length());
    } catch (Exception e) {
      new MessageDialog().error(e.getMessage());
    }
    return command;
  }

}
