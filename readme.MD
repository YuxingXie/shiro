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



<div id="cp1">
Shiro 关于
              由 北公爵无欢 创建， 最后一次修改 2016-08-12
              跟我学 Shiro
              
              Apache Shiro 是一个强大易用的 Java 安全框架，提供了认证、授权、加密和会话管理等功能，对于任何一个应用程序，Shiro 都可以提供全面的安全管理服务。并且相对于其他安全框架，Shiro 要简单的多。本教程只介绍基本的 Shiro 使用，不会过多分析源码等，重在使用。
              适用人群
              
              Java 企业级安全应用开发人员。
              学习前提
              
              相比较 Spring Security，Shiro 小巧的多，但是学习本教程前，你还是需要了解 Java 开发语言。
              http://jinnianshilongnian.iteye.com/blog/2018936</div>

<div id="cp2">
Shiro 简介
由 北公爵无欢 创建， 最后一次修改 2016-08-12
简介

Apache Shiro 是 Java 的一个安全框架。目前，使用 Apache Shiro 的人越来越多，因为它相当简单，对比 Spring Security，可能没有 Spring Security 做的功能强大，但是在实际工作时可能并不需要那么复杂的东西，所以使用小而简单的 Shiro 就足够了。对于它俩到底哪个好，这个不必纠结，能更简单的解决项目问题就好了。
本教程只介绍基本的 Shiro 使用，不会过多分析源码等，重在使用。
Shiro 可以非常容易的开发出足够好的应用，其不仅可以用在 JavaSE 环境，也可以用在 JavaEE 环境。Shiro 可以帮助我们完成：认证、授权、加密、会话管理、与 Web 集成、缓存等。这不就是我们想要的嘛，而且 Shiro 的 API 也是非常简单；其基本功能点如下图所示：

Authentication：身份认证 / 登录，验证用户是不是拥有相应的身份；
Authorization：授权，即权限验证，验证某个已认证的用户是否拥有某个权限；即判断用户是否能做事情，常见的如：验证某个用户是否拥有某个角色。或者细粒度的验证某个用户对某个资源是否具有某个权限；
Session Manager：会话管理，即用户登录后就是一次会话，在没有退出之前，它的所有信息都在会话中；会话可以是普通 JavaSE 环境的，也可以是如 Web 环境的；
Cryptography：加密，保护数据的安全性，如密码加密存储到数据库，而不是明文存储；
Web Support：Web 支持，可以非常容易的集成到 Web 环境；
Caching：缓存，比如用户登录后，其用户信息、拥有的角色 / 权限不必每次去查，这样可以提高效率；
Concurrency：shiro 支持多线程应用的并发验证，即如在一个线程中开启另一个线程，能把权限自动传播过去；
Testing：提供测试支持；
Run As：允许一个用户假装为另一个用户（如果他们允许）的身份进行访问；
Remember Me：记住我，这个是非常常见的功能，即一次登录后，下次再来的话不用登录了。
记住一点，Shiro 不会去维护用户、维护权限；这些需要我们自己去设计 / 提供；然后通过相应的接口注入给 Shiro 即可。
接下来我们分别从外部和内部来看看 Shiro 的架构，对于一个好的框架，从外部来看应该具有非常简单易于使用的 API，且 API 契约明确；从内部来看的话，其应该有一个可扩展的架构，即非常容易插入用户自定义实现，因为任何框架都不能满足所有需求。
首先，我们从外部来看 Shiro 吧，即从应用程序角度的来观察如何使用 Shiro 完成工作。如下图：

可以看到：应用代码直接交互的对象是 Subject，也就是说 Shiro 的对外 API 核心就是 Subject；其每个 API 的含义：
Subject：主体，代表了当前 “用户”，这个用户不一定是一个具体的人，与当前应用交互的任何东西都是 Subject，如网络爬虫，机器人等；即一个抽象概念；所有 Subject 都绑定到 SecurityManager，与 Subject 的所有交互都会委托给 SecurityManager；可以把 Subject 认为是一个门面；SecurityManager 才是实际的执行者；
SecurityManager：安全管理器；即所有与安全有关的操作都会与 SecurityManager 交互；且它管理着所有 Subject；可以看出它是 Shiro 的核心，它负责与后边介绍的其他组件进行交互，如果学习过 SpringMVC，你可以把它看成 DispatcherServlet 前端控制器；
Realm：域，Shiro 从从 Realm 获取安全数据（如用户、角色、权限），就是说 SecurityManager 要验证用户身份，那么它需要从 Realm 获取相应的用户进行比较以确定用户身份是否合法；也需要从 Realm 得到用户相应的角色 / 权限进行验证用户是否能进行操作；可以把 Realm 看成 DataSource，即安全数据源。
也就是说对于我们而言，最简单的一个 Shiro 应用：
应用代码通过 Subject 来进行认证和授权，而 Subject 又委托给 SecurityManager；
我们需要给 Shiro 的 SecurityManager 注入 Realm，从而让 SecurityManager 能得到合法的用户及其权限进行判断。
从以上也可以看出，Shiro 不提供维护用户 / 权限，而是通过 Realm 让开发人员自己注入。
接下来我们来从 Shiro 内部来看下 Shiro 的架构，如下图所示：

Subject：主体，可以看到主体可以是任何可以与应用交互的 “用户”；
SecurityManager：相当于 SpringMVC 中的 DispatcherServlet 或者 Struts2 中的 FilterDispatcher；是 Shiro 的心脏；所有具体的交互都通过 SecurityManager 进行控制；它管理着所有 Subject、且负责进行认证和授权、及会话、缓存的管理。
Authenticator：认证器，负责主体认证的，这是一个扩展点，如果用户觉得 Shiro 默认的不好，可以自定义实现；其需要认证策略（Authentication Strategy），即什么情况下算用户认证通过了；
Authrizer：授权器，或者访问控制器，用来决定主体是否有权限进行相应的操作；即控制着用户能访问应用中的哪些功能；
Realm：可以有 1 个或多个 Realm，可以认为是安全实体数据源，即用于获取安全实体的；可以是 JDBC 实现，也可以是 LDAP 实现，或者内存实现等等；由用户提供；注意：Shiro 不知道你的用户 / 权限存储在哪及以何种格式存储；所以我们一般在应用中都需要实现自己的 Realm；
SessionManager：如果写过 Servlet 就应该知道 Session 的概念，Session 呢需要有人去管理它的生命周期，这个组件就是 SessionManager；而 Shiro 并不仅仅可以用在 Web 环境，也可以用在如普通的 JavaSE 环境、EJB 等环境；所有呢，Shiro 就抽象了一个自己的 Session 来管理主体与应用之间交互的数据；这样的话，比如我们在 Web 环境用，刚开始是一台 Web 服务器；接着又上了台 EJB 服务器；这时想把两台服务器的会话数据放到一个地方，这个时候就可以实现自己的分布式会话（如把数据放到 Memcached 服务器）；
SessionDAO：DAO 大家都用过，数据访问对象，用于会话的 CRUD，比如我们想把 Session 保存到数据库，那么可以实现自己的 SessionDAO，通过如 JDBC 写到数据库；比如想把 Session 放到 Memcached 中，可以实现自己的 Memcached SessionDAO；另外 SessionDAO 中可以使用 Cache 进行缓存，以提高性能；
CacheManager：缓存控制器，来管理如用户、角色、权限等的缓存的；因为这些数据基本上很少去改变，放到缓存中后可以提高访问的性能
Cryptography：密码模块，Shiro 提高了一些常见的加密组件用于如密码加密 / 解密的。
到此 Shiro 架构及其组件就认识完了，接下来挨着学习 Shiro 的组件吧。
</div>
<div id="cp3">
Shiro 身份验证
由 北公爵无欢 创建， 最后一次修改 2016-08-12
身份验证

