package models;

import java.sql.Timestamp;

public class Reimbursement {

	private int reimbId;
	private double amount;
	private Timestamp timeSubmitted;
	private Timestamp timeResolved;
	private String description;
	private String authorId;
	private String resolverId;
	private int statusId;
	private int typeId;
	
	public Reimbursement() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Reimbursement(int reimbId, int statusId) {
		super();
		this.reimbId = reimbId;
		this.statusId = statusId;
	}



	public Reimbursement(int reimbId, double amount, Timestamp timeSubmitted, Timestamp timeResolved,
			String description, String authorId, String resolverId, int statusId, int typeId) {
		super();
		this.reimbId = reimbId;
		this.amount = amount;
		this.timeSubmitted = timeSubmitted;
		this.timeResolved = timeResolved;
		this.description = description;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.statusId = statusId;
		this.typeId = typeId;
	}

	public Reimbursement(double amount, Timestamp timeSubmitted, Timestamp timeResolved, String description,
			String authorId, String resolverId, int statusId, int typeId) {
		super();
		this.amount = amount;
		this.timeSubmitted = timeSubmitted;
		this.timeResolved = timeResolved;
		this.description = description;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.statusId = statusId;
		this.typeId = typeId;
	}

	public Reimbursement(int reimbId, double amount, Timestamp timeSubmitted, String authorId, int statusId, int typeId) {
		super();
		this.reimbId = reimbId;
		this.amount = amount;
		this.timeSubmitted = timeSubmitted;
		this.authorId = authorId;
		this.statusId = statusId;
		this.typeId = typeId;
	}

	public Reimbursement(double amount, Timestamp timeSubmitted, String authorId, int statusId, int typeId) {
		super();
		this.amount = amount;
		this.timeSubmitted = timeSubmitted;
		this.authorId = authorId;
		this.statusId = statusId;
		this.typeId = typeId;
	}

	public Reimbursement(int reimbId, double amount, Timestamp timeSubmitted, String description, String authorId,
			int statusId, int typeId) {
		super();
		this.reimbId = reimbId;
		this.amount = amount;
		this.timeSubmitted = timeSubmitted;
		this.description = description;
		this.authorId = authorId;
		this.statusId = statusId;
		this.typeId = typeId;
	}

	public Reimbursement(double amount, Timestamp timeSubmitted, String description, String authorId, int statusId,
			int typeId) {
		super();
		this.amount = amount;
		this.timeSubmitted = timeSubmitted;
		this.description = description;
		this.authorId = authorId;
		this.statusId = statusId;
		this.typeId = typeId;
	}
	
	public Reimbursement(double amount, String description, String authorId, int statusId, int typeId) {
		super();
		this.amount = amount;
		this.description = description;
		this.authorId = authorId;
		this.statusId = statusId;
		this.typeId = typeId;
	}

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getTimeSubmitted() {
		return timeSubmitted;
	}

	public void setTimeSubmitted(Timestamp timeSubmitted) {
		this.timeSubmitted = timeSubmitted;
	}

	public Timestamp getTimeResolved() {
		return timeResolved;
	}

	public void setTimeResolved(Timestamp timeResolved) {
		this.timeResolved = timeResolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getResolverId() {
		return resolverId;
	}

	public void setResolverId(String resolverId) {
		this.resolverId = resolverId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", amount=" + amount + ", timeSubmitted=" + timeSubmitted
				+ ", timeResolved=" + timeResolved + ", description=" + description + ", authorId=" + authorId
				+ ", resolverId=" + resolverId + ", statusId=" + statusId + ", typeId=" + typeId + "]";
	}
	
}
