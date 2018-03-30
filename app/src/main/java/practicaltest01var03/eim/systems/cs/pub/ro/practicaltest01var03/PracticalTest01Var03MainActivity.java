package practicaltest01var03.eim.systems.cs.pub.ro.practicaltest01var03;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var03MainActivity extends AppCompatActivity {
    private TextView textView;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private EditText editText1;
    private EditText editText2;
    private Button nextActivity;
    private Button display;
    private IntentFilter intentFilter = new IntentFilter();

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            textView = (TextView) findViewById(R.id.textView2);
            String val1 = "";
            String val2 = "";
            if(checkBox1.isChecked()) {
                val1 = editText1.getText().toString();
            }
            if(checkBox2.isChecked()) {
                val2 = editText2.getText().toString();
            }
            if(!val1.equals("") && !val2.equals("")) {
                textView.setText(textView.getText().toString() + " " + val1 + " " + val2);
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var03Service.class);
                intent.putExtra("name", editText1.getText().toString());
                intent.putExtra("grupa", editText2.getText().toString());
                getApplicationContext().startService(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Empty String", Toast.LENGTH_LONG).show();
            }
        }
    }

    private NextActivityButtonClickListener nextActivityButtonClickListener = new NextActivityButtonClickListener();
    private class NextActivityButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), PracticalTest01Var03SecondaryActivity.class);
            intent.putExtra("name", editText1.getText().toString());
            intent.putExtra("grupa", editText2.getText().toString());
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_main);
        intentFilter.addAction("intent");

        editText1 = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);

        if (savedInstanceState != null) {
            editText1.setText(savedInstanceState.getString("edit1"));
            editText2.setText(savedInstanceState.getString("edit2"));
        }

        checkBox1 = (CheckBox) findViewById(R.id.checkBox);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);

        display = (Button)findViewById(R.id.button2);
        display.setOnClickListener(buttonClickListener);

        nextActivity = (Button) findViewById(R.id.button);
        nextActivity.setOnClickListener(nextActivityButtonClickListener);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("edit1", editText1.getText().toString());
        savedInstanceState.putString("edit2", editText2.getText().toString());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch(requestCode) {
            case 1:
                String val;
                if(resultCode == 0) {
                    val = "CANCEL";
                } else {
                    val = "OK";
                }
                Toast.makeText(this, "The activity returned with result " + val, Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var03Service.class);
        stopService(intent);
        super.onDestroy();
    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[MessageBroadcast]", intent.getStringExtra("message"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }
}
