package vo;

/**
 * @author zhou_zhou
 * @date 2019/4/8 15:06
 */
public class ZanInfo {
    private int userid;
    private int commentid;
    private int islike;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getCommentid() {
        return commentid;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }

    public int getIslike() {
        return islike;
    }

    public void setIslike(int islike) {
        this.islike = islike;
    }

    public ZanInfo(int userid, int commentid, int islike) {
        this.userid = userid;
        this.commentid = commentid;
        this.islike = islike;
    }

    public ZanInfo() {
    }
}
