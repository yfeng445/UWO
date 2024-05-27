
/**
 * a short path finder
 *
 * @author
 */
public class ShortestPath {

    /**
     * city may
     */
    private CityMap cityMap;

    public ShortestPath(CityMap theMap) {
        cityMap = theMap;
    }

    /**
     * perform BFS traverse
     */
    public void findShortestPath() {
        // used as a queue
        OrderedCircularArray<MapCell> list = new OrderedCircularArray<MapCell>();

        int cellIndex = 0;
        MapCell cell = cityMap.getStart();
        cell.markInitial();
        list.insert(cell, cellIndex++);
        MapCell cur = null;
        while (!list.isEmpty()) {

            // pick the first element from queue
            cur = list.getSmallest();
            if (cur.isDestination()) {
                break;
            }
            int dist = cur.getDistanceToStart();

            // iterate all of 4 neighborhood
            for (int i = 0; i < 4; i++) {
                MapCell neigh = cur.getNeighbour(i);
                if (neigh == null || neigh.isBlock()) {
                    continue;
                }

                if (neigh.isNorthRoad() && i != 0 || neigh.isEastRoad() && i != 1 || neigh.isSouthRoad() && i != 2 || neigh.isWestRoad() && i != 3) {
                    continue;
                }

                if (neigh.getDistanceToStart() == Integer.MAX_VALUE) {
                    // update new distance
                    neigh.setDistanceToStart(dist + 1);
                    // record the parent
                    neigh.setPredecessor(cur);
                    list.insert(neigh, cellIndex++);
                }
            }

        }
        if (!cur.isDestination()) {
            System.out.println("No path found");
            return;
        }

        // trackback the route
        int len = 1;
        while (!cur.isStart()) {
            cur.markInList();
            len++;
            cur = cur.getPredecessor();
        }
        System.out.println("Path Found. Total Length: " + len);

    }

    /**
     * this method is not used
     *
     * @param cell
     * @return
     */
    private MapCell nextCell(MapCell cell) {
        return null;
    }

}
