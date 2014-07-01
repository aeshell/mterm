/*
 * Copyright 2014 EsmerilProgramming.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.esmerilprogramming.mterm.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
