# MapReduce
## wordcount-Demo

#### 需求：在一堆给定的文本文件中统计输出每一个单词出现的总次数

#### 数据准备：

格式：txt

```properties
hello,world,hadoop
hive,sqoop,flume,hello
kitty,tom,jerry,world
hadoop
```

上传到HDFS：

```properties
hdfs dfs -mkdir /wordcount/
hdfs dfs -put wordCount.txt /wordcount/
```

验证：

[0]: http://pc-1:50070/explorer.html#/	"文件预览"

#### 编码



#### 运行

- 集群方式
  - 打包jar，上传，运行

  ```
  hadoop jar mapreduce-0.0.1-SNAPSHOT.jar cn.running4light.mapreduce.JobMain
  ```

  

  - 未找到job：

  ```properties
  20/12/11 14:01:03 INFO client.RMProxy: Connecting to ResourceManager at pc-1/192.168.80.131:8032
  20/12/11 14:01:04 WARN mapreduce.JobResourceUploader: No job jar file set.  User classes may not be found. See Job or Job#setJar(String).
  20/12/11 14:01:05 INFO input.FileInputFormat: Total input paths to process : 1
  20/12/11 14:01:05 INFO mapreduce.JobSubmitter: number of splits:1
  20/12/11 14:01:05 INFO mapreduce.JobSubmitter: Submitting tokens for job: job_1607654342595_0001
  20/12/11 14:01:06 INFO mapred.YARNRunner: Job jar is not present. Not adding any jar to the list of resources.
  ```

  

  - 设置jar包

  

  ```java
  job.setJar("mapreduce-0.0.1-SNAPSHOT.jar");
  或
  job.setJarByClass(JobMain.class);
  ```

  

  - 
  - 运行成功：

  ```properties
  20/12/11 14:01:03 INFO client.RMProxy: Connecting to ResourceManager at pc-1/192.168.80.131:8032
  20/12/11 14:01:04 WARN mapreduce.JobResourceUploader: No job jar file set.  User classes may not be found. See Job or Job#setJar(String).
  20/12/11 14:01:05 INFO input.FileInputFormat: Total input paths to process : 1
  20/12/11 14:01:05 INFO mapreduce.JobSubmitter: number of splits:1
  20/12/11 14:01:05 INFO mapreduce.JobSubmitter: Submitting tokens for job: job_1607654342595_0001
  20/12/11 14:01:06 INFO mapred.YARNRunner: Job jar is not present. Not adding any jar to the list of resources.
  20/12/11 14:01:06 INFO impl.YarnClientImpl: Submitted application application_1607654342595_0001
  20/12/11 14:01:06 INFO mapreduce.Job: The url to track the job: http://pc-1:8088/proxy/application_1607654342595_0001/
  20/12/11 14:01:06 INFO mapreduce.Job: Running job: job_1607654342595_0001
  20/12/11 14:01:18 INFO mapreduce.Job: Job job_1607654342595_0001 running in uber mode : true
  20/12/11 14:01:18 INFO mapreduce.Job:  map 100% reduce 100%
  20/12/11 14:01:19 INFO mapreduce.Job: Job job_1607654342595_0001 failed with state FAILED due to: Task failed task_1607654342595_0001_m_000000
  Job failed as tasks failed. failedMaps:1 failedReduces:0
  
  20/12/11 14:01:19 INFO mapreduce.Job: Counters: 17
  	Job Counters 
  		Failed map tasks=1
  		Failed reduce tasks=1
  		Launched map tasks=1
  		Launched reduce tasks=1
  		Other local map tasks=1
  		Total time spent by all maps in occupied slots (ms)=213
  		Total time spent by all reduces in occupied slots (ms)=34
  		TOTAL_LAUNCHED_UBERTASKS=2
  		NUM_UBER_SUBMAPS=1
  		NUM_UBER_SUBREDUCES=1
  		NUM_FAILED_UBERTASKS=2
  		Total time spent by all map tasks (ms)=213
  		Total time spent by all reduce tasks (ms)=34
  		Total vcore-milliseconds taken by all map tasks=213
  		Total vcore-milliseconds taken by all reduce tasks=34
  		Total megabyte-milliseconds taken by all map tasks=218112
  		Total megabyte-milliseconds taken by all reduce tasks=34816
  [root@pc-1 hadoop]# hadoop jar mapreduce-0.0.1-SNAPSHOT.jar cn.running4light.mapreduce.JobMain
  20/12/11 14:44:50 INFO client.RMProxy: Connecting to ResourceManager at pc-1/192.168.80.131:8032
  20/12/11 14:44:53 INFO input.FileInputFormat: Total input paths to process : 1
  20/12/11 14:44:53 INFO mapreduce.JobSubmitter: number of splits:1
  20/12/11 14:44:53 INFO mapreduce.JobSubmitter: Submitting tokens for job: job_1607654342595_0002
  20/12/11 14:44:54 INFO impl.YarnClientImpl: Submitted application application_1607654342595_0002
  20/12/11 14:44:54 INFO mapreduce.Job: The url to track the job: http://pc-1:8088/proxy/application_1607654342595_0002/
  20/12/11 14:44:54 INFO mapreduce.Job: Running job: job_1607654342595_0002
  20/12/11 14:45:07 INFO mapreduce.Job: Job job_1607654342595_0002 running in uber mode : true
  20/12/11 14:45:07 INFO mapreduce.Job:  map 100% reduce 0%
  20/12/11 14:45:09 INFO mapreduce.Job:  map 100% reduce 100%
  20/12/11 14:45:10 INFO mapreduce.Job: Job job_1607654342595_0002 completed successfully
  20/12/11 14:45:10 INFO mapreduce.Job: Counters: 52
  	File System Counters
  		FILE: Number of bytes read=426
  		FILE: Number of bytes written=655
  		FILE: Number of read operations=0
  		FILE: Number of large read operations=0
  		FILE: Number of write operations=0
  		HDFS: Number of bytes read=412
  		HDFS: Number of bytes written=258657
  		HDFS: Number of read operations=35
  		HDFS: Number of large read operations=0
  		HDFS: Number of write operations=8
  	Job Counters 
  		Launched map tasks=1
  		Launched reduce tasks=1
  		Other local map tasks=1
  		Total time spent by all maps in occupied slots (ms)=3981
  		Total time spent by all reduces in occupied slots (ms)=1745
  		TOTAL_LAUNCHED_UBERTASKS=2
  		NUM_UBER_SUBMAPS=1
  		NUM_UBER_SUBREDUCES=1
  		Total time spent by all map tasks (ms)=3981
  		Total time spent by all reduce tasks (ms)=1745
  		Total vcore-milliseconds taken by all map tasks=3981
  		Total vcore-milliseconds taken by all reduce tasks=1745
  		Total megabyte-milliseconds taken by all map tasks=4076544
  		Total megabyte-milliseconds taken by all reduce tasks=1786880
  	Map-Reduce Framework
  		Map input records=4
  		Map output records=12
  		Map output bytes=167
  		Map output materialized bytes=197
  		Input split bytes=105
  		Combine input records=0
  		Combine output records=0
  		Reduce input groups=9
  		Reduce shuffle bytes=197
  		Reduce input records=12
  		Reduce output records=9
  		Spilled Records=24
  		Shuffled Maps =1
  		Failed Shuffles=0
  		Merged Map outputs=1
  		GC time elapsed (ms)=105
  		CPU time spent (ms)=3200
  		Physical memory (bytes) snapshot=511729664
  		Virtual memory (bytes) snapshot=6032080896
  		Total committed heap usage (bytes)=271409152
  	Shuffle Errors
  		BAD_ID=0
  		CONNECTION=0
  		IO_ERROR=0
  		WRONG_LENGTH=0
  		WRONG_MAP=0
  		WRONG_REDUCE=0
  	File Input Format Counters 
  		Bytes Read=73
  	File Output Format Counters 
  		Bytes Written=70
  
  ```

  

  - s
  - s

## MapReduce分区

#### 



