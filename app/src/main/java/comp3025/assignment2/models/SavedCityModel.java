package comp3025.assignment2.models;

import com.google.firebase.Timestamp;

/**
 * Firestore document model for a saved city.
 * Path: users/{uid}/savedCities/{cityId}
 */
public class SavedCityModel {

    private String    name;
    private String    region;
    private String    country;
    private Timestamp lastSearched;
    private transient String documentId;

    public SavedCityModel() {}

    public String    getName()         { return name; }
    public String    getRegion()       { return region != null ? region : ""; }
    public String    getCountry()      { return country; }
    public Timestamp getLastSearched() { return lastSearched; }
    public String    getDocumentId()   { return documentId; }

    public void setName(String name)          { this.name = name; }
    public void setRegion(String region)      { this.region = region; }
    public void setCountry(String country)    { this.country = country; }
    public void setLastSearched(Timestamp ts) { this.lastSearched = ts; }
    public void setDocumentId(String id)      { this.documentId = id; }

    public String getQueryString() {
        if (region == null || region.isEmpty()) return name + ", " + country;
        return name + ", " + region + ", " + country;
    }
}
