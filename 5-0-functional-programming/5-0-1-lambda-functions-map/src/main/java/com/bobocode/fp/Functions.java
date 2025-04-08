package com.bobocode.fp;

/**
 * An util class that provides a factory method for creating an instance of a {@link FunctionMap} filled with a list
 * of functions.
 * <p>
 * <p>
 * @author Taras Boychuk
 */
public class Functions {
    private Functions() {
    }

    /**
     * A static factory method that creates an integer function map with basic functions:
     * - abs (absolute value)
     * - sgn (signum function)
     * - increment
     * - decrement
     * - square
     *
     * @return an instance of {@link FunctionMap} that contains all listed functions
     */
    public static FunctionMap<Integer, Integer> intFunctionMap() {
        FunctionMap<Integer, Integer> intFunctionMap = new FunctionMap<>();
        intFunctionMap.addFunction("abs", Math::abs);
        intFunctionMap.addFunction("sgn", a -> (int) Math.signum(a));
        intFunctionMap.addFunction("increment", a -> ++a);
        intFunctionMap.addFunction("decrement", a -> --a);
        intFunctionMap.addFunction("square", a -> a * a);
        return intFunctionMap;
    }
}
