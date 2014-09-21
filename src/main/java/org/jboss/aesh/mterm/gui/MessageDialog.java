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
package org.jboss.aesh.mterm.gui;

import javax.swing.JOptionPane;

/**
 * MessageDialog class is a JOPtionPane handler to show exceptions and others types of messages.
 * Since 'out' and 'err' are redirecting to JTextArea the error messages are going to 'up' for now.
 *
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
public class MessageDialog {

    /**
     * Shows the error message from exception.
     * 
     * @param message String
     */
    public void error(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Shows the info message.
     * 
     * @param message String
     */
    public void info(String message) {
        JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

}
