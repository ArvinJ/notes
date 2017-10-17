# 一、架构

## 1. 表示层框架Struts2

Struts是一个在JSP Model2基础上实现的MVC框架，其运行流程如下图所示

![img](images/struts.png)

Struts2框架结构清晰，开发流程一目了然，开发人员可以很好的掌控开发的过程，有丰富的标签，提供了使用OGNL访问各种作用域中的数据的简单方式，大大简化了开发人员在获取这些数据时的代码量。此外，struts2还提供了强大的拦截器以及易于扩展的插件机制，对后续功能扩展非常方便。

 

*此外表示层框架还可以选择Struts、Spring/MVC，Webwork，JSF等几个Web框架；

*Struts2不是Struts1的升级，而是继承了webwork的血统，它吸收了struts1和webwork的优势。

*Spring MVC属于SpringFrameWork的后续产品，已经融合在SpringWeb Flow里面。Spring 框架提供了构建 Web应用程序的全功能 MVC 模块。使用 Spring 可插入的 [MVC](http://baike.baidu.com/view/31.htm) 架构，从而在使用Spring进行WEB开发时，可以选择使用Spring的SpringMVC框架或集成其他MVC开发框架，如Struts1，Struts2等，相对于struts2使用SpringMVC有如下区别：

**机制：**spring mvc的入口是servlet，而struts2是filter（这里要指出，filter                   和servlet是不同的。以前认为filter是 servlet的一种特殊），这样就导                致了二者的机制不同，这里就牵涉到servlet和filter的区别了。

**性能：**spring会稍微比struts快。springmvc是基于方法的设计，而sturts                    是基于类，每次发一次请求都会实例一个action，每个action都会被注                入属性，而spring基于方法，粒度更细，但要小心把握像在servlet控制               数据一样。spring3 mvc是方法级别的拦截，拦截到方法后根据参数上的          注解，把request数据注入进去，在spring3 mvc中，一个方法对应一个               request上下文。而struts2框架是类级别的拦截，每次来了请求就创建                一个Action，然后调用setter getter方法把request中的数据注入；struts2                   实际上是通过setter getter方法与request打交道的；struts2中，一个                 Action对象对应一个request上下文。

**参数传递：**struts是在接受参数的时候，可以用属性来接受参数，这就说                明参数是让多个方法共享的。

**设计思想上：**struts更加符合oop的编程思想， spring就比较谨慎，在                 servlet上扩展。

**intercepter****的实现机制：**struts有以自己的interceptor机制，spring mvc                    用的是独立的AOP方式。这样导致struts的配置文件量还是比spring mvc               大，虽然struts的配置能继承，所以我觉得论使用上来讲，spring mvc                    使用更加简洁，开发效率Spring MVC确实比struts2高。springmvc是           方法级别的拦截，一个方法对应一个request上下文，而方法同时又跟一               个url对应，所以说从架构本身上spring3 mvc就容易实现restful url。                  struts2是类级别的拦截，一个类对应一个request上下文；实现restful url                     要费劲，因为struts2 action的一个方法可以对应一个url；而其类属性却               被所有方法共享，这也就无法用注解或其他方式标识其所属方法了。                    spring3mvc的方法之间基本上独立的，独享request response数据，请                  求数据通过参数获取，处理结果通过ModelMap交回给框架方法之间不                共享变量，而struts2搞的就比较乱，虽然方法之间也是独立的，但其所                有Action变量是共享的，这不会影响程序运行，却给我们编码，读程序                时带来麻烦。

**spring3 mvc****的验证**也是一个亮点，支持JSR303，处理ajax的请求更是               方便，只需一个注解@ResponseBody ，然后直接返回响应文本即可。

整体来说，springMVC和struts2各有优缺点，springMVC是struts2之后出现      的框架，但并不是struts2的替代框架，两者是可以互相替代的。springMVC和struts       的选择主要在于项目经理以及team leader对哪个更熟练以及对哪个理解的程度更    深一点，我们这边主要对struts2比较熟悉，就最终采用struts2了。

## 2. 业务逻辑层框架Spring

Spring是一个解决了许多J2EE开发中常见问题并能够代替EJB技术的强大的轻量级框架。其框架结构图如下

![img](images/spring.png)

Spring Core Container是一个用来管理业务组件的Ioc容器，是Spring应用的核心；SpringDAO和Spring ORM不仅提供数据访问的抽象模块，还集成了对Hibernate、JDO和iBatis等流行的对象关系映射框架的支持模块，并且提供了缓冲连接池、事物处理等重要的服务功能，保证了系统的性能和数据的完整性；Spring是模块化的框架，同时是一个松耦合的框架，允许开发人员自由地挑选适合自己应用的模块进行开发，若后续数据访问需要转为IBatis或表示层直接JSP+Servlet开发都较为容易。

## 3. 数据持久层框架Hibernate

O/R mapping技术是为了解决关系型数据库和面向对象的程序设计之间不匹配的矛盾而产生的。Hibernate是目前最为流行的O/Rmapping框架，它在关系型数据库和Java对象之间做了一个自动映射，使得程序员可以以非常简单的方式实现对数据库的操作。Hibernate工作原理如下图所示

![img](images/hibernate.png)

Hibernate通过对JDBC的封装，向程序员屏蔽了底层的数据库操作，使程序员专注于OO程序的开发，有助于提高开发效率。程序员访问数据库所需要做的就是为持久化对象编制xml映射文件[4]。
​    底层数据库的改变只需要简单地更改初始化配置文件(hibernate.cfg.xml或者hibernate.properties)即可，不会对应用程序产生影响。
​     Hibernate有自己的面向对象的查询语言HQL，HQL功能强大，支持目前大部分主流的数据库，如Oracle、DB2、MySQL、Microsoft SQL Server等，是目前应用最广泛的O/R映射工具。Hibernate为快速开发应用程序提供了底层的支持。

*此外数据库持久层框架常见还有MyBatis和spring jdbc

Hibernate和MyBatis两者相比的优缺点如下：

1、开发上手难度

Hibernate的真正掌握（封装的功能和特性非常多）要比Mybatis来   得难。在真正产品级应用上要用Hibernate，不仅对开发人员的要求 高，hibernate往往还不适合（多表关联查询等）。

 

2、系统调优调优方案对比

Hibernate:

* 制定合理的缓存策略；

* 尽量使用延迟加载特性；
  ​       * 采用合理的Session管理机制；
  ​       * 使用批量抓取，设定合理的批处理参数（batch_size）;

* 进行合理的O/R映射设计

Mybatis：

* MyBatis在Session方面和Hibernate的Session生命周期是一致的，       同样需要合理的Session管理机制。MyBatis同样具有二级缓存机制。 

* MyBatis可以进行详细的SQL优化设计。

3、SQL优化方面

Hibernate的查询会将表中的所有字段查询出来，这一点会有性能消  耗。Hibernate也可以自己写SQL来指定需要查询的字段，但这样就 破坏了Hibernate开发的简洁性。

Mybatis的SQL是手动编写的，所以可以按需求指定查询的字段。

总的来说，Hibernate使用的是封装好，通用的SQL来应付所有场景，    而Mybatis是针对响应的场景设计的SQL。Mybatis的SQL会更灵活、       可控性更好、更优化。

​                     4、移植性

Hibernate与具体数据库的关联只需在XML文件中配置即可，所有   的HQL语句与具体使用的数据库无关，移植性很好。MyBatis项目      中所有的SQL语句都是依赖所用的数据库的，所以不同数据库类型 的支持不好。

5、JDBC

Hibernate是在JDBC上进行了一次封装。

Mybatis是基于原生的JDBC的。Mybatis有运行速度上的优势。

6、功能、特性丰富程度

Hibernate提供了诸多功能和特性。要全掌握很难。

Mybatis自身功能很有限，但Mybatis支持plugin，可以使用开源的  plugin来扩展功能。

7、动态SQL

Mybatismapper xml 支持动态SQL

Hibernate不支持

spring jdbc用的较少。hibernate有强大的HQL，mybatis的sql一般是自己写，     或者工具生成。适用的话，没多大区别，关键是看最熟悉哪个。

## 4. 集成说明

工程中集成了struts2+hibernate+spring框架，目前可以完全按照ssh2的开发模式进行开发。此外，工程为了便于继承以前web工程的界面以及一些流程设计，同时集成了JQuery框架，Dwr框架，并继承了一些自定义的Javascript对象，同时还集成了JSTL表达式，提示框使用JQuery的JBox插件。

也就是说目前项目的开发并不是严格按照ssh2框架结构进行开发的，而是在ssh2框架的基础上兼容目前web项目的开发模式。目前开发是比较灵活的，比如jsp界面，可以直接使用原生的html标签，也可以使用struts2的标签；比如获取页面的值，可以使用ognl，也可以使用jstl，甚至直接%%写java代码；比如表单验证可以使用JQuery，也可以使用Strusts2拦截器；比如获取数据库数据更新表单，可以使用struts2的action提交表单，也可以使用Dwr异步获取数据。

总体上说，View层偏于集成原web项目开发模式，控制层兼容原开发模式   和ssh2模式，数据访问层使用ssh2的hibernate框架。

*此外组合框架还有EJB以及Spring+SpringMVC+Mybatis。

EJB早于SSH框架而出现，EJB是重量级框架，EJB最初的设计思想考虑的       是为分布式的应用服务的，分布式是针对大型应用构造的跨平台的协作       计   算，EJB最初的目的就是为这种计算服务的。但是软件发展到目前为   止，大多数应用不需要采用分布式的解决方案，因此用EJB显得太臃肿 了。Spring的出现恰恰为了解决这个问题。一般的几百万的项目，基本都     是SSH，EJB只适合在国家级的项目中运用到，如早期的银行系统。

Spring+Spring MVC+Mybatis是代替SSH的一种解决方案，在一线互联网公  司中SSH用的越来越少，而Spring+Spring MVC+Mybatis用的越来越多，互    联网公司比较喜欢开源，常把性能，优化，瓶颈挂在嘴边，技术氛围较浓，      而企业级开发还是以SSH为主，企业级开发，重的是开发效率和成本，喜欢       用旧的稳定的东西，人员也好招。我们最终选择SSH的主要原因也是对SSH   比较熟悉，没有时间，人员等来研究Spring+Spring MVC+Mybatis。

*最早期的开发还有直接采用JSP+Servlet+javabean的方式进行开发，不使用 任何开发框架，他们的比较如下：

jsp+servlet+javabean的开发模式需要写很多的重复代码，比如固定的doGet() 方法，而且它的控制跳转不灵活，往往一个问题处理需要两个.java文件，而       且当采用MVC模式开发时有很大的耦合度，对于后期的维护 相当不变。它    的好处是，适合于初学者，对于理解其中的交互过程很适合，便于以后对SSH    框架的理解。

SSH框架下的web开发，将三层给解耦了，而且封装了很多的工具类等，使       开发效率化。但是有一点坏处就是当框架更新时，需要对框架的更新内容进      行学习，尤其进行大的更新时。使用SSH框架你可以不用深入了解它的深层     原理便可以完成相关开发，所以说开发的效率化是其最大的优点。

稍微大些的企业级开发都不会直接使用jsp+servlet+javabean的方式的，这种  方式我们也不考虑。

 

**5. ****数据库******

 

目前使用sqlserver数据库进行开发，原因如下：

\1. 开发中使用了数据库框架hibernate，使用了通用数据库语言，会根据不同的数据库自动转换为相应的数据库底层语言，后续移植到任何数据库都非常方便。

\2. Sqlserver数据库有良好的开发调试界面，易于开发。

\3. Oracle数据库对硬件的要求很高，价格比较昂贵，管理维护麻烦一些，可以考虑后续再移植为oracle数据库。

# 二、业务功能模块

功能模块主要分为后台用户管理、权限管理、学校管理、学科管理、年级管理、班级管理（包含班主任任免）、老师管理、班级学生分配、班级老师分配、老师布置作业、老师批阅作业、题库导入、题库导出等模块

## 1. 后台用户管理

可以预置或手动录入一些后台管理账户，这些管理账户可以新建学校等一些操作。

## 2. 权限管理

根据不同的角色显示不同的页面，一些功能如添加学校、添加年级等也可以通过权限进行控制，权限预先设置，可以分配，不能新增和修改。

## 3. 学校管理

后台管理账户有权限进行学校管理，创建修改学校等；在创建学校的同时，会创建一个校级管理权限的账号，该账户可以创建该学校的学科、年级、班级以及老师。

## 4. 年级管理和班级管理

校级管理员可以创建年级和班级，年级和班级在一个界面显示；可以选择新增年级，新增班级，如果新增班级，必须选择对应的年级；可以对目前的年级班级进行修改；可以对班级年级进行删除，如果删除年级会连带删除其下的所有班级。

## 5. 学科管理

系统会预置一些学科；校级管理员也可以为该学校新增一些学科。

## 6. 老师管理

校级管理员可以统一录入老师，并任命哪些老师为相应的年级管理员以及班主任，录入可以提供单独录入，或提供模板，让统一录入。

录入老师的时候可以为老师分配学科，后续也可以进行修改。目前设计一个老师对应一个学科，这样学生可通过班级找到该班级总共有哪些任课老师，然后根据所有任课老师就可以找到该学生总共有哪些学科。

## 7. 班级学生分配（学生管理）

班主任可以单个的录入本班学生，也可提供模板，供老师批量录入本班学生。班主任还可以对本班学生进行管理，如删除本班学生。可以提供学生的批量操作，如批量删除本班学生，将学生批量改为另一年级的学生（升级）。

## 8. 老师布置作业

服务器端可以供老师布置作业，老师布置作业的时候可以从现有题库选择题，也可以新增题，新增题包括任务型题已经图片音频类题目。

服务器端还负责同老师端通信，接收老师布置的作业。

服务器端布置作业后分发给学生并接收学生端作答结果。

## 9. 老师批阅作业

服务器端可以直接批阅作业。

老师端也可以向服务器端加载未批改作业在老师端进行批改，教师端批改后批改结果再传给服务器。

## 10. 题库导入

制定标准的word文档格式，用户按照格式制作文档后可以批量导入题到题库中。题是以html标签的形式进行保存，从word文档读出题生成html是本项目的一个难点。

## 11. 题库导出

从题库中选择若干题，可以以word格式导出。后续根据实际需要再兼容其他格式的导出。

# 三、表结构

## 1. 用户管理

【用户信息（t_user）】

| 名称              | 类型             | 中文名         | 备注                                       |
| --------------- | -------------- | ----------- | ---------------------------------------- |
| _id             | bigint         | 序号——索引      | 自动生成ID，唯一标识，索引                           |
| username        | nvarchar(30)   | 登陆名         |                                          |
| pwd             | nvarchar(30)   | 登陆密码        | 不为空                                      |
| email           | nvarchar(50)   | 邮箱          |                                          |
| phone_number    | nvarchar(30)   | 电话号码        |                                          |
| name            | nvarchar(30)   | 昵称          |                                          |
| Sex             | Nvarchar(5)    | 性别          | 男,女,没有为空                                 |
| IconUrl         | Nvarchar(50)   | 头像地址        | 没有为空                                     |
| is_admin        | int            |             | 是否为预置管理员，默认为0  0：不是 1：是                  |
| user_type       | int            | 用户类型        | 0：其他  1：学生  2：老师  3：家长  4：管理员  5：系统级别管理员 |
| class_id        | bigint         | 班级id        | 学生必须有班级id                                |
| class_name      | nvarchar(30)   | 班级名称        |                                          |
| School_id       | Bigint         | 学校id        |                                          |
| School_name     | Nvarchar(30)   | 学校名称        |                                          |
| subject_id      | bigint         | 科目id        | 分配给老师的，老师最多对应一个科目                        |
| subject_name    | nvarchar(30)   | 科目名称        |                                          |
| last_login_time | datetime       | 最后一次登录时间    | 根据客户端,浏览器等记录最后一次登录                       |
| save_pwd_day    | int            | 密码保存时间      | 0：不保存  其它值：按具体保存时间                       |
| session_id      | nvarchar(50)   | Sessionid   | 用户生成id,用于记住密码登录，唯一标识                     |
| channel_id      | nvarchar(50)   | channelid   | 用于baidu push定位用户                         |
| create_date     | datetime       | 创建时间        |                                          |
| update_date     | datetime       | 更新时间        |                                          |
| update_comments | nvarchar(1000) | 更新内容        |                                          |
| longitude       | nvarchar(20)   | 经度          | (生/师)跟随学校的经纬度                            |
| latitude        | nvarchar(20)   | 纬度          | (生/师)跟随学校的经纬度                            |
| [is_useable]()  | int            | 账号是否可用      | 0不可用，1可用                                 |
| Imei_number     | varchar(255)   | 设备Imei号     |                                          |
| iccid           | varchar(255)   | 设备iccid     |                                          |
| Model_name      | varchar(255)   | 设备modelName |                                          |

 

【学生-家长信息（t_stu_parent）】

| 名称        | 类型     | 中文名    | 备注             |
| --------- | ------ | ------ | -------------- |
| _id       | bigint | 序号——索引 | 自动生成ID，唯一标识，索引 |
| stu_id    | bigint | 学生id   |                |
| parent_id | bigint | 家长id   |                |
| stat      | int    | 电子围栏状态 | 0或null关闭,1开启   |

 

【权限表（t_permission）】

| 名称           | 类型            | 中文名     | 备注                                       |
| ------------ | ------------- | ------- | ---------------------------------------- |
| _id          | bigint        | 序号——索引  | 自动生成ID，唯一标识，索引                           |
| per_name     | nvarchar(30)  | 权限名称    |                                          |
| parent_value | int           | 父权限值    | 用于生成多级权限，0或空为无父权限                        |
| per_value    | int           | 权限值     |                                          |
| per_type     | int           | 类型      | 从标题权限开始  1：标题权限  2：标题子权限  3：标题子子权限  .... 依次类推 |
| url          | nvarchar(100) | 权限页面url | 这些都是预置，需要根据不同工程具体情况进行后台修改注：目前改变了策略，此字段以及没有意义了 |
| per_desc     | nvarchar(100) | 权限 描述   |                                          |

 

【用户权限表（t_user_permission）-- 暂时没有用】

| 名称           | 类型     | 中文名    | 备注             |
| ------------ | ------ | ------ | -------------- |
| _id          | bigint | 序号——索引 | 自动生成ID，唯一标识，索引 |
| user_type    | int    | 用户类型   |                |
| permissionid | bigint | 权限id   |                |

 

【角色权限表（t_role_permission）】

| 名称           | 类型     | 中文名    | 备注             |
| ------------ | ------ | ------ | -------------- |
| _id          | bigint | 序号——索引 | 自动生成ID，唯一标识，索引 |
| roleid       | bigint | 角色     |                |
| permissionid | bigint | 权限id   |                |

 

【角色表（t_role）】

| 名称              | 类型             | 中文名    | 备注             |
| --------------- | -------------- | ------ | -------------- |
| _id             | bigint         | 序号——索引 | 自动生成ID，唯一标识，索引 |
| role_name       | nvarchar(30)   | 用户类型   | 不为空            |
| role_desc       | nvarchar(100)  | 权限id   |                |
| create_date     | datetime       | 创建时间   |                |
| update_date     | datetime       | 更新时间   |                |
| update_comments | nvarchar(1000) | 更新内容   |                |

 

【用户角色表（t_user_role）】

| 名称     | 类型     | 中文名    | 备注             |
| ------ | ------ | ------ | -------------- |
| _id    | bigint | 序号——索引 | 自动生成ID，唯一标识，索引 |
| userid | bigint | 用户id   |                |
| roleid | bigint | 角色id   |                |

 

【反馈题目表（t_feedback_question）】

| 名称              | 类型            | 中文名    | 备注             |
| --------------- | ------------- | ------ | -------------- |
| _id             | bigint        | 序号——索引 | 自动生成ID，唯一标识，索引 |
| question_id     | bigint        | 题目id   |                |
| tags            | nvarchar(300) | 标签     | String 类型的数组   |
| question_type   | int           | 题类型    |                |
| stat            | int           | 问题状态   | 0. 新提 ,1.已经处理  |
| class_id        | bigint        | 班级id   | 学生必须有班级id      |
| class_name      | nvarchar(30)  | 班级名称   |                |
| subject_id      | bigint        | 科目id   |                |
| subject_name    | nvarchar(30)  | 科目名称   |                |
| create_userid   | bigint        | 创建人id  |                |
| create_username | nvarchar(30)  | 创建人名称  |                |
| create_date     | Datetime      | 创建时间   |                |
| create_comments | Varchar(500)  | 创建说明   | 主要是说明反馈哪些内容    |
| update_userid   | bigint        | 修改人id  |                |
| update_username | nvarchar(30)  | 修改人名称  |                |
| update_date     | Datetime      | 修改时间   |                |
| update_comments | Varchar(500)  | 修改说明   | 主要是说明对反馈的描述    |

 

【公共字段表（t_project_params）】

| 名称           | 类型           | 中文名    | 备注             |
| ------------ | ------------ | ------ | -------------- |
| _id          | bigint       | 序号——索引 | 自动生成ID，唯一标识，索引 |
| param_name   | narchar(20)  | 字段名称   |                |
| param_value  | nvarchar(20) | 字段值    | (保证数字)         |
| parent_value | Nvarchar(20) | 父字段的值  | (-1最外)优先根据类型判断 |
| param_type   | Int          | 所属类型   | (-1为最外节点)      |

 

## [2. 群组]()管理

\3. 【学校表（t_school）】

| 名称              | 类型            | 中文名    | 备注             |
| --------------- | ------------- | ------ | -------------- |
| _id             | bigint        | 序号——索引 | 自动生成ID，唯一标识，索引 |
| school_name     | nvarchar(30)  | 学校名称   |                |
| province        | nvarchar(30)  | 所在省    |                |
| city            | nvarchar(30)  | 所在市    |                |
| area            | nvarchar(30)  | 所在区    |                |
| school_desc     | nvarchar(200) | 描述     |                |
| create_date     | datetime      | 创建日期   |                |
| create_username | nvarchar(30)  | 创建人名称  |                |
| create_userid   | bigint        | 创建人id  |                |
| longitude       | nvarchar(20)  | 经度     |                |
| latitude        | nvarchar(20)  | 纬度     |                |
| [begin_date]()  | datetime      | 服务开始时间 |                |
| end_date        | datetime      | 服务结束时间 |                |
| status          | int           | 状态     | 0不可用，1  可用     |
| version_index   | int           | 教材版本   | 学校所用版本，便于默认    |
| version_name    | nvarchar(20)  | 教材版本   |                |

 

\4. 【学校服务时间修改记录（t_school_leader）】

| 名称          | 类型       | 中文名    | 备注             |
| ----------- | -------- | ------ | -------------- |
| _id         | bigint   | 序号——索引 | 自动生成ID，唯一标识，索引 |
| school_id   | bigint   | 学校id   |                |
| begin_date  | datetime | 服务开始时间 |                |
| end_date    | datetime | 服务结束时间 |                |
| update_date | datetime | 修改日期   |                |
| user_id     | bigint   | 用户id   |                |

 

【校级负责人表（t_school_leader）】

| 名称        | 类型     | 中文名    | 备注             |
| --------- | ------ | ------ | -------------- |
| _id       | bigint | 序号——索引 | 自动生成ID，唯一标识，索引 |
| userid    | bigint | 用户id   | 校级负责人id        |
| school_id | bigint | 学校id   | 学校id           |

 

【年级表（t_grade）】

| 名称              | 类型            | 中文名    | 备注             |
| --------------- | ------------- | ------ | -------------- |
| _id             | bigint        | 序号——索引 | 自动生成ID，唯一标识，索引 |
| grade_name      | nvarchar(30)  | 年级名称   |                |
| school_id       | bigint        | 学校id   |                |
| school_name     | nvarchar(30)  | 学校名称   |                |
| grade_desc      | nvarchar(200) | 年级描述   |                |
| create_date     | datetime      | 创建日期   |                |
| create_username | nvarchar(30)  | 创建人名称  |                |
| create_userid   | bigint        | 创建人id  |                |
| stat            | int           | 状态     | 1.小学,2初中       |

 

【年级负责人表（t_grade_leader）】

| 名称       | 类型     | 中文名    | 备注             |
| -------- | ------ | ------ | -------------- |
| _id      | bigint | 序号——索引 | 自动生成ID，唯一标识，索引 |
| userid   | bigint | 用户id   | 年级负责人的id       |
| grade_id | bigint | 年级id   |                |

 

【班级表（t_classes）】

| 名称              | 类型            | 中文名    | 备注                     |
| --------------- | ------------- | ------ | ---------------------- |
| _id             | bigint        | 序号——索引 | 自动生成ID，唯一标识，索引         |
| im_id           | bigint        | 班群id   | openIm中班群id，创建班级新加人员   |
| imp_id          | bigint        | 家长群id  | openIm家长群id，根据班级学生关系添加 |
| class_name      | nvarchar(30)  | 班级名称   |                        |
| grade_id        | bigint        | 年级id   |                        |
| grade_name      | nvarchar(30)  | 年级名次   |                        |
| school_id       | bigint        | 学校id   |                        |
| school_name     | nvarchar(30)  | 学校名称   |                        |
| [class_desc]()  | nvarchar(200) | 年级描述   |                        |
| leader_userid   | bigint        | 班主任id  |                        |
| leader_username | nvarchar(30)  | 班主任名称  |                        |
| tag_name        | nvarchar(100) | 标签名称   | 用于baidu push统一通信       |
| create_date     | datetime      | 创建日期   |                        |
| create_username | nvarchar(30)  | 创建人名称  |                        |
| create_userid   | bigint        | 创建人id  |                        |
| stat            | int           | 状态     | 1.小学,2初中,根据年级的stat,    |

 

【学生班级表（t_stu_class）】

| 名称       | 类型     | 中文名    | 备注             |
| -------- | ------ | ------ | -------------- |
| _id      | bigint | 序号——索引 | 自动生成ID，唯一标识，索引 |
| stu_id   | bigint | 学生id   | 用户表id          |
| class_id | bigint | 班级id   |                |

 

【老师班级表（t_teacher_class）】

| 名称         | 类型     | 中文名    | 备注             |
| ---------- | ------ | ------ | -------------- |
| _id        | bigint | 序号——索引 | 自动生成ID，唯一标识，索引 |
| teacher_id | bigint | 学生id   | 用户表id          |
| class_id   | bigint | 班级id   |                |

 

【老师班级Log表（t_teacher_class_Log）只增不减,主要做记录不让老师教过哪个班,与teacher_class不同的是teacher_class真正维护关系】

| 名称         | 类型     | 中文名      | 备注             |
| ---------- | ------ | -------- | -------------- |
| _id        | bigint | 序号——索引   | 自动生成ID，唯一标识，索引 |
| teacher_id | bigint | 学生id     | 用户表id          |
| class_id   | bigint | 班级id     |                |
| subject_id | bigint | 科目id     |                |
| begin_date | date   | 带这个班级时间  |                |
| end_date   | date   | 不带这个班级时间 |                |

 

【科目班级表（t_subject_class）】

| 名称         | 类型     | 中文名    | 备注             |
| ---------- | ------ | ------ | -------------- |
| _id        | bigint | 序号——索引 | 自动生成ID，唯一标识，索引 |
| subject_id | bigint | 科目id   | 科目表id          |
| class_id   | bigint | 班级id   |                |

 

【科目表（t_subjects）】

| 名称              | 类型           | 中文名     | 备注                                    |
| --------------- | ------------ | ------- | ------------------------------------- |
| _id             | bigint       | 序号——索引  | 自动生成ID，唯一标识，索引                        |
| subject_name    | nvarchar(30) | 科目名称    |                                       |
| school_id       | bigint       | 学校id    | 可以为空，如果ID为空为预置科目，所有学校均有，预置科目不可编辑不可删除。 |
| is_preset       | int          | 是否为预置科目 | 0：非预置科目  1：为预置科目                      |
| create_date     | datetime     | 创建日期    |                                       |
| create_username | nvarchar(30) | 创建人名称   |                                       |
| create_userid   | bigint       | 创建人id   |                                       |

## 3. 作业管理

【作业表（t_homework）】 -- 一个作业由多道题组成

| 名称              | 类型           | 中文名    | 备注                                       |
| --------------- | ------------ | ------ | ---------------------------------------- |
| _id             | bigint       | 序号——索引 | 自动生成ID，唯一标识，索引                           |
| homework_name   | nvarchar(30) | 作业名称   |                                          |
| subject_id      | bigint       | 科目id   |                                          |
| subject_name    | nvarchar(30) | 科目名称   |                                          |
| class_id        | bigint       | 班级id   |                                          |
| class_name      | nvarchar(30) | 班级名称   |                                          |
| grade_id        | bigint       | 年级id   | 可通过搬家获得学校id，此处添加只是为了查询方便                 |
| grade_name      | nvarchar(30) | 年级名称   |                                          |
| school_id       | bigint       | 学校id   | 可通过班级获得学校id，此处添加只是为了查询方便                 |
| school_name     | nvarchar(30) | 学校名称   |                                          |
| send_time       | datetime     | 定时发送时间 | 可以为空，为空则及时发送                             |
| expired_time    | datetime     | 作业过期时间 | 过期后学生不能再作答。不传递或非法默认7天后过期                 |
| stat            | int          | 状态     | 0：待发送  1：已发送  2：已过期，学生不能再作答（目前先不用该状态，主要是需要有定时处理为过期的操作，先直接当前时间和expired_time进行比较来判断是否过期） |
| show_stat       | int          | 可见状态   | 0：待发送(或null,只能自己见)  1：其他人可见(后面可扩展校外…..先不扩展) |
| create_date     | datetime     | 创建日期   |                                          |
| create_username | nvarchar(30) | 创建人名称  | 老师名称                                     |
| create_userid   | bigint       | 创建人id  | 老师id，只有老师才能创建作业                          |

 

【作业-学生关联表（t_homework_stu）】

| 名称          | 类型           | 中文名      | 备注                            |
| ----------- | ------------ | -------- | ----------------------------- |
| _id         | bigint       | 序号——索引   | 自动生成ID，唯一标识，索引                |
| homework_id | bigint       | 作业id     |                               |
| user_id     | bigint       | 学生id     |                               |
| stat        | int          | 状态       | 0：老师发作业给学生  1：学生作答提交  2：老师已批阅 |
| submit_date | datetime     | 学生提交作业时间 |                               |
| parent_sign | Varchar(max) | 家长签字     |                               |
| sign_date   | datetime     | 家长签字作业时间 |                               |

 

【题的层级目录表（t_question_catalog）】

| 名称                | 类型            | 中文名      | 备注                                       |
| ----------------- | ------------- | -------- | ---------------------------------------- |
| _id               | bigint        | 序号——索引   | 自动生成ID，唯一标识，索引                           |
| version_index     | int           | 教材版本     | (公有题库才有)(不用了)                            |
| book_index        | int           | 课本       | (公有题库才有) (不用了)                           |
| version_name      | nvarchar(20)  | 教材版本     | (公有题库才有)                                 |
| book_name         | nvarchar(20)  | 课本       | (公有题库才有)                                 |
| catalog_name      | nvarchar(255) | 目录名称     |                                          |
| parent_catalog_id | bigint        | 父目录id    |                                          |
| catalog_index     | int           | 目录层级     | 1,2,3,... 依次递增  1表示根目录，2,3,...依次为二级目录，三级目录，... |
| stat              | int           | 目录状态     | 0：没有题没有子目录（默认）  1：有子目录没有题  2：有题没有子目录     |
| _type             | int           | 目录类型     | 1：知识点  2：教辅  3：试卷  4：章节（我的）  子目录的类型必须同父目录保持一致 |
| grade_id          | bigint        | 年级id     | 非必须                                      |
| subject_id        | bigint        | 科目id     | 非必须                                      |
| public_flag       | int           | 是否为公共知识点 | 0：不是  --  默认为0  1：是  9: 目录逻辑删除           |
| create_user_id    | bigint        | 创建者id    | 主要考虑到可能有私有知识点，非必须                        |

 

【题目录-题关联表（t_question_catalog_relation）】

| 名称          | 类型     | 中文名    | 备注             |
| ----------- | ------ | ------ | -------------- |
| _id         | bigint | 序号——索引 | 自动生成ID，唯一标识，索引 |
| catalog_id  | bigint | 题目录id  |                |
| question_id | bigint | 题id    |                |

 

 

【题表（t_question）】

| 名称                     | 类型             | 中文名    | 备注                                       |
| ---------------------- | -------------- | ------ | ---------------------------------------- |
| _id                    | bigint         | 序号——索引 | 自动生成ID，唯一标识，索引                           |
| question_content       | nvarchar(4000) | 题干     |                                          |
| question_content_thumb | nvarchar(4000) | 缩略图    | 当题干类型位图片时有效，是图片题干的缩略图  最大长度由255改为4000    |
| is_public              | int            | 是否为公共题 | 0：不是  1：是                                |
| content_type           | int            | 题干类型   | 0. html文本  1. 普通文本  2. 图片  3. 音频         |
| question_type          | int            | 题类型    | 1. 选择题（[客观题]()）  2. 多选题（）  3. 判断题（客观题）  4. 填空题（）  5. 主观题（主观题） |
| option_num             | int            | 选项数量   | 用于客观题，具体有多少个选项每个选项内容是什么还可以关联t_question_option查询 |
| correct_answer         | nvarchar(30)   | 正确答案   | 用于客观题，正确答案可能有多个，如果有多个以逗号分隔               |
| total_score            | int            | 总分     | 用于主观题                                    |
| analysis               | nvarchar(4000) | 试题解析   |                                          |
| key_point              | nvarchar(255)  | 考点     |                                          |
| [school_id]()          | bigint         | 学校id   |                                          |
| school_name            | nvarchar(30)   | 学校名称   |                                          |
| grade_id               | bigint         | 年级id   |                                          |
| grade_name             | nvarchar(30)   | 年级名称   |                                          |
| subject_id             | bigint         | 科目id   |                                          |
| subject_name           | nvarchar(30)   | 科目名称   |                                          |
| create_date            | datetime       | 创建日期   |                                          |
| create_username        | nvarchar(30)   | 创建人名称  |                                          |
| create_userid          | bigint         | 创建人id  |                                          |
| correct_rate           | bigint         | 正确率    | 正确率和                                     |
| do_times               | bigint         | 做题次数   |                                          |
| arrange_times          | bigint         | 布置次数   |                                          |
| difficulty             | Int            | 题的难度   | 1-5  0或null 未知                           |
| answer_num             | Int            | 答案个数   | (针对数学里面的填空题)                             |
| answer_perch           | nvarchar(50)   | 题目占位符  | (针对数学里面的填空题,在题干中的这个值代表填空题空格)             |
| answer_fills           | Nvarchar(50)   | 填空题    | (针对数学里面的填空题,在题干中的这个值代表填空题空格)             |
| status                 | Int            | 题目状态   | 1,待审核题 2,审核中 3,已审核 4,被驳回 8,题目反馈中         |

 

【个人题数统计（t_question_statistics）】

| 名称           | 类型     | 中文名    | 备注             |
| ------------ | ------ | ------ | -------------- |
| _id          | bigint | 序号——索引 | 自动生成ID，唯一标识，索引 |
| correct_rate | bigint | 正确率    |                |
| result_times | bigint | 答题次数   |                |
| homework_id  | bigint | 作业id   |                |
| subject_id   | bigint | 科目id   |                |
| class_id     | bigint | 班级id   |                |
| update_date  | date   | 提交时间   |                |
| user_id      | bigint | 用户id   |                |

 

【个人知识点统计（t_catalog_statistics）】

| 名称           | 类型     | 中文名    | 备注             |
| ------------ | ------ | ------ | -------------- |
| _id          | bigint | 序号——索引 | 自动生成ID，唯一标识，索引 |
| correct_rate | bigint | 正确率    |                |
| result_times | bigint | 答题次数   |                |
| subject_id   | bigint | 科目id   |                |
| class_id     | bigint | 班级id   |                |
| update_date  | date   | 最后修改时间 |                |
| user_id      | bigint | 用户id   |                |
| catalog_id   | bigint | 题目录id  |                |

 

 

【作业-题关联表（t_homework_question）】

| 名称          | 类型     | 中文名    | 备注             |
| ----------- | ------ | ------ | -------------- |
| _id         | bigint | 序号——索引 | 自动生成ID，唯一标识，索引 |
| homework_id | bigint | 作业id   |                |
| question_id | bigint | 题id    |                |

 

【题收藏表（t_user_question）】

| 名称          | 类型     | 中文名    | 备注             |
| ----------- | ------ | ------ | -------------- |
| _id         | bigint | 序号——索引 | 自动生成ID，唯一标识，索引 |
| user_id     | bigint | 用户id   |                |
| question_id | bigint | 题id    |                |
| group_id    | bigint | 组id    |                |

 

【题收藏组表（t_user_question_group）】

| 名称             | 类型           | 中文名    | 备注             |
| -------------- | ------------ | ------ | -------------- |
| _id            | bigint       | 序号——索引 | 自动生成ID，唯一标识，索引 |
| create_user_id | bigint       | 创建用户id |                |
| group_name     | nvarchar(30) | 组名称    |                |

 

【题-选项表（t_question_option）】-- 一般客观题才有选项

| 名称             | 类型            | 中文名    | 备注             |
| -------------- | ------------- | ------ | -------------- |
| _id            | bigint        | 序号——索引 | 自动生成ID，唯一标识，索引 |
| question_id    | bigint        | 题id    |                |
| option_title   | nvarchar(10)  | 选择标题   | A、B、C、D、T、F等   |
| option_content | nvarchar(500) | 选项内容   | 可以为空           |
| sequence       | int           | 显示顺序   |                |

 

【题答案表（t_question_result）】

| 名称                    | 类型            | 中文名                | 备注                           |
| --------------------- | ------------- | ------------------ | ---------------------------- |
| _id                   | bigint        | 序号——索引             | 自动生成ID，唯一标识，索引               |
| question_id           | bigint        | 对应题的id             |                              |
| homework_id           | bigint        | 作业id               |                              |
| solution_content      | nvarchar(max) | 作答答案               |                              |
| solution_type         | int           |                    | 1,填空题可自动批改                   |
| solution_content_path | nvarchar(max) | 点集                 |                              |
| solution_content_img  | nvarchar(max) | 图片                 |                              |
| solution_content_aud  | nvarchar(max) | 音频                 |                              |
| total_score           | int           | 总分                 | 用于主观题（默认100）                 |
| score_result          | int           | 打分                 | 用于主观题（0-100），这里可以当做正确率       |
| result                | int           | 答案结果               | 0：错误  1：正确  2：部分正确（主观题）      |
| total_time            | int           | 答题时长               | 秒为单位                         |
| start_time            | datetime      | 答题开始时间             |                              |
| end_time              | datetime      | 答题结束时间             |                              |
| comments_path         | nvarchar(max) | 点集                 |                              |
| comments_img          | nvarchar(max) | 图片                 |                              |
| comments_aud          | nvarchar(max) | 音频                 |                              |
| create_username       | nvarchar(30)  | 作答者名称              | 学号                           |
| create_userid         | bigint        | 作答者id              |                              |
| tags                  | nvarchar(300) | 标签                 | String 类型的数组“\|”分割           |
| answer_num            | Int           | 答案个数               | (针对数学里面的填空题)                 |
| answer_perch          | nvarchar(50)  | 题目占位符              | (针对数学里面的填空题,在题干中的这个值代表填空题空格) |
| correct_answer_fills  | Nvarchar(500) | 填空题(字符串保存数组)  正确答案 |                              |
| answer_fills          | Nvarchar(500) | 填空题(字符串保存数组)  作答   | (针对数学里面的填空题,在题干中的这个值代表填空题空格) |
| create_name           | nvarchar(30)  | 创建人姓名              | 真实姓名                         |
| status                | int           | 状态                 | 1，作答 2，草稿                    |

 

【题-选项表学生作答图片（t_question_result_image）】-- 一般客观题才有选项

| 名称                 | 类型            | 中文名    | 备注                   |
| ------------------ | ------------- | ------ | -------------------- |
| _id                | bigint        | 序号——索引 | 自动生成ID，唯一标识，索引       |
| question_result_id | bigint        | 作答id   |                      |
| sequence           | int           | 显示顺序   | 从0开始                 |
| image_url          | nvarchar(255) | 图片地址   |                      |
| _type              | int           | 类型     | 1：学生作答图片  2：老师批阅结果图片 |

 

 

【错题集（t_stu_wrong_questions）】

| 名称                         | 类型           | 中文名       | 备注               |
| -------------------------- | ------------ | --------- | ---------------- |
| _id                        | bigint       | 序号——索引    | 自动生成ID，唯一标识，索引   |
| question_id                | bigint       | 题id       |                  |
| user_id                    | bigint       |           |                  |
| cout                       | int          | 错误次数      |                  |
| newest_date                | datetime     | 最近一次错误的时间 |                  |
| score_result               | int          | 正确率       | 0-100,默认为0       |
| _type                      | int          |           | 1. 自动添加  2. 手动添加 |
| homework_id                | bigint       | 作业id      |                  |
| last_solution_content      | varchar(max) | 最后次作答     | 文本               |
| last_solution_content_path | varchar(max) |           | 点集               |
| last_solution_content_img  | varchar(max) |           | 图片               |
| last_solution_content_aud  | varchar(max) |           | 音频               |

 

 

【共有题库上传文件（t_upload_public_file）】

| 名称              | 类型            | 中文名    | 备注                         |
| --------------- | ------------- | ------ | -------------------------- |
| _id             | bigint        | 序号——索引 | 自动生成ID，唯一标识，索引             |
| file_name       | nvarchar(100) | 文件名称   |                            |
| file_path       | nvarchar(200) | 文件存储路径 |                            |
| file_stat       | int           | 文件状态   | 1、待审核  2、被驳回  3、录入中  4、已完成 |
| create_date     | datetime      | 上传日期   |                            |
| create_username | nvarchar(30)  | 上传者名称  |                            |
| create_userid   | bigint        | 上传者id  |                            |
| version_index   | int           | 教材版本   |                            |
| book_index      | int           | 课本     |                            |
| version_name    | nvarchar(20)  | 教材版本   |                            |
| book_name       | nvarchar(20)  | 课本     |                            |
| catalog_id      | bigint        | 目录id   |                            |
| catalog_name    | nvarchar(255) | 目录名称   |                            |
| grade_id        | bigint        | 年级id   | 非必须                        |
| grade_name      | nvarchar(30)  | 年级名称   |                            |
| describe        | nvarchar(200) | 对文件的描述 | 如果给出的目录不能涵盖所发的文件内容，可描述     |
| subject_id      | bigint        | 科目id   | 非必须                        |
| subject_name    | nvarchar(30)  | 科目名称   |                            |
| file_describe   | nvarchar(200) | 描述     | 例如：被驳回原因                   |
| is_public       | int           | 是否公有   | 0，校内 1，公有                  |

 

 

## 4. 软件管理

【软件表（t_app）】

| 名称              | 类型           | 中文名    | 备注                                 |
| --------------- | ------------ | ------ | ---------------------------------- |
| id              | bigint       | 序号——索引 | 自动生成ID，唯一标识，索引                     |
| app_name        | Varchar(30)  | 软件名称   | 不为空                                |
| app_comment     | Varchar(300) | 软件描述   |                                    |
| status          | int          | 应用状态   | [0.]()新添加,1.审核中,2.审核通过,3.审核不通过     |
| create_userid   | bigint       | 创建人id  |                                    |
| create_username | nvarchar(30) | 创建人名称  |                                    |
| create_date     | Datetime     | 创建时间   |                                    |
| create_comments | Varchar(500) | 创建说明   | 主要是说明升级了哪些内容                       |
| update_date     | Datetime     | 修改时间   |                                    |
| update_comments | Varchar(500) | 修改说明   | 主要是说明对软件的描述                        |
| app_title_path  | Varchar(100) | 图片路径   |                                    |
| type            | Varchar(255) | App区分  | 1排行2外语扩展 3课堂同步4综合提升5推荐6版本升级，多个用,隔开 |
|                 |              |        |                                    |

 

 

【软件版本表（t_version）】

| 名称              | 类型           | 中文名      | 备注                                       |
| --------------- | ------------ | -------- | ---------------------------------------- |
| id              | bigint       | 序号——索引   | 自动生成ID，唯一标识，索引                           |
| version_name    | Varchar(30)  | 版本号      | 不为空                                      |
| app_id          | Long         | 软件id     | 软件id                                     |
| app_type        | int          | 应用编号     | (and)1.Student  ,2Teacher,3Parent  (IOS)4.Student,5Teacher,6Parent  0或者null 商城 |
| must_upgrade    | int          | 是否强制更新   | 0 非强制更新  1.       重大更新  2 强制更新           |
| url             | Varchar(100) | 版本文件下载地址 | 可为空，目前下载地址有文件路径+文件名称组成                   |
| app_file_name   | Varchar(50)  | 文件名称     |                                          |
| app_file_path   | Varchar(100) | 文件路径     | 目前设计的是文件的父文件夹的名字                         |
| app_file_size   | Bigint       | 版本文件大小   | 以字节为单位                                   |
| create_userid   | bigint       | 创建人id    |                                          |
| create_username | nvarchar(30) | 创建人名称    |                                          |
| create_date     | Datetime     | 创建时间     |                                          |
| create_comments | Varchar(500) | 创建说明     | 主要是说明升级了哪些内容                             |
| click_count     | int          | 下载次数     |                                          |

 

 

 

【反馈表（t_feedback）】

| 名称              | 类型           | 中文名    | 备注                                       |
| --------------- | ------------ | ------ | ---------------------------------------- |
| _id             | bigint       | 序号——索引 | 自动生成ID，唯一标识，索引                           |
| version_id      | bigint       | 版本id   |                                          |
| version_name    | nvarchar(30) | 版本名称   |                                          |
| app_type        | int          | 应用编号   | (and)1.Student  ,2Teacher,3Parent  (IOS)4.Student,5Teacher,6Parent |
| stat            | int          | 问题状态   | 1. 新提                                    |
| create_userid   | bigint       | 创建人id  |                                          |
| create_username | nvarchar(30) | 创建人名称  |                                          |
| create_date     | Datetime     | 创建时间   |                                          |
| create_comments | Varchar(500) | 创建说明   | 主要是说明反馈哪些内容                              |
| update_userid   | bigint       | 修改人id  |                                          |
| update_username | nvarchar(30) | 修改人名称  |                                          |
| update_date     | Datetime     | 修改时间   |                                          |
| update_comments | Varchar(500) | 修改说明   | 主要是说明对反馈的描述                              |

【反馈题目表（t_feedback_question）】

| 名称              | 类型            | 中文名    | 备注             |
| --------------- | ------------- | ------ | -------------- |
| _id             | bigint        | 序号——索引 | 自动生成ID，唯一标识，索引 |
| question_id     | bigint        | 题目id   |                |
| tags            | nvarchar(300) | 标签     | String 类型的数组   |
| question_type   | int           | 题类型    |                |
| stat            | int           | 问题状态   | 0. 新提 ,1.已经处理  |
| class_id        | bigint        | 班级id   | 学生必须有班级id      |
| class_name      | nvarchar(30)  | 班级名称   |                |
| subject_id      | bigint        | 科目id   |                |
| subject_name    | nvarchar(30)  | 科目名称   |                |
| create_userid   | bigint        | 创建人id  |                |
| create_username | nvarchar(30)  | 创建人名称  |                |
| create_date     | Datetime      | 创建时间   |                |
| create_comments | Varchar(500)  | 创建说明   | 主要是说明反馈哪些内容    |
| update_userid   | bigint        | 修改人id  |                |
| update_username | nvarchar(30)  | 修改人名称  |                |
| update_date     | Datetime      | 修改时间   |                |
| update_comments | Varchar(500)  | 修改说明   | 主要是说明对反馈的描述    |

 

 

## 5. 公告管理

【公告表（t_notice）】

| 名称             | 类型           | 中文名   | 备注             |
| -------------- | ------------ | ----- | -------------- |
| id             | bigint       | 序号-索引 | 自动生成ID，唯一标识，索引 |
| title          | Varchar(100) | 标题    |                |
| content        | LongText     | 内容    |                |
| noticeType     | int          | 类型    | 0:普通,1:富文本     |
| noticeStatus   | int          | 使用状态  | 0:无效 ,1:有效     |
| createUserId   | bigint       | 创建人id |                |
| createUserName | nvarchar(30) | 创建人名称 |                |
| createDate     | Datetime     | 创建时间  |                |
| createComments | Varchar(500) | 创建说明  | 主要是说明反馈哪些内容    |
| modifyUserId   | bigint       | 修改人id |                |
| modifyUserName | nvarchar(30) | 修改人名称 |                |
| modifyDate     | Datetime     | 修改时间  |                |
| modifyComments | Varchar(500) | 修改说明  | 主要是说明对反馈的描述    |

 

## 6. SIM管理

【sim激活记录表（t_sim）】

| 名称             | 类型          | 中文名         | 备注             |
| -------------- | ----------- | ----------- | -------------- |
| _id            | bigint      | 序号-索引       | 自动生成ID，唯一标识，索引 |
| user_id        | bigint      | 激活用户id      | 对应t_user中的_id  |
| sim_iccid      | varchar(30) | 卡的唯一标识iccid |                |
| sim_imsi       | varchar(30) | imsi        | 区分运营商和地区       |
| activated_date | timestamp   | 激活时间        | 用户激活时的时间       |

 

# 四、通信协议

与客户端通过http请求进行通信，数据格式采用json字符串

请求格式为：{"name":操作名称, "details":请求时数据}

响应格式为：{"code":操作结果, "name":数据类型, "details":返回时数据} 

//name,details可选，name此处一般指代推送类型

所有接口路径

(ip 端口号暂时确定为测试服务器)

[http://61.129.57.40:8083/HomeworkManagement/server/req.action?json={“name”:”xxxxx”,”details](http://61.129.57.40:8083/HomeworkManagement/server/req.action?json=%7b“name”:”xxxxx”,”details)”:{所请求的json数据}}

Json 携带时候可以多带多带的json不做处理

 

 

  以下为通用返回格式

暂定100为操作成功，100以上为具体接口不定结果,100以下为通用结果

​    {"code":100} //操作成功

 

{“code”:-1} //请求失败，未定义的失败（未知失败）

{"code":99} //请求失败，发生了异常

{"code":98} //请求失败，请求内容为空 

{"code":97} // 请求失败，操作名称为空

{"code":96} // 请求失败，未定义的操作名称

{"code":95} // 请求失败，操作参数有错误,比如Email注册，就必须有Email参数

{"code":94} // 请求失败，登录失效了

{“code”:93} // 请求失败，用户类型错误,例如给老师的接口传过来的参数是学生 

​       {“code”:92}// 请求失败，用户不存在

通常请求返回:

{

“code”:xxx,

​       “details”:{

​              具体返回json参数

},

“versions”:[{

"id":xxx        //版本id  (检测更新用到)

"versionName":"xxx" //版本名||版本号

"appType":xxx,      //版本类型 ，(and)1.Student,2Teacher,3Parent

(IOS)4.Student,5Teacher,6Parent

 

"mustUpgrade":xxx   // 0||null 普通更新 , 1.重大更新,2.强制更新

"appFileName":"xxx"     // 服务器保存的文件名

"appFilePath":"xxx"     //下载路径 

"appFileSize": xxx      //apk大小

"appMd5":"xxx"      //文件MD5编码后的文件,确保版本完整性

"createUserid": xxx     //创建人id  

"createUsername":"xxx"  //创建人 name

"createComments":"xxx"  //创建描述

"createDate":"xxx"    // 创建时间 

},{},{},,,,]

} 

 

## 1)  请求公共信息

### a.   请求所有学校

{"name":"c_get_schools",

​       "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型， 1.Android,2.IOS,3.Web,4.Other      

​        

​        }

}

响应格式：

{ “code”:100, //操作结果，此处均为00

 "details":[

{  

“schoolId”:xxx，//学校id

“schoolName”:”xxx”，//学校Name

​    },

​    ……….

]

}

 

### b.   请求学校下年级班级科目信息

 

{"name":"c_get_schoolinfo",

​       "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型， 1.Android,2.IOS,3.Web,4.Other      

 “schoolId”:xxx，//学校id

“schoolName”:”xxx”，//学校Name

​        }

}

