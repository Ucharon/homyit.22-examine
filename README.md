# 后端最后一次培训-JavaWeb的简单实战



## 三层架构与MVC模式

### MVC概念:

**MVC**的全名是Model View Controller，是模型(Model)－视图(view)－控制器(controller)的缩写，是一种设计模式。它是**用一种**业务逻辑、数据与界面显示分离的**方法来组织代码**，将众多的业务逻辑聚集到一个部件里面，在需要改进和个性化定制界面及用户交互的同时，不需要重新编写业务逻辑，达到减少编码的时间，提高代码复用性。

**使用的MVC的目的：**它将这些对象、显示、控制分离以提高软件的的灵活性和复用性，MVC结构可以使程序具有对象化的特征，也更容易维护。

**Model、View、Controller概述：**

**模型层（Model）**：指从现实世界中抽象出来的对象模型，是应用逻辑的反应；它封装了数据和对数据的操作，是实际进行数据处理的地方（模型层与数据库才有交互）

**视图层（View）**：是应用和用户之间的接口，它负责将应用显示给用户 和 显示模型的状态。

**控制器（Controller**）:控制器负责视图和模型之间的交互，控制对用户输入的响应、响应方式和流程；它主要负责两方面的动作，一是把用户的请求分发到相应的模型，二是吧模型的改变及时地反映到视图上。

在网页当中，

V即View视图是指用户看到并与之交互的界面。比如由html元素组成的网页界面，或者软件的客户端界面。MVC的好处之一在于它能为应用程序处理很多不同的视图。在视图中其实没有真正的处理发生，它只是作为一种输出数据并允许用户操纵的方式。

M即model模型是指模型表示业务规则。在MVC的三个部件中，模型拥有最多的处理任务。被模型返回的数据是中立的，模型与数据格式无关，这样一个模型能为多个视图提供数据，由于应用于模型的代码只需写一次就可以被多个视图重用，所以减少了代码的重复性。

C即controller控制器是指控制器接受用户的输入并调用模型和视图去完成用户的需求，控制器本身不输出任何东西和做任何处理。它只是接收请求并决定调用哪个模型构件去处理请求，然后再确定用哪个视图来显示返回的数据。



![img](https://pic1.zhimg.com/80/v2-0887746ae6096d489192ea4e062acd14_720w.webp)



用户首先在界面中进行人机交互，然后请求发送到控制器，控制器根据请求类型和请求的指令发送到相应的模型，模型可以与数据库进行交互，进行增删改查操作，完成之后，根据业务的逻辑选择相应的视图进行显示，此时用户获得此次交互的反馈信息，用户可以进行下一步交互，如此循环。

需要注意的是：MVC三层结构与软件的三层结构是有区别的。MVC是一种设计模式，三层结构是软件结构，用MVC这种设计模式可以实现三层软件结构。在完整三层软件结构中 表现层包括了MVC中的表现层和控制层。而MVC中的模型层其实包括了三层软件结构的业务逻辑层、数据访问层、业务实体类（model）和共用类。软件三层结构的UI（Web）包含了MVC中的视图层（V）和控制层（C）。



### MVC举例：

最典型的MVC就是jsp+servlet+javabean模式。

JavaBean作为模型，既可以作为数据模型来封装业务数据，又可以作为业务逻辑模型来包含应用的业务操作。其中，数据模型用来存储或传递业务数据，而业务逻辑模型接收到控制器传过来的模型更新请求后，执行特定的业务逻辑处理，然后返回相应的执行结果。

JSP作为视图层，负责提供页面为用户展示数据，提供相应的表单（Form）来用于用户的请求，并在适当的时候（点击按钮）向控制器发出请求来请求模型进行更新。

Serlvet作为控制器，用来接收用户提交的请求，然后获取请求中的数据，将之转换为业务模型需要的数据模型，然后调用业务模型相应的业务方法进行更新，同时根据业务执行结果来选择要返回的视图。



![img](https://pic4.zhimg.com/80/v2-b811af190747a0dec11f37194b221f33_720w.webp)



MVC设计模式相对于模式1（虚线表示模式1，不是MVC，即JSP+JavaBean），把模式1 的表现层中的页面与表现逻辑（流程控制）分开。视图层只负责页面的显示，而数据的获取、调用业务逻辑和页面的选择均由控制层完成。在MVC模式中，视图层用JSP、HTML、CSS和JavaScript技术来实现；



对了，入门后可以看看这篇文章，彻底搞懂MVC：

[深入理解MVC](https://zhuanlan.zhihu.com/p/35680070)

我们之后会在学习Spring的时候再次接触到的，相信我！



**相信大家看这些概念是不是觉得一头雾水？没关系，接下来我们直接上实战**



## 项目实战！

### 1. maven项目构建

新建项目/模块，选择**Maven构建工具**，选择从**原型创建（Create from archetype）**，选择

![截屏2023-04-14 15.48.09](https://typora-1312272916.cos.ap-shanghai.myqcloud.com//img%E6%88%AA%E5%B1%8F2023-04-14%2015.48.09.png)

也就是创建个webapp的原型，一个简单的java项目的模板



### 2. 数据库表的创建以及JavaBean的编写

这次我们的项目主要是实现用户的登录、注册的功能，因此只需要一个简单的**用户表**就OK

建表SQL语句如下：

```sql
/*
 Navicat Premium Data Transfer

 Source Server         : my-server
 Source Server Type    : MySQL
 Source Server Version : 80024 (8.0.24)
 Source Host           : localhost:3306
 Source Schema         : teach

 Target Server Type    : MySQL
 Target Server Version : 80024 (8.0.24)
 File Encoding         : 65001

 Date: 14/04/2023 15:57:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID（全局唯一）',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
  `password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
  `age` int DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` (`id`, `user_name`, `password`, `age`, `email`) VALUES (1, 'zhangsan', '81dc9bdb52d04dc20036dbd8313ed055', 24, '1302299@163.com');
INSERT INTO `tb_user` (`id`, `user_name`, `password`, `age`, `email`) VALUES (3, 'lisi', '81dc9bdb52d04dc20036dbd8313ed055', NULL, NULL);
INSERT INTO `tb_user` (`id`, `user_name`, `password`, `age`, `email`) VALUES (4, 'wangwu', '81dc9bdb52d04dc20036dbd8313ed055', NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

```

**字段如下：**

![截屏2023-04-14 15.58.26](https://typora-1312272916.cos.ap-shanghai.myqcloud.com//img%E6%88%AA%E5%B1%8F2023-04-14%2015.58.26.png)



**接下来我工具表结构写一个简单的JavaBean：**

```java
package cn.homyit.entity;

import lombok.Data;

/**
 * @TableName tb_user
 */
@Data
public class User {

    /**
     * 用户id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 班级
     */
    private Integer age;
    /**
     * 邮箱
     */
    private String email;

}
```

我们这里直接用`Lombok`的`Data`注解，自动帮我们装配上javabean需要的方法，**如getter、setter、toString等**

这一步，完结，下一步我们要让我们的项目连接到数据库，让其可以操作数据库



### 3. Mybatis框架的使用与Druid数据库连接池的配置

这次项目我选择了当前比较流行的**Mybatis**作为持久层框架，顺便选择了Druid作为我们的数据库连接池

那么这里就有个问题了，为什么要使用数据库连接池？？

#### 为什么使用数据库连接池

**[转载知乎](https://www.zhihu.com/question/349816338/answer/854647922)**

​	应用程序和数据库建立连接的过程是这样的：

1. 首先通过TCP协议的三次握手和[数据库服务器](https://www.zhihu.com/search?q=数据库服务器&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"answer"%2C"sourceId"%3A"854647922"})建立连接，然后发送[数据库用户](https://www.zhihu.com/search?q=数据库用户&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"answer"%2C"sourceId"%3A"854647922"})账号密码，等待数据库验证用户身份。

2. 完成用户[身份验证](https://www.zhihu.com/search?q=身份验证&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"answer"%2C"sourceId"%3A"854647922"})后，系统才可以提交SQL语句到数据库执行。

3. 好了这个时候假设我们不使用[数据库连接池](https://www.zhihu.com/search?q=数据库连接池&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"answer"%2C"sourceId"%3A"854647922"})，那么完成一次SQL查询后，我们还要把连接关闭，关闭连接就需要和[数据库](https://www.zhihu.com/search?q=数据库&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"answer"%2C"sourceId"%3A"854647922"})通信告诉它我们要断开连接了然后再TCP四次挥手最后完成关闭。

​	这个过程中每一次发起SQL查询所经历的TCP建立连接，数据库验证用户身份，数据库用户登出，TCP断开连接消耗的等待时间都是可以避免的，这明显是一种浪费。打个比方，你去网吧去玩游戏，每次去到呢先插网线，然后开机登录游戏，玩了一会儿要去上厕所，你就退出游戏，然后关机拔网线。去完厕所回来就又重新插网线开机登游戏。

​	有没有觉得上面例子中的行为很扯蛋，所以每次SQL[查询](https://www.zhihu.com/search?q=查询&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"answer"%2C"sourceId"%3A"854647922"})都创建链接，查询完后又关闭连接这个做法本身就很扯蛋。合理的做法就应该是系统启动的时候就创建数据库连接，然后需要使用SQL查询的时候，就从系统拿出数据库连接对象并提交查询，查询完了就把连接对象还给系统。系统在整个程序运行结束的时候再把数据库连接关闭。考虑到一般[数据库应用](https://www.zhihu.com/search?q=数据库应用&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"answer"%2C"sourceId"%3A"854647922"})都是Web多用户并发应用，那么只有一个数据库连接对象肯定不够用，所以系统启动的时候就应该多创建几个数据库连接对象供多个线程使用，这一批数据库连接对象集合在一起就被称之为数据库连接池。

​	数据库连接池就是典型的用空间换时间的思想，系统启动预先创建多个数据库连接对象虽然会占用一定的内存空间，但是可以省去后面每次SQL查询时创建连接和关闭连接消耗的时间。

选择Druid数据库连接池是阿里巴巴维护的一个开源项目，速度较快，是一个比较好的数据库连接池的实现



#### 数据库配置文件编写

这边我们用一个`properties`文件写数据库的配置，方便我们维护

```properties
jdbc.driverClass=com.mysql.cj.jdbc.Driver
jdbc.jdbcUrl=jdbc:mysql://101.34.210.50:3306/teach?useSSL=false&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT
jdbc.user=teach
jdbc.password=x4bfS3W6xpArnXTf
```

这边用了远程云服务器的数据库

#### Druid数据库连接池的配置类

这里是固定的书写方式，用来获取连接池，可以配置具体信息，如最大连接数等，这边可以自行根据要求书写

```java
package cn.homyit.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

public class DruidDataSourceFactory implements DataSourceFactory {

    private Properties properties;

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public DataSource getDataSource() {
        DruidDataSource dds = new DruidDataSource();
        dds.setUrl(this.properties.getProperty("url"));
        dds.setUsername(this.properties.getProperty("username"));
        dds.setPassword(this.properties.getProperty("password"));

        try {
            dds.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dds;
    }
}
```

#### 创建Mybatis-config.xml文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">


<configuration>

    <!-- 外部 properties 配置文件  -->
    <properties resource="db.properties"/>
    <!--驼峰命名自动映射-->
    <settings>
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>
    <!--配置数据源-->
    <environments default="development-mysql">
        <environment id="development-mysql">
            <transactionManager type="JDBC"/>
            <dataSource type="cn.homyit.utils.DruidDataSourceFactory">
                <property name="url" value="${jdbc.jdbcUrl}"/>
                <property name="username" value="${jdbc.user}"/>
                <property name="password" value="${jdbc.password}"/>
            </dataSource>
        </environment>
    </environments>
</configuration>
```

这里外部配置文件就是上面写的`properties`

这里的数据源就写上我们刚才写的`DruidDataSourceFactory`类



### 4. 测试数据库的连接，与UserMapper的编写

真正开发的时候我们需要常写单元测试，这是一个非常好的习惯，现在我们就可以测试数据库连接是否正常

```java
@SneakyThrows
@Test
public void testConnect() {
    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession sqlSession = sqlSessionFactory.openSession();
    System.out.println("sqlSession = " + sqlSession);
}
```

![截屏2023-04-14 16.17.36](https://typora-1312272916.cos.ap-shanghai.myqcloud.com//img%E6%88%AA%E5%B1%8F2023-04-14%2016.17.36.png)



成功连接

接下来我们来写**UserMapper**

UserMapper是个接口：

```java
import java.util.List;

public interface UserMapper {
    List<User> listUser();
}
```

这里我们顺便写了个方法用来测试，就是列出所有的用户

我们通过`MybatisX`这个插件可以自动生成一个该Mapper对应的xml文件：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="cn.homyit.mapper.UserMapper">

    <select id="listUser" resultType="cn.homyit.entity.User">
        select *
        from teach.tb_user
    </select>
</mapper>
```

请将该mapper放到resoures文件夹里，顺便在`mybatis-config.xml`里写上该xml的位置

```xml
<mappers>
    <!--加载sql映射文件-->
    <mapper resource="mapper/UserMapper.xml"/>
</mappers>
```



**接下来我们来测试这个mapper能不能正常使用**

```java
@SneakyThrows
@Test
public void listUser() {
    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession sqlSession = sqlSessionFactory.openSession();
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    List<User> users = userMapper.listUser();
    System.out.println("users = " + users);
}
```

![截屏2023-04-14 16.23.33](https://typora-1312272916.cos.ap-shanghai.myqcloud.com//img%E6%88%AA%E5%B1%8F2023-04-14%2016.23.33.png)

测试成功，既然我们已经完成持久层（mapper）的构建，接下来我们可以开始写Servlet了



### 5. 手撸一个简单的登录的Servlet

新建一个Servlet框架

```java
@WebServlet(value = "/user/login", name = "用户登录接口")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
```

请求路径是`user/login`，这个不用多说

相信你们写过简单的登录接口，但是你们写的接口应该是前后端不分离的交互方式，前端通过简单的表单提交给后端接收

真正的前后端分离模式，大多使用JSON真正数据格式进行前后端交互

#### 简单介绍下JSON

- 什么是JSON

​	JSON：**JavaScript Object Notation** 【JavaScript 对象表示法】

​	**JSON 是存储和交换文本信息的语法。类似 XML。**

​	**JSON采用完全独立于任何程序语言的文本格式，使JSON成为理想的数据交换语言S**

- 为什么需要JSON

    提到JSON，我们就应该和XML来进行对比。XML也是一种存储和交换文本信息的手段。那么JSON好在哪里呢？？

    **JSON 比 XML 更小、更快，更易解析**。

    - javaScript原生支持JSON，解析速度会很快

    - XML解析成DOM对象的时候，浏览器【IE和fireFox】会有差异

    - 使用JSON会更简单

入门看一下这篇文章

 **[JSON快速入门](https://www.zhihu.com/search?type=content&q=json)**



既然我们接受到了JSON字符串，我们自然要把它转换成Java对象，对该对象进行具体的业务逻辑操作

#### 从请求数据获取JSON字符串，并将其转换为Java对象

这里我们得获取到用户的登录信息，那只需传用户名和密码即可，对应的JSON字符串如下：

```json
{
    "userName": "zhangsan",
    "password": "1234"
}
```

接下来我们得从请求体里读取到该字符串，具体操作如下：

```java
//获取登录信息
BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
String line = null;
StringBuilder sb = new StringBuilder();
while ((line = br.readLine()) != null) {
    sb.append(line);
}
br.close();
System.out.println("sb = " + sb);
```

##### 使用Apifox进行请求

Apifox是一个中文版的api请求根据，测试接口挺方便。也可以使用postman

我们先部署并启动tomcat项目

然后我们在地址栏输入请求地址：`http://localhost:8080/teach/user/login`，并将请求方式设置成post请求

接下来我们在body-json一栏填入刚才需要测试的json字符串并点击发送

![截屏2023-04-14 16.49.15](https://typora-1312272916.cos.ap-shanghai.myqcloud.com//img%E6%88%AA%E5%B1%8F2023-04-14%2016.49.15.png)

可以发现请求结果为空，当然我们没有设置返回结果，那肯定为空

我们主要看下控制台打印了什么：

![截屏2023-04-14 16.45.06](https://typora-1312272916.cos.ap-shanghai.myqcloud.com//img%E6%88%AA%E5%B1%8F2023-04-14%2016.45.06.png)

很欣慰，打印成功了

这里我们确实接受到了json格式的字符串，那我们怎么把它转换为java对象呢？



##### Hutool工具包的运用

Hutool是一个小而全的Java工具类库，通过静态方法封装，降低相关API的学习成本，提高工作效率，使Java拥有函数式语言般的优雅，让Java语言也可以“甜甜的”。

Hutool中的工具方法来自每个用户的精雕细琢，它涵盖了Java开发底层代码中的方方面面，它既是大型项目开发中解决小问题的利器，也是小型项目中的效率担当；

Hutool是项目中“util”包友好的替代，它节省了开发人员对项目中公用类和公用工具方法的封装时间，使开发专注于业务，同时可以最大限度的避免封装不完善带来的bug。

具体可以翻阅文档：

**[hutool文档](https://hutool.cn/docs/)**

我们找到JSON工具包这一栏，这里有很多比较好用的JSON工具，其中就包括我们现在需要的json字符串转化为javabean的工具类



![截屏2023-04-14 16.56.05](https://typora-1312272916.cos.ap-shanghai.myqcloud.com//img%E6%88%AA%E5%B1%8F2023-04-14%2016.56.05.png)

##### json转换为javabean

很简单，我们只需要指定json字符串与其需要转换成java对象的class对象（反射的内容，不懂的可以看一下反射的入门）

```java
User user = JSONUtil.toBean(sb.toString(), User.class);
System.out.println("user = " + user);
```

![截屏2023-04-14 16.59.24](https://typora-1312272916.cos.ap-shanghai.myqcloud.com//img%E6%88%AA%E5%B1%8F2023-04-14%2016.59.24.png)

我们发现已经完全封装过来了！

我们还发现了，其他很多内容是不需要接受的，好像有一点不合理，那怎么办呢

##### dto的引入

**DTO（Data Transfer Object）：数据传输对象**

这个概念来源于J2EE的设计模式，原来的目的是为了EJB的[分布式应用](https://www.zhihu.com/search?q=分布式应用&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"article"%2C"sourceId"%3A"444490701"})提供粗粒度的数据实体，以减少分布式调用的次数，从而提高分布式调用的性能和降低网络负载，但在这里，更符合泛指用于[展示层](https://www.zhihu.com/search?q=展示层&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"article"%2C"sourceId"%3A"444490701"})与服务层之间的数据传输对象。

是不是看不懂，没关系，我也是（（（

这里我们用户登录只需要传用户名和密码，这样我们就简单的定义一个dto专门用来接受用户登录的参数：

```java
@Data
public class LoginUserDto {

    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
}
```

这就是dto的简单运用，哈哈不理解也没关系，以后会经常碰见的。

那么，接下来我们只需要调用数据库用户名和密码查询数据库就可以啦



### 6. Service层的引入

我们前面讲了MVC，知道了应用分层，既然Controller是我们的Servlet，是第一层，我们要考虑到代码复用，不能把所有的业务逻辑写在Controller层

因此我们引入了**Service层：业务层**，重业务，而我们的Controller主要的则是接受和处理http的请求，最好不要涉及到具体的业务逻辑，这就是MVC模式的简单运用

具体可以看这里的链接，里面讲了分层的意义：

[java为什么要分为service层，dao层，controller层？ - 知乎 (zhihu.com)](https://www.zhihu.com/question/431911268/answer/2135642849)

我们新建一个service软件包，并创建一个UserService接口

```java
public interface UserService {
    
}
```

并在该接口的同目录下创建个软件包impl，里面存放service具体的实现，并创建UserServiceImpl实现接口：

```java
public class UserServiceImpl implements UserService {

}

```

这样我们业务层的框架就搭好了！

那我们现在就可以写具体的业务逻辑啦



### 7. 实现登录的业务逻辑

接下来，我们就可以写登录的业务了，现在我们在sevlet里面已经有了LoginUserDto对象了

那我们肯定得工具里面的用户名密码查询数据库是否有该用户存在，密码是否正确，如果成功就返回true，否则是false

那我们定义一下登录的service方法

```java
boolean login(LoginUserDto loginUserDto);
```

记得在实现里面重写该方法

我们缕一下，缕一下，这个登录的逻辑是什么样的呢？

1. 根据用户名查询用户是否存在，需要调用userMapper，如果不存在那就是没有该用户，直接返回false
2. 走到这那表示用户存在，则需要较验密码，当然，密码一定要加密存在数据库（一定！！！不要学CSDN）
3. 密码对那就返回true，否则false

缕好了，那就直接上代码！！！

先写根据用户名查询数据库的方法，写UserMapper里：

```java
@Select("select * from teach.`tb_user` where user_name = #{userName}")
User getByUserName(String userName);
```

这里比较简单，直接用注解写入sql语句

接下来写业务逻辑：

```java
try {
    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession sqlSession = sqlSessionFactory.openSession();
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    User user = userMapper.getByUserName(loginUserDto.getUserName());

    if (user == null || !user.getPassword().equals(DigestUtil.md5Hex(loginUserDto.getPassword()))) {
        return false;
    }
    return true;
} finally {
    sqlSession.close();
}
```

这里使用hutool的DigestUtil工具类对密码进行md5加密，并和数据库里的密码进行比对

我们发现，这里的部分代码很是啰嗦，其实真正业务是业务代码是我们查询数据库和校验用户名密码，其他的，无论哪个业务都相同

所以说我们需要自己封装一个工具类来获取mapper

#### 封装mybatis工具类

我们刚开始mybatis入门的时候都是使用SqlSessionFactory来获取sqlsession，但是每次调用都得手动关闭，交给我们来手动操作既麻烦又容易失误，如果忘记关则会导致资源占用过多！

那怎么才能实现自动关呢，这里介绍下SqlSessionManager，其获取的session以及mapper就无需关心链接关闭的事情了！

关于原理，想了解的可以查看：

[MyBatis 自动关闭 session - 贺墨于 - 博客园 (cnblogs.com)](https://www.cnblogs.com/hemou/p/14700091.html)

基于jdk的动态代理实现

```java
public class MybatisUtil {

    /**
     * 这里使用sqlSessionManager，妈妈再也不用当心我忘记关数据库啦
     */
    private static SqlSessionManager sessionManager = null;

    static {
        try {
            InputStream inputStream = MybatisUtil.class.getClassLoader()
                    .getResourceAsStream("mybatis-config.xml");
            sessionManager = SqlSessionManager.newInstance(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("the Configuration of Mybatis is not exist!");
        }
    }

    /**
     * 获取 Mapper
     *
     * @param <T>
     * @param clazz
     * @return
     */
    public static <T> T getMapper(Class<T> clazz) {
        return sessionManager.getMapper(clazz);
    }

    /**
     * 获取 SqlSessionManager
     *
     * @return
     */
    public static SqlSessionManager getSessionManager() {
        return sessionManager;
    }

}
```

接下来，我们需要mapper的时候只需要一行代码就可以了！

我们的业务代码也变得很简洁：

```java
UserMapper userMapper = MybatisUtil.getMapper(UserMapper.class);
User user = userMapper.getByUserName(loginUserDto.getUserName());

if (user == null || !user.getPassword().equals(DigestUtil.md5Hex(loginUserDto.getPassword()))) {
    return false;
}
return true;
```

### 8. 返回登录结果

业务层已经写好了，我们sevlet就会收到来自业务层登录失败还是成功的信息，我们只需要返回登录成功or失败给用户

那代码应该很简单：

```java
response.setCharacterEncoding(EncodingConstant.UTF_8);
response.setContentType("text/html;charset=UTF-8");
if (userService.login(loginUserDto)) {
    response.getWriter().println("登录成功");
    return;
}
response.getWriter().println("登录失败");
```

接下来我们看看返回结果：

登录成功

![截屏2023-04-14 20.09.27](https://typora-1312272916.cos.ap-shanghai.myqcloud.com//img%E6%88%AA%E5%B1%8F2023-04-14%2020.09.27.png)

登录失败

![截屏2023-04-14 20.09.49](https://typora-1312272916.cos.ap-shanghai.myqcloud.com//img%E6%88%AA%E5%B1%8F2023-04-14%2020.09.49.png)

当然只是这样返回是肯定不行的！因为我们是前后端分离，这样返回前端会很头大的（doge），所以我们得换成返回json数据

既然json是键值对的数据那我们用hashmap不就行了么！

一般来说需要返回给后端描述信息（desc）、数据（可以为空）、状态码（code）、成功标志（success）就行了

马上上手

```java
response.setCharacterEncoding(EncodingConstant.UTF_8);
response.setContentType("text/html;charset=UTF-8");
response.setContentType("application/json");
HashMap<String, String> map = new HashMap<>();
if (userService.login(loginUserDto) != null) {
    map.put("code", "200");
    map.put("desc", "登录成功");
    map.put("success", "true");
    response.getWriter().println(JSONUtil.parse(map));
    return;
}
map.put("code", "600");
map.put("desc", "登录失败");
map.put("success", "false");
response.getWriter().println(JSONUtil.parse(map));
```

这下很标准了！

![截屏2023-04-14 20.17.36](https://typora-1312272916.cos.ap-shanghai.myqcloud.com//img%E6%88%AA%E5%B1%8F2023-04-14%2020.17.36.png)

但是，接下来我们发现这样还是好复杂，每个都要重复写这些破代码，能不能封装成一个通用的结果呢，因为每一个返回结果基本上格式一样！

### 9. 自定义通用相应体

既然每次返回都有code、desc、data、success，那我把这些都封装到一个实体类里面不就好了！

```java
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result {

    private Integer code;
    private String desc;
    private Object data;
    private Boolean success;

    public Result(Integer code, String desc, Boolean success) {
        this.code = code;
        this.desc = desc;
        this.success = success;
    }
    
}    
```

这下我们返回就更简单一点了！

```java
response.setCharacterEncoding(EncodingConstant.UTF_8);
response.setContentType("text/html;charset=UTF-8");
response.setContentType("application/json");
if (userService.login(loginUserDto) != null) {
    response.getWriter().println(JSONUtil.parse(new Result(200, "登录成功", true)));
    return;
}
response.getWriter().println(JSONUtil.parse(new Result(600, "登录失败", false)));
```

结果和上面一模一样，但是代码更简洁！！

![截屏2023-04-14 20.26.49](https://typora-1312272916.cos.ap-shanghai.myqcloud.com//img%E6%88%AA%E5%B1%8F2023-04-14%2020.26.49.png)

现在我们的登录逻辑是完成了，我们看看整体的代码

```java
//获取登录信息
BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
String line = null;
StringBuilder sb = new StringBuilder();
while ((line = br.readLine()) != null) {
    sb.append(line);
}
br.close();
LoginUserDto loginUserDto = JSONUtil.toBean(sb.toString(), LoginUserDto.class);
response.setCharacterEncoding(EncodingConstant.UTF_8);
response.setContentType("text/html;charset=UTF-8");
response.setContentType("application/json");
if (userService.login(loginUserDto) != null) {
    response.getWriter().println(JSONUtil.parse(new Result(200, "登录成功", true)));
    return;
}
response.getWriter().println(JSONUtil.parse(new Result(600, "登录失败", false)));
```

其实已经够简单了，但是我们依然可以发现可能重复的代码！

#### 使用工具类解决servlet的代码复用

- 请求json字符串转换为javabean

我们可以发现每次从请求中获取json字符串的步骤都是相同的，唯一不同的地方就是json转成javabean时，就是`LoginUserDto.class`不一样

因此我们只需要将这部分提取出来，把`LoginUserDto.class`这部分作为形参就完美解决复用问题了！！

我们创建个**WebUtils类**

```java
/**
 * 将request中的json字符串转换为javabean
 * @param request 请求
 * @param type 需要转换成对应的类型
 * @param <T> 泛型，表示javabean对应的类
 * @return javabean对象
 * @throws IOException
 */
public static <T> T requestJsonToBean(HttpServletRequest request, Class<T> type) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
    String line = null;
    StringBuilder sb = new StringBuilder();
    while ((line = br.readLine()) != null) {
        sb.append(line);
    }
    br.close();
    return JSONUtil.toBean(sb.toString(), type);
}
```

其实只要学过一点点泛型和反射的话，这个方法就很简单了，这方面你们得自己复习或者预习一下子

- 返回为json字符串

当然，我们返回也有很多是可复用的，就result对象不相同！

我们再封装一个！

```java
/**
 * 返回为json字符串
 * @param response 相应
 * @param result none
 */
@SneakyThrows
public static void returnResponse(HttpServletResponse response, Result result) {
    response.setCharacterEncoding(EncodingConstant.UTF_8);
    response.setContentType("text/html;charset=UTF-8");
    response.setContentType("application/json");
    response.getWriter().println(JSONUtil.parse(result));
}
```

这样的话，我们的代码就简洁多了！

```java
//获取登录信息
LoginUserDto loginUserDto = WebUtils.requestJsonToBean(request, LoginUserDto.class);

if (userService.login(loginUserDto) != null) {
    WebUtils.returnResponse(response, new Result(200, "登录成功", true));
    return;
}
WebUtils.returnResponse(response, new Result(600, "登录失败", false));
```

这样太简洁，太优雅了！！



#### 枚举类的引入

如果如果哪天你不想登录成功后返回“登录成功”的字样，而是其他字样，你就要找到这里的代码进行修改，万一你代码量非常庞大呢，万一其他地方也有”登录成功“呢，那你也得定位过去，那有什么方法能统一管理返回状态和信息呢，这里就有枚举的一席之地了！

这里有个视频能很好的入门枚举类：

**[既然都有常量了，为啥还要用枚举](https://www.bilibili.com/video/BV1A34y1v7aL/?spm_id_from=333.999.0.0)**

`ResultCodeEnums`的创建

```java
@Getter
@AllArgsConstructor
@ToString
public enum ResultCodeEnums {

    SUCCESS(200, "成功");

    /**
     * 用户相关
     */
    LOGIN_ERROR(600, "用户名或密码错误"),
    LOGIN_STATUS_EXPIRED(601, "登录状态过期"),
    DUPLICATE_USER_NAME(602, "用户名重复")
    ;

    private Integer code;
    private String desc;
}
```

是不是看起来很简单，但就是看不懂呢！？

我们来拆解一下

我们用了lombok注解，搞了个全参构造，第一个传code，第二个传desc

自然你你可以发现上面的`SUCCESS`其实就是执行了这个构造方法，code为200，desc为成功

我们打印下看看

```java
System.out.println("ResultCodeEnums.SUCCESS = " + ResultCodeEnums.SUCCESS);
```

![截屏2023-04-14 20.56.41](https://typora-1312272916.cos.ap-shanghai.myqcloud.com//img%E6%88%AA%E5%B1%8F2023-04-14%2020.56.41.png)



可以看见，我们只需指定下SUCCESS就会自动执行构造方法，并传入相关参数！

所以说很方便，方便管理各种状态码和返回信息

接下来我们使用这个ResultCodeEnums来润色下Result类

#### 使用枚举类优化Result类

我们只需要添加几个静态方法，就可以满足使用了

```java
public static Result success() {
    return new Result(ResultCodeEnums.SUCCESS.getCode(),
            ResultCodeEnums.SUCCESS.getDesc(),
            true);
}

public static Result success(Object data) {
    return new Result(ResultCodeEnums.SUCCESS.getCode(),
            ResultCodeEnums.SUCCESS.getDesc(), data,
            true);
}

public static Result error(ResultCodeEnums resultCodeEnums) {
    return new Result(resultCodeEnums.getCode(),
            resultCodeEnums.getDesc(),
            false);
}
```

相信我，后面你们写项目会有很多地方用到枚举的！

现在我们登录接口就变成如下

```java
//获取登录信息
LoginUserDto loginUserDto = WebUtils.requestJsonToBean(request, LoginUserDto.class);

if (userService.login(loginUserDto) != null) {
    WebUtils.returnResponse(response, Result.success());
    return;
}
WebUtils.returnResponse(response, Result.error(ResultCodeEnum.LOGIN_ERROR));
```



### 10. 注册接口的实现

Servlet写法，注册也只需账号密码即可

```java
LoginUserDto loginUserDto = WebUtils.requestJsonToBean(request, LoginUserDto.class);
Result result = userService.register(loginUserDto);
WebUtils.returnResponse(response, result);
```

业务逻辑的代码可能比较麻烦，容我捋一捋捋一捋

1. 首先查询是否有相同用户名，如果有，则返回失败
2. 否则，插入该用户，表示成功注册
3. 再将密码加密存入数据库！

你看是不是很简单，直接上代码

```java
//首先查询是否有相同用户名
UserMapper userMapper = MybatisUtil.getMapper(UserMapper.class);
User user = userMapper.getByUserName(loginUserDto.getUserName());
//如果有，则返回失败
if (user != null) {
    return Result.error(ResultCodeEnums.DUPLICATE_USER_NAME);
}
//否则，插入该用户，表示成功注册
//先将密码加密
loginUserDto.setPassword(DigestUtil.md5Hex(loginUserDto.getPassword()));
userMapper.saveUser(loginUserDto.getUserName(), loginUserDto.getPassword());

return Result.success();
```

其实，mapper使用的方法也差不多，只需另外加一个插入的方法



### 11. 登录过滤器的实现

我们上面所写的登录以及注册接口，肯定能对未登录的用户开放，但如果是其他的接口呢，比如说查看个人信息？

当然，当用户登录后才能查看个人信息，如果用户未登录，我们肯定不能让他访问这个接口，那该如何实现呢？

我们都知道HTTP是无状态的，就是记不住是谁发的请求

这时候我们就需要记住每个用户，把每个人都区分开，所以想出的办法就是给大家发一个会话标识(session id), 说白了就是一个随机的字串，每个人收到的都不一样， 每次大家向我发起HTTP请求的时候，把这个字符串给一并捎过来， 这样我就能区分开谁是谁了

#### 引入session

session 从字面上讲，就是会话。这个就类似于你和一个人交谈，你怎么知道当前和你交谈的是张三而不是李四呢？对方肯定有某种特征（长相等）表明他就是张三。

session 也是类似的道理，服务器要知道当前发请求给自己的是谁。为了做这种区分，服务器就要给每个客户端分配不同的“身份标识”，然后客户端每次向服务器发请求的时候，都带上这个“身份标识”，服务器就知道这个请求来自于谁了。至于客户端怎么保存这个“身份标识”，可以有很多种方式，对于浏览器客户端，大家都默认采用 cookie 的方式。

服务器使用session把用户的信息临时保存在了服务器上，用户离开网站后session会被销毁。这种用户信息存储方式相对cookie来说更安全，可是session有一个**缺陷：**如果web服务器做了负载均衡，那么下一个操作请求到了另一台服务器的时候session会丢失。

当然还有其他种类的认证方式如token啊，**后面用的多的都是token**

当然这是后面需要学的内容

可以看看这篇文章搞懂

[彻底理解cookie，session，token - 知乎 (zhihu.com)](https://zhuanlan.zhihu.com/p/63061864)



#### 用户登录后记住该用户

我们需要在用户用户名和密码都正确校验之后进行session的存储，用来记录状态

```java
//服务端记录登录状态
//将登录成功后的user对象，存储到session中
HttpSession session = request.getSession();
session.setAttribute("user", user);
```

这样用户成功登录后，浏览器会存有sessionId，这里的sessionId就在Cookie里，之后请求都会携带该cookie

我们可以通过Apifox里看

这里我请求了登录接口，成功后会立刻生成了sessionid存入了Cookie

![截屏2023-04-14 22.48.37](https://typora-1312272916.cos.ap-shanghai.myqcloud.com//img%E6%88%AA%E5%B1%8F2023-04-14%2022.48.37.png)

我们既然有了sessionid，那这个sessionid怎么使用呢，这里就要自定义一个登录拦截器了

#### 登录拦截器实现

创建一个LoginFilter类，拦截所有的请求，我们先实现下Fileter接口，重点重写doFilter方法，这里就是写过滤的逻辑

我们知道登录注册无论有无登录都无需拦截，因此当我们监测到用户访问的是登录或者注册时，我们需要直接放行

```java
HttpServletRequest req = (HttpServletRequest) request;
//记录下需要不需要拦截的接口
Set<String> noFilter = CollectionUtil.newHashSet("/user/login", "/user/register");
//获取当前资源的访问路径
final String url = req.getRequestURL().toString();
//判断下url是否包含不要拦截的路径
for (String s : noFilter) {
    //如果是。直接放行
    if (url.contains(s)) {
        chain.doFilter(request, response);
        return;
    }
}
```

走到下面则表示需要登录才能访问的接口

我们则需要检查用户是否有sessionId，判断该session是否有user

有的话直接放行，反之直接拦截，提醒用户登录

```java
//1. 判断session中是否有user
HttpSession session = req.getSession();
Object user = session.getAttribute("user");
//2. 判断user是否为null
if (user != null) {
    //登录过了，直接放行
    chain.doFilter(request, response);
} else {
    //没有登录，提醒用户登录状态过期
    WebUtils.returnResponse((HttpServletResponse) response,
            Result.error(ResultCodeEnums.LOGIN_STATUS_EXPIRED));
}
```



这里我们来个测试接口



![截屏2023-04-14 22.56.20](https://typora-1312272916.cos.ap-shanghai.myqcloud.com//img%E6%88%AA%E5%B1%8F2023-04-14%2022.56.20.png)

使用Apifox请求该接口

有cookie的情况：

![截屏2023-04-14 22.56.58](https://typora-1312272916.cos.ap-shanghai.myqcloud.com//img%E6%88%AA%E5%B1%8F2023-04-14%2022.56.58.png)



无session的情况：（直接删除cookie就行了）

![截屏2023-04-14 22.57.26](https://typora-1312272916.cos.ap-shanghai.myqcloud.com//img%E6%88%AA%E5%B1%8F2023-04-14%2022.57.26.png)

本次实战的内容到此就结束了，接下来我会给你们布置考核的内容



















