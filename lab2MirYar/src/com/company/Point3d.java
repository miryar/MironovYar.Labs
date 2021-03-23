package com.company;
import java.util.Scanner;

public class Point3d {
    private double xCoord;
    private double yCoord;
    private double zCoord;
    public Point3d ( double x, double y,double z)
    {
        xCoord = x;
        yCoord = y;
        zCoord = z;
    }
    public Point3d() {
        this(0, 0,0);
    }
    public double getx () {
        return xCoord;
    }
    public double gety () {
        return yCoord;
    }
    public double getz () {
        return zCoord;
    }
    public void setX ( double val) {
        xCoord = val;
    }
    public void sety ( double val) {
        yCoord = val;
    }
    public void setz ( double val) {
        zCoord = val;
    }

    public static boolean dcheck(Point3d First,Point3d Second)
    {
        double x1=First.getx();
        double y1=First.gety();
        double z1=First.getz();
        double x2=Second.getx();
        double y2=Second.gety();
        double z2=Second.getz();
        if (x1==x2 && y1==y2 && z1==z2) return true;
        return false;
    }

    public static double distanceTo(Point3d First,Point3d Second )
    {
        double x1=First.getx();
        double y1=First.gety();
        double z1=First.getz();
        double x2=Second.getx();
        double y2=Second.gety();
        double z2=Second.getz();
        double formula= Math.sqrt(Math.pow((x1-x2),2)+Math.pow((y1-y2),2)+Math.pow((z1-z2),2));
        double roundformula = Math.round(formula * 100.0) / 100.0;
        return roundformula;
    }
    public static Point3d vvod() {

        Scanner vvod = new Scanner(System.in);//задаем сканер
        System.out.print("Введите x: ");
        double x = Double.parseDouble(vvod.nextLine());
        System.out.print("Введите y: ");
        double y = Double.parseDouble(vvod.nextLine());
        System.out.print("Введите z: ");
        double z = Double.parseDouble(vvod.nextLine());
        Point3d First = new Point3d(x,y,z);
        return First;
    }
    public static double computeArea(Point3d First,Point3d Second,Point3d Third)
    {
            double firstlen = distanceTo(First, Second);
            double secondtlen = distanceTo(First, Third);
            double thirdlen = distanceTo(Second, Third);
            double pp = 0.5 * (firstlen + secondtlen + thirdlen);
            double formula = Math.sqrt(pp * (pp - firstlen) * (pp - secondtlen) * (pp - thirdlen));
            double result = Math.round(formula * 100.0) / 100.0;
            return result;
    }



}
