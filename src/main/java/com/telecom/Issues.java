package com.telecom;

import java.io.Serializable;
import java.util.List;

/**
 * This class was automatically generated by the data modeler tool.
 */

public class Issues implements Serializable {

	static final long serialVersionUID = 1L;

	private List<Issue> list;

	private int lastIssueId;

	public Issues() {
	}

	public List<Issue> getList() {
		return this.list;
	}

	public void setList(List<Issue> list) {
		this.list = list;
	}

	public void create(Issue issue) {
		list.add(issue.getIssueId(), issue);
	}

	public void delete(Issue issue) {
		list.remove(issue);
		this.lastIssueId--;
	}

	public int getLastIssueId() {
		return this.lastIssueId;
	}

	public int newIssueId() {
	    this.lastIssueId++;
		return this.lastIssueId;
	}

	public void setLastIssueId(int lastIssueId) {
		this.lastIssueId = lastIssueId;
	}

	public Issues(List<Issue> list, int lastIssueId) {
		this.list = list;
		this.lastIssueId = lastIssueId;
	}

}