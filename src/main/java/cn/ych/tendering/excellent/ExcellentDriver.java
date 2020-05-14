package cn.ych.tendering.excellent;


import cn.ych.tendering.excellent.HotCity.FirstHotCityMapper;
import cn.ych.tendering.excellent.HotCity.FirstHotCityReducer;
import cn.ych.tendering.excellent.HotCity.SecondHotCityMapper;
import cn.ych.tendering.excellent.HotCity.SecondHotCityReducer;
import cn.ych.tendering.excellent.NullRate.NullRateMapper;
import cn.ych.tendering.excellent.NullRate.NullRateReducer;
import cn.ych.tendering.utils.DoubleWritableComparator;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class ExcellentDriver {

    public static void main(String[] args) throws Exception {

//        new Timer("testTimer1").schedule(new TimerTask() {
//            @Override
//            public void run() {
//                try {
//                    new ExcellentDriver().start();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }, 0, 24 * 60 * 60 * 1000);

        ExcellentDriver excellentDriver = new ExcellentDriver();
        excellentDriver.startHotCity();
        excellentDriver.startNullRate();
    }

    public void startHotCity() throws Exception {
        new Init().initHotCity();

        Configuration conf = new Configuration();
        conf.set("mapred.textoutputformat.ignoreseparator", "true");
        conf.set("mapred.textoutputformat.separator", ",");

        FileSystem fs = FileSystem.get(conf);
        fs.copyFromLocalFile(new Path(Init.hotCityFileName), new Path(Init.hotCityFileName));
        // 1 获取Job对象
        Job job = Job.getInstance(conf);

        // 2 设置jar存储位置
        job.setJarByClass(ExcellentDriver.class);

        // 3 关联Map和Reduce类
        job.setMapperClass(FirstHotCityMapper.class);
        job.setReducerClass(FirstHotCityReducer.class);

        // 4 设置Mapper阶段输出数据的key和value类型
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(Text.class);

        // 5 设置最终数据输出的key和value类型
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);

        // 6 设置输入路径和输出路径
        FileInputFormat.setInputPaths(job, new Path(Init.hotCityFileName));

        String output = "tempOutput-hot-city" + System.currentTimeMillis();
        FileOutputFormat.setOutputPath(job, new Path(output));

        // 7 提交job
//		boolean result = job.waitForCompletion(true);
        job.waitForCompletion(true);

        // 1 获取Job对象
        Job job1 = Job.getInstance(conf);

//        job1.setSortComparatorClass(DoubleWritableComparator.class);
        // 2 设置jar存储位置
        job1.setJarByClass(ExcellentDriver.class);

        // 3 关联Map和Reduce类
        job1.setMapperClass(SecondHotCityMapper.class);
        job1.setReducerClass(SecondHotCityReducer.class);

        // 4 设置Mapper阶段输出数据的key和value类型
        job1.setMapOutputKeyClass(DoubleWritable.class);
        job1.setMapOutputValueClass(Text.class);

        // 5 设置最终数据输出的key和value类型
        job1.setOutputKeyClass(IntWritable.class);
        job1.setOutputValueClass(Text.class);

        // 6 设置输入路径和输出路径
        FileInputFormat.setInputPaths(job1, new Path(output + "/part-r-00000"));

        FileOutputFormat.setOutputPath(job1, new Path("output-hot-city" + System.currentTimeMillis()));

        // 7 提交job
        job1.waitForCompletion(true);

    }

    public void startNullRate() throws Exception {
        new Init().initNullRate();

        Configuration conf = new Configuration();
        conf.set("mapred.textoutputformat.ignoreseparator", "true");
        conf.set("mapred.textoutputformat.separator", ",");

        FileSystem fs = FileSystem.get(conf);
        fs.copyFromLocalFile(new Path(Init.nullRateFileName), new Path(Init.nullRateFileName));
        // 1 获取Job对象
        Job job = Job.getInstance(conf);

        // 2 设置jar存储位置
        job.setJarByClass(ExcellentDriver.class);

        // 3 关联Map和Reduce类
        job.setMapperClass(NullRateMapper.class);
        job.setReducerClass(NullRateReducer.class);

        // 4 设置Mapper阶段输出数据的key和value类型
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(Text.class);

        // 5 设置最终数据输出的key和value类型
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);

        // 6 设置输入路径和输出路径
        FileInputFormat.setInputPaths(job, new Path(Init.nullRateFileName));

        String output = "output-null-rate-" + System.currentTimeMillis();
        FileOutputFormat.setOutputPath(job, new Path(output));

        // 7 提交job
//		boolean result = job.waitForCompletion(true);
        job.waitForCompletion(true);

    }
}
