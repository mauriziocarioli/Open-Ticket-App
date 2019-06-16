package com.telecom.cep;

import java.util.LinkedList;
import java.util.List;

public class LinkListStack {
	LinkedList<AlertEvent> li = new LinkedList<AlertEvent>();

	public void push(AlertEvent data) {
		li.add(data);
	}

	public void pushAll(List<AlertEvent> data) {
		li.addAll(data);
	}

	public AlertEvent poll() {
		while (!li.isEmpty()) {
			return li.poll();
		}
		return null;
	}

	public void displayStack() {
		System.out.println("garv  ");
		li.peek();
		li.remove();
	}
}
