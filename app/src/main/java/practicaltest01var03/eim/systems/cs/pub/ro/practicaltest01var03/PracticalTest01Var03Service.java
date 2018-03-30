package practicaltest01var03.eim.systems.cs.pub.ro.practicaltest01var03;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PracticalTest01Var03Service extends Service {
    private ProcessingThread processingThread = null;

    public PracticalTest01Var03Service() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String text = intent.getStringExtra("name");
        String text2 = intent.getStringExtra("grupa");
        processingThread = new ProcessingThread(getApplicationContext(), text, text2);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void onDestroy() {
        processingThread.stopThread();
    }
}
