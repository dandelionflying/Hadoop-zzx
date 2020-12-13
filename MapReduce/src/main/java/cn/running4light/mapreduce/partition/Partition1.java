package cn.running4light.mapreduce.partition;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 *
 */
public class Partition1 extends Partitioner<Text, NullWritable> {
    /**
     *  @Description
     *      1:定义分区规则
     *      2:返回对应的分区编号
     *  @Author running4light朱泽雄
     *  @CreateTime 10:47 2020/12/13
     *
     */
    @Override
    public int getPartition(Text text, NullWritable nullWritable, int i) {
        //1:拆分行文本数据(K2),获取中奖字段的值
        String[] split = text.toString().split("\t");
        String numStr = split[5];

        //2:判断中奖字段的值和15的关系，然后返回对应的分区编号
        if(Integer.parseInt(numStr) > 15){
            return  1;
        }else{
            return  0;
        }
    }
}
