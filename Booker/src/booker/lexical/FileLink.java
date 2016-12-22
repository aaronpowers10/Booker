package booker.lexical;

/**
 * @author Aaron Powers
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileLink implements MoreCharactersSource {

	private BufferedReader in;

	public FileLink(String fileName) {

		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String nextChar() {
		try {
			int nextChar = in.read();
			if (nextChar == -1) {
				throw new EndOfSequenceException("The end of the file was reached.");
			} else {
				return Character.toString((char) nextChar);
			}
		} catch (IOException e) {
			throw new EndOfSequenceException("The end of the file was reached.");
		}

	}

	public void close() throws IOException {
		in.close();
	}

}
