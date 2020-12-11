# Hadoop-zzx
## 集群配置

### 集群规划

```properties
pc-1:196.168.80.131
pc-2:196.168.80.132
pc-3:196.168.80.133
pc-4:192.168.80.134 
```

域名映射设置：

```properties
vim /etc/hosts
```

修改各主机主机名方法：

```properties
vim /etc/hostname
```

### hadoop配置

core-site.xml

```xml
<configuration>
	<!--  指定集群的文件系统类型:分布式文件系统 -->
	<property>
		<name>fs.default.name</name>
		<value>hdfs://pc-1:8020</value>  
	</property>
	<!--  指定临时文件存储目录 -->
	<property>
		<name>hadoop.tmp.dir</name>
		<value>/my/servers/hadoop-2.7.2/hadoopDatas/tempDatas</value>
	</property>
	<!--  缓冲区大小，实际工作中根据服务器性能动态调整 -->
	<property>
		<name>io.file.buffer.size</name>
		<value>4096</value>
	</property>

	<!--  开启hdfs的垃圾桶机制，删除掉的数据可以从垃圾桶中回收，单位分钟 -->
	<property>
		<name>fs.trash.interval</name>
		<value>10080</value>
	</property>
</configuration>
```

hadoop-env.sh

```sh
# The java implementation to use.
export JAVA_HOME=/my/servers/jdk1.8.0_271/
```

hdfs-site.xml

```xml

<configuration>
	<property>
		<name>dfs.namenode.secondary.http-address</name>
		<value>pc-1:50090</value>
	</property>

	<!-- 指定namenode的访问地址和端口 -->
	<property>
		<name>dfs.namenode.http-address</name>
		<value>pc-1:50070</value>
	</property>
	<!-- 指定namenode元数据的存放位置 -->
	<property>
		<name>dfs.namenode.name.dir</name>
		<value>file:///my/servers/hadoop-2.7.2/hadoopDatas/namenodeDatas,file:///export/servers/hadoop-2.7.2/hadoopDatas/namenodeDatas2</value>
	</property>
	<!--  定义dataNode数据存储的节点位置，实际工作中，一般先确定磁盘的挂载目录，然后多个目录用，进行分割  -->
	<property>
		<name>dfs.datanode.data.dir</name>
		<value>file:///my/servers/hadoop-2.7.2/hadoopDatas/datanodeDatas,file:///export/servers/hadoop-2.7.2/hadoopDatas/datanodeDatas2</value>
	</property>
	
	<!-- 指定namenode日志文件的存放目录 -->
	<property>
		<name>dfs.namenode.edits.dir</name>
		<value>file:///my/servers/hadoop-2.7.2/hadoopDatas/nn/edits</value>
	</property>
	

	<property>
		<name>dfs.namenode.checkpoint.dir</name>
		<value>file:///my/servers/hadoop-2.7.2/hadoopDatas/snn/name</value>
	</property>
	<property>
		<name>dfs.namenode.checkpoint.edits.dir</name>
		<value>file:///my/servers/hadoop-2.7.2/hadoopDatas/dfs/snn/edits</value>
	</property>
	<!-- 文件切片的副本个数-->
	<property>
		<name>dfs.replication</name>
		<value>3</value>
	</property>

	<!-- 设置HDFS的文件权限-->
	<property>
		<name>dfs.permissions</name>
		<value>false</value>
	</property>

	<!-- 设置一个文件切片的大小：128M-->
	<property>
		<name>dfs.blocksize</name>
		<value>134217728</value>
	</property>
</configuration>
```

mapred-env.sh

```sh
export JAVA_HOME=/my/servers/jdk1.8.0_271/
```

mapred-site.xml

```xml

<configuration>
	<!-- 指定分布式计算使用的框架是yarn -->
	<property>
			<name>mapreduce.framework.name</name>
			<value>yarn</value>
	</property>

	<!-- 开启MapReduce小任务模式 -->
	<property>
		<name>mapreduce.job.ubertask.enable</name>
		<value>true</value>
	</property>
	
	<!-- 设置历史任务的主机和端口 -->
	<property>
		<name>mapreduce.jobhistory.address</name>
		<value>pc-1:10020</value>
	</property>

	<!-- 设置网页访问历史任务的主机和端口 -->
	<property>
		<name>mapreduce.jobhistory.webapp.address</name>
		<value>pc-1:19888</value>
	</property>
</configuration>
```

slaves

```properties
pc-1
pc-2
pc-3
```

yarn-site.xml

```xml

<configuration>

<!-- Site specific YARN configuration properties -->
	<!-- 配置yarn主节点的位置 -->
	<property>
		<name>yarn.resourcemanager.hostname</name>
		<value>pc-1</value>
	</property>

	<property>
		<name>yarn.nodemanager.aux-services</name>
		<value>mapreduce_shuffle</value>
	</property>
	
	<!-- 开启日志聚合功能 -->
	<property>
		<name>yarn.log-aggregation-enable</name>
		<value>true</value>
	</property>
	<!-- 设置聚合日志在hdfs上的保存时间 -->
	<property>
		<name>yarn.log-aggregation.retain-seconds</name>
		<value>604800</value>
	</property>
	<!-- 设置yarn集群的内存分配方案 -->
	<property>    
		<name>yarn.nodemanager.resource.memory-mb</name>    
		<value>20480</value>
	</property>

	<property>  
        	 <name>yarn.scheduler.minimum-allocation-mb</name>
         	<value>2048</value>
	</property>
	<property>
		<name>yarn.nodemanager.vmem-pmem-ratio</name>
		<value>2.1</value>
	</property>
</configuration>
```



## hadopp集群启动

进入hadoop目录,执行：

```shell
bin/hdfs namenode -format //格式化（修改配置文件后需要格式化）
sbin/start-dfs.sh
sbin/start-yarn.sh
sbin/mr-jobhistory-daemon.sh start historyserver
```

检验

jps：

```properties
[root@pc-1 hadoop-2.7.2]# jps
6000 DataNode
6848 Jps
6181 SecondaryNameNode
5866 NameNode
6778 JobHistoryServer
6335 ResourceManager
6623 NodeManager
```

```properties
[root@pc-2 ~]# jps
1797 NamesrvStartup
7781 NodeManager
7909 Jps
1831 BrokerStartup
7655 DataNode
```

可视化：

```shell
pc-1:50070  //查看hdfs
pc-1:8088 //查看yarn集群
pc-1:19888 // 查看历史完成的任务
```



