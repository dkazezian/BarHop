package cs464.barhop;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Favorites extends ListActivity implements android.widget.CompoundButton.OnCheckedChangeListener{
    ListView lv;
    ArrayList<Favorite> favoritesList;
    FavoritesAdapter favAdapter;
    public int lineNumber;
    public String BarName;
    //  public String[] Bars;
    // private List<Favorite>;
    public ArrayList<String> values;
    private InputStream reader;
    private BufferedReader input;
    private File myFavorites = new File("myFavorites.txt");
    public ArrayList<Favorite> favorites = new ArrayList<>();
    public ArrayList<Boolean> selected = new ArrayList<>();
    //public File myFavorites = "myFavorites.txt";


    private void loadFavorites() {
        Scanner scan = new Scanner(reader);
        try {
            String line = input.readLine();

            while (scan.hasNextLine()) {
                BarName = scan.next();
                lineNumber = scan.nextInt();
                Favorite temp = new Favorite(BarName, lineNumber);
                favorites.add(temp);
                selected.add(false);


                line = scan.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
        int pos = lv.getPositionForView(buttonView);
        if(pos != ListView.INVALID_POSITION){
            Favorite p = favorites.get(pos);
            p.setSelected(isChecked);
        }


    }

    private void RemoveFavorites() {
        // Handle app bar item clicks here. The app bar
        // automatically handles clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        boolean p;
        String REMOVE;
        ArrayList<Integer> del = new ArrayList<>();
        //   ArrayList<String> re = new ArrayList<>();
        for (int i = 0; i < selected.size(); i++) {
            p = selected.get(i);
            if (p) {
                REMOVE = favorites.get(i).Name + " " + favorites.get(i).LineNum;
                removeLineFromFile(REMOVE);
                favorites.remove(i);
                del.add(i);
            }
        }
        for (int k = del.size(); k > 0; k--) {
            selected.remove(k);

        }

    }

    private void addFavorite(String Nam, int barId) {

        try {


            if (!myFavorites.isFile()) {
                //System.out.println("Parameter is not an existing file");
                return;
            }

            //Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(myFavorites.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader(myFavorites));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String line;
            Favorite Create = new Favorite(Nam, barId);
            //Read from the original file and write to the new
            //unless content matches data to be removed.
            while ((line = br.readLine()) != null) {
                if (favorites.contains(Create)) {
                    return;
                }

            }
            pw.println(line);
            pw.close();
            br.close();

            //Delete the original file
            if (!myFavorites.delete()) {
                //System.out.println("Could not delete file");
                return;
            }

            //Rename the new file to the filename the original file had.
            // if (!tempFile.renameTo(myFavorites))
            //System.out.println("Could not rename file");

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void removeLineFromFile(String lineToRemove) {

        try {


            if (!myFavorites.isFile()) {
                //System.out.println("Parameter is not an existing file");
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
                //System.out.println("Could not delete file");
                return;
            }

            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(myFavorites)){}
               // System.out.println("Could not rename file");

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
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
            loadFavorites();
            //BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        lv = (ListView)findViewById(R.id.listView);




    }
    private void displayFavorites(){


        favAdapter = new FavoritesAdapter(favorites,this);
        //ListView listView = (ListView)findViewById(R.id.listView);
        lv.setAdapter(favAdapter);

    }
    public void searchmethod(View veiw){
                Intent activityChangeIntent = new Intent(Favorites.this, Search.class);
                Favorites.this.startActivity(activityChangeIntent);
    }
    public void nearbymethod(View veiw){
        Intent intent = new Intent(Favorites.this, Searchresults.class);
        ArrayList <Integer> finalresults=new ArrayList();
        finalresults.add(1);
        finalresults.add(11);
        finalresults.add(21);
        finalresults.add(31);
        finalresults.add(41);
        finalresults.add(51);
        finalresults.add(61);
        finalresults.add(71);
        finalresults.add(81);
        finalresults.add(91);
        finalresults.add(101);
        finalresults.add(111);
        finalresults.add(121);
        finalresults.add(131);
        finalresults.add(141);
        finalresults.add(151);
        finalresults.add(161);
        finalresults.add(171);
        finalresults.add(181);
        finalresults.add(191);
        Bundle b = new Bundle();
        b.putIntegerArrayList("searchresults", finalresults);
        intent.putExtras(b);
        Favorites.this.startActivity(intent);
        finish();
    }
    public void favemethod(View veiw){
        //donothing
    }



}