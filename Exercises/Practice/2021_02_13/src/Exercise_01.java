// любой java класс должен называться также как и файл
// слово public указывает на модификатор доступа
public class Exercise_01 {

  // void указывает на то, что функция main ничего не возвращает
  public static void main(String[] args) {
    /*
    * А) Создайте переменную типа с плавающей точкой с наибольшим диапазоном чисел.
    * Присвойте ей произвольное дробное значение.
    */

    // буква 'd' в конце числа указывает на тип double
    // https://www.examclouds.com/ru/java/java-core-russian/literals-russian
    double variable1 = 4.5d;
    // можно и так double variable1 = 4.5;

    /*
    * Б) Создайте переменную целочисленного типа с наименьшим диапазоном чисел.
    * Присвойте  ей наибольшее возможное значение.
    * */

    // максимальное значение типа float хранится в классе Float
    // Float.MAX_VALUE
    float variable2 = Float.MAX_VALUE;

    // выводит значения переменных на экран
    System.out.println(variable1);
    System.out.println(variable2);

  }
}