响应格式：

{ “code”:100, //操作结果，此处均为00

 "details":{

"id":xxx ,//学校id

“schoolName”:”xxx”，//学校Name

“province”:”xxx”，//省

“city”:”xxx”，//城市

“area”:”xxx”，//区域

“schoolDesc”:”xxx”，//学校描述

“createDate”:”xxx”，//创建时间(数据创建)

“createUserid”:xxx，//创建人id(数据创建)

“createUsername”:”xxx”，//创建人(数据创建)

“grades”:[{

​        “Id”:[xxx](), //年级id

​        “gradeName”: “xxx”，//年级名

“gradeDesc”: “xxx”，//年级描述

“schoolId”: xxx，   //学校id

“schoolName”: “xxx”，//学校名称

“createDate”: “xxx”,    //创建时间

“createUserid”: xxx,    //创建人

“createUsername”: “xxx”,//创建人名

classList:[{                //年级下所有班级

​            “Id”: xxx,          //班级id

​            “imId”: “xxx”，         //班级群id

“imPId”: “xxx”，            //班级群id

​            “tribeIcon”:“xxx”       //URL地址

​            “tribePIcon”:“xxx”      //URL家长地址

​            “className”: “xxx”，        //班级名称

“gradeId”: xxx，            //所在年级id

“gradeName”: “xxx”，        //年级名称

“schoolId”: xxx，       //学校id

“schoolName”: “xxx”，       //学校名称

“leaderUserId”:xxx,     //班主任id

“leaderUsereName”:”xxx” //班主任名称

“classDesc”:”xxx”           //班级描述

“tagName”:”xxx”         //标签名称(baidu push)

“createDate”:”xxx”      //创建时间

“createUserName”:”xxx”  //创建人名称

“createUserid”:xxx,     //创建人id 

“students”:null,     //班级下所有学生 (不查,查太多影响效率)

“teachers”:null      //班级下所有科目老师(不查,查太多影响效率)

},

. . . 

],

“subjectList”:[{    //该学校下所有科目, 注意：并不是每一个班级都是这些科目,针对哪个班的科目要单独请求那个班级科目

“Id”: xxx, //科目 id

​        “subjectName”: “xxx”，

“schoolId”: xxx，

“isPreset”: xxx，  //是否预置  1.预置，0.非预置

“createDate”: “xxx”,

“createUserid”: xxx,

“createUsername”: “xxx”,

},

{. . . }

]

},

...

]

 

}

}

