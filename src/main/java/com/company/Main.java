package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        do {
            System.out.print("Введите выражение(можно использовать функции: cos, sin tan, ctg, log(натуральный лог.) и одно символьные переменные): \n >"); //запрашиваем выражение
            String str = in.nextLine(); //считываем выражение
            System.out.println("Результат: " + Calculate.calculate(str));
            System.out.print("Введите e для выхода из программы, либой другой символ для повторения: ");
        } while (in.nextLine().compareTo("e") != 0); //Если необходимо, начинаем новый цикл
    }
}