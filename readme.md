# 文件依赖

### com.util.emil

+ **EmailUtil.java**  
	*javax.mail-api-1.5.4.jar*  
	
	<code><pre>   
		<!-- https://mvnrepository.com/artifact/javax.mail/javax.mail-api -->
		<dependency>
		   <groupId>javax.mail</groupId>
		   <artifactId>javax.mail-api</artifactId>
		   <version>1.5.4</version>
		</dependency>   
	</pre></code>
---

### com.util.http
	
+ **OkHttpUtil.java**  
	*okhttp-4.8.0.jar*  
	
	<!-- https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp -->
	<dependency>
	   <groupId>com.squareup.okhttp3</groupId>
	   <artifactId>okhttp</artifactId>
	   <version>4.8.0</version>
	</dependency>
---



### com.util.jdbc

+ **JdbcUtil.java**  
	*mysql-connector-java-5.0.7.jar*  
	
	<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
	<dependency>
	   <groupId>mysql</groupId>
	   <artifactId>mysql-connector-java</artifactId>
	   <version>5.0.7</version>
	</dependency>
---
+ **DruidUtil.java**  
	*druid-1.1.11.jar*  
	
	<!-- https://mvnrepository.com/artifact/com.alibaba/druid -->
	<dependency>
	   <groupId>com.alibaba</groupId>
	   <artifactId>druid</artifactId>
	   <version>1.1.11</version>
	</dependency>
---

+ **DBUtil.java**  
	*MapperUtil.java*  
	*JdbcUtil.java*
	
---

+ **QueryRunnerDbUtil.java**  
	*commons-dbutils-1.7.jar*  
	*mchange-commons-java-0.2.12.jar*  
	*JdbcUtil.java*  
	
	<!-- https://mvnrepository.com/artifact/commons-dbutils/commons-dbutils -->
	<dependency>
	    <groupId>commons-dbutils</groupId>
	    <artifactId>commons-dbutils</artifactId>
	    <version>1.7</version>
	</dependency>
	
	<!-- https://mvnrepository.com/artifact/com.mchange/mchange-commons-java -->
	<dependency>
	    <groupId>com.mchange</groupId>
	    <artifactId>mchange-commons-java</artifactId>
	    <version>0.2.12</version>
	</dependency>
---

### com.util.json

+ **JsonUtil.java**  
	*jackson-core-2.10.0.jar*  
	*jackson-annotations-2.10.0.jar*  
	*jackson-databind-2.10.0.jar*  
	
	<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core -->
	<dependency>
	    <groupId>com.fasterxml.jackson.core</groupId>
	    <artifactId>jackson-core</artifactId>
	    <version>2.10.0</version>
	</dependency>
	
	<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-annotations -->
	<dependency>
	    <groupId>com.fasterxml.jackson.core</groupId>
	    <artifactId>jackson-annotations</artifactId>
	    <version>2.10.0</version>
	</dependency>
	
	<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
	<dependency>
	    <groupId>com.fasterxml.jackson.core</groupId>
	    <artifactId>jackson-databind</artifactId>
	    <version>2.10.0</version>
	</dependency>
---

### com.util.servlet

+ **CookieUtil.java**  
	*servlet-api-2.5.jar*

---

+ **RequestUtil.java**  
	*servlet-api-2.5.jar*  
	*JavaTypeUtil.java*

---

+ **ResponseUtil.java**  
	*servlet-api-2.5.jar*  
	*ContentType.java*  
	
	<!-- https://mvnrepository.com/artifact/javax.servlet/servlet-api -->
	<dependency>
	    <groupId>javax.servlet</groupId>
	    <artifactId>servlet-api</artifactId>
	    <version>2.5</version>
	    <scope>provided</scope>
	</dependency>
	