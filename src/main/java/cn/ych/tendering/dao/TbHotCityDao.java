package cn.ych.tendering.dao;

import cn.ych.tendering.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TbHotCityDao {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public int insert(String start_city,String end_city) {
        Connection con = DBUtils.connect();
        PreparedStatement pre = null;
        try {
            pre = con.prepareStatement("insert into tb_hot_city (start)city,end_city,date) values (?,?,?)");
            pre.setString(1, start_city);
            pre.setString(2, end_city);
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
