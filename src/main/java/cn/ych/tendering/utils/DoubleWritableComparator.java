package cn.ych.tendering.utils;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * IntWritable类的倒叙排序
 * @author   administrator
 * @Date	 2019年10月7日
 */
public class DoubleWritableComparator extends WritableComparator {

    /*
     * 重写构造方法，定义比较类 IntWritable
     */
    public DoubleWritableComparator() {
        super(DoubleWritable.class, true);
    }
    /*
     * 重写compare方法，自定义比较规则
     */
    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        //向下转型
        DoubleWritable ia = (DoubleWritable) a;
        DoubleWritable ib = (DoubleWritable) b;
        return ib.compareTo(ia);
    }
}