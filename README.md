## java_codegen - a simple preprocessor for Java source code.

The [axkr/java_codegen](https://github.com/axkr/java_codegen) Github project contains a simple preprocessor tool to expand ordinary strings into Java source code.

The strings are included into Java line comments opened by `// [$ ... $]` or `//[$ ... $]` and closed by `// $$` or `//$$`. The preprocessor program takes this prepared files and substitutes the comments by generated source code. 

### Simple Uppercase Example

There is a simple example [org.matheclipse.tools.HelloWorldExample](https://github.com/axkr/java_codegen/blob/master/java_codegen/java_codegen/src/org/matheclipse/tools/HelloWorldExample.java) which converts the following lines into upper case strings:

```
public class HelloWorldExample extends AbstractCodeGenerator {

	public final static String HELLO_WORLD1 = //
			// [$ hello world 1 $]
			"test 1"; // $$;

	public final static String HELLO_WORLD2 = //
			// [$ hello world 2 $]
			"test 2"; // $$;
```

to these lines:

```
public class HelloWorldExample extends AbstractCodeGenerator {

	public final static String HELLO_WORLD1 = //
			// [$ hello world 1 $]
"HELLO WORLD 1"; // $$;

	public final static String HELLO_WORLD2 = //
			// [$ hello world 2 $]
"HELLO WORLD 2"; // $$;
```

**Note:** the `;` character behind the closing `// $$` tag will be appended to the generated source code.

After running the `HelloWorldExample` you can use the Eclipse menu "Copy qualified name" for the file which you would like to convert:

```
Input qualified Java file name for converting text to upper case strings
▶ /java_codegen/src/org/matheclipse/tools/HelloWorldExample.java
```

For implementing your own conversion you have to implement the abstract `apply()` method:

```
	public boolean apply(String command, StringBuilder buf) {
		command = command.trim();
		buf.append("\"" + command.toUpperCase() + "\"");
		return true;
	}
```
### Symja math expression code generation

There is already a more sophisticated tool [ExprPreprocessor](https://github.com/axkr/symja_android_library/blob/master/symja_android_library/tools/src/main/java/org/matheclipse/core/preprocessor/ExprPreprocessor.java) 
 in the [Symja project](https://github.com/axkr/symja_android_library), which converts math expressions into Java source code.
 Because Java doesn't support operator overloading the tool simplifies the maintenance of large math expressions.

In the example from the [FunctionExpand](https://github.com/axkr/symja_android_library/blob/master/symja_android_library/matheclipse-core/src/main/java/org/matheclipse/core/reflection/system/FunctionExpand.java) 
function the Symja expression `Gamma(1+x)` 

```
MATCHER.caseOf(Factorial(x_), //
          // [$ Gamma(1+x) $]
          F.Null); // $$);
```

will be converted into special Symja Java source code

```
MATCHER.caseOf(Factorial(x_), //
          // [$ Gamma(1+x) $]
          F.Gamma(F.Plus(F.C1, x))); // $$);
```
