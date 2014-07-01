/*
 * Copyright 2014 EsmerilProgramming.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.esmerilprogramming.mterm.gui;

import java.io.*;
import javax.swing.*;

/**
 * The main class.
 *
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
public class MtermPrintStream extends PrintStream {

    private JTextArea jTextArea;

    public MtermPrintStream(JTextArea jTextArea, OutputStream os) {
        super(os);
        this.jTextArea = jTextArea;
    }

    public void println(String s) {
        jTextArea.append(s + "\n");
    }

    public void print(String s) {
        jTextArea.append(s);
    }
}
