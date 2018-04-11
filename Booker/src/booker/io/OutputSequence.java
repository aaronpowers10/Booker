/*
 *
 *  Copyright (C) 2017 Aaron Powers
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package booker.io;

import java.util.Scanner;

public class OutputSequence {

	private StringBuilder output;

	public OutputSequence() {
		output = new StringBuilder();
	}

	public void write(String string) {
		Scanner in = new Scanner(string);
		in.useDelimiter("");
		while (in.hasNext()) {
			output.append(in.next());
		}
		in.close();
	}

	public void insert(int offset, String string) {
		output.insert(offset, string);
	}

	public int length() {
		return output.length();
	}

	public int column() {
		int lastLineBreak = output.lastIndexOf(System.lineSeparator());
		if (lastLineBreak == -1) {
			return length() + 1;
		} else {
			return length() - lastLineBreak - 1;
		}
	}

	public String getString() {
		return output.toString();
	}

}
