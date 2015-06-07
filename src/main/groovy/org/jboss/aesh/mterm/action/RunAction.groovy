/*
 * JBoss, Home of Professional Open Source Copyright 2014 Red Hat Inc. and/or its affiliates and
 * other contributors as indicated by the @authors tag. All rights reserved. See the copyright.txt
 * in the distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the 'License') you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in
 * writing, software distributed under the License is distributed on an 'AS IS' BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package org.jboss.aesh.mterm.action

import java.awt.event.ActionEvent

import javax.swing.JTextArea
import javax.swing.text.BadLocationException

import org.jboss.aesh.mterm.util.AeshUtil
import org.jboss.aesh.mterm.util.MtermUtil

/**
 * @author Helio Frota  00hf11 at gmail.com
 */
class RunAction extends BaseAction {

  RunAction(JTextArea textArea) {
    super(textArea)
  }

  @Override
  void actionPerformed(ActionEvent ae) {
    try {
      String command = extractCommand()
      if (command.contains('clear')) {
        clear()
      } else {
        if (command) {
          aesh.run(command)
          if (!aesh.isRunning()) {
            System.exit(0)
          }
        }
      }

      if (command.isEmpty()) {
        System.out.print('\n' + MtermUtil.instance.ps1)
      } else {
        String result = aesh.parseResult()
        MtermUtil.instance.checkUpdatePS1(AeshUtil.INSTANCE.getExecutedCommand())
        print(result + MtermUtil.instance.ps1)
      }
      aesh.reset()
    } catch (e) {
      e.printStackTrace()
    }
  }

  private void clear() {
    try {
      textArea.document.remove(0, textArea.document.length)
    } catch (BadLocationException e) {
      e.printStackTrace()
    }
  }

  @Override
  protected void perform() {

  }

}
