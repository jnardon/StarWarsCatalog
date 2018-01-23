package br.com.accera.starwarscatalog.lists.holders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.accera.starwarscatalog.R;
import br.com.accera.starwarscatalog.activities.CharacterDetailActivity;
import br.com.accera.starwarscatalog.activities.QrCodeReaderActivity;

/**
 * Created by juliano.nardon on 22/01/18.
 */

public class CardHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView nameTextView;
    public TextView urlTextView;

    private Context context;

    public CardHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);

        context = itemView.getContext();
        nameTextView = itemView.findViewById(R.id.name_text_view);
        urlTextView = itemView.findViewById(R.id.url_text_view);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, CharacterDetailActivity.class);
        intent.putExtra("characterModelName", nameTextView.getText());
        context.startActivity(intent);
    }
}