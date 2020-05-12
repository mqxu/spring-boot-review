# spring-boot-config模块
## 1.SpringBoot启动banner配置

 > resources目录下的banner.txt 是自定义的SpringBoot应用启动图标文件

[在线ASCII艺术字生成网站](https://tools.kalvinbg.cn/txt/ascii)
 
## 2.application.properties
> 属性定义时候出现波浪线："Cannot resolve configuration property"
> 可以使用SpringBoot 的 configuration processor 模块来解决问题
```$xslt
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```
然后在配置类上添加 @ConfigurationProperties 注解即可

## 3.Tomcat的相关配置


## 4.HTTPS的配置
[参考这篇](https://www.cnblogs.com/chenpi/p/9696371.html)

## 5.在配置文件中自定义配置属性
- 定义
- 读取



