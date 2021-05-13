
# issues found & skills learnt

---
### *issue.1* `synchronized` and `@Transactional` co-exist
`@Transactional` will add transaction begin and commit or rollback before and after the method,
so adding `synchronized`and `@Transactional` doesn't mean the whole block is synchronized，
refer to the method  `register` in user-service `org.shop.service.UserServiceImpl` class.  example:

    void proxy(obj,method){
        transaction.begin();
        synchronized{
            obj.invoke(method);
        }
        transaction.commit();
    }
### *issue.2* new thread running inside a method annotated with  `@Transactional` & `@Rollback`
`@Transactional` & `Rollback` doesn't work for the new thread created inside the annotated method. example:
   
    @Transactional
    @Rollback
    void testNewThread(){
        new Thread(()->{
            mapper.insert(obj);
        }).start();
    }
### 

### *issue.3* dyingThread.join(). the calling thread such as main thread will wait for this  dyingThread to die.
source code:

    while(thread.isAlive()){ //dying thread is alive
        object.wait();// calling thread wait();
    }

example:

    Thread dying = new Thread(()->{
        sout("dying begin");
        Thread.sleep(3000);
        sout("died")
    });
    dying.start();
    dying.join(); //blocking until the dying thread is finished
    sout("main\calling thread finished");

---

# redis  
1.  import `jedis` and `spring-boot-starter-data-redis`
2.  by default, there will be two default redisTemplates: `redisTemplate` and `StringRedisTemplate` configured in `RedisAutoConfiguration` class 
3.  config a new redisTemplate, it needs to be renamed: `@Bean(name = "redisTemplate")` and new serializers need to be configured. 
        
---
#Shiro
    
* `permission`:
    * Resource Level  A user can edit customer(any) records or open(any) doors. 
    * Instance Level  A user can edit a certain type of customer(i.e : IBM) or open a specific type of door such as kitchen door.
    * Attribute Level A user can edit Address field on the IBM Customer Record.
    
* `roles`: 
    * Implicit Roles : admin can do whatever he wants, but no idea what permissions admin have. 
    * Explicit Roles(better): admin can do whatever he wants, because admin have all the permission under admin role (*)
* `users`:
    * Users can have multiple roles, roles have different permission. therefore, users are granted with different permissions.

* `Principals`:
    Username, email, phone number anything that is `unique` 
* Credentials: `passwords`, biometric data such as fingerprints and retina scans, and X.509 certificates