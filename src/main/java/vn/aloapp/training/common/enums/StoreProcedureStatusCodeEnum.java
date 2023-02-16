package vn.aloapp.training.common.enums;


public enum StoreProcedureStatusCodeEnum {
	SUCCESS(0),
	FAIL(1),
	INPUT_INVALID(2),
	ALREADY_EXITS(3);

	private final int value;

	private StoreProcedureStatusCodeEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static StoreProcedureStatusCodeEnum valueOf(int value) {
		switch (value) {
		case 0:
			return SUCCESS;
		case 2:
			return INPUT_INVALID;
		case 3:
			return ALREADY_EXITS;
		default:
			return FAIL;
		}
	}
}