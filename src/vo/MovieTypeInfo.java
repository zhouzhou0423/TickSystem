package vo;
/**
 * 电影类型类
 * @author admin
 *
 */
public class MovieTypeInfo {
	private int typeid;
	private String type;
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public MovieTypeInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MovieTypeInfo(int typeid, String type) {
		super();
		this.typeid = typeid;
		this.type = type;
	}
	
}
