# springboot-dubbo-shiro
blog后台源代码

基于springboot,dubbo,shiro,jwt的restful风格api

### 项目结构
```
.
├── app-api         api接口
├── app-provider    dubbo服务提供者
├── app-utils       通用工具模块
├── app-web         dubbo服务消费者
├── pom.xml
```

### shiro集成
web被设计成为一个无状态的web应用,服务器端不保存状态(session),并且设置shiro兼容restful形式的url,即对同一个接口的某一个httpMethod可以选择拦截:
例如:
```
/index -GET
/index -POST
```
我们在开发过程中,可能就会遇到这种,对于get请求不进行拦截,但是对于post请求就需要拦截,这时默认的shiro已经无能为力,通过自定义`RestPathMatchingFilter`,
`RestPathMatchingFilterChainResolver`,`RestShiroFilterFactoryBean`对这种请将进行兼容.

具体参考:
> https://github.com/tomsun28/bootshiro

### dubbo集成
按照官方集成demo:[apache/incubator-dubbo-spring-boot-project](https://github.com/apache/incubator-dubbo-spring-boot-project)进行
配置,引入一下依赖到`provider`和`consumer`中(即本项目的`app-provider`和`app-web`)
```xml
<!-- zookeeper -->
<dependency>
    <groupId>org.apache.zookeeper</groupId>
    <artifactId>zookeeper</artifactId>
</dependency>
<dependency>
    <groupId>org.apache.curator</groupId>
    <artifactId>curator-framework</artifactId>
</dependency>
<!-- dubbo -->
<dependency>
    <groupId>com.alibaba.boot</groupId>
    <artifactId>dubbo-spring-boot-starter</artifactId>
    <version>0.2.0</version>
</dependency>
<dependency>
    <groupId>com.alibaba.boot</groupId>
    <artifactId>dubbo-spring-boot-actuator</artifactId>
    <version>0.2.0</version>
</dependency>
```
并配置好application.yml
app-provider
```
dubbo :
  application :
    id: app-provider
    name: app-provider
    version: 1.0.0
  protocol:
    prot: 20880
    name: dubbo
    id: dubbo
    status: server
  registry:
    address: zookeeper://127.0.0.1:2181
    id: app-provider
  scan:
    basePackages: com.ysla.provider.module
```
app-web
```
dubbo :
  application :
    id: app-web
    name : app-web
  protocol :
    prot : 20880
    name  : dubbo
    id: dubbo
  registry :
    address : zookeeper://127.0.0.1:2181
    id: app-web
  scan:
    basePackages: com.ysla.web.controller
```
然后改变app-provider的启动类为非web形式
```java
@SpringBootApplication
public class ProviderApplication{
    public static void main(String[] args) {
        new SpringApplicationBuilder(ProviderApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
```
app-web则还是一个正常的web应用.

其他详细参看官方项目以及本项目源代码.

### mybatis集成
集成mybatis采用注解形式,主要介绍一下`@Results`和`@ResultMap`注解
1. @Results:当数据库字段名与实体类对应的属性名不一致时，可以使用@Results映射来将其对应起来。column为数据库字段名，porperty为实体类属性名，jdbcType为数据库字段数据类型，id为是否为主键
    ```java
    @Results({
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ref_user_id", property="refUserId", jdbcType=JdbcType.VARCHAR),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="nick_name", property="nickName", jdbcType=JdbcType.VARCHAR),
        @Result(column="salt", property="salt", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.TINYINT),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="head_image", property="headImage", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_ip", property="createIp", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_date", property="createDate", jdbcType=JdbcType.BIGINT)
    })
    User selectByPrimaryKey(Integer userId);
    ```
2. @ResultMap:当这段@Results代码需要在多个方法用到时，为了提高代码复用性，我们可以为这个@Results注解设置id，然后使用@ResultMap注解来复用这段代码。
    ```java
    @Results(id = "userInfo",value = {
            @Result(column="ref_user_id", property="refUserId", jdbcType=JdbcType.VARCHAR),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="nick_name", property="nickName", jdbcType=JdbcType.VARCHAR),
            @Result(column="sex", property="sex", jdbcType=JdbcType.TINYINT),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR)
    })
    @Select({"select ref_user_id, username, nick_name from t_user where ref_user_id = #{userId}"})
    JSONObject simpleInfo(String userId);
    
    @ResultMap(value = "userInfo")
    @Select({"select ref_user_id, username, nick_name,sex,email from t_user where ref_user_id = #{userId}"})
    JSONObject simpleInfo2(String userId);
    ```
    这里用一个地方需要注意:***@Results的id所在的那个方法的返回类型一定要和@ResultMap注解方法的返回类型一致***.

### 遇到的问题以及解决办法
1. [记一次踩坑:springboot2.0.2配置fastjson不生效](https://segmentfault.com/a/1190000015975405)

### 重要:已知问题
1. 在app-web模块中的单元测试(项目正常启动则可以正常运行)无法正常运行,dubbo注解@service导致属性空指针,具体说明见我的提问:[springboot+dubbo在写单元测试的时候controller中的@Reference注解属性为null](https://segmentfault.com/q/1010000015989534)