package vo;

/**
 * @author zhou_zhou
 * @date 2019/4/5 15:42
 */
public class RoomInfo {
    private int roomid;
    private int cinemaid;
    private int rownum;
    private String rowsit;
    private int rowcountnum;
    private int columnnum;
    private int totalsit;

    public int getRowcountnum() {
        return rowcountnum;
    }

    public void setRowcountnum(int rowcountnum) {
        this.rowcountnum = rowcountnum;
    }

    public int getColumnnum() {
        return columnnum;
    }

    public void setColumnnum(int columnnum) {
        this.columnnum = columnnum;
    }

    public int getTotalsit() {
        return totalsit;
    }

    public void setTotalsit(int totalsit) {
        this.totalsit = totalsit;
    }

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public int getCinemaid() {
        return cinemaid;
    }

    public void setCinemaid(int cinemaid) {
        this.cinemaid = cinemaid;
    }

    public int getRownum() {
        return rownum;
    }

    public void setRownum(int rownum) {
        this.rownum = rownum;
    }

    public String getRowsit() {
        return rowsit;
    }

    public void setRowsit(String rowsit) {
        this.rowsit = rowsit;
    }

    public RoomInfo() {
    }

    public RoomInfo(int roomid, int cinemaid, int rownum, String rowsit, int rowcountnum, int columnnum, int totalsit) {
        this.roomid = roomid;
        this.cinemaid = cinemaid;
        this.rownum = rownum;
        this.rowsit = rowsit;
        this.rowcountnum = rowcountnum;
        this.columnnum = columnnum;
        this.totalsit = totalsit;
    }
}
