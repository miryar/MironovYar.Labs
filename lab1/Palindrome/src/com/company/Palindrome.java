package com.company;
import java.util.Scanner;//импортируем сканер, чтобы вводить слова

public class Palindrome {

    public static String reverseString(String word){ // метод для переворачивания строки
        String revword="";
        for (int i=word.length()-1;i>=0;i=i-1)//перебираем числа от длинны строки-1(так как счет элементов строки идет не от 1, а от нуля) до нуля включительно
        {
            revword+=word.charAt(i);//подставляем символ строки номера того числа на котором сейчас перебор к нашему перевернутому слову
        }
        return revword;
    }
    public static boolean isPalindrome(String word)//проверяем является ли строка палиндромом
    {
        String revword = reverseString(word);
        if (word.equals(revword)) return true;//проверяем равна ли строка своей перевернутой копии
        else return false;
    }
    public static void main(String[] args) {

            Scanner vvod = new Scanner(System.in);//задаем сканер
            String word = vvod.nextLine();//вводим данные с клавиатуры
            boolean palcheck = isPalindrome(word);//проверяем является ли слово палиндромом
            if (palcheck)//выдаем результат
            {
                System.out.println("Строка " + word + " является палиндромом");
                System.out.println(word + " " + reverseString(word));
            } else {
                System.out.println("Строка " + word + " не является палиндромом");
                System.out.println(word + " " + reverseString(word));
            }


    }
}