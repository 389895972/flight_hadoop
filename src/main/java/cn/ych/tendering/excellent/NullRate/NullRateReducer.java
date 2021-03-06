package cn.ych.tendering.excellent.NullRate;

import cn.ych.tendering.dao.TbNullRateDao;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class NullRateReducer extends Reducer<IntWritable, Text, IntWritable, Text> {

    Text v = new Text();
    TbNullRateDao tbNullRateDao = new TbNullRateDao();
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
        v.set(key.get() + "," + null_rate);
        tbNullRateDao.insert(key.get(), null_rate);
        context.write(key, v);
    }
}
