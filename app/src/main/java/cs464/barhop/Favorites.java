package cs464.barhop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;


public class Favorites extends AppCompatActivity {
    public int UniqueId;
    public String BarName;
    private InputStream reader;
    private BufferedReader input;
    private File myFavorites = new File("myFavorites.txt");
    public ArrayList<String> favorites = new ArrayList<>();
    public ArrayList<Boolean> selected = new ArrayList<>();

    //public File myFavorites = "myFavorites.txt";

    private void loadFavorites(){
        Scanner scan = new Scanner("myFavorites.txt");
        try {
            String line = input.readLine();

            while (line!= null) {
                UniqueId = scan.nextInt();
                BarName = scan.next();
                favorites.add(BarName);
                selected.add(false);
                line = scan.nextLine();
            }
        }catch( IOException e){
            e.printStackTrace();
        }

    }


    private void RemoveFavorites() {
        // Handle app bar item clicks here. The app bar
        // automatically handles clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        boolean p;
        ArrayList<Integer> del = new ArrayList<>();

        for(int i =0; i<selected.size();i++){
            p = selected.get(i);
            if( p){

                favorites.remove(i);
                del.add(i);
            }
        }
        for(int k = del.size(); k>0;k--) {
            selected.remove(k);
        }

    }
    public void removeLineFromFile( String lineToRemove) {

        try {



            if (!myFavorites.isFile()) {
                System.out.println("Parameter is not an existing file");
                return;
            }

            //Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(myFavorites.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader(myFavorites));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String line;

            //Read from the original file and write to the new
            //unless content matches data to be removed.
            while ((line = br.readLine()) != null) {

                if (!line.trim().equals(lineToRemove)) {

                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();

            //Delete the original file
            if (!myFavorites.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(myFavorites))
                System.out.println("Could not rename file");

        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        try {
            reader = getAssets().open("myFavorites.txt");
            input = new BufferedReader(new InputStreamReader(reader));
            //BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
