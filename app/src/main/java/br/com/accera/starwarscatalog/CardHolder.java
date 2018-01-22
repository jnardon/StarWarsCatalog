package br.com.accera.starwarscatalog;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by juliano.nardon on 22/01/18.
 */

public class CardHolder extends RecyclerView.ViewHolder {

    public TextView title;

    public CardHolder(View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.name_label_text_view);
    }
}