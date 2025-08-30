##Use spring boot actuator

####Table of Contents
1. [Maven dependency](####Maven dependency)
2. [Actuator API](####Actuator API)
3. [List of Endpoints](####List of Endpoints)
4. [Configurations](####Configurations)
5. [Spring Boot Default Metrics](####Spring Boot Default Metrics)
6. [Auto-configured HealthIndicators](####Auto-configured HealthIndicators)
7. [Spring Boot default metrics](####Spring Boot default metrics)
8. [Configurations in Shahkar 3.0](####Configurations in Shahkar 3.0)

####Maven dependency
برای پرژه شما به کتابخانه های زیر نیاز دارید 
```xml

<dependencies>

<!--    <dependency>-->
<!--        <groupId>org.testng</groupId>-->
<!--        <artifactId>testng</artifactId>-->
<!--        <version>7.11.0</version>-->
<!--    </dependency>-->
<!--    <dependency>-->
<!--        <groupId>io.rest-assured</groupId>-->
<!--        <artifactId>rest-assured</artifactId>-->
<!--    </dependency>-->
<!--    <dependency>-->
<!--        <groupId>io.qameta.allure</groupId>-->
<!--        <artifactId>allure-testng</artifactId>-->
<!--        <version>2.29.1</version>-->
<!--    </dependency>-->

    <dependency>
        <groupId>io.qameta.allure</groupId>
        <artifactId>allure-testng</artifactId>
        <version>2.24.0</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/io.qameta.allure/allure-rest-assured -->
    <dependency>
        <groupId>io.qameta.allure</groupId>
        <artifactId>allure-rest-assured</artifactId>
        <version>2.24.0</version>
    </dependency>
</dependencies>
```


####2. command to run project
باید تنظیمات مربوط به REST Assured را در پروژه انجام دهید تا بتوانید تست‌های خود را با استفاده از Allura اجرا کنید. این تنظیمات معمولاً در کلاس‌های تست شما انجام می‌شود.
* mvn clean test
* mvn allure:serve


####2. تنظیمات برای REST Assured و Allura
باید تنظیمات مربوط به REST Assured را در پروژه انجام دهید تا بتوانید تست‌های خود را با استفاده از Allura اجرا کنید. این تنظیمات معمولاً در کلاس‌های تست شما انجام می‌شود.
* your_api/actuator
* your_api/actuator/health`
* 

####Actuator API
For using actuator after adding dependency and configure the actuator we can access the actuator by this pattern
 * your_api/actuator
 * your_api/actuator/health`


####List of Endpoints
There many endpoints that we can monitor them: These endpoits are more general and recommended

 | Endpoint name | Description |
 | ---------- | -----------------------------------|
 | auditevents | Exposes audit events information for the current application. Requires an AuditEventRepository bean. |
 | beans | Displays a complete list of all the Spring beans in your application.|
 | caches | Exposes available caches.|
 | conditions | Shows the conditions that were evaluated on configuration and auto-configuration classes and the reasons why they did or did not match.|
 | configprops | Displays a collated list of all @ConfigurationProperties. |
 | env | Exposes properties from Spring’s ConfigurableEnvironment.|
 | flyway | Shows any Flyway database migrations that have been applied. Requires one or more Flyway beans.|
 | health | Shows application health information. | 
 | httptrace | Displays HTTP trace information (by default, the last 100 HTTP request-response exchanges). Requires an HttpTraceRepository bean.
 | info | Displays arbitrary application info. |
 | integrationgraph | Shows the Spring Integration graph. Requires a dependency on spring-integration-core. |
 | loggers | Shows and modifies the configuration of loggers in the application. |
 | liquibase | Shows any Liquibase database migrations that have been applied. Requires one or more Liquibase beans. |
 | metrics | Shows ‘metrics’ information for the current application. |
 | mappings | Displays a collated list of all @RequestMapping paths. |
 | scheduledtasks | Displays the scheduled tasks in your application. |
 | sessions | Allows retrieval and deletion of user sessions from a Spring Session-backed session store. Requires a Servlet-based web application using Spring Session. |
 | shutdown | Lets the application be gracefully shutdown. Disabled by default. |
 | startup | Shows the startup steps data collected by the ApplicationStartup. Requires the SpringApplication to be configured with a BufferingApplicationStartup. |
 | threaddump | Performs a thread dump. |
	

####Configurations
We can also configure by adding these configurations to application.yaml
  
* Endpoint enabling

```yaml
	management:
	  endpoint:
	    shutdown:
	      enabled: true
```

* Didsable default endpoint

```yaml
	management:
	  endpoints:
	    enabled-by-default: false
	  endpoint:
	    info:
	      enabled: true
```


 * Switch to a Different Server Port
```yaml
	server.port: 9000
	management.server.port: 9001
	management.server.address: 127.0.0.1
```



####Auto-configured HealthIndicators

	You can also enable/disable selected indicators by configuring management.health.key.enabled, with the key listed in the table below.
	
 | metric | Indicator | objective |
 |--------|-----------|-----------|
 | httptracedatasource | DataSourceHealthIndicator | Checks that a connection to DataSource can be obtained. |
 | diskspace | DiskSpaceHealthIndicator | Checks for low disk space. |
 | elasticsearch | ElasticsearchRestHealthIndicator | Checks that an Elasticsearch cluster is up. |
 | jms | JmsHealthIndicator | Checks that a JMS broker is up. |
 | ldap | LdapHealthIndicator| Checks that an LDAP server is up. |
 | mongo | MongoHealthIndicator | Checks that a Mongo database is up.|
 | ping | PingHealthIndicator | Always responds with UP. |
 | redis | RedisHealthIndicator | Checks that a Redis server is up. |
 | rabbit | RabbitHealthIndicator | Checks that a Rabbit server is up.|
	


 * Additional health indicators
 
 | metric | Indicator | objective |
 |--------|-----------|-----------|
 | livenessstate | LivenessStateHealthIndicator | Exposes the "Liveness" application availability state. |
 | readinessstate | ReadinessStateHealthIndicator | Exposes the "Readiness" application availability state. |
	

 * Customizing the Management Endpoint Paths
 
 ```yaml
	management:
	  endpoints:
	    web:
	      base-path: "/manage"
```
	     
	     
	     
 * Remap health check indicator
 
 ```yaml
	management:
	  endpoints:
	    web:
	      base-path: "/"
	      path-mapping:
		health: "healthcheck"
```


* The location of the Elastic server to use can be provided using the following property:

```yaml
	management:
	  metrics:
	    export:
	      elastic:
		host: "https://elastic.example.com:8086"
```

####Spring Boot default metrics
 * Spring Boot registers the following core metrics when applicable:
 
	* JVM metrics, report utilization of:
	    Various memory and buffer pools
	    Statistics related to garbage collection	
	    Threads utilization
	    Number of classes loaded/unloaded

	* CPU metrics
	* File descriptor metrics
	* Kafka consumer and producer metrics
	* Log4j2 metrics: record the number of events logged to Log4j2 at each level
	* Logback metrics: record the number of events logged to Logback at each level
	* Uptime metrics: report a gauge for uptime and a fixed gauge representing the application’s absolute start time
	* Tomcat metrics (server.tomcat.mbeanregistry.enabled must be set to true for all Tomcat metrics to be registered)
	* Spring Integration metrics

 * you can enable instrumentation by adding @Timed to a request-handling method

 ```java
@RestController
@Timed 
public class MyController {

    @GetMapping("/api/people")
    @Timed(extraTags = { "region", "us-east-1" }) 
    @Timed(value = "all.people", longTask = true) 
    public List<Person> listPeople() { ... }

}
```

 * You can configure hibernate statistics metric expose

```yaml
Hibernate Metrics
	spring:
	  jpa:
	    properties:
	      "[hibernate.generate_statistics]": true
```

####Configurations in Shahkar 3.0
```properties
management.auditevents.enabled=true
management.endpoint.beans.cache.time-to-live=5s
management.endpoint.beans.enabled=true
management.endpoint.caches.enabled=true
management.endpoint.camelroutecontroller.enabled=true
management.endpoint.camelroutes.enabled=true
management.endpoint.env.enabled=true
management.endpoint.health.enabled=true
management.endpoint.health.probes.enabled=true
management.endpoint.health.show-components=always
management.endpoint.heapdump.enabled=true
management.endpoint.httptrace.enabled=true
management.endpoint.info.enabled=true
management.endpoint.integrationgraph.enabled=true
management.endpoint.conditions.enabled=true
management.endpoint.logfile.enabled=true
management.endpoint.loggers.enabled=true
management.endpoint.scheduledtasks.enabled=true
management.endpoint.sessions.enabled=true
management.endpoint.mappings.enabled=true
management.endpoint.metrics.enabled=true
management.endpoint.threaddump.enabled=true
management.health.db.enabled=true
management.health.defaults.enabled=true
management.health.diskspace.enabled=true
management.info.camel.enabled=true
management.info.git.enabled=true
management.metrics.export.stackdriver.enabled=true
management.health.elasticsearch.enabled=true
management.health.diskspace.threshold=
management.health.mongo.enabled=true
management.health.ping.enabled=true
management.health.redis.enabled= true
management.health.rabbit.enabled=true
management.health.livenessstate.enabled=true
management.health.readinessstate.enabled=true
management.info.env.enabled=true
management.metrics.export.elastic.enabled=true
management.metrics.export.elastic.auto-create-index=true
management.metrics.export.elastic.host=""
management.metrics.export.elastic.user-name=""
management.metrics.export.elastic.password=""
management.metrics.export.elastic.read-timeout=1s
management.metrics.export.elastic.connect-timeout=5s
management.endpoint.shutdown.enabled= true
management.endpoint.health.show-details=always
```



