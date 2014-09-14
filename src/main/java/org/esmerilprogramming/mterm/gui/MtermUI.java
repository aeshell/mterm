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

import org.jboss.aesh.cl.CommandDefinition;
import org.jboss.aesh.console.AeshConsole;
import org.jboss.aesh.console.AeshConsoleBuilder;
import org.jboss.aesh.console.Prompt;
import org.jboss.aesh.console.command.Command;
import org.jboss.aesh.console.command.CommandResult;
import org.jboss.aesh.console.command.invocation.CommandInvocation;
import org.jboss.aesh.console.command.registry.AeshCommandRegistryBuilder;
import org.jboss.aesh.console.command.registry.CommandRegistry;
import org.jboss.aesh.console.settings.SettingsBuilder;
import org.jboss.aesh.extensions.cat.Cat;
import org.jboss.aesh.extensions.clear.Clear;
import org.jboss.aesh.extensions.grep.Grep;
import org.jboss.aesh.extensions.groovy.GroovyCommand;
import org.jboss.aesh.extensions.harlem.aesh.Harlem;
import org.jboss.aesh.extensions.less.aesh.Less;
import org.jboss.aesh.extensions.matrix.Matrix;
import org.jboss.aesh.extensions.more.aesh.More;
import org.jboss.aesh.extensions.pwd.Pwd;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Main gui class.
 *
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
public class MtermUI {

    private GridBagLayout gridBagLayout;
    private GridBagConstraints gridBagConstraints;
    private JTextArea textSpace;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    public MtermUI() {
        new MFrame();
    }

    private class MFrame extends JFrame {

        private JPanel panel;

        public MFrame() {
            this.init();
        }

        public void init() {
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

            Menu m = new Menu()
                    .addMenu("File")
                    .addMenu("Edit")
                    .addMenu("View")
                    .addMenu("Search")
                    .addMenu("Terminal")
                    .addMenu("Help")
                    .addSubMenu(0, "New")
                    .addSubMenu(0, "Exit");

            this.setJMenuBar(m.create());


            initTextSpace();
            try {
                initAesh();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            panel.setSize(600, 400);

            JScrollPane scrollPane = new JScrollPane(textSpace);
            scrollPane.setMinimumSize(new Dimension(730, 500));

            panel.add(scrollPane);

            this.add(panel);
            this.setVisible(true);

            try {
                baos.write("pwd".getBytes());
                baos.flush();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void initTextSpace() {
        textSpace = new JTextArea();
        textSpace.setColumns(20);
        textSpace.setRows(80);
        textSpace.setMinimumSize(new Dimension(730, 500));
        JScrollPane scrollPane = new JScrollPane(textSpace);
        textSpace.setEditable(true);
        textSpace.setCaretPosition(0);
    }


    private void initAesh() throws IOException {

        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis = new PipedInputStream(pos);

        SettingsBuilder sb = new SettingsBuilder()
                .inputStream(pis)
                .outputStream(new MtermPrintStream(textSpace, baos));

        CommandRegistry registry = new AeshCommandRegistryBuilder()
                .command(ExitCommand.class)
                .command(Less.class)
                .command(More.class)
                .command(Harlem.class)
                .command(Clear.class)
                .command(Matrix.class)
                .command(GroovyCommand.class)
                .command(Grep.class)
                .command(Cat.class)
                .command(Pwd.class)
                .create();

        AeshConsole aeshConsole = new AeshConsoleBuilder()
                .commandRegistry(registry)
                .settings(sb.create())
                .prompt(new Prompt("$ "))
                .create();

        aeshConsole.start();
    }

    @CommandDefinition(name="exit", description = "exit the program")
    public static class ExitCommand implements Command {

        @Override
        public CommandResult execute(CommandInvocation commandInvocation) throws IOException {
            commandInvocation.stop();
            return CommandResult.SUCCESS;
        }
    }
}
