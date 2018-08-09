# share-app
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