package br.com.accera.starwarscatalog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView catalogRecyclerView;
    private CardAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.bindViews();
        this.setUpRecyclerView();
    }

    private void bindViews() {
        this.catalogRecyclerView = findViewById(R.id.catalog_recycler_view);
    }

    private void setUpRecyclerView() {
        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        catalogRecyclerView.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        // Está sendo criado com lista vazia, pois será preenchida posteriormente.
        ArrayList list = new ArrayList<>(0);
        list.add("um");
        list.add("dois");
        list.add("tres");
        list.add("quatro");
        mAdapter = new CardAdapter(list);
        catalogRecyclerView.setAdapter(mAdapter);
    }
}
