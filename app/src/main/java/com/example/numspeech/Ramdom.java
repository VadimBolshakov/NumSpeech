package com.example.numspeech;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

class Random_Num {

    public static String Number;
    public static String StringNumber;

    public static  void GetRandomNumber()
    {
        //voice.SelectVoiceByHints(System.Speech.Synthesis.VoiceGender.Female, VoiceAge.Adult, 1, System.Globalization.CultureInfo.CurrentCulture);
        //voice.SetOutputToDefaultAudioDevice();
        //voice.Volume = trackBar1;
        //voice.Rate = trackBar2;

         Map<Integer, String> dict = new HashMap<Integer, String>();


            dict.put(1,"one ");
            dict.put(2,"two ");
            dict.put(3,"three ");
            dict.put(4,"four ");
            dict.put(5,"five ");
            dict.put(6,"six ");
            dict.put(7,"seven ");
            dict.put(8,"eight ");
            dict.put(9,"nine ");
            dict.put(10,"ten ");
            dict.put(11,"eleven ");
            dict.put(12,"twelve ");
            dict.put(13,"thirteen ");
            dict.put(14,"fourteen ");
            dict.put(15,"fifteen ");
            dict.put(16,"sixteen ");
            dict.put(17,"seventeen ");
            dict.put(18,"eighteen ");
            dict.put(19,"nineteen ");
            dict.put(20,"twenty ");
            dict.put(30,"thirty ");
            dict.put(40,"forty ");
            dict.put(50,"fifty ");
            dict.put(60,"sixty ");
            dict.put(70,"seventy ");
            dict.put(80,"eighty ");
            dict.put(90,"ninety ");


        int RanN = ThreadLocalRandom.current().nextInt(1,100);

        if (RanN < 21)
        {
            Number = Integer.toString(RanN);
            StringNumber = dict.get(RanN);

            //await CrossTextToSpeech.Current.Speak(dict[RanN]);
        }
        else
        {
            int dec = (RanN / 10) * 10;
            int num = RanN % 10;
            if (num == 0)
            {
                Number = Integer.toString(RanN);
                StringNumber = dict.get(RanN);
                //await CrossTextToSpeech.Current.Speak(dict[RanN]);
            }
            else
            {
                Number = Integer.toString(RanN);
                //StringNumber = dict.get((int)dec) + dict.get((int)num);
                StringNumber = dict.get(dec) + dict.get(num);
                // await CrossTextToSpeech.Current.Speak(dict[dec] + dict[num]);
            }
        }
    }
}
