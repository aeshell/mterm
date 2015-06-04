/*
 * JBoss, Home of Professional Open Source Copyright 2014 Red Hat Inc. and/or its affiliates and
 * other contributors as indicated by the @authors tag. All rights reserved. See the copyright.txt
 * in the distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the 'License'); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in
 * writing, software distributed under the License is distributed on an 'AS IS' BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package org.jboss.aesh.mterm.action.menu

import java.awt.Desktop
import java.awt.event.ActionEvent

import javax.swing.ImageIcon

/**
 * @author Helio Frota  00hf11 at gmail.com
 */
class MenuContentsAction extends MenuBaseAction {

  MenuContentsAction(String text, ImageIcon icon) {
    super(text, icon)
  }

  @Override
  void actionPerformed(ActionEvent ae) {
    String url = 'https://github.com/aeshell/mterm'
    try {
      if (Desktop.isDesktopSupported()) {
        Desktop.getDesktop().browse(new URI(url))
      } else {
        Runtime runtime = Runtime.getRuntime()
        runtime.exec('firefox -new-window ' + url)
      }
    } catch (e) {
      e.printStackTrace()
    }
  }
}
