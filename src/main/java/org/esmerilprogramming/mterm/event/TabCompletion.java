package org.esmerilprogramming.mterm.event;

import javax.swing.JTextArea;

public class TabCompletion implements Runnable {

  private String completion;
  private JTextArea textArea;
  private int pos;

  public TabCompletion(String completion, JTextArea textArea) {
    this.completion = completion;
    this.textArea = textArea;
  }

  public void run() {
    textArea.insert(completion, pos);
    textArea.setCaretPosition(pos + completion.length());
    textArea.moveCaretPosition(pos);
  }
}
