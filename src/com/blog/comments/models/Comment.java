package com.blog.comments.models;

import java.util.Date;

public class Comment {

	private Integer commentId;
	private Integer articleId;
	private String userName;
	private String timeStamp;
	private String content;
	private Integer parentId;
	private Integer numberOfLikes;
	private Integer isDeleted;
	private Integer timesReported;
	
	/**
	 * default constructor
	 */
	public Comment() {
		super();
	}

	/**
	 * @param commentId
	 * @param userName
	 * @param timeStamp
	 * @param content
	 * @param parentId
	 * @param numberOfLikes
	 * @param isDeleted
	 * @param timesReported
	 */
	public Comment(Integer commentId, Integer articleId, String userName, String timeStamp, String content, Integer parentId,
			Integer numberOfLikes, Integer isDeleted, Integer timesReported) {
		super();
		this.articleId = articleId;
		this.commentId = commentId;
		this.userName = userName;
		this.timeStamp = timeStamp;
		this.content = content;
		this.parentId = parentId;
		this.numberOfLikes = numberOfLikes;
		this.isDeleted = isDeleted;
		this.timesReported = timesReported;
	}

	/**
	 * @return the commentId
	 */
	public Integer getCommentId() {
		return commentId;
	}

	/**
	 * @param commentId the commentId to set
	 */
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	/**
	 * @return the articleId
	 */
	public Integer getArticleId() {
		return articleId;
	}

	/**
	 * @param articleId the articleId to set
	 */
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the timeStamp
	 */
	public String getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the parentId
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the numberOfLikes
	 */
	public Integer getNumberOfLikes() {
		return numberOfLikes;
	}

	/**
	 * @param numberOfLikes the numberOfLikes to set
	 */
	public void setNumberOfLikes(Integer numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}

	/**
	 * @return the isDeleted
	 */
	public Integer getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * @return the timesReported
	 */
	public Integer getTimesReported() {
		return timesReported;
	}

	/**
	 * @param timesReported the timesReported to set
	 */
	public void setTimesReported(Integer timesReported) {
		this.timesReported = timesReported;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", userName=" + userName + ", timeStamp=" + timeStamp + ", content="
				+ content + ", parentId=" + parentId + ", numberOfLikes=" + numberOfLikes + ", isDeleted=" + isDeleted
				+ ", timesReported=" + timesReported + "]";
	}
	
	
	
}