import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;

public class FractalExplorer
{
    private int dispSize;//размер экрана
    private JImageDisplay imgDisp; // ссыла для обновления отображения
    private FractalGenerator fractGen;//ссылка на отображение других фракталов в будующем
    private Rectangle2D.Double range;//диапозон комлексной плокости
    private JComboBox CB;//задаем комбобокс для вывода разных фракталов

    //принимаем диапазон, сохраняем его, инициируе
    private FractalExplorer (int dispSize) //принимаем размер экрана
    {
        this.dispSize = dispSize;// сохраняем его
        this.fractGen = new Mandelbrot();//инициируем фрактальный генератор
        this.range = new Rectangle2D.Double(0,0,0,0);//инициируем диапазон
        fractGen.getInitialRange(this.range);
    }

    public void createAndShowGUI () { //задаем пользовательский интерфейс
        imgDisp = new JImageDisplay(dispSize, dispSize);
        imgDisp.addMouseListener(new MouseListener());//"увеличивем" изображение по клику мыши
        JLabel label = new JLabel("Fractal:");
        JFrame Frame = new JFrame("Fractal Generator");//название окна
        JButton Button2 = new JButton("Save");//кнопка для сохранения изображения
        JButton Button1 = new JButton("Reset");//кнопка ресета изображения
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        Button1.setActionCommand("Reset");//кнопки сохранения и ресета
        Button1.addActionListener(new Action());
        Button2.setActionCommand("Save");
        Button2.addActionListener(new Action());
        CB = new JComboBox();//инициализируем комбобокс
        CB.addItem(new Mandelbrot());//добавляем все фракталы
        CB.addItem(new Tricorn());
        CB.addItem(new BurningShip());
        CB.addActionListener(new Action());//прикрепляем наш список к actionlistener
        jPanel1.add(label, BorderLayout.CENTER);//в первую панель добавляем список и lable
        jPanel1.add(CB, BorderLayout.CENTER);
        jPanel2.add(Button1, BorderLayout.CENTER);//во вторую панель добавляем кнопки
        jPanel2.add(Button2, BorderLayout.CENTER);
        Frame.setLayout(new java.awt.BorderLayout());
        Frame.add(imgDisp, BorderLayout.CENTER);//ставим изображение по центру окна
        Frame.add(jPanel1, BorderLayout.NORTH);//панель с списком на верх
        Frame.add(jPanel2, BorderLayout.SOUTH);//панель с кнопками вниз
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

    public class Action implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand().equals("Reset"))
            {//при вызове класса проверяем какая кнопка нажата если ресет, то обновляем изображение
                fractGen.getInitialRange(range);
                drawFractal();
            }
            else if (e.getActionCommand().equals("Save")) //если save то сохраняем
            {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("PNG Images", "png");
                fileChooser.setFileFilter(fileFilter);
                fileChooser.setAcceptAllFileFilterUsed(false);

                int i = fileChooser.showSaveDialog(imgDisp);
                if (i == JFileChooser.APPROVE_OPTION)
                {
                    try//Проверяем можем ли сохранить файл, сохраняем если можем и выводим сообщение об ошибке если не можем
                    {
                        File file = fileChooser.getSelectedFile();
                        ImageIO.write(imgDisp.getImage(), "png", new File(file.getAbsolutePath()+".png"));
                    } catch (NullPointerException | IOException ex)
                    {
                        JOptionPane.showMessageDialog(imgDisp, ex.getMessage(), "Cannot save image", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            else
                {
                fractGen = (FractalGenerator) CB.getSelectedItem();//если не сэйв, то остается только список
                range = new Rectangle2D.Double(0,0,0,0);
                fractGen.getInitialRange(range);
                drawFractal();
                }
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
        FractalExplorer fractalExplorer = new FractalExplorer(800);//открываем окно размеров в 800
        fractalExplorer.createAndShowGUI();//ставив GUI
        fractalExplorer.drawFractal();//Рисуем фрактиал
    }

}
