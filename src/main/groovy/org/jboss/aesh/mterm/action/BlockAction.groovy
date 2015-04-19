/*
 * JBoss, Home of Professional Open Source Copyright 2014 Red Hat Inc. and/or its affiliates and
 * other contributors as indicated by the @authors tag. All rights reserved. See the copyright.txt
 * in the distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License") you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in
 * writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package org.jboss.aesh.mterm.action

import java.awt.event.ActionEvent

import javax.swing.AbstractAction
import javax.swing.Action
import javax.swing.JTextArea

import org.jboss.aesh.mterm.util.MtermUtil

/**
 * @author Helio Frota  00hf11 at gmail.com
 */
class BlockAction extends AbstractAction {

  private final Action action

  BlockAction(Action action) {
    this.action = action
  }

  void actionPerformed(ActionEvent ae) {
    JTextArea t = (JTextArea) ae.source
    if (t.caretPosition > t.text.lastIndexOf(MtermUtil.instance.ps1) + MtermUtil.instance.ps1.length()) {
      action.actionPerformed(null)
    }
  }
}
