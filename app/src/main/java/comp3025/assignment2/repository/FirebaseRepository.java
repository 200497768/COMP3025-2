package comp3025.assignment2.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import comp3025.assignment2.models.SavedCityModel;

/**
 * This class contains all Firebase Auth and Firestore operations for Assignment 3.
 * All Firebase API calls are grouped here so that ViewModels do not call Firebase directly.
 * <p>
 * Firestore document path: users/{uid}/savedCities/{cityId}
 * <p>
 * Required Firebase APIs used in this class:
 * - FirebaseAuth.getInstance()
 * - signInWithEmailAndPassword()
 * - createUserWithEmailAndPassword()
 * - addAuthStateListener()
 * - signOut()
 */
public class FirebaseRepository {

    /**
     * This interface is used to return the result of a Firebase Auth operation.
     * onSuccess is called when the operation completed without errors.
     * onError is called with a friendly message when the operation failed.
     */
    public interface AuthCallback {
        void onSuccess();

        void onError(String message);
    }

    /**
     * This interface is used to notify when the Firebase Auth state changes.
     * onSignedIn is called when a user is signed in.
     * onSignedOut is called when no user is signed in.
     */
    public interface AuthStateCallback {
        void onSignedIn();

        void onSignedOut();
    }

    /**
     * This interface is used to return a real-time list of saved cities from Firestore.
     * onUpdate is called every time the Firestore snapshot listener fires.
     * onError is called if the listener encounters an error.
     */
    public interface SavedCitiesCallback {
        void onUpdate(List<SavedCityModel> cities);

        void onError(String message);
    }

    /**
     * This interface is used to return the result of a Firestore write or delete operation.
     */
    public interface ActionCallback {
        void onSuccess();

        void onError(String message);
    }

    /**
     * This interface replaces OnSuccessListener<Boolean> to avoid importing com.google.firebase.tasks.
     * It is used to return the result of a Firestore document existence check.
     */
    public interface BooleanCallback {
        void onResult(boolean result);
    }

    private final FirebaseAuth auth;
    private final FirebaseFirestore db;

    /**
     * Stored so it can be removed when the ViewModel is cleared.
     */
    private FirebaseAuth.AuthStateListener authStateListener;

    public FirebaseRepository() {
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    // ── Auth ──────────────────────────────────────────────────────────────────

    /**
     * This method signs the user in with Firebase Auth using email and password.
     * The callback receives the result when the operation finishes.
     */
    public void signIn(String email, String password, AuthCallback cb) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(r -> cb.onSuccess())
                .addOnFailureListener(e -> cb.onError(friendlyError(e.getMessage())));
    }

