package comp3025.assignment2.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import comp3025.assignment2.models.SavedCityModel;
import comp3025.assignment2.repository.FirebaseRepository;
import com.google.firebase.firestore.ListenerRegistration;

/**
 * ViewModel for Firebase Auth and Saved Cities.
 * Exposes LiveData only — no Firebase calls, no UI logic.
 */
public class AuthViewModel extends ViewModel {

    private final FirebaseRepository repo = new FirebaseRepository();

    // ── Auth ──────────────────────────────────────────────────────────────────

    private final MutableLiveData<Boolean> authLoading = new MutableLiveData<>(false);
    private final MutableLiveData<String>  authError   = new MutableLiveData<>();
    private final MutableLiveData<Boolean> authSuccess = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isSignedIn  = new MutableLiveData<>();

    public LiveData<Boolean> getAuthLoading() { return authLoading; }
    public LiveData<String>  getAuthError()   { return authError; }
    public LiveData<Boolean> getAuthSuccess() { return authSuccess; }
    public LiveData<Boolean> getIsSignedIn()  { return isSignedIn; }

    public AuthViewModel() {
        // addAuthStateListener keeps isSignedIn in sync automatically
        repo.addAuthStateListener(new FirebaseRepository.AuthStateCallback() {
            @Override public void onSignedIn()  { isSignedIn.setValue(true); }
            @Override public void onSignedOut() { isSignedIn.setValue(false); }
        });
    }

    public void signIn(String email, String password) {
        authLoading.setValue(true);
        authError.setValue(null);
        repo.signIn(email, password, new FirebaseRepository.AuthCallback() {
            @Override public void onSuccess() {
                authLoading.setValue(false);
                authSuccess.setValue(true);
            }
            @Override public void onError(String msg) {
                authLoading.setValue(false);
                authError.setValue(msg);
            }
        });
    }

    public void register(String email, String password) {
        authLoading.setValue(true);
        authError.setValue(null);
        repo.register(email, password, new FirebaseRepository.AuthCallback() {
            @Override public void onSuccess() {
                authLoading.setValue(false);
                authSuccess.setValue(true);
            }
            @Override public void onError(String msg) {
                authLoading.setValue(false);
                authError.setValue(msg);
            }
        });
    }

    public void signOut()           { repo.signOut(); }
    public boolean isSignedIn()     { return repo.isSignedIn(); }
    public String getCurrentEmail() { return repo.getCurrentEmail(); }

    public void consumeAuthError()   { authError.setValue(null); }
    public void consumeAuthSuccess() { authSuccess.setValue(null); }

    // ── Saved Cities ──────────────────────────────────────────────────────────

    private final MutableLiveData<List<SavedCityModel>> savedCities = new MutableLiveData<>();
    private final MutableLiveData<Boolean>              isCitySaved = new MutableLiveData<>(false);
    private final MutableLiveData<String>               saveMessage = new MutableLiveData<>();
    private final MutableLiveData<String>               savedError  = new MutableLiveData<>();

    private ListenerRegistration citiesListener;

    public LiveData<List<SavedCityModel>> getSavedCities() { return savedCities; }
    public LiveData<Boolean>              getIsCitySaved() { return isCitySaved; }
    public LiveData<String>               getSaveMessage() { return saveMessage; }
    public LiveData<String>               getSavedError()  { return savedError; }

    public void startListening() {
        if (citiesListener != null) return;
        citiesListener = repo.listenSavedCities(new FirebaseRepository.SavedCitiesCallback() {
            @Override public void onUpdate(List<SavedCityModel> cities) { savedCities.setValue(cities); }
            @Override public void onError(String msg)                   { savedError.setValue(msg); }
        });
    }

    public void stopListening() {
        if (citiesListener != null) { citiesListener.remove(); citiesListener = null; }
    }

    public void checkIfCitySaved(String name, String country) {
        repo.isCitySaved(name, country, result -> isCitySaved.setValue(result));
    }

    public void saveCity(String name, String region, String country) {
        repo.saveCity(name, region, country, new FirebaseRepository.ActionCallback() {
            @Override public void onSuccess() {
                isCitySaved.setValue(true);
                saveMessage.setValue("\u2713 " + name + ", " + country + " saved to your cities");
            }
            @Override public void onError(String msg) {
                saveMessage.setValue("Could not save: " + msg);
            }
        });
    }

    public void deleteCity(String documentId) {
        repo.deleteCity(documentId, new FirebaseRepository.ActionCallback() {
            @Override public void onSuccess() {}
            @Override public void onError(String msg) { savedError.setValue(msg); }
        });
    }

    public void resetCitySaved()     { isCitySaved.setValue(false); }
    public void consumeSaveMessage() { saveMessage.setValue(null); }
    public void consumeSavedError()  { savedError.setValue(null); }

    @Override
    protected void onCleared() {
        super.onCleared();
        repo.removeAuthStateListener();
        stopListening();
    }
}
