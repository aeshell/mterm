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

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTextArea;

/**
 * BlockAction class.
 *
 * This action blocks the deletion of the prompt string.
 * 
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
@SuppressWarnings("serial")
public class BlockAction extends AbstractAction {

    private int promptStringLength;

    private Action action;

    /**
     * Parametric constructor initializes this action with prompt string length and <br>
     * a java swing Action.
     * 
     * @param promptStringLength int
     * @param action Action
     */
    public BlockAction(int promptStringLength, Action action) {
        this.promptStringLength = promptStringLength;
        this.action = action;
    }

    public void actionPerformed(ActionEvent ae) {
        if (((JTextArea) ae.getSource()).getCaretPosition() > promptStringLength) {
            action.actionPerformed(null);
        }
    }
}
