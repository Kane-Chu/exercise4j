# spring-boot flyway

Flyway可以对数据库进行升级，从任意一个版本升级到最新的版本。但是升级的依据是用户自己编写的sql脚本，用户自己决定每一个版本的升级内容。

flyway在升级数据库的时候，会检查已经执行过的版本对应的脚本是否发生变化，包括脚本文件名，以及脚本内容。如果flyway检测到发生了变化，则抛出错误，并终止升级。

如果已经执行过的脚本没有发生变化，flyway会跳过这些脚本，依次执行后续版本的脚本，并在记录表中插入对应的升级记录。

所以，flyway总是幂等的，而且可以支持跨版本的升级。

升级一个空的数据库，或者在一直使用flyway升级方案的数据库上进行升级，都不会又问题。但是，如果在已有的数据库引入flyway，就需要一些额外的工作。

flyway检测数据库中是否有历史记录表，没有则代表是第一次升级。此时，flyway要求数据库是空的，并拒绝对数据库进行升级。

你可以设置baseline-on-migrate参数为true，flyway会自动将当前的数据库记录为V1版本，然后执行升级脚本。这也表示用户所准备的脚本中，V1版本的脚本会被跳过，只有V1之后的版本才会被执行。


#### 依赖版本
由于springboot中auto configuration对应的flyway版本不同，因此需要使用对应的版本，否则有可能会报错
> spring-boot 2.2.5.RELEASE<br>
> flyway 6.3.0

#### flyway sql脚本格式
V+版本号+双下划线+描述+结束符 

例如：`V1.0.0__First_version.sql`
其中V是默认值， 可以通过`flyway.sql-migration-prefix`来指定前缀 

#### 命令行
用户可以在官网下载适合自己平台的工具包，进行相关配置之后，就可以通过命令行的方式使用Flyway。

#### 使用Maven或Gradle插件
[配置方式](https://flywaydb.org/getstarted/firststeps/maven)
[Maven插件说明](https://flywaydb.org/documentation/maven/)

使用maven命令执行插件，默认在`classpath:/db/migration`目录搜索脚本，如果该目录不存在，命令将被忽略。

命令执行格式执行：`mvn flyway:{flyway-command}`

  * **migrate**
  
    `mvn flyway:migrate`
    
    这个命令会搜索默认的脚本目录，检测并根据结果选择执行升级脚本。
    
  * **clean**
  
    `mvn flyway:clean`
   
     这个命令会清除指定schema下所有的对象，包括table、view、triggers...，让schema变成空的状态。
   
   * **info**
   
     `mvn flyway:info`
   
     这个命令显示指定schema的升级状态，当前的数据库的版本信息。
   
   * **validate**
   
     `mvn flyway:validate`
   
     这个命令用于校验，范围包括已升级的脚本是否改名，已升级的脚本内容是否修改。所有针对已升级的脚本进行的改动都会导致校验失败。
     执行migrate会自动进行校验，如果失败将不会做任何的migrate。
     flyway希望用户提供的脚本是稳定的，以免造成额外的复杂性和混乱。
   
   * **baseline**
   
     `mvn flyway:baseline`
   
     如果用户从一个已有的数据库导出脚本，作为flyway的升级脚本。已存在的数据库是不需要升级的。
   
     baseline用于将当前数据库标记为baseline，并记录version为1。这表示用户继续执行migrate命令时，会自动跳过V1版本对应的脚本。
   
     而对于空的数据库，因为没有执行baseline，所以可以正常的执行V1版本对应的脚本。
   
     > **`P.S.`** <br> 
     > 手动修改flyway自动生成的baseline记录，将版本号改为其他的版本号，将自动跳过该版本及更早的版本。

#### Java API

Flyway提供了基于Java的API包，用户可以将API包引入maven依赖，直接通过调用其API来执行相关命令。

Spring-Boot集成了Flyway，只要把API包加入classpath，spring-boot在启动应用时会去指定的目录查找脚本文件，并根据一定的策略选择或忽略执行。

使用Spring-Boot，用户不必再显式的编写代码调用API，只需要将脚本文件放在约定的目录，或者告诉Spring-Boot你把脚本文件放在哪里了。

如果用户需要实现非常灵活的迁移，Spring-Boot默认的方案无法满足，也可以尝试寻找自己编码调用API的方案。

spring为flyway准备了专属的数据源配置，但是在默认的情况下，可以直接使用spring.datasource的配置。


#### 使用总结

我们是在中途尝试使用flyway，所以开发环境会有一个已存在的数据库。我们需要从开发环境中导出数据库脚本，并对开发环境数据库进行baseline标记。导出的脚本可用于新环境的部署。

如果我们已经有了生产环境，而且生产环境和开发环境的数据库已经有了较大的差异。暂时可以想到的方案大概有2个方案：

方案一：

在生产环境备份数据库，然后创建一个全新的数据库，手动将备份库里的数据导入到新的数据库。

方案二：

基于生产环境的数据库，创建V1版本的脚本；基于开发库相对于生产库的变更，创建V2版本的脚本。在开发环境baseline，然后修改版本记录，改为2。在生产环境中baseline，然后migrate使其升级到2。

方案一，需要更多的人工介入，但是比较稳妥；方案二，难点在于溯源出正确的差异，编制V2脚本。



> 参考：<br>
> 作者：heyikan<br>
> 链接：https://www.jianshu.com/p/b321dafdfe83<br>
> 来源：简书<br>
> 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。<br>
