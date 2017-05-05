package com.wesdm.springmvc.spittr;

public class SpittleNotFoundException extends RuntimeException {
	private long spittleId;

	public SpittleNotFoundException(long spittleId) {
		this.spittleId = spittleId;
	}

	public long getSpittleId() {
		return spittleId;
	}
}
