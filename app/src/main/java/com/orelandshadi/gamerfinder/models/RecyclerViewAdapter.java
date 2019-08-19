package com.orelandshadi.gamerfinder.models;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.orelandshadi.gamerfinder.R;

import java.util.ArrayList;
import java.util.List;

/* The adapter is the main code responsible for RecyclerView. It holds all the important methods dealing with the implementation of RecylcerView.
 * The basic methods for a successful implementation are:
 *  onCreateViewHolder: which deals with the inflation of the card layout as an item for the RecyclerView.
 *  onBindViewHolder: which deals with the setting of different data and methods related to clicks on particular items of the RecyclerView.
 *  getItemCount: which Returns the length of the RecyclerView.
 *  onAttachedToRecyclerView: which attaches the adapter to the RecyclerView.
 *
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    //The Data class is a custom java class that acts as a structure for holding the information for every item of the RecyclerView.
    private List<Game> mListOfGame;
    private ArrayList<Integer> mFavoriteGames;


    // Constructor
    public RecyclerViewAdapter(Context mContext, List<Game> mListOfGame, ArrayList<Integer> mFavoriteGame) {
        this.mContext = mContext;
        this.mListOfGame = mListOfGame;
        this.mFavoriteGames = mFavoriteGame;
    }

    //is a java class that stores the reference to the card layout views that have to be dynamically
    // modified during the execution of the program by a list of data obtained either by online
    // databases or added in some other way.
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater mInflater = LayoutInflater.from(mContext);
        View view = mInflater.inflate(R.layout.cardview_item_game, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, final int position) {

        viewHolder.tv_game_title.setText(mListOfGame.get(position).getTitle());
        viewHolder.img_game_thumbnail.setImageResource(mListOfGame.get(position).getThumbnail());

        final int currentPosition = position;
        final Game infoGame = mListOfGame.get(position);

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = mListOfGame.get(position).getTitle();
                infoGame.setSelected(!infoGame.isSelected());
                if (infoGame.isSelected()) {
                    viewHolder.cardView.setBackgroundColor(Color.parseColor("#039be5"));
                    viewHolder.tv_game_title.setTextColor(Color.parseColor("#ffffff"));
                    mFavoriteGames.add(infoGame.getID());
                    //Toast.makeText(mContext,"You select " + mListOfGame.get(position).getTitle(),Toast.LENGTH_SHORT).show();
                } else {
                    viewHolder.cardView.setBackgroundColor(Color.parseColor("#ffffff"));
                    viewHolder.tv_game_title.setTextColor(Color.parseColor("#000000"));
                    mFavoriteGames.remove(infoGame.getID());
                    //Toast.makeText(mContext,"unselect "+ mListOfGame.get(position).getTitle(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListOfGame.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_game_title;
        ImageView img_game_thumbnail;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_game_title = (TextView) itemView.findViewById(R.id.game_title_id);
            img_game_thumbnail = (ImageView) itemView.findViewById(R.id.game_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);

        }
    }

}
