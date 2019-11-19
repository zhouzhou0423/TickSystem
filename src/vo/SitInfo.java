package vo;

/**
 * @author zhou_zhou
 * @date 2019/4/5 17:10
 */
public class SitInfo {
    private int roomid;
    private int arrangeid;
    private int state;
    private String sitposition;

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public int getArrangeid() {
        return arrangeid;
    }

    public void setArrangeid(int arrangeid) {
        this.arrangeid = arrangeid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getSitposition() {
        return sitposition;
    }

    public void setSitposition(String sitposition) {
        this.sitposition = sitposition;
    }

    public SitInfo() {
    }

    public SitInfo(int roomid, int arrangeid, int state, String sitposition) {
        this.roomid = roomid;
        this.arrangeid = arrangeid;
        this.state = state;
        this.sitposition = sitposition;
    }
}
