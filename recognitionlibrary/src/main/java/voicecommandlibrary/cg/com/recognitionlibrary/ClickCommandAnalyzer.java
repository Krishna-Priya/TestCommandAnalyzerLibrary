package voicecommandlibrary.cg.com.recognitionlibrary;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

/**
 * Created by devicelab2 on 11/09/19.
 */

public class ClickCommandAnalyzer {
    ArrayList<String> listOfClickCommands = new ArrayList<>();

    public ArrayList<String> findClickCommand(ArrayList<String> matches, final TextToSpeech t1,
                                              Context activity, final boolean destroyed, ViewPager viewPager) {
        listOfClickCommands.add("click booking status");
        listOfClickCommands.add("show booking status");
        listOfClickCommands.add("booking status");

        listOfClickCommands.add("click next tab");
        listOfClickCommands.add("show next tab");
        listOfClickCommands.add("next tab");

        listOfClickCommands.add("click previous tab");
        listOfClickCommands.add("show previous tab");
        listOfClickCommands.add("previous tab");

        listOfClickCommands.add("click find restaurant");
        listOfClickCommands.add("show find restaurant");
        listOfClickCommands.add("find restaurant");

        listOfClickCommands.add("click search bar");



        ArrayList<String> inputIsReceivedAndScrolled = new ArrayList<>();

        for (String input : matches) {
            if (listOfClickCommands.contains(input)) {
                inputIsReceivedAndScrolled.add("true");
                inputIsReceivedAndScrolled.add("true");
                if (input.contains("booking status") || input.contains("next tab")){
                    int totalTabs =viewPager.getChildCount() -1;
                    if (viewPager.getCurrentItem() != totalTabs)
                        viewPager.setCurrentItem((viewPager.getCurrentItem())+1);
                }
                if (input.contains("find restaurant") || input.contains("previous tab")){
                    if (viewPager.getCurrentItem() != 0) {
                        viewPager.setCurrentItem((viewPager.getCurrentItem()) - 1);
                    }

                }

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
            inputIsReceivedAndScrolled.add("false");
            inputIsReceivedAndScrolled.add("false");
        }

        return inputIsReceivedAndScrolled;
    }
}
