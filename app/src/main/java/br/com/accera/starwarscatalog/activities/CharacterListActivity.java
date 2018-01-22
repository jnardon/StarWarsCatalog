package br.com.accera.starwarscatalog.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.util.ArrayList;

import br.com.accera.starwarscatalog.CardAdapter;
import br.com.accera.starwarscatalog.R;

public class CharacterListActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView catalogRecyclerView;
    private CardAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);

        this.bindViews();
        this.setUpRecyclerView();
    }

    private void bindViews() {
        this.catalogRecyclerView = findViewById(R.id.catalog_recycler_view);

        findViewById(R.id.scan_qr_code_fab).setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.scan_qr_code_fab:
                Intent intent = new Intent(this, QrCodeReaderActivity.class);
                startActivity(intent);
                break;
        }

    }
}
