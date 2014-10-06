/*
 * JBoss, Home of Professional Open Source Copyright 2014 Red Hat Inc. and/or its affiliates and
 * other contributors as indicated by the @authors tag. All rights reserved. See the copyright.txt
 * in the distribution for a full listing of individual contributors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in
 * writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package org.jboss.aesh.mterm.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.PrintStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jboss.aesh.mterm.action.menu.MenuAboutAction;
import org.jboss.aesh.mterm.action.menu.MenuClearAction;
import org.jboss.aesh.mterm.action.menu.MenuContentsAction;
import org.jboss.aesh.mterm.action.menu.MenuCopyAction;
import org.jboss.aesh.mterm.action.menu.MenuExitAction;
import org.jboss.aesh.mterm.action.menu.MenuFullScreenAction;
import org.jboss.aesh.mterm.action.menu.MenuNewAction;
import org.jboss.aesh.mterm.action.menu.MenuPasteAction;
import org.jboss.aesh.mterm.action.menu.MenuTitleAction;
import org.jboss.aesh.mterm.event.EventConfig;
import org.jboss.aesh.mterm.util.AeshUtil;
import org.jboss.aesh.mterm.util.MtermUtil;

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
    private static final String ICON_PATH = "/org/jboss/aesh/mterm/gui/icons/";

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
        setIconImage(new ImageIcon(getClass().getResource(ICON_PATH + "icon.png")).getImage());
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

    private JMenuBar createMenuBar() {
        Menu m = new Menu();
        m.addMenu("File","Edit","View","Search","Terminal","Help");
        m.addSubMenu(0, new MenuNewAction("New", addIcon("new.png")));
        m.addSubMenu(0, new MenuExitAction("Exit", addIcon("exit.png")));
        m.addSubMenu(1, new MenuCopyAction("Copy", addIcon("copy.png"), textArea));
        m.addSubMenu(1, new MenuPasteAction("Paste", addIcon("paste.png"), textArea));
        m.addSubMenu(2, new MenuFullScreenAction("Full Screen", addIcon("full_screen.png"), this));
        m.addSubMenu(4, new MenuClearAction("Clear", addIcon("clear.png"), textArea));
        m.addSubMenu(4, new MenuTitleAction("Set Title", addIcon("set-title.png"), this));
        m.addSubMenu(5, new MenuContentsAction("Contents", addIcon("content.png")));
        m.addSubMenu(5, new MenuAboutAction("About", addIcon("about.png")));
        return m.create();
    }

    private ImageIcon addIcon(String icon) {
        return new ImageIcon(getClass().getResource(ICON_PATH + icon));
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
