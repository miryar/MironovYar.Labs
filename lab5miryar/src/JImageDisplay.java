import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class JImageDisplay extends JComponent {
    //созданм private поле доступа для bufferimage
    private BufferedImage Buffimage;

    //принимаем целочисленные значения ширины и высоты
    public JImageDisplay(int width, int height) {
        //инициализируем новое изображение с заданной шириной и высотой
        Buffimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Dimension dim = new Dimension(width, height);
        super.setPreferredSize(dim);
    }
    // отрисовываем изображение
    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(Buffimage, 0, 0, Buffimage.getWidth(), Buffimage.getHeight(), null);
    }

    //метод очистки
    public void clearImage() {
        //прокручиваем все пиксели и перекрашиваем их в черный
        for (int i = 0; i < Buffimage.getWidth(); i++) {
            for (int j = 0; j < Buffimage.getHeight(); j++) {
                drawPixel(i, j, 0);
            }
        }
    }
    //метод для установки пикселяв определенный цвет
    public void drawPixel(int x, int y, int rgbColor)
    {
        Buffimage.setRGB(x, y, rgbColor);
    }

    public BufferedImage getImage()
    {
        return Buffimage;
    }


}
