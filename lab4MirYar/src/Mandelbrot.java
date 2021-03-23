import java.awt.geom.Rectangle2D;


 class Mandelbrot extends FractalGenerator
{
    public static final int MAX_ITERATIONS = 2000;

    //создаем метод для определения наибольее интересной области для конкретного фрактала, берем данные из методички
    public void getInitialRange (Rectangle2D.Double Initialrange)
    {
        Initialrange.x = -2;
        Initialrange.y = -1.5;
        Initialrange.height = 3;
        Initialrange.width = 3;
    }
    //реализуем итеративную функци, без Math.Pow()
    public int numIterations(double x, double y) {
        double a,b,c,d;
        a = x;
        b = y;
        int count = 0;
        while (count < MAX_ITERATIONS) {
            count++;
            c = a*a-b*b+x;
            d = 2*a*b+y;
            a = c;
            b = d;
            if (a*a+b*b > 4)
                break;
        }
        if (count == MAX_ITERATIONS)
            return -1;
        return count;
    }
}
