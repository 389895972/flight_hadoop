package cn.ych.tendering.excellent.HotCity;

import cn.ych.tendering.dao.TbHotCityDao;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SecondHotCityReducer extends Reducer<DoubleWritable, Text, IntWritable, Text> {

    private final IntWritable k = new IntWritable();
    private final Text v = new Text();
    private final TbHotCityDao tbHotCityDao = new TbHotCityDao();
    private int i = 0;

    @Override
    protected void reduce(DoubleWritable key, Iterable<Text> values,
                          Context context) throws IOException, InterruptedException {
        if (i == 0) {
            for (Text value : values) {
                String string = value.toString();
                String[] split = string.split(":");
                tbHotCityDao.insert(split[0], split[1]);
            }
            i++;
        }
    }
}
