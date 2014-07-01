/*
 * Copyright 2014 EsmerilProgramming.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.esmerilprogramming.mterm.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Main gui class.
 *
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
public class MtermUI {

    private GridBagLayout gridBagLayout;
    private GridBagConstraints gridBagConstraints;

    public MtermUI() {
        new MFrame();
    }

    private class MFrame extends JFrame {

        private JMenuBar menuBar;
        private JMenu menuFile;
        private JMenu menuEdit;
        private JMenu menuView;
        private JMenuItem menuSearch;
        private JMenuItem menuTerminal;
        private JMenuItem menuHelp;
        private JPanel panel;

        public MFrame() {
            this.initComponents();
        }

        public void initComponents() {
            this.setTitle("$mterm");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(730, 500);
            this.setResizable(false);
            this.setLocationRelativeTo(null);

            gridBagLayout = new GridBagLayout();
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
            gridBagConstraints.insets = new Insets(15, 0, 38, 0);

            panel = new JPanel();
            panel.setLayout(gridBagLayout);

            menuBar = new JMenuBar();
            this.setJMenuBar(menuBar);

            menuFile = new JMenu("File");
            menuBar.add(menuFile);

            menuEdit = new JMenu("Edit");
            menuBar.add(menuEdit);

            menuView = new JMenu("View");
            menuBar.add(menuView);

            menuSearch = new JMenu("Search");
            menuBar.add(menuSearch);

            menuTerminal = new JMenu("Terminal");
            menuBar.add(menuTerminal);

            menuHelp = new JMenu("Help");
            menuBar.add(menuHelp);

            //menuItemExit = new JMenuItem("Exit");
            //menuFile.add(menuItemExit);
            //menuItemExit.addActionListener(new ActionListener() {
            //    public void actionPerformed(ActionEvent actionEvent) {
            //        System.exit(0);
            //    }
            //});

            this.add(panel);
            this.setVisible(true);
        }

    }
}
