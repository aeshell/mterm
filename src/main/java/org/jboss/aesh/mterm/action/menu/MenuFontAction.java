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

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * Menu MenuFontAction class.
 *
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
@SuppressWarnings("serial")
public class MenuFontAction extends MenuBaseAction {

    private JTextArea textArea;

    public MenuFontAction(String text, JTextArea textArea) {
        super(text);
        this.textArea = textArea;
    }

    public void actionPerformed(ActionEvent e) {

        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        String selectedFont = (String) JOptionPane.showInputDialog(null,
            "Font:",
            "Set Font",
            JOptionPane.INFORMATION_MESSAGE,
            null,
            fonts,
            fonts[0]);
        
        Font currentFont = textArea.getFont();
        textArea.setFont(new Font(selectedFont, currentFont.getStyle(), currentFont.getSize()));

    }
}
