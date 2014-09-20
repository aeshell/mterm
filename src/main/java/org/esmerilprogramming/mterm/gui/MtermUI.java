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
import java.io.IOException;
import java.io.PrintStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.esmerilprogramming.mterm.action.menu.MenuCopyAction;
import org.esmerilprogramming.mterm.action.menu.MenuExitAction;
import org.esmerilprogramming.mterm.action.menu.MenuNewAction;
import org.esmerilprogramming.mterm.action.menu.MenuPasteAction;
import org.esmerilprogramming.mterm.event.EventConfig;
import org.esmerilprogramming.mterm.util.AeshUtil;
import org.esmerilprogramming.mterm.util.MtermUtil;

/**
 * Main gui class.
 *
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
@SuppressWarnings("serial")
public final class MtermUI extends JFrame {

  private JTextArea textArea;
  private JScrollPane scrollPane;
  private boolean fullScreen;
  private static final String ICON_PATH = "/org/esmerilprogramming/mterm/gui/icons/";

  public MtermUI() {
    configureGraphics();
    configureStreams();
    configureEvents();
  }

  private void configureGraphics() {
    setTitle("$mterm");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(730, 505);
    setMinimumSize(new Dimension(320, 150));
    setIconImage(loadImageIcon());
    configureTextArea();
    setJMenuBar(createMenuBar());
    scrollPane = new JScrollPane(textArea);
    setLayout(new GridBagLayout());
    add(scrollPane, configureGridBag());
    setLocationRelativeTo(null);
    setFullScreen(false);
    setVisible(true);
  }

  private GridBagConstraints configureGridBag() {
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
    return gbc;
  }

  private void configureTextArea() {
    textArea = new JTextArea(MtermUtil.INSTANCE.getPs1(), 21, 80);
    textArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    textArea.setBackground(new Color(0, 43, 54));
    Color fg = new Color(101, 123, 131);
    textArea.setForeground(fg);
    textArea.setCaretColor(fg);
  }

  private Image loadImageIcon() {
    Image image = null;
    try {
      image =
          ImageIO.read(this.getClass().getResource("/org/esmerilprogramming/mterm/gui/icon.png"));
    } catch (IOException e) {
      new MessageDialog().error(e.getMessage());
    }
    return image;
  }

  private JMenuBar createMenuBar() {

    ImageIcon iconNew = new ImageIcon(getClass().getResource(ICON_PATH + "new.png"));
    ImageIcon iconExit = new ImageIcon(getClass().getResource(ICON_PATH + "exit.png"));
    ImageIcon iconCopy = new ImageIcon(getClass().getResource(ICON_PATH + "copy.png"));
    ImageIcon iconPaste = new ImageIcon(getClass().getResource(ICON_PATH + "paste.png"));
    ImageIcon iconFullScreen = new ImageIcon(getClass().getResource(ICON_PATH + "full_screen.png"));

    Menu m = new Menu();
    m.addMenu("File");
    m.addMenu("Edit");
    m.addMenu("View");
    m.addMenu("Search");
    m.addMenu("Terminal");
    m.addMenu("Help");
    m.addSubMenu(0, new MenuNewAction("New", iconNew));
    m.addSubMenu(0, new MenuExitAction("Exit", iconExit));
    m.addSubMenu(1, new MenuCopyAction("Copy", iconCopy, textArea));
    m.addSubMenu(1, new MenuPasteAction("Paste", iconPaste, textArea));

    m.addSubMenu(2, "Full Screen",
        new ImageIcon(getClass().getResource(ICON_PATH + "full_screen.png")));
    m.addSubMenu(4, "Clear", new ImageIcon(getClass().getResource(ICON_PATH + "clear.png")));
    m.addSubMenu(4, "Set Title...",
        new ImageIcon(getClass().getResource(ICON_PATH + "set-title.png")));
    m.addSubMenu(5, "Contents", new ImageIcon(getClass().getResource(ICON_PATH + "content.png")));
    m.addSubMenu(5, "About", new ImageIcon(getClass().getResource(ICON_PATH + "about.png")));
    return m.create();
  }

  private void configureEvents() {
    new EventConfig(scrollPane, textArea).configure();
  }

  private void configureStreams() {
    PrintStream printStream = new PrintStream(new MtermOutputStream(textArea));
    System.setErr(printStream);
    System.setOut(printStream);
    AeshUtil.INSTANCE.start(printStream);
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
