package com.pzy.entity;
import java.util.Date;


/***
 * 分类
 *
 */
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/***
 * 成绩表
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_score")
public class Score {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	 @ManyToOne(fetch = FetchType.EAGER)
	private Category category;
	
	private Double score;
	
	private Double maxscore;
	
	private String degree;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	private Date createDate;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Double getMaxscore() {
		return maxscore;
	}

	public void setMaxscore(Double maxscore) {
		this.maxscore = maxscore;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	
	
}