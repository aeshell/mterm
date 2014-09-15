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
package org.esmerilprogramming.mterm.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

/**
 * The menu class.
 *
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
public class Menu {

  private JMenuBar menuBar;

  public Menu() {
    menuBar = new JMenuBar();
  }

  public Menu addMenu(String m) {
    menuBar.add(new JMenu(m));
    return this;
  }

  public Menu addSubMenu(int index, String sm) {
    menuBar.getMenu(index).add(new JMenuItem(sm));
    return this;
  }

  public JMenuBar create() {
    registerActions();
    return this.menuBar;
  }

  private void registerActions() {
    this.menuBar.getMenu(0).getItem(0).addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        SwingUtilities.invokeLater(new Runnable() {
          @Override
          public void run() {
            new MtermUI();
          }
        });
      }
    });

    this.menuBar.getMenu(0).getItem(1).addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        System.exit(0);
      }
    });
  }

}
