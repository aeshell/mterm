/*
 * Copyright 2014 EsmerilProgramming.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
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
                    .addMenu("Help");

            this.setJMenuBar(m.get());

            initTextSpace();
            try {
                initAesh();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            panel.setSize(600, 400);
            panel.add(textSpace);

            this.add(panel);

            this.setVisible(true);
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
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        System.setIn(new ByteArrayInputStream(textSpace.getText().getBytes()));
        SettingsBuilder sb = new SettingsBuilder();
        sb.readInputrc(false);
        sb.inputStream(System.in);
        sb.outputStream(new MtermPrintStream(textSpace, baos));
        sb.logging(true);

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
