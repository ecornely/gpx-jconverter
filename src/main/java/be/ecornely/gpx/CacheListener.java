package be.ecornely.gpx;

import java.io.Serializable;

import be.ecornely.gpx.data.Geocache;

public interface CacheListener extends Serializable{

	void newCache(Geocache g);

}
