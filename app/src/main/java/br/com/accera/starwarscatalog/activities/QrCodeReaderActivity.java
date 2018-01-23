package br.com.accera.starwarscatalog.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import br.com.accera.starwarscatalog.data.services.CharacterService;
import br.com.accera.starwarscatalog.helpers.AlertHelper;
import br.com.accera.starwarscatalog.helpers.DateHelper;
import br.com.accera.starwarscatalog.helpers.GPSTracker;
import br.com.accera.starwarscatalog.network.settings.ApiClient;
import br.com.accera.starwarscatalog.network.settings.ApiInterface;
import br.com.accera.starwarscatalog.R;
import br.com.accera.starwarscatalog.data.models.CharacterModel;
import info.androidhive.barcode.BarcodeReader;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by juliano.nardon on 22/01/18.
 */

public class QrCodeReaderActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {

    private BarcodeReader barcodeReader;
    private boolean isTryingToGetCharacter = false;
    private ProgressDialog loader;
    private CharacterModel characterModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_reader);
        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_fragment);
    }

    @Override
    public void onScanned(final Barcode barcode) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!isTryingToGetCharacter) {
                    isTryingToGetCharacter = true;

                    loader = AlertHelper.instance.loader(QrCodeReaderActivity.this, getString(R.string.loading));
                    loader.show();

                    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    Call<CharacterModel> call = apiService.getCharacter(barcode.displayValue);
                    call.enqueue(new Callback<CharacterModel>() {
                        @Override
                        public void onResponse(Call<CharacterModel> call, Response<CharacterModel> response) {
                            characterModel = response.body();
                            saveLocalData();
                        }

                        @Override
                        public void onFailure(Call<CharacterModel> call, Throwable t) {
                            loader.dismiss();
                            t.printStackTrace();
                            isTryingToGetCharacter = false;
                        }
                    });
                }
            }
        });
    }

    private void saveLocalData() {
        GPSTracker tracker = new GPSTracker(this);
        if (tracker.canGetLocation()) {
            characterModel.setLatitude(tracker.getLatitude());
            characterModel.setLongitude(tracker.getLongitude());
            characterModel.setPickedDate(DateHelper.instance.getCurrentDateString());

            CharacterService.instance.addCharacter(characterModel);

            loader.dismiss();

            Intent intent = new Intent(QrCodeReaderActivity.this, CharacterDetailActivity.class);
            intent.putExtra("characterModelName", characterModel.getName());
            startActivity(intent);
            QrCodeReaderActivity.this.finish();
            isTryingToGetCharacter = false;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100) {
            saveLocalData();
        }
    }

    @Override
    public void onScannedMultiple(List<Barcode> list) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String s) {

    }

    @Override
    public void onCameraPermissionDenied() {
        Toast.makeText(getApplicationContext(), "Camera permission denied!", Toast.LENGTH_LONG).show();
        this.finish();
    }
}
