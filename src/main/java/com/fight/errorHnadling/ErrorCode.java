package com.fight.errorHnadling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCode {
	DEFAULT_ERROR(10),
	WALK_ONLY_ON_MAP(11),
	SAVING_FAILED(12),
	LOADING_FAILED(13);

	private int errCode;

}
