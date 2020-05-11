package cn.ych.tendering.excellent.HotCity;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SecondHotCityMapper extends Mapper<LongWritable, Text, DoubleWritable, Text> {

    Text v = new Text();
    DoubleWritable k = new DoubleWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String input = value.toString();
        if (StringUtils.isEmpty(input)) {
            return;
        }
        String[] s = input.split(",");
        v.set(s[0] + "," + s[1]);
        k.set(Double.parseDouble(s[2]));
        context.write(k, v);
    }
}
