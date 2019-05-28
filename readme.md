1.搭建父工程
	1.1 创建pom父工程
	1.2 添加相关依赖和插件
		<parent>
	  		<groupId>org.springframework.boot</groupId>
	  		<artifactId>spring-boot-starter-parent</artifactId>
	  		<version>2.0.3.RELEASE</version>
   		</parent>
   
   		<properties>
        	<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
       		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        	<java.version>1.8</java.version>
        	<spring-cloud.version>Finchley.RELEASE</spring-cloud.version>
    	</properties>
   
    	<dependencies>
    		<!-- 使用web -->
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-web</artifactId>
			</dependency>
			<!-- springboot热部署：需要将project -> build automatically打开  -->
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-devtools</artifactId>
			</dependency>
       	 	<dependency>
            	<groupId>org.springframework.boot</groupId>
            	<artifactId>spring-boot-starter-test</artifactId>
            	<scope>test</scope>
        	</dependency>
    	</dependencies>

    	<dependencyManagement>
        	<dependencies>
            	<dependency>
                	<groupId>org.springframework.cloud</groupId>
                	<artifactId>spring-cloud-dependencies</artifactId>
                	<version>${spring-cloud.version}</version>
                	<type>pom</type>
                	<scope>import</scope>
            	</dependency>
        	</dependencies>
  		</dependencyManagement>
  
  		<build>
	  		<plugins>
			<!-- maven插件 -->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-surefire-plugin</artifactId>
				<configuration>
					<testFailureIgnore>true</testFailureIgnore>
					<!-- 跳过单元测试 -->
					<skip>true</skip>
				</configuration>
			</plugin>
			
			<!-- 打包成可执行jar包 -->
			<!-- 
				作用：如果不引用该插件，则使用maven install后打包生成的jar包是不能直接运行的
					使用该插件maven install后生成的jar包是可以直接运行的
			 -->
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
      </plugins>	
      
2.创建子模块 — eureka注册中心
	2.1 创建mavan module
	2.2 添加eureka-server依赖 
    	<dependency>
      		<groupId>org.springframework.cloud</groupId>
      		<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    	</dependency>
    2.3 添加启动类SpringCloudStartRegister
    	2.3.1 添加@SpringBootApplication
    	2.3.2 添加@EnableEurekaServer 表明该项目是eureka-server, 即注册中心
    2.4 添加主配置文件application.yml
    	server:
  			port: 8000  # 该服务端口

		eureka:
  			instance:
    			hostname: localhost  # eureka的实例名称
  			client:
    			registerWithEureka: false  # false表示当前项目不以客户端注册到服务中心(因为该项目本身就是注册中心)
    			fetchRegistry: false  # false表示当前项目不需要从注册中心拉取服务配置(因为该项目本身就是注册中心)
    		serviceUrl:
      			defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/  #  注册中心的访问

		spring:
  			application:
    			name: server-register  # 当前项目的实例名称(很重要)
    2.5 添加日志配置文件logback.xml(内容略), 并在主配置文件application.yml文件中注册
    	logging:
  			file: logback.xml			
    2.6 验证
    	2.6.1 启动当前项目
    	2.6.2 访问: localhost:8000
    	
3.创建子模块 — eureka客户端 — 服务提供者
	3.1 创建maven module
	3.2 添加eureka-client依赖
		<dependency>
     		<groupId>org.springframework.cloud</groupId>
     		<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
   		</dependency>
   	3.3 添加启动类SpringCloudStartProvider
    	3.3.1 添加@SpringBootApplication
    	3.3.2 添加@EnableEurekaClient 表明该项目是eureka-client, 即表示当前项目是eureka客户端
    3.4 添加主配置文件
    	server:
  			port: 8001
		spring:
  			application:
    			name: client-provider
		eureka:
  			client:
    			serviceUrl:
      				defaultZone: http://localhost:8000/eureka/
		logging:
  			file: logback.xml
  	3.5 添加日志配置文件logback.xml(内容略)
  	3.6 添加对外访问Controller接口localhost:8001/hello
  	3.7 验证
  		3.7.1 启动当前项目
  		3.7.2 访问localhost:8000
  		
4.创建子模块 — eureka客户端 — 服务消费者
	4.1 创建maven module
	4.2 添加eureka-client, openfeign依赖
		<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
    4.3 添加启动类SpringCloudStartConsumer
    	4.3.1 添加@SpringBootApplication
    	4.3.2 添加@EnableEurekaClient 表明该项目是eureka-client, 即表示当前项目是eureka客户端
    	4.3.3 添加@EnableFeignClients 开启openfeign
    4.4 添加主配置文件
    	server:
  			port: 8002
		eureka:
  			client:
    			serviceUrl:
      				defaultZone: http://localhost:8000/eureka/
		spring:
  			application:
    			name: client-consumer
		logging:
  			file: logback.xml
  	4.5 添加日志配置文件logback.xml(内容略)
  	4.6 添加对外接口Controller
  		Controller
  			Service (ServiceImpl)
  				Feign
  	4.7 Feign类
  		4.7.1 定义Feign接口
  		4.7.2 添加@FeignClient("服务提供名")
  		4.7.3 添加对应的RequestMapping
  		4.7.4 添加接口及对应的RequestMapping
  	4.8 验证
		4.8.1 启动当前项目
  		4.8.2 访问localhost:8000
  		
  		
