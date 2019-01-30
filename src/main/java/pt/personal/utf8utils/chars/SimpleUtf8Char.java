package pt.personal.utf8utils.chars;

public class SimpleUtf8Char implements Utf8Char {
	
	byte[] bytes;
	
	public SimpleUtf8Char(byte[] bytes) {
		this.bytes = bytes;
	}

	@Override
	public byte[] getBytes() {
		return bytes;
	}

}
