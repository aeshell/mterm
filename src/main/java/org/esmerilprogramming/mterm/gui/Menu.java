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

import java.awt.Desktop;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;

import org.esmerilprogramming.mterm.util.MtermUtil;

/**
 * The menu class.
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

        ((MtermUI) menuBar.getParent().getParent().getParent()).getAesh().stop();

        System.exit(0);
      }
    });

    this.menuBar.getMenu(1).getItem(0).addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection string =
            new StringSelection(((MtermUI) menuBar.getParent().getParent().getParent())
                .getTextArea().getSelectedText());
        clipboard.setContents(string, string);

      }
    });

    this.menuBar.getMenu(1).getItem(1).addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable transferable = clipboard.getContents(Menu.this);
        try {
          String clip = transferable.getTransferData(DataFlavor.stringFlavor).toString();
          ((MtermUI) menuBar.getParent().getParent().getParent()).getTextArea().replaceRange(
              clip,
              ((MtermUI) menuBar.getParent().getParent().getParent()).getTextArea()
                  .getSelectionStart(),
              ((MtermUI) menuBar.getParent().getParent().getParent()).getTextArea()
                  .getSelectionEnd());
        } catch (UnsupportedFlavorException | IOException e) {
          new MessageDialog().error(e.getMessage());
        }
      }
    });

    this.menuBar.getMenu(2).getItem(0).addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        if (!((MtermUI) menuBar.getParent().getParent().getParent()).isFullScreen()) {
          ((JFrame) menuBar.getParent().getParent().getParent())
              .setExtendedState(Frame.MAXIMIZED_BOTH);
          ((MtermUI) menuBar.getParent().getParent().getParent()).setFullScreen(true);
        } else {
          ((JFrame) menuBar.getParent().getParent().getParent()).setExtendedState(Frame.NORMAL);
          ((MtermUI) menuBar.getParent().getParent().getParent()).setFullScreen(false);
        }
      }
    });

    this.menuBar.getMenu(4).getItem(0).addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {

        try {
          ((MtermUI) menuBar.getParent().getParent().getParent())
              .getTextArea()
              .getDocument()
              .remove(
                  0,
                  ((MtermUI) menuBar.getParent().getParent().getParent()).getTextArea()
                      .getDocument().getLength());
          System.out.print(MtermUtil.createPromptString());
        } catch (BadLocationException e) {
          new MessageDialog().error(e.getMessage());
        }

      }
    });

    this.menuBar.getMenu(4).getItem(1).addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {


        String newTitle =
            (String) JOptionPane.showInputDialog(null, "Title:", "Set Title",
                JOptionPane.PLAIN_MESSAGE, null, null, ((JFrame) menuBar.getParent().getParent().getParent()).getTitle());

        if ((newTitle != null) && (!newTitle.isEmpty())) {
          ((JFrame) menuBar.getParent().getParent().getParent()).setTitle(newTitle);
          return;
        }

      }
    });

    this.menuBar.getMenu(5).getItem(0).addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        String url = "https://github.com/EsmerilProgramming/mterm";
        try {
          if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(new URI(url));
          } else {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("firefox -new-window " + url);
          }
        } catch (Exception e) {
          new MessageDialog().error(e.getMessage());
        }
      }
    });

    this.menuBar.getMenu(5).getItem(1).addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) {
          sb.append(" ");
        }
        sb.append("\n-=-=-=-= Mterm =-=-=-=-");
        sb.append("\n\n\nA simple java terminal emulator");
        for (int i = 0; i < 10; i++) {
          sb.append(" ");
        }
        for (int i = 0; i < 5; i++) {
          sb.append("\n");
        }
        sb.append("Created by Helio Frota");
        new MessageDialog().info(sb.toString());
      }
    });

  }

}
