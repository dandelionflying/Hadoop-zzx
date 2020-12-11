package cn.running4light.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.net.URI;

/**
 *
 */
public class JobMain extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {
        // 创建job
        Job job = Job.getInstance(super.getConf(), "wordcount");
        job.setJarByClass(JobMain.class);
//        job.setJar("mapreduce-0.0.1-SNAPSHOT.jar");
        // 配置job(八个步骤)

        // 1:指定文件的读取方式和读取路径
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job, new Path("hdfs://pc-1:8020/wordcount"));
        // 2:指定map阶段的处理方式和数据类型
        job.setMapperClass(WordCountMapper.class);
        //设置Map阶段K2的类型
        job.setMapOutputKeyClass(Text.class);
        //设置Map阶段V2的类型
        job.setMapOutputValueClass(LongWritable.class);

        // 7:指定reduce阶段的处理方式和数据类型
        job.setReducerClass(WordCountReducer.class);
        //设置K3的类型
        job.setOutputKeyClass(Text.class);
        //设置V3的类型
        job.setOutputValueClass(LongWritable.class);
        // 8:设置输出类型
        Path path = new Path("hdfs://pc-1:8020/wordcount_out");
        TextOutputFormat.setOutputPath(job, path);

        //获取FileSystem
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://pc-1:8020"), new Configuration());
        //判断目录是否存在
        boolean bl2 = fileSystem.exists(path);
        if(bl2){
            //删除目标目录
            fileSystem.delete(path, true);
        }


        boolean b = job.waitForCompletion(true);


        return b ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        // 启动job
        // configuration存在于JobMain的父类
        int run = ToolRunner.run(configuration, new JobMain(), args);// 0:成功
        System.exit(run);
    }
}