身份验证，即在应用中谁能证明他就是他本人。一般提供如他们的身份 ID 一些标识信息来表明他就是他本人，如提供身份证，用户名 / 密码来证明。
在 shiro 中，用户需要提供 principals （身份）和 credentials（证明）给 shiro，从而应用能验证用户身份：
principals：身份，即主体的标识属性，可以是任何东西，如用户名、邮箱等，唯一即可。一个主体可以有多个 principals，但只有一个 Primary principals，一般是用户名 / 密码 / 手机号。
credentials：证明 / 凭证，即只有主体知道的安全值，如密码 / 数字证书等。
最常见的 principals 和 credentials 组合就是用户名 / 密码了。接下来先进行一个基本的身份认证。
另外两个相关的概念是之前提到的 Subject 及 Realm，分别是主体及验证主体的数据源。
环境准备

本文使用 Maven 构建，因此需要一点 Maven 知识。首先准备环境依赖：
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.9</version>
    </dependency>
    <dependency>
        <groupId>commons-logging</groupId>
        <artifactId>commons-logging</artifactId>
        <version>1.1.3</version>
    </dependency>
    <dependency>
        <groupId>org.apache.shiro</groupId>
        <artifactId>shiro-core</artifactId>
        <version>1.2.2</version>
    </dependency>
</dependencies>
添加 junit、common-logging 及 shiro-core 依赖即可。
登录 / 退出

1、首先准备一些用户身份 / 凭据（shiro.ini）
[users]
zhang=123
wang=123
此处使用 ini 配置文件，通过 [users] 指定了两个主体：zhang/123、wang/123。
2、测试用例（com.github.zhangkaitao.shiro.chapter2.LoginLogoutTest）
@Test
public void testHelloworld() {
    //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager  Factory<org.apache.shiro.mgt.SecurityManager> factory =
            new IniSecurityManagerFactory("classpath:shiro.ini");
    //2、得到SecurityManager实例 并绑定给SecurityUtils   org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
    SecurityUtils.setSecurityManager(securityManager);
    //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
    Subject subject = SecurityUtils.getSubject();
    UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
    try {
        //4、登录，即身份验证
        subject.login(token);
    } catch (AuthenticationException e) {
        //5、身份验证失败
    }
    Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
    //6、退出
    subject.logout();
}
首先通过 new IniSecurityManagerFactory 并指定一个 ini 配置文件来创建一个 SecurityManager 工厂；
接着获取 SecurityManager 并绑定到 SecurityUtils，这是一个全局设置，设置一次即可；
通过 SecurityUtils 得到 Subject，其会自动绑定到当前线程；如果在 web 环境在请求结束时需要解除绑定；然后获取身份验证的 Token，如用户名 / 密码；
调用 subject.login 方法进行登录，其会自动委托给 SecurityManager.login 方法进行登录；
如果身份验证失败请捕获 AuthenticationException 或其子类，常见的如： DisabledAccountException（禁用的帐号）、LockedAccountException（锁定的帐号）、UnknownAccountException（错误的帐号）、ExcessiveAttemptsException（登录失败次数过多）、IncorrectCredentialsException （错误的凭证）、ExpiredCredentialsException（过期的凭证）等，具体请查看其继承关系；对于页面的错误消息展示，最好使用如 “用户名 / 密码错误” 而不是 “用户名错误”/“密码错误”，防止一些恶意用户非法扫描帐号库；
最后可以调用 subject.logout 退出，其会自动委托给 SecurityManager.logout 方法退出。
从如上代码可总结出身份验证的步骤：
收集用户身份 / 凭证，即如用户名 / 密码；
调用 Subject.login 进行登录，如果失败将得到相应的 AuthenticationException 异常，根据异常提示用户错误信息；否则登录成功；
最后调用 Subject.logout 进行退出操作。
如上测试的几个问题：
用户名 / 密码硬编码在 ini 配置文件，以后需要改成如数据库存储，且密码需要加密存储；
用户身份 Token 可能不仅仅是用户名 / 密码，也可能还有其他的，如登录时允许用户名 / 邮箱 / 手机号同时登录。
身份认证流程


流程如下：
首先调用 Subject.login(token) 进行登录，其会自动委托给 Security Manager，调用之前必须通过 SecurityUtils.setSecurityManager() 设置；
SecurityManager 负责真正的身份验证逻辑；它会委托给 Authenticator 进行身份验证；
Authenticator 才是真正的身份验证者，Shiro API 中核心的身份认证入口点，此处可以自定义插入自己的实现；
Authenticator 可能会委托给相应的 AuthenticationStrategy 进行多 Realm 身份验证，默认 ModularRealmAuthenticator 会调用 AuthenticationStrategy 进行多 Realm 身份验证；
Authenticator 会把相应的 token 传入 Realm，从 Realm 获取身份验证信息，如果没有返回 / 抛出异常表示身份验证失败了。此处可以配置多个 Realm，将按照相应的顺序及策略进行访问。
Realm

Realm：域，Shiro 从从 Realm 获取安全数据（如用户、角色、权限），就是说 SecurityManager 要验证用户身份，那么它需要从 Realm 获取相应的用户进行比较以确定用户身份是否合法；也需要从 Realm 得到用户相应的角色 / 权限进行验证用户是否能进行操作；可以把 Realm 看成 DataSource，即安全数据源。如我们之前的 ini 配置方式将使用 org.apache.shiro.realm.text.IniRealm。
org.apache.shiro.realm.Realm 接口如下：
String getName(); //返回一个唯一的Realm名字
boolean supports(AuthenticationToken token); //判断此Realm是否支持此Token
AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
 throws AuthenticationException;  //根据Token获取认证信息
