package org.shop.model.vo;

public enum Status {

	ON_SALE("ON_SALE"),
	OUT_OF_ORDER("OUT_OF_ORDER");

	public String getVal() {
		return val;
	}

	private final String val;

	Status(String val) {
		this.val = val;
	}
}
