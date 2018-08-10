# springboot-dubbo-shiro
blog后台源代码

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