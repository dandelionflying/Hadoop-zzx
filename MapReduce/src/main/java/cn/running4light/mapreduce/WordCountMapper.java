package cn.running4light.mapreduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 *
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
    /**
     *  @Description    将<k1,v1>转为<k2,v2>
     *  @Author running4light朱泽雄
     *  @CreateTime 11:52 2020/12/11
     *  @param key 行偏移量
     *  @param value 每一行的文本数据
     *  @param context   上下文
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        Text text = new Text();
        LongWritable longWritable = new LongWritable();
        String[] split = value.toString().split(",");
        for (String word : split) {
            //3:将K2和V2写入上下文
            text.set(word);
            longWritable.set(1);
            context.write(text, longWritable);
        }
    }
}
