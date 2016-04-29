package cs464.barhop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Favorites extends AppCompatActivity {
    public int UniqueId;
    public String BarName;
    ArrayList<String> favorites = new ArrayList<String>();
    ArrayList<Boolean> selected = new ArrayList<Boolean>();

    //public File myFavorites = "myFavorites.txt";

    private void loadFavorites(){
        Scanner scan = new Scanner("myFavorites.txt");
        try {
            string line = input.readLine();
            while (line!= null) {
                UniqueId = scan.nextInt();
                BarName = input.next();
                line = input.readLine();
            }
        }catch( IOException e){
            e.printStackTrace();
        }

    }


    public boolean onOptionsItemSelected(ArrayList selected) {
        // Handle app bar item clicks here. The app bar
        // automatically handles clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = selected.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_profile);
        try {
            reader = getAssets().open("myFavorites.txt");
            input = new BufferedReader(new InputStreamReader(reader));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
