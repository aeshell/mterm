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
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.PrintStream;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import org.esmerilprogramming.mterm.Mterm;
import org.esmerilprogramming.mterm.event.MtermDocListener;
import org.esmerilprogramming.mterm.event.RunAction;
import org.esmerilprogramming.mterm.event.TabAction;
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
  private JScrollPane scrollPane;
  private boolean fullScreen;

  public MtermUI() {
    initGraphComponents();
    applyEventsAndStreams();
    System.out.print(Mterm.buildPS1());
  }

  private void initGraphComponents() {
    setTitle("$mterm");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(730, 505);
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
            .addSubMenu(1, "Copy").addSubMenu(1, "Paste").addSubMenu(2, "Full Screen")
            .addSubMenu(5, "Contents").addSubMenu(5, "About");

    setJMenuBar(m.create());

    textArea = new JTextArea(21, 80);
    textArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
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
    gbc.insets = new Insets(1, 1, 1, 1);
    gbc.anchor = GridBagConstraints.WEST;
    gbc.gridx = 1;
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 2;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    scrollPane = new JScrollPane(textArea);
    add(scrollPane, gbc);

    setLocationRelativeTo(null);
    setFullScreen(false);
    setVisible(true);
  }

  private void applyEventsAndStreams() {
    PrintStream printStream = new PrintStream(new MtermOutputStream(textArea));
    System.setErr(printStream);
    System.setOut(printStream);

    aesh = new AeshHandler();

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
    textArea.getInputMap().put(KeyStroke.getKeyStroke("BACK_SPACE"), "none");
    
    textArea.getDocument().addDocumentListener(new MtermDocListener());
  }

  public JTextArea getTextArea() {
    return textArea;
  }

  public boolean isFullScreen() {
    return fullScreen;
  }

  public void setFullScreen(boolean fullScreen) {
    this.fullScreen = fullScreen;
  }

}
