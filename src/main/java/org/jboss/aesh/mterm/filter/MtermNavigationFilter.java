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
package org.jboss.aesh.mterm.filter;

import javax.swing.Action;
import javax.swing.JTextArea;
import javax.swing.text.NavigationFilter;
import javax.swing.text.Position;

import org.jboss.aesh.mterm.action.BlockAction;
import org.jboss.aesh.mterm.util.MtermUtil;

public class MtermNavigationFilter extends NavigationFilter {

    private int promptStringLength = MtermUtil.INSTANCE.getPs1().length();
    private static final String EVENT_KEY = "delete-previous";

    private JTextArea textarea;
    public MtermNavigationFilter(JTextArea textarea) {
        this.textarea = textarea;
        filter(this.textarea);
    }

    private void filter(JTextArea textarea) {
        Action action = textarea.getActionMap().get(EVENT_KEY);
        textarea.getActionMap().put(EVENT_KEY, new BlockAction(action));
        textarea.setCaretPosition(promptStringLength);
    }

    public void setDot(NavigationFilter.FilterBypass filter, int dot, Position.Bias bias) {
        filter.setDot(Math.max(dot, this.textarea.getText().lastIndexOf(MtermUtil.INSTANCE.getPs1()) + MtermUtil.INSTANCE.getPs1().length()), bias);
    }

    public void moveDot(NavigationFilter.FilterBypass filter, int dot, Position.Bias bias) {
        filter.moveDot(Math.max(dot, this.textarea.getText().lastIndexOf(MtermUtil.INSTANCE.getPs1()) + MtermUtil.INSTANCE.getPs1().length()), bias);
    }

}
