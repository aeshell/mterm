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

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.jboss.aesh.mterm.action.menu.MenuBaseAction;

/**
 * The menu class.
 *
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
class Menu {

    private JMenuBar menuBar;

    public Menu() {
        menuBar = new JMenuBar();
    }

    void addMenu(String... labels) {
        for (String m : labels) {
            JMenu jm = new JMenu(m);
            jm.setName(m);
            menuBar.add(jm);
        }
    }

    Menu addSubMenu(int index, MenuBaseAction menuAction) {
        JMenuItem jmi = new JMenuItem(menuAction);
        jmi.setName(menuAction.getMenuName());
        menuBar.getMenu(index).add(jmi);
        return this;
    }

    JMenuBar create() {
        return this.menuBar;
    }

}