{"code":141} //[请求失败](),请求的学校不存在

### c.    请求关于作业本详情或帮助

{"name":"c_get_details_help",

​       "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型， 1.Android,2.IOS,3.Web,4.Other,

"type":xxx, //1,作业本详情,2.帮助

​      "userType":xxx, //1.学生 2.老师 3.家长 

   "textType"：xxx,//1,txt文本,2.html文本

​       

​        }

}

响应格式：

{ “code”:100, //操作结果，此处均为00

 "details":

{

“content”:”xxx”，//内容

​    }

}

### d.   反馈

{"name":" [c_feedback]()",

​       "details":{

“[protocol]()”:”1.0”,//协议版本

“[deviceType]()”:  “xxxx” , //设备类型，1.Android,2.IOS,3.Web,4.Other,

"sessionId":" "E8D62A406E5816757", //sessionId

"userId":xxx, //用户名id

"userType":xxx, //1.学生 2.老师 3.家长

["]()[userName]()": "xxx", //用户名字

"versionId":xxx     //版本id

"versionName":"xxx" //版本名||版本号

"appId":xxx //(android)1.Student ,2Teacher,3Parent

(IOS)4.Student,5Teacher,6.Parent

"createComments":"xxx", //反馈内容 

['createDate'](): 'xxx' // 时间 yyyy-MM-ddHH:mm:ss

​        }

}

响应格式：

{ “code”:100, }  

### e.    反馈题目

{"name":"c_feedback_question",

​       "details":{

“protocol”:”1.0”, //协议版本

“deviceType”: “xxxx” , //设备类型， 1.Android,2.IOS,3.Web,4.Other,

"sessionId":" "E8D62A406E5816757", //sessionId

"userId":xxx, //用户名id

"userType":xxx, //1.学生 2.老师 3.家长

"userName": "xxx", //用户工号

"name": "xxx", //用户名字

 

"questionId":xxx        //题目 id

"questionType":xxx      //题目类型

"tags["]():["xxx","xxx"]        //标签 

"classId":xxx           //班级id 

"className":"xxx"               //班级名称

"subjectId":xxx         //科目id

"subjectName":"xxx"         //科目名称

"createComments":"xxx", //反馈内容 

'createDate': 'xxx' // 时间 yyyy-MM-ddHH:mm:ss

​        }

}

响应格式：

{ “code”:100, }  

{ “code”:190, }  //反馈的题目不存在

### f.     检测更新

{"name":" c_check_update ",

​       "details":{

“protocol”:”1.0”, //协议版本

“deviceType”: “xxxx” , //设备类型， 1.Android,2.IOS,3.Web,4.Other,

"sessionId":" "E8D62A406E5816757", //sessionId(没有就不传)

"userType":xxx, //1.学生 2.老师 3.家长           (没有就不传)

["userName]()": "xxx", //用户名字                                     (没有就不传)

"appType":xxx   //(android)1.Student ,2Teacher,3Parent

(IOS)4.Student,5Teacher,6.Parent

"versionId":xxx     //版本id

//"appId":xxx       //软件id

//"versionName": "xxx", //当前版本号

​        

​        }

}

响应格式：

{ “code”:100, 

"details":{

"id":xxx        //版本id  (检测更新用到)

"versionName":"xxx" //版本名||版本号

"appType":xxx       //版本类型

"mustUpgrade":xxx   // 0||null 普通更新 , 1.重大更新,2.强制更新

"appFileName":"xxx"     // 服务器保存的文件名

"appFilePath":"xxx"     //下载路径

"appFileSize": xxx      //apk大小

"appMd5":"xxx"      //文件MD5编码后的文件,确保版本完整性

"createUserid": xxx     //创建人id  

"createUsername":"xxx"  //创建人name

"createComments":"xxx"  //创建描述

"createDate":"xxx"    // 创建时间 

​    }

 }

{ “code”:181 }  //已经是最新版本了  

{ “code”:182 }  //系统还没有其版本

 

### g.   Itunes信息

请求格式：

{

"name":"c_get_itunes ",

​       "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:  “xxxx” , //设备类型，1.Android,2.IOS,3.Web,4.Other,

"sessionId":""E8D62A406E5816757", //sessionId(没有就不传)

"userType":xxx,     //1.学生 2.老师 3.家长                     (没有就不传)

"userName": "xxx", //用户名字                                      (没有就不传) 

​        }

}

响应格式：

 

{ “code”:100, 

"details":{

{

 "resultCount":1,

 "results": [

{

"ipadScreenshotUrls":[

"http://a3.mzstatic.com/us/r30/Purple111/v4/78/61/02/78610290-a3ff-5faa-5fd8-3f5fb8443061/sc1024x768.jpeg","http://a4.mzstatic.com/us/r30/Purple122/v4/0e/ac/9e/0eac9ef4-9bff-0ee3-0309-22cc3b260988/sc1024x768.jpeg","http://a4.mzstatic.com/us/r30/Purple122/v4/71/ba/3f/71ba3f97-420d-7e6c-ef9a-5a236a2d695c/sc1024x768.jpeg","http://a4.mzstatic.com/us/r30/Purple111/v4/fd/91/da/fd91da6b-e8ae-a1be-9d9c-b40146d57b28/sc1024x768.jpeg","http://a3.mzstatic.com/us/r30/Purple111/v4/00/f5/77/00f577f3-79d2-275b-18fe-1c2177b6da01/sc1024x768.jpeg"],"appletvScreenshotUrls":[],"artworkUrl60":"http://is2.mzstatic.com/image/thumb/Purple111/v4/a0/76/9b/a0769bb9-b992-956f-b0b6-14f6fb23eadf/source/60x60bb.jpg","artworkUrl512":"http://is2.mzstatic.com/image/thumb/Purple111/v4/a0/76/9b/a0769bb9-b992-956f-b0b6-14f6fb23eadf/source/512x512bb.jpg","artworkUrl100":"http://is2.mzstatic.com/image/thumb/Purple111/v4/a0/76/9b/a0769bb9-b992-956f-b0b6-14f6fb23eadf/source/100x100bb.jpg","artistViewUrl":"https://itunes.apple.com/us/developer/wheat/id1095999120?uo=4","kind":"software","features":["iosUniversal"],

"supportedDevices":["iPad2Wifi","iPad23G", "iPhone4S", "iPadThirdGen","iPadThirdGen4G", "iPhone5", "iPodTouchFifthGen","iPadFourthGen", "iPadFourthGen4G", "iPadMini","iPadMini4G", "iPhone5c", "iPhone5s","iPhone6", "iPhone6Plus", "iPodTouchSixthGen"],"advisories":[],

"screenshotUrls":["http://a1.mzstatic.com/us/r30/Purple111/v4/77/7d/66/777d6611-d636-876e-4740-1c89201a8bf5/screen696x696.jpeg","http://a5.mzstatic.com/us/r30/Purple122/v4/79/ed/85/79ed8528-42cc-c2e3-263b-5c4f7e91932b/screen696x696.jpeg","http://a4.mzstatic.com/us/r30/Purple111/v4/21/c4/55/21c455f8-e0d1-2be2-9f60-b35015bd26a7/screen696x696.jpeg","http://a2.mzstatic.com/us/r30/Purple111/v4/a6/58/57/a65857e3-cf8b-9158-423a-c3fe94ded694/screen696x696.jpeg","http://a4.mzstatic.com/us/r30/Purple111/v4/43/5d/04/435d04ee-4e15-7818-ae7a-a78533356fa3/screen696x696.jpeg"],"isGameCenterEnabled":false,"contentAdvisoryRating":"4+","trackCensoredName":"麦穗作业本家长端-学生的成长手册","languageCodesISO2A":["ZH"],"fileSizeBytes":"31398912","trackViewUrl":"https://itunes.apple.com/us/app/mai-sui-zuo-ye-ben-jia-zhang/id1195792578?mt=8&uo=4","trackContentRating":"4+","genreIds":["6017", "6002"], "currency":"USD","wrapperType":"software","version":"1.1", "artistId":1095999120,"artistName":"wheat","genres":["Education", "Utilities"],"price":0.00,

"description":"本应用为整套应用体系中的家长客户端。本套应用提供了一种电子化教学系统，包括教师终端、学生终端、家长终端、云端、远程管理终端。\n\n教师终端包括：通信模块，教师上传或布置教学内容，并与外部设备通信连接，以下发教学内容，教师批改学生上传的作业，并与云端通信连接，以下发学生作业状态；数据库模块，与通信模块连接，存储教学内容以形成教学数据库。\n\n学生终端包括：交互模块，学生于交互模块处提交或书写作业内容，使交互模块接收学生的学习内容；收发模块，与交互模块连接，接收学习内容，且收发模块与教师终端的通信模块连接，向通信模块发送学习内容。\n\n家长终端包括：信号传输模块，分别与通信模块及收发模块连接，接收教师终端下发的学生的学习状态及学生终端下发的学生的作业状态。\n\n云端包括：教师终端、学生终端和家长终端可通过云端作为中间媒介来接收或存储数据；远程管理终端包括：与云端连接，将存储在云端中的数据以后台的形式显示，并展示给管理员。\n\n采用上述技术方案后，教师可根据已有的课件或教学进度实时更新教学内容，学生作业方式和历史作业的浏览更加归一化，家长也可更加方便快捷地了解学生的学习进度。", "trackName":"麦穗作业本家长端-学生的成长手册","bundleId":"com.wheat.homebook","trackId":1195792578,"releaseDate":"2017-01-18T17:23:57Z","primaryGenreName":"Education","isVppDeviceBasedLicensingEnabled":true,"minimumOsVersion":"8.0","currentVersionReleaseDate":"2017-01-23T20:46:19Z","releaseNotes":"-优化了聊天通讯界面的UI设计\n-修改了文件上传会出现的bug\n-重新设计了部分文案展示","sellerName":"Shanghai Yunchngju Technology Co., Ltd.","primaryGenreId":6017, "formattedPrice":"Free"}]}

 

}

}

 

### h.   获取课程表

请求格式：

{   "name":"c_get_classSchedule",

​    "details":{

“protocol”:”1.0”,    //协议版本

“deviceType”:”xxxx”,//设备类型，1.Android,2.IOS,3.Web,4.Other 

"sessionId":" E8D62A406E5816757", //sessionId

"userId":xxx,          //用户id 

“schoolId”:xxx,   //学校主键（可选）

​     “classId”:xxx,    //班级主键（可选）

​     “used”:xxx        //使用状态（0:禁用,1:启用）（可选）

​    }

}

响应格式：

{ “code”:100, 

"details":{"classSchedule":[{"classId":109, //班级主键

"className":"四年级三班",//班级名称

"createTime":"2017-03-07T10:29:19",//创建时间

"createUserId":686,//创建用户主键

"createUserName":"100011",//创建用户名称

"id":6, //课程表主键

"lessonList":[{"classScheduleId":6, //课程表外键

"endTime":"09:00:00",//下课时间

"fridaySubjectId":1, //星期五科目

"fridaySubjectName":null, //星期五科目名称

"id":46, //课节主键

"lesson":1, //第几课

"mondaySubjectId":1, //星期一科目

"mondaySubjectName":null, //星期一科目名称

"saturdaySubjectId":null, //星期六科目

"saturdaySubjectName":null, //星期六科目名称

"startTime":"08:15:00",//上课时间

"sundaySubjectId":null, //星期日科目

"sundaySubjectName":null, //星期日科目名称

"thursdaySubjectId":1, //星期四科目

"thursdaySubjectName":null, //星期四科目名称

"tuesdaySubjectId":1, //星期二科目

"tuesdaySubjectName":null, //星期二科目名称

"wednesdaySubjectId":1, //星期三科目

"wednesdaySubjectName":null//星期三科目名称

}],

"name":"四年级三班第二张课程表",//课程表名称

"remark":null, //备注

"schoolId":68, //学校主键

"schoolName":"上海实验小学",//学校名称

"used":1, //是否启用（0:禁用,1:启用）

"usedDisplay":"启用"//used中文显示

}]

​    }

 }

 

###                       i.              添加、更新课程表

 

请求格式：

{   "name":"c_update_classSchedule",

​    "details":{

“protocol”:”1.0”,    //协议版本

“deviceType”:”xxxx”,//设备类型，1.Android,2.IOS,3.Web,4.Other 

"sessionId":" E8D62A406E5816757", //sessionId

"userId":xxx,          //用户id 

​     "type":xxx,            //类型（0:添加,1:更新）

​     "classSchedule":{"classId":109, //班级主键

"className":"四年级三班",//班级名称

"createTime":"2017-03-07T10:29:19",//创建时间

"createUserId":686,//创建用户主键

"createUserName":"100011",//创建用户名称

"id":6, //课程表主键(添加：空，更新：原server端课程表主键)

"lessonList":[{"classScheduleId":6, //课程表外键(添加：空，更新：原server端课程表外键)

"endTime":"09:00:00",//下课时间

"fridaySubjectId":1, //星期五科目

"fridaySubjectName":null, //星期五科目名称

"id":46, //课节主键(添加：空，更新：原server端课节主键)

"lesson":1, //第几课

"mondaySubjectId":1, //星期一科目

"mondaySubjectName":null, //星期一科目名称

"saturdaySubjectId":null, //星期六科目

"saturdaySubjectName":null, //星期六科目名称

"startTime":"08:15:00",//上课时间

"sundaySubjectId":null, //星期日科目

"sundaySubjectName":null, //星期日科目名称

"thursdaySubjectId":1, //星期四科目

"thursdaySubjectName":null, //星期四科目名称

"tuesdaySubjectId":1, //星期二科目

"tuesdaySubjectName":null, //星期二科目名称

"wednesdaySubjectId":1, //星期三科目

"wednesdaySubjectName":null//星期三科目名称

}],

"name":"四年级三班第二张课程表",//课程表名称

"remark":null, //备注

"schoolId":68, //学校主键

"schoolName":"上海实验小学",//学校名称

"used":1, //是否启用（0:禁用,1:启用）

"usedDisplay":"启用"//used中文显示

}

 

​    }

}

响应格式：

{ “code”:100, 

"details":{

}

 }

### i.      删除题目

请求格式：

{   "name":"c_delete_question",

​     "details":{

“protocol”:”1.0”,    //协议版本

“deviceType”:”xxxx”,//设备类型，1.Android,2.IOS,3.Web,4.Other 

"sessionId":" E8D62A406E5816757", //sessionId

"userId":xxx,            //用户id 

“questionId”:xxx,   //题目主键（可选）

 }

}

响应格式：

{ “code”:100, //100成功，1100题不存在，1104存在作业，无法删除

"details":{

}

 }

### j.      批量删除题目

请求格式：

{   "name":"c_delete_questions",

​     "details":{

“protocol”:”1.0”,    //协议版本

“deviceType”:”xxxx”,//设备类型，1.Android,2.IOS,3.Web,4.Other 

"sessionId":" E8D62A406E5816757", //sessionId

"userId":xxx,            //用户id 

“questionIds”:[ xxx, xxx],   //题目主键必填

 }

}

响应格式：

{ “code”:100, //100成功，1100题不存在，1104存在作业，无法删除

}

 

## 2)  用户信息

### [a.     登录]()

请求格式：

1.账号登录

以 账号登录,手机号登录,密码登录为一起, 判断优先级  账号》手机号》sessionId

{"name":"c_login",

​    "details":{

“protocol”:”2.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other   

"userType":xxx, //1.学生 2.老师 3.家长

"[channelId]()":"868969010014527",//baidu push定位用户具有唯一标识功能 （必带） 

“encryptType”:xxx, //0：加密； 1：不加密 ; 密码加密(默认0),2.IOS 加密

"userName":"xxxxxx", //用户名

"phoneNumber":"xxxxxx",//手机号码   

"sessionId":" E8D62A406E5816757", //版本第一次登陆不用带,用户、手机登录都要

"imeiNumber":"xxxx"     //设备imei号

"ICCID":"xxxx"  //设备ICCID号

"modelName":"xx"    //设备modelName号

"versionName":"xx"  //设备versionName号

"pwd":"xxxx"        //密码   密码加密后发送三种登录都需要

​    }

}

 

响应格式：

