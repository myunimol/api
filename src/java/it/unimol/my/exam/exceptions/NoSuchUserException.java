package it.unimol.my.exam.exceptions;

public class NoSuchUserException extends Exception {

	private static final long serialVersionUID = -920593234209428234L;

	public NoSuchUserException(String username) {
		super("No "+username+" user or wrong credentials entered.");
	}
}
