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
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;
import java.io.PrintStream;

import javax.imageio.ImageIO;
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

  private Boolean fullScreen;

  public MtermUI() {
    initGraphComponents();
    applyEventsAndStreams();
    System.out.print("[mterm@localhost ~]$ ");
  }

  private void initGraphComponents() {
    setTitle("$mterm");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(730, 500);
    setMinimumSize(new Dimension(320, 150));

    Image image = null;
    try {
      image =
          ImageIO.read(this.getClass().getResource("/org/esmerilprogramming/mterm/gui/icon.png"));
    } catch (IOException e) {
      new MessageDialog().error(e.getMessage());
    }
    setIconImage(image);

    Menu m =
        new Menu().addMenu("File").addMenu("Edit").addMenu("View").addMenu("Search")
            .addMenu("Terminal").addMenu("Help").addSubMenu(0, "New").addSubMenu(0, "Exit")
            .addSubMenu(2, "Full Screen");

    setJMenuBar(m.create());

    textArea = new JTextArea(80, 20);
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    textArea.setBackground(new Color(0, 43, 54));
    Color fg = new Color(101, 123, 131);
    textArea.setForeground(fg);
    textArea.setCaretColor(fg);

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

    setLocationRelativeTo(null);
    setFullScreen(false);
    setVisible(true);
  }

  private void applyEventsAndStreams() {
    PrintStream printStream = new PrintStream(new MtermOutputStream(textArea));
    System.setErr(printStream);
    System.setOut(printStream);

    aesh = new AeshHandler();

    textArea.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "run");
    textArea.getActionMap().put("run", new AeshAction(textArea, aesh));
    textArea.getDocument().addDocumentListener(new MtermDocListener());
  }

  public Boolean isFullScreen() {
    return fullScreen;
  }

  public void setFullScreen(Boolean fullScreen) {
    this.fullScreen = fullScreen;
  }

}
