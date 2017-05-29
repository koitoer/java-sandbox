package android.reactive.koitoer.com.reactiveapp.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mmena on 4/16/17.
 */

public class SearchResult {

    private List<Geoname> geonames = new ArrayList<>();

    public List<Geoname> getGeonames() {
        return geonames;
    }

    public void setGeonames(List<Geoname> geonames) {
        this.geonames = geonames;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "geoNames=" + geonames +
                '}';
    }
}
