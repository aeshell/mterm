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
package org.jboss.aesh.mterm.action;

import java.io.PrintStream;

import javax.swing.JTextArea;

import org.jboss.aesh.mterm.gui.MtermOutputStream;
import org.jboss.aesh.mterm.util.AeshUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
public class TabActionTest {

    private JTextArea textArea;
    private TabAction action;

    @Before
    public void setUp() {
        textArea = new JTextArea();
        PrintStream printStream = new PrintStream(new MtermOutputStream(textArea));
        System.setErr(printStream);
        System.setOut(printStream);
        AeshUtil.INSTANCE.start(printStream);
        action = new TabAction(textArea);
    }

    @Test
    public void testPerform() {
        action.perform();
    }

    @After
    public void tearDown() {
        AeshUtil.INSTANCE.stop();
    }

}
