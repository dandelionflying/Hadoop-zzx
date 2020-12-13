package cn.running4light.mapreduce.partition;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 *
 */
public class PartitionMapper1 extends Mapper<LongWritable, Text, Text, NullWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 方法1 定时器
//        Counter counter = context.getCounter("MR_COUNTER", "partition_counter");
//        counter.increment(1L);
        context.write(value,NullWritable.get());
    }
}
