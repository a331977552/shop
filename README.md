<h1>前言:该项目为前后端分离项目,主要利用React,SpringBoot作为前后端技术框架</h1>


<p >在线购物商城 基于 SpringBoot,Spring Cloud 开发,微服务架构,目前还在开发中. </p>  
<p >前端使用了typescript react 作为主要开发语言和框架</p>

<p >online shopping website，developed with SpringBoot in microservice architecture.
  Typescript and React are mainly used to develop frentend</p>
  


| 模块(服务) | 作用|
| ---------------- | ------------- |
| scheme.sql| 数据表结构|
| user-service| 用户服务|
| product-service| 商品服务| 
| api-gateway| api gateway|
| admin-client| 后台管理界面|
| common|所有模块都会引用,包含了很多公用工具|
| test-utility|所有模块都会引用,封装了一些test工具|



目前可以展示的部分页面(part of demonstrable pages): 


front admin page code struture(under adming-client folder): 

![alt text](https://github.com/a331977552/shop/blob/main/docs/front_admin_client_code_structure.png?raw=true)



![alt text](https://github.com/a331977552/shop/blob/main/docs/product_list.png?raw=true)
<br/>
![alt text](https://github.com/a331977552/shop/blob/main/docs/product_add1.png?raw=true)
<br/>
![alt text](https://github.com/a331977552/shop/blob/main/docs/productadd.jpg?raw=true)
<br/>
![alt text](https://github.com/a331977552/shop/blob/main/docs/brand.jpg?raw=true)
<br/>
![alt text](https://github.com/a331977552/shop/blob/main/docs/brand.jpg?raw=true)
<br/>
![alt text](https://github.com/a331977552/shop/blob/main/docs/category.jpg?raw=true)
<br/>
![alt text](https://github.com/a331977552/shop/blob/main/docs/order.png?raw=true)
<br/>
![alt text](https://github.com/a331977552/shop/blob/main/docs/product_attri.png?raw=true)
<br/>
![alt text](https://github.com/a331977552/shop/blob/main/docs/product_specs.png?raw=true)


sql uml diagram:
<br/>
![alt text](https://github.com/a331977552/shop/blob/main/docs/db_uml/shop.png?raw=true)





| module  | functionality |detail|
| ------------- | ------------- |----------|
| scheme.sql|table's meta data|can be imported into database,  generate tables
| user-service| user service |user login, user info acquire, jwt token generation
| product-service| product service  | the CRUD of products,categories,products' specifcations, attributes 
| api-gateway| api gateway |all api calls will go through this services in which it will collect all sorts of info

<h3 >still under development</h3>

instead of using `ribbon`,  `zuul`, `hystrix`,   `resilience4j` `spring load balancer` and `spring cloud gateway` are applied in this project since it's the latest recommendation from spring.io
