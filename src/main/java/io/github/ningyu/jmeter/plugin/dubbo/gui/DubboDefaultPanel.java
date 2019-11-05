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

import org.apache.jmeter.testelement.TestElement;

import javax.swing.*;

/**
 * DubboDefaultPanel
 */
public class DubboDefaultPanel extends DubboCommonPanel {

    public void drawPanel(JPanel parent) {
        parent.add(drawConfigCenterSettingsPanel());
        parent.add(drawRegistrySettingsPanel());
        parent.add(drawProtocolSettingsPanel());
        parent.add(drawConsumerSettingsPanel());
    }

    public void configure(TestElement element) {
        configureConfigCenter(element);
        configureRegistry(element);
        configureProtocol(element);
        configureConsumer(element);
    }

    public void modifyTestElement(TestElement element) {
        modifyConfigCenter(element);
        modifyRegistry(element);
        modifyProtocol(element);
        modifyConsumer(element);
    }

    public void clearGui() {
        clearConfigCenter();
        clearRegistry();
        clearProtocol();
        clearConsumer();
    }
}
