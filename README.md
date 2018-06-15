java_codegen is a preprocessor for Java source code.

You add special line comments opened by  `// [$ ... $] ` or  `//[$ ... $] ` and closed by `// $$` or `//$$` into to the code, then run the preprocessor.

There is already a tool [ExprPreprocessor](https://github.com/axkr/symja_android_library/blob/master/symja_android_library/tools/src/main/java/org/matheclipse/core/preprocessor/ExprPreprocessor.java) 
which converts Symja expressions in Java line comments opened by  `// [$ ... $] ` or  `//[$ ... $] ` and closed by `// $$` or `//$$` into Java source code.

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


```
Input qualified Java file for converting Symja expressions to Java source
▶ /symja_android_library/matheclipse-core/src/main/java/org/matheclipse/core/reflection/system/TrigToExp.java
```
