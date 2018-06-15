package org.matheclipse.tools;

/**
 * Convert expressions in line comments opened by <code>// [$ ... $]</code> and closed by <code>// $$</code> into Java
 * source code. The closing tag can be followed by postfix characters which are appended after the generated source
 * code.
 * 
 */
public class HelloWorldExample extends AbstractCodeGenerator {

	public final static String HELLO_WORLD1 = //
			// [$ hello world 1 $]
			"test 1"; // $$;

	public final static String HELLO_WORLD2 = //
			// [$ hello world 2 $]
			"test 2"; // $$;

	public HelloWorldExample() {
		super();
	}

	public static void main(String[] args) {
		System.out.println("Input qualified Java file name for converting text to upper case strings");
		System.out.println("In Eclipse do a right mouse click on a Java file and select menu \"Copy qualified name\"");
		AbstractCodeGenerator epp = new HelloWorldExample();

		runConsole(epp);
	}

	public boolean apply(String command, StringBuilder buf) {
		command = command.trim();
		buf.append("\"" + command.toUpperCase() + "\"");
		return true;
	}
}