    /**
     * This method registers a new user with Firebase Auth using email and password.
     * The callback receives the result when the operation finishes.
     */
    public void register(String email, String password, AuthCallback cb) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(r -> cb.onSuccess())
                .addOnFailureListener(e -> cb.onError(friendlyError(e.getMessage())));
    }

    /**
     * This method signs the currently signed-in user out.
     */
    public void signOut() {
        auth.signOut();
    }

    /**
     * This method returns true if a user is currently signed in.
     */
    public boolean isSignedIn() {
        return auth.getCurrentUser() != null;
    }

    /**
     * This method returns the UID of the currently signed-in user.
     * Returns null if no user is signed in.
     */
    public String getCurrentUid() {
        FirebaseUser u = auth.getCurrentUser();
        return u != null ? u.getUid() : null;
    }

    /**
     * This method returns the email of the currently signed-in user.
     * Returns null if no user is signed in.
     */
    public String getCurrentEmail() {
        FirebaseUser u = auth.getCurrentUser();
        return u != null ? u.getEmail() : null;
    }

    /**
     * This method registers an addAuthStateListener with FirebaseAuth.
     * The callback fires immediately with the current state, and again whenever the state changes.
     */
    public void addAuthStateListener(AuthStateCallback cb) {
        authStateListener = firebaseAuth -> {
            if (firebaseAuth.getCurrentUser() != null) cb.onSignedIn();
            else cb.onSignedOut();
        };
        auth.addAuthStateListener(authStateListener);
    }

    /**
     * This method removes the auth state listener that was added by addAuthStateListener.
     * It should be called when the ViewModel is cleared to prevent memory leaks.
     */
    public void removeAuthStateListener() {
        if (authStateListener != null) {
            auth.removeAuthStateListener(authStateListener);
            authStateListener = null;
        }
    }

    // ── Saved Cities ──────────────────────────────────────────────────────────

    /**
     * This method attaches a real-time Firestore snapshot listener to the savedCities collection.
     * The callback fires every time the collection changes.
     * The returned ListenerRegistration must be removed when the fragment is destroyed.
     */
    public ListenerRegistration listenSavedCities(SavedCitiesCallback cb) {
        String uid = getCurrentUid();
        if (uid == null) {
            cb.onError("Not signed in");
            return null;
        }

        return db.collection("users").document(uid)
                .collection("savedCities")
                .orderBy("lastSearched", Query.Direction.DESCENDING)
                .addSnapshotListener((snap, err) -> {
                    if (err != null) {
                        cb.onError(err.getMessage());
                        return;
                    }
                    List<SavedCityModel> list = new ArrayList<>();
                    if (snap != null) {
                        for (var doc : snap.getDocuments()) {
                            SavedCityModel city = doc.toObject(SavedCityModel.class);
                            if (city != null) {
                                city.setDocumentId(doc.getId());
                                list.add(city);
                            }
                        }
                    }
                    cb.onUpdate(list);
                });
    }

    /**
     * This method saves a city to Firestore under the signed-in user's UID.
     * If the city already exists (same cityId), the lastSearched timestamp is updated.
     * Document path: users/{uid}/savedCities/{cityId}
     */
    public void saveCity(String name, String region, String country, ActionCallback cb) {
        String uid = getCurrentUid();
        if (uid == null) {
            cb.onError("Not signed in");
            return;
        }

        //Build the document data map.
        String cityId = buildCityId(name, country);
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("region", region != null ? region : "");
        data.put("country", country);
        data.put("lastSearched", FieldValue.serverTimestamp());

        //Use set() so that saving a city that is already saved just updates the timestamp.
        db.collection("users").document(uid)
                .collection("savedCities").document(cityId)
                .set(data)
                .addOnSuccessListener(v -> cb.onSuccess())
                .addOnFailureListener(e -> cb.onError(e.getMessage()));
    }

    /**
     * This method checks whether a city is already saved in Firestore.
     * Uses BooleanCallback instead of OnSuccessListener<Boolean> to avoid importing firebase.tasks.
     */
    public void isCitySaved(String name, String country, BooleanCallback cb) {
        String uid = getCurrentUid();
        if (uid == null) {
            cb.onResult(false);
            return;
        }

        db.collection("users").document(uid)
                .collection("savedCities").document(buildCityId(name, country))
                .get()
                .addOnSuccessListener(doc -> cb.onResult(doc.exists()))
                .addOnFailureListener(e -> cb.onResult(false));
    }

    /**
     * This method deletes a saved city from Firestore by its document ID.
     * The document ID is stored in SavedCityModel.documentId.
     */
    public void deleteCity(String documentId, ActionCallback cb) {
        String uid = getCurrentUid();
        if (uid == null) {
            cb.onError("Not signed in");
            return;
        }

        db.collection("users").document(uid)
                .collection("savedCities").document(documentId)
                .delete()
                .addOnSuccessListener(v -> cb.onSuccess())
                .addOnFailureListener(e -> cb.onError(e.getMessage()));
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    /**
     * This method builds a Firestore document ID from the city name and country.
     * Only alphanumeric characters are kept; everything else becomes an underscore.
     * This prevents duplicate documents when the same city is saved more than once.
     */
    private String buildCityId(String name, String country) {
        return (name + "_" + country).replaceAll("[^a-zA-Z0-9]", "_").toLowerCase();
    }

    /**
     * This method converts a raw Firebase Auth error message into a friendly string.
     * Firebase error codes are not user-friendly, so this method maps them to plain English.
     */
    private String friendlyError(String raw) {
        if (raw == null) return "An unexpected error occurred.";
        if (raw.contains("INVALID_EMAIL")) return "Please enter a valid email address.";
        if (raw.contains("INVALID_LOGIN_CREDENTIALS") || raw.contains("WRONG_PASSWORD")
                || raw.contains("user-not-found")) return "Incorrect email or password.";
        if (raw.contains("EMAIL_EXISTS") || raw.contains("email-already-in-use"))
            return "This email is already registered.";
        if (raw.contains("WEAK_PASSWORD") || raw.contains("weak-password"))
            return "Password must be at least 6 characters.";
        if (raw.contains("NETWORK_ERROR") || raw.contains("network"))
            return "Network error. Check your connection.";
        if (raw.contains("TOO_MANY_REQUESTS")) return "Too many attempts. Try again later.";
        return "Authentication failed. Please try again.";
    }
}
