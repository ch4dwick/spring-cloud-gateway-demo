# Sample Spring Cloud Gateway with load balancing

This project demonstrates the use of the new [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway).

As a minor step further I enabled the load balancing feature when this service is connected to a service registry such as Eureka or Consul. So instead of the usual http://remote.service.com, it uses the format lb://service-in-registry.

This project assumes you have a configured and running service registry. It can start up but will keep searching for a running registry.

I have included configurations [application.properties](src/main/resources/application.properties) and [application.yml.backup](src/main/resources/application.yml.backup) for whatever preference you have. I have enabled the properties file by default because it was tricky for developers using this implementation when configuring CORS, which is not so clearly documented in SC Gateway as of this writing.

I have also included two routing implementations depending on your preference. If you prefer to route through code via builder API, There's a commented out code in [RouteConfig](src/main/java/gateway/config/RouteConfig.java) class.
