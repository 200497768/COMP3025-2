package comp3025.assignment2.models;

import com.google.firebase.Timestamp;

/**
 * This model represents a saved city document in Firestore.
 * The Firestore document path is: users/{uid}/savedCities/{cityId}
 * Each field corresponds to a field in the Firestore document.
 * The documentId field is marked transient so Firestore does not try to serialize it.
 */
public class SavedCityModel {

    /**
     * This field is the name of the city.
     */
    private String name;

    /**
     * This field is the region or province of the city.
     */
    private String region;

    /**
     * This field is the country the city is in.
     */
    private String country;

    /**
     * This field is the timestamp of the last time this city was searched.
     * It is written as a server timestamp by Firestore when the document is saved.
     */
    private Timestamp lastSearched;

    /**
     * This field is the Firestore document ID for this saved city.
     * It is marked transient so Firestore does not try to read or write it as a document field.
     * It is set manually after the document is retrieved from Firestore.
     */
    private transient String documentId;

    /**
     * Required empty constructor for Firestore deserialization.
     */
    public SavedCityModel() {
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region != null ? region : "";
    }

    public String getCountry() {
        return country;
    }

    public Timestamp getLastSearched() {
        return lastSearched;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLastSearched(Timestamp ts) {
        this.lastSearched = ts;
    }

    public void setDocumentId(String id) {
        this.documentId = id;
    }

    /**
     * This method returns a query string that can be used to search for this city.
     * The string combines name, region, and country so the weather API can identify the city.
     */
    public String getQueryString() {
        if (region == null || region.isEmpty()) return name + ", " + country;
        return name + ", " + region + ", " + country;
    }
}
