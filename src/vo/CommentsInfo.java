package vo;

import java.sql.Timestamp;

public class CommentsInfo {
	private int commentid;
	private int movieid;
	private int userid;
	private String username;
	private String comment;
	private Timestamp time;
	private int grade;
	private int zancount;
	private String moviename;

	public String getMoviename() {
		return moviename;
	}

	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}

	public int getCommentid() {
		return commentid;
	}

	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}

	public int getMovieid() {
		return movieid;
	}

	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}

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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getZancount() {
		return zancount;
	}

	public void setZancount(int zancount) {
		this.zancount = zancount;
	}

	public CommentsInfo() {
	}

	public CommentsInfo(int commentid, int movieid, int userid, String username, String comment, Timestamp time, int grade, int zancount, String moviename) {
		this.commentid = commentid;
		this.movieid = movieid;
		this.userid = userid;
		this.username = username;
		this.comment = comment;
		this.time = time;
		this.grade = grade;
		this.zancount = zancount;
		this.moviename = moviename;
	}
}
