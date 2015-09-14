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
package org.jboss.aesh.mterm.util

import javax.swing.JTextArea

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * @author <a href='mailto:00hf11@gmail.com'>Helio Frota</a>
 */
class AeshUtilTest {

  private JTextArea textArea
  private AeshUtil aesh

  @Before
  void setUp() {
    textArea = new JTextArea()
    PrintStream printStream = new PrintStream(new MtermOutputStream(textArea, System.out))
    System.setOut(printStream)
    aesh = AeshUtil.INSTANCE
    aesh.start(printStream)
  }

  @Test
  void testGetCurrentDirectory() throws IOException {
    Assert.assertEquals('mterm', aesh.getCurrentDirectory())
    aesh.run('cd src')
    Assert.assertEquals('src', aesh.getCurrentDirectory())
  }

  @Test
  void testGetRegisteredCommands() {
    Set<String> registered = aesh.getRegisteredCommands()
    String[] commands = [
      'cd',
      'ls',
      'touch',
      'mkdir',
      'pwd',
      'rm',
      'mv',
      'touch',
      'cat',
      'clear',
      'echo'] as String[]
    for (String c : commands) {
      Assert.assertTrue(registered.contains(c))
    }
  }

  @Test
  void testGetResult() throws IOException {
    aesh.run('echo abc')
    Assert.assertEquals('\nabc\n', aesh.parseResult())
    aesh.run('\t') // same as TabAction
    Assert.assertTrue(aesh.parseResult().contains('echo'))
  }

  @After
  void tearDown() {
    aesh.stop()
  }
}
