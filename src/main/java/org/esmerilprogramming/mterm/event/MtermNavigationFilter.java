/*
 * Copyright 2014 EsmerilProgramming.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.esmerilprogramming.mterm.event;

import javax.swing.Action;
import javax.swing.JTextArea;
import javax.swing.text.NavigationFilter;
import javax.swing.text.Position;

/**
 * MtermNavigationFilter class.
 * This class take care of the caret position.
 * 
 * @author <a href="mailto:00hf11@gmail.com">Helio Frota</a>
 */
public class MtermNavigationFilter extends NavigationFilter {
  private int promptStringLength;
  private Action blockPromptStringDeletionAction;

  public MtermNavigationFilter(int promptStringLength, JTextArea textarea) {
    this.promptStringLength = promptStringLength;
    blockPromptStringDeletionAction = textarea.getActionMap().get("delete-previous");
    textarea.getActionMap().put("delete-previous",
        new BlockPromptStringDeletionAction(promptStringLength, blockPromptStringDeletionAction));
    textarea.setCaretPosition(promptStringLength);
  }

  public void setDot(NavigationFilter.FilterBypass filter, int dot, Position.Bias bias) {
    filter.setDot(Math.max(dot, promptStringLength), bias);
  }

  public void moveDot(NavigationFilter.FilterBypass filter, int dot, Position.Bias bias) {
    filter.moveDot(Math.max(dot, promptStringLength), bias);
  }


}
