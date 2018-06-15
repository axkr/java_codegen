java_codegen is a preprocessor for Java source code.

You add special line comments opened by  `// [$ ... $] ` or  `//[$ ... $] ` and closed by `// $$` or `//$$` into the code, then run the preprocessor.

There is an example [org.matheclipse.tools.HelloWorldExample](https://github.com/axkr/java_codegen/blob/master/java_codegen/java_codegen/src/org/matheclipse/tools/HelloWorldExample.java) which converts the follwing lines:

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

There is already a more sophisticated tool [ExprPreprocessor](https://github.com/axkr/symja_android_library/blob/master/symja_android_library/tools/src/main/java/org/matheclipse/core/preprocessor/ExprPreprocessor.java) 
 in the [Symja project](https://github.com/axkr/symja_android_library), which converts math expressions in Java line comments opened by 
 `// [$ ... $] ` or  `//[$ ... $] ` and closed by `// $$` or `//$$` into Java source code.

In the example from the [TrigToExp ](https://github.com/axkr/symja_android_library/blob/master/symja_android_library/matheclipse-core/src/main/java/org/matheclipse/core/reflection/system/TrigToExp.java) 
function the Symja expression `2/(E^x-E^(-x))` 

```
 MATCHER.caseOf(Csch(x_), //
  		x -> // [$ 2/(E^x-E^(-x)) $]
 		F.Null); // $$);
```

will be converted into Java source code
```
 MATCHER.caseOf(Csch(x_), //
  		x -> // [$ 2/(E^x-E^(-x)) $]
 		F.Times(F.C2, F.Power(F.Plus(F.Negate(F.Power(F.E, F.Negate(x))), F.Power(F.E, x)), -1))); // $$);
```


