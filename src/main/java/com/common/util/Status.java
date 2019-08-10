package com.common.util;

public enum Status {
	New(1),
	
	Modified(2),
	
	Delete(3);
	
	private int stat;
	
	private Status(int stat) {
		this.stat = stat;
	}
	
	public int getStatus() {
		return stat;
	}

}
