package vo;
/**
 * ”√ªß¿‡
 * @author admin
 *
 */
public class UserInfo {
	private int userid;
	private String username;
	private String userpwd;
	private String usertel;
	private String userphoto;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getUsertel() {
		return usertel;
	}
	public void setUsertel(String usertel) {
		this.usertel = usertel;
	}
	public String getUserphoto() {
		return userphoto;
	}
	public void setUserphoto(String userphoto) {
		this.userphoto = userphoto;
	}
	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserInfo(int userid, String username, String userpwd,
                    String usertel, String userphoto) {
		super();
		this.userid = userid;
		this.username = username;
		this.userpwd = userpwd;
		this.usertel = usertel;
		this.userphoto = userphoto;
	}
	
	
	
}
