package comp3025.assignment2.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
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
 * All Firebase Auth and Firestore operations for Assignment 3.
 * Firestore path: users/{uid}/savedCities/{cityId}
 */
public class FirebaseRepository {

    public interface AuthCallback {
        void onSuccess();
        void onError(String message);
    }

    public interface AuthStateCallback {
        void onSignedIn();
        void onSignedOut();
    }

    public interface SavedCitiesCallback {
        void onUpdate(List<SavedCityModel> cities);
        void onError(String message);
    }

    public interface ActionCallback {
        void onSuccess();
        void onError(String message);
    }

    public interface BooleanCallback {
        void onResult(boolean result);
    }

    private final FirebaseAuth      auth;
    private final FirebaseFirestore db;
    private FirebaseAuth.AuthStateListener authStateListener;

    public FirebaseRepository() {
        auth = FirebaseAuth.getInstance();
        db   = FirebaseFirestore.getInstance();
    }

    // ── Auth ──────────────────────────────────────────────────────────────────

    public void signIn(String email, String password, AuthCallback cb) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(r -> cb.onSuccess())
                .addOnFailureListener(e -> {
                    if (e instanceof FirebaseAuthInvalidCredentialsException || e instanceof FirebaseAuthInvalidUserException) {
                        cb.onError("Incorrect email or password. Please try again.");
                    } else {
                        cb.onError(friendlyError(e.getMessage()));
                    }
                });
    }

    public void register(String email, String password, AuthCallback cb) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(r -> cb.onSuccess())
                .addOnFailureListener(e -> cb.onError(friendlyError(e.getMessage())));
    }

    public void signOut() { auth.signOut(); }

    public boolean isSignedIn() { return auth.getCurrentUser() != null; }

    public String getCurrentUid() {
        FirebaseUser u = auth.getCurrentUser();
        return u != null ? u.getUid() : null;
    }

    public String getCurrentEmail() {
        FirebaseUser u = auth.getCurrentUser();
        return u != null ? u.getEmail() : null;
    }

    public void addAuthStateListener(AuthStateCallback cb) {
        authStateListener = firebaseAuth -> {
            if (firebaseAuth.getCurrentUser() != null) cb.onSignedIn();
            else                                       cb.onSignedOut();
        };
        auth.addAuthStateListener(authStateListener);
    }

    public void removeAuthStateListener() {
        if (authStateListener != null) {
            auth.removeAuthStateListener(authStateListener);
            authStateListener = null;
        }
    }

    // ── Saved Cities ──────────────────────────────────────────────────────────

    public ListenerRegistration listenSavedCities(SavedCitiesCallback cb) {
        String uid = getCurrentUid();
        if (uid == null) { cb.onError("Not signed in"); return null; }

        return db.collection("users").document(uid)
                .collection("savedCities")
                .orderBy("lastSearched", Query.Direction.DESCENDING)
                .addSnapshotListener((snap, err) -> {
                    if (err != null) { cb.onError(err.getMessage()); return; }
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

    public void saveCity(String name, String region, String country, ActionCallback cb) {
        String uid = getCurrentUid();
        if (uid == null) { cb.onError("Not signed in"); return; }

        String cityId = buildCityId(name, country);
        Map<String, Object> data = new HashMap<>();
        data.put("name",         name);
        data.put("region",       region != null ? region : "");
        data.put("country",      country);
        data.put("lastSearched", FieldValue.serverTimestamp());

        db.collection("users").document(uid)
                .collection("savedCities").document(cityId)
                .set(data)
                .addOnSuccessListener(v -> cb.onSuccess())
                .addOnFailureListener(e -> cb.onError(e.getMessage()));
    }

    public void isCitySaved(String name, String country, BooleanCallback cb) {
        String uid = getCurrentUid();
        if (uid == null) { cb.onResult(false); return; }

        db.collection("users").document(uid)
                .collection("savedCities").document(buildCityId(name, country))
                .get()
                .addOnSuccessListener(doc -> cb.onResult(doc.exists()))
                .addOnFailureListener(e  -> cb.onResult(false));
    }

    public void deleteCity(String documentId, ActionCallback cb) {
        String uid = getCurrentUid();
        if (uid == null) { cb.onError("Not signed in"); return; }

        db.collection("users").document(uid)
                .collection("savedCities").document(documentId)
                .delete()
                .addOnSuccessListener(v -> cb.onSuccess())
                .addOnFailureListener(e -> cb.onError(e.getMessage()));
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private String buildCityId(String name, String country) {
        return (name + "_" + country).replaceAll("[^a-zA-Z0-9]", "_").toLowerCase();
    }

    private String friendlyError(String raw) {
        if (raw == null) return "An unexpected error occurred.";

        String msg = raw.toLowerCase();

        if (msg.contains("invalid_email") || msg.contains("badly formatted") || msg.contains("valid email")) {
            return "Please enter a valid email address.";
        }
        if (msg.contains("invalid_login_credentials") || msg.contains("wrong_password")
                || msg.contains("user-not-found") || msg.contains("credential is incorrect")
                || msg.contains("password is invalid") || msg.contains("no user record")) {
            return "Incorrect email or password. Please try again.";
        }
        if (msg.contains("email_exists") || msg.contains("email-already-in-use")) {
            return "This email is already registered.";
        }
        if (msg.contains("weak_password") || msg.contains("weak-password")) {
            return "Password must be at least 6 characters.";
        }
        if (msg.contains("network_error") || msg.contains("network")) {
            return "Network error. Check your connection.";
        }
        if (msg.contains("too_many_requests")) {
            return "Too many attempts. Try again later.";
        }
        return "Authentication failed. Please try again.";
    }
}
