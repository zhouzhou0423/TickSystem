package vo;

/**
 * @author zhou_zhou
 * @date 2019/4/18 13:46
 */
public class OrderInfo {
    private String orderid;
    private int userid;
    private int movieid;
    private String moviename;
    private String ondate;
    private String starttime;
    private String endtime;
    private int num;
    private double movieprice;
    private double totalprice;
    private int cinemaid;
    private int arrangeid;
    private String sitposition;
    private int orderstate;
    private String ordertime;
    private int roomid;
    private String ticketcode;
    private String cinemaname;

    public String getCinemaname() {
        return cinemaname;
    }

    public void setCinemaname(String cinemaname) {
        this.cinemaname = cinemaname;
    }

    public String getTicketcode() {
        return ticketcode;
    }

    public void setTicketcode(String ticketcode) {
        this.ticketcode = ticketcode;
    }

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public String getOndate() {
        return ondate;
    }

    public void setOndate(String ondate) {
        this.ondate = ondate;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getMovieprice() {
        return movieprice;
    }

    public void setMovieprice(double movieprice) {
        this.movieprice = movieprice;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public int getCinemaid() {
        return cinemaid;
    }

    public void setCinemaid(int cinemaid) {
        this.cinemaid = cinemaid;
    }

    public int getArrangeid() {
        return arrangeid;
    }

    public void setArrangeid(int arrangeid) {
        this.arrangeid = arrangeid;
    }

    public String getSitposition() {
        return sitposition;
    }

    public void setSitposition(String sitposition) {
        this.sitposition = sitposition;
    }

    public int getOrderstate() {
        return orderstate;
    }

    public void setOrderstate(int orderstate) {
        this.orderstate = orderstate;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public OrderInfo() {
    }

    public OrderInfo(String orderid, int userid, int movieid, String moviename, String ondate, String starttime, String endtime, int num, double movieprice, double totalprice, int cinemaid, int arrangeid, String sitposition, int orderstate, String ordertime, int roomid, String ticketcode, String cinemaname) {
        this.orderid = orderid;
        this.userid = userid;
        this.movieid = movieid;
        this.moviename = moviename;
        this.ondate = ondate;
        this.starttime = starttime;
        this.endtime = endtime;
        this.num = num;
        this.movieprice = movieprice;
        this.totalprice = totalprice;
        this.cinemaid = cinemaid;
        this.arrangeid = arrangeid;
        this.sitposition = sitposition;
        this.orderstate = orderstate;
        this.ordertime = ordertime;
        this.roomid = roomid;
        this.ticketcode = ticketcode;
        this.cinemaname = cinemaname;
    }

    public OrderInfo(String orderid, int userid, int num, double totalprice, int arrangeid, String sitposition, int orderstate, String ordertime) {
        this.orderid = orderid;
        this.userid = userid;
        this.num = num;
        this.totalprice = totalprice;
        this.arrangeid = arrangeid;
        this.sitposition = sitposition;
        this.orderstate = orderstate;
        this.ordertime = ordertime;
    }
}
