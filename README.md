# registerDemo



## 資料庫
使用的是 Mysql 8.0.16 版本。

資料庫名稱(test1)

帳號為預設(root)

密碼為(kennychao)

####Table schema如下：

```
-- CREATE TABLE "userInfo" -------------------------------------
CREATE TABLE `userInfo` ( 
	`userId` VarChar( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	`username` VarChar( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	`pwd` VarChar( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	`signdate` Timestamp NOT NULL )
CHARACTER SET = utf8
COLLATE = utf8_general_ci
ENGINE = InnoDB;
-- -------------------------------------------------------------
```

```
-- CREATE TABLE "nameUse" --------------------------------------
CREATE TABLE `nameUse` ( 
	`name` VarChar( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	`usetime` Int( 255 ) NOT NULL )
CHARACTER SET = utf8
COLLATE = utf8_general_ci
ENGINE = InnoDB;
-- -------------------------------------------------------------
```


#### Tomcat 的 server.xml請放置這段code:
目前使用是tomcat 7

```
<Context docBase="registerDemo" path="/registerDemo" reloadable="true" source="org.eclipse.jst.jee.server:registerDemo">
      	<Resource auth="Container" driverClassName="com.mysql.jdbc.Driver" maxActive="100" maxIdle="30" name="jdbc/test1DB" password="kennychao" type="javax.sql.DataSource" url="jdbc:mysql://localhost:3306/test1" username="root"/>
</Context>
```



## Owner

- Kenny
