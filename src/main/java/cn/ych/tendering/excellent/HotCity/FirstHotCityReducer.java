package cn.ych.tendering.excellent.HotCity;

import cn.ych.tendering.dao.TbNullRateDao;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class FirstHotCityReducer extends Reducer<IntWritable, Text, IntWritable, Text> {

    Text v = new Text();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void reduce(IntWritable key, Iterable<Text> values,
                          Context context) throws IOException, InterruptedException {
        int empty = 0;
        double null_rate = 0;
        Text text = null;
        for (Text value : values) {
            text = value;
            empty++;
        }
        if (text == null) {
            return;
        }
        String[] split = text.toString().split(":");
        int sum = Integer.parseInt(split[3]) + Integer.parseInt(split[4]);
        null_rate = (sum - empty) / (sum + 0.0);
        v.set(split[0] + "," + split[1] + "," + null_rate);
        context.write(key, v);
    }
}