单 Realm 配置
1、自定义 Realm 实现（com.github.zhangkaitao.shiro.chapter2.realm.MyRealm1）：
public class MyRealm1 implements Realm {
    @Override
    public String getName() {
        return "myrealm1";
    }
    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持UsernamePasswordToken类型的Token
        return token instanceof UsernamePasswordToken; 
    }
    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();  //得到用户名
        String password = new String((char[])token.getCredentials()); //得到密码
        if(!"zhang".equals(username)) {
            throw new UnknownAccountException(); //如果用户名错误
        }
        if(!"123".equals(password)) {
            throw new IncorrectCredentialsException(); //如果密码错误
        }
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}&nbsp;
2、ini 配置文件指定自定义 Realm 实现 (shiro-realm.ini)
\#声明一个realm
myRealm1=com.github.zhangkaitao.shiro.chapter2.realm.MyRealm1
\#指定securityManager的realms实现
securityManager.realms=$myRealm1&nbsp;
通过 $name 来引入之前的 realm 定义
3、测试用例请参考 com.github.zhangkaitao.shiro.chapter2.LoginLogoutTest 的 testCustomRealm 测试方法，只需要把之前的 shiro.ini 配置文件改成 shiro-realm.ini 即可。
多 Realm 配置
1、ini 配置文件（shiro-multi-realm.ini）
\#声明一个realm
myRealm1=com.github.zhangkaitao.shiro.chapter2.realm.MyRealm1
myRealm2=com.github.zhangkaitao.shiro.chapter2.realm.MyRealm2
\#指定securityManager的realms实现
securityManager.realms=$myRealm1,$myRealm2&nbsp;
securityManager 会按照 realms 指定的顺序进行身份认证。此处我们使用显示指定顺序的方式指定了 Realm 的顺序，如果删除 “securityManager.realms=$myRealm1,$myRealm2”，那么securityManager 会按照 realm 声明的顺序进行使用（即无需设置 realms 属性，其会自动发现），当我们显示指定 realm 后，其他没有指定 realm 将被忽略，如 “securityManager.realms=$myRealm1”，那么 myRealm2 不会被自动设置进去。
2、测试用例请参考 com.github.zhangkaitao.shiro.chapter2.LoginLogoutTest 的 testCustomMultiRealm 测试方法。
Shiro 默认提供的 Realm

以后一般继承 AuthorizingRealm（授权）即可；其继承了 AuthenticatingRealm（即身份验证），而且也间接继承了 CachingRealm（带有缓存实现）。其中主要默认实现如下：
org.apache.shiro.realm.text.IniRealm：[users] 部分指定用户名 / 密码及其角色；[roles] 部分指定角色即权限信息；
org.apache.shiro.realm.text.PropertiesRealm： user.username=password,role1,role2 指定用户名 / 密码及其角色；role.role1=permission1,permission2 指定角色及权限信息；
org.apache.shiro.realm.jdbc.JdbcRealm：通过 sql 查询相应的信息，如 “select password from users where username = ?” 获取用户密码，“select password, password_salt from users where username = ?” 获取用户密码及盐；“select role_name from user_roles where username = ?” 获取用户角色；“select permission from roles_permissions where role_name = ?” 获取角色对应的权限信息；也可以调用相应的 api 进行自定义 sql；
JDBC Realm 使用
1、数据库及依赖
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.25</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>0.2.23</version>
        </dependency>&nbsp;
本文将使用 mysql 数据库及 druid 连接池；
2、到数据库 shiro 下建三张表：users（用户名 / 密码）、user_roles（用户 / 角色）、roles_permissions（角色 / 权限），具体请参照 shiro-example-chapter2/sql/shiro.sql；并添加一个用户记录，用户名 / 密码为 zhang/123；
3、ini 配置（shiro-jdbc-realm.ini）
jdbcRealm=org.apache.shiro.realm.jdbc.JdbcRealm
dataSource=com.alibaba.druid.pool.DruidDataSource
dataSource.driverClassName=com.mysql.jdbc.Driver
dataSource.url=jdbc:mysql://localhost:3306/shiro
dataSource.username=root
\#dataSource.password=
jdbcRealm.dataSource=$dataSource
securityManager.realms=$jdbcRealm&nbsp;
变量名 = 全限定类名会自动创建一个类实例
变量名. 属性 = 值 自动调用相应的 setter 方法进行赋值
$ 变量名 引用之前的一个对象实例
测试代码请参照 com.github.zhangkaitao.shiro.chapter2.LoginLogoutTest 的 testJDBCRealm 方法，和之前的没什么区别。
Authenticator 及 AuthenticationStrategy

Authenticator 的职责是验证用户帐号，是 Shiro API 中身份验证核心的入口点：
public AuthenticationInfo authenticate(AuthenticationToken authenticationToken)
            throws AuthenticationException;&nbsp;
如果验证成功，将返回 AuthenticationInfo 验证信息；此信息中包含了身份及凭证；如果验证失败将抛出相应的 AuthenticationException 实现。
SecurityManager 接口继承了 Authenticator，另外还有一个 ModularRealmAuthenticator 实现，其委托给多个 Realm 进行验证，验证规则通过 AuthenticationStrategy 接口指定，默认提供的实现：
FirstSuccessfulStrategy：只要有一个 Realm 验证成功即可，只返回第一个 Realm 身份验证成功的认证信息，其他的忽略；
AtLeastOneSuccessfulStrategy：只要有一个 Realm 验证成功即可，和 FirstSuccessfulStrategy 不同，返回所有 Realm 身份验证成功的认证信息；
AllSuccessfulStrategy：所有 Realm 验证成功才算成功，且返回所有 Realm 身份验证成功的认证信息，如果有一个失败就失败了。
ModularRealmAuthenticator 默认使用 AtLeastOneSuccessfulStrategy 策略。
假设我们有三个 realm：
myRealm1： 用户名 / 密码为 zhang/123 时成功，且返回身份 / 凭据为 zhang/123；
myRealm2： 用户名 / 密码为 wang/123 时成功，且返回身份 / 凭据为 wang/123；
myRealm3： 用户名 / 密码为 zhang/123 时成功，且返回身份 / 凭据为 zhang@163.com/123，和 myRealm1 不同的是返回时的身份变了；
1、ini 配置文件 (shiro-authenticator-all-success.ini)
\#指定securityManager的authenticator实现
authenticator=org.apache.shiro.authc.pam.ModularRealmAuthenticator
securityManager.authenticator=$authenticator
\#指定securityManager.authenticator的authenticationStrategy
allSuccessfulStrategy=org.apache.shiro.authc.pam.AllSuccessfulStrategy
securityManager.authenticator.authenticationStrategy=$allSuccessfulStrategy
myRealm1=com.github.zhangkaitao.shiro.chapter2.realm.MyRealm1
myRealm2=com.github.zhangkaitao.shiro.chapter2.realm.MyRealm2
myRealm3=com.github.zhangkaitao.shiro.chapter2.realm.MyRealm3
securityManager.realms=$myRealm1,$myRealm3
2、测试代码（com.github.zhangkaitao.shiro.chapter2.AuthenticatorTest）
首先通用化登录逻辑
    private void login(String configFile) {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
                new IniSecurityManagerFactory(configFile);
        //2、得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        subject.login(token);
    }