{ “code”,100 //操作结果，此处均为00

,

 ["details":{]()[ ]()

“userId”:xxx, //用户id

“imName”:”xxx” //openIm 登录id

“openImPwd”:”xxx”, //用于OpenIm聊天登录密码

​    “username”:”xxx”, // 用户名 同等/学号/工号

“email”:”xxx”, //email

“phoneNumber”:”xxx”, //电话号码 

[“]()name”:”xxx”, //用户姓名,

“age”:”2”, //年龄,

“sex”:  “*”,    //男，女，null

“isAdmin”:”0” ,// 是否为预置管理员，默认为0 ,1是管理员(客户端基本用不到)

“userType”:”xxx”, //0：其他1：学生2：老师3：家长4：管理员

“classId”:”0”, //班级id -- 用于学生

“className”:”xxxx”, //班级名称

“subjectId”:”0”, //科目id -- 用于老师

“versionIndex:xxx  //教材版本id     //教材针对每个学校一个教材

“versionName”:”xxx”, //教材版本Name

“bookIndex:xxx//课本id

“bookName:”xxx”//课本名称

“subjectName”:”xxxx”, //科目Name

“schoolId”:”0”，//学校id

“schoolName”:”xxx”，//学校Name

​    “gradeId”:xxx, //年级id -- 目前只有学生才会返回年级id

“gradeName”:”xxx”, //年级名称

“stat”:xxx, //监控状态  0或null ,没用监控,1已经监控学生才

“longitude”:xxx,            //x.xxxxxxx   横坐标,

“latitude”:xxx,     //x.xxxxxxx纵坐标

“sessionId”:”xxxx”，//SessionId

“channelId”:”xxxx”，//嵌套id

“lastLoginTime”:”xxx”, //最后一次登录时间

“createDate”:”xxx”, //创建时间

“updateDate”:”xxx”, //修改时间

“updateComments”:”xxx” //修改内容 ,

“iconUrl”:””//头像地址,为null用默认的

 }

}

 //操作成功

{"code":110} //用户名或密码错误

{"code":111} //用户名或手机号不存在

{"code":93} //用户登录类型出错(eg:老师在学生app登录)

{"code":[113](),

Details:{
​    ….  ……与100 一样

 }

}               //第一次登陆

 

### b.     注册

请求格式：

{"name":"c_[register]()",

​    "details":{

“protocol”:”1.0”, //协议版本 (升至2.0)

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other

"encryptType":xxx, //0：加密； 1：不加密 ; 密码加密(默认0)2.IOS加密

"userType":xxx, //1.学 2.老师 3.家长 

"userName":"xxx",  //用户名 同等/学号/工号/家长号 

"pwd":"xxxx"       //密码

"phoneNumber":"xxxxxx",//手机号码  

​     "name":"xxx",  //姓名

​     "sex":"xxx",   //性别 男,女

"age":xxx, //年龄

"schoolId":xxx, //学校Id

"schoolName":"xxx", //学校名称

"email": xxx       //email

​    }

}

 

响应格式：

{"code":100,

details:{

“userId”:xxx, //用户id，同时是OpenIm聊天登录账号

“openImPwd”:”xxx”, //用于OpenIm聊天登录密码

“username”:”xxx”, // 用户名 同等/学号/工号/家长号

“email”:”xxx”, //email

“phoneNumber”:”xxx”, //电话号码 

“name”:”xxx”, //用户姓名,

“age”:”2”, //年龄,

“sex”:  “*”,    //男，女，null

“isAdmin”:”0” ,// 是否为预置管理员，默认为0 ,1是管理员(客户端基本用不到)

“userType”:”xxx”, //0：其他1：学生2：老师3：家长4：管理员

“classId”:”0”, //班级id -- 用于学生

“className”:”xxxx”, //班级名称

“subjectId”:”0”, //科目id-- 用于老师

“subjectName”:”xxxx”, //科目Name

“schoolId”:”0”，//学校id

“schoolName”:”xxx”，//学校Name

“gradeId”:xxx, //年级id -- 目前只有学生才会返回年级id

“gradeName”:”xxx”, //年级名称

“sessionId”:”xxxx”，//SessionId

“channelId”:”xxxx”，//嵌套id

“lastLoginTime”:”xxx”, //最后一次登录时间

“createDate”:”xxx”, //创建时间

“updateDate”:”xxx”, //修改时间

“updateComments”:”xxx” //修改内容 ,

“iconUrl”:””//头像地址,为null用默认的

 

}} //成功

{"code":115} //手机号或用户名已经占用

 

### c.     获取用户个人信息(与登录基本一致)

请求格式：

{"name":"c_get_user_details",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other   

"userType":xxx, //1.学生 2.老师 3.家长

"sessionId":"xxx", //sessionId

"userId":xxx, 

 "getUserId":xxx, //需要得到个人信息的用户id, (可为空),

"getPhoneNumber":xxx,//需要得到人的手机号码与userType并列取(可为空),

​    }

}

 

响应格式：

{ “code”,100 //操作结果，此处均为00

,

 "details":{ 

“userId”:xxx, //用户id

“imName”:”xxx” //openIm 登录id

“openImPwd”:”xxx”, //用于OpenIm聊天登录密码

​    “username”:”xxx”, // 用户名 同等/学号/工号

“email”:”xxx”, //email

“phoneNumber”:”xxx”, //电话号码 

“name”:”xxx”, //用户姓名,

“age”:”2”, //年龄,

“sex”:  “*”,    //男，女，null

“isAdmin”:”0” ,// 是否为预置管理员，默认为0 ,1是管理员(客户端基本用不到)

“userType”:”xxx”, //0：其他1：学生2：老师3：家长4：管理员

“classId”:”0”, //班级id -- 用于学生

“className”:”xxxx”, //班级名称

“subjectId”:”0”, //科目id-- 用于老师

“subjectName”:”xxxx”, //科目Name

“schoolId”:”0”，//学校id

“schoolName”:”xxx”，//学校Name

   

“sessionId”:”xxxx”，//SessionId

“channelId”:”xxxx”，//嵌套id

“lastLoginTime”:”xxx”, //最后一次登录时间

“createDate”:”xxx”, //创建时间

“updateDate”:”xxx”, //修改时间

“updateComments”:”xxx” //修改内容 ,

“iconUrl”:””//头像地址,为null用默认的

 }

}

 //操作成功

{"code":111 } //用户不存在

### [d.     家长绑定学生或者解除绑定]()

请求格式：

{"name":"[c_]()band_p_stu",

​    "details":{

“protocol”:”1.0”, //协议版本

"sessionId":" E8D62A406E5816757", //sessionId

“deviceType”:”xxxx”, //设备类型， 1.Android,2.IOS,3.Web,4.Other

“siteType”: “xxx” , //1.请求孩子绑定 需经过孩子同意, 2取消对孩子绑定,并且取消监控位置

“phoneNumber”: “xxx” , //家长手机号   用于返回

"userId":xxx        //用户id(家长)

"userType":xxx, //1.学 2.老师 3.家长 

"stuUsername":"xxx",   //学生学号  

 

"stuName":"xxx",   //学生真实name  

"stuSex":"xxx", //学生性别       (下面是为绑定加的  siteType=1)

"schoolId":"xxx",  //学生学校id  

"schoolName":"xxx", //学生学校名称  

"gradeId":"xxx",   //学生年级id  

"gradeName":"xxx", //学生年级name  

"classId":"xxx",   //学生班级id  

"className":"xxx", //学生班级name  

 

​    }

}

 

[响应格式： ]()

{"code":100} //操作成功等待学生同意

{"code":177, // siteType 为1时候返回num表示解绑成功数量

"details":{

num:xxx

}

} 

 

{"code":121} //没有找到学生

{"code":93} // 学生类型错了 

{"code":152} //已经绑定，绑定重复了 

 

 

 

### [e.     学生回应家长绑定]()

请求格式：

{"name":"[c_]()respond_p_stu",

​    "details":{

“protocol”:”1.0”,    //协议版本

“deviceType”:”xxxx”,//设备类型，1.Android,2.IOS,3.Web,4.Other 

"sessionId":" E8D62A406E5816757", //sessionId

"userId":xxx,          //用户id 

"stuId":xxx,           //学生id    

"[parId]()":xxx,           //家长id  

" [flag]()": xxx,           // 1.同意 2.不同意 

​    }

}

 

响应格式：

  {"code":100} //操作成功等待学生同意

{"code":161}  //绑定失败(主要出于绑定途中有角色被删除了) 

{"code":162}  //绑定失败 绑定拒绝

 

{"code":152}  //绑定重复

 

### [f.       家长请求自己的孩子信息]()

 

请求格式：

{"name":"c_get_per_childs",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other   

"userId":"xxxxxx", //家长id 

"sessionId":" E8D62A406E5816757" //sessionId

​    }

}

响应格式： 

{ “code”,100 //操作结果，此处均为00

,

 "details":

childs : [{ 

“userId”:xxx, //用户id，同时是OpenIm聊天登录账号

“openImPwd”:”xxx”, //用于OpenIm聊天登录密码

​    “username”:”xxx”, // 用户名 同等/学号/工号

“email”:”xxx”, //email

“phoneNumber”:”xxx”, //电话号码 

“name”:”xxx”, //用户姓名,

“age”:”2”, //年龄,

“sex”:  “*”,    //男，女，null

“isAdmin”:”0” ,// 是否为预置管理员，默认为0 ,1是管理员(客户端基本用不到)

“userType”:”xxx”, //0：其他1：学生2：老师3：家长4：管理员

“classId”:”0”, //班级id -- 用于学生

“className”:”xxxx”, //班级名称

“subjectId”:”0”, //科目id -- 用于老师

“subjectName”:”xxxx”, //科目Name

“schoolId”:”0”，//学校id

“schoolName”:”xxx”，//学校Name

​    “gradeId”:xxx, //年级id      (返回空,需要再查)

“gradeName”:”xxx”, //年级名称    (返回空,需要再查)

“stat”:xxx, //监控状态  0或null ,没用监控,1已经监控

“accuracy” : xxx ,      最近一周正确率

“longitude”:xxx,            //x.xxxxxxx   学校横坐标,

“latitude”:xxx,     //x.xxxxxxx 学校纵坐标

“ownLongitude”:xxx,     //x.xxxxxxx   自己的横坐标,

“ownLatitude”:xxx,      //x.xxxxxxx 自己的纵坐标

“ownLastUpdateDate”:”xxx”, //自己的最后一次上传位置时间

“sessionId”:”xxxx”，//SessionId

“channelId”:”xxxx”，//嵌套id

“lastLoginTime”:”xxx”, //最后一次登录时间

“createDate”:”xxx”, //创建时间

“updateDate”:”xxx”, //修改时间

“updateComments”:”xxx” //修改内容 ,

“iconUrl”:””//头像地址,为null用默认的

 },

. . . 

] 

}

 

### [g.     家长请求孩子位置或者解绑位置权限]()

请求格式：

{"name":"[c_]()site_p_stu",

​    "details":{

“protocol”:”1.0”, //协议版本

"sessionId":" E8D62A406E5816757", //sessionId

“deviceType”:”xxxx”, //设备类型， 1.Android,2.IOS,3.Web,4.Other

“phoneNumber”: “xxx” , //家长手机号   用于返回

[“siteType”: “xxx” , ]()

//1.请求孩子位置 需经过孩子同意, 2取消监控孩子位置,3.获取实时位置(code:180)

"password":"xxxx"       //密码   密码加密后发送 三种登录都需要

"userId":xxx        //用户id(家长)

"userType":xxx, //1.学 2.老师 3.家长 

"stuUsername":"xxx",   //学生name 

"stuUserId":"xxx", //学生id  如果stuUsername没有就以stuUserId来找孩子

​    }

}

 

响应格式： 

{"code":100,    //一次只绑定一个,parStu不返回了

"details":{

[parStu:[{]()

​    id: xxx ,   客户端拿着没用,忽略即可

​    perId:xxx,  家长id

​    stuId:xxx, 学生id

​    stat :xxx ,监控状态  0或null ,没用监控,1已经监控

},

{},{}. . .]

}

 

} //操作成功等待学生同意

{"code":176, // siteType 为1时候返回num表示解绑数量

"details":{

num:xxx

}

} 

 

{"code":171} //没有找到学生 

{"code":172} //已经同意，不需再发请求了 

{"code":173} //家长信息确认失败

{"code":175} //电子围栏绑定重复 

