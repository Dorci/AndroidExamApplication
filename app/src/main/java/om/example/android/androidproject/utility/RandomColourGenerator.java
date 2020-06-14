package om.example.android.androidproject.utility;
import android.annotation.SuppressLint;
import android.graphics.Color;

import java.util.Random;

/**
 * Code from : https://stackoverflow.com/questions/4246351/creating-random-colour-in-java#:~:text=Random%20rand%20%3D%20new%20Random()%3B,float%20r%20%3D%20rand.
 */
public class RandomColourGenerator {
    public String[] mColors = {
            "#39add1", // light blue
            "#3079ab", // dark blue
            "#c25975", // mauve
            "#e15258", // red
            "#f9845b", // orange
            "#838cc7", // lavender
            "#7d669e", // purple
            "#53bbb4", // aqua
            "#51b46d", // green
            "#e0ab18", // mustard
            "#637a91", // dark gray
            "#f092b0", // pink
            "#b7c0c7"  // light gray
    };

    public int getColor() {
        String color = "";

        // Randomly select a fact
        Random randomGenerator = new Random(); // Construct a new Random number generator
        int randomNumber = randomGenerator.nextInt(mColors.length);

        color = mColors[randomNumber];
        @SuppressLint("Range") int colorAsInt = Color.parseColor(color);

        return colorAsInt;

    }
}
