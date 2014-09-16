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

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import org.esmerilprogramming.mterm.event.AeshAction;
import org.esmerilprogramming.mterm.event.MtermDocListener;
import org.esmerilprogramming.mterm.handler.AeshHandler;

/**
 * Main gui class.
 *
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
@SuppressWarnings("serial")
public final class MtermUI extends JFrame {

  private AeshHandler aesh;
  private JTextArea textArea;

  public MtermUI() {

    setTitle("$mterm");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(730, 500);
    setResizable(false);

    Menu m =
        new Menu().addMenu("File").addMenu("Edit").addMenu("View").addMenu("Search")
            .addMenu("Terminal").addMenu("Help").addSubMenu(0, "New").addSubMenu(0, "Exit");
    setJMenuBar(m.create());

    textArea = new JTextArea(80, 20);
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    textArea.setBackground(new Color(0,43,54));
    Color fg = new Color(101,123,131);
    textArea.setForeground(fg);
    textArea.setCaretColor(fg);
    
    PrintStream printStream = new PrintStream(new MtermOutputStream(textArea));
    System.setErr(printStream);
    System.setOut(printStream);

    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.anchor = GridBagConstraints.WEST;
    gbc.gridx = 1;
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 2;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    add(new JScrollPane(textArea), gbc);

    aesh = new AeshHandler();

    textArea.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "run");
    textArea.getActionMap().put("run", new AeshAction(textArea, aesh));
    textArea.getDocument().addDocumentListener(new MtermDocListener());

    setLocationRelativeTo(null);
    setVisible(true);

    System.out.print("[mterm@localhost ~]$ ");
  }

}
