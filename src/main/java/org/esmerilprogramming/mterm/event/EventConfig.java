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
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import org.esmerilprogramming.mterm.action.ClearAction;
import org.esmerilprogramming.mterm.action.RunAction;
import org.esmerilprogramming.mterm.action.TabAction;
import org.esmerilprogramming.mterm.filter.MtermNavigationFilter;
import org.esmerilprogramming.mterm.handler.AeshHandler;
import org.esmerilprogramming.mterm.util.MtermUtil;

/**
 * EventConfig configures terminal events.
 *
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
public class EventConfig {

  private JScrollPane scrollPane;
  private JTextArea textArea;
  private AeshHandler aesh;

  public EventConfig(JScrollPane scrollPane, JTextArea textArea, AeshHandler aesh) {
    this.scrollPane = scrollPane;
    this.textArea = textArea;
    this.aesh = aesh;
  }

  @SuppressWarnings("serial")
  public void configure() {
    scrollPane.getActionMap().put("unitScrollDown", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent arg0) {

      }
    });

    scrollPane.getActionMap().put("unitScrollUp", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent arg0) {

      }
    });

    textArea.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "run");
    textArea.getActionMap().put("run", new RunAction(textArea, aesh));
    textArea.getInputMap().put(KeyStroke.getKeyStroke("TAB"), "tab");
    textArea.getActionMap().put("tab", new TabAction(textArea, aesh));
    textArea.getInputMap().put(KeyStroke.getKeyStroke("UP"), "none");
    textArea.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "none");
    textArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK),
        "clear");
    textArea.getActionMap().put("clear", new ClearAction(textArea));
    
    textArea.setNavigationFilter(new MtermNavigationFilter(MtermUtil.createPromptString().length(),
        textArea));
  }

}
