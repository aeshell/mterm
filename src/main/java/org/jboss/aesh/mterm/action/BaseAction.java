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

import javax.swing.AbstractAction;
import javax.swing.JTextArea;

import org.jboss.aesh.mterm.gui.MessageDialog;
import org.jboss.aesh.mterm.util.AeshUtil;
import org.jboss.aesh.mterm.util.MtermUtil;

/**
 * BaseAction class.
 * 
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
@SuppressWarnings("serial")
public abstract class BaseAction extends AbstractAction {

    protected AeshUtil aesh = AeshUtil.INSTANCE;
    protected JTextArea textArea;

    protected String getCommand() {
        String command = "";
        try {
            int lineOffset = textArea.getLineOfOffset(textArea.getCaretPosition());
            int lineStart = textArea.getLineStartOffset(lineOffset);
            int lineEnd = textArea.getLineEndOffset(lineOffset);
            command = textArea.getText(lineStart, (lineEnd - lineStart));
            command = command.substring(MtermUtil.INSTANCE.getPs1().length());
        }
        catch (Exception e) {
            new MessageDialog().error(e.getMessage());
        }
        return command;
    }

}
