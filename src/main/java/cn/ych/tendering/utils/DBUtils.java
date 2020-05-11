package cn.ych.tendering.utils;

import java.sql.*;


public class DBUtils {
    private static Connection con = null;
    private static final String url = "jdbc:mysql://47.96.165.193:3306/aiyou?useUnicode=true&characterEncoding=utf8&autoReconnect=true&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8";
    private static final String user = "root";
    private static final String password = "YCH670989882@qq.com";

    static {
        final String driver = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driver);
            System.out.println("驱动程序加载成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection connect() {
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("连接数据库失败: " + e.getMessage());
        } finally {
            return con;
        }
    }

    public static void close(Connection con, PreparedStatement pre, ResultSet res) {
        if (res != null)
            try {
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (pre != null)
                    try {
                        pre.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        if (con != null)
                            try {
                                con.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                    }
            }


    }
}
