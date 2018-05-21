# from：[w3school](https://www.w3cschool.cn/shiro/)
# 目录
## <a href='#cp1' target='_self'>Shiro 关于</a>
## <a href='#cp2' target='_self'>Shiro 简介</a>
## <a href='#cp3' target='_self'>Shiro 身份验证</a>
## <a href='#cp41' target='_self'>Shiro 授权</a>
## <a href='#cp5' target='_self'>Shiro InI 配置</a>
## <a href='#cp6' target='_self'>Shiro 编码加密</a>
## <a href='#cp7' target='_self'>Shiro Realm</a>
## <a href='#cp8' target='_self'>Shiro Web 集成</a>
## <a href='#cp8' target='_self'>Shiro Web 拦截器机制</a>
## <a href='#cp8' target='_self'>Shiro Web JSP 标签</a>
## <a href='#cp8' target='_self'>Shiro Web 会话管理</a>
## <a href='#cp8' target='_self'>Shiro Web 缓存机制</a>
## <a href='#cp8' target='_self'>Shiro Web Spring 集成</a>
## <a href='#cp8' target='_self'>Shiro Web RememberMe</a>
## <a href='#cp8' target='_self'>Shiro Web SSL</a>
## <a href='#cp8' target='_self'>Shiro Web 单点登录</a>
## <a href='#cp8' target='_self'>Shiro Web 综合实例</a>
## <a href='#cp8' target='_self'>Shiro Web OAuth2</a>
## <a href='#cp8' target='_self'>Shiro Web 并发登录控制</a>
## <a href='#cp8' target='_self'>Shiro Web 动态 URL</a>
## <a href='#cp8' target='_self'>Shiro Web 无状态 Web</a>
## <a href='#cp8' target='_self'>Shiro Web 授予身份和切换身份</a>
## <a href='#cp8' target='_self'>Shiro Web 集成验证码</a>
## <a href='#cp8' target='_self'>Shiro Web 多项目</a>
## <a href='#cp8' target='_self'>Shiro Web 在线会话管理</a>

<div id="cp1"></div>

## Shiro 关于

### 跟我学 Shiro

Apache Shiro 是一个强大易用的 Java 安全框架，提供了认证、授权、加密和会话管理等功能，对于任何一个应用程序，Shiro 都可以提供全面的安全管理服务。并且相对于其他安全框架，Shiro 要简单的多。本教程只介绍基本的 Shiro 使用，不会过多分析源码等，重在使用。

### 适用人群

Java 企业级安全应用开发人员。

### 学习前提

相比较 Spring Security，Shiro 小巧的多，但是学习本教程前，你还是需要了解 Java 开发语言。

<div id="cp2"></div>

## Shiro 简介

### 简介
Apache Shiro 是 Java 的一个安全框架。目前，使用 Apache Shiro 的人越来越多，因为它相当简单，对比 Spring Security，可能没有 Spring Security 做的功能强大，但是在实际工作时可能并不需要那么复杂的东西，所以使用小而简单的 Shiro 就足够了。对于它俩到底哪个好，这个不必纠结，能更简单的解决项目问题就好了。
本教程只介绍基本的 Shiro 使用，不会过多分析源码等，重在使用。

Shiro 可以非常容易的开发出足够好的应用，其不仅可以用在 JavaSE 环境，也可以用在 JavaEE 环境。Shiro 可以帮助我们完成：认证、授权、加密、会话管理、与 Web 集成、缓存等。这不就是我们想要的嘛，而且 Shiro 的 API 也是非常简单；其基本功能点如下图所示：

![](https://github.com/YuxingXie/shiro/raw/master/doc/1.png)
* <B>Authentication</B>：身份认证 / 登录，验证用户是不是拥有相应的身份；
* <B>Authorization</B>：授权，即权限验证，验证某个已认证的用户是否拥有某个权限；即判断用户是否能做事情，常见的如：验证某个用户是否拥有某个角色。或者细粒度的验证某个用户对某个资源是否具有某个权限；
* <B>Session Manager</B>：会话管理，即用户登录后就是一次会话，在没有退出之前，它的所有信息都在会话中；会话可以是普通 JavaSE 环境的，也可以是如 Web 环境的；
* <B>Cryptography</B>：加密，保护数据的安全性，如密码加密存储到数据库，而不是明文存储；
* <B>Web Support</B>：Web 支持，可以非常容易的集成到 Web 环境；
* <B>Caching</B>：缓存，比如用户登录后，其用户信息、拥有的角色 / 权限不必每次去查，这样可以提高效率；
* <B>Concurrency</B>：shiro 支持多线程应用的并发验证，即如在一个线程中开启另一个线程，能把权限自动传播过去；
* <B>Testing</B>：提供测试支持；
* <B>Run As</B>：允许一个用户假装为另一个用户（如果他们允许）的身份进行访问；
* <B>Remember Me</B>：记住我，这个是非常常见的功能，即一次登录后，下次再来的话不用登录了。

记住一点，Shiro 不会去维护用户、维护权限；这些需要我们自己去设计 / 提供；然后通过相应的接口注入给 Shiro 即可。

接下来我们分别从外部和内部来看看 Shiro 的架构，对于一个好的框架，从外部来看应该具有非常简单易于使用的 API，且 API 契约明确；从内部来看的话，其应该有一个可扩展的架构，即非常容易插入用户自定义实现，因为任何框架都不能满足所有需求。

首先，我们从外部来看 Shiro 吧，即从应用程序角度的来观察如何使用 Shiro 完成工作。如下图：

![](https://github.com/YuxingXie/shiro/raw/master/doc/2.png)

可以看到：应用代码直接交互的对象是 Subject，也就是说 Shiro 的对外 API 核心就是 Subject；其每个 API 的含义：
* <B>Subject</B>：主体，代表了当前 “用户”，这个用户不一定是一个具体的人，与当前应用交互的任何东西都是 Subject，如网络爬虫，机器人等；即一个抽象概念；所有 Subject 都绑定到 SecurityManager，与 Subject 的所有交互都会委托给 SecurityManager；可以把 Subject 认为是一个门面；SecurityManager 才是实际的执行者；
* <B>SecurityManager</B>：安全管理器；即所有与安全有关的操作都会与 SecurityManager 交互；且它管理着所有 Subject；可以看出它是 Shiro 的核心，它负责与后边介绍的其他组件进行交互，如果学习过 SpringMVC，你可以把它看成 DispatcherServlet 前端控制器；
* <B>Realm</B>：域，Shiro 从从 Realm 获取安全数据（如用户、角色、权限），就是说 SecurityManager 要验证用户身份，那么它需要从 Realm 获取相应的用户进行比较以确定用户身份是否合法；也需要从 Realm 得到用户相应的角色 / 权限进行验证用户是否能进行操作；可以把 Realm 看成 DataSource，即安全数据源。


也就是说对于我们而言，最简单的一个 Shiro 应用：

* ：应用代码通过 Subject 来进行认证和授权，而 Subject 又委托给 SecurityManager；
* ：我们需要给 Shiro 的 SecurityManager 注入 Realm，从而让 SecurityManager 能得到合法的用户及其权限进行判断。


从以上也可以看出，Shiro 不提供维护用户 / 权限，而是通过 Realm 让开发人员自己注入。

接下来我们来从 Shiro 内部来看下 Shiro 的架构，如下图所示：

![](https://github.com/YuxingXie/shiro/raw/master/doc/3.png)

<div id="cp3"></div>
<div id="cp6">cp6</div>
<div id="cp7">cp7</div>
<div id="cp8">cp8</div>