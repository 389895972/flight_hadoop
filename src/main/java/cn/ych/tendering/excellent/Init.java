package cn.ych.tendering.excellent;

import cn.ych.tendering.dao.TbFlightInfoDao;
import cn.ych.tendering.pojo.TbFlightInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Init {
    private File file;
    public static String hotCityFileName = "input-hot-city-";
    public static String nullRateFileName = "input-null-rate-";

    public void initHotCity() throws IOException {
        hotCityFileName += System.currentTimeMillis();
        file = new File(hotCityFileName);

        file.createNewFile();

        FileWriter fileWriter = new FileWriter(file);
        List<TbFlightInfo> tbFlightInfoList = new TbFlightInfoDao().selectHotCity();
        for (TbFlightInfo tbFlightInfo : tbFlightInfoList) {
            fileWriter.append(String.valueOf(tbFlightInfo.getFlight_no())).append("\t").append(tbFlightInfo.getFrom_city()).append(":").append(tbFlightInfo.getTo_city()).append(":")
            .append(tbFlightInfo.getFlight_date()).append(":").append(String.valueOf(tbFlightInfo.getEconomy_num())).append(":")
            .append(String.valueOf(tbFlightInfo.getHead_num())).append("\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }

    public void initNullRate() throws IOException {
        nullRateFileName += System.currentTimeMillis();
        file = new File(nullRateFileName);

        file.createNewFile();

        FileWriter fileWriter = new FileWriter(file);
        List<TbFlightInfo> tbFlightInfoList = new TbFlightInfoDao().selectNullRate();
        for (TbFlightInfo tbFlightInfo : tbFlightInfoList) {
            fileWriter.append(String.valueOf(tbFlightInfo.getFlight_no())).append("\t").append(tbFlightInfo.getFrom_city()).append(":").append(tbFlightInfo.getTo_city()).append(":")
                    .append(tbFlightInfo.getFlight_date()).append(":").append(String.valueOf(tbFlightInfo.getEconomy_num())).append(":")
                    .append(String.valueOf(tbFlightInfo.getHead_num())).append("\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }
}
