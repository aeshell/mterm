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

import javax.swing.JFrame;

import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The MtermUITest class.
 *
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
public class MtermUITestFunctional {

    FrameFixture frame;

    @BeforeClass
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @After
    public void tearDown() {
        frame.cleanUp();
    }

    @Before
    public void setUp() {

        JFrame.setDefaultLookAndFeelDecorated(true);
        MtermUI mtermUI = GuiActionRunner.execute(new GuiQuery<MtermUI>() {
            protected MtermUI executeInEDT() {
                return new MtermUI();
            }
        });
        frame = new FrameFixture(mtermUI);
        frame.show();
    }

    @Test
    public void menuFileClick() {
        pause();
        frame.menuItem("File").click();
        pause();
    }

    @Test
    public void menuEditClick() {
        pause();
        frame.menuItem("Edit").click();
        pause();
    }

    @Test
    public void menuViewClick() {
        pause();
        frame.menuItem("View").click();
        pause();
    }

    @Test
    public void menuTerminalClick() {
        pause();
        frame.menuItem("Terminal").click();
        pause();
    }

    @Test
    public void menuHelpClick() {
        pause();
        frame.menuItem("Help").click();
        pause();
    }

    @Test
    public void subMenuNewClick() {
        pause();
        frame.menuItem("New").click();
        pause();
    }

    @Test
    public void subMenuCopyClick() {
        pause();
        frame.menuItem("Copy").click();
        pause();
    }

    @Test
    public void subMenuPasteClick() {
        pause();
        frame.menuItem("Paste").click();
        pause();
    }

    @Test
    public void subMenuFontClick() {
        pause();
        frame.menuItem("Font").click();
        pause();
    }

    @Test
    public void subMenuColorClick() {
        pause();
        frame.menuItem("Color").click();
        pause();
    }

    @Test
    public void subMenuFullScreenClick() {
        pause();
        frame.menuItem("Full Screen").click();
        pause();
    }

    @Test
    public void subMenuClearClick() {
        pause();
        frame.menuItem("Clear").click();
        pause();
    }

    @Test
    public void subMenuSetTitleClick() {
        pause();
        frame.menuItem("Set Title").click();
        pause();
    }
    
    @Test
    public void subMenuAboutClick() {
        pause();
        frame.menuItem("About").click();
        pause();
    }

    private void pause() {
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