[{"]()code":179} //电子围栏绑定双方不是亲属关系 

 

{ //获取实时位置 (siteType=3)

"code":100,

​    ["]()details"：{

​        "userId":xxx                //学生id

​        "username":"xxx",           //学生工号
​        "name":"xxx",               //学生名字

​        "childLatitude"："xxx",     //学生纬度

​        "childLongitude"："xxx",    //学生经度

​        "childUpdateDate"："xxx",   //学生位置修改时间

​        "schoolLatitude"："xxx",    //学校纬度

​        "schoolLongitude"："xxx",   //学校经度

 

}

}

{"code":183} //没有孩子位置信息

 

### h.     学生回应家长请求位置

请求格式：

{"name":"c_respond_site_p_stu",

​    "details":{

“protocol”:”1.0”,    //协议版本

“deviceType”:”xxxx”,//设备类型，1.Android,2.IOS,3.Web,4.Other 

"sessionId":" E8D62A406E5816757", //sessionId

"userId":xxx,          //用户id 

"stuId":xxx,           //学生id    

"parId":xxx,           //家长id  

" flag": xxx,           //1.同意 2.不同意 

​    }

}

 

响应格式：

  {"code":100,

“details”: {

"userId":xxx,          //用户id 

"stuId":xxx,            //学生id    

}} //操作成功

 

{"code":174} //绑定失败(主要出于绑定途中有角色被删除了或者已经解除家属关系了)

{"code":175}  [//]()电子围栏绑定重复 (弃用,第二次绑定默认不操作)

{"code":178}  //孩子拒绝监控位置(在已经监控他的位置的情况下) 

 

### i.        学生上传自己位置信息

请求格式：

{"name":"[c_]()upload_site_stu_p",

​    "details":{

“protocol”:”1.0”,    //协议版本

“deviceType”:”xxxx”,//设备类型，1.Android,2.IOS,3.Web,4.Other 

"sessionId":" E8D62A406E5816757", //sessionId

"userId":xxx,          //用户id 

"stuId":xxx,           //学生id    

"parId":xxx,           //家长id  (多个家长逻辑不合,去掉系统查)

" flag": xxx,           //1.进入学校 2. 离开学校 3.位置更改

'[timestrap]()':'xxx'  //离校时间, 进校时间, yyyy-MM-dd HH:mm:ss

"latitude"："xxx",    //学生纬度 (用于flag=3)

​      "longitude"："xxx",   //学生经度  (用于flag=3)

​      "updateDate"："xxx",  //学生位置修改时间 (用于flag=3)

​    }

}

 

响应格式：

{"code":100} //操作成功

### j.       学生上传设备关机信息

请求格式：

{"name":"c_upload_shutdown",

​    "details":{

“protocol”:”1.0”,    //协议版本

“deviceType”:”xxxx”,//设备类型，1.Android,2.IOS,3.Web,4.Other 

"sessionId":" E8D62A406E5816757", //sessionId

"userId":xxx,          //用户id 

"userType":"xxx", //0：其他1：学生2：老师3：家长4：管理员

"type":"xxx", //1：关机 2：开机

"updateDate"："xxx",   //学生

​    }

}

 

响应格式：

{"code":100} //操作成功 

 

### k.检测是否绑定家长.

Request：

```json
{
    "name": "c_check_band_parent",
    "details": {
        "studentId": xxx //学生id
    }
}
```

Response：

```json
{
    "code": 100, //成功100
    "details": 1 //返回绑定家长的个数，没有则为0
}
```





## 3)  设置信息

### k.     设置手机号

请求格式： 

{"name":"c_set_pwd_phone",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other   

 

"userId":xxx, //用户id      

"sessionId":" E8D62A406E5816757", //sessionId

"channelId":"868969010014527", //baidu push定位用户具有唯一标识功能 （必带） 

"encryptType":xxx, //0：加密； 1：不加密 ; 密码加密(默认0)

"oldPwd":"xxxx"     //原密码

["pwd":"xxxx"        //]()新密码   密码加密后发送  不设置:null 

"phoneNumber":"xxxxxx",//手机号码           不设置:null 

​    }

}

[响应格式：]()

{"code":100,

"details":{ 

“phoneNumber”:xxx, //电话号码 

“sessionId”:”xxx”, //密码和手机号任意修改了后sessionId需要改变以重新登录

} //操作成功

[{"]()code":121} //没有找到用户

{"code":122} //原来密码或电话本来就这样

{"code":126} //原密码错误

{"code":125} //手机号已经占用

 

### l.        重置密码

密码重置后 sessionId更新 

请求格式： 

{"name":"c_reset_pwd",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other

"encryptType":xxx, //0：加密； 1：不加密 ; 密码加密(默认0)

“resetType”: xxx   //0 方式(学生,老师)账号密码 1.家长(手机).2(手机+密码) 

"oldPwd":"xxx",     //原密码 手机号重置可以不传

"userType":xxx, //1.学 2.老师 3.家长 

"userName":"xxx",  //用户名 同等/学号/工号

"pwd":"xxxx"       //新密码

"phoneNumber":"xxxxxx",//手机号码   

​    }

}

 

响应格式：

{"code":100,

"details":{

“userId”:xxx, //用户id，同时是OpenIm聊天登录账号

“openImPwd”:”xxx”, //用于OpenIm聊天登录密码

“username”:”xxx”, // 用户名 同等/学号/工号/家长号

“email”:”xxx”, //email

“phoneNumber”:”xxx”, //电话号码 

“name”:”xxx”, //用户姓名,

“age”:”2”, //年龄,

“sex”:  “*”,    //男，女，null

“isAdmin”:”0” ,// 是否为预置管理员，默认为0 ,1是管理员(客户端基本用不到)

“userType”:”xxx”, //0：其他1：学生2：老师3：家长4：管理员

“classId”:”0”, //班级id -- 用于学生

“className”:”xxxx”, //班级名称

“subjectId”:”0”, //科目id-- 用于老师

“subjectName”:”xxxx”, //科目Name

“schoolId”:”0”，//学校id

“schoolName”:”xxx”，//学校Name

“gradeId”:xxx, //年级id -- 目前只有学生才会返回年级id

“gradeName”:”xxx”, //年级名称

“sessionId”:”xxxx”，//SessionId

“channelId”:”xxxx”，//嵌套id

“lastLoginTime”:”xxx”, //最后一次登录时间

“createDate”:”xxx”, //创建时间

“updateDate”:”xxx”, //修改时间

“updateComments”:”xxx” //修改内容 ,

“iconUrl”:””//头像地址,为null用默认的

}

},

{"code":121} //没有找到用户

{"code":124} //找到多个用户 

{"code":127} //原密码与新密码不能相同

### m.  设置个人信息

 {"name":"[c_]()set_details",设置个人信息

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other

"userType":xxx,        //1.学 2.老师 3.家长 

"userId":xxx,              //用户id

"encryptType":xxx, //0：加密(android) 密码加密(默认0) 2.IOS加密

"toUserId":xxx,                //要修改的用户id

"userName":"xxx",          //用户名 同等/学号/工号

"phoneNumber":"xxxxxx",   //手机号码   家长端手机号 

"sessionId":"xxxx"         //sessionId 登录后返回的         

"encryptType":xxx, //0：加密； 1：不加密 ; 密码加密(默认0)   

​     "pwd":"xxx"                //要修改的密码,有就修改没有不操作

​     "name":"xxx",  //姓名

​     "sex":"xxx",   //性别 男,女

"age":xxx, //年龄

"schoolId":xxx, //学校Id

"schoolName":"xxx", //学校名称

 "gradeId":xxx, //年级id

"gradeName":"xxx", //年级名称       

"classId":xxx, //班级id

"className":"xxx", //班级名称

“updateDate”:”xxx”, //修改时间      

“updateComments”:”xxx”, //修改内容 

"subjectId":xxx, //科目id          //针对老师端科目

"subjectName":"xxx", //科目名称

“iconUrl”:””        //图片转字符串上传,服务器解后保存返路径

"email": xxx       //email

​    }

}

 

响应格式：

{ “code”:100 //操作结果，此处均为00

 "details":{ 

“userId”:xxx, //用户id，同时是OpenIm聊天登录账号

“openImPwd”:”xxx”, //用于OpenIm聊天登录密码

​    “username”:”xxx”, // 用户名 同等/学号/工号

“email”:”xxx”, //email

“phoneNumber”:”xxx”, //电话号码 

“name”:”xxx”, //用户姓名,

“age”:”2”, //年龄,

“sex”:  “*”,    //男，女，null

“isAdmin”:”0” ,// 是否为预置管理员，默认为0 ,1是管理员(客户端基本用不到)

“userType”:”xxx”, //0：其他1：学生2：老师3：家长4：管理员

“classId”:”0”, //班级id -- 用于学生

“className”:”xxxx”, //班级名称

“subjectId”:”0”, //科目id-- 用于老师

“subjectName”:”xxxx”, //科目Name

“schoolId”:”0”，//学校id

“schoolName”:”xxx”，//学校Name

​    “gradeId”:xxx, //年级id -- 目前只有学生才会返回年级id

   “gradeName”:”xxx”, //年级名称

“sessionId”:”xxxx”，//SessionId

“channelId”:”xxxx”，//嵌套id

“lastLoginTime”:”xxx”, //最后一次登录时间

“createDate”:”xxx”, //创建时间

“updateDate”:”xxx”, //修改时间

“updateComments”:”xxx” //修改内容 ,

[“iconUrl”:””//]()头像地址,为null用默认的

 }

}

 

{"code":121} //没有找到用户

{"code":124} //找到多个用户基本不会出现 

[{"]()code":125} //手机号或username已经占用

 

### n.     设置学校教材版本与个人课本版本

请求格式： 

{"name":"c_set_version_book_index",

  "details":{

"protocol":"1.0", //协议版本

"deviceType": "xxxx", //设备类型， 1.Android,2.IOS,3.Web,4.Other   

"sessionId":" E8D62A406E5816757", //sessionId

"userId":xxx, //用户名id  

"sessionId":" E8D62A406E5816757", //sessionId,

"setType":"xxx",// 0修学校教材,1修学校教材 (先传1)

"schoolId":xxx,//学校id           //必须

"versionIndex":"xxx",// 学校教材id  (现在判断一个学校一个教材)

"versionName":"xxx",//学校教材name

"bookIndex": "xxx",  //课本版本id (课本id以与个人对应)

"bookName" : "xxx"    //课本name

  }

}

 

响应格式：

{"code":100} //成功

{"code":191}不能修改versionIndex 与系统中保留不一致,不能修改

{"code":192}//bookindex在versionIndex下不存在

{"code":141/学校不存在 

 

## 4)  获得用户信息

### a. 老师获得所有其任课的班级

请求格式： 

{"name":"c_get_teacher_classes",

​    "details":{

​		“protocol”:”1.0”, //协议版本

​		“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other   

​		"userId":xxx, //用户名id    

​		"sessionId":" E8D62A406E5816757", //sessionId

   	 }

}

响应格式：

{"code":100, //成功获得班级信息

"details":[

{

“id”:xxx, //班级id

“className”:”xxx”, //班级名称

“classDesc”:”xxx”, //班级描述

“schoolId”:xxx, //学校id

“schoolName”:”xxx”, //学校名称

“gradeId”:xxx, //年级id

“gradeName”:”xxx”, //年级名称

“tribeIcon”:“xxx”       //URL 地址

​        “tribePIcon”:“xxx”      //URL家长地址

“imId”:”xxx” //学生班群号

​        “imPId”:”xxx” //家长班群号

[“stat”:xxx, // ]()状态 1.小学,2初中

“tagName”:”xxx”, //用于baidu push

“leaderUserId”:xxx, //班主任id

“leaderUserName”:”xxx”, //班主任名称

“createUserid”:xxx, //班级创建者id

“createUsername”:”xxx”, //班级创建者名称

“createDate”:”xxx”, //班级创建时间

“submissionRate”:xxx,//作业提交率

“homeworkCount”:xxx,//累计作业数

“correctRate”:”xxx”//正确率

"stuCount": xxx,//班级人数 

 

 

},

{......},

......

]

}

{"code":93} //该用户不是老师

{"code":121} //该用户不存在

### b. 学生获得当前学习的所有科目

请求格式：

{"name":"[c_]()get_student_subjects",

​    "details":{

“protocol”:”1.0”, //协议版本

[“deviceType”:”xxxx”, //设备类型， 1.Android,2.IOS,3.Web,4.Other   ]()

"userId":xxx, //用户名id   

"sessionId":" E8D62A406E5816757", //sessionId

​    }

} 

 

响应格式：

{"code":100, //成功获得科目信息

"details":[

{

“id”:xxx, //科目id

“subjectName”:”xxx”, //班级名称 

“schoolId”:xxx, //学校id 

“isPreset”:xxx, //是否预置  0–非预置 ，1-预置

“gradeId”:xxx, //年级id

“gradeName”:”xxx”, //年级名称

“classId”:xxx, //班级id

“className”:”xxx”, //班级名称

 “classTribeName”:”xxx”//班群名

 "subjectIconUrl":"xxx",// //科目头像路径

“createUserid”:xxx, //科目创建者id 

“accuracy” :xxx         //正确率 0~100

“weekAccuracy” : xxx,//最近一周正确率 0~100,当前时间前7天的正确率

},

{......},

......

]

}

{"code":121} //该用户不存在

{"code":131} //学生没有班级信息 

### c. 给定班级找到其班级下群里面的个人说明

请求格式：

{"name":"[c_]()get_tribe_members",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型， 1.Android,2.IOS,3.Web,4.Other   

"userId":xxx, //用户名id   

"sessionId":" E8D62A406E5816757", //sessionId

​     "classId": xxx,  //班级id

​     "tribeId": xxx,  //群id

​     "memberType": xxx,  //得到人员类型 //1,学生群,2.家长群

​    }

} 

响应格式：

{"code":100, //成功获得科目信息

"details":[

{

“userId”:xxx, //用户id

“imName”:”xxx” //openIm 登录id

“openImPwd”:”xxx”, //用于OpenIm聊天登录密码

​    “username”:”xxx”, // 用户名 同等/学号/工号

“email”:”xxx”, //email

“phoneNumber”:”xxx”, //电话号码 

“name”:”xxx”, //用户姓名,

“age”:”2”, //年龄,

“sex”:  “*”,    //男，女，null

“isAdmin”:”0” ,// 是否为预置管理员，默认为0 ,1是管理员(客户端基本用不到)

“userType”:”xxx”, //0：其他1：学生2：老师3：家长4：管理员

“classId”:”0”, //班级id -- 用于学生

“className”:”xxxx”, //班级名称

“subjectId”:”0”, //科目id-- 用于老师

“subjectName”:”xxxx”, //科目Name

“schoolId”:”0”，//学校id

“schoolName”:”xxx”，//学校Name 

“longitude”:xxx,            //x.xxxxxxx   横坐标,

“latitude”:xxx,     //x.xxxxxxx 纵坐标

“sessionId”:”xxxx”，//SessionId

“channelId”:”xxxx”，//嵌套id

“lastLoginTime”:”xxx”, //最后一次登录时间

“createDate”:”xxx”, //创建时间

“updateDate”:”xxx”, //修改时间

“updateComments”:”xxx” //修改内容 ,

“iconUrl”:””//头像地址,为null用默认的

},

{......},

......

]

}

{“code”:1210} //班级不存在

 

 

 

## 5)  上传作业

### a.     获得教材及课本

请求格式：

{"name":" c_get_teaching_material",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型， 1.Android,2.IOS,3.Web,4.Other   

"userId":xxx, //用户名id    

"sessionId":"E8D62A406E5816757", //sessionId

​    }

} 

响应格式：

{"code":100, //成功获得科目信息

"details":[

{

“id”:xxx, //教材id

“paramName”:”xxx”,//教材名称 

“paramValue”:xxx, //字段值

“children”:[ //课本

{

“id”:xxx, //课本id

“paramName”:”xxx”,//课本名称 

“paramValue”:xxx, //课本值 

},{...},......

]

},

{......},

......

 

### b.     获得题的目录

请求格式：

{"name":"c_get_question_catalog",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型， 1.Android,2.IOS,3.Web,4.Other   

"userId":xxx, //用户名id    

"sessionId":"E8D62A406E5816757", //sessionId

“catalogIndex”:xxx, //目录层级，1,2,3...，可以为空，为空表示不限制

“versionIndex”:xxx //教材版本(教辅，知识点时为空)

“bookIndex”:xxx //课本(教辅，知识点时为空)

“flag”:xxx //共有题库目录/校内题库目录 1，共有 2，校内

“type”:xxx, //目录类型：1-知识点，2-教辅，3-试卷，4-章节

“status”:xxx, //目录有3种状态：1-其下没有题没有子目录，2-有子目录，4-有题

   //0或空表示不限制，若要返回多个状态，对应状态值相加

“gradeIds”: [1,2,3.. .], //年级id，可以为0或空，0或空表示不限制

“subjectId”:xxx //科目id，可以为0或空，0或空表示不限制

​    }

} 

 

响应格式：

{"code":100, //成功获得科目信息

"details":[

{

“id”:xxx, //目录id

“catalogName”:”xxx”, //目录名称 

“catalogIndex”:xxx, //目录层级，1,2,3,4... 

“status”:xxx, //目录状况：0-无题无子目录，1-有子目录，2-有题

“type”:xxx //目录类型：1-知识点，2-教辅，3-试卷，4-自定义

[“versionIndex”:xxx //]()教材版本

“bookIndex”:xxx //课本

“versionName”:”xxx” //教材名称

“bookName”: ”xxx” //课本名称

“children”:[ //子目录，无子目录为null

{

“id”:xxx, //目录id

“catalogName”:”xxx”, //目录名称 

“catalogIndex”:xxx, //目录层级，1,2,3,4... 

“status”:xxx, //目录状况：0-无题无子目录，1-有子目录，2-有题

“type”:xxx //目录类型：1-知识点，2-教辅，3-试卷，4-章节 

“children”:[...]

},{...},......

]

},

{......},

......

}

### c.     获得目录的子目录

请求格式：

{"name":"c_get_question_catalog_children",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型， 1.Android,2.IOS,3.Web,4.Other   

"userId":xxx, //用户名id    

"sessionId":"E8D62A406E5816757", //sessionId

“catalogId”:xxx, //目录id

“catalogLayers”:xxx //子目录层数，默认1。2表示会返回其下2层子目录...

​    }

} 

 

响应格式：

{"code":100, //成功获得目录信息

"details":[

{

“id”:xxx, //目录id

“catalogName”:”xxx”, //目录名称 

“catalogIndex”:xxx, //目录层级，1,2,3,4... 

“versionIndex”:xxx //教材版本

“bookIndex”:xxx //课本

“versionName”:”xxx” //教材名称

“bookName”: ”xxx” //课本名称

“status”:xxx, //目录状况：0-无题无子目录，1-有子目录，2-有题

“type”:xxx //目录类型：1-知识点，2-教辅，3-试卷，4-自定义

“children”:[ //子目录，无子目录为null

{

“id”:xxx, //目录id

“catalogName”:”xxx”, //目录名称 

“catalogIndex”:xxx, //目录层级，1,2,3,4... 

“status”:xxx, //目录状况：0-无题无子目录，1-有子目录，2-有题

“type”:xxx //目录类型：1-知识点，2-教辅，3-试卷，4-自定义

“children”:[...]

},{...},......

]

},

{......},

......

]

}

{“code”:1101} //目录不存在

### d.     获得叶子目录下的题 -- 支持分页

请求格式：

{"name":"c_get_catalog_questions",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型， 1.Android,2.IOS,3.Web,4.Other   

"userId":xxx, //用户名id    

"sessionId":"E8D62A406E5816757", //sessionId

“catalogId”:xxx, //目录id

“questionType”:xxx, //题类型：1(单选),2(多选),3(判断),4(填空),5(解答)

“createUserId”:xxx  //创建人id

“difficulty”: xxx, //难度, 1-5 ,数字 0 未知 传null不限制

“[getType]()”:xxx,  //题目的类型, 1,已出过,2.未出过,0或null不限制

“pageIndex”:xxx, //当前第几页

“pageSize”:xxx //每页显示多少条

​    }

} 

 

响应格式：

{"code":100, //获得成功

"details":{ 

“recordCount”:xxx, //总共有多少条记录

“pageCount”:xxx, //总共多少页

“pageSize”:xxx, //每页显示多少条数据

“pageIndex”:xxx, //当前第几页

“questions”:[ //题的集合

{

“id”:xxx, //题id

"[judgeCollect]()":1,//题目是否已被收藏 1.已收藏，2.未收藏

"schoolId":xxx, //学校id

"schoolName":"xxx", //学校名称

“correctRate”:xxx,//正确率

​            “arrangeTimes”:xxx//布置次数

“difficulty”:xxx//难度系数      1-5 ,0 未知

"gradeId":xxx, //年级id

"gradeName":"xxx", //年级名称        

"subjectId":xxx, //科目id

"subjectName":"xxx", //科目名称

“createUserId”:xxx, //创建该题的用户id

“createUsername”:”xxx”,//创建该题的用户名称

“createName”:”xxx”,     //创建人名字.(TODO最好不加) 

“createDate”:”xxx”, //该题的创建日期，格式：yyyy-MM-dd HH:mm:ss

“contentType”:xxx, //题干类型：0(html富文本),1(普通文本),2(图片),3(音频)

“questionContent”:”xxx”,//题干，可以是图片或音频或普通文本

“questionContentThumb”:”xxx”,//题干缩略图，仅题干为图片时有效

“questionType”:xxx, //题类型：1(单选),2(多选),3(判断),4(填空),5(解答)

“correctAnswer”:””, //正确答案，主要为客观题设计，判断题为’T’或’F’

“totalScore”:xxx, //该题分数，为客观题设计，可以不传，默认100

“optionNum”:xx, //选项数量，一般只有选择题才有选项

“opts”:[ //

{

“id”:xxx, //选项id

“sequence”:xxx, //序号，排序用

“optionTitle”:”xxx”, //选项值，如A

“optionContent”:”xxx” //选项内容，可以为空

},{...},{...},...],

“analsis”:”xxx”, //解析，可以为空

“analsisType”:xxx, //解析类型：0(html富文本),1(普通文本),2(图片),3(音频)

“analsis_thumb”:”xxx”, //解析缩略图，当解析类型位图片时有效

“keyPoint”:”xxx” //知识点，可以为空

},{...},{...},...

]

}

}

{“code”:1101} //目录不存在

### e.     添加题到我的收藏

请求格式：

{"name":"c_add_collection_question",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型， 1.Android,2.IOS,3.Web,4.Other   

"userId":xxx, //用户名id    

"sessionId":"E8D62A406E5816757", //sessionId

“questionId”:xxx, //题id

​    }

} 

 

响应格式：

{"code":100} //添加收藏成功

{“code”:1100} //题不存在，添加收藏失败

{“code”:1102} //用户已经收藏了该题，添加收藏失败

### f.       批量添加题到我的收藏

请求格式：

{"name":"c_add_coll_questions",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型， 1.Android,2.IOS,3.Web,4.Other   

"userId":xxx, //用户名id    

"sessionId":"E8D62A406E5816757", //sessionId 

“questionIds”:[xxx,xxx], //题数组ids

​    }

} 

 

响应格式：

{"code":100,

 "details":{

​     "alreadyIds": [xxx,xxx] //已经添加过的题目Id

}

} //添加收藏成功

{“code”:1100} //题不存在，添加收藏失败 

 

### g.     取消收藏

请求格式：

{"name":"c_remove_collection_question",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型， 1.Android,2.IOS,3.Web,4.Other   

"userId":xxx, //用户名id    

"sessionId":"E8D62A406E5816757", //sessionId

“groupId”:xxx, //收藏组id，从该组中去掉收藏,可以为空，0为空表示从默认值删除

“questionIds”:[xxx,xxx,...],//题ids

​    }

}

 

响应格式：

{"code":100} //取消收藏成功

### h.     创建一个收藏组

请求格式： 

{"name":"c_create_collection_group",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other   

"userId":xxx,//用户id  

"sessionId":"E8D62A406E5816757", //sessionId

“groupName”:”xxx” //组名称

​    }

}

响应格式：

{"code":100, //成功获得班级信息

"details":{

“id”:xxx  //组id

}

}

{"code":1201} //收藏组已存在，添加失败

### i.        删除一个收藏组

请求格式： 

{"name":"c_delete_collection_group",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other   

"userId":xxx,//用户id  

"sessionId":"E8D62A406E5816757", //sessionId

“groupId”:”xxx” //组id

​    }

}

响应格式：

{"code":100} //删除成功，删除了组并取消了组下的所有收藏

{"code":1202} //收藏组不存在，删除失败

### j.       修改一个收藏组

请求格式： 

{"name":"c_update_collection_group",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other   

"userId":xxx,//用户id  

"sessionId":"E8D62A406E5816757", //sessionId

“groupId”:”xxx” //组id

“groupName”:”xxx” //组名称

​    }

}

响应格式：

{"code":100} //修改成功

{"code":1201} //收藏组已存在，添加失败

{"code":1202} //收藏组不存在，修改失败

### k.     获得用户所有的收藏组

请求格式： 

{"name":"c_download_collection_groups",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other   

"userId":xxx,//用户id  

"sessionId":"E8D62A406E5816757" //sessionId

 }

}

响应格式：

{"code":100, //成功获得所有的收藏组

"details":[{

“id”:xxx, //组id

“groupName”:”xxx”, //组名称

“questionCount”:xxx //该收藏下总共有多少题

},{...},...]

}

### l.        题批量复制到收藏组

请求格式： 

{"name":"c_copy_question_to_collection_group",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other   

"userId":xxx,//用户id  

"sessionId":"E8D62A406E5816757", //sessionId

“groupId”:xxx, //要复制到的目标组id

“questionIds”:[xxx,xxx,...]//题的id集合

​    }

}

响应格式：

{"code":100} //复制成功

### m.  题批量移动到收藏组

请求格式： 

{"name":"[c_]()move_question_to_collection_group",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other   

"userId":xxx,//用户id  

"sessionId":"E8D62A406E5816757", //sessionId

“fromGroupId”:xxx, //来源组id。注：如果从默认收藏移动到收藏组可用复制

“toGroupId”:xxx, //目标组id

“questionIds”:[xxx,xxx,...]//题的id集合

​    }

}

响应格式：

{"code":100} //移动成功

{“code”:1203, //移动成功，部分题在目标组中已存在,不存在的题已经移动,存在的题在当前目录删除

 “details”:[xx,xx,...]//哪些题在目标组中已存在

}

{“code”:1204, //移动失败，题在来源组中不存在 

 “details”:[xx,xx,...]//哪些题在来源组中不存在

}

### n.     获得我的收藏中的题 -- 支持分页

请求格式：

{"name":"c_get_collection_questions",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型， 1.Android,2.IOS,3.Web,4.Other   

"userId":xxx, //用户id  

"sessionId":"E8D62A406E5816757", //sessionId

“groupId”:xxx,//组id,可以为NULL，为NULL表示获取没有分组的收藏

“questionType”:xxx, //题类型：1(单选),2(多选),3(判断),4(填空),5(解答)

“pageIndex”:xxx, //当前第几页

“pageSize”:xxx //每页显示多少条

​    }

} 

 

响应格式：

{"code":100, //获得成功

"details":{ 

“recordCount”:xxx, //总共有多少条记录

“pageCount”:xxx, //总共多少页

“pageSize”:xxx, //每页显示多少条数据

“pageIndex”:xxx, //当前第几页

“questions”:[ //题的集合

{

“id”:xxx, //题id

"schoolId":xxx, //学校id

"schoolName":"xxx", //学校名称

"gradeId":xxx, //年级id

"gradeName":"xxx", //年级名称        

"subjectId":xxx, //科目id

"subjectName":"xxx", //科目名称

“createUserId”:xxx, //创建该题的用户id

“createUsername”:”xxx”,//创建该题的用户名称

“createDate”:”xxx”, //该题的创建日期，格式：yyyy-MM-dd HH:mm:ss

“contentType”:xxx, //题干类型：0(html富文本),1(普通文本),2(图片),3(音频)

“questionContent”:”xxx”,//题干，可以是图片或音频或普通文本

“questionContentThumb”:”xxx”,//题干缩略图，仅题干为图片时有效

“questionType”:xxx, //题类型：1(单选),2(多选),3(判断),4(填空),5(解答)

“correctAnswer”:””, //正确答案，主要为客观题设计，判断题为’T’或’F’

“totalScore”:xxx, //该题分数，为客观题设计，可以不传，默认100

“optionNum”:xx, //选项数量，一般只有选择题才有选项

“opts”:[ //

{

“id”:xxx, //选项id

“sequence”:xxx, //序号，排序用

“optionTitle”:”xxx”, //选项值，如A

“optionContent”:”xxx” //选项内容，可以为空

},{...},{...},...],

“analsis”:”xxx”, //解析，可以为空

“analsisType”:xxx, //解析类型：0(html富文本),1(普通文本),2(图片),3(音频)

“analsis_thumb”:”xxx”, //解析缩略图，当解析类型位图片时有效

“keyPoint”:”xxx” //知识点，可以为空

},{...},{...},...

]

}

}

### o.     批量上传作业题

请求格式： 

{"name":"[c_]()upload_questions",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other

"sessionId":"E8D62A406E5816757", //sessionId

“createUserId”:xxx, //用户id

“createUsername”:”xxx”,//用户名称   

"schoolId":xxx, //学校id

"schoolName":"xxx", //学校名称   

"gradeId":xxx, //年级id

"gradeName":"xxx", //年级名称        

"subjectId":xxx, //科目id

"subjectName":"xxx", //科目名称

“questions”:[

{

“contentType”:xxx, //题干类型：0(html富文本),1(普通文本),2(图片),3(音频)

“questionContent”:”xxx”,//题干，可以是图片或音频或普通文本

“questionContentThumb”:”xxx”,//题干缩略图，仅题干为图片时有效

“questionType”:xxx, //题类型：1(单选),2(多选),3(判断),4(填空),5(解答)

“correctAnswer”:””, //正确答案，主要为客观题设计，判断题为’T’或’F’

“totalScore”:xxx, //该题分数，为客观题设计，可以不传，默认100

“opts”:[ //

{

“sequence”:xxx, //序号，排序用

“optionTitle”:”xxx”, //选项值，如A

“optionContent”:”xxx” //选项内容，可以为空

},

{

......

},

......

],

“analsis”:”xxx”, //解析，可以为空

“analsisType”:xxx, //解析类型：0(html富文本),1(普通文本),2(图片),3(音频)

“analsis_thumb”:”xxx”, //解析缩略图，当解析类型位图片时有效

[“keyPoint”:”xxx”, //]()知识点，可以为空

“catalogIds”:[xx,xx,...]//目录ids，目前支持一个作业在多个目录下

“difficulty”: xxx, //难度, 1-5 ,数字

“source”: “xxx” ,  //来源(可以不用,schoolName或userName都可以)

 

(answerNum&answerPerch&answerFills只能对富文本或普通文本下的数学填空题有效,参数验证)

“answerNum”:xxx ,   //填空题数量 

​            “answerPerch”: “xxx”,//填空题位置(用此字符串到题干中找空位)

“answerFills”:[“xxx”,”xxx”],//填空题正确答案

},{

......

},......

]

​    }

}

响应格式：

{"code":100, //上传成功

"details":{ 

“questionIds”:[xx,xx,xx,xx,...], //作业题ids，保存后生成的id集合

}

}

### p.     一键布置作业(可以走c_upload_homework接口)

{"name":"c_assignment_homework",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other

"sessionId":"E8D62A406E5816757", //sessionId

“userId”,xxx //用户id

“flag”,xxx //1，公有 2，校内

“catalogIds”:[xx,xx,xx]//布置的目录的id ,用数组

“questionType”:xxx,//类型：1(单选),2(多选),3(判断),4(填空),5(解答)，可为空

“difficulty”: xxx, //难度, 1-5 ,数字 0 未知 传null不限制

[“]()[judgeArrange]()”:xxx,// 1.已被布置,2.未被布置过. null,判断判目录里该状态的题是否布置

(questionType, difficulty,judgeArrange)参数只是限制catalogId里面的题

“createUserId”, //用户id

“createUsername”, //用户名称 

“homeworkName”:”xxxx”, //作业名称，如果为空默认为“科目名称+当前时间”  "subjectId":xxx, //科目id

"subjectName":"xxx", //科目名称

“status”:xx, //0：待发送  1：已发送(设置为1会立即发送)

“classes”:[ //如此设计是为了将该作业同时发给多个班级

{“classId”:xxx,  //班级id

​     “className”:”xxx” //班级名称

},{...},......

]

“className”:xxx //班级名称,           

"schoolId":xxx, //学校id

"schoolName":"xxx", //学校名称   

“status”:xx, //0：待发送  1：已发送(设置为1会立即发送)

"gradeId":xxx, //年级id

"gradeName":"xxx", //年级名称    

“sendTime”:”xxx”, //推送时间，格式yyyy-MM-dd HH:mm:ss

“expiredTime”:”xxx”, //作业截止时间，格式yyyy-MM-dd HH:mm:ss。如果过期时间为空或不合法，默认为7天后过期。

 

​    }

}

{"code":100} //过期时间小于发送时间

{"code":1210} //  没有班级信息

{"code":1011} // 没有目录或目录不存在

### q.     上传作业

请求格式： 

{"name":"c_[upload_homework_student]()",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other

"sessionId":"E8D62A406E5816757", //sessionId

“createUserId”, //用户id

“flag”,xxx //1，公有 2，校内

“createUsername”, //用户名称

“homeworkName”:”xxxx”, //作业名称，如果为空默认为“科目名称+当前时间”  

“homeworkType”:xxxx, //作业类型，1,普作有分,2,随堂有分,3,普作没分,4,随堂没分,5,考试,6,预习,7,课中随堂

“courseId”:xxx,		//课程主键id 针对(课中随堂作业必填) 		t_class_course

"detectionId":xxx,		//随堂检测模版主键  针对(课中随堂作业必填)   t_class_course__detection

"courseClassId":xxx,		//上课记录主键  针对(课中随堂作业必填)   t_class_course__class

"schoolId":xxx, //学校id

"schoolName":"xxx", //学校名称   

"gradeId":xxx, //年级id

"gradeName":"xxx", //年级名称    

“classes”:[ //如此设计是为了将该作业同时发给多个班级

{“classId”:xxx,  //班级id

​     “className”:”xxx” //班级名称

},{...},......

] 

“catalogIds”:[xx,xx,xx]//目录id ,用数组,作业题目=目录下题+questions

“questionType”:xxx,//类型：1(单选),2(多选),3(判断),4(填空),5(解答)，可为空

“difficulty”: xxx, //难度, 1-5 ,数字 0 未知 传null不限制

“judgeArrange”:xxx,// 1.已被布置,2.未被布置过. null,判断判目录里该状态的题是否布置

(questionType, difficulty,judgeArrange)参数只是限制catalogId里面的题

"subjectId":xxx, //科目id

"subjectName":"xxx", //科目名称

“status”:xx, //0：待发送  1：已发送(设置为1会立即发送)

“sendTime”:”xxx”, //推送时间，格式yyyy-MM-dd HH:mm:ss

[“expiredTime”]():”xxx”, //作业截止时间，格式yyyy-MM-dd HH:mm:ss。如果过期时间为空或不合法，默认为7天后过期。

//“questions”:[xx,xx,xx,xx]//所有题的id集合

"homeworkType":  xxx ; 	//1. 日常作业 , 2.随堂检测

"homeworkScore": xxx;	//2.作业分数

“answerTime” : xxx ;    //答题时间, 日常作业为空,随堂检测必须有时间

"scoreQuestions":[

​		{"id":xxx,"score":"xx"},{},{}...

​	]  

​    }

}

响应格式：

{"code":100, //上传成功

"details":[{ 

“homeworkId”:xxx, //作业id

“homeworkName”:”xxx” //作业名称，上传作业如果作业名称为空或重复会自动在作业名称后加上当前时间做为新作业名称，所以此处需要传回作业名称 

},{...},......]

}

{"code":1006} //上传作业 ,没有指定班级或题目

{"code":1008} //发送时间不能小于系统时间 (手机时间与系统时间可能不一致,占不返回)

{"code":1009} //过期时间小于系统时间      (手机时间与系统时间可能不一致,占不返回)

{
	"code":1013,
	"details":{
		"quizWork":[//未做完的随堂检测,现每班返回一条作业,如果有多条 回最后截止时间最大的一条
				{
			“id”:xxx, //作业id
	
			“homeworkName”:”xxx”, //作业名称
	
			“createUserId”:xxx, //创建作业的用户id
	
			“createUsername”:”xxx”,//创建作业的用户名称
	
			“createDate”:”xxx”, //作业创建时间，格式：yyyy-MM-dd HH:mm:ss
	
			“sendTime”:”xxx”, //实际发布时间，格式：yyyy-MM-dd HH:mm:ss
	
			“expiredTime”:”xxx”, //作业截止时间，格式yyyy-MM-dd HH:mm:ss
	
			"schoolId":xxx, //学校id
	
			"schoolName":"xxx", //学校名称   
	
			"gradeId":xxx, //年级id
	
			"gradeName":"xxx", //年级名称        
	
			"subjectId":xxx, //科目id
	
			"subjectName":"xxx", //科目名称
	
			"subjectTeacherId":xxx, //科目老师 id
	
			"subjectTeacherName":"xxx", //科目老师名称
	
			"subjectTeacherIconUrl":"xxx", //科目老师头像路径
	
			"classId":xxx, //班级id
	
			"className":"xxx", //班级名称
	
			"parentSign":"xxx" //这个学生的家长签名, 
	
			"signDate":"xxx" //这个学生的家长签名时间, 
	
			"showStatus":"xxx" //作业显示状态  0或 null 不显示 ,1公开
	
			“status”:xxx, //作业状态，0（未发送），1（已发送学生可以作答），2（已过去，学生无法再作答）
},
{}....
		]
	}
} //过期时间小于发送时间


### r.      请求随机出题(错题本用)

请求格式：

{"name":"c_download_random_questions",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other

"sessionId":"E8D62A406E5816757", //sessionId

“userId”:xxx, //用户id  

“[getQuestionType]()”:xxx,// 1. 从题目录里面取题目,2.从所有的里面取题目

“createUserId”:xxx, //这个题的创建人id   

“questionType”:xxx,//类型：1(单选),2(多选),3(判断),4(填空),5(解答)，可为空

“isPublic”: xxx,    //是否公开题目,0：不是,1.是,-1不限制

“difficulty”: xxx, //难度, 1-5 ,数字 0||null 未知 

"catalogIds":[1,2,3，. . .],//知识点id (必须)

 

“schoolId”:xxx, //学校id

“gradeId”:xxx, //年级id

“subjectId”:xxx, //科目id

“startTime”:”xxx”, //返回startTime之后创建的题，格式：yyyy-MM-dd HH:mm:ss

“endTime”:”xxx”, //返回endTime之前，以题添加时间为准,可为空

“count”:xxx, //随机取多少条数据

​    }

}

响应格式： 

与下面 请求错题集响应一致 c_download_questions 没有翻页相关的四条数据

 

{"code":201} //getQuestionType为1从目录里面取,catalogIds 为空

 

### s.      请求私有题集

请求格式： 

{"name":"c_download_questions",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other

"sessionId":"E8D62A406E5816757", //sessionId

“userId”:xxx, //用户id  

“createUserId”:xxx  //创建人id

“difficulty”: xxx, //难度, 1-5 ,数字0未知,或null不限制

“questionType”:xxx, //题类型：1(单选),2(多选),3(判断),4(填空),5(解答)，可以为空

“keyPoint”:”xxx”, //知识点

“schoolId”:xxx, //学校id

“gradeId”:xxx, //年级id

“subjectId”:xxx, //科目id

“startTime”:”xxx”, //返回startTime之后创建的题，格式：yyyy-MM-dd HH:mm:ss

“pageIndex”:xxx, //当前第几页

“pageSize”:xxx //每页显示多少条

“rtnJudgeCollect”:xxx,// 1.判断拿到的题是否被收藏,0或null,不判断

​    }

}

响应格式：

{"code":100, //获得成功

"details":{ 

“recordCount”:xxx, //总共有多少条记录

“pageCount”:xxx, //总共多少页

“pageSize”:xxx, //每页显示多少条数据

“pageIndex”:xxx, //当前第几页

“questions”:[ //题的集合

{

“id”:xxx, //题id

"schoolId":xxx, //学校id

"schoolName":"xxx", //学校名称

"gradeId":xxx, //年级id

"gradeName":"xxx", //年级名称        

"subjectId":xxx, //科目id

"subjectName":"xxx", //科目名称

“createUserId”:xxx, //创建该题的用户id

“createUsername”:”xxx”,//创建该题的用户名称

“createDate”:”xxx”, //该题的创建日期，格式：yyyy-MM-dd HH:mm:ss

“contentType”:xxx, //题干类型：0(html富文本),1(普通文本),2(图片),3(音频)

“questionContent”:”xxx”,//题干，可以是图片或音频或普通文本

“questionContentThumb”:”xxx”,//题干缩略图，仅题干为图片时有效

“questionType”:xxx, //题类型：1(单选),2(多选),3(判断),4(填空),5(解答)

“correctAnswer”:””, //正确答案，主要为客观题设计，判断题为’T’或’F’

“totalScore”:xxx, //该题分数，为客观题设计，可以不传，默认100

“optionNum”:xx, //选项数量，一般只有选择题才有选项

“opts”:[ //

{

“id”:xxx, //选项id

“sequence”:xxx, //序号，排序用

“optionTitle”:”xxx”, //选项值，如A

“optionContent”:”xxx” //选项内容，可以为空

},{...},{...},...],

“analsis”:”xxx”, //解析，可以为空

“analsisType”:xxx, //解析类型：0(html富文本),1(普通文本),2(图片),3(音频)

“analsis_thumb”:”xxx”, //解析缩略图，当解析类型位图片时有效

“keyPoint”:”xxx” //知识点，可以为空

},{...},{...},...

]

}

}

### t.       ©添加题目到错题集

请求格式： 

{"name":"[c_]()add_wrong_questions",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other

"sessionId":"E8D62A406E5816757", //sessionId

“userId”:xxx, //用户id  

​    “questions”:[{

​    “questionId”:xxx

​     “userId”:xxx

}

,. . . 

]

​    }

}

 

响应格式： 

{"code":100} //添加成功 

### u.     请求错题集列表

请求格式： 

[{]()"name":"[c_]()get_wrong_lists",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other

"sessionId":"E8D62A406E5816757", //sessionId

“userId”:xxx, //用户id  

​    “stuId”:xxx,请求的学生id

​         “subjectId”:xxx,请求的科目id

​        }

}

响应格式： 

{"code":100, //获得成功

"details":{ 

“questions”:[ //题的集合

{ 

"subjectId":xxx, //科目id

"subjectName":"xxx", //科目名称 

"[questionNum]()":xxx, //错题集中的题数量

},{...},{...},...

​        ]

}

### v.     请求某科目下类型数量

请求格式： 

{"name":"[c_]()get_wrong_types",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other

"sessionId":"E8D62A406E5816757", //sessionId

“userId”:xxx, //用户id  

​    “stuId”:xxx,请求的学生id,

​    “subjectId”:xxx,请求的科目id 

​        }

}

请求格式：

(如数量为0不返回)

{"code":100, //获得成功

"details":{ 

“types”:[ //题的集合

{ 

"questionType":xxx, //错题类型：        1,2,3,4,5

"questionTypeName":"xxx", //[错题类型]()名称：单选题,多选题,判断题,填空题,解答题

"questionNum":xxx, //错题集中的题数量

},{...},{...},...

​        ]

}

 

### w.   上传错题集作答

 

 

### x.     请求错题集数据（TODEBUG：先不做分页,后期如需要再扩展）

 

请求格式： 

 

{"name":"[c_]()get_wrong_questions",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other

"sessionId":"E8D62A406E5816757", //sessionId

“userId”:xxx, //用户id  

​    [“stuId”:xxx,]()请求的学生id

“subjectId”:xxx,请求的科目id

“[questionType]()”:[xxx,xxx], //题类型：1(单选),2(多选),3(判断),4(填空),5(解答),空都请求

“[lastDate]()”: “xxx”,   //最后操作时间.可为空

 yyyy-MM-dd HH:mm:ss返回大于或等于这个时间的数据 

​        }

}

响应格式： 

{"code":100, //获得成功

"details":{ 

“questions”:[ //题的集合

{

“id”:xxx, //题id

"schoolId":xxx, //学校id

"schoolName":"xxx", //学校名称

"gradeId":xxx, //年级id

"gradeName":"xxx", //年级名称        

"subjectId":xxx, //科目id

"subjectName":"xxx", //科目名称

“createUserId”:xxx, //创建该题的用户id

“createUsername”:”xxx”,//创建该题的用户名称

“createDate”:”xxx”, //该题的创建日期，格式：yyyy-MM-dd HH:mm:ss

“contentType”:xxx, //题干类型：0(html富文本),1(普通文本),2(图片),3(音频)

“questionContent”:”xxx”,//题干，可以是图片或音频或普通文本

“questionContentThumb”:”xxx”,//题干缩略图，仅题干为图片时有效

“questionType”:xxx, //题类型：1(单选),2(多选),3(判断),4(填空),5(解答)

“correctAnswer”:””, //正确答案，主要为客观题设计，判断题为’T’或’F’

“totalScore”:xxx, //该题分数，为客观题设计，可以不传，默认100

“optionNum”:xx, //选项数量，一般只有选择题才有选项

“lastSolutionContent”,//最后一次作答答案

“lastSolutionType”,//最后一次作答类型

“lastAnswerFills”,//最后一次作答答案

“difficulty”: xxx, //难度, 1-5 ,数字 0||null 未知

 

 

“count”:xxx,这道题的错误次数

“type”: xxx,1.手动添加,2.自动

“stuWrongQuestionId”:xxx ,//错题id

“userId”: “xxx”     ,// 做这个题目的人

“scoreResult”:”xxx” , //这道题的正确率 （可能占时不正确

“homeworkId”:xxx ,//最后一次做这个作业的作业id

“newestDate”:”xxx”,    //最后一次错题时间

“questionResultId”:”xxx”,      //最后一次错题作答Id

“solutionContentDraft”:”xxx”,/最后一次错题草稿

“opts[”]():[//

{

“id”:xxx, //选项id

“sequence”:xxx, //序号，排序用

“optionTitle”:”xxx”, //选项值，如A

“optionContent”:”xxx” //选项内容，可以为空

},{...},{...},...],

“analsis”:”xxx”, //解析，可以为空

“analsisType”:xxx, //解析类型：0(html富文本),1(普通文本),2(图片),3(音频)

“analsis_thumb”:”xxx”, //解析缩略图，当解析类型位图片时有效

“keyPoint”:”xxx” //知识点，可以为空

},{...},{...},...

​    ]

​    "catalogIds":[1,2,3. . .],//知识点id 错题

}

### [y.     删除错题集数据]()

请求格式： 

 

{"name":"[c_]()delete_stu_wrongs ",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other

"sessionId":"E8D62A406E5816757", //sessionId

“userId”:xxx, //用户id  

​    “stuId”:xxx,学生id

​        “[questionIds]()”:[1,2,3],题目id 数组

“date”: “xxx”,       //最后操作时间. 如不为空删除这之前的数据 (占不支持)

 yyyy-MM-dd HH:mm:ss删除大于或等于这个时间的数据 

​        }

}

 

响应格式：

{"code":100, xxx}//成功删除数据 放回删除数量



### z.     分页获取目录下的文件资源

Request ：

```json
{
    "name": "c_get_catalog_resource",
    "details": {
        "catalogId": 0, //目录id
        "resourceType": 0, //资源类型，0 备课资源，1 微课资源
        "fileType": 0,   //文件类型，0 全部类型,1 视频，2 PPT, 3 Word ,4 Excel ,5 其他类型
        "pageIndex": 1, //当前第几页
        "pageSize": 10 //每页显示多少条
    }
}
```



Response ：

```json
{
    "code": 100, //100，获得成功，1590 参数错误，1101 目录不存在，99 服务器异常
    "details": { //无结果则为 null
        "recordCount":1, //总共有多少条记录
        "pageCount":1, //总共多少页
        "pageSize":10, //每页显示多少条数据
        "pageIndex":1, //当前第几页
        "resource":
              [{
                "fileName":'xxx', //文件名
                "type": 1, //1 视频，2 PPT, 3 Word ,4 Excel ,5 其他类型
                "fileSize": 0.0, //文件大小 字节
                'createTime':'yyyy-MM-dd HH:mm:ss', //创建时间
                'updateTime':'yyyy-MM-dd HH:mm:ss', //最后修改时间
                'createUserId':0, //创建人id
                'createUsername':'xxx', //创建人
                'downCount':0, //下载次数
                'schoolId':0, //学校id，没有则-1
                'status':1, //状态（暂时没用）
                'resourceStatus':1, //资源状态（暂时没用）
                'fileUrl':'http://xxxx' //文件oss下载链接
              }],
              [{...}]...
    }
}
```



### ?1.     获取Oss资源上传Key规范

Request ：

```json
{
    "name": "c_get_up_resource_dir",
    "details": {
      	"fileName": 'xxx.doc', //文件名
        "catalogId": 19, //目录id
        "schoolId": 0  //上传者的学校id
    }
}
```

Response ：

```json
{
    "code": 100,
    "details": {
      "bucketName": 'wtk-test-002',  //Oss bucketName
      "key": 'school_0/2/4/6/19/xxx.doc' //Oss key
    } 
}
```



### ?2.     Oss资源数据保存到服务器

Request ：

```json
{
    "name": "c_up_resource",
    "details": {
      	"resourceType": 0, //资源类型，0 备课资源，1 微课资源
        "fileType": 5,   //文件类型，1 视频，2 PPT, 3 Word ,4 Excel ,5 其他类型
      	"fileName":'xxx.doc', //文件名
        "fileSize": 0, //文件大小（字节）
        "catalogId": 0, //目录id
        "createUserId": 0, //用户id
        "createUsername": 0, //用户编号
        "schoolId": 0, //上传者学校id
        "fileUrl": 'http://xxx', //oss文件下载链接
    }
}
```

Response ：

```json
{
    "code": 100, //100 成功，1590 参数错误，99 异常
    "details": null
}
```





## 6)  获取作业

### a. 批量获得作业（不包含题）

请求格式： 

{"name":"c_get_homework_cacheTable",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other

"sessionId":"E8D62A406E5816757", //sessionId

“userId”:xxx, //用户id  

“userType”:xxx, //1:学生 2:老师 其他暂不支持

"schoolId":xxx, //学校id，可以为空，空表示不限制

"gradeId":xxx, //年级id，可以为空，空表示不限制

//(有classId,gradeId 先不用传,不传是查询这个班下面的所有,加gradeId是为了找升年级后的作业)

"classId":xxx, //班级id，可以为空，空表示不限制

"subjectId":xxx, //科目id，可以为空，空表示不限制

“startDate”:”xxx”, //开始日期，格式yyyy-MM-dd HH:mm:ss，可以为空，空表示不限制

“endDate”:”xxx”, //结束日期，格式yyyy-MM-dd HH:mm:ss，可以为空，空表示不限制

"keyPoint":"xxx", //知识点，模糊查询，可以为空，空表示不限制

“status”:xxx, //作业状态：0-未发布，1-已发布，2-逾期，3 已撤回 4，非撤回的作业 

“checkStatus”: xxx ,//批改状态,1：未批改,2已批改(已批阅未实现),(0/null不限制),只有有一个学生提交了未批改都会查询,相对应t_homework_stu#stat

“status”:xxx, //作业状态：0-未发布，1-已发布，2-逾期，3 已撤回 4，非撤回的作业

“hasWithdraw”:xxx,// 0 已经删除的,1.没有删除也要

“[submitStatus]()”:xxx,//提交状态：0-未提交，1-已提交，2-老师已批阅，-1.只是已提交-1-不限制. -- **submitStatus****仅学生端请求作业时有意义，表示本学生的提交状态****(****传****1****的时候放回的是已提交****+****已批阅****,****为此加个参数****-1****只返回已提交的****)******

“pageIndex”:xxx, //当前第几页

“pageSize”:xxx, //每页显示多少条

“rtnStudents”:0, //是否返回作业分配情况，默认为0-不返回，1-返回

“rtnQuestions”:0 //是否返回作业的所有题，默认为0-不返回，1-返回

"homeworkType":xxx //作业类型（String类型）

​    }

}

响应格式：

{"code":100, //成功获得作业集合

"details":{ 

“recordCount”:xxx, //总共有多少条记录

“pageCount”:xxx, //总共多少页

“pageSize”:xxx, //每页显示多少条数据

“pageIndex”:xxx, //当前第几页

“homeworks”:[ //作业集合，此处没有直接将details当做作业集合是为了以后分页扩展

{

“id”:xxx, //作业id

“homeworkName”:”xxx”, //作业名称

“homeworkType”:xxxx, //作业类型，1,普作有分,2,随堂有分,3,普作没分,4,随堂没分,5,考试,6,预习,7,课中随堂

“createUserId”:xxx, //创建作业的用户id

“createUsername”:”xxx”,//创建作业的用户名称

“createDate”:”xxx”, //作业创建时间，格式：yyyy-MM-dd HH:mm:ss

“sendTime”:”xxx”, //作业发布时间，格式：yyyy-MM-dd HH:mm:ss

“expiredTime”:”xxx”, //作业截止时间，格式yyyy-MM-dd HH:mm:ss

"questionCount":xxx,//题量

"schoolId":xxx, //学校id

"schoolName":"xxx", //学校名称   

"gradeId":xxx, //年级id

"gradeName":"xxx", //年级名称        

"subjectId":xxx, //科目id

"subjectName":"xxx", //科目名称

"subjectIconUrl":"xxx",// //科目头像路径

"classId":xxx, //班级id

"className":"xxx", //班级名称

“status”:xxx, //作业状态，0（老师未发布），1（已发布学生可以作答），2（已逾期，学生无法再作答）

“submitStatus”:xxx, //本学生提交状态：0-未提交，1-已提交，2-老师已批阅  --- 注：学生获得时有效，老师获得请无视该返回项

“showStatus”:xxx, //作业提交状态：0-未公开，1-已公开,

（学生端用此判断是否已批阅,作业下最后一个学生提交触发已批阅0->1，接口触发）

“submitDate”:”xxx”, //本学生提交日期，格式：yyyy-MM-dd HH:mm:ss --- 注：学生获得时有效，老师获得时请忽略改返回项

“allCount”:xxx, //总共有多少学生

“unSubmitCount”:xxx, //总共有多少未提交

“correctedCount”:xxx, //总共有多少学生已批阅

 “submitCount”:xxx, //该作业有多少学生提交了

“students”:[ **//****仅****rtnStudents****为****1****才返回值，否则为****NULL**

{

“userId”:xxx, //学生id

“userName”:”xxx”, //学生名称

“homeworkName”:”xxx”, //作业名称

“submitDate”:”xxx”, //提交日期

“status”:xxx //状态：0-老师发作业给学生1-学生作答提交2-老师已批阅

},{...},......

],

“questions”:[ **//****仅****rtnQuestions****为****1****才返回值，否则为****NULL**

{

“id”:xxx, //题id

"schoolId":xxx,//学校id

"schoolName":"xxx",//学校名称

"gradeId":xxx,//年级id

"gradeName":"xxx",//年级名称        

"subjectId":xxx,//科目id

"subjectName":"xxx",//科目名称

“createUserId”:xxx, //创建该题的用户id

“createUsername”:”xxx”,//创建该题的用户名称

“createDate”:”xxx”, //该题的创建日期，格式：yyyy-MM-dd HH:mm:ss

“contentType”:xxx, //题干类型：0(html富文本),1(普通文本),2(图片),3(音频)

“questionContent”:”xxx”,//题干，可以是图片或音频或普通文本

“questionContentThumb”:”xxx”,//题干缩略图，仅题干为图片时有效

“questionType”:xxx, //题类型：1(单选),2(多选),3(判断),4(填空),5(解答)

“correctAnswer”:””, //正确答案，主要为客观题设计，判断题为’T’或’F’

“totalScore”:xxx, //该题分数，为客观题设计，可以不传，默认100

“optionNum”:xx, //选项数量，一般只有选择题才有选项

“opts”:[ //

{

“id”:xxx, //选项id

“sequence”:xxx, //序号，排序用

“optionTitle”:”xxx”, //选项值，如A

“optionContent”:”xxx” //选项内容，可以为空

},{...},{...},...],

“analsis”:”xxx”, //解析，可以为空

“analsisType”:xxx, //解析类型：0(html富文本),1(普通文本),2(图片),3(音频)

“analsis_thumb”:”xxx”, //解析缩略图，当解析类型位图片时有效

“keyPoint”:”xxx” //知识点，可以为空

},{...},{...},......

]

},{...},{...},......

] 

}

}

### b. 根据作业id获取特定的一个作业（包含题）

请求格式： 

{"name":"c_get_homework_cacheTable_detail",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other   

"userId":xxx, //用户名id    

"sessionId":"E8D62A406E5816757", //sessionId

“stuId”:xxx, //学生id (有学生id,就有可能放回家长签名,没有忽略)

“homeworkId”:xxx, //作业id

“rtnStudents”:0, //是否返回作业分配情况，默认为0-不返回，1-返回

​    }

}

响应格式：

