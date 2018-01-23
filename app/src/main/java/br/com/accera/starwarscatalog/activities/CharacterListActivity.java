package br.com.accera.starwarscatalog.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import br.com.accera.starwarscatalog.lists.adapters.CardAdapter;
import br.com.accera.starwarscatalog.R;
import br.com.accera.starwarscatalog.data.services.CharacterService;

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

    @Override
    protected void onRestart() {
        super.onRestart();

        mAdapter.mCharacters = CharacterService.instance.getAllCharacters();
        catalogRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();

        LinearLayout emptyDbLinearLayout = findViewById(R.id.empty_db_placeholder_linear_layout);

        if (mAdapter.mCharacters.size() == 0) {
            emptyDbLinearLayout.setVisibility(View.VISIBLE);
        } else {
            emptyDbLinearLayout.setVisibility(View.GONE);
        }
    }

    private void bindViews() {
        this.catalogRecyclerView = findViewById(R.id.catalog_recycler_view);

        findViewById(R.id.scan_qr_code_fab).setOnClickListener(this);
    }

    private void setUpRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        catalogRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new CardAdapter(CharacterService.instance.getAllCharacters());
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