==============================================================

5.ribbon负载均衡算法



==============================================================

6.服务雪崩
	6.1 概念
		在微服务架构中，微服务是完成一个单一的业务功能，这样做的好处是可以做到解耦，每个微服务可以独立演进。但是，一个应用可能
		会有多个微服务组成，微服务之间的数据交互通过远程过程调用完成。这就带来一个问题，假设微服务A调用微服务B和微服务C，微服
		务B和微服务C又调用其它的微服务，这就是所谓的“扇出”。如果扇出的链路上某个微服务的调用响应时间过长或者不可用，对微服务A
		的调用就会占用越来越多的系统资源，进而引起系统崩溃，所谓的“雪崩效应”。

7.熔断机制与服务降级
	7.1 概念
		熔断机制是服务雪崩的一种有效解决方案, 当服务消费者所请求的提供者暂时不能提供服务时, 消费者会被阻塞,且长时间占用请求链路。
		为了防止这种情况的发生,当在设定阈值时限达到时仍未获得提供者的服务,则系统将通过断路器直接将此请求链路断开。这种像熔断"保
		险丝"一样的解决方案称为熔断机制。
		服务降级是指当服务的提供者无法正常提供服务时,为了增加用户体验,保证整个系统能够正常运行,由服务消费者端调用本地操作,暂时
		给出用户响应结果的情况。
	7.2 区别
	
	7.3 hystrix
		7.3.1 作用
			在分布式系统中, 许多服务不可避免的会出现调用失败的情况,比如超时,异常等。hystrix用来解决在一个服务出现问题时,不会导致整
			个系统的瘫痪。hystrix提供了熔断,隔离,fallback,cache,监控等功能,能够在一个或多个服务同时出现问题时保证整个系统依
			然可用。hystrix是一种开关机制(默认5秒调用20次时打开该机制),类似于熔断保险丝,当hystrix监控到某个服务发生故障后,其不
			会让该服务的消费者阻塞,或向消费者抛出异常,而是向消费者返回一个符合预期的,可处理的备选响应,这样就避免了服务雪崩的发生。
		7.3.2 基本使用
			第一步 添加hystrix依赖
			<dependency>
            	<groupId>org.springframework.cloud</groupId>
            	<artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
        	</dependency>
			第二步 在处理器方法上添加@HystrixCommond注解,并添加处理方法
			第三步 在启动类上添加@EnableCircuitBreaker注解
		
			基本使用的缺陷: 每个处理器方法上都需要配置@HystrixCommond注解,并添加处理方法,会使得处理器类很臃肿,代码不够简洁
		7.3.3 hystrix整合openfeign
			第一步 在Feign接口所在包下定义降级处理类(自定义FallbackFactory实现FallbackFactory<T>接口), 并加上@Component标签
			第二步 在Feign接口中指定要使用的降级处理类(在@FeignClient标签上添加fallbackFactory属性) 
			第三步 在配置文件中开启Feign对Hystrix的支持(feign.hystrix.enabled=true)
			
			