{"code":100, //成功获得班级信息

"details":{

“id”:xxx, //作业id

“homeworkName”:”xxx”, //作业名称

“createUserId”:xxx, //创建作业的用户id

“createUsername”:”xxx”,//创建作业的用户名称

“createName”:”xxx”      //创建人名字

“createDate”:”xxx”, //作业创建时间，格式：yyyy-MM-dd HH:mm:ss

“sendTime”:”xxx”, //实际发布时间，格式：yyyy-MM-dd HH:mm:ss

“expiredTime”:”xxx”, //作业截止时间，格式yyyy-MM-dd HH:mm:ss

"schoolId":xxx, //学校id

"schoolName":"xxx", //学校名称   

"gradeId":xxx, //年级id

"gradeName":"xxx", //年级名称        

"subjectId":xxx, //科目id

"subjectName":"xxx", //科目名称

"subjectTeacherId":xxx, //科目老师 id

"subjectTeacherName":"xxx", //科目老师名称

"subjectTeacherIconUrl":"xxx", //科目老师头像路径

"classId":xxx, //班级id

"className":"xxx", //班级名称

"parentSign":"xxx" //这个学生的家长签名, 

"signDate":"xxx" //这个学生的家长签名时间,

"submitStatus":"xxx" //某个人作业提交状态 

"showStatus":"xxx" //作业显示状态 0或null 不可见,1公开

“status”:xxx, //作业状态，0（未发送），1（已发送学生可以作答），2（已过去，学生无法再作答）

“questions”:[

{

“id”:xxx, //题id

“questionRate”:xxxx,

"schoolId":xxx, //学校id

"schoolName":"xxx", //学校名称

"gradeId":xxx, //年级id

"gradeName":"xxx", //年级名称        

"subjectId":xxx, //科目id

"subjectName":"xxx", //科目名称

“createUserId”:xxx, //创建该题的用户id

“createUsername”:”xxx”,//创建该题的用户名称

“createDate”:”xxx”, //该题的创建日期，格式：yyyy-MM-dd HH:mm:ss

“contentType”:xxx, //题干类型：0(html富文本),1(普通文本),2(图片),3(音频)

“questionContent”:”xxx”,//题干，可以是图片或音频或普通文本

“questionContentThumb”:”xxx”,//题干缩略图，仅题干为图片时有效

“questionResultId”:xxx,这次作答的id //有草稿纸的一定有

“solutionContentDraft”:”xxx”,//答案 草稿

“questionType”:xxx, //题类型：1(单选),2(多选),3(判断),4(填空),5(解答)

“correctAnswer”:””, //正确答案，主要为客观题设计，判断题为’T’或’F’

“totalScore”:xxx, //该题分数，为客观题设计，可以不传，默认100

“optionNum”:xx, //选项数量，一般只有选择题才有选项

“opts”:[ //

{

“id”:xxx, //选项id

“sequence”:xxx, //序号，排序用

“optionTitle”:”xxx”, //选项值，如A

“optionContent”:”xxx” //选项内容，可以为空

},{...},{...},...],

“analsis”:”xxx”, //解析，可以为空

“analsisType”:xxx, //解析类型：0(html富文本),1(普通文本),2(图片),3(音频)

“analsis_thumb”:”xxx”, //解析缩略图，当解析类型位图片时有效

“keyPoint”:”xxx” //知识点，可以为空

​    

(answerNum&answerPerch&answerFills只能对富文本或普通文本下的数学填空题有效,参数验证)

“answerNum”:xxx ,   //填空题数量 

​            “answerPerch”: “xxx”,//填空题位置(用此字符串到题干中找空位)

“answerFills”:[“xxx”,”xxx”],//填空题正确答案

 

},{...},{...},......

],

“students”:[ //仅rtnStudents为1才返回值，否则为NULL

{

“userId”:xxx, //学生id

“userName”:”xxx”, //学生学号

“name”:”xxx”, //学生名称

“homeworkName”:”xxx”, //作业名称

“submitDate”:”xxx”, //提交日期

“status”:xxx, //状态：0-老师发作业给学生1-学生作答提交2-老师已批阅

“iconUrl”:”xxx” //头像地址,为null用默认的

},{...},......

]

}

{"code":1002} //该作业不存在

{"code":1003} //该学生无此作业

 

### c. 仅根据作业id获取特定的一个作业提公布状态

（c_get_homework_detail相似少一些对其它参数的操作,）

 

{"name":"c_get_homework_showstat",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other   

"userId":xxx, //用户名id    

"sessionId":"E8D62A406E5816757", //sessionId 

“homeworkId”:xxx, //作业id 

​    }

}

响应格式：

{"code":100, //成功获得班级信息

"details":{

“id”:xxx, //作业id

“homeworkName”:”xxx”, //作业名称

“createUserId”:xxx, //创建作业的用户id

“createUsername”:”xxx”,//创建作业的用户名称

“createDate”:”xxx”, //作业创建时间，格式：yyyy-MM-dd HH:mm:ss

“sendTime”:”xxx”, //实际发布时间，格式：yyyy-MM-dd HH:mm:ss

“expiredTime”:”xxx”, //作业截止时间，格式yyyy-MM-dd HH:mm:ss

"schoolId":xxx, //学校id

"schoolName":"xxx", //学校名称   

"gradeId":xxx, //年级id

"gradeName":"xxx", //年级名称        

"subjectId":xxx, //科目id

"subjectName":"xxx", //科目名称

"subjectTeacherId":xxx, //科目老师 id

"subjectTeacherName":"xxx", //科目老师名称

"subjectTeacherIconUrl":"xxx", //科目老师头像路径

"classId":xxx, //班级id

"className":"xxx", //班级名称

"parentSign":"xxx" //这个学生的家长签名, 

"signDate":"xxx" //这个学生的家长签名时间, 

"showStatus":"xxx" //作业显示状态  0或 null 不显示 ,1公开

“status”:xxx, //作业状态，0（未发送），1（已发送学生可以作答），2（已过去，学生无法再作答）

 

}

{"code":1002} //该作业不存在 

 

### d. 根据作业id获取其完成时间和正确率

请求格式： 

{"name":"c_get_homework_stat ",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other   

"userId":xxx, //用户名id    

"sessionId":"E8D62A406E5816757", //sessionId

“homeworkId”:xxx, //作业id

“sortType”:xxx,    // 1.以时间排序, 2.以 正确率排序

“orderType”:xxx    //0.正序 1.倒序 默认正序

“topNum”: xxx ,    // 前几排序大于0的数字0或null表示不排序, 具体值代表取前几个

​    }

}

响应格式：

{"code":100, //成功获得作业信息

"details":

{

​    “homeworkRate”:xxxx;  //当前作业正确率

 “stuDetail”: [          //学生具体信息

{

“userId”:xxx, //学生id

​         “userName”: “xxx”,学生学号

“name”:”xxx”, //学生名称

“iconUrl”:”xxx头像连接

“dotime”:xxx // 做作业持续时间 单位(秒)

“accuracy”:xxx  //正确率

},{...},......

]

}

### e. 修改作业截止日期

请求格式： 

{"name":"[c_update_homework_expiredtime]()",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other   

"userId":xxx, //用户名id    

"sessionId":"E8D62A406E5816757", //sessionId

“homeworkId”:xxx, //作业id

 “expiredTime”: “yyyy-MM-dd HH:mm:ss”  // 作业截止时间

​    }

}

响应格式：

{"code":100, //成功获得作业信息

"details":{

}

{[“code”:1002]()}//修改的作业不存在

{“code”:[1007]()}//修改作业截止日期 日期为空

{“code”:1009}//截止时间不能小于系统时间

 

### [f. 修改作业状态（撤回）]()

请求格式： 

{"name":"c_update_homework_status ",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other   

"userId":xxx, //用户名id    //userId只能是老师,发送IM消息要

"sessionId":"E8D62A406E5816757", //sessionId

“homeworkId”:xxx, //作业id

}

}

响应格式：

{"code":100, //成功获得作业信息

"details":{

}

{“code”：1002}//作业不存在

{“code”：1012}//时间超时或已有学生提交  已不可撤回

 

### g. 公布作业

请求格式： 

{"name":"c_published_homework",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other   

"userId":xxx, //用户名id    //userId只能是老师,发送IM消息要

"sessionId":"E8D62A406E5816757", //sessionId

“homeworkId”:xxx, //作业id 

​    }

}

响应格式：

{"code":100 } 

{"code":1002} //该作业不存在 

 

## 7)  学生作答

### a.     上传学生作答草稿

请求格式： 

{"name":"[c_upload_answers]()_draft",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other    

"userId":xxx, //用户名id    

"sessionId":"E8D62A406E5816757", //sessionId

“homeworkId”:xxx, //作业id ，

“questionId”:xxx,这次作答的id

 

“questionResultId”:xxx,这次作答的id //不一定有,有为修改没有新增

“solutionContentDraft”:”xxx”,//答案 草稿

​    }

}

 

响应格式：

{

"code":100 ,

​    “details”:[

​        {

​            “stuWrongQuestionsId”

“homeworkId”:xxx, //作业id

“stuId”:xxx, //作答该题的学生id  

“questionResultId”:xxx,这次作答的id //有草稿纸的一定有

“solutionContentDraft”:”xxx”,//答案 草稿 

},{……}

]

}

 

### b.     上传学生作答

请求格式： 

{"name":"c_upload_answers",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other

"sessionId":"E8D62A406E5816757", //sessionId

“userId”:xxx, //用户id（此处应该是学生id）   

"homeworkId":xxx, //作业id

“answers”:[

{

“questionResultId”:xxx,这次作答的id //有草稿纸的一定有

“questionId”:xxx, //题id

"childrenQuestionId": xxx,  //小题id(如果不是小题则传0)

“questionType”:xxx, //题类型：1(单选),2(多选),3(判断),4(填空),5(解答)

[“solutionContentDraft”:”xxx”,//]()答案 草稿

“[solutionType]()”:xxx,//1，可以自动批改 2 不可自动批改

“solutionContent”:[{“xxx”},{“xxx”},.. .], //答案(文本格式的

“solutionContentImg”:[{“xxx”},{“xxx”},. . .],//答案(xxx:图片路径)

“solutionContentAud”:[{“xxx”},{“xxx”},. . .], //答案(xxx音频格)

“audioTime”:xxx,		//单位(s)

“audioScore”:xxx,		//语音分数

“audioColorString”:xxx,	//服务器就以普通文本保存

“solutionImages”:[{

sequence:xxx, //序号,从0开始

imageContent:”xxx” //图片内容

},{...}, ... ...],

“startTime”:”xxx”, //答题开始时间，格式：yyyy-MM-dd HH:mm:ss

“endTime”:”xxx”, //答题技术时间，格式：yyyy-MM-dd HH:mm:ss

“totalTime”:”xxx” //答题总时长，秒为单位

},{...},{...},......

]

​    }

}

响应格式：

{"code":100, //上传成功

"details":{ 

}

}

{“code”:1002}//上传失败：作业不存在

{“code”:1003}//上传失败：该学生无此作业

{“code”:1004}//上传失败：改作业已过期，不能再作答

{“code”:1005}//上传失败：作业已提交过，不能重复提交

### c.      获取学生作答

请求格式： 

{"name":"[c_]()download_answers",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other

"sessionId":"E8D62A406E5816757", //sessionId

“userId”:xxx, //用户id

"homeworkId":xxx, //作业id

“questionId”:xxx, //题id，可以为空，为空表示不限制

“stuId”:xxx //作答的学生id，上面有userId，userId可能是老师，跟此处可能不同，可以为空，为空表示不限制。注：题id和学生id最多只能有一个为空，题id为空表示查找该学生针对该作业的所有作答；学生id为空表示查找该题所有学生的作答。

​    }

}

响应格式：

{"code":100, //成功获得

"details":{

"homeworkAccuracy":xxx.xx //这份作业的正确率(班级)Double 

"homeworkAvgTime":xxx.xx//这份作业的平均用时(班级) Double 

"parentSign":"xxx" //这个学生的家长签名, 

"signDate":"xxx" //这个学生的家长签名时间,

“results”:[

{

“questionId”:xxx, //问题id



“questionType”:xxx, //题类型：1(单选),2(多选),3(判断),4(填空),5(解答)

“homeworkId”:xxx, //作业id

“stuId”:xxx, //作答该题的学生id

“stuName”:”xxx”, //作答该题的学生名称

“name”:”xxx”, //作答该题的学生姓名

“submitTime”:”xxx”, //作答提交到服务器的时间，格式：yyyy-MM-dd HH:mm:ss

“questionResultId”:xxx,这次作答的id //有草稿纸的一定有

“solutionContentDraft”:”xxx”,//答案 草稿

“solutionType”:xxx, //1，可以自动批改 2 不可自动批改

“solutionContent”:[{“xxx”},{“xxx”},.. .], //答案(文本格式的)

“solutionContentImg”:[{“xxx”},{“xxx”},. . .],//答案(xxx:图片路径)

“solutionContentAud”:[{“xxx”},{“xxx”},. . .], //答案(xxx音频格)

“startTime”:”xxx”, //答题开始时间，格式：yyyy-MM-dd HH:mm:ss

“endTime”:”xxx”, //答题技术时间，格式：yyyy-MM-dd HH:mm:ss

“totalTime”:”xxx”, //答题总时长，秒为单位

“correctAnswer”:”xxx”, //正确答案  -- 该项客观题才有值

“resultStatus”:”xxx”, //作答状态--null 未批改 , 1.错误,2.正确.3半对

“scoreResult”:xxx, //正确率0-100，客观题自动批阅，所以学生作答后该项就有值

“solutionType”:xxx, //答案类型：1-Path，2-图片

“solutionImages”:[{ //答案图片列表，可以为空或NULL

sequence:xxx, //序号,从0开始

imageContent:”xxx” //图片内容

},{...}, ... ...],

“tags”: “xxx|xxx|xxx”,    // 标签以|分割成字符串

 

(answerFills只能对富文本或普通文本下的数学填空题有效,参数验证) 

“studentAnswerFills”:[“xxx”,”xxx”],//填空题学生作答答案

“correctAnswerFills”: [“xxx”,”xxx”]  //数组填空题正确答案,

“answerNum”:xxx ,   //填空题数量 

​            “answerPerch”: “xxx”,//填空题位置(用此字符串到题干中找空位)

},{...},{...},......

]

}

}

### d.     作业不会作答找老师提问

请求格式： 

{"name":"c_present_question",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other

"sessionId":"E8D62A406E5816757", //sessionId

“userId”:xxx, //用户id

“presentType”: xxx//0,null 提问,1. 第二次提问

“stuId”:xxx  , //学生id 

“teaId”:xxx  , //询问老师id

"[homeworkId]()":xxx, //作业id

“[questionId]()”:xxx,//题id，可以为空，为空表示不限制 

​    }

}

响应格式：

{"code":100, //成功提交

"details": { //第二次提问返回才有意义

'stuId': 'xxx',  //学生id

  'teaId': 'xxx',   //老师id

  'questionId': xxx //题目id

  'homeworkId': xxx //作业id

'contentType': xxx, //题干类型：0(html富文本),1(普通文本),2(图片),3(音频)

'questionContent':'xxx', //题干，可以是图片或音频或普通文本

}

}

{"code":1100, //题目没有找到(系统已删除) }

### e.      家长确定检查过学生的作业(类似用于签到)

请求格式： 

{"name":"[c_]()parent_checkout_homework",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other

"sessionId":"E8D62A406E5816757", //sessionId

“userId”:xxx, //用户id

“stuId”:xxx  , //学生id 

"homeworkId":xxx, //作业id 

"parentSign":"xxx", //家长签字

​    }

}

响应格式：

{"code":100, //成功提交}

{"code":1100, //题目没有找到(系统已删除) }//是作业找不到

 

 

## 8)  批阅结果

### a.     获得作业分配给了哪些学生

请求格式： 

{"name":"c_download_homework_students",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other   

"userId":xxx, //用户名id    

"sessionId":" E8D62A406E5816757", //sessionId

“homeworkId”:xxx //作业id

​    }

}

响应格式：

{"code":100, //获得成功

"details":{

"stuDetail": [

{

“userId”:xxx, //学生id

“iconUrl”:”xxx”//头像

“userName”:”xxx”, //学生账户名

“name”:”xxx”, //学生姓名

“homeworkName”:”xxx”, //作业名称

“status”:xxx //状态：0-老师发作业给学生1-学生作答提交2-老师已批阅

"parentSign":"xxx" //这个学生的家长签名, 

"signDate":"xxx" //这个学生的家长签名时间,

},{...},......

]

"correctRate": xxx  //新增当前作业正确率

}

}

{“code”:1002} //作业不存在[或者作业中没有题]()

### b.     老师上传批阅结果

请求格式： 

{

 "name": "c_upload_check_results",

 "details": {

   "deviceType": "Android",

   "homeworkId": "xxx",

   "protocol": "xxx",

   "results": [

​      {
​ 		"commentsAud": ["xxx","xxx","xxx"],//音频,xxx是文件可以访问的地址
​ 		"commentsImg": ["xxx","xxx","xxx"],//图片,
​ 		"commentsPath": [
​ 		{
​           "canvasHeight": xxx,
​           "canvasWidth": xxx,
​           "lines": [
​             {
​               "color": "xxxxxxx",//例 ffff0000（需此种格式）
​               "points": [
​                 {
​                   "prs": x,
​                   "x": xxx,//x坐标
​                   "xv": x,
​                   "y": xxx,//y坐标
​                   "yv": x
​                 },
​                 {

​                   "prs": 1,

​                    "x": 557.5723,

​                   "xv": 0,

​                   "y": 562.09937,

​                   "yv": 0

​                 }

​               ]

​             }

​           ]

​          }

​        ],

​       "questionId": "643",//题目id

​        "score": 100, //分数

​       "stuId": "20517",//学生id

​      }

​    ],

   "sessionId": "eeb8f041-dc53-4f6d-9280-6bf15f75e4b9",

   "userId": "20509",

   "userType": 2,

   "versionId": 1,

   "versionName": "1.0"

  }

}响应格式：

{"code":100, //上传成功

"details":{

}

}

{“code”:1003,//上传失败：学生无此作业

“details”:[ //具体哪些没有学生题对应关系

{

“homeworkId”:xxx, //作业id

“questionId”:xxx, //题id

“stuId”:xxx //学生id

}

]

}

### c.      获得批阅结果(包含学生作答)

请求格式： 

{"name":"c_download_check_results",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other

"sessionId":"E8D62A406E5816757", //sessionId

“userId”:xxx, //用户id

"homeworkId":xxx, //作业id

 “questionId”:xxx, //题id，可以为空，为空表示不限制

 "versionId": xxx,

 "versionName": "xxx"

“stuId”:xxx //作答的学生id，上面有userId，userId可能是老师，跟此处可能不同，可以为空，为空表示不限制。注：题id和学生id最多只能有一个为空，题id为空表示查找该学生在该题中所有的批阅结果；学生id为空表示查找该题的所有批阅结果。

"childQuestionId":xxx //小题id

​    }

}

响应格式：

{"code":100, //成功获得

"details":{ 

"homeworkAccuracy":xxx.xx //这份作业的正确率(班级)Double 

"homeworkAvgTime":xxx.xx//这份作业的平均用时(班级) Double 

 

"parentSign":"xxx" //这个学生的家长签名,  

"signDate":"xxx" //这个学生的家长签名时间,

“results”:[

{

“questionId”:xxx, //问题id

 "questionChildrenResults": [ //小题作答结果

          {
            "childrenId": 237,   //小题id
            "commentsImg": null, //老师批阅结果
            "correctAnswer": "C", //正确答案
            "createUserId": 71433,
            "currScore": null,
            "featureCode": "100001", //小题code
            "homeworkId": 56937,
            "id": 13,
            "parentQuestionId": 4343089, //大题id
            "questionScore": null,
            "questionType": 1,
            "scoreResult": 0, //正确率
            "solutionContent": "[A]", //学生文本作答
            "solutionContentAud": null, //学生音频作答
            "solutionContentImg": null, //学生图片作答
            "status": null,
            "tags": null,   //老师批改评语
            "totalTime": 7  //学生作答用时
          },
          {。。。 。。。}，
          {。。。 。。。}，
          {。。。 。。。}
        ],
“questionType”:xxx, //题类型：1(单选),2(多选),3(判断),4(填空),5(解答)

“homeworkId”:xxx, //作业id

“stuId”:xxx, //作答该题的学生id

“stuName”:”xxx”, //作答该题的学生名称

“name”:”xxx”, //作答该题的学生姓名

"iconUrl": xxxx,//作答该题的学生头像

“submitTime”:”xxx”, //作答提交到服务器的时间，格式：yyyy-MM-dd HH:mm:ss

“startTime”:”xxx”, //答题开始时间，格式：yyyy-MM-dd HH:mm:ss

“endTime”:”xxx”, //答题技术时间，格式：yyyy-MM-dd HH:mm:ss

“totalTime”:”xxx”, //答题总时长，秒为单位

“resultStatus”:”xxx”, //作答状态--null 未批改 , 1.错误,2.正确.3半对

 “scoreResult”:xxx, //正确率0-100 

“solutionType”:xxx, //1，可以自动批改 2 不可自动批改

“solutionContent”:[{“xxx”},{“xxx”},.. .], //答案(文本格式的)

“solutionContentImg”:[{“xxx”},{“xxx”},. . .],//答案(xxx:图片路径)

“solutionContentAud”:[{“xxx”},{“xxx”},. . .], //答案(xxx音频格)

“solutionImages”:[{ //答案图片列表，可以为空或NULL

sequence:xxx, //序号,从0开始

imageContent:”xxx” //图片内容

},{...}, ... ...],

“commentsImages”:[{ //答案图片列表，可以为空或NULL

sequence:xxx, //序号,从0开始

imageContent:”xxx” //图片内容

“tags”: “xxx|xxx|xxx”//作答标签

(answerFills只能对富文本或普通文本下的数学填空题有效,参数验证) 

“answerFills”:[“xxx”,”xxx”],//填空题正确答案

},{...}, ... ...],

},{...},{...},......

],

“students”:[ //仅rtnStudents为1才返回值，否则为NULL

{

“userId”:xxx, //学生id

“submitDate”:”xxx”, //提交日期

“status”:xxx, //状态：0-老师发作业给学生1-学生作答提交2-老师已批阅

“userName”:”xxx”, //学生学号(返回null)

“name”:”xxx”,           //学生名称 (返回null)

“homeworkName”:”xxx”, //作业名称(返回null)

“iconUrl”:”xxx” //头像地址,为null用默认的(返回null)

},{...},......

]

 

}

}

 当childQuestionId不为空时查询当前作业一道题的学生作答（教师端按题批改用）

{
  "code": 100,
  "details": [

    "unCorrected": 2,
    "results":[
      "audioDurations": null,
      "childrenId": 1231,
      "commentsImg": null,
      "correctAnswer": "A",
      "createUserId": 1211231385,
      "currScore": null,
      "featureCode": "100001",
      "homeworkId": 58265,
      "id": 1646,
      "parentQuestionId": 4344095,
      "questionScore": null,
      "questionType": 1,
      "scoreResult": 100,
      "solutionContent": [
        "A"
      ],
      "solutionContentAud": null,
      "solutionContentImg": null,
      "solutionContentPath": null,
      "status": null,
      "tags": null,
      "totalTime": null,
      "userName": "1006608"
    }]
  ],
  "msg": "操作成功",
  "sysDate": "2017-09-13 15:25:42",
  "userTokenService": {},
  "versions": null
}

## 9)  概率统计

### a.   学生端统计

请求格式：

{"name":" [c_get_stu_probability_statis]()",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other

"userType":xxx,         //1.学生 2.老师 3.家长

"sessionId":"E8D62A406E5816757", //sessionId

“userId”:xxx, //学生id

“subjectId”:xxx //科目id

​    }

}

 

响应格式：

{"code":100, //成功获得

"details":

​        {

“doHomeworkTimes”:XXX,         //答题总时长，秒为单位(教师请求返回)

​        “correctRate”:xxx,             //班级正确率         (教师请求返回)

​        “questionAmount”:XXX,          //所有的题量

​        “coverKnowledge”:XXX           //覆盖知识点个数

​        “catalog”:[{                   //知识点名称，该知识点占总知识点的个数和百分比

​                “catalogName”:”xxx”,  //知识点名称

​                “catalogNum”:xxx,      //知识点个数

​                “catalogProportion”:xxx.xxx  //知识点百分比

}

,{…}

,{…}

...

]

​        “learnedKnowledge”:XXX         //已学知识点个数

​        “averageCorrect”:XXX.XXX       //正确率

​        “knowledgePoints”:xxx          //正确率在90-100%的知识点个数

​        “curWeekComplete”:xxx          //本周完成题目的总数

​        “weekAccuracy”:XXX.XXX         //近一周的正确率

​        “accuraccyPromote”:xxx.xxx      //本周正确率提升    

​        “curWeekAccuracy”:[{           //近一周的正确率{周几，对应正确率}

​                “week”:”xxx”;         // 周几

​                “accuraccy”:xxx.xxx     //正确率

}

,{…}

,{…}

…

]

​        }

}

 

