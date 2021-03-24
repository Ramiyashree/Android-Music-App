package com.example.cs478_project2;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView nameView;
    private String layout = "listView";
    private ArrayList<SubjectData> arrayList = new ArrayList<SubjectData>();
    boolean islistview = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Getting the RecyclerView
        nameView = (RecyclerView) findViewById(R.id.recycler_view);

        //Adding SubjectData Object which contains Song Title, Artist/Band Name, Song Link, Thumbnail, Song Wiki, Artist/Band Wiki
        arrayList.add(new SubjectData("Surf's Up", "The Beach Boys", Uri.parse("https://www.youtube.com/watch?v=tyOYQ8qfFng"), R.drawable.pic1,Uri.parse("https://en.wikipedia.org/wiki/Surf%27s_Up_(album)") ,Uri.parse("https://en.wikipedia.org/wiki/The_Beach_Boys")));
        arrayList.add(new SubjectData("Masters of War", "Bob Dylan",Uri.parse("https://www.youtube.com/watch?v=JEmI_FT4YHU"), R.drawable.pic2, Uri.parse("https://en.wikipedia.org/wiki/Masters_of_War"), Uri.parse("https://en.wikipedia.org/wiki/Bob_Dylan") ));
        arrayList.add(new SubjectData("Hey Jude", "The Beatles ",Uri.parse("https://www.youtube.com/watch?v=A_MjCqQoLLA"), R.drawable.pic3, Uri.parse("https://en.wikipedia.org/wiki/Hey_Jude"), Uri.parse("https://en.wikipedia.org/wiki/The_Beatles") ));
        arrayList.add(new SubjectData("Cheap Thrills", "Sia",Uri.parse("https://www.youtube.com/watch?v=nYh-n7EOtMA"), R.drawable.pic4, Uri.parse("https://en.wikipedia.org/wiki/Cheap_Thrills_(song)"), Uri.parse("https://en.wikipedia.org/wiki/Sia_(musician)") ));
        arrayList.add(new SubjectData("Old Man", "Neil Young", Uri.parse("https://www.youtube.com/watch?v=An2a1_Do_fc"), R.drawable.pic5, Uri.parse("https://en.wikipedia.org/wiki/Old_Man_(song)"), Uri.parse("https://en.wikipedia.org/wiki/Neil_Young") ));
        arrayList.add(new SubjectData("Big Yellow Taxi", "Joni Mitchell",Uri.parse("https://www.youtube.com/watch?v=94bdMSCdw20"), R.drawable.pic6, Uri.parse("https://en.wikipedia.org/wiki/Big_Yellow_Taxi"), Uri.parse("https://en.wikipedia.org/wiki/Joni_Mitchell") ));
        arrayList.add(new SubjectData("Sorry", "Justin Bieber",Uri.parse("https://www.youtube.com/watch?v=8ELbX5CMomE"), R.drawable.pic7, Uri.parse("https://en.wikipedia.org/wiki/Sorry_(Justin_Bieber_song)"), Uri.parse("https://en.wikipedia.org/wiki/Justin_Bieber") ));
        arrayList.add(new SubjectData("Blank Space", "Taylor Swift",Uri.parse("https://www.youtube.com/watch?v=e-ORhEE9VVg"), R.drawable.pic8, Uri.parse("https://en.wikipedia.org/wiki/Blank_Space"), Uri.parse("https://en.wikipedia.org/wiki/Taylor_Swift") ));
        arrayList.add(new SubjectData("Roar", "Katy Perry",Uri.parse("https://www.youtube.com/watch?v=CevxZvSJLk8"), R.drawable.pic9, Uri.parse("https://en.wikipedia.org/wiki/Roar_(song)"), Uri.parse("https://en.wikipedia.org/wiki/Katy_Perry") ));
        arrayList.add(new SubjectData("Sugar", "Maroon 5",Uri.parse("https://www.youtube.com/watch?v=09R8_2nJtjg"), R.drawable.pic10, Uri.parse("https://en.wikipedia.org/wiki/Sugar_(Maroon_5_song)"), Uri.parse("https://en.wikipedia.org/wiki/Maroon_5") ));

        // passing the data to be displayed in MyAdapter
        MyAdapter adapter = new MyAdapter(arrayList, this);
        nameView.setHasFixedSize(true);
        nameView.setAdapter(adapter);

        //Retrieving the state of the recycler view
        if(savedInstanceState!=null)
        {
            layout = savedInstanceState.getString("changestate");
        }
        layoutset();
    }

    //Setting the layout based on the user preference
    protected void layoutset()
    {
        if(layout == "listView")
        {
            nameView.setLayoutManager(new LinearLayoutManager(this)); //For List View
            islistview = true;
        }
        else
        {
            nameView.setLayoutManager(new GridLayoutManager(this,2)); //For Grid View
            islistview = false;
        }
    }

    //Saving the state for retaining the layout of the recyclerView
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("changestate", layout);
    }

    //OptionsMenu Creation and ItemSelection
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.layout_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        nameView = (RecyclerView) findViewById(R.id.recycler_view);
        switch (item.getItemId()) {
            case R.id.listview: // Cards List
                Log.i("ON_CLICK", "listview");
                layout = "listView";
                if(!islistview)
                {layoutset();}
                break;
            case R.id.gridview: // Grid  Layout
                Log.i("ON_CLICK", "gridview");
                layout = "gridView";
                if(islistview)
                {layoutset();}
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}