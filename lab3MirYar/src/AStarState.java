import java.util.HashMap;


public class AStarState
{

    private Map2D map;
    private HashMap<Location, Waypoint> OpenedWaypoint = new HashMap<>();//задаем открытые вершины
    private HashMap<Location, Waypoint> ClosedWaypoint = new HashMap<>();//задаем закрытые вершины

    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    public Map2D getMap()
    {
        return map;
    }


    public Waypoint getMinOpenWaypoint()//находим минимальную открытую вершину
    {
        if (numOpenWaypoints() == 0) {//выдаем нулл если их нет
            return null;
        }

        Waypoint MinOpenWaypoint = null;
        float minimal = Float.MAX_VALUE;

        for (Waypoint waypoint : OpenedWaypoint.values()) {//прокручиваем вершины чтобы найти минимальную и выдать ее
            float cena = waypoint.getTotalCost();
            if (cena < minimal) {
                minimal = cena;
                MinOpenWaypoint = waypoint;
            }
        }
        return MinOpenWaypoint;
    }

    public boolean addOpenWaypoint(Waypoint NewWayPoint)//добавление открытой вершины
    {
        Waypoint openedWayPoint = OpenedWaypoint.get(NewWayPoint.loc);

        if (openedWayPoint == null || NewWayPoint.getPreviousCost() < openedWayPoint.getPreviousCost()) {//проверяем является ли новая вершина лучше старой
            OpenedWaypoint.put(NewWayPoint.loc, NewWayPoint);
            return true;
        }
        return false;
    }

    public int numOpenWaypoints()//возвращаем размер Вершины
    {
        return OpenedWaypoint.size();
    }

    public void closeWaypoint(Location location)//перемещаем вершину из открытых в закрытую
    {
        Waypoint waypoint = OpenedWaypoint.remove(location);//удаляем
        if (OpenedWaypoint != null) {
            ClosedWaypoint.put(location, waypoint);//добавляем
        }
    }

    public boolean isLocationClosed(Location loc)//проверка является ли вершина закрытой
    {
        return ClosedWaypoint.containsKey(loc);
    }
}

