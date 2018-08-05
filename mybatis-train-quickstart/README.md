
## 使用流程

* 1. 使用DB.xml 或 Configuration配置文件，导入配置文件。使用SqlFactoryBuilder生成一个SqlFactroy.
* 2. 使用SqlFactory生成一个SqlSession.
* 3. 使用session.mapper()方法 进行映射，获取映射的Mapper对象.
* 4. 使用Mapper对象，使用Mapper对象完成业务逻辑.(复杂对象时 有时可能需要获取多个Mapper对象.)
* 5. 使用完毕，关闭SqlSession对象.并且销毁mapper对象与session对象.

注: SqlSession对象为非线程安全的，所以不能在多线程内使用同一个Session对象。但是Mybatis-Spring内的SqlSession对象为线程安全的，所以可以多线程调用，并且保证单例模式。(Mapper对象也为同理。)

## 生命周期

主要的类为:

* SqlSessionFactoryBuilder(SqlSesionFactory创造器)
* SqlSessionFactroy(SqlSession的工厂类)
* SqlSession(SqlSession类)
* Mapper(映射类)


## 作用域（Scope）和生命周期

理解我们目前已经讨论过的不同作用域和生命周期类是至关重要的，因为错误的使用会导致非常严重的并发问题。

提示 对象生命周期和依赖注入框架

依赖注入框架可以创建线程安全的、基于事务的 SqlSession 和映射器（mapper）并将它们直接注入到你的 bean 中，因此可以直接忽略它们的生命周期。如果对如何通过依赖注入框架来使用 MyBatis 感兴趣可以研究一下 MyBatis-Spring 或 MyBatis-Guice 两个子项目。

* SqlSessionFactoryBuilder

这个类可以被实例化、使用和丢弃，一旦创建了 SqlSessionFactory，就不再需要它了。因此 SqlSessionFactoryBuilder 实例的最佳作用域是方法作用域（也就是局部方法变量）。你可以重用 SqlSessionFactoryBuilder 来创建多个 SqlSessionFactory 实例，但是最好还是不要让其一直存在以保证所有的 XML 解析资源开放给更重要的事情。

* SqlSessionFactory

SqlSessionFactory 一旦被创建就应该在应用的运行期间一直存在，没有任何理由对它进行清除或重建。使用 SqlSessionFactory 的最佳实践是在应用运行期间不要重复创建多次，多次重建 SqlSessionFactory 被视为一种代码“坏味道（bad smell）”。因此 SqlSessionFactory 的最佳作用域是应用作用域。有很多方法可以做到，最简单的就是使用单例模式或者静态单例模式。

* SqlSession

每个线程都应该有它自己的 SqlSession 实例。SqlSession 的实例不是线程安全的，因此是不能被共享的，所以它的最佳的作用域是请求或方法作用域。绝对不能将 SqlSession 实例的引用放在一个类的静态域，甚至一个类的实例变量也不行。也绝不能将 SqlSession 实例的引用放在任何类型的管理作用域中，比如 Servlet 架构中的 HttpSession。如果你现在正在使用一种 Web 框架，要考虑 SqlSession 放在一个和 HTTP 请求对象相似的作用域中。换句话说，每次收到的 HTTP 请求，就可以打开一个 SqlSession，返回一个响应，就关闭它。这个关闭操作是很重要的，你应该把这个关闭操作放到 finally 块中以确保每次都能执行关闭。下面的示例就是一个确保 SqlSession 关闭的标准模式：

SqlSession session = sqlSessionFactory.openSession();
try {
  // do work
} finally {
  session.close();
}

在你的所有的代码中一致性地使用这种模式来保证所有数据库资源都能被正确地关闭。

* 映射器实例（Mapper Instances）

映射器是一个你创建来绑定你映射的语句的接口。映射器接口的实例是从 SqlSession 中获得的。因此从技术层面讲，任何映射器实例的最大作用域是和请求它们的 SqlSession 相同的。尽管如此，映射器实例的最佳作用域是方法作用域。也就是说，映射器实例应该在调用它们的方法中被请求，用过之后即可废弃。并不需要显式地关闭映射器实例，尽管在整个请求作用域（request scope）保持映射器实例也不会有什么问题，但是很快你会发现，像 SqlSession 一样，在这个作用域上管理太多的资源的话会难于控制。所以要保持简单，最好把映射器放在方法作用域（method scope）内。下面的示例就展示了这个实践：

SqlSession session = sqlSessionFactory.openSession();
try {
  BlogMapper mapper = session.getMapper(BlogMapper.class);
  // do work
} finally {
  session.close();
}

### Reference

[1]. [(Offical)Mybatis-QuickStart](http://www.mybatis.org/mybatis-3/zh/getting-started.html)
