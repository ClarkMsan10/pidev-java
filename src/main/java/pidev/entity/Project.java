package pidev.entity;

import java.sql.Date;

public class Project {

	private int id;
	private String title;
	private String slug;
	private String description;
	private int budget;
	private int estimation;
	private Date createdAt;
	private User publishedBy;
	private int votes;
	
	public Project() {
		super();
	}
	
	
	public Project(int id, String title, String description, int budget, int estimation, String slug) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.budget = budget;
		this.estimation = estimation;
		
		java.util.Date p = new java.util.Date();
		
		this.createdAt =  new Date(p.getTime());
		this.slug = slug;
	}
	
	


	public Project(String title, String description, int budget, int estimation, User publishedBy, String slug) {
		super();
		this.title = title;
		this.description = description;
		this.budget = budget;
		this.estimation = estimation;
		java.util.Date p = new java.util.Date();
		this.createdAt =  new Date(p.getTime());
		this.publishedBy = publishedBy;
		this.slug = slug;
		this.votes = 0;
	}
	
	


	public Project(int id, String title, String description, int budget, int estimation, Date createdAt, String slug) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.budget = budget;
		this.estimation = estimation;
		this.createdAt = createdAt;
		this.slug = slug;
	}
	
	
	
	
	
	public Project(int id, String title, String description, int budget, int estimation, Date createdAt, int votes) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.budget = budget;
		this.estimation = estimation;
		this.createdAt = createdAt;
		this.votes = votes;
	}


	public Project(int id, String title, String description, int budget, int estimation, Date createdAt,
			User publishedBy, int votes) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.budget = budget;
		this.estimation = estimation;
		this.createdAt = createdAt;
		this.publishedBy = publishedBy;
		this.votes = votes;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getBudget() {
		return budget;
	}


	public void setBudget(int budget) {
		this.budget = budget;
	}


	public int getEstimation() {
		return estimation;
	}


	public void setEstimation(int estimation) {
		this.estimation = estimation;
	}

	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public User getPublishedBy() {
		return publishedBy;
	}


	public void setPublishedBy(User publishedBy) {
		this.publishedBy = publishedBy;
	}
	

	public int getVotes() {
		return votes;
	}


	public void setVotes(int votes) {
		this.votes = votes;
	}
	
	
	


	public String getSlug() {
		return slug;
	}


	public void setSlug(String slug) {
		this.slug = slug;
	}


	@Override
	public String toString() {
		return "Project [id=" + id + ", title=" + title + ", description=" + description + ", budget=" + budget
				+ ", estimation=" + estimation + ", createdAt=" + createdAt + ", publishedBy=" + publishedBy + "]";
	}


	
	
	
	
}