### b.   班群详情

请求格式： 

{"name":" c_get_tea_class_details",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other   

"userId":xxx, //用户名id

"classId":xxx,  //班级id

"sessionId":" E8D62A406E5816757", //sessionId

​    }

}

 

响应格式：

{"code":100,

​    "details":{

“submissionRate”:xxx, //作业提交率

“homeworkCount”:xxx, //累计作业数 

“correctRate”:xxx, //正确率

“questionCount”:xxx, //题量    

“stuRate”:[

​    {

​        “uesrName”:xxx, //学生姓名

“correctRate”:xxx, //学生正确率 

“submitRate”:xxx, //学生提交率

"userId":xxx,//学生id 

"iconUrl":“xxxx”,// 学生头像          

},

{. . .},

{. . .},

. . .]

​        }

}

 

### c.    学情分析

请求格式：

{"name":" c_get_learn_situation_analysis",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other   

"userId":xxx, //用户名id

"classId":xxx,//班级id 

"sessionId":" E8D62A406E5816757", //sessionId

​    }

}

 

响应格式：

{"code":100,

​    "details":{

“homeworkCount”:xxx, //作业总数

“questionCount”:xxx, //题量

“catalogCount”:xxx, //知识点

“correctRate”:xxx, //正确率 

“currentWeekRate”:xxx, //本周正确率 

“ratePromote”:xxx, //本周正确率提升（这个不再返回）

}   

}

### d.   作业分析（一个/多个班 所有作业）

请求格式：

{"name":"c_get_homework_analysis",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other   

"userId":xxx,//用户名id

“state”:xxx /Integer 1.月 2.周 3.日  

"classIds":[xxx,xxx],// 班级id

"sessionId":" E8D62A406E5816757", //sessionId

​    }

}

 

响应格式：

{"code":100,

​    "details":[

[{

​    “name”:”xxx”,       //班级名称

​    “day”:xxx,          //日期

“submitRate”:xxx,   //提交率

“correctRate”:xxx, //正确率

“doTimes”:xxx       //做作业总时间(秒)

}，

{

​    “name”:”xxx”,       //班级名称

​    “day”:xxx,          //日期

“submitRate”:xxx,   //提交率

“correctRate”:xxx, //正确率

“doTimes”:xxx       //做作业总时间(秒)

}，

{. . . },

{. . . },

{. . . }],

[. . . ],

[. . . ]

]

}

### e.    知识点分布

请求格式：

{"name":" c_get_knowledge_point_distribution",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other   

"userId":xxx,//用户名id

"classId":xxx,// 班级id

"sessionId":" E8D62A406E5816757", //sessionId

​    }

}

 

响应格式：

{"code":100,

​    "details":[

{

​    “catalogName”:”xxx”,        //知识点名称

​    “catalogNum”:xxx,           //知识点数量

“catalogProportion”:xxx,    //知识点正确率

}，

{

​    “catalogName”:”xxx”,        //知识点名称

​    “catalogNum”:xxx,           //知识点数量

“catalogProportion”:xxx,    //知识点正确率

}，

{. . . },

{. . . },

{. . . }

]

}

 

### f.     当前作业学生做题情况排名

请求格式：

{"name":" c_get_stu_current_homework_rank",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other   

"userId":xxx,//用户名id

"classId":xxx,// 班级id

"homeworkId":xxx,// 作业id

"sessionId":" E8D62A406E5816757", //sessionId

​    }

}

响应格式：

{"code":100,

​    "details":[

{

​    “uesrName”:”xxx”,       //学生姓名

​    “submitRate”:xxx,       //提交率

“correctRate”:xxx       //学生正确率

"userId":xxx,           //学生id  

"iconUrl":“xxxx”,     // 学生头像    

}，

{

​    “uesrName”:”xxx”,       //学生姓名

​    “submitRate”:xxx,       //提交率

“correctRate”:xxx       //学生正确率

"userId":xxx,           //学生id  

"iconUrl":“xxxx”,     // 学生头像    

}，

{. . . },

{. . . },

{. . . }

]

}

### g.   单个学生批改

请求格式：

{"name":" c_get_stu_single_correct_detial",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other   

"userId":xxx,//用户名id

"stuId"::xxx,// 学生id

"homeworkId":xxx,// 作业id

"sessionId":" E8D62A406E5816757", //sessionId

​    }

}

响应格式：

{"code":100,

​    "details":

{

​    “stuCorrect”:”xxx”,     //学生本次作业的正确率

​    “homeworkCorrect”:xxx       //班级的正确率

}

}

 

### h.   统计主界面

请求格式：

{"name":" c_get_stu_single_correct_detial",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other   

"userId":xxx,//用户名id

"state":1, // 1.月 2.周 3.日

"sessionId":" E8D62A406E5816757", //sessionId

​    }

}

响应格式：

{"code":100,

​    "details":[

[{

​    “name”:”xxx”,       //班级名称

​    “day”:xxx,          //日期

“correctRate”:xxx,  //正确率

“submitRate”:xxx    //提交率

}，

{

​    “name”:”xxx”,       //班级名称

​    “day”:xxx,          //日期

“correctRate”:xxx,  //正确率

“submitRate”:xxx    //提交率

}，

{. . . },

{. . . },

{. . . }

],

[. . .],

[. . .]

}

 

 

### [i.      作业分析(]()几个学生，所有作业)

请求格式：

{"name":"c_get_stu_analysis",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型， 1.Android,2.IOS,3.Web,4.Other

"sessionId":"E8D62A406E5816757", //sessionId

“userId”:xxx, //用户id  

​        “stuIds”:[1,2,3], 学生id

​        "state":1,// 1.月 2.周 3.日

"classId":1 //班级id

​        }

}

响应格式：

{"code":100,

​    "details":[

​        [

{

"id":xxx,//学生id   

"iconUrl": “xxxx”,//学生头像    

​    “name”:”xxx”,       //学生姓名

​    “day”:xxx,          //时间

“submitRate”:xxx,   //提交率

“correctRate”:xxx,  //正确率

}，

{

"userId":xxx,//学生id   

"iconUrl": “xxxx”,//学生头像    

​    “name”:”xxx”,       //学生姓名

​    “day”:xxx,          //时间

“submitRate”:xxx,   //提交率

“correctRate”:xxx,  //正确率

}，

{. . . },

{. . . },

{. . . }

]

[. . .],

[. . .],

[. . .]

]

 

}

 

 

 

 

 

 

### j.      统计详情(几个班，几个知识点)

请求格式：

{"name":"c_get_class_knowledge_correct",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型， 1.Android,2.IOS,3.Web,4.Other

"sessionId":"E8D62A406E5816757", //sessionId

“userId”:xxx, //用户id  

​        "catalogIds":[1,2,3，. . .],//知识点id

"classIds":[1,2,3，. . .]   //班级id 或根据老师查询所教的班级

​        }

}

响应格式：

{"code":100,

​    "details":[

​        [

{

​    “catalogName”:”xxx”,        //知识点名称

​    “correctRate”:xxx,          //正确率

“name”:xxx,                 //班级名称

}，

{

​    “catalogName”:”xxx”,        //知识点名称

​    “correctRate”:xxx,          //正确率

“name”:xxx,                 //班级名称

}，

{. . . },

{. . . },

{. . . }

]

[. . .],

[. . .],

[. . .]

]

 

}

 

### k.   统计详情(几个学生，几个知识点)

请求格式：

{"name":"c_get_stu_knowledge_correct",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型， 1.Android,2.IOS,3.Web,4.Other

"sessionId":"E8D62A406E5816757", //sessionId

“userId”:xxx, //用户id  

​        "catalogIds":[1,2,3，. . .],//知识点id

"stuIds":[1,2,3，. . .] //班级id 或根据老师查询所教的班级

​        }

}

响应格式：

{"code":100,

​    "details":[

​        [

{

"id":xxx,//学生id   

"iconUrl": “xxxx”,//学生头像    

​    “name”:”xxx”,       //学生姓名

​    “catalogName”:xxx,  //知识点名称

“correctRate”:xxx,  //正确率

}，

{

"userId":xxx,//学生id   

"iconUrl": “xxxx”,//学生头像    

​    “name”:”xxx”,       //学生姓名

​    “catalogName”:xxx,  //知识点名称

“correctRate”:xxx,  //正确率

}，

{. . . },

{. . . },

{. . . }

]，

[. . .],

[. . .],

[. . .]

]

 

}

## 10)        商城接口

### a．获取app下载列表(webView 加载页面,暂时不用)

请求格式： 

{"name":"c_get_apps",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other

"sessionId":"E8D62A406E5816757", //sessionId

“userId”:xxx, //用户id  

“userType”:xxx, //1:学生 2:老师 其他暂不支持

 

"appTypeId":xxx, //软件分类id，可以为空，空表示不限制

“sortord”:”xxx”， //排序方式    desc 按下载量降序,asc按下载量升序, 默认降序

“startDate”:”xxx”, //开始日期，格式yyyy-MM-dd HH:mm:ss，可以为空，空表示不限制

“endDate”:”xxx”, //结束日期，格式yyyy-MM-dd HH:mm:ss，可以为空，空表示不限制

"keyPoint":"xxx", //app，模糊查询，可以为空，空表示不限制

“status”:xxx, //app状态：0-未审核，1-审核中，2-审核通过，-1,不限制

“submitStatus”:xxx, //提交状态：0-未提交，1-已提交，2-老师已批阅，-1-不限制.  

 

“pageIndex”:xxx, //当前第几页

“pageSize”:xxx, //每页显示多少条

​    }

}

响应格式：

{"code":100, //成功获得作业集合

"details":{ 

“recordCount”:xxx, //总共有多少条记录

“pageCount”:xxx, //总共多少页

“pageSize”:xxx, //每页显示多少条数据

“pageIndex”:xxx, //当前第几页

“apps”:[ //app列表集合，此处没有直接将details当做集合是为了以后分页扩展

{

“id”:xxx, //app id

“appName”:”xxx”, //软件名称

"appTitlePath":"xxx", //软件头像

“createUserId”:xxx, //创建app的用户id

“createUsername”:”xxx”,//创建app的用户名称

“createDate”:”xxx”, //app创建时间，格式：yyyy-MM-dd HH:mm:ss

 “createComments”:”xxx” //APP描述

"downloadCount":xxx,//下载量

"appFileSize":xxx, //文件大小   数字计算,数字

"appFilePath":"xxx", //软件下载链接  

"appMd5":xxx, //数字,保证下载后的数据完整性 

 “status”:xxx, //app状态：0-未审核，1-审核中，2-审核通过

},{...},{...},......

] 

}

}

### b.   根据id获取具体的app内容(webView,暂时不用)

请求格式： 

{"name":"c_get_app_detail",

​    "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other   

"userId":xxx, //用户名id    

"sessionId":"E8D62A406E5816757", //sessionId

 

“appId”:xxx, //appid  

​    }

}

响应格式：

{"code":100, //成功获得app信息

"details":{

“id”:xxx, //appid

“appName”:”xxx”, //软件名称

"appTitlePath":"xxx", //软件头像

“createUserId”:xxx, //创建app的用户id

“createUsername”:”xxx”,//创建app的用户名称

“createDate”:”xxx”, //app创建时间，格式：yyyy-MM-dd HH:mm:ss

 “createComments”:”xxx” //APP描述

"downloadCount":xxx,//下载量

"appFileSize":xxx, //文件大小   数字计算,数字

"appFilePath":"xxx", //软件下载链接  

"appMd5":xxx, //数字,保证下载后的数据完整性 

“status”:xxx, //app状态：0-未审核，1-审核中，2-审核通过

 

“pictures”: [“xxx”,”xxx”,…]//app里面的描述图片里面的是图片路径

}

}

{"code":xxx} //该app不存在

{"code":xxx} //该app被停用无法下载

 

## 11)        阿里云Oss接口

### a．从服务器拿到操作阿里云权限

请求格式：

{"name":"c_get_oss_permission",

  "details":{

“protocol”:”1.0”, //协议版本

“deviceType”:”xxxx”, //设备类型，1.Android,2.IOS,3.Web,4.Other     

"userId":xxx,//用户id    

"userType":xxx,//用户类型 

"versionId":xxx       //版本id    (字段先保留,避免有问题的APK不能上传)

​      "versionName": "xxx", //当前版本号

"sessionId":"E8D62A406E5816757", //sessionId

​       "encrypt":"xxx"//deviceType 为4时候加密后的值与服务器保存的一致才给权限

  }

}

响应格式:

 {

"code":100, //成功获得app信息

"details":{

“accessKeyId”:xxx, //

“accessKeySecret”:”xxx”,//

“securityToken”:”xxx”, //

 “expiration”:”xxx” //

"sysDate":xxx,// 服务器时间 格式: yyyy-MM-dd HH:mm:ss:SSS

"address":xxx, eg: oss-cn-beijing.aliyuncs.com

​        "bucketName", "xxx" // eg:wtk-001

"fileRootDir":{

["]()questionContentImage", "xxx" // 1.题目题干-图片,

"questionAnalysisImage", "xxx"//2.题目解析-图片

"questionCorrectImage", "xxx" //,3.批改-图片,

"questionResultImage", "xxx"//4.作答-图片(Integer)

"questionContentAudio", "xxx";//5.题目题干-音频,

"questionAnalysisAudio", "xxx";//6.题目解析-音频,

"questionCorrectAudio", "xxx";//7.批改-音频,

"questionResultAudio", "xxx" //8.作答-音频, 

"userIcons", "xxx" //9.头像-频

"default", "xxx" //其他

}, //软件下载链接     eg: icon/2017-01-14

 

}

}

{"code":1301} //我们认为没有上传权限

{"code":1302 }  //对阿里云获取权限错误

 

# 五、百度云Push

## 1）通信格式

推送采用百度云，推送消息采用json格式，基本格式如下：

推送消息格式为：{"type":消息类型, "details":附加数据}

## 2）老师发布作业通知学生

{"type":”push_post_homework”, 

"details":{

“homeworkId”:xxx, //作业id

}

}

## 3）学生完成作业通知老师

{"type":”push_submit_answers”, 

"details":{

“stuId”:xxx, //学生id

“homeworkId”:xxx, //作业id

}

}

## 4）老师批改结束通知学生

{"type":”push_correct_results”, 

"details":{

“homeworkId”:xxx, //作业id

“questionId”:xxx //题ids

}

}

# 六、OpenIm

OpenIm 是单向的,主要负责服务器端给客户端发送消息聊天界面定制化个人信息

### [a.     发布作业(]()群聊) 

(web端作业点击发布按钮  || 老师端上传作业_c_upload_homework(需标明立即)) 

发送端: 

DispatchWorkDate:

[{]()

 'createId': xxx,  //作业创建人

 'homeworkId': xxx,     作业id

'homeworkName': xxx,   作业名称

 'subjectId': xxx,      科目id

 'subjectName': 'xxx', 科目名称

 'subjectTeacherId': XXX ,//科目老师id

 'date': 'yyyy-MM-ddHH:mm:ss',

'expiredTime': 'yyyy-MM-ddHH:mm:ss' //截止时间

}

### b.     撤回作业(群聊)  

(web端  || 老师端上传作业_c_ update_homework_status )

发送端: 

RecallHomework:

{

 'createId': xxx,  //作业创建人

 'homeworkId': xxx,     作业id

 'homeworkName': xxx,   作业名称

 'subjectId': xxx,      科目id

 'subjectName': 'xxx', 科目名称

 'subjectTeacherId': XXX ,//科目老师id

'expiredTime': 'yyyy-MM-ddHH:mm:ss',//截止时间

 'date': 'yyyy-MM-ddHH:mm:ss'

}

### c.     公布作业(群聊)  

(web端  || 老师端公布作业_c_published_homework )

发送端:（按班级布置） 

PublishedHomework:

{

 'createId': xxx,  //作业创建人

 'homeworkId': xxx,     作业id

 'homeworkName': xxx,   作业名称

 'homeworkType': xxx,   作业类型

 'subjectId': xxx,      科目id

 'subjectName': 'xxx', 科目名称

 'subjectTeacherId': XXX ,//科目老师id

 'date': 'yyyy-MM-dd HH:mm:ss'

}

发送端: （按人布置）

publishedHomeworkStudent:

{

 'createId': xxx,  //作业创建人

 'homeworkId': xxx,     作业id

 'homeworkName': xxx,   作业名称

 'homeworkType': xxx,   作业类型

 'subjectId': xxx,      科目id

 'subjectName': 'xxx', 科目名称

 'subjectTeacherId': XXX ,//科目老师id

 'date': 'yyyy-MM-dd HH:mm:ss'

}

 

### d.     老师批改作业(单聊) 

(教师端批改完成作业触发) c_upload_check_results

CheckWorkFinished 

   {

 'createId': xxx,  //作业创建人

 'homeworkId': xxx,     作业创建人

'homeworkName':xxx,       作业名称

 'subjectId': xxx,      科目id

 'subjectName': 'xxx', 科目名称

 'date': 'yyyy-MM-dd HH:mm:ss' 时间

}

 

 

### e.     家长请求绑定学生(单聊)

(家长发送绑定请求) c_band_p_stu

ParentBindConfirm

 

 

{

  'customizeMessageType': 'ParentBindConfirm',  //绑定类型

  'stuId': 'xxx',                                //学生id

'stuName':'xxx',                          //学生姓名

  'parId': 'xxx',                               //家长id

  'phoneNumber': 'xxx'                          //家长手机号码

}

### f.       孩子响应家长绑定请求(单聊) 

[(]()孩子响应绑定请求 ) c_respond_p_stu 

StudentBindConfirm

{  

'customizeMessageType':'StudentBindConfirm',

  'stuId': 'xxx',   //学生id

'stuName':'xxx',                          //学生姓名

'schoolId':'xxx',                             //学校Id

['schoolName':'xxx',                           //]()学校名称

  'parId': 'xxx',   //家长id

  'flag': 'xxx' 1. 同意家长绑定,2不同意家长绑定

 

}

 

### g.     家长请求定位孩子位置(单聊)

(教师端批改完成作业触发) c_site_p_stu

ParentFenceConfirm

{ 

'customizeMessageType':'ParentFenceConfirm', 

  'stuId': 'xxx',       //学生id

'stuName':'xxx',                          //学生姓名

'schoolId':'xxx',                             //学校Id

'schoolName':'xxx',                           //学校名称

 

  'parId': 'xxx',       //家长id

  'password': 'xxx', //请求密码,必须与学生绑定时对的上

  'phoneNumber': 'xxx' //家长电话号码

“siteType”: “xxx” , //1.请求孩子位置, 2取消监控位置,3.获取实时位置(code:180)

}

### h.     孩子响应家长绑定位置(单聊) (流程弃用)

[(]()孩子响应绑定位置请求触发 ) c_respond_site_p_stu 

StudentFenceConfirm

{

'customizeMessageType':'StudentFenceConfirm', 

  'stuId': 'xxx',   //学生id

  'parId': 'xxx',   //家长id

  'flag': 'xxx'     //1.同意家长监控位置,2不同意家长监控位置

 

}

### i.        学生上传位置信息告诉家长(单聊)

(孩子上传位置数据触发 ) c_upload_site_stu_p

StudentSendLocation

 

{

 'customizeMessageType': 'StudentSendLocation',

  'stuId': 'xxx',  //学生id

  'parId': 'xxx',   //家长id

  'flag': 'xxx'     //1.

  'timestrap': 'xxx' // 时间 yyyy-MM-ddHH:mm:ss

}

### j.       学生上传不会的题目请求给老师 (单聊)

(孩子上传位置数据触发 ) StudentPresentQuestion

StudentPresentQuestion 

{

 'customizeMessageType': '[StudentPresentQuestion]()',

  'stuId': 'xxx',  //学生id

  [']()[teaId]()':'xxx',   //老师id 

  '[questionId]()': xxx //题目id

  '[homeworkId]()': xxx //作业id

'contentType': xxx, //题干类型：0(html富文本),1(普通文本),2(图片),3(音频)

'[questionContent]()':'xxx', //题干，可以是图片或音频或普通文本

 

}

 

### k.     学生做完作业发送信息给家长 (单聊)

(孩子上传作答触发 ) StudentFinishedHomeworkTellParent

[StudentFinishedHomeworkTellParent]() 

{

 'customizeMessageType': 'StudentFinishedHomeworkTellParent ',

  'stuId': 'xxx',  //学生id

  'teaId': 'xxx',   //老师id

  'stuName': 'xxx',//学生名字

  'submitStatus': 'xxx',//作业状态

  'expiredTime': 'xxxx-mm-dd ss:hh:mm', // 作业过期时间

  'createId': xxx //作业创建人id

  'homeworkId': xxx //作业id,

'homeworkTitle': xxx //作业名称,

'subjectId': xxx //科目 id

'date': 'xxxx-mm-ddss:hh:mm', // 作业创建时间

}

 

 

### l.        定制系统消息(单聊)(统一一个账户)

(类似与一个系统角色) SystemMessage

{

 'customizeMessageType': '[SystemMessage]()',

'msgTitle'：'xxx',//系统消息标题

  'msgContent': 'xxx'//系统消息内容

  'herfUrl': 'xxx',  //跳转链接url

  'imageUrl': 'xxx',  //图片url

'date': 'xxxx-mm-ddss:hh:mm', // 作业创建时间,

'expired':  xxx //过期天数,如果有时间限制几天后不让其进去,默认-1

}

 

### m.  学生设备关机发送消息告诉家长(可能多个)

(孩子关机主动发送请求 ) StudentShutdown

 

{

 'customizeMessageType': 'StudentShutdown',

  'stuId': 'xxx',  //学生id 

  'stuName': 'xxx',//学生名字  

'type': 'xxx', //1：关机 2：开机

'updateDate': 'xxxx-mm-ddss:hh:mm', // 开关机修改时间

}

 

 

# 七、Jasper

### a.改变SIM卡状态

(app端  c_sim_change_status )

{

 'iccid': 'xxx',//集成电路id，iccid，该编码号印制在sim卡上

 'imsi': 'xxx',//判断运营商字段

'type': 'xxx',//状态类型，1激活 2停用 3已失效

'username': 'xxx',//用户账号

'password': 'xxx'//用户密码（加密）

}

 

Response : 

{

Code:

100,第一次激活并成功

1500,操作成功，但不是第一次操作，在数据库中已经有过激活记录

110,用户名或密码错误

1456，iccid错误

1590,参数错误

1591,imsi未知，没有在comfig.properties中记录

1592,功能未实现

-1, japser错误，details中有错误code

}

### b.根据iccd查询imei和状态

(app端  c_sim_findImei)

{

 'iccid': 'xxx',//集成电路id，iccid，该编码号印制在sim卡上

}

 

Response : 

{

imei设备唯一，

status卡状态（激活,停用,库存,更换）,

iccid卡的唯一

}

### c.不登录改变iccid状态（不更新用户数据，流量状态更改用。）

Request:

```json
{
    "name": "c_change_sim",
    "details": {
        "iccid": "xxx", //集成电路id，iccid，该编码号印制在sim卡上
        "type": 2 // 1激活  2停用
    }
}

```



Response : 

{

​	code : 100 ,  // 100成功！其他为失败。

​	details : 'xxx'	 //错误信息提示

}