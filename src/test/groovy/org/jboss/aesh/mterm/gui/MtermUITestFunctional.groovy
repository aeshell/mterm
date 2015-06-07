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
package org.jboss.aesh.mterm.gui

import javax.swing.JFrame

import org.fest.swing.edt.FailOnThreadViolationRepaintManager
import org.fest.swing.edt.GuiActionRunner
import org.fest.swing.edt.GuiQuery
import org.fest.swing.fixture.FrameFixture
import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

/**
 * The MtermUITest class.
 *
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
class MtermUITestFunctional {

  FrameFixture frame

  @BeforeClass
  static void setUpOnce() {
    FailOnThreadViolationRepaintManager.install()
  }

  @After
  void tearDown() {
    frame.cleanUp()
  }

  @Before
  void setUp() {
    JFrame.setDefaultLookAndFeelDecorated(true)
    MtermUI mtermUI = GuiActionRunner.execute(new GuiQuery<MtermUI>() {
          protected MtermUI executeInEDT() {
            return new MtermUI()
          }
        })
    frame = new FrameFixture(mtermUI)
    frame.show()
  }

  @Test
  void menuFileClick() {
    sleep(500)
    frame.menuItem('File').click()
    sleep(500)
  }

  @Test
  void menuEditClick() {
    sleep(500)
    frame.menuItem('Edit').click()
    sleep(500)
  }

  @Test
  void menuViewClick() {
    sleep(500)
    frame.menuItem('View').click()
    sleep(500)
  }

  @Test
  void menuTerminalClick() {
    sleep(500)
    frame.menuItem('Terminal').click()
    sleep(500)
  }

  @Test
  void menuHelpClick() {
    sleep(500)
    frame.menuItem('Help').click()
    sleep(500)
  }

  @Test
  void subMenuNewClick() {
    sleep(500)
    frame.menuItem('New').click()
    sleep(500)
  }

  @Test
  void subMenuCopyClick() {
    sleep(500)
    frame.menuItem('Copy').click()
    sleep(500)
  }

  @Test
  void subMenuPasteClick() {
    sleep(500)
    frame.menuItem('Paste').click()
    sleep(500)
  }

  @Test
  void subMenuFontClick() {
    sleep(500)
    frame.menuItem('Font').click()
    sleep(500)
  }

  @Test
  void subMenuColorClick() {
    sleep(500)
    frame.menuItem('Color').click()
    sleep(500)
  }

  @Test
  void subMenuFullScreenClick() {
    sleep(500)
    frame.menuItem('Full Screen').click()
    sleep(500)
  }

  @Test
  void subMenuClearClick() {
    sleep(500)
    frame.menuItem('Clear').click()
    sleep(500)
  }

  @Test
  void subMenuSetTitleClick() {
    sleep(500)
    frame.menuItem('Set Title').click()
    sleep(500)
  }

  @Test
  void subMenuAboutClick() {
    sleep(500)
    frame.menuItem('About').click()
    sleep(500)
  }

}
