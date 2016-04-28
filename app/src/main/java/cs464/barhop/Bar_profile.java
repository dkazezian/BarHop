package cs464.barhop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Bar_profile extends AppCompatActivity {

    private String barname="";
    private String address="";
    private boolean favorited=false;
    private String hours="";
    private String phone="";
    private String tags="";
    private String reviews="";
    private String specevents="";
    private String menu="";
    private InputStream reader;
    private BufferedReader input;




    Bar_profile(int linenumber){
        int go=0;
        try {
            while (go < linenumber) {
                input.readLine();
                go++;
            }
            barname=input.readLine();
            address=input.readLine();
            hours=input.readLine();
            phone=input.readLine();
            tags=input.readLine();
            reviews=input.readLine();
            specevents=input.readLine();
            menu=input.readLine();









        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_profile);
        try {
            reader = getAssets().open("barpageexample");
            input = new BufferedReader(new InputStreamReader(reader));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }



}
