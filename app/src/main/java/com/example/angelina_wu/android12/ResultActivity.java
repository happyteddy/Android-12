package com.example.angelina_wu.android12;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_result);
        String message = getIntent().getStringExtra(PingService.EXTRA_MESSAGE);
        TextView text = (TextView) findViewById(R.id.result_message);
        text.setText(message);
    }

    public void onSnoozeClick(View v) {
        Intent intent = new Intent(getApplicationContext(), PingService.class);
        intent.setAction(PingService.ACTION_SNOOZE);
        startService(intent);
        //this.finish();
        Intent returnIntent = new Intent(this, MainActivity.class);
        startActivity(returnIntent);
    }

    public void onDismissClick(View v) {
        Intent intent = new Intent(getApplicationContext(), PingService.class);
        intent.setAction(PingService.ACTION_DISMISS);
        startService(intent);
        //this.finish();
        Intent returnIntent = new Intent(this, MainActivity.class);
        startActivity(returnIntent);
    }
}

