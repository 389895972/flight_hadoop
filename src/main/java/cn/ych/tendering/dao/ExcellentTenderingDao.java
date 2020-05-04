package cn.ych.tendering.dao;

import cn.ych.tendering.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExcellentTenderingDao {
    public int deleteAll() {
        Connection con = DBUtils.connect();
        PreparedStatement pre = null;
        try {
            pre = con.prepareStatement("delete from excellent_tendering");
            return pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(con, pre, null);
        }
        return 0;
    }

    public int insert(int e_id, String e_name, int win, int fail, int sum, double winRate, double failRate) {
        Connection con = DBUtils.connect();
        PreparedStatement pre = null;
        try {
            pre = con.prepareStatement("insert into excellent_tendering (e_id,e_name,win,fail,sum,win_rate,fail_rate) values (?,?,?,?,?,?,?)");
            pre.setInt(1, e_id);
            pre.setString(2, e_name);
            pre.setInt(3, win);
            pre.setInt(4, fail);
            pre.setInt(5, sum);
            pre.setDouble(6, winRate);
            pre.setDouble(7, failRate);
            return pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(con, pre, null);
        }
        return 0;
    }
}
