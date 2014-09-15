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
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

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
  private String PS1 = "[mterm@localhost ~]$ ";

  public AeshAction(JTextArea textArea, AeshHandler aesh) {
    this.textArea = textArea;
    this.aesh = aesh;
  }

  public void actionPerformed(ActionEvent ev) {
    try {
      int lineOffset = textArea.getLineOfOffset(textArea.getCaretPosition());
      int lineStart = textArea.getLineStartOffset(lineOffset);
      int lineEnd = textArea.getLineEndOffset(lineOffset);

      try {

        String command = textArea.getText(lineStart, (lineEnd - lineStart));
        command = command.substring(PS1.length());
        
        if (command.contains("clear")) {
          clear();  
        }
        
        aesh.run(command);
        String result = aesh.getResult();
        result = result.substring(command.length());
        aesh.reset();
        System.out.print(result + PS1);
      } catch (IOException e) {
        e.printStackTrace();
      }

    } catch (BadLocationException ex) {
      System.out.println(ex.getMessage());
    }

  }

  private void clear() {
    try {
      textArea.getDocument().remove(0, textArea.getDocument().getLength());
    } catch (BadLocationException e) {
      e.printStackTrace();
    }
  }
  
}
