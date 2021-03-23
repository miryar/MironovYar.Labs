package com.company;
import static com.company.Point3d.*;

public class lab2 {

    public static void main(String[] args) {
        System.out.println("Введите первую точку: ");
        Point3d first = vvod();
        System.out.println("Введите вторую точку: ");
        Point3d second = vvod();
        System.out.println("Введите третью точку: ");
        Point3d third = vvod();
        if (dcheck(first,second) || dcheck(second,third) || dcheck(first,third))
        {
            System.out.println("Две или три точки равны между собой");
        }
        else {
            System.out.println("Отрезок между первой и второй точкой равен: "+distanceTo(first,second));
            System.out.println("Отрезок между второй и третьей точкой равен: "+distanceTo(second,third));
            System.out.println("Отрезок между третьей и первой точкой равен: "+distanceTo(first,third));
            System.out.println("Площадь треугольника: "+computeArea(first, second, third));
        }

    }
}

