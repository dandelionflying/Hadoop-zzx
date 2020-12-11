package cn.running4light.mapreduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 *
 */
public class WordCountReducer extends Reducer<Text,LongWritable,Text,LongWritable> {
    /**
     *          新  K2         V2
     *             hello      <1,1,1>
     *             world      <1,1>
     *             hadoop     <1>
     *         ------------------------
     *            K3        V3
     *            hello     3
     *            world     2
     *            hadoop    1
     * @param key
     * @param values
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        long count = 0;
        //1:遍历集合，将集合中的数字相加，得到 V3
        for (LongWritable value : values) {
            count += value.get();
        }
        //2:将K3和V3写入上下文中
        context.write(key, new LongWritable(count));
    }
}
