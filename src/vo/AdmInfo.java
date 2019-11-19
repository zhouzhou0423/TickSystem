package vo;
/**
 * 影院管理员类
 * @author admin
 *
 */
public class AdmInfo {
	private int admid;
	private String admname;
	private String admpwd;
	private String admtel;
	public int getAdmid() {
		return admid;
	}
	public void setAdmid(int admid) {
		this.admid = admid;
	}
	public String getAdmname() {
		return admname;
	}
	public void setAdmname(String admname) {
		this.admname = admname;
	}
	public String getAdmpwd() {
		return admpwd;
	}
	public void setAdmpwd(String admpwd) {
		this.admpwd = admpwd;
	}
	public String getAdmtel() {
		return admtel;
	}
	public void setAdmtel(String admtel) {
		this.admtel = admtel;
	}
	public AdmInfo(int admid, String admname, String admpwd, String admtel) {
		super();
		this.admid = admid;
		this.admname = admname;
		this.admpwd = admpwd;
		this.admtel = admtel;
	}
	public AdmInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
