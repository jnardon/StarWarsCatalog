package br.com.accera.starwarscatalog.lists.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.accera.starwarscatalog.lists.holders.CardHolder;
import br.com.accera.starwarscatalog.R;
import br.com.accera.starwarscatalog.data.models.CharacterModel;

/**
 * Created by juliano.nardon on 22/01/18.
 */

public class CardAdapter extends RecyclerView.Adapter<CardHolder> {

    public List<CharacterModel> mCharacters;

    public CardAdapter(ArrayList characters) {
        mCharacters = characters;
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CardHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_card_view, parent, false));
    }

    @Override
    public void onBindViewHolder(CardHolder holder, int position) {
        holder.nameTextView.setText(mCharacters.get(position).getName());
        holder.urlTextView.setText(mCharacters.get(position).getUrl());
    }


    @Override
    public int getItemCount() {
        return mCharacters != null ? mCharacters.size() : 0;
    }

}
