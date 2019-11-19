package vo;

/**
 * @author zhou_zhou
 * @date 2019/4/114:37
 */
public class RoomBaseInfo {
    private int roomId;
    private int cinemaId;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public RoomBaseInfo() {
    }

    public RoomBaseInfo(int roomId, int cinemaId) {
        this.roomId = roomId;
        this.cinemaId = cinemaId;
    }
}
