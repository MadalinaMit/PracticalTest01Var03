package practicaltest01var03.eim.systems.cs.pub.ro.practicaltest01var03;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

/**
 * Created by MadalinaM on 3/30/2018.
 */

public class ProcessingThread extends Thread {
    private boolean isRunning = true;
    Context context;
    String text;
    String text2;

    ProcessingThread(Context context, String text, String text2) {
        this.context = context;
        this.text = text;
        this.text2 = text;
    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("[ProcessingThread]", "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction("intent");
        intent.putExtra("message", new Date(System.currentTimeMillis()) + " " + text + " " + text2);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
