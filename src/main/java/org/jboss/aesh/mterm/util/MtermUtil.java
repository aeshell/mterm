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
package org.jboss.aesh.mterm.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.jboss.aesh.mterm.gui.MessageDialog;

/**
 * This class has util methods to improve the app.
 *
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
public enum MtermUtil {

    INSTANCE;

    private String ps1 = createPs1();
    private String currentDirectory;

    private static final String START = "[";
    private static final String AT = "@";
    private static final String END = " ~]$ ";

    /**
     * Creates the terminal prompt string.
     * 
     * @return String
     */
    public String createPs1() {
        String ps1 = START + System.getProperty("user.name") + AT;
        try {
            ps1 += InetAddress.getLocalHost().getHostName().split("\\.")[0] + END;
        }
        catch (UnknownHostException e) {
            new MessageDialog().error(e.getMessage());
        }
        return ps1;
    }

    public String getPs1() {
        return ps1;
    }

    public String getCurrentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(String currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

}
