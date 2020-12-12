# 文件依赖

## 1、com.util.emil

### 1.1、EmailUtil.java
*javax.mail-api-1.5.4.jar*<br>
<pre>
<code>
&lt;!-- https://mvnrepository.com/artifact/javax.mail/javax.mail-api --&gt;
&lt;dependency&gt;
	&lt;groupId&gt;javax.mail&lt;/groupId&gt;
	&lt;artifactId&gt;javax.mail-api&lt;/artifactId&gt;
	&lt;version&gt;1.5.4&lt;/version&gt;
&lt;/dependency&gt;
</code>
</pre>


## 2、com.util.http

### 2.1、OkHttpUtil.java
*okhttp-4.8.0.jar*
*okio-2.7.0.jar*
<pre>
<code>
&lt;!-- https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp --&gt;
&lt;dependency&gt;
	&lt;groupId&gt;com.squareup.okhttp3&lt;/groupId&gt;
	&lt;artifactId&gt;okhttp&lt;/artifactId&gt;
	&lt;version&gt;4.8.0&lt;/version&gt;
&lt;/dependency&gt;

&lt;!-- https://mvnrepository.com/artifact/com.squareup.okio/okio --&gt;
&lt;dependency&gt;
	&lt;groupId&gt;com.squareup.okio&lt;/groupId&gt;
	&lt;artifactId&gt;okio&lt;/artifactId&gt;
	&lt;version&gt;2.7.0&lt;/version&gt;
&lt;/dependency&gt;
</code>
</pre>


## 3、com.util.jdbc

### 3.1、JdbcUtil.java
*mysql-connector-java-5.0.7.jar*<br>
<pre>
<code>
&lt;!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java --&gt;
&lt;dependency&gt;
	&lt;groupId&gt;mysql&lt;/groupId&gt;
	&lt;artifactId&gt;mysql-connector-java&lt;/artifactId&gt;
	&lt;version&gt;5.0.7&lt;/version&gt;
&lt;/dependency&gt;
</code>
</pre>


### 3.2、DruidUtil.java
*druid-1.1.11.jar*<br>
<pre>
<code>
&lt;!-- https://mvnrepository.com/artifact/com.alibaba/druid --&gt;
&lt;dependency&gt;
	&lt;groupId&gt;com.alibaba&lt;/groupId&gt;
	&lt;artifactId&gt;druid&lt;/artifactId&gt;
	&lt;version&gt;1.1.11&lt;/version&gt;
&lt;/dependency&gt;
</code>
</pre>


### 3.3、DBUtil.java
*MapperUtil.java*<br>
*JdbcUtil.java*


### 3.4、QueryRunnerDbUtil.java
*commons-dbutils-1.7.jar*<br>
*mchange-commons-java-0.2.12.jar*<br>
*JdbcUtil.java*<br>
<pre>
<code>
&lt;!-- https://mvnrepository.com/artifact/commons-dbutils/commons-dbutils --&gt;
&lt;dependency&gt;
	&lt;groupId&gt;commons-dbutils&lt;/groupId&gt;
	&lt;artifactId&gt;commons-dbutils&lt;/artifactId&gt;
	&lt;version&gt;1.7&lt;/version&gt;
&lt;/dependency&gt;

&lt;!-- https://mvnrepository.com/artifact/com.mchange/mchange-commons-java --&gt;
&lt;dependency&gt;
	&lt;groupId&gt;com.mchange&lt;/groupId&gt;
	&lt;artifactId&gt;mchange-commons-java&lt;/artifactId&gt;
	&lt;version&gt;0.2.12&lt;/version&gt;
&lt;/dependency&gt;
</code>
</pre>


## 4、com.util.json

### 4.1、JsonUtil.java
*jackson-core-2.10.0.jar*<br>
*jackson-annotations-2.10.0.jar*<br>
*jackson-databind-2.10.0.jar*<br>
<pre>
<code>
&lt;!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core --&gt;
&lt;dependency&gt;
	&lt;groupId&gt;com.fasterxml.jackson.core&lt;/groupId&gt;
	&lt;artifactId&gt;jackson-core&lt;/artifactId&gt;
	&lt;version&gt;2.10.0&lt;/version&gt;
&lt;/dependency&gt;

&lt;!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-annotations --&gt;
&lt;dependency&gt;
	&lt;groupId&gt;com.fasterxml.jackson.core&lt;/groupId&gt;
	&lt;artifactId&gt;jackson-annotations&lt;/artifactId&gt;
	&lt;version&gt;2.10.0&lt;/version&gt;
&lt;/dependency&gt;

&lt;!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind --&gt;
&lt;dependency&gt;
	&lt;groupId&gt;com.fasterxml.jackson.core&lt;/groupId&gt;
	&lt;artifactId&gt;jackson-databind&lt;/artifactId&gt;
	&lt;version&gt;2.10.0&lt;/version&gt;
&lt;/dependency&gt;
</code>
</pre>


## 5、com.util.servlet

### 5.1、CookieUtil.java
*servlet-api-2.5.jar*


### 5.2、RequestUtil.java
*servlet-api-2.5.jar*<br>
*JavaTypeUtil.java*


### 5.3、ResponseUtil.java
*servlet-api-2.5.jar*<br>
*ContentType.java*<br>
<pre>
<code>
&lt;!-- https://mvnrepository.com/artifact/javax.servlet/servlet-api --&gt;
&lt;dependency&gt;
	&lt;groupId&gt;javax.servlet&lt;/groupId&gt;
	&lt;artifactId&gt;servlet-api&lt;/artifactId&gt;
	&lt;version&gt;2.5&lt;/version&gt;
	&lt;scope&gt;provided&lt;/scope&gt;
&lt;/dependency&gt;
</code>
</pre>


## 6、com.util.qrcode

### 6.1、QrcodeUtil
*zxing-core-3.3.1.jar*<br>
*zxing-javase-3.3.1.jar*<br>
<pre>
<code>
&lt;!-- https://mvnrepository.com/artifact/com.google.zxing/core --&gt;
&lt;dependency&gt;
	&lt;groupId&gt;com.google.zxing&lt;/groupId&gt;
	&lt;artifactId&gt;core&lt;/artifactId&gt;
	&lt;version&gt;3.3.1&lt;/version&gt;
&lt;/dependency&gt;

&lt;!-- https://mvnrepository.com/artifact/com.google.zxing/javase --&gt;
&lt;dependency&gt;
	&lt;groupId&gt;com.google.zxing&lt;/groupId&gt;
	&lt;artifactId&gt;javase&lt;/artifactId&gt;
	&lt;version&gt;3.3.1&lt;/version&gt;
&lt;/dependency&gt;
</code>
</pre>