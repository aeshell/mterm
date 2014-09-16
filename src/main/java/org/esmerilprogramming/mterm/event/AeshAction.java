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
package org.esmerilprogramming.mterm.event;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

import org.esmerilprogramming.mterm.Mterm;
import org.esmerilprogramming.mterm.gui.MessageDialog;
import org.esmerilprogramming.mterm.handler.AeshHandler;

/**
 * AeshAction class.
 *
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
@SuppressWarnings("serial")
public class AeshAction extends AbstractAction {

  private AeshHandler aesh;
  private JTextArea textArea;
  
  public AeshAction(JTextArea textArea, AeshHandler aesh) {
    this.textArea = textArea;
    this.aesh = aesh;
  }

  public void actionPerformed(ActionEvent ev) {
    try {

      String command = getCommand();
      String result = "";

      if (!command.contains("clear")) {
        aesh.run(command);
        result = aesh.getResult();
        result = result.substring(command.length());
        aesh.reset();
      } else {
        clear();
      }

      System.out.print(result + Mterm.buildPS1());

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
      command = command.substring(Mterm.buildPS1().length());
    } catch (Exception e) {
      new MessageDialog().error(e.getMessage());
    }
    return command;
  }

  private void clear() {
    try {
      textArea.getDocument().remove(0, textArea.getDocument().getLength());
    } catch (BadLocationException e) {
      new MessageDialog().error(e.getMessage());
    }
  }

}