测试 AllSuccessfulStrategy 成功：
    @Test
    public void testAllSuccessfulStrategyWithSuccess() {
        login("classpath:shiro-authenticator-all-success.ini");
        Subject subject = SecurityUtils.getSubject();
        //得到一个身份集合，其包含了Realm验证成功的身份信息
        PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(2, principalCollection.asList().size());
    }&nbsp;
即 PrincipalCollection 包含了 zhang 和 zhang@163.com 身份信息。
测试 AllSuccessfulStrategy 失败：
    @Test(expected = UnknownAccountException.class)
    public void testAllSuccessfulStrategyWithFail() {
        login("classpath:shiro-authenticator-all-fail.ini");
        Subject subject = SecurityUtils.getSubject();
}&nbsp;
shiro-authenticator-all-fail.ini 与 shiro-authenticator-all-success.ini 不同的配置是使用了 securityManager.realms=$myRealm1，$myRealm2；即 myRealm 验证失败。
对于 AtLeastOneSuccessfulStrategy 和 FirstSuccessfulStrategy 的区别，请参照 testAtLeastOneSuccessfulStrategyWithSuccess 和 testFirstOneSuccessfulStrategyWithSuccess 测试方法。唯一不同点一个是返回所有验证成功的 Realm 的认证信息；另一个是只返回第一个验证成功的 Realm 的认证信息。
自定义 AuthenticationStrategy 实现，首先看其 API：
//在所有Realm验证之前调用
AuthenticationInfo beforeAllAttempts(
Collection<? extends Realm> realms, AuthenticationToken token) 
throws AuthenticationException;
//在每个Realm之前调用
AuthenticationInfo beforeAttempt(
Realm realm, AuthenticationToken token, AuthenticationInfo aggregate) 
throws AuthenticationException;
//在每个Realm之后调用
AuthenticationInfo afterAttempt(
Realm realm, AuthenticationToken token, 
AuthenticationInfo singleRealmInfo, AuthenticationInfo aggregateInfo, Throwable t)
throws AuthenticationException;
//在所有Realm之后调用
AuthenticationInfo afterAllAttempts(
AuthenticationToken token, AuthenticationInfo aggregate) 
throws AuthenticationException;&nbsp;
因为每个 AuthenticationStrategy 实例都是无状态的，所有每次都通过接口将相应的认证信息传入下一次流程；通过如上接口可以进行如合并 / 返回第一个验证成功的认证信息。
自定义实现时一般继承 org.apache.shiro.authc.pam.AbstractAuthenticationStrategy 即可，具体可以参考代码 com.github.zhangkaitao.shiro.chapter2.authenticator.strategy 包下 OnlyOneAuthenticatorStrategy 和 AtLeastTwoAuthenticatorStrategy。
到此基本的身份验证就搞定了，对于 AuthenticationToken 、AuthenticationInfo 和 Realm 的详细使用后续章节再陆续介绍。
</div>
<div id="cp4">
<div class="content-top">
<h1>Shiro 授权</h1>
</div>
<div class="kn-infomation">
由&nbsp;<span>北公爵无欢</span>&nbsp;创建， 最后一次修改&nbsp;<span>2016-08-12</span>    
</div>          
</div>          
<div class="content-bg">
<div class="content-intro view-box "><div class="wkcontent"><h2>授权</h2>
<p>授权，也叫访问控制，即在应用中控制谁能访问哪些资源（如访问页面/编辑数据/页面操作等）。在授权中需了解的几个关键对象：主体（Subject）、资源（Resource）、权限（Permission）、角色（Role）。  </p>
<p><strong>主体</strong><br />
主体，即访问应用的用户，在 Shiro 中使用 Subject 代表该用户。用户只有授权后才允许访问相应的资源。</p>
<p><strong>资源</strong><br />
在应用中用户可以访问的任何东西，比如访问 JSP 页面、查看/编辑某些数据、访问某个业务方法、打印文本等等都是资源。用户只要授权后才能访问。</p>
<p><strong>权限</strong><br />
安全策略中的原子授权单位，通过权限我们可以表示在应用中用户有没有操作某个资源的权力。即权限表示在应用中用户能不能访问某个资源，如：
访问用户列表页面<br />
查看/新增/修改/删除用户数据（即很多时候都是 CRUD（增查改删）式权限控制）<br />
打印文档等等。。。</p>
<p>如上可以看出，权限代表了用户有没有操作某个资源的权利，即反映在某个资源上的操作允不允许，不反映谁去执行这个操作。所以后续还需要把权限赋予给用户，即定义哪个用户允许在某个资源上做什么操作（权限），Shiro 不会去做这件事情，而是由实现人员提供。  </p>
<p>Shiro 支持粗粒度权限（如用户模块的所有权限）和细粒度权限（操作某个用户的权限，即实例级别的），后续部分介绍。  </p>
<p><strong>角色</strong><br />
角色代表了操作集合，可以理解为权限的集合，一般情况下我们会赋予用户角色而不是权限，即这样用户可以拥有一组权限，赋予权限时比较方便。典型的如：项目经理、技术总监、CTO、开发工程师等都是角色，不同的角色拥有一组不同的权限。</p>
<p><strong>隐式角色</strong>：<br />
即直接通过角色来验证用户有没有操作权限，如在应用中 CTO、技术总监、开发工程师可以使用打印机，假设某天不允许开发工程师使用打印机，此时需要从应用中删除相应代码；再如在应用中 CTO、技术总监可以查看用户、查看权限；突然有一天不允许技术总监查看用户、查看权限了，需要在相关代码中把技术总监角色从判断逻辑中删除掉；即粒度是以角色为单位进行访问控制的，粒度较粗；如果进行修改可能造成多处代码修改。</p>
<p><strong>显示角色</strong>：<br />
在程序中通过权限控制谁能访问某个资源，角色聚合一组权限集合；这样假设哪个角色不能访问某个资源，只需要从角色代表的权限集合中移除即可；无须修改多处代码；即粒度是以资源/实例为单位的；粒度较细。</p>
<p>请 google 搜索“RBAC”和“RBAC新解”分别了解“基于角色的访问控制”“基于资源的访问控制(Resource-Based Access Control)”。</p>
<h2 class="head2">授权方式</h2>
<p>Shiro 支持三种方式的授权：  </p>
<p>编程式：通过写 if/else 授权代码块完成：</p>
<pre><code>Subject subject = SecurityUtils.getSubject();
if(subject.hasRole(“admin”)) {
    //有权限
} else {
    //无权限
}&amp;nbsp;</code></pre>
<p>注解式：通过在执行的 Java 方法上放置相应的注解完成： </p>
<pre><code>@RequiresRoles("admin")
public void hello() {
    //有权限
}&amp;nbsp;</code></pre>
<p>没有权限将抛出相应的异常；</p>
<p>JSP/GSP 标签：在 JSP/GSP 页面通过相应的标签完成： </p>
<pre><code>&lt;shiro:hasRole name="admin"&gt;
&lt;!— 有权限 —&gt;
&lt;/shiro:hasRole&gt;&amp;nbsp;</code></pre>
<p>后续部分将详细介绍如何使用。</p>
<h2 class="head2">授权</h2>
<p><strong>基于角色的访问控制（隐式角色）</strong></p>
<p>1、在 ini 配置文件配置用户拥有的角色（shiro-role.ini） </p>
<pre><code>[users]
zhang=123,role1,role2
wang=123,role1&amp;nbsp;</code></pre>
<p>规则即：“用户名=密码,角色1，角色2”，如果需要在应用中判断用户是否有相应角色，就需要在相应的 Realm 中返回角色信息，也就是说 Shiro 不负责维护用户-角色信息，需要应用提供，Shiro 只是提供相应的接口方便验证，后续会介绍如何动态的获取用户角色。</p>
<p>2、测试用例（com.github.zhangkaitao.shiro.chapter3.RoleTest）</p>
<pre><code>    @Test
    public void testHasRole() {
        login("classpath:shiro-role.ini", "zhang", "123");
        //判断拥有角色：role1
        Assert.assertTrue(subject().hasRole("role1"));
        //判断拥有角色：role1 and role2
        Assert.assertTrue(subject().hasAllRoles(Arrays.asList("role1", "role2")));
        //判断拥有角色：role1 and role2 and !role3
        boolean[] result = subject().hasRoles(Arrays.asList("role1", "role2", "role3"));
        Assert.assertEquals(true, result[0]);
        Assert.assertEquals(true, result[1]);
        Assert.assertEquals(false, result[2]);
    }&amp;nbsp;</code></pre>
