/*
 * Copyright (c) 2018, Tsoft and/or its affiliates. All rights reserved.
 * FileName: DubboSampleGui.java
 * Author:   ningyu
 * Date:     2018年2月8日
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package cn.tsoft.framework.testing.jmeter.plugin.dubbo.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.apache.jmeter.gui.util.HorizontalPanel;
import org.apache.jmeter.gui.util.VerticalPanel;
import org.apache.jmeter.samplers.gui.AbstractSamplerGui;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

import cn.tsoft.framework.testing.jmeter.plugin.dubbo.sample.DubboSample;
import cn.tsoft.framework.testing.jmeter.plugin.dubbo.sample.MethodArgument;

/**
 * gui上创建一个sampler，其调用的顺序是**clearGui()->createTestElement()->modifyTestElement()->configure()**
 * 
 * @author ningyu
 * @date 2018年2月8日 上午10:09:59
 */
public class DubboSampleGui extends AbstractSamplerGui {
    
    private static final Logger log = LoggingManager.getLoggerForClass();
    
    /**
     */
    private static final long serialVersionUID = -3248204995359935007L;
    
    private JComboBox<String> protocolText;
    private JTextField addressText;
    private JTextField timeoutText;
    private JTextField versionText;
    private JTextField retriesText;
    private JTextField clusterText;
    private JTextField interfaceText;
    private JTextField methodText;
    private JTextField groupText;
    private JTextField connectionsText;
    private JComboBox<String> loadbalanceText;
    private JComboBox<String> asyncText;
    private DefaultTableModel model;
    private String[] columnNames = {"paramType", "paramValue"};
    private String[] tmpRow = {"", ""};
    private int textColumns = 2;
    

    public DubboSampleGui() {
        super();
        init();
    }
    
    /**
     * 初始化界面布局与元素
     * 
     * @author ningyu
     * @date 2018年2月8日 上午10:46:06
     *
     */
    private void init() {
        //所有设置panel，垂直布局
        JPanel settingPanel = new VerticalPanel(5, 0);
        settingPanel.setBorder(makeBorder());
        Container container = makeTitlePanel();
        
        JLabel linklabel = new JLabel("<html><a href='https://github.com/ningyu1/jmeter-plugins-dubbo'>The plug-in author is Ningyu, Plug-in GitHub</a></html>");
        linklabel.setIcon(new ImageIcon(getClass().getResource("/images/about.png")));
        linklabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URL("https://github.com/ningyu1/jmeter-plugins-dubbo").toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        container.add(linklabel);
        settingPanel.add(container);
        
        JPanel serverSettings = new VerticalPanel();
        serverSettings.setBorder(BorderFactory.createTitledBorder("Server Settings"));
        
        //Protocol
        JPanel ph = new HorizontalPanel();
        JLabel protocolLable = new JLabel("Protocol:", SwingConstants.RIGHT);
        protocolText = new JComboBox<String>(new String[]{"dubbo@直连", "zookeeper@注册中心", "multicast@注册中心", "redis@注册中心", "simple@注册中心"});
        protocolLable.setLabelFor(protocolText);
        ph.add(protocolLable);
        ph.add(protocolText);
        serverSettings.add(ph);
        
        //Address
        JPanel ah = new HorizontalPanel();
        JLabel addressLable = new JLabel("Address:", SwingConstants.RIGHT);
        addressText = new JTextField(textColumns);
        addressLable.setLabelFor(addressText);
        ah.add(addressLable);
        ah.add(addressText);
        serverSettings.add(ah);
        
        JPanel h = new HorizontalPanel();
        //Timeout
        JLabel timeoutLable = new JLabel(" Timeout:", SwingConstants.RIGHT);
        timeoutText = new JTextField(textColumns);
        timeoutText.setText(DubboSample.DEFAULT_TIMEOUT);
        timeoutLable.setLabelFor(timeoutText);
        h.add(timeoutLable);
        h.add(timeoutText);
        //Version
        JLabel versionLable = new JLabel("Version:", SwingConstants.RIGHT);
        versionText = new JTextField(textColumns);
        versionText.setText(DubboSample.DEFAULT_VERSION);
        versionLable.setLabelFor(versionText);
        h.add(versionLable);
        h.add(versionText);
        //Retries
        JLabel retriesLable = new JLabel("Retries:", SwingConstants.RIGHT);
        retriesText = new JTextField(textColumns);
        retriesText.setText(DubboSample.DEFAULT_RETRIES);
        retriesLable.setLabelFor(retriesText);
        h.add(retriesLable);
        h.add(retriesText);
        //Cluster
        JLabel clusterLable = new JLabel("Cluster:", SwingConstants.RIGHT);
        clusterText = new JTextField(textColumns);
        clusterText.setText(DubboSample.DEFAULT_CLUSTER);
        clusterLable.setLabelFor(clusterText);
        h.add(clusterLable);
        h.add(clusterText);
        //Group
        JLabel groupLable = new JLabel("Group:", SwingConstants.RIGHT);
        groupText = new JTextField(textColumns);
        groupLable.setLabelFor(groupText);
        h.add(groupLable);
        h.add(groupText);
        //Connections
        JLabel connectionsLable = new JLabel("Connections:", SwingConstants.RIGHT);
        connectionsText = new JTextField(textColumns);
        connectionsText.setText(DubboSample.DEFAULT_CONNECTIONS);
        connectionsLable.setLabelFor(connectionsText);
        h.add(connectionsLable);
        h.add(connectionsText);
        serverSettings.add(h);
        
        JPanel hp1 = new HorizontalPanel();
        //Async
        JLabel asyncLable = new JLabel("     Async:", SwingConstants.RIGHT);
        asyncText = new JComboBox<String>(new String[]{"sync@同步", "async@异步"});
        asyncLable.setLabelFor(asyncText);
        hp1.add(asyncLable);
        hp1.add(asyncText);
        //Loadbalance
        JLabel loadbalanceLable = new JLabel("Loadbalance:", SwingConstants.RIGHT);
        loadbalanceText = new JComboBox<String>(new String[]{"random@随机", "roundrobin@轮播", "leastactive@最少活跃"});
        loadbalanceLable.setLabelFor(loadbalanceText);
        hp1.add(loadbalanceLable);
        hp1.add(loadbalanceText);
        serverSettings.add(hp1);
        
        JPanel interfaceSettings = new VerticalPanel();
        interfaceSettings.setBorder(BorderFactory.createTitledBorder("Interface Settings"));
        //Interface
        JPanel ih = new HorizontalPanel();
        JLabel interfaceLable = new JLabel("Interface:", SwingConstants.RIGHT);
        interfaceText = new JTextField(textColumns);
        interfaceLable.setLabelFor(interfaceText);
        ih.add(interfaceLable);
        ih.add(interfaceText);
        interfaceSettings.add(ih);
        //Method
        JPanel mh = new HorizontalPanel();
        JLabel methodLable = new JLabel("   Method:", SwingConstants.RIGHT);
        methodText = new JTextField(textColumns);
        methodLable.setLabelFor(methodText);
        mh.add(methodLable);
        mh.add(methodText);
        interfaceSettings.add(mh);
        
        //表格panel
        JPanel tablePanel = new HorizontalPanel();
        //Args
        JLabel argsLable = new JLabel("        Args:", SwingConstants.RIGHT);
        model = new DefaultTableModel();
//        model.setDataVector(new String[][]{{"", ""}}, columnNames);
        model.setDataVector(null, columnNames);
        final JTable table = new JTable(model);
        table.setRowHeight(40);
        //添加按钮
        JButton addBtn = new JButton("增加");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                model.addRow(tmpRow);
            }
        });
        JButton delBtn = new JButton("删除");
        delBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                int rowIndex = table.getSelectedRow();
                if(rowIndex != -1)
                    model.removeRow(rowIndex);
            }
        });
        //表格滚动条
        JScrollPane scrollpane = new JScrollPane(table);
        tablePanel.add(argsLable);
        tablePanel.add(scrollpane);
        tablePanel.add(addBtn);
        tablePanel.add(delBtn);
        interfaceSettings.add(tablePanel);
        
        //所有设置panel
        settingPanel.add(serverSettings);
        settingPanel.add(interfaceSettings);
        
        //全局布局设置
        setLayout(new BorderLayout(0, 5));
        setBorder(makeBorder());
        add(settingPanel,BorderLayout.CENTER);
    }

    /**
     * 这个方法将Smapler的数据设置到gui中
     */
    @Override
    public void configure(TestElement element) {
        super.configure(element);
        log.info("sample赋值给gui");
        DubboSample sample = (DubboSample) element;
        protocolText.setSelectedItem(sample.getProtocol());
        addressText.setText(sample.getAddress());
        versionText.setText(sample.getVersion());
        timeoutText.setText(sample.getTimeout());
        retriesText.setText(sample.getRetries());
        groupText.setText(sample.getGroup());
        connectionsText.setText(sample.getConnections());
        loadbalanceText.setSelectedItem(sample.getLoadbalance());
        asyncText.setSelectedItem(sample.getAsync());
        clusterText.setText(sample.getCluster());
        interfaceText.setText(sample.getInterface());
        methodText.setText(sample.getMethod());
        Vector<String> columnNames = new Vector<String>();
        columnNames.add("paramType");
        columnNames.add("paramValue");
        model.setDataVector(paserMethodArgsData(sample.getMethodArgs()), columnNames);
    }

    /**
     * 创建新的sampler。并且将它传给你创建的modifyTestElement(TestElement)方法。
     */
    @Override
    public TestElement createTestElement() {
        log.info("创建sample对象");
        //创建sample对象
        DubboSample sample = new DubboSample();
        modifyTestElement(sample);
        return sample;
    }

    /**
     * 这个方法应该返回代表的component的title/name的名字
     */
    @Override
    public String getLabelResource() {
        return this.getClass().getSimpleName();
    }

    /**
     * 这个方法是用来将数据从你的gui传到TestElement.这个方法和configure()方法在逻辑上是相反的
     */
    @SuppressWarnings("unchecked")
    @Override
    public void modifyTestElement(TestElement element) {
        log.info("gui数据赋值给sample");
        //给sample赋值
        super.configureTestElement(element);
        DubboSample sample = (DubboSample) element;
        sample.setProtocol(protocolText.getSelectedItem().toString());
        sample.setAddress(addressText.getText());
        sample.setTimeout(timeoutText.getText());
        sample.setVersion(versionText.getText());
        sample.setRetries(retriesText.getText());
        sample.setGroup(groupText.getText());
        sample.setConnections(connectionsText.getText());
        sample.setLoadbalance(loadbalanceText.getSelectedItem().toString());
        sample.setAsync(asyncText.getSelectedItem().toString());
        sample.setCluster(clusterText.getText());
        sample.setInterfaceName(interfaceText.getText());
        sample.setMethod(methodText.getText());
        sample.setMethodArgs(getMethodArgsData(model.getDataVector()));
    }
    
    private Vector<Vector<String>> paserMethodArgsData(List<MethodArgument> list) {
    	Vector<Vector<String>> res = new Vector<Vector<String>>();
    	for (MethodArgument args : list) {
    		Vector<String> v = new Vector<String>();
    		v.add(args.getParamType());
    		v.add(args.getParamValue());
    		res.add(v);
    	}
    	return res;
    }
    
    private List<MethodArgument> getMethodArgsData(Vector<Vector<String>> data) {
    	List<MethodArgument> params = new ArrayList<MethodArgument>();
    	if (!data.isEmpty()) {
    		 //处理参数
            Iterator<Vector<String>> it = data.iterator();
            while(it.hasNext()) {
                Vector<String> param = it.next();
                if (!param.isEmpty()) {
                	params.add(new MethodArgument(param.get(0), param.get(1)));
                }
            }
    	}
    	return params;
    }

    /**
     * 这个方法应该返回代表的Sample的name
     */
    @Override
    public String getStaticLabel() {
        return "Dubbo Sample";
    }

    /**
     * 这个方法是用来在你创建新的sampler时，清除数据的。
     */
    @Override
    public void clearGui() {
        log.info("清空gui数据");
        super.clearGui();
        protocolText.setSelectedIndex(0);
        addressText.setText("");
        timeoutText.setText(DubboSample.DEFAULT_TIMEOUT);
        versionText.setText(DubboSample.DEFAULT_VERSION);
        retriesText.setText(DubboSample.DEFAULT_RETRIES);
        clusterText.setText(DubboSample.DEFAULT_CLUSTER);
        groupText.setText("");
        connectionsText.setText(DubboSample.DEFAULT_CONNECTIONS);
        loadbalanceText.setSelectedIndex(0);
        asyncText.setSelectedIndex(0);
        interfaceText.setText("");
        methodText.setText("");
        model.setDataVector(null, columnNames);
    }

}


