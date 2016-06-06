package com.example.angelina_wu.android12;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NotificationFragment extends Fragment {


    /*public static final String ACTION_PING = "com.example.android.pingme.ACTION_PING";
    public static final String EXTRA_MESSAGE= "com.example.android.pingme.EXTRA_MESSAGE";
    public static final String EXTRA_TIMER = "com.example.android.pingme.EXTRA_TIMER";*/

    private View mView;
    private Intent mServiceIntent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_notification, container, false);
        mServiceIntent = new Intent(getActivity().getApplicationContext(), PingService.class);

        Button b = (Button) mView.findViewById(R.id.ping_button);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onPingClick();
            }
        });
        return mView;
    }



    public void onPingClick() {

        Log.d("NotificationFragment","onPingClick");
        int seconds;

        EditText editText = (EditText)mView.findViewById(R.id.edit_seconds);
        String input_seconds = editText.getText().toString();

        EditText msgText = (EditText) mView.findViewById(R.id.edit_reminder);
        String input_message = msgText.getText().toString();

        mServiceIntent.putExtra(PingService.EXTRA_MESSAGE, input_message);
        mServiceIntent.setAction(PingService.ACTION_PING);
        Toast.makeText(getActivity(), R.string.timer_start, Toast.LENGTH_SHORT).show();

        // The number of seconds the timer should run.


        if(input_seconds == null || input_seconds.trim().equals("")){
            // If user didn't enter a value, sets to default.
            seconds = R.string.seconds_default;
        } else {
            seconds = Integer.parseInt(input_seconds);
        }
        int milliseconds = (seconds * 1000);
        mServiceIntent.putExtra(PingService.EXTRA_TIMER, milliseconds);
        // Launches IntentService "PingService" to set timer.
        getActivity().startService(mServiceIntent);

        if(this.getActivity().getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(this.getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
