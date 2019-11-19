package vo;
/**
 * 演员类
 * @author admin
 *
 */
public class RoleInfo {
	private int roleid;
	private int actorid;
	private int movieid;
	private String actorname;
	private String rolename;
	private String photo;
	/*0-导演1-主演2-其他演员*/
	private int isimportant;
	private String moviename;

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public int getActorid() {
		return actorid;
	}

	public void setActorid(int actorid) {
		this.actorid = actorid;
	}

	public String getMoviename() {
		return moviename;
	}

	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}

	public int getMovieid() {
		return movieid;
	}
	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}
	public String getActorname() {
		return actorname;
	}
	public void setActorname(String actorname) {
		this.actorname = actorname;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getIsimportant() {
		return isimportant;
	}
	public void setIsimportant(int isimportant) {
		this.isimportant = isimportant;
	}
	public RoleInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoleInfo(int roleid, int actorid, int movieid, String actorname, String rolename, String photo, int isimportant, String moviename) {
		this.roleid = roleid;
		this.actorid = actorid;
		this.movieid = movieid;
		this.actorname = actorname;
		this.rolename = rolename;
		this.photo = photo;
		this.isimportant = isimportant;
		this.moviename = moviename;
	}
}
