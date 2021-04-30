# issues found & skills learnt

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

    
        