package domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Review implements Serializable {
	@Id
	@GeneratedValue
	private int idx;
	private String buyerName;
	private int score;
	private String comment;
	private String date;
	
	public Review() {
		super();
	}
	
	public Review(String buyerName, int score, String comment, String date) {
		this.buyerName = buyerName;
		this.score = score;
		this.comment = comment;
		this.date = date;
	}
	
	public String getBuyerName() {
		return buyerName;
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
	
	@Override
	public String toString() {
		return "[Buyer: " + buyerName + " | Score: " + score + " | Comment: " + comment + " | Date: " + date + "]";
	}
}
