package com.company;

import com.sun.jdi.CharType;

import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Math.*;

public class Calculate {

    public static double calculate(String str) {
        if (checkStr(str)) { //проверяем выражение на коректность
            HashMap<Character, Double> variables = new HashMap<>();
            return countStr(str, variables); //считаем и выводим ответ
        }
        return -1;
    }

    private static double countStr(String str, HashMap<Character, Double> variables) {
        int pos = findFirstMot(str); //получаем позицию самой НЕ приоритетной операции
        if (pos == -1) // строка состоит из выражения в скобка или это число
            if (str.charAt(0) == '(' && str.charAt(str.length() - 1) == ')') //если выражение состоит из скобок
                return countStr(str.substring(1, str.length() - 1), variables); //считаем внутри скобок
            else
                if (str.length() > 4)
                {
                    switch (str.substring(0, 4)) {
                        case "sin(":
                            return sin(countStr(str.substring(4, str.length() - 1), variables));
                        case "cos(":
                            return cos(countStr(str.substring(4, str.length() - 1), variables));
                        case "tan(":
                            return tan(countStr(str.substring(4, str.length() - 1), variables));
                        case "ctg(":
                            return atan(countStr(str.substring(4, str.length() - 1), variables));
                        case "log(":
                            return log(countStr(str.substring(4, str.length() - 1), variables));
                        default:
                            return getVariable(str, variables);
                    }
                }
                else
                    return getVariable(str, variables); // если внутри число
        else // если найдена операция, то выполняем ее
            switch (str.charAt(pos)) {
                case '+':
                    return countStr(str.substring(0, pos), variables) + countStr(str.substring(pos + 1), variables);
                case '-':
                    return countStr(str.substring(0, pos), variables) - countStr(str.substring(pos + 1), variables);
                case '*':
                    return countStr(str.substring(0, pos), variables) * countStr(str.substring(pos + 1), variables);
                case '/':
                    return countStr(str.substring(0, pos), variables) / countStr(str.substring(pos + 1), variables);
            }
        return -1;
    }

    private static int findFirstMot(String str) {
        int cnt = 0; // счетчик скобок
        int pos = -1, priority = 100; //pos - позиция элемента, priority - наименьший приоритет
        for (int i = str.length()-1; i >= 0; i--) // проходимся по всему выржению
            if (str.charAt(i) == '(')
                cnt++;
            else if (str.charAt(i) == ')')
                cnt--;
            else if (cnt == 0) // если мы сейчас не в скобка, то обрабатываем символ
                if (priori(str.charAt(i)) < priority)//если оператор имеее меньший приоритет, то обрабатываем его
                    if (str.charAt(i) == '+' || str.charAt(i) == '-') //если это минус или плюс, то они имеют наименьший прир. и дальше искать нет необходимости
                        return i;
                    else {
                        pos = i;
                        priority = priori(str.charAt(i));
                    }
        return priority == 100 ? -1 : pos; //если нет оперторов или все выражение находится в скобка возвращаем -1, иначе pos
    }

    /*
    проверяет строку на коректность
    */
    private static boolean checkStr(String str) {
        int n = str.length();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) == '(') { // если перед скобкой стоит не скобка или не другая скобка - это некоректно.
                cnt++;
            } else if (str.charAt(i) == ')') {// если после скобки стоит не скобка или не другая скобка - это некоректно.
                cnt--;
            }
        }

        if (cnt != 0) { //в выражении неправильное кол-во скобок
            System.out.println("Не правильное кол-во скобок.");
            return false;
        }
        return true;
    }

    /*
    возвращает приоритет операции
    */
    private static int priori(char mot) {
        switch (mot) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 101; // для всего остального
        }
    }

    private static double getVariable(String str, HashMap<Character, Double> variables) {
        if(str.length() ==1) {
            char c =str.charAt(0);
            if(c >= '0' && c <= '9')
                return Double.parseDouble(str);
            if (variables.containsKey(c)) return variables.get(c);
            else {
                System.out.print("Введите переменную " + c + ":");
                Scanner in = new Scanner(System.in);
                double val = in.nextDouble();
                variables.put(c, val);
                return val;
            }
        }
        else
            return Double.parseDouble(str);
    }
}
