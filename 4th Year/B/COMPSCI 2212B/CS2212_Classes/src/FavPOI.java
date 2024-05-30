import java.util.*;

public class FavPOI extends POI{
	ArrayList<POI> FavList;
	int isFavPOI = 0;
	public FavPOI(String type, String building, String floor, String room, String description){
		super(type, building, floor, room, description);
		this.FavList = null;
	}
	
	void setFav(POI favPOI){
		this.FavList.add(favPOI);
	}
	
}
