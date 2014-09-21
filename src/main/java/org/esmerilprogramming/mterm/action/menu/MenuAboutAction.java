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
package org.esmerilprogramming.mterm.action.menu;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import org.esmerilprogramming.mterm.gui.MessageDialog;

/**
 * Menu MenuAboutAction class.
 *
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
@SuppressWarnings("serial")
public class MenuAboutAction extends MenuBaseAction {

  public MenuAboutAction(String text, ImageIcon icon) {
    super(text, icon);
  }

  public void actionPerformed(ActionEvent e) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 20; i++) {
      sb.append(" ");
    }
    sb.append("\n-=-=-=-= Mterm =-=-=-=-");
    sb.append("\n\n\nA simple java terminal emulator");
    for (int i = 0; i < 10; i++) {
      sb.append(" ");
    }
    for (int i = 0; i < 5; i++) {
      sb.append("\n");
    }
    sb.append("Created by Helio Frota");
    new MessageDialog().info(sb.toString());
  }
}
