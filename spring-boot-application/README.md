# spring-boot-application模块
> 在Spring Boot的入口类中，我们通常是通过调用SpringApplication的run方法来启动Spring Boot项目
> 因此，了解SpringApplication的运行机制是很有必要的
>

## 1、SpringApplication 启动类
- 禁用默认启动
- 构造 SpringApplication 类对象
- 自定义设置
- 调用该对象的 run() 方法启动

## 2、SpringApplication 实例的初始化
- 推断应用的类型：创建的是 REACTIVE应用、SERVLET应用、NONE 三种中的某一种
- 使用 SpringFactoriesLoader查找并加载 classpath下 META-INF/spring.factories文件中所有可用的 ApplicationContextInitializer
- 使用 SpringFactoriesLoader查找并加载 classpath下 META-INF/spring.factories文件中的所有可用的 ApplicationListener
- 推断并设置 main方法的定义类

## 3、SpringApplication 的run()方法
- 通过 SpringFactoriesLoader 加载 META-INF/spring.factories 文件，获取并创建 SpringApplicationRunListener 对象
- 然后由 SpringApplicationRunListener 来发出 starting 消息
- 创建参数，并配置当前 SpringBoot 应用将要使用的 Environment
- 完成之后，依然由 SpringApplicationRunListener 来发出 environmentPrepared 消息
- 创建 ApplicationContext
- 初始化 ApplicationContext，并设置 Environment，加载相关配置等
- 由 SpringApplicationRunListener 来发出 contextPrepared 消息，告知SpringBoot 应用使用的 ApplicationContext 已准备OK
- 将各种 beans 装载入 ApplicationContext，继续由 SpringApplicationRunListener 来发出 contextLoaded 消息，告知 SpringBoot 应用使用的 ApplicationContext 已装填OK
- refresh ApplicationContext，完成IoC容器可用的最后一步
- 由 SpringApplicationRunListener 来发出 started 消息
- 完成最终的程序的启动
- 由 SpringApplicationRunListener 来发出 running 消息，告知程序已运行起来了