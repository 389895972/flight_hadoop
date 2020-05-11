package cn.ych.tendering.dao;

import cn.ych.tendering.pojo.TbFlightInfo;
import cn.ych.tendering.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbFlightInfoDao {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<TbFlightInfo> selectHotCity() {
        Connection con = DBUtils.connect();
        PreparedStatement pre = null;
        List<TbFlightInfo> bidList = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            pre = con.prepareStatement("select * from tb_flight_info,tb_flight_seat where tb_flight_info.flight_no=tb_flight_seat.flight_no and flight_date < ?");
            pre.setString(1,simpleDateFormat.format(new Date()));
            resultSet = pre.executeQuery();
            while (resultSet.next()) {
                TbFlightInfo bid = new TbFlightInfo();
                bid.setEconomy_num(resultSet.getInt("economy_num"));
                bid.setHead_num(resultSet.getInt("head_num"));
                bid.setFlight_no(resultSet.getInt("flight_no"));
                bid.setFrom_city(resultSet.getString("from_city"));
                bid.setTo_city(resultSet.getString("to_city"));
                bid.setFlight_date(resultSet.getString("flight_date"));
                bidList.add(bid);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(con, pre, resultSet);
        }
        return bidList;
    }

    public List<TbFlightInfo> selectNullRate() {
        Connection con = DBUtils.connect();
        PreparedStatement pre = null;
        List<TbFlightInfo> bidList = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            pre = con.prepareStatement("select * from tb_flight_info left join tb_flight_seat on tb_flight_info.flight_no=tb_flight_seat.flight_no and flight_date >= ?");
            pre.setString(1,simpleDateFormat.format(new Date()));
            resultSet = pre.executeQuery();
            while (resultSet.next()) {
                TbFlightInfo bid = new TbFlightInfo();
                bid.setEconomy_num(resultSet.getInt("economy_num"));
                bid.setHead_num(resultSet.getInt("head_num"));
                bid.setFlight_no(resultSet.getInt("flight_no"));
                bid.setFrom_city(resultSet.getString("from_city"));
                bid.setTo_city(resultSet.getString("to_city"));
                bid.setFlight_date(resultSet.getString("flight_date"));
                bidList.add(bid);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(con, pre, resultSet);
        }
        return bidList;
    }
}
