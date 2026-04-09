package comp3025.assignment2.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.ListenerRegistration;

import java.util.List;

import comp3025.assignment2.models.SavedCityModel;
import comp3025.assignment2.repository.FirebaseRepository;

/**
 * This class is the ViewModel for Firebase Auth and Saved Cities.
 * It exposes LiveData fields to Fragments and calls FirebaseRepository for all data operations.
 * No Firebase API calls are made directly in this class.
 */
public class AuthViewModel extends ViewModel {

    /**
     * This field is the repository that handles all Firebase Auth and Firestore operations.
     * The ViewModel calls into the repository instead of calling Firebase directly.
     */
    private final FirebaseRepository repo = new FirebaseRepository();

    // ── Auth LiveData ─────────────────────────────────────────────────────────

    /**
     * This field is true while a Firebase Auth operation is in progress.
     */
    private final MutableLiveData<Boolean> authLoading = new MutableLiveData<>(false);

    /**
     * This field holds a friendly error message when a Firebase Auth operation fails.
     */
    private final MutableLiveData<String> authError = new MutableLiveData<>();

    /**
     * This field is set to true when a Firebase Auth operation succeeds.
     */
    private final MutableLiveData<Boolean> authSuccess = new MutableLiveData<>();

    /**
     * This field reflects whether a user is currently signed in.
     */
    private final MutableLiveData<Boolean> isSignedIn = new MutableLiveData<>();

    public LiveData<Boolean> getAuthLoading() {
        return authLoading;
    }

    public LiveData<String> getAuthError() {
        return authError;
    }

    public LiveData<Boolean> getAuthSuccess() {
        return authSuccess;
    }

    public LiveData<Boolean> getIsSignedIn() {
        return isSignedIn;
    }

    public AuthViewModel() {
        //Register an auth state listener so that isSignedIn stays in sync automatically.
        //This uses the required FirebaseAuth.addAuthStateListener() API.
        repo.addAuthStateListener(new FirebaseRepository.AuthStateCallback() {
            @Override
            public void onSignedIn() {
                isSignedIn.setValue(true);
            }

            @Override
            public void onSignedOut() {
                isSignedIn.setValue(false);
            }
        });
    }

    /**
     * This method signs the user in with Firebase Auth.
     * It updates authLoading, authError, and authSuccess via LiveData.
     */
    public void signIn(String email, String password) {
        authLoading.setValue(true);
        authError.setValue(null);
        repo.signIn(email, password, new FirebaseRepository.AuthCallback() {
            @Override
            public void onSuccess() {
                authLoading.setValue(false);
                authSuccess.setValue(true);
            }

            @Override
            public void onError(String msg) {
                authLoading.setValue(false);
                authError.setValue(msg);
            }
        });
    }

    /**
     * This method registers a new user with Firebase Auth.
     * It updates authLoading, authError, and authSuccess via LiveData.
     */
    public void register(String email, String password) {
        authLoading.setValue(true);
        authError.setValue(null);
        repo.register(email, password, new FirebaseRepository.AuthCallback() {
            @Override
            public void onSuccess() {
                authLoading.setValue(false);
                authSuccess.setValue(true);
            }

            @Override
            public void onError(String msg) {
                authLoading.setValue(false);
                authError.setValue(msg);
            }
        });
    }

    /**
     * This method signs the current user out.
     */
    public void signOut() {
        repo.signOut();
    }

    /**
     * This method returns true if a user is currently signed in.
     */
    public boolean isSignedIn() {
        return repo.isSignedIn();
    }

    /**
     * This method returns the email of the currently signed-in user.
     */
    public String getCurrentEmail() {
        return repo.getCurrentEmail();
    }

    /**
     * This method sets the authError field to null so the observer doesn't fire again.
     */
    public void consumeAuthError() {
        authError.setValue(null);
    }

    /**
     * This method sets the authSuccess field to null so the observer doesn't fire again.
     */
    public void consumeAuthSuccess() {
        authSuccess.setValue(null);
    }

    // ── Saved Cities LiveData ─────────────────────────────────────────────────

    /**
     * This field holds the list of saved cities from the Firestore snapshot listener.
     */
    private final MutableLiveData<List<SavedCityModel>> savedCities = new MutableLiveData<>();

    /**
     * This field is true if the current city shown in ShowWeatherFragment is already saved.
     */
    private final MutableLiveData<Boolean> isCitySaved = new MutableLiveData<>(false);

    /**
     * This field holds a confirmation message to show after a city is saved.
     */
    private final MutableLiveData<String> saveMessage = new MutableLiveData<>();

    /**
     * This field holds an error message if a Firestore saved cities operation fails.
     */
    private final MutableLiveData<String> savedError = new MutableLiveData<>();

    /**
     * This field holds the Firestore snapshot listener registration so it can be removed later.
     */
    private ListenerRegistration citiesListener;

    public LiveData<List<SavedCityModel>> getSavedCities() {
        return savedCities;
    }

    public LiveData<Boolean> getIsCitySaved() {
        return isCitySaved;
    }

    public LiveData<String> getSaveMessage() {
        return saveMessage;
    }

    public LiveData<String> getSavedError() {
        return savedError;
    }

    /**
     * This method starts the Firestore real-time snapshot listener for saved cities.
     * It should be called when SavedCitiesFragment is created.
     * It does nothing if the listener is already running.
     */
    public void startListening() {
        if (citiesListener != null) return;
        citiesListener = repo.listenSavedCities(new FirebaseRepository.SavedCitiesCallback() {
            @Override
            public void onUpdate(List<SavedCityModel> cities) {
                savedCities.setValue(cities);
            }

            @Override
            public void onError(String msg) {
                savedError.setValue(msg);
            }
        });
    }

    /**
     * This method stops the Firestore real-time snapshot listener.
     * It should be called when SavedCitiesFragment is destroyed to prevent memory leaks.
     */
    public void stopListening() {
        if (citiesListener != null) {
            citiesListener.remove();
            citiesListener = null;
        }
    }

    /**
     * This method checks Firestore to see if the given city is already saved.
     * The result is posted to the isCitySaved LiveData field.
     */
    public void checkIfCitySaved(String name, String country) {
        repo.isCitySaved(name, country, result -> isCitySaved.setValue(result));
    }

    /**
     * This method saves a city to Firestore.
     * On success, isCitySaved is set to true and a confirmation message is posted to saveMessage.
     */
    public void saveCity(String name, String region, String country) {
        repo.saveCity(name, region, country, new FirebaseRepository.ActionCallback() {
            @Override
            public void onSuccess() {
                isCitySaved.setValue(true);
                //Show the city name and country in the confirmation message.
                saveMessage.setValue("\u2713 " + name + ", " + country + " saved to your cities");
            }

            @Override
            public void onError(String msg) {
                saveMessage.setValue("Could not save: " + msg);
            }
        });
    }

    /**
     * This method deletes a saved city from Firestore by its document ID.
     * The Firestore snapshot listener will automatically update the savedCities LiveData.
     */
    public void deleteCity(String documentId) {
        repo.deleteCity(documentId, new FirebaseRepository.ActionCallback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError(String msg) {
                savedError.setValue(msg);
            }
        });
    }

    /**
     * This method resets isCitySaved to false when leaving the weather screen.
     */
    public void resetCitySaved() {
        isCitySaved.setValue(false);
    }

    /**
     * This method sets saveMessage to null so the observer doesn't fire again.
     */
    public void consumeSaveMessage() {
        saveMessage.setValue(null);
    }

    /**
     * This method sets savedError to null so the observer doesn't fire again.
     */
    public void consumeSavedError() {
        savedError.setValue(null);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        //Remove the auth state listener and snapshot listener when this ViewModel is destroyed.
        repo.removeAuthStateListener();
        stopListening();
    }
}