8.网关zuul
	8.1 简介
		zuul主要提供了对请求的路由和过滤功能。路由功能主要指,将外部请求转发到具体的微服务实例上,是外部访问微服务的统一入口。过滤功能是指,对请求
		的处理过程进行干预,对请求进行校验,服务聚合等处理。
		zuul与eureka进行整合,将zuul自身注册为eureka服务治理下的应用,从eureka server中获取到其他微服务信息,使外部对于微服务的访问都是
		通过zuul进行转发的。
	8.2 基本使用
		1.创建zuul springboot module
		2.添加依赖 eureka-client和zuul
			<!-- eureka -->
    		<dependency>
        		<groupId>org.springframework.cloud</groupId>
        		<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    		</dependency>
    		<!-- zuul -->
     		<dependency>
        		<groupId>org.springframework.cloud</groupId>
        		<artifactId>spring-cloud-starter-netflix-zuul</artifactId>
    		</dependency>
    	3.添加主配置文件
			server:
  				port: 9000
			eureka:
  				client:
    				serviceUrl:
     		 			defaultZone: http://localhost:8000/eureka/
			spring:
  				application:
    				name: zuul
			logging:
  				file: logback.xml
  		4.添加启动类,加上注解
  			@EnableZuulProxy //开启zuul代理
			@SpringBootApplication
		5.在主配置文件中添加zuul代理
			zuul:	
  				routes:
  					consumer:
  						serviceId: client-consumer
  						path: /zuul-consumer/**
	8.3 网关过滤器ZuulFilter
		8.3.1 Filter是Zuul的核心，用来实现对外服务的控制
		8.3.2 类型:
			PRE： 这种过滤器在请求被路由之前调用。我们可利用这种过滤器实现身份验证、在集群中选择请求的微服务、记录调试信息等。
			ROUTING：这种过滤器将请求路由到微服务。这种过滤器用于构建发送给微服务的请求，并使用Apache HttpClient或Netfilx Ribbon请求微服务。
			POST：这种过滤器在路由到微服务以后执行。这种过滤器可用来为响应添加标准的HTTP Header、收集统计信息和指标、将响应从微服务发送给客户端等。
			ERROR：在其他阶段发生错误时执行该过滤器。 除了默认的过滤器类型，Zuul还允许我们创建自定义的过滤器类型。例如，我们可以定制
					一种STATIC类型的过滤器，直接在Zuul中生成响应，而不将请求转发到后端的微服务。
		8.3.3 自定义实现Filter
			第一步:自定义Filter类继承,使用@Component注册
			第二步:重写四个方法
				String filterType(); //定义filter的类型，有pre、route、post、error四种
				int filterOrder(); //定义filter的顺序，数字越小表示顺序越高，越先执行
				boolean shouldFilter(); //表示是否需要执行该filter，true表示执行，false表示不执行
				Object run(); //filter需要执行的具体操作
	8.4 其他配置
		8.4.1 默认配置
			开启zuul网关服务后并将其注册到eureka后, 即使不配置zuul.routes, eureka上所有注册的服务都会被zuul创建映射关系来进行路由。如果某个服务
			不希望通过zuul网关来路由,则可以配置：
			zuul.ignored-services: service-name
		8.4.2 忽略某些请求路径
			zuul.ignored-patterns: /**/hello/**
================================================================================================

9.注册中心Eureka高可用配置
	9.1 原理
		通过Eureka集群解决高可用问题。Eureka Server的高可用实际上就是将自己作为服务向其他服务注册中心注册自己，这样就会形成一组互相注册的服务
		注册中心，进而实现服务清单的互相同步，达到高可用的效果
	9.2 实现
		修改注册中心服务springcloud-start-register，配置多环境
		1.添加application-1.yml文件和application-2.yml文件
		2.修改相关配置：
			2.1 server.port
			2.2 eureka.instance.hostname
			2.3 spring.application.name
			(上述三项只要不一致即可)
			2.4 eureka.client.serviceUrl.defaultZone
			(该配置应使两个服务相互指向)
		3.分别打包成两套jar包, 并分别启动
	9.3 验证
		1.启动springcloud-start-provider和springcloud-start-consumer, 两个服务可以向任意注册中心注册
		2.访问springcloud-start-consumer
		3.关掉一个注册中心,再访问springcloud-start-consumer
		
10.服务提供者springcloud-start-provider高可用及负载均衡
	10.1 原理
		feign与eureka整合, 实现了负载均衡
	10.2 实现
		只要修改两个springcloud-start-provider的启动端口, 使其不一致即可
		
		
===================================================================================================
11. 创建子模块——配置中心服务端 config
	11.1 创建maven module — springcloud-start-config
	11.2 添加erueka-client和config-server依赖
		<dependency>
    		<groupId>org.springframework.cloud</groupId>
    		<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-config-server</artifactId>
		</dependency>		
	11.3 添加启动类SpringCloudStartConfig
    	11.3.1 添加@SpringBootApplication
    	11.3.2 添加@EnableConfigServer 开启配置中心
    11.4 添加主配置文件application.yml
    	server:
  			port: 20080  # 该服务端口
		spring:
  			application:
    			name: config-server
  			cloud:
    			config:
      				server:
        				git:
          					uri: https://github.com/870235784/springcloud-config.git   # 配置git仓库的地址
          					search-paths: config                                 # git仓库地址下的相对地址，可以配置多个，用,分割。
          					username: 					                         # git仓库的账号
          					password: 			                                 # git仓库的密码
		eureka:
  			client:
    			serviceUrl:
      				defaultZone: http://localhost:8001/eureka/
		
    11.5 添加日志配置文件logback.xml(内容略), 并在主配置文件application.yml文件中注册
    	logging:
  			file: logback.xml
  	11.6 如何访问
  		如：远程库的目录结构为：
  			--config
  				--app-dev.properties
  				--app-test.properties
  				--app-prod.properties
  		访问：
  				application-name:配置文件应用名称
  				profile:环境名称
  				label:分支名称
  			方式一:ip:port/{application-name}/{profile}/{label}	  
  					localhost:20080/app/prod/master
  			

12.  创建子模块——配置中心客户端		
    12.1 创建maven module — springcloud-start-config-client
	12.2  添加rueka-client和config依赖
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-starter-config</artifactId>
			</dependency>
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-web</artifactId>
			</dependency>
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-test</artifactId>
				<scope>test</scope>
			</dependency>
			<dependency>
    			<groupId>org.springframework.cloud</groupId>
    			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
			</dependency>
		</dependencies>
	12.3 添加启动类SpringCloudStartConfig
    	12.3.1 添加@SpringBootApplication
    12.4 添加主配置文件application.yml
    	server:
  			port: 20090
		spring:
  			application:
    			name: config-client
		eureka:
  			client:
    			serviceUrl:
      				defaultZone: http://localhost:8001/eureka/
		logging:
  			file: logback.xml
  
	

		
		
		























    	
  
    	
    	
