<h1>前言:该项目为前后端分离项目,主要利用React,SpringBoot作为前后端技术框架</h1>


<p >在线购物商城 基于 SpringBoot,Spring Cloud 开发,微服务架构,目前还在开发中. </p>  
<p >前端使用了typescript react 作为主要开发语言和框架</p>

<p >online shopping website，developed with SpringBoot in microservice architecture.
  Typescript and React are mainly used to develop frentend</p>
  
<h3 >still under development</h3>

| 模块 | 作用|细节|
| ---------------- | ------------- |----------|
| scheme.sql| 数据表的元数据|可以导入数据库中生成对应的表
| user-service| 用户服务|用户登录,用户的信息查询,jwt token的生成
| product-service| 商品服务 | 商品,种类,种类属性,规格的增删改查
| api-gateway| api gateway |所有的api都会经过这里,信息收集,认证

目前可以展示的部分页面(part of demonstrable pages): 

![alt text](https://github.com/a331977552/shop/blob/main/docs/brand.jpg?raw=true)
![alt text](https://github.com/a331977552/shop/blob/main/docs/category.jpg?raw=true)
![alt text](https://github.com/a331977552/shop/blob/main/docs/productadd.jpg?raw=true)



| module  | functionality |detail|
| ------------- | ------------- |----------|
| scheme.sql|table's meta data|can be imported into database,  generate tables
| user-service| user service |user login, user info acquire, jwt token generation
| product-service| product service  | the CRUD of products,categories,products' specifcations, attributes 
| api-gateway| api gateway |all api calls will go through this services in which it will collect all sorts of info



instead of using `ribbon`,  `zuul`, `hystrix`,   `resilience4j` `spring load balancer` and `spring cloud gateway` are applied in this project since it's the latest recommendation from spring.io
