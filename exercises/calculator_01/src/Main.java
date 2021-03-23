/**
 * О калькуляторе: приложение должно читать из консоли введенные пользователем арифметические операции и выводить в консоль результат их выполнения;
 * Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с двумя числами: a + b, a - b, a * b, a / b. Данные передаются в одну строку! Решения, в которых каждое число и арифмитеческая операция передаются с новой строки считаются неверными.
 * 23:51
 * Калькулятор умеет работать как с арабскими (1,2,3,4,5…), так и с римскими (I,II,III,IV,V…) числами.
 * Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более. На выходе числа не ограничиваются по величине и могут быть любыми.
 * Калькулятор умеет работать только с целыми числами.
 * Калькулятор умеет работать только с арабскими или римскими цифрами одновременно, при вводе пользователем строки вроде 3 + II калькулятор должен выбросить исключение и прекратить свою работу.
 * При вводе римских чисел, ответ должен быть выведен римскими цифрами, соответственно, при вводе арабских - ответ ожидается арабскими.
 * При вводе пользователем неподходящих чисел приложение выбрасывает исключение и завершает свою работу.
 * При вводе пользователем строки, не соответствующей одной из вышеописанных арифметических операций, приложение выбрасывает исключение и завершает свою работу.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static Map<String, Method> methodsMap;
    public static Map<String, Integer> roman2arab;

    public static void initialize() {
        methodsMap = new HashMap<>();
        try {
            methodsMap.put("+", Main.class.getMethod("add", int.class, int.class));
            methodsMap.put("-", Main.class.getMethod("subtract", int.class, int.class));
            methodsMap.put("*", Main.class.getMethod("multiply", int.class, int.class));
            methodsMap.put("/", Main.class.getMethod("divide", int.class, int.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        roman2arab = new HashMap<>() {{
            put("I", 1);
            put("II", 2);
            put("III", 3);
            put("IV", 4);
            put("V", 5);
            put("VI", 6);
            put("VII", 7);
            put("VIII", 8);
            put("IX", 9);
            put("X", 10);
            put("XI", 11);
            put("XII", 12);
            put("XIII", 13);
            put("XIV", 14);
            put("XV", 15);
        }};
    }

    public static void main(String[] args) {
        initialize();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;

        try {
            line = reader.readLine();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        Triple triple = null;
        try {
            triple = parseLine(line);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        System.out.println(operation(triple));
    }

    public static int add(int a, int b) {
        return a+b;
    }

    public static int subtract(int a, int b) {
        return a-b;
    }

    public static int multiply(int a, int b) {
        return a*b;
    }

    public static double divide(int a, int b) {
        return (double) a/b;
    }

    public static Object operation(Triple triple) {
        Object result = null;

        try {
            checkInput(triple.number1, triple.number2);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.exit(3);
        }

        try {
            result = methodsMap.get(triple.sign).invoke(new Main(), triple.number1, triple.number2);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
            System.exit(1);
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
            System.exit(2);
        }

        if (triple.roman) {
            for (Map.Entry<String, Integer> entry : roman2arab.entrySet()){
                int value = entry.getValue();
                int goal = (int) result;
                if ( value == goal )
                    return entry.getKey();
            }
        }

        return result;
    }

    public static void checkInput(int a, int b) throws IllegalArgumentException {
        if (a < 1 || a > 10 || b < 1 || b > 10)
            throw new IllegalArgumentException("Числа из неверного диапазона.");
    }

    public static Triple parseLine(String line) throws NoSuchMethodException {
        String operation = null;
        int x, y;
        boolean isRoman;

        for (String temp : methodsMap.keySet()) {
            if (line.contains(temp)) {
                operation = temp;
            }
        }

        if (operation == null) {
            throw new NoSuchMethodException("Такой операции не существует.");
        }

        String[] elements = line.split("\\"+operation);
        String element1, element2;
        element1 = elements[0].trim();
        element2 = elements[1].trim();

        boolean firstRoman, secondRoman;
        firstRoman = roman2arab.containsKey(elements[0].trim());
        secondRoman = roman2arab.containsKey(elements[1].trim());

        if (firstRoman && secondRoman) {
            x = roman2arab.get(element1);
            y = roman2arab.get(element2);
            isRoman = true;
        }
        else if (firstRoman || secondRoman) {
            throw new IllegalArgumentException("Числа разных видов.");
        }
        else {
            x = Integer.parseInt(element1);
            y = Integer.parseInt(element2);
            isRoman = false;
        }

        return new Triple(operation, x, y, isRoman);
    }

    public static class Triple {
        String sign;
        int number1;
        int number2;
        boolean roman;

        public Triple(String sign, int number1, int number2, boolean roman) {
            this.sign = sign;
            this.number1 = number1;
            this.number2 = number2;
            this.roman = roman;
        }

    }

}
