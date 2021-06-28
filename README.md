<br />
<p align="center">在线购物商城 基于 SpringBoot,Spring Cloud 开发,微服务架构,目前还在开发中. </p>  
<p align="center">前端使用了typescript react 作为主要开发语言和框架</p>

<p align="center">online shopping website，developed with SpringBoot in microservice architecture.
  Typescript and React are mainly used to develop frentend</p>
  
<h3 align="center">still under development</h3>

| 模块(module)  | 作用(functionality) |细节 (detail)|
| ------------- | ------------- |----------|
| scheme.sql| 数据表的元数据,(table's meta data)|可以导入数据库中生成对应的表(can be imported into database,  generate tables)
| user-service| 用户服务(user service)  |用户登录,用户的信息查询,jwt token的生成(user login, user info acquire, jwt token generation)
| product-service| 商品服务(product service)  | 商品,种类,种类属性,规格的增删改查(the CRUD of products,categories,products' specifcations, attributes )
| api-gateway| api gateway |所有的api都会经过这里,信息收集,认证,(all api calls will go through this services in which it will collect all sorts of info)


目前可以展示的部分页面(part of demonstrable pages): 

![alt text](https://github.com/a331977552/shop/blob/main/docs/brand.jpg?raw=true)
![alt text](https://github.com/a331977552/shop/blob/main/docs/category.jpg?raw=true)
![alt text](https://github.com/a331977552/shop/blob/main/docs/productadd.jpg?raw=true)



instead of using `ribbon`,  `zuul`, `hystrix`,   `resilience4j` `spring load balancer` and `spring cloud gateway` are applied in this project since it's the latest recommendation from spring.io
