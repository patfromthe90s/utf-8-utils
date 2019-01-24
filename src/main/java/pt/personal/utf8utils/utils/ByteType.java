package pt.personal.utf8utils.utils;

/**
 * Enum to represent the various bytes found in UTF-8.
 * @author patrick
 *
 */
public enum ByteType {
	
	UTF_8_4, // 11110xxx
	UTF_8_3, // 1110xxxx
	UTF_8_2, // 110xxxxx
	UTF_8_1, // 0xxxxxxxx
	UTF_8_TAIL, // 10xxxxxx
	UNKNOWN;

}
