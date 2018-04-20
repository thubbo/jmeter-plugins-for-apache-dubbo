# Dubbo Plugin for Apache JMeter

# 介绍

Dubbo Plugin for Apache JMeter是用来在Jmeter里更加方便的测试Dubbo接口而开发的插件

# 项目地址

具体可以查看blog地址：https://ningyu1.github.io/site/post/60-jmeter-plugins-dubbo-support/

[项目地址:jmeter-plugins-dubbo](https://github.com/ningyu1/jmeter-plugins-dubbo) 

<a href="https://github.com/ningyu1/jmeter-plugins-dubbo/releases"><img src="https://img.shields.io/github/release/ningyu1/jmeter-plugins-dubbo.svg?style=social&amp;label=Release"></a>&nbsp;<a href="https://github.com/ningyu1/jmeter-plugins-dubbo/stargazers"><img src="https://img.shields.io/github/stars/ningyu1/jmeter-plugins-dubbo.svg?style=social&amp;label=Star"></a>&nbsp;<a href="https://github.com/ningyu1/jmeter-plugins-dubbo/fork"><img src="https://img.shields.io/github/forks/ningyu1/jmeter-plugins-dubbo.svg?style=social&amp;label=Fork"></a>&nbsp;<a href="https://github.com/ningyu1/jmeter-plugins-dubbo/watchers"><img src="https://img.shields.io/github/watchers/ningyu1/jmeter-plugins-dubbo.svg?style=social&amp;label=Watch"></a> <a href="https://opensource.org/licenses/MIT"><img src="https://img.shields.io/badge/license-MIT-blue.svg"></a>

# 版本更新

[V1.2.4](https://github.com/ningyu1/jmeter-plugins-dubbo/releases/tag/V1.2.4)

问题：
由于sample执行错误会统计到用例的Error%内，但是有时我们的接口返回异常并不代表是真正的错误，有可能是正常的校验，因此本次优化了sample的执行状态。

解决：
当接口返回异常时，sample标识为successful，可以通过响应内容做断言来判断是否需要标识为failure，比如接口有一些校验性质的异常，不代表这个操作是错误的，这样就可以灵活的判断，不至于正常的校验返回导致测试用例Error%的不真实

示例：
![1](https://user-images.githubusercontent.com/3387548/38234811-8fd16e90-3751-11e8-9823-39407bb004a2.png)
![2](https://user-images.githubusercontent.com/3387548/38234812-902c7e02-3751-11e8-8edc-039cdae2667b.png)
![3](https://user-images.githubusercontent.com/3387548/38234813-9070dd68-3751-11e8-958f-44f04a7a93ff.png)
![4](https://user-images.githubusercontent.com/3387548/38234814-90fad086-3751-11e8-9680-b9a76eff64d6.png)
![5](https://user-images.githubusercontent.com/3387548/38234816-916f47ae-3751-11e8-9f35-f6be4a39c40d.png)
![6](https://user-images.githubusercontent.com/3387548/38234817-92255bac-3751-11e8-9ee7-daf4a1a9974a.png)
![7](https://user-images.githubusercontent.com/3387548/38234820-92c746d8-3751-11e8-8d4d-0f157c3b87a9.png)

[V1.2.3](https://github.com/ningyu1/jmeter-plugins-dubbo/releases/tag/V1.2.3)

1. bug fix参数类型报错问题

[V1.2.2](https://github.com/ningyu1/jmeter-plugins-dubbo/releases/tag/V1.2.2)

1. 升级dubbo版本->2.6.1
2. 使用ReferenceConfigCache缓存ReferenceConfig对象
3. 增加远程调用方式：Dubbo:FST

[V1.2.1](https://github.com/ningyu1/jmeter-plugins-dubbo/releases/tag/V1.2.1)

1. 支持注册中心增加：multicast、redis、simple
2. 修改GUI中Protocol值显示方式，增加了描述，例如：dubbo修改为dubbo@直连、zookeeper修改为zookeeper@注册中心
![image](https://user-images.githubusercontent.com/3387548/37705925-8fcc0b58-2d38-11e8-8095-656687f13951.png)

[V1.2.0](https://github.com/ningyu1/jmeter-plugins-dubbo/releases/tag/V1.2.0)
1. 使用gson进行json序列化、反序列化
2. 使用dubbo泛化调用方式重构反射调用方式
3. 支持复杂类型、支持泛型，例如："java.lang.List<ResourceVo>,Map<String,ResourceVo> map,List<Map<String, ResourceVo>> list"

本次版本主要对反射参数类型进行了增强，支持复杂类型、支持参数泛型，可以参考如下的参数对照表：

![image](https://user-images.githubusercontent.com/3387548/37324055-495fbb50-26c2-11e8-9bfa-3fb0739a5cf6.png)

[V1.1.0](https://github.com/ningyu1/jmeter-plugins-dubbo/releases/tag/V1.1.0)
1. 工具界面输入信息均支持使用jmeter变量${var}，函数${__RandomString(5,12345,ids)}进行参数化。
2. 接口参数类型与值支持使用jmeter变量${var}，函数${__RandomString(5,12345,ids)}进行参数化

ps.很遗憾的是升级插件后以前的jmx文件无法打开需要重新创建jmx脚本

下面是测试截图 
![1](https://user-images.githubusercontent.com/3387548/37082704-310fa8ce-2228-11e8-88ff-f278ce1a0009.png)
![2](https://user-images.githubusercontent.com/3387548/37082705-315581b4-2228-11e8-930a-e246f18dc371.png)
![3](https://user-images.githubusercontent.com/3387548/37082707-319ca698-2228-11e8-8b20-47cf315ee267.png)

[V1.0.0](https://github.com/ningyu1/jmeter-plugins-dubbo/releases/tag/V1.0.0)
1. 增加了DubboSample，协议支持：zookeeper、dubbo
2. 增加调用接口与方法以及参数支持
3. 主要用于Dubbo RPC接口测试

# DubboSample使用

## 支持Jmeter版本

Jmeter版本：3.0

## 插件安装

插件包可以去`github`上下载。将插件包放入Jmeter的lib的ext下。

```
${Path}\apache-jmeter-3.0\lib\ext
```

如果使用的是：`jmeter-plugins-dubbo-1.0.0-SNAPSHOT-jar-with-dependencies.jar`包含所有依赖，推荐使用这个包。

如果使用的是：`jmeter-plugins-dubbo-1.0.0-SNAPSHOT.jar`需要自行添加插件的依赖包，依赖包版本如下：

```
dubbo-2.5.3.jar
javassist-3.15.0-GA.jar
zookeeper-3.4.6.jar
zkclient-0.1.jar
jline-0.9.94.jar
netty-3.7.0-Final.jar
slf4j-api-1.7.5.jar
log4j-over-slf4j-1.7.5.jar
```

## 插件使用

启动`Jmeter`添加`DubboSample`如下图：

![](https://ningyu1.github.io/site/img/jmeter-plugins-dubbo/1.png)

添加后能看到`DubboSample`的具体操作页面，如下图：

![](https://ningyu1.github.io/site/img/jmeter-plugins-dubbo/2.png)

根据上图提示传入值即可。

接口以及接口依赖包请添加到`classpath`下，可以放在`apache-jmeter-3.0\lib\ext`下，也可以通过下图方式添加：

![](https://ningyu1.github.io/site/img/jmeter-plugins-dubbo/3.png)

## 运行结果

![](https://ningyu1.github.io/site/img/jmeter-plugins-dubbo/4.png)

![](https://ningyu1.github.io/site/img/jmeter-plugins-dubbo/5.png)

![](https://ningyu1.github.io/site/img/jmeter-plugins-dubbo/6.png)

## 注意事项

1. 当使用zk，address填入zk地址（集群地址使用","分隔）,使用dubbo直连，address填写直连地址和服务端口
2. `timeout`：服务方法调用超时时间(毫秒)
3. `version`：服务版本，与服务提供者的版本一致
4. `retries`：远程服务调用重试次数，不包括第一次调用，不需要重试请设为0
5. `cluster`：集群方式，可选：failover/failfast/failsafe/failback/forking
6. 接口需要填写类型完全名称，含包名
7. 参数支持任何类型，包装类直接使用`java.lang`下的包装类，小类型使用：`int、float、shot、double、long、byte、boolean、char`，自定义类使用类完全名称。
8. 参数值，基础包装类和基础小类型直接使用值，例如：int为1，boolean为true等，自定义类与`List`或者`Map`等使用json格式数据。
9. 更多dubbo参数查看官方文档：[http://dubbo.apache.org/books/dubbo-user-book/references/xml/dubbo-reference.html](http://dubbo.apache.org/books/dubbo-user-book/references/xml/dubbo-reference.html)

