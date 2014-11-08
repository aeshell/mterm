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
package org.jboss.aesh.mterm.action.menu;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * Menu MenuColorAction class.
 *
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
@SuppressWarnings("serial")
public class MenuColorAction extends MenuBaseAction {

    private JTextArea textArea;

    private static final int BG = 0;
    private static final int FG = 1;

    public MenuColorAction(String text, ImageIcon icon, JTextArea textArea) {
        super(text, icon);
        this.textArea = textArea;
    }

    public void actionPerformed(ActionEvent e) {

        String[] colors = { "<html><font color=default>DEFAULT</font></html>",
            "<html><font color=red>RED</font></html>",
            "<html><font color=green>GREEN</font></html>",
            "<html><font color=blue>BLUE</font></html>",
            "<html><font color=black>BLACK</font></html>",
            "<html><font color=white>WHITE</font></html>",
            "<html><font color=yellow>YELLOW</font></html>" };

        JComboBox<String> bgColor = new JComboBox<String>();
        JComboBox<String> fgColor = new JComboBox<String>();
        for (String c : colors) {
            bgColor.addItem(c);
            fgColor.addItem(c);
        }

        Object[] drops = { "BG:", bgColor, "FG:", fgColor };

        int option = JOptionPane.showConfirmDialog(null,
            drops,
            "Set Color",
            JOptionPane.OK_CANCEL_OPTION);

        if (option == 0) {
            textArea.setBackground(getColor(((JComboBox<String>) drops[1]).getSelectedItem().toString(), BG));
            Color fg = getColor(((JComboBox<String>) drops[3]).getSelectedItem().toString(), FG);
            textArea.setForeground(fg);
            textArea.setCaretColor(fg);
        }
    }

    private Color getColor(String selectedColor, int type) {
        Color c;
        if (type == BG) {
            c = new Color(0, 43, 54);
        }
        else {
            c = new Color(101, 123, 131);
        }
        if (selectedColor.contains("RED")) {
            c = new Color(255, 0, 0);
        }
        else if (selectedColor.contains("GREEN")) {
            c = new Color(0, 128, 0);
        }
        else if (selectedColor.contains("BLUE")) {
            c = new Color(0, 0, 255);
        }
        else if (selectedColor.contains("BLACK")) {
            c = new Color(0, 0, 0);
        }
        else if (selectedColor.contains("WHITE")) {
            c = new Color(255, 255, 255);
        }
        else if (selectedColor.contains("YELLOW")) {
            c = new Color(255, 255, 0);
        }
        return c;
    }
}
