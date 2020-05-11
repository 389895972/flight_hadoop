package cn.ych.tendering.pojo;

public class TbFlightInfo {
    private int flight_no;
    private int economy_num;
    private int head_num;
    private String from_city;
    private String to_city;
    private String flight_date;

    public int getFlight_no() {
        return flight_no;
    }

    public void setFlight_no(int flight_no) {
        this.flight_no = flight_no;
    }

    public int getEconomy_num() {
        return economy_num;
    }

    public void setEconomy_num(int economy_num) {
        this.economy_num = economy_num;
    }

    public int getHead_num() {
        return head_num;
    }

    public void setHead_num(int head_num) {
        this.head_num = head_num;
    }

    public String getFrom_city() {
        return from_city;
    }

    public void setFrom_city(String from_city) {
        this.from_city = from_city;
    }

    public String getTo_city() {
        return to_city;
    }

    public void setTo_city(String to_city) {
        this.to_city = to_city;
    }

    public String getFlight_date() {
        return flight_date;
    }

    public void setFlight_date(String flight_date) {
        this.flight_date = flight_date;
    }
}
