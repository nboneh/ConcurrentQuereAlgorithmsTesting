package com.concurrency.queue;

public class EmptyQueueException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyQueueException () {

	}

	public EmptyQueueException (String message) {
		super (message);
	}

	public EmptyQueueException (Throwable cause) {
		super (cause);
	}

	public EmptyQueueException (String message, Throwable cause) {
		super (message, cause);
	}

}
