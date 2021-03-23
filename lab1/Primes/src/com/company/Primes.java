package com.company;

public class Primes {
    public static boolean isPrime(int n) //метод для поиска простого числа
    {
        if (n == 2) { // так как мы начинаем с двух и число 2 является простым ставим ему значение true
            return true;
        }
        if (n % 2 == 0) {// отметаем все числа которые делятся на 2
            return false;
        }
        for (int i = 3; i <= Math.sqrt(n) + 1; i = i + 2) {// делим n на все числа от 3х до корня n с шагом два
            if (n % i == 0) {
                return false;
            }
        }
        return true;//возвращаем true, если число прошло все проверки
    }
    public static void main(String[] args) {
        for (int n =2;n<=100;n+=+1){//прокручиваем все цифры от 2 до 100
            if (isPrime(n)) System.out.print(n+" ");//если число простое, оно выписывается
        }

    }

}