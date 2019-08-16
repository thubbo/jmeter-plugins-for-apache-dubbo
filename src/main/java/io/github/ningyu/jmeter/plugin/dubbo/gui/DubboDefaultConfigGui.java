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

import io.github.ningyu.jmeter.plugin.util.Constants;
import org.apache.jmeter.config.ConfigTestElement;
import org.apache.jmeter.config.gui.AbstractConfigGui;
import org.apache.jmeter.gui.util.VerticalPanel;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

import javax.swing.*;
import java.awt.*;

/**
 * DubboDefaultConfigGui </br>
 * invoke sequence**clearGui()->createTestElement()->modifyTestElement()->configure()**
 */
public class DubboDefaultConfigGui extends AbstractConfigGui {

    private static final Logger log = LoggingManager.getLoggerForClass();
    private static final long serialVersionUID = -4454521491983368380L;
    private final DubboDefaultPanel panel;

    public DubboDefaultConfigGui() {
        super();
        panel = new DubboDefaultPanel();
        init();
    }

    private void init() {
        //所有设置panel，垂直布局
        JPanel settingPanel = new VerticalPanel(5, 0);
        settingPanel.setBorder(makeBorder());
        Container container = makeTitlePanel();
        settingPanel.add(container);
        //所有设置panel
        panel.drawPanel(settingPanel);
        //全局布局设置
        setLayout(new BorderLayout(0, 5));
        setBorder(makeBorder());
        add(settingPanel,BorderLayout.CENTER);
    }

    @Override
    public String getLabelResource() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void configure(TestElement element) {
        super.configure(element);
        log.debug("sample赋值给config gui");
        panel.configure(element);
        panel.bundleElement(element);
        Constants.redundancy(element);
    }

    @Override
    public TestElement createTestElement() {
        log.debug("创建sample对象");
        //创建sample对象
        ConfigTestElement config = new ConfigTestElement();
        modifyTestElement(config);
        return config;
    }

    @Override
    public void modifyTestElement(TestElement element) {
        log.debug("gui数据赋值给sample");
        //给sample赋值
        super.configureTestElement(element);
        panel.modifyTestElement(element);
        panel.bundleElement(element);
        Constants.redundancy(element);
    }

    @Override
    public String getStaticLabel() {
        return "Dubbo Config";
    }

    @Override
    public void clearGui() {
        log.debug("清空gui数据");
        super.clearGui();
        panel.clearGui();
    }

}
