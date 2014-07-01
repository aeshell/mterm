/*
 * Copyright 2014 EsmerilProgramming.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.esmerilprogramming.mterm.gui;

import javax.swing.*;

/**
 * The main class.
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

    public JMenuBar get() {
        return this.menuBar;
    }

    //menuItemExit = new JMenuItem("Exit");
    //menuFile.add(menuItemExit);
    //menuItemExit.addActionListener(new ActionListener() {
    //    public void actionPerformed(ActionEvent actionEvent) {
    //        System.exit(0);
    //    }
    //});

}
