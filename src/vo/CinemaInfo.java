package vo;
/**
 * 影院信息类
 * @author admin
 *
 */
public class CinemaInfo {
	private int cinemaid;
	private String cinemaname;
	private String cinematel;
	private String cinemaaddr;
	private String cinemaintro;
	private String cinemaimg;
	private String cinemapwd;
	private double lowPrice;
	private double state;

	public double getState() {
		return state;
	}

	public void setState(double state) {
		this.state = state;
	}

	public double getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(double lowPrice) {
		this.lowPrice = lowPrice;
	}

	public int getCinemaid() {
		return cinemaid;
	}

	public void setCinemaid(int cinemaid) {
		this.cinemaid = cinemaid;
	}

	public String getCinemaname() {
		return cinemaname;
	}

	public void setCinemaname(String cinemaname) {
		this.cinemaname = cinemaname;
	}

	public String getCinematel() {
		return cinematel;
	}

	public void setCinematel(String cinematel) {
		this.cinematel = cinematel;
	}

	public String getCinemaaddr() {
		return cinemaaddr;
	}

	public void setCinemaaddr(String cinemaaddr) {
		this.cinemaaddr = cinemaaddr;
	}

	public String getCinemaintro() {
		return cinemaintro;
	}

	public void setCinemaintro(String cinemaintro) {
		this.cinemaintro = cinemaintro;
	}

	public String getCinemaimg() {
		return cinemaimg;
	}

	public void setCinemaimg(String cinemaimg) {
		this.cinemaimg = cinemaimg;
	}

	public String getCinemapwd() {
		return cinemapwd;
	}

	public void setCinemapwd(String cinemapwd) {
		this.cinemapwd = cinemapwd;
	}


	public CinemaInfo() {
	}

	public CinemaInfo(int cinemaid, String cinemaname, String cinematel, String cinemaaddr, String cinemaintro, String cinemaimg, String cinemapwd, double lowPrice, double state) {
		this.cinemaid = cinemaid;
		this.cinemaname = cinemaname;
		this.cinematel = cinematel;
		this.cinemaaddr = cinemaaddr;
		this.cinemaintro = cinemaintro;
		this.cinemaimg = cinemaimg;
		this.cinemapwd = cinemapwd;
		this.lowPrice = lowPrice;
		this.state = state;
	}

	@Override
	public String toString() {
		return "CinemaInfo{" +
				"cinemaid=" + cinemaid +
				", cinemaname='" + cinemaname + '\'' +
				", cinematel='" + cinematel + '\'' +
				", cinemaaddr='" + cinemaaddr + '\'' +
				", cinemaintro='" + cinemaintro + '\'' +
				", cinemaimg='" + cinemaimg + '\'' +
				", cinemapwd='" + cinemapwd + '\'' +
				", lowPrice=" + lowPrice +
				", state=" + state +
				'}';
	}
}
