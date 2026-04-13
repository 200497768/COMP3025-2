package comp3025.assignment2.sounds;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import comp3025.assignment2.views.CompletedAction;

/**
 * This is the code that's responsible for causing a sound to happen.
 * We want sounds to happen multiple times in this assignment.
 * If we use SoundCode, we can avoid repeating that code, since it will be part of the startSound method.
 * @author Harshit Gambhir
 * @author Yatri Devangbhai Padhiyar
 * @author Dawa Angchuk Sherpa
 * @author Hao Tian
 */
public class SoundCode {

    /**
     * This field is MediaPlayer that was created when starting an existing sound.
     */
    public static MediaPlayer startedMediaPlayer;

    /**
     * This field is needed by the sound code in order to retrieve MediaPlayer.
     */
    private Context context;

    /**
     * This field isn't needed, but if it's provided, the sound code will ensure that the sound can start.
     * This field will be used to retrieve AudioManager.
     */
    private AppCompatActivity activity;

    public SoundCode(Context context, AppCompatActivity activity) {
        this.context = context;
        this.activity = activity;
    }

    /**
     * This method stops an existing sound, if it exists.
     * This method determines whether an existing sound exists by checking the field.
     */
    public static void stopExistingSound() {

            //Check whether an existing sound exists.
            if (SoundCode.startedMediaPlayer == null) {
                //No existing sound exists.
            } else {
                //Stop the existing sound.
                try {
                SoundCode.startedMediaPlayer.stop();
                } catch (Exception e) {
                    Log.i("200497768", "Exception during the method that stops an existing sound.");
                }

                //Use the release method.
                //The release method must be used, even if the code wasn't able to stop the sound.
                try {
                SoundCode.startedMediaPlayer.release();
                } catch (Exception e) {
                    Log.i("200497768", "Exception during the method that stops an existing sound.");
                }
            }

    }

    /**
     * This method starts a sound.
     * We learned that sounds are possible through (Smyth, 2021, p. 603).
     * The example code from the book was complicated, so we didn't use it.
     * We retrieved the code for this method from (Tutorials Point, n.d.).
     */
    public void startSound(Sound sound){
        //Start the tone sound.
        //When the tone sound has finished, start the sound that was provided to this method.
        SoundCode soundCode = this;
        this.startToneSound(new CompletedAction() {
            @Override
            public void completed() {
                try {
                    //Before a sound can be started, ensure that an existing sound no longer exists.
                    SoundCode.stopExistingSound();

                    //Ensure that the sound can start, if possible.
                    soundCode.ensureCanStart();

                    //Retrieve the number from the sound, and start it.
                    int number = sound.getNumber();
                    MediaPlayer mediaPlayer = MediaPlayer.create(soundCode.context, number);

                    //The release method must be used when the sound has finished.
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                        /**
                         * This method will happen when the sound has finished.
                         * The code for this method is responsible for ensuring that the release method is used.
                         */
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            //Use the release method.
                            mp.release();
                        }
                    });

                    //Start the sound.
                    mediaPlayer.start();

                    //Change the field so that the sound code will be able to stop this sound.
                    SoundCode.startedMediaPlayer = mediaPlayer;
                } catch (Exception e) {
                    Log.i("200497768", "Exception during the method that starts a sound.");
                }
            }
        });
    }

    /**
     * This method starts a tone sound.
     */
    private void startToneSound(CompletedAction completedAction) {
        try {
            //Before a sound can be started, ensure that an existing sound no longer exists.
            SoundCode.stopExistingSound();

            //Ensure that the sound can start, if possible.
            this.ensureCanStart();

            //Create the tone sound.
            ToneSound sound = new ToneSound();

            //Retrieve the number from the sound, and start it.
            int number = sound.getNumber();
            MediaPlayer mediaPlayer = MediaPlayer.create(this.context, number);

            //The release method must be used when the sound has finished.
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                /**
                 * This method will happen when the sound has finished.
                 * The code for this method is responsible for ensuring that the release method is used.
                 */
                @Override
                public void onCompletion(MediaPlayer mp) {
                    //Use the release method.
                    mp.release();

                    //Use the completed method.
                    //Another action might be waiting for this sound to finish.
                    //The completed method will cause that action to happen.
                    completedAction.completed();
                }
            });

            //Start the sound.
            mediaPlayer.start();

            //Change the field so that the sound code will be able to stop this sound.
            SoundCode.startedMediaPlayer = mediaPlayer;
        } catch (Exception e) {
            Log.i("200497768", "Exception during the method that starts a sound.");
        }

    }

    /**
     * This method ensures that the sound can start, if possible.
     * This is only possible if the activity field was provided to the sound code.
     * If the field isn't available, this method won't change whether the sound can start.
     */
    private void ensureCanStart() {
        try {
        if (this.activity == null) {
            //This method can't ensure whether the sound can start.
        } else {
            for (int number = 0; number < 10; number = number + 1) {
                AudioManager audioManager = (AudioManager) this.activity.getSystemService(Context.AUDIO_SERVICE);
                audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
            }
        }
        } catch (Exception e) {
            Log.i("200497768", "Exception during the method that ensures that a sound can start.");
        }
    }
}
