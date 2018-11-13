/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.ningyu.jmeter.plugin.dubbo.gui;

import javax.swing.*;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Vector;

/**
 * AutoCompleter
 */
public class AutoCompleter implements KeyListener {

    private JComboBox owner = null;
    private JTextField editor = null;

    private ComboBoxModel model = null;

    public AutoCompleter(JComboBox comboBox) {
        owner = comboBox;
        editor = (JTextField) comboBox.getEditor().getEditorComponent();
        editor.addKeyListener(this);
        model = comboBox.getModel();
        // owner.addItemListener(this);
    }

    public AutoCompleter(JComboBox comboBox, ItemListener itemListener) {
        owner = comboBox;
        editor = (JTextField) comboBox.getEditor().getEditorComponent();
        editor.addKeyListener(this);
        model = comboBox.getModel();
        owner.addItemListener(itemListener);
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
//        char ch = e.getKeyChar();
//        if (ch == KeyEvent.VK_ENTER) {
//            return;
//        }
//        editor.setText("");
    }

    public void keyReleased(KeyEvent e) {
        char ch = e.getKeyChar();
        if (ch == KeyEvent.VK_ENTER) {
            int caretPosition = editor.getCaretPosition();
            String str = editor.getText();
            if (str.length() == 0)
                return;
            autoComplete(str, caretPosition);
        }
//        if (ch == KeyEvent.CHAR_UNDEFINED || Character.isISOControl(ch)
//                || ch == KeyEvent.VK_DELETE)
//            return;
    }

    /**
     * 自动完成。根据输入的内容，在列表中找到相似的项目.
     */
    protected void autoComplete(String strf, int caretPosition) {
        Object[] opts;
        opts = getMatchingOptions(strf.substring(0, caretPosition));
        if (owner != null) {
            model = new DefaultComboBoxModel(opts);
            owner.setModel(model);
        }
        if (opts.length > 0) {
            String str = opts[0].toString();
            // editor.setCaretPosition(caretPosition);
            if (owner != null) {
                try {
                    owner.showPopup();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * 找到相似的项目, 并且将之排列到数组的最前面。
     *
     * @param str
     * @return 返回所有项目的列表。
     */
    protected Object[] getMatchingOptions(String str) {
        List v = new Vector();
        List v1 = new Vector();
        model = owner.getModel();
        for (int k = 0; k < model.getSize(); k++) {
            Object itemObj = model.getElementAt(k);
            if (itemObj != null) {
                String item = itemObj.toString();
                if (item.toUpperCase().indexOf(str.toUpperCase()) != -1)
                    v.add(model.getElementAt(k));
                else
                    v1.add(model.getElementAt(k));
            } else
                v1.add(model.getElementAt(k));
        }
        for (int i = 0; i < v1.size(); i++) {
            v.add(v1.get(i));
        }
        if (v.isEmpty())
            v.add(str);
        return v.toArray();
    }
}
