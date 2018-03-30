package practicaltest01var03.eim.systems.cs.pub.ro.practicaltest01var03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01Var03SecondaryActivity extends AppCompatActivity {
    private TextView textView;
    private TextView textView3;
    private Button ok;
    private Button cancel;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.button3)
                setResult(RESULT_OK, null);
            else if(view.getId() == R.id.button4)
                setResult(RESULT_CANCELED, null);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_secondary);

        Intent intent = getIntent();
        textView = (TextView) findViewById(R.id.textView);
        textView3 = (TextView) findViewById(R.id.textView3);
        ok = (Button) findViewById(R.id.button3);
        cancel = (Button) findViewById(R.id.button4);

        textView.setText(intent.getStringExtra("name"));
        textView3.setText(intent.getStringExtra("grupa"));

        ok = (Button)findViewById(R.id.button3);
        ok.setOnClickListener(buttonClickListener);
        cancel = (Button)findViewById(R.id.button4);
        cancel.setOnClickListener(buttonClickListener);
    }
}
