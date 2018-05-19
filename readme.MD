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


## Shiro 关于
<div id="cp1"></div>

### 跟我学 Shiro

Apache Shiro 是一个强大易用的 Java 安全框架，提供了认证、授权、加密和会话管理等功能，对于任何一个应用程序，Shiro 都可以提供全面的安全管理服务。并且相对于其他安全框架，Shiro 要简单的多。本教程只介绍基本的 Shiro 使用，不会过多分析源码等，重在使用。

### 适用人群

Java 企业级安全应用开发人员。

### 学习前提

相比较 Spring Security，Shiro 小巧的多，但是学习本教程前，你还是需要了解 Java 开发语言。


## Shiro 简介
<div id="cp2"></div>

### 简介
Apache Shiro 是 Java 的一个安全框架。目前，使用 Apache Shiro 的人越来越多，因为它相当简单，对比 Spring Security，可能没有 Spring Security 做的功能强大，但是在实际工作时可能并不需要那么复杂的东西，所以使用小而简单的 Shiro 就足够了。对于它俩到底哪个好，这个不必纠结，能更简单的解决项目问题就好了。
本教程只介绍基本的 Shiro 使用，不会过多分析源码等，重在使用。

Shiro 可以非常容易的开发出足够好的应用，其不仅可以用在 JavaSE 环境，也可以用在 JavaEE 环境。Shiro 可以帮助我们完成：认证、授权、加密、会话管理、与 Web 集成、缓存等。这不就是我们想要的嘛，而且 Shiro 的 API 也是非常简单；其基本功能点如下图所示：
![](https://img.w3cschool.cn/attachments/image/wk/shiro/1.png)
<div id="cp5">cp5</div>
<div id="cp6">cp6</div>
<div id="cp7">cp7</div>
<div id="cp8">cp8</div>