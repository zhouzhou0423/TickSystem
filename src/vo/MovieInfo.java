package vo;

/**
 * 电影信息类
 * @author admin
 *
 */
public class MovieInfo {
	private int movieid;
	private int cinemaid;
	private String moviename;
	private String moviedirector;
	private String movieactor;
	private int timelimit;
	private String movieintro;
	private String releasetime;
	private String typeid;
	private String movieimg;
	private String nation;
	private long countnumber;
	private String language;
	private long wantseecount;
	private int grade;
	private int state;
	private String typename;
	private int cmstate;

	public String getMoviedirector() {
		return moviedirector;
	}

	public void setMoviedirector(String moviedirector) {
		this.moviedirector = moviedirector;
	}

	public String getMovieactor() {
		return movieactor;
	}

	public void setMovieactor(String movieactor) {
		this.movieactor = movieactor;
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

	public String getMoviename() {
		return moviename;
	}

	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}

	public int getTimelimit() {
		return timelimit;
	}

	public void setTimelimit(int timelimit) {
		this.timelimit = timelimit;
	}

	public String getMovieintro() {
		return movieintro;
	}

	public void setMovieintro(String movieintro) {
		this.movieintro = movieintro;
	}

	public String getReleasetime() {
		return releasetime;
	}

	public void setReleasetime(String releasetime) {
		this.releasetime = releasetime;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getMovieimg() {
		return movieimg;
	}

	public void setMovieimg(String movieimg) {
		this.movieimg = movieimg;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public long getCountnumber() {
		return countnumber;
	}

	public void setCountnumber(long countnumber) {
		this.countnumber = countnumber;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public long getWantseecount() {
		return wantseecount;
	}

	public void setWantseecount(long wantseecount) {
		this.wantseecount = wantseecount;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public int getCmstate() {
		return cmstate;
	}

	public void setCmstate(int cmstate) {
		this.cmstate = cmstate;
	}

	public MovieInfo() {
	}

	public MovieInfo(int movieid, int cinemaid, String moviename, String moviedirector, String movieactor, int timelimit, String movieintro, String releasetime, String typeid, String movieimg, String nation, long countnumber, String language, long wantseecount, int grade, int state, String typename, int cmstate) {
		this.movieid = movieid;
		this.cinemaid = cinemaid;
		this.moviename = moviename;
		this.moviedirector = moviedirector;
		this.movieactor = movieactor;
		this.timelimit = timelimit;
		this.movieintro = movieintro;
		this.releasetime = releasetime;
		this.typeid = typeid;
		this.movieimg = movieimg;
		this.nation = nation;
		this.countnumber = countnumber;
		this.language = language;
		this.wantseecount = wantseecount;
		this.grade = grade;
		this.state = state;
		this.typename = typename;
		this.cmstate = cmstate;
	}
}
