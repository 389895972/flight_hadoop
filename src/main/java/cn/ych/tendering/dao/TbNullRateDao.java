package cn.ych.tendering.dao;

import cn.ych.tendering.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TbNullRateDao {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public int insert(int flight_no, double null_rate) {
        Connection con = DBUtils.connect();
        PreparedStatement pre = null;
        try {
            pre = con.prepareStatement("insert into tb_null_rate (flight_no,null_rate,date) values (?,?,?)");
            pre.setInt(1, flight_no);
            pre.setDouble(2, null_rate);
            pre.setString(3, simpleDateFormat.format(new Date()));
            return pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(con, pre, null);
        }
        return 0;
    }
}
