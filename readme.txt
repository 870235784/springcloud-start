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
    2.5 验证
    	2.5.1 启动当前项目
    	2.5.2 访问: localhost:8000
      
    	
