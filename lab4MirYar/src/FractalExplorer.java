import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class FractalExplorer
{
    private int dispSize;//размер экрана
    private JImageDisplay imgDisp; // ссыла для обновления отображения
    private FractalGenerator fractGen;//ссылка на отображение других фракталов в будующем
    private Rectangle2D.Double range;//диапозон комлексной плокости

    //принимаем диапазон, сохраняем его, инициируе
    private FractalExplorer (int dispSize) //принимаем размер экрана
    {
        this.dispSize = dispSize;// сохраняем его
        this.fractGen = new Mandelbrot();//инициируем фрактальный генератор
        this.range = new Rectangle2D.Double(0,0,0,0);//инициируем диапазон
        fractGen.getInitialRange(this.range);
    }

    public void createAndShowGUI () { //задаем пользовательский интерфейс
        JFrame Frame = new JFrame("Fractal Generator");//название окна
        JButton Button = new JButton("Reset");//кнопка ресета изображения
        imgDisp = new JImageDisplay(dispSize, dispSize);
        imgDisp.addMouseListener(new MouseListener());//"увеличивем" изображение по клику мыши
        Button.addActionListener(new Reset());//ставим изначальное изображение при нажатии на кнопку
        Frame.setLayout(new java.awt.BorderLayout());
        Frame.add(imgDisp, BorderLayout.CENTER);//ставим изображение по центру окна
        Frame.add(Button, BorderLayout.SOUTH);//ставим кнопку на юг(вниз)
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//выход из программы по закрытию окна
        Frame.pack();
        Frame.setVisible(true);
        Frame.setResizable(false);
    }

    private void drawFractal()
    {
        for (int x = 0; x < dispSize; x++)//проходим по циклично по всем пикселям
        {
            for (int y = 0; y < dispSize; y++)
            {
                int count = fractGen.numIterations(FractalGenerator.getCoord(range.x, range.x + range.width, dispSize, x),
                        fractGen.getCoord(range.y, range.y + range.width, dispSize, y));//определяем координаты
                if (count == -1)//колличество итераций равно -1 ставим черный пиксаль
                {
                    imgDisp.drawPixel(x, y, 0);
                }
                else {//если колличество итераций не равно нулю ставим ему цвет основанный на колличестве итераций
                    float hue = 0.7f + (float) count / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    imgDisp.drawPixel(x, y, rgbColor);
                }
            }
        }
        imgDisp.repaint();//обновляем JimageDisplay
    }

    public class Reset implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            fractGen.getInitialRange(range);
            drawFractal();
        }
    }

    public class MouseListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            double x = FractalGenerator.getCoord(range.x, range.x + range.width, dispSize, e.getX());//Находим x координату клика мышки
            double y = FractalGenerator.getCoord(range.y, range.y + range.width, dispSize, e.getY());//Находим y координату клика мышки
            fractGen.recenterAndZoomRange(range, x, y, 0.5);//"увеличиваем" изображение в месте клика мыши
            drawFractal();
        }
    }

    public static void main(String[] args)
    {
        FractalExplorer fractalExplorer = new FractalExplorer(800);//открвыаем окно размеров в 800
        fractalExplorer.createAndShowGUI();//ставив GUI
        fractalExplorer.drawFractal();//Рисуем фрактиал
    }

}