<p>Shiro 提供了 hasRole/hasRole 用于判断用户是否拥有某个角色/某些权限；但是没有提供如 hashAnyRole 用于判断是否有某些权限中的某一个。 </p>
<pre><code>    @Test(expected = UnauthorizedException.class)
    public void testCheckRole() {
        login("classpath:shiro-role.ini", "zhang", "123");
        //断言拥有角色：role1
        subject().checkRole("role1");
        //断言拥有角色：role1 and role3 失败抛出异常
        subject().checkRoles("role1", "role3");
    }&amp;nbsp;</code></pre>
<p>Shiro 提供的 checkRole/checkRoles 和 hasRole/hasAllRoles 不同的地方是它在判断为假的情况下会抛出 UnauthorizedException 异常。</p>
<p>到此基于角色的访问控制（即隐式角色）就完成了，这种方式的缺点就是如果很多地方进行了角色判断，但是有一天不需要了那么就需要修改相应代码把所有相关的地方进行删除；这就是粗粒度造成的问题。  </p>
<p><strong>基于资源的访问控制（显示角色）</strong>  </p>
<p>1、在 ini 配置文件配置用户拥有的角色及角色-权限关系（shiro-permission.ini）  </p>
<pre><code>[users]
zhang=123,role1,role2
wang=123,role1
[roles]
role1=user:create,user:update
role2=user:create,user:delete&amp;nbsp;</code></pre>
<p>规则：“用户名=密码，角色 1，角色 2”“角色=权限 1，权限 2”，即首先根据用户名找到角色，然后根据角色再找到权限；即角色是权限集合；Shiro 同样不进行权限的维护，需要我们通过 Realm 返回相应的权限信息。只需要维护“用户——角色”之间的关系即可。  </p>
<p>2、测试用例（com.github.zhangkaitao.shiro.chapter3.PermissionTest）  </p>
<pre><code>    @Test
    public void testIsPermitted() {
        login("classpath:shiro-permission.ini", "zhang", "123");
        //判断拥有权限：user:create
        Assert.assertTrue(subject().isPermitted("user:create"));
        //判断拥有权限：user:update and user:delete
        Assert.assertTrue(subject().isPermittedAll("user:update", "user:delete"));
        //判断没有权限：user:view
        Assert.assertFalse(subject().isPermitted("user:view"));
    }&amp;nbsp;</code></pre>
<p>Shiro 提供了 isPermitted 和 isPermittedAll 用于判断用户是否拥有某个权限或所有权限，也没有提供如 isPermittedAny 用于判断拥有某一个权限的接口。</p>
<pre><code>    @Test(expected = UnauthorizedException.class)
    public void testCheckPermission () {
        login("classpath:shiro-permission.ini", "zhang", "123");
        //断言拥有权限：user:create
        subject().checkPermission("user:create");
        //断言拥有权限：user:delete and user:update
        subject().checkPermissions("user:delete", "user:update");
        //断言拥有权限：user:view 失败抛出异常
        subject().checkPermissions("user:view");
    }&amp;nbsp;</code></pre>
<p>但是失败的情况下会抛出 UnauthorizedException 异常。</p>
<p>到此基于资源的访问控制（显示角色）就完成了，也可以叫基于权限的访问控制，这种方式的一般规则是“资源标识符：操作”，即是资源级别的粒度；这种方式的好处就是如果要修改基本都是一个资源级别的修改，不会对其他模块代码产生影响，粒度小。但是实现起来可能稍微复杂点，需要维护“用户——角色，角色——权限（资源：操作）”之间的关系。   </p>
<h2 class="head2">Permission</h2>
<h3>字符串通配符权限</h3>
<p>规则：“资源标识符：操作：对象实例 ID”  即对哪个资源的哪个实例可以进行什么操作。其默认支持通配符权限字符串，“:”表示资源/操作/实例的分割；“,”表示操作的分割；“*”表示任意资源/操作/实例。  </p>
<p><strong>1、单个资源单个权限</strong></p>
<p><code>subject().checkPermissions("system:user:update");</code></p>
<p>用户拥有资源“system:user”的“update”权限。</p>
<p><strong>2、单个资源多个权限</strong></p>
<p><code>role41=system:user:update,system:user:delete</code></p>
<p>然后通过如下代码判断</p>
<p><code>subject().checkPermissions("system:user:update", "system:user:delete");</code></p>
<p>用户拥有资源“system:user”的“update”和“delete”权限。如上可以简写成：</p>
<p>ini 配置（表示角色4拥有 system:user 资源的 update 和 delete 权限）</p>
<p><code>role42="system:user:update,delete"</code></p>
<p>接着可以通过如下代码判断 </p>
<p><code>subject().checkPermissions("system:user:update,delete");</code></p>
<p>通过“system:user:update,delete”验证“system:user:update, system:user:delete”是没问题的，但是反过来是规则不成立。</p>
<p><strong>3、单个资源全部权限</strong></p>
<p>ini 配置 </p>
<p><code>role51="system:user:create,update,delete,view"</code></p>
<p>然后通过如下代码判断 </p>
<p><code>subject().checkPermissions("system:user:create,delete,update:view");</code></p>
<p>用户拥有资源“system:user”的“create”、“update”、“delete”和“view”所有权限。如上可以简写成：</p>
<p>ini 配置文件（表示角色 5 拥有 system:user 的所有权限）</p>
<p><code>role52=system:user:*</code></p>
<p>也可以简写为（推荐上边的写法）：</p>
<p><code>role53=system:user</code></p>
<p>然后通过如下代码判断</p>
<pre><code>subject().checkPermissions("system:user:*");
subject().checkPermissions("system:user");&amp;nbsp;</code></pre>
<p>通过“system:user:*”验证“system:user:create,delete,update:view”可以，但是反过来是不成立的。</p>
<p><strong>4、所有资源全部权限</strong></p>
<p>ini 配置 </p>
<p><code>role61=*:view</code> </p>
<p>然后通过如下代码判断</p>
<p><code>subject().checkPermissions("user:view");</code></p>
<p>用户拥有所有资源的“view”所有权限。假设判断的权限是“&quot;system:user:view”，那么需要“role5=<em>:</em>:view”这样写才行</p>
<p><strong>5、实例级别的权限</strong></p>
<ul>
<li>单个实例单个权限</li>
</ul>
<p>ini 配置</p>
<p><code>role71=user:view:1</code></p>
<p>对资源 user 的 1 实例拥有 view 权限。</p>
<p>然后通过如下代码判断  </p>
<p><code>subject().checkPermissions("user:view:1");</code></p>
<ul>
<li>单个实例多个权限</li>
</ul>
<p>ini 配置  </p>
<p><code>role72="user:update,delete:1"</code></p>
<p>对资源 user 的 1 实例拥有 update、delete 权限。  </p>
<p>然后通过如下代码判断</p>
<pre><code>subject().checkPermissions("user:delete,update:1");
subject().checkPermissions("user:update:1", "user:delete:1");&amp;nbsp;</code></pre>
<ul>
<li>单个实例所有权限</li>
</ul>
<p>ini 配置</p>
<p><code>role73=user:*:1</code></p>
<p>对资源 user 的 1 实例拥有所有权限。  </p>
<p>然后通过如下代码判断  </p>
<p><code>subject().checkPermissions("user:update:1", "user:delete:1", "user:view:1");</code></p>
<ul>
<li>所有实例单个权限 </li>
</ul>
<p>ini 配置</p>
<p><code>role74=user:auth:*</code></p>
<p>对资源 user 的 1 实例拥有所有权限。  </p>
<p>然后通过如下代码判断  </p>
<p><code>subject().checkPermissions("user:auth:1", "user:auth:2");</code></p>
<ul>
<li>所有实例所有权限</li>
</ul>
<p>ini 配置</p>
<p><code>role75=user:*:*</code></p>
<p>对资源 user 的 1 实例拥有所有权限。  </p>
<p>然后通过如下代码判断   </p>
<p><code>subject().checkPermissions("user:view:1", "user:auth:2");</code></p>
<p><strong>6、Shiro 对权限字符串缺失部分的处理</strong></p>
<p>如“user:view”等价于“<code>user:view:*</code>”；而“organization”等价于“<code>organization:*</code>”或者“<code>organization:*:*</code>”。可以这么理解，这种方式实现了前缀匹配。  </p>
<p>另外如“<code>user:*</code>”可以匹配如“<code>user:delete</code>”、“<code>user:delete</code>”可以匹配如“<code>user:delete:1</code>”、“<code>user:*:1</code>”可以匹配如“user:view:1”、“user”可以匹配“<code>user:view</code>”或“<code>user:view:1</code>”等。即<code>*</code>可以匹配所有，不加<code>*</code>可以进行前缀匹配；但是如“<code>*:view</code>”不能匹配“<code>system:user:view</code>”，需要使用“<code>*:*:view</code>”，即后缀匹配必须指定前缀（多个冒号就需要多个<code>*</code>来匹配）。  </p>
<p><strong>7、WildcardPermission</strong></p>
<p>如下两种方式是等价的：</p>
<pre><code>subject().checkPermission("menu:view:1");
subject().checkPermission(new WildcardPermission("menu:view:1"));&amp;nbsp;</code></pre>
<p>因此没什么必要的话使用字符串更方便。</p>
<p><strong>8、性能问题</strong>  </p>
<p>通配符匹配方式比字符串相等匹配来说是更复杂的，因此需要花费更长时间，但是一般系统的权限不会太多，且可以配合缓存来提供其性能，如果这样性能还达不到要求我们可以实现位操作算法实现性能更好的权限匹配。另外实例级别的权限验证如果数据量太大也不建议使用，可能造成查询权限及匹配变慢。可以考虑比如在sql查询时加上权限字符串之类的方式在查询时就完成了权限匹配。</p>
<h2 class="head2">授权流程</h2>
<p><img src="//img.w3cschool.cn/attachments/image/wk/shiro/6.png" alt="" /></p>
<p>流程如下：  </p>
<ol>
<li>首先调用 <code>Subject.isPermitted*/hasRole*</code>接口，其会委托给 SecurityManager，而 SecurityManager 接着会委托给 Authorizer；</li>
<li>Authorizer 是真正的授权者，如果我们调用如 isPermitted(“user:view”)，其首先会通过 PermissionResolver 把字符串转换成相应的 Permission 实例；</li>
<li>在进行授权之前，其会调用相应的 Realm 获取 Subject 相应的角色/权限用于匹配传入的角色/权限；</li>
<li>Authorizer 会判断 Realm 的角色/权限是否和传入的匹配，如果有多个 Realm，会委托给 ModularRealmAuthorizer 进行循环判断，如果匹配如 <code>isPermitted*/hasRole*</code> 会返回 true，否则返回 false 表示授权失败。</li>
</ol>
<p>ModularRealmAuthorizer 进行多 Realm 匹配流程：</p>
<ul>
<li>首先检查相应的 Realm 是否实现了实现了 Authorizer；</li>
<li>如果实现了 Authorizer，那么接着调用其相应的 <code>isPermitted*/hasRole*</code> 接口进行匹配；</li>
<li>如果有一个 Realm 匹配那么将返回 true，否则返回 false。</li>
</ul>
<p>如果 Realm 进行授权的话，应该继承 AuthorizingRealm，其流程是：  </p>
<ul>
<li>如果调用 <code>hasRole*</code>，则直接获取 AuthorizationInfo.getRoles() 与传入的角色比较即可；首先如果调用如 isPermitted(“user:view”)，首先通过 PermissionResolver 将权限字符串转换成相应的 Permission 实例，默认使用 WildcardPermissionResolver，即转换为通配符的 WildcardPermission；  </li>
<li>通过 AuthorizationInfo.getObjectPermissions() 得到 Permission 实例集合；通过 AuthorizationInfo.getStringPermissions() 得到字符串集合并通过 PermissionResolver 解析为 Permission 实例；然后获取用户的角色，并通过 RolePermissionResolver 解析角色对应的权限集合（默认没有实现，可以自己提供）；  </li>
<li>接着调用 Permission.implies(Permission p) 逐个与传入的权限比较，如果有匹配的则返回 true，否则 false。</li>
</ul>
<h2 class="head2">Authorizer、PermissionResolver及RolePermissionResolver</h2>
<p>Authorizer 的职责是进行授权（访问控制），是 Shiro API 中授权核心的入口点，其提供了相应的角色/权限判断接口，具体请参考其 Javadoc。SecurityManager 继承了 Authorizer 接口，且提供了 ModularRealmAuthorizer 用于多 Realm 时的授权匹配。PermissionResolver 用于解析权限字符串到 Permission 实例，而 RolePermissionResolver 用于根据角色解析相应的权限集合。</p>
<p>我们可以通过如下 ini 配置更改 Authorizer 实现：</p>
<pre><code>authorizer=org.apache.shiro.authz.ModularRealmAuthorizer
securityManager.authorizer=$authorizer&amp;nbsp;</code></pre>
<p>对于 ModularRealmAuthorizer，相应的 AuthorizingSecurityManager 会在初始化完成后自动将相应的 realm 设置进去，我们也可以通过调用其 setRealms() 方法进行设置。对于实现自己的 authorizer 可以参考 ModularRealmAuthorizer 实现即可，在此就不提供示例了。</p>
<p>设置 ModularRealmAuthorizer 的 permissionResolver，其会自动设置到相应的 Realm 上（其实现了 PermissionResolverAware 接口），如：</p>
<pre><code>permissionResolver=org.apache.shiro.authz.permission.WildcardPermissionResolver
authorizer.permissionResolver=$permissionResolver&amp;nbsp;</code></pre>
<p>设置 ModularRealmAuthorizer 的 rolePermissionResolver，其会自动设置到相应的 Realm 上（其实现了 RolePermissionResolverAware 接口），如：  </p>
<pre><code>rolePermissionResolver=com.github.zhangkaitao.shiro.chapter3.permission.MyRolePermissionResolver
authorizer.rolePermissionResolver=$rolePermissionResolver&amp;nbsp;</code></pre>
<p><strong>示例</strong></p>
<p><strong>1、ini 配置（shiro-authorizer.ini）</strong></p>
<pre><code>[main]
\#自定义authorizer
authorizer=org.apache.shiro.authz.ModularRealmAuthorizer
\#自定义permissionResolver
\#permissionResolver=org.apache.shiro.authz.permission.WildcardPermissionResolver
permissionResolver=com.github.zhangkaitao.shiro.chapter3.permission.BitAndWildPermissionResolver
authorizer.permissionResolver=$permissionResolver
\#自定义rolePermissionResolver
rolePermissionResolver=com.github.zhangkaitao.shiro.chapter3.permission.MyRolePermissionResolver
authorizer.rolePermissionResolver=$rolePermissionResolver
securityManager.authorizer=$authorizer</code></pre>
<pre><code>\#自定义realm 一定要放在securityManager.authorizer赋值之后（因为调用setRealms会将realms设置给authorizer，并给各个Realm设置permissionResolver和rolePermissionResolver）
realm=com.github.zhangkaitao.shiro.chapter3.realm.MyRealm
securityManager.realms=$realm&amp;nbsp;</code></pre>
<p>设置 securityManager 的 realms 一定要放到最后，因为在调用 SecurityManager.setRealms 时会将 realms 设置给 authorizer，并为各个 Realm 设置 permissionResolver 和 rolePermissionResolver。另外，不能使用 IniSecurityManagerFactory 创建的 IniRealm，因为其初始化顺序的问题可能造成后续的初始化 Permission 造成影响。</p>
<p><strong>2、定义 BitAndWildPermissionResolver 及 BitPermission</strong></p>
<p>BitPermission 用于实现位移方式的权限，如规则是：  </p>
<p>权限字符串格式：+ 资源字符串 + 权限位 + 实例 ID；以 + 开头中间通过 + 分割；权限：0 表示所有权限；1 新增（二进制：0001）、2 修改（二进制：0010）、4 删除（二进制：0100）、8 查看（二进制：1000）；如 +user+10 表示对资源 user 拥有修改 / 查看权限。</p>
<pre><code>public class BitPermission implements Permission {
    private String resourceIdentify;
    private int permissionBit;
    private String instanceId;
    public BitPermission(String permissionString) {
        String[] array = permissionString.split("\\+");
        if(array.length &gt; 1) {
            resourceIdentify = array[1];
        }
        if(StringUtils.isEmpty(resourceIdentify)) {
            resourceIdentify = "*";
        }
        if(array.length &gt; 2) {
            permissionBit = Integer.valueOf(array[2]);
        }
        if(array.length &gt; 3) {
            instanceId = array[3];
        }
        if(StringUtils.isEmpty(instanceId)) {
            instanceId = "*";
        }
    }
    @Override
    public boolean implies(Permission p) {
        if(!(p instanceof BitPermission)) {
            return false;
        }
        BitPermission other = (BitPermission) p;
        if(!("*".equals(this.resourceIdentify) || this.resourceIdentify.equals(other.resourceIdentify))) {
            return false;
        }
        if(!(this.permissionBit ==0 || (this.permissionBit &amp; other.permissionBit) != 0)) {
            return false;
        }
        if(!("*".equals(this.instanceId) || this.instanceId.equals(other.instanceId))) {
            return false;
        }
        return true;
    }
}&amp;nbsp;</code></pre>
<p>Permission 接口提供了 boolean implies(Permission p) 方法用于判断权限匹配的；  </p>
<pre><code>public class BitAndWildPermissionResolver implements PermissionResolver {
    @Override
    public Permission resolvePermission(String permissionString) {
        if(permissionString.startsWith("+")) {
            return new BitPermission(permissionString);
        }
        return new WildcardPermission(permissionString);
    }
}&amp;nbsp;</code></pre>
<p>BitAndWildPermissionResolver 实现了 PermissionResolver 接口，并根据权限字符串是否以 “+” 开头来解析权限字符串为 BitPermission 或 WildcardPermission。  </p>
<p><strong>3、定义 MyRolePermissionResolver</strong></p>
<p>RolePermissionResolver 用于根据角色字符串来解析得到权限集合。  </p>
<pre><code>public class MyRolePermissionResolver implements RolePermissionResolver {
    @Override
    public Collection&lt;Permission&gt; resolvePermissionsInRole(String roleString) {
        if("role1".equals(roleString)) {
            return Arrays.asList((Permission)new WildcardPermission("menu:*"));
        }
        return null;
    }
}&amp;nbsp;</code></pre>
<p>此处的实现很简单，如果用户拥有 role1，那么就返回一个 “<code>menu:*</code>” 的权限。  </p>
<p><strong>4、自定义 Realm</strong></p>
<pre><code>public class MyRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole("role1");
        authorizationInfo.addRole("role2");
        authorizationInfo.addObjectPermission(new BitPermission("+user1+10"));
        authorizationInfo.addObjectPermission(new WildcardPermission("user1:*"));
        authorizationInfo.addStringPermission("+user2+10");
        authorizationInfo.addStringPermission("user2:*");
        return authorizationInfo;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //和com.github.zhangkaitao.shiro.chapter2.realm.MyRealm1. getAuthenticationInfo代码一样，省略
}
}&amp;nbsp;</code></pre>
<p>此时我们继承 AuthorizingRealm 而不是实现 Realm 接口；推荐使用 AuthorizingRealm，因为：
AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)：表示获取身份验证信息；AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)：表示根据用户身份获取授权信息。这种方式的好处是当只需要身份验证时只需要获取身份验证信息而不需要获取授权信息。对于 AuthenticationInfo 和 AuthorizationInfo 请参考其 Javadoc 获取相关接口信息。  </p>
<p>另外我们可以使用 JdbcRealm，需要做的操作如下：  </p>
<ol>
<li>执行 sql/ shiro-init-data.sql 插入相关的权限数据；  </li>
<li>使用 shiro-jdbc-authorizer.ini 配置文件，需要设置 jdbcRealm.permissionsLookupEnabled 为 true 来开启权限查询。</li>
</ol>
<p>此次还要注意就是不能把我们自定义的如 “+user1+10” 配置到 INI 配置文件，即使有 IniRealm 完成，因为 IniRealm 在 new 完成后就会解析这些权限字符串，默认使用了WildcardPermissionResolver 完成，即此处是一个设计权限，如果采用生命周期（如使用初始化方法）的方式进行加载就可以解决我们自定义 permissionResolver 的问题。</p>
<p><strong>5、测试用例</strong></p>
<pre><code>public class AuthorizerTest extends BaseTest {
    @Test
    public void testIsPermitted() {
        login("classpath:shiro-authorizer.ini", "zhang", "123");
        //判断拥有权限：user:create
        Assert.assertTrue(subject().isPermitted("user1:update"));
        Assert.assertTrue(subject().isPermitted("user2:update"));
        //通过二进制位的方式表示权限
        Assert.assertTrue(subject().isPermitted("+user1+2"));//新增权限
        Assert.assertTrue(subject().isPermitted("+user1+8"));//查看权限
        Assert.assertTrue(subject().isPermitted("+user2+10"));//新增及查看
        Assert.assertFalse(subject().isPermitted("+user1+4"));//没有删除权限
        Assert.assertTrue(subject().isPermitted("menu:view"));//通过MyRolePermissionResolver解析得到的权限
    }
}&amp;nbsp;  </code></pre>
<p>通过如上步骤可以实现自定义权限验证了。另外因为不支持 hasAnyRole/isPermittedAny 这种方式的授权，可以参考我的一篇《简单 shiro 扩展实现 NOT、AND、OR 权限验证 》进行简单的扩展完成这个需求，在这篇文章中通过重写 AuthorizingRealm 里的验证逻辑实现的。  </p></div></div>
<div style="clear:both"></div>
</div>
<!--我要赞赏-->
<div class="project-sq"></div>
<!--我要赞赏结束-->
<div class="content-links">
<div class="previous-link">← <a href="/shiro/xgj31if4.html" title="上一篇：Shiro 身份验证">Shiro 身份验证</a></div>
<div class="next-link"><a href="/shiro/h5it1if8.html" title="下一篇：Shiro InI 配置">Shiro InI 配置</a> →</div>
</div>
<!--写笔记-->
<div class="project-operation">
<a href="javascript:;" title="写笔记" class="note-btn" onclick="openNote()"><i class="icon-pencil"></i>写笔记</a>
</div>
<!--相关推荐|wiki推荐-->
<!--相关推荐|wiki推荐 结束-->
<!--横版广告放置-->
<div class="abox-item">    <div class="content-abox">        <a href="//www.w3cschool.cn/adlog?w3ctype=workview&amp;url=https://www.w3cschool.cn/article/5461759" target="_blank"><img src="/attachments/image/20171120/1511159975177003.png" alt="W3Cschool学编程上微课-边学边练" /></a>    </div></div></div>
<div class="navigation-box">
<a href="/shiro/xgj31if4.html" title="上一篇：Shiro 身份验证" class="navigation navigation-prev"><i class="icon-chevron-left"></i></a>
<a href="/shiro/h5it1if8.html" title="下一篇：Shiro InI 配置" class="navigation navigation-next"><i class="icon-chevron-right"></i></a>
</div>
  </div>
<div id="pro-footer">
<div class="s-footer">
<div class="s-footer-bottom">
<p>Copyright &copy; 2018 <a href="//www.w3cschool.cn" title="w3cschool">w3cschool.cn </a> All Rights Reserved. </p>
</div>
</div>
</div>
</div>
</div>
<div class="bottom-tools" style="bottom: 40px;">
<a id="scrollUp" href="javascript:;" title="飞回顶部"></a>
<a class="onlinenote" style="display: none;" title="在线笔记" href="javascript:;" data-module="page">在线笔记</a>
<div class="qr-tool">二维码</div>
<a id="feedback" class="webjy" title="建议反馈" href="javascript:;" data-module="page" onclick="showDialogBox(this)">建议反馈</a>    
<div class="qr-img"><img src="//statics.w3cschool.cn/images/w3c/w3cschool.jpg" alt="二维码"></div>
</div>

<div class="feedback-Box" style="display: none;">
<div class="feedback-Box-content" style="width: 800px;">
<div class="feedback-Box-top">
<span class="feedback-Box-title">意见反馈</span>
<span onclick="closeDialogBox(this)" class="feedback-Box-close">X</span>
</div>
<ul>
<li class="feedback-box" >
<div id="feedbackRadio" type="radio" name="feedbackType" data-value="0" style=""> 意见反馈：</div>
<textarea  name="reportInfo" id="elmaaa" class="feedback-text" rows="15" placeholder="请留下您宝贵的意见反馈吧。" style="width: 700px;height:200px;overflow:auto;" ></textarea>
<p>联系方式：</p>
<input type="text" placeholder="留个联系方式呗..." name="reportAddress" class="radio-text" id="reportAddress" style="height: 30px;width: 200px;">
</li>
</ul>
<button class="search-s" onclick="dialogSubmit(this)" id="reportSubmit">提交</button>
</div>
</div>

<div id="note-start" style="display: none"></div>

<div id="detail" style="display:none;">
<div id="note-Dialog" class="note-Dialog" >
<div class="note-Box-content" >
<div class="ntitlebox">
<input type="text" placeholder="" name="ntitle" class="ntitle" id="ntitle" >
</div>
<div id="editorarea">
<textarea  name="ncontent" id="ncontent" class="ncontent" rows="15" placeholder="" style="width: 700px;height:200px;overflow:auto;" ></textarea>
</div>
<p></p>
<a class="closenote" href="/my/note" target="_blank" >查看完整版笔记</a>
<button class="notesubmit"  id="notesubmit">保存</button>
<button class="notebtn pull-right closenote" style="margin:20px 0" >关闭</button>
</div>
</div>
</div>



</div>
<div id="cp5">cp5</div>
<div id="cp6">cp6</div>
<div id="cp7">cp7</div>
<div id="cp8">cp8</div>