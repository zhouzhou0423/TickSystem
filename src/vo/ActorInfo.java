package vo;

/**
 * @author zhou_zhou
 * @date 2019/4/14 11:44
 */
public class ActorInfo {
    private int actorid;
    private String actorname;
    private String photo;

    public int getActorid() {
        return actorid;
    }

    public void setActorid(int actorid) {
        this.actorid = actorid;
    }

    public String getActorname() {
        return actorname;
    }

    public void setActorname(String actorname) {
        this.actorname = actorname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public ActorInfo(int actorid, String actorname, String photo) {
        this.actorid = actorid;
        this.actorname = actorname;
        this.photo = photo;
    }

    public ActorInfo() {
    }
}
