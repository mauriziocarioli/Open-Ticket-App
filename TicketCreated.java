package com.telecom;

import java.io.Serializable;

/**
 * This class was automatically generated by the data modeler tool.
 */

public class TicketCreated implements Serializable {

	static final long serialVersionUID = 1L;

	private Integer issueId;

	public TicketCreated() {
	}

	public Integer getIssueId() {
		return this.issueId;
	}

	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}

	public TicketCreated(Integer issueId) {
		this.issueId = issueId;
	}

}