package com.fight.errorHnadling;

import java.util.Optional;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public final class GameExceptions extends RuntimeException {

	private static final long serialVersionUID = 6112749064896914454L;

	public static final String DEFAULT_MSG = "Server Error.Please contact administrator";


	private final ErrorCode errorCode;
	private final transient Optional<Throwable> throwable;

	public GameExceptions(ErrorCode errorCode, String message, Optional<Throwable> throwable) {
		super(message);
		this.errorCode = errorCode;
		this.throwable = throwable;
	}

	@Override
	public String toString() {
		return "GameExceptions{" +
				"errorCode=" + errorCode +
				'}';
	}
}
