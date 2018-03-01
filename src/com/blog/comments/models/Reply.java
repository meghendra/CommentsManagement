package com.blog.comments.models;

public class Reply {

	private Integer replyId;
	private Integer parentCommentId;
	private Integer childCommentId;
	
	/**
	 * 
	 */
	public Reply() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param replyId
	 * @param parentCommentId
	 * @param childCommentId
	 */
	public Reply(Integer replyId, Integer parentCommentId, Integer childCommentId) {
		super();
		this.replyId = replyId;
		this.parentCommentId = parentCommentId;
		this.childCommentId = childCommentId;
	}
	
	/**
	 * @return the replyId
	 */
	public Integer getReplyId() {
		return replyId;
	}
	
	/**
	 * @param replyId the replyId to set
	 */
	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}
	
	/**
	 * @return the parentCommentId
	 */
	public Integer getParentCommentId() {
		return parentCommentId;
	}
	
	/**
	 * @param parentCommentId the parentCommentId to set
	 */
	public void setParentCommentId(Integer parentCommentId) {
		this.parentCommentId = parentCommentId;
	}
	
	/**
	 * @return the childCommentId
	 */
	public Integer getChildCommentId() {
		return childCommentId;
	}
	
	/**
	 * @param childCommentId the childCommentId to set
	 */
	public void setChildCommentId(Integer childCommentId) {
		this.childCommentId = childCommentId;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Reply [replyId=" + replyId + ", parentCommentId=" + parentCommentId + ", childCommentId="
				+ childCommentId + "]";
	}
	
	
}
