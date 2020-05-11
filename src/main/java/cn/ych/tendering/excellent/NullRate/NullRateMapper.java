package cn.ych.tendering.excellent.NullRate;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class NullRateMapper extends Mapper<LongWritable, Text, IntWritable, Text> {

    Text v = new Text();
    IntWritable k = new IntWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        String input = value.toString();
        if (StringUtils.isEmpty(input)) {
            return;
        }
        String[] s = input.split("\t");
        k.set(Integer.parseInt(s[0]));
        v.set(s[1]);
        context.write(k, v);
    }
}
