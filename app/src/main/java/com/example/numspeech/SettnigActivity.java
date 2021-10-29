package com.example.numspeech;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class SettnigActivity extends AppCompatActivity {

    public static  final String SET_FILE="Setting";
    public static final String SPEECH_RATE_KEY="Text_SpeechRate";
    public static final String DELAY_NUM_KEY="Text_delay_num";
    public static final String PITCH_NUM_KEY="Text_Pitch_num";
    public static final String NO_DELAY_KEY="FALSE";
    SharedPreferences settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settnig);
        settings=getSharedPreferences(SET_FILE, MODE_PRIVATE);

        TextView textView_SpeechRate_num= findViewById(R.id.text_SpeechRate_num);
        String SpeechRate_num=settings.getString(SPEECH_RATE_KEY, "10");
        textView_SpeechRate_num.setText(SpeechRate_num);
        SeekBar seekBar_SpeechRate=(SeekBar) findViewById(R.id.seekBar_SpeechRate);
        seekBar_SpeechRate.setProgress(Integer.parseInt(SpeechRate_num));

        TextView textView_Delay_num= findViewById(R.id.text_delay_num);
        String Delay_num=settings.getString(DELAY_NUM_KEY, "3");
        textView_Delay_num.setText(Delay_num);
        SeekBar seekBar_delay=(SeekBar) findViewById(R.id.seekBar_delay);
        seekBar_delay.setProgress(Integer.parseInt(Delay_num));

        TextView textView_Pitch_num= findViewById(R.id.text_Pitch_num);
        String Pitch_num=settings.getString(PITCH_NUM_KEY, "10");
        textView_Pitch_num.setText(Pitch_num);
        SeekBar seekBar_Pitch=(SeekBar) findViewById(R.id.seekBar_Pitch);
        seekBar_Pitch.setProgress(Integer.parseInt(Pitch_num));

        Switch swich_No_delay=findViewById(R.id.switch_no_delay);
        Boolean No_delay=settings.getBoolean(NO_DELAY_KEY, false);
        swich_No_delay.setChecked(No_delay);

        //SeekBar seekBar_delay=(SeekBar) findViewById(R.id.seekBar_delay);
        final TextView textView_delay=(TextView) findViewById(R.id.text_delay_num);
        seekBar_delay.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView_delay.setText(String.valueOf(seekBar.getProgress()));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        //SeekBar seekBar_SpeechRate=(SeekBar) findViewById(R.id.seekBar_SpeechRate);
        final TextView textView_SpeechRate=(TextView) findViewById(R.id.text_SpeechRate_num);
        seekBar_SpeechRate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView_SpeechRate.setText(String.valueOf(seekBar.getProgress()));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        //SeekBar seekBar_Pitch=(SeekBar) findViewById(R.id.seekBar_Pitch);
        final TextView textView_Pitch=(TextView) findViewById(R.id.text_Pitch_num);
        seekBar_Pitch.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView_Pitch.setText(String.valueOf(seekBar.getProgress()));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }

    public void back(View view) {
        Intent intent=new Intent(this, MainActivity.class);

        TextView textView_SpeechRate_num= findViewById(R.id.text_SpeechRate_num);
        String Speech_Rate=textView_SpeechRate_num.getText().toString();

        TextView textView_Pitch_num= findViewById(R.id.text_Pitch_num);
        String Pitch=textView_Pitch_num.getText().toString();

        TextView textView_Delay_num= findViewById(R.id.text_delay_num);
        String Delay=textView_Delay_num.getText().toString();

        Switch swich_No_delay=findViewById(R.id.switch_no_delay);
        Boolean No_Delay=swich_No_delay.isChecked();

        intent.putExtra("Speech_Rate", Speech_Rate);
        intent.putExtra("Pitch", Pitch);
        intent.putExtra("Delay", Delay);
        intent.putExtra("No_Delay", No_Delay);

        startActivity(intent);
    }


    public void save_setting(View view) {
        TextView textView_SpeechRate_num= findViewById(R.id.text_SpeechRate_num);
        String SpeechRate_num=textView_SpeechRate_num.getText().toString();
        SharedPreferences.Editor setEditor=settings.edit();
        setEditor.putString(SPEECH_RATE_KEY, SpeechRate_num);

        TextView textView_Delay_num= findViewById(R.id.text_delay_num);
        String Delay_num=textView_Delay_num.getText().toString();
        setEditor.putString(DELAY_NUM_KEY, Delay_num);

        TextView textView_Pitch_num= findViewById(R.id.text_Pitch_num);
        String Pitch_num=textView_Pitch_num.getText().toString();
        setEditor.putString(PITCH_NUM_KEY, Pitch_num);

        Switch swich_No_delay=findViewById(R.id.switch_no_delay);
        Boolean No_delay=swich_No_delay.isChecked();
        setEditor.putBoolean(NO_DELAY_KEY, No_delay);

        setEditor.apply();
        Toast toast=Toast.makeText(this, "settings have been saved", Toast.LENGTH_SHORT);
        toast.show();

    }
}