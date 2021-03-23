
public class Location
{
    public int xCoord;
    public int yCoord;
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }
    public Location()
    {
        this(0, 0);
    }

    @Override
    public int hashCode() {
        int result = 20;//мы берем простое число
        result = 40 * result + xCoord;//умножаем на другое просто число+координты дважды
        result = 40 * result + yCoord;
        return result;
    }
    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;
        if (object == null || object.getClass() != this.getClass())
            return false;

        Location location = (Location) object;
        return xCoord == location.xCoord && yCoord == location.yCoord;
    }
}

