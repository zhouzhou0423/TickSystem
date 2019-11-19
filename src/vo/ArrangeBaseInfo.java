package vo;

/**
 * @author zhou_zhou
 * @date 2019/4/116:18
 */
public class ArrangeBaseInfo {
    private int arrangeid;
    private int movieid;
    private int cinemaid;
    private String starttime;
    private String endtime;
    private int roomid;
    private double movieprice;
    private String ondate;

    public int getArrangeid() {
        return arrangeid;
    }

    public void setArrangeid(int arrangeid) {
        this.arrangeid = arrangeid;
    }

    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    public int getCinemaid() {
        return cinemaid;
    }

    public void setCinemaid(int cinemaid) {
        this.cinemaid = cinemaid;
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

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public double getMovieprice() {
        return movieprice;
    }

    public void setMovieprice(double movieprice) {
        this.movieprice = movieprice;
    }

    public String getOndate() {
        return ondate;
    }

    public void setOndate(String ondate) {
        this.ondate = ondate;
    }

    public ArrangeBaseInfo() {
    }

    public ArrangeBaseInfo(int arrangeid, int movieid, int cinemaid, String starttime, String endtime, int roomid, double movieprice, String ondate) {
        this.arrangeid = arrangeid;
        this.movieid = movieid;
        this.cinemaid = cinemaid;
        this.starttime = starttime;
        this.endtime = endtime;
        this.roomid = roomid;
        this.movieprice = movieprice;
        this.ondate = ondate;
    }
}
