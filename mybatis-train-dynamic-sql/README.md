
### elements

* <mapper>
* <ResultMap>-<id>-<result>
* <insert>
* <update> 
* <select>
* <delete>
* <sql>
* <paramType>
* <paramMap>
* <resultType>
* <resultMap>

### 集合

 * <associate>
 * <collections>
 
### Dynamic SQL

 * <if>
 * <choose> <when> <otherwise>
 * <trim> <where> <set>
 * <foreach>
 * <bind> 使用的不是很多
 
 ### 多数据库支持
 
 ### 可插拔？

### 日志显示(log4j.xml)

能够看到执行多SQL:

1. configuration设置log类型。
2. mybatis-config.xml设置 settings里面设置。

### Questions

`＃` 与 `$`

```
｀#｀会进行字符串转义 但是有的时候是不需要字符串转义的 例如:ORDER BY
字符串替换

默认情况下,使用 #{} 格式的语法会导致 MyBatis 创建 PreparedStatement 参数并安全地设置参数（就像使用 ? 一样）。这样做更安全，更迅速，通常也是首选做法，不过有时你就是想直接在 SQL 语句中插入一个不转义的字符串。比如，像 ORDER BY，你可以这样来使用：

ORDER BY ${columnName}

这里 MyBatis 不会修改或转义字符串。

NOTE 用这种方式接受用户的输入，并将其用于语句中的参数是不安全的，会导致潜在的 SQL 注入攻击，因此要么不允许用户输入这些字段，要么自行转义并检验。 
```

