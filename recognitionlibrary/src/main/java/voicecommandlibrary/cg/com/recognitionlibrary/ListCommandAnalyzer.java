package voicecommandlibrary.cg.com.recognitionlibrary;

import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by devicelab2 on 11/09/19.
 */

public class ListCommandAnalyzer {
    ArrayList<String> scrollDownCommands = new ArrayList<>();
    ArrayList<String> scrollUpCommands = new ArrayList<>();

    private int position = 0;
    public int getPosition(){
        return position;
    }

    public ArrayList<Boolean> findListCommand(ArrayList<String> matches, int position, final TextToSpeech t1,
                                              RecyclerView.SmoothScroller smoothScroller, RecyclerView recyclerRestaurants, final boolean destroyed) {
        scrollDownCommands.add("slide down");
        scrollDownCommands.add("scroll down");
        scrollDownCommands.add("swipe down");
        scrollUpCommands.add("slide up");
        scrollUpCommands.add("swipe up");
        scrollUpCommands.add("scroll up");

        ArrayList<Boolean> inputIsReceivedAndScrolled = new ArrayList<>();

        //TextToSpeech textToSpeech=new TextToSpeech(context,onInitListener);


        for (String input : matches) {
            if (scrollDownCommands.contains(input)) {
                smoothScroller.setTargetPosition(position + 4);
                this.position = position+4;
                recyclerRestaurants.getLayoutManager().startSmoothScroll(smoothScroller);
                inputIsReceivedAndScrolled.add(true);
                inputIsReceivedAndScrolled.add(true);
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
            if (scrollUpCommands.contains(input)) {
                smoothScroller.setTargetPosition(position - 4);
                this.position = position-4;
                recyclerRestaurants.getLayoutManager().startSmoothScroll(smoothScroller);
                inputIsReceivedAndScrolled.add(true);
                inputIsReceivedAndScrolled.add(true);
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
        if (inputIsReceivedAndScrolled.size() <= 0) {
            inputIsReceivedAndScrolled.add(false);
            inputIsReceivedAndScrolled.add(false);
        }

        return inputIsReceivedAndScrolled;
    }
}
