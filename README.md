# auto-gens-db-docs

把数据库表自动生成world数据库文档

课程视频地址：
  http://www.fei-kuai.com

项目说明

采用spring boot、mybatis框架，搭建的把数据库表自动生成world文档。
支持数据库：mysql

项目结构：
``` 

  ├─java
│  └─io
│      └─feikuai
│          ├─controller  --控制层
│          ├─dao        ---持久层
│          ├─model       --数据模型
│          └─service      ---服务接口
│              └─impl     --服务实现
└─resources  --配置文件
 
```

技术选型：

核心框架：Spring Boot 2.0
视图框架：Spring MVC 5.0
持久层框架：MyBatis 3.3
日志管理：SLF4J 1.7、Log4j


软件需求

JDK1.8
MySQL5.5+
Maven3.0+


本地部署
  修复 application.properties 文件的数据库配置信息 ,请求接口 http://127.0.0.1:8080/gens 就能把配置的数据库表生成word文档
  
