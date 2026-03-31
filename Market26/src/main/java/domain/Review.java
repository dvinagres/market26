package domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Review implements Serializable {
	@Id
	@GeneratedValue
	private int idx;
	private int score;
	private String comment;
	private String date;
	
	public Review() {
		super();
	}
	
	public Review(int score, String comment, String date) {
		this.score = score;
		this.comment = comment;
		this.date = date;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
}
