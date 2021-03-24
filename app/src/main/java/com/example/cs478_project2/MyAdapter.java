package com.example.cs478_project2;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private ArrayList<SubjectData> nameList;
    private Context context;
    int position;

    //Data passed from MainActivity
    public MyAdapter(ArrayList<SubjectData> theList, Context c){
        nameList = theList;
        context = c;
    }


    // Initialise the viewHolder and creates the associated view using the inflater
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View listView = inflater.inflate(R.layout.song_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listView);
        return viewHolder;
    }

    //Fills the content to the viewholder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SubjectData subjectData = nameList.get(position);
        holder.name.setText(subjectData.SubjectName);
        holder.artist.setText(subjectData.ArtistName);
        holder.image.setImageResource(subjectData.Image);
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    //For implementing OnClickListener and OnContextMenuListener
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener{

        public TextView name;
        public TextView artist;
        public ImageView image;
        private View itemView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView);
            artist = (TextView) itemView.findViewById(R.id.artist);
            image = (ImageView) itemView.findViewById(R.id.imageView);
            this.itemView = itemView;
            itemView.setOnCreateContextMenuListener(this); //set context menu for each list item (LONG CLICK)
            itemView.setOnClickListener(this); //set short click listener
        }

        //listener for option menu items clicked
        @Override
        public void onClick(View v) {
            SubjectData subjectData = nameList.get(getAdapterPosition());
            Intent aIntent = new Intent(Intent.ACTION_VIEW);
            aIntent.setData(subjectData.Link);
            Log.i("link", "inside menu1" + aIntent);
            aIntent.addCategory(Intent.CATEGORY_BROWSABLE) ;
            context.startActivity(aIntent);
        }

        //ContextMenu Implementation
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            //inflate menu from xml
            MenuInflater inflater = new MenuInflater(v.getContext());
            inflater.inflate(R.menu.context_menu, menu);

            // Setting ClickListener to the ContextMenu options
            menu.getItem(0).setOnMenuItemClickListener(onMenu);
            menu.getItem(1).setOnMenuItemClickListener(onMenu);
            menu.getItem(2).setOnMenuItemClickListener(onMenu);

        }
            //listener for menu items clicked
        private final MenuItem.OnMenuItemClickListener onMenu = new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item){
                Log.i("link","hello menu");
                switch(item.getItemId()){
                    // CASE 1: For Video Playing
                    case R.id.menu1:
                        SubjectData subjectData = nameList.get(getAdapterPosition());
                        Intent aIntent = new Intent(Intent.ACTION_VIEW);
                        aIntent.setData(subjectData.Link);
                        aIntent.addCategory(Intent.CATEGORY_BROWSABLE) ;
                        context.startActivity(aIntent);
                        break;
                    // CASE 2: For Song Wikipedia
                    case R.id.menu2:
                        SubjectData subjectData1 = nameList.get(getAdapterPosition());
                        Intent aIntent1 = new Intent(Intent.ACTION_VIEW);
                        aIntent1.setData(subjectData1.Wiki_song);
                        aIntent1.addCategory(Intent.CATEGORY_BROWSABLE) ;
                        context.startActivity(aIntent1);
                        break;
                    // CASE 3: For Artist Wikipedia
                    case R.id.menu3:
                        SubjectData subjectData2 = nameList.get(getAdapterPosition());
                        Intent aIntent2 = new Intent(Intent.ACTION_VIEW);
                        aIntent2.setData(subjectData2.Wiki_artist);
                        aIntent2.addCategory(Intent.CATEGORY_BROWSABLE) ;
                        context.startActivity(aIntent2);
                        break;
                }
                return true;
            }
        };
    }
}
