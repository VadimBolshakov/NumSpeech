package com.example.numspeech;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;



public class MainActivity extends AppCompatActivity {

    TextToSpeech TTS;
    SharedPreferences settings;

    final static String text_number_key="TEXT_NUMBER";
    final static String text_string_number_key="TEXT_STRING_NUMBER";

    private String Random_Num_Number="0";
    private String Random_Num_StringNumber="Null";

    private String Delay="3";
    private Boolean No_Delay=false;
    private String Pitch="10";
    private String Speech_Rate="10";

    private Boolean status=false;
    private Boolean status1=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle arg = getIntent().getExtras();

        if (arg != null) {
            Speech_Rate = arg.getString("Speech_Rate");
            TextView text_num_Rate = findViewById(R.id.text_num_Rate);
            text_num_Rate.setText(Speech_Rate);

            Pitch = arg.getString("Pitch");
            TextView text_num_Voice = findViewById(R.id.text_num_Pitch);
            text_num_Voice.setText(Pitch);
            
            Delay = arg.getString("Delay");
            No_Delay = arg.getBoolean("No_Delay", false);
        }else {
            settings=getSharedPreferences(SettnigActivity.SET_FILE, MODE_PRIVATE);

            Speech_Rate=settings.getString(SettnigActivity.SPEECH_RATE_KEY, "10");
            TextView text_num_Rate= findViewById(R.id.text_num_Rate);
            text_num_Rate.setText(Speech_Rate);

            String Pitch=settings.getString(SettnigActivity.PITCH_NUM_KEY, "10");
            TextView text_num_Voice = findViewById(R.id.text_num_Pitch);
            text_num_Voice.setText(Pitch);

            Delay=settings.getString(SettnigActivity.DELAY_NUM_KEY, "3");
            No_Delay=settings.getBoolean(SettnigActivity.NO_DELAY_KEY, false);
        }

        /*Button button_text=(Button) findViewById(R.id.button_start);
        button_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status) {
                    Button button_text=(Button) findViewById(R.id.button_start);
                    if (button_text.getText() !="Start")  button_text.setText("Start");
                    status=false;
                    return;
                }

                if ((!No_Delay) && (Integer.parseInt(Delay)!=0))
                {

                    Button button_text=(Button) findViewById(R.id.button_start);
                    button_text.setText("Stop");

                    status=true;


                    while (true) {
                        Random_Num.GetRandomNumber();
                        Random_Num_Number = Random_Num.Number;
                        Random_Num_StringNumber = Random_Num.StringNumber;

                        TextView text_number = (TextView) findViewById(R.id.text_number);
                        text_number.setText(Random_Num_Number);
                        TextView text_string_number = (TextView) findViewById(R.id.text_string_number);
                        text_string_number.setText(Random_Num_StringNumber);
                        TTS.speak(Random_Num_StringNumber, TextToSpeech.QUEUE_ADD, null, null);
                        //if (!button_text.performClick()) break;
                        if (!button_text.performClick()) return;

                        try {
                            Thread.sleep(Integer.parseInt(Delay) * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });*/

        // Volume voice
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        
        // Setting TextToSpeech
        TTS = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    TTS.setLanguage(Locale.US);
                    TTS.setPitch((Float.parseFloat(Pitch)) / 10);
                    TTS.setSpeechRate((Float.parseFloat(Speech_Rate)) / 10);
                } 
               /* if(status==TextToSpeech.SUCCESS) {
                    if (TTS.isLanguageAvailable(new Locale(Locale.getDefault().getLanguage()))
                    == TextToSpeech.LANG_AVAILABLE) {
                        TTS.setLanguage(new Locale(Locale.getDefault().getLanguage()));
                    } else {
                        TTS.setLanguage(Locale.UK);
                    }
                    TTS.setPitch(1.5f);
                    TTS.setSpeechRate(1.0f);
                    //TTS.Enabled = true;
                } 
                } */
            }
        });
    }

    // save state
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(text_number_key, Random_Num_Number);
        outState.putString(text_string_number_key, Random_Num_StringNumber);

        super.onSaveInstanceState(outState);
    }

    //get state
    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);

        Random_Num_Number=saveInstanceState.getString(text_number_key);
        TextView text_number=(TextView) findViewById(R. id. text_number);
        text_number.setText(Random_Num_Number);
        Random_Num_StringNumber=saveInstanceState.getString(text_string_number_key);
        TextView text_string_number=(TextView) findViewById(R. id. text_string_number);
        text_string_number.setText(Random_Num_StringNumber);
    }

    //Event key "Set"
    public void set(View view) {
      Intent intent_set=new Intent(this, SettnigActivity.class) ;
      startActivity(intent_set);
    }

    //Event key "Start"
   public void start(View view) {
        if (status) {
            Button button_text=(Button) findViewById(R.id.button_start);
            if (button_text.getText() !="Start")  button_text.setText("Start");
            //status=false;
            //return;
            //recreate();
        }

        if ((No_Delay) && (Integer.parseInt(Delay)!=0))
        {
            final TextView text_number = (TextView) findViewById(R.id.text_number);
            final Button button_text=(Button) findViewById(R.id.button_start);
            if (button_text.getText() !="Stop")  button_text.setText("Stop");
            Button button_set=(Button) findViewById(R. id.button_set) ;
            button_set.setEnabled(false);



           Runnable runnable= new Runnable() {
               //volatile boolean running=true;
               @Override

               public void run() {


                   while (!Thread.currentThread().isInterrupted()) {

                       try {
                            text_number.post(new Runnable() {
                                @Override
                                public void run() {

                                    Random_Num.GetRandomNumber();
                                    Random_Num_Number = Random_Num.Number;
                                    Random_Num_StringNumber = Random_Num.StringNumber;


                                    text_number.setText(Random_Num_Number);
                                    TextView text_string_number = (TextView) findViewById(R.id.text_string_number);
                                    text_string_number.setText(Random_Num_StringNumber);

                                    TTS.speak(Random_Num_StringNumber, TextToSpeech.QUEUE_ADD, null, null);

                                    //if (button_text.callOnClick()) {running=false;}
                                    //if (Thread.interrupted()){status1=true; return; }
                                    //if(!running) return;

                                   // if (button_text.callOnClick()) {Thread.interrupted(); return ;}



                                }
                            });
                           Thread.sleep(Integer.parseInt(Delay) * 1000);
                           //thread.interrupt();
                           //recreate();
                       }
                       catch (InterruptedException e) {
                           //status1=true;
                           e.printStackTrace();
                           //break;
                       }
                   }


               }
           };
            Thread thread=new Thread(runnable);
            if (status) {thread.interrupt();
             status=false;}

            else {thread.start();
                status=true;}

//            try {
//
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//                thread.interrupt();
//                return;
//            }
            //





        } else {
            Button button_text=(Button) findViewById(R.id.button_start);
            if (button_text.getText() !="Next")  button_text.setText("Next");

            Random_Num.GetRandomNumber();
            Random_Num_Number=Random_Num.Number;
            Random_Num_StringNumber=Random_Num.StringNumber;

            TextView text_number=(TextView) findViewById(R. id. text_number);
            text_number.setText(Random_Num_Number);
            TextView text_string_number=(TextView) findViewById(R. id. text_string_number);
            text_string_number.setText(Random_Num_StringNumber);
            TTS.speak(Random_Num_StringNumber, TextToSpeech.QUEUE_ADD,null, null);
        }
    }



}