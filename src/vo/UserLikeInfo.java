package vo;

/**
 * @author zhou_zhou
 * @date 2019/4/16 22:42
 */
public class UserLikeInfo {
    private int id;
    private int userid;
    private int movieid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public UserLikeInfo() {
    }

    public UserLikeInfo(int id, int userid, int movieid) {
        this.id = id;
        this.userid = userid;
        this.movieid = movieid;
    }
}
