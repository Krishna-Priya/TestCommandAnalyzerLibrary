package voicecommandlibrary.cg.com.recognitionlibrary;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;

import java.util.ArrayList;

/**
 * Created by devicelab2 on 09/09/19.
 */

public class NavigationCommandAnalyzer {
    public static ArrayList<Boolean> findNavigationCommand(ArrayList<String> matches, final TextToSpeech t1, final Activity activity, final Boolean destroyed) {
        ArrayList<String> listOfCommands = new ArrayList<>();
        listOfCommands.add("go back");
        listOfCommands.add("go to previous page");
        listOfCommands.add("previous page");
        listOfCommands.add("exit");
        listOfCommands.add("yes");
        listOfCommands.add("no");

        ArrayList<Boolean> inputIsReceivedAndGoBack = new ArrayList<>();

        for (String input : matches) {

            if (listOfCommands.contains(input))  {
                if (input.equals("exit")){
                    inputIsReceivedAndGoBack.add(true);
                    inputIsReceivedAndGoBack.add(true);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            t1.speak("Are you sure you want to exit?",
                                    TextToSpeech.QUEUE_FLUSH, null, "exit confirmation");
                        }
                    }, 2000);
                    break;
                }
                if(input.equals("yes")){
                    inputIsReceivedAndGoBack.add(true);
                    inputIsReceivedAndGoBack.add(true);
                    activity.finishAffinity();
                    break;
                }
                if (input.equals("no")){
                    inputIsReceivedAndGoBack.add(true);
                    inputIsReceivedAndGoBack.add(true);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (!destroyed)
                                t1.speak("You can give next command after the beep!",
                                        TextToSpeech.QUEUE_FLUSH, null, "resume");

                        }
                    }, 1000);
                    break;
                }
                inputIsReceivedAndGoBack.add(true);
                inputIsReceivedAndGoBack.add(true);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (!destroyed) {
                            activity.finish();
                        }
                    }
                });
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!destroyed) {
                            t1.speak("You can give next command after the beep!",
                                    TextToSpeech.QUEUE_FLUSH, null, "next command");
                        }
                    }
                }, 2000);
                break;
            }
        }
        if (inputIsReceivedAndGoBack.size() <= 0) {
            inputIsReceivedAndGoBack.add(false);
            inputIsReceivedAndGoBack.add(false);
        }

        return inputIsReceivedAndGoBack;
    }

}
