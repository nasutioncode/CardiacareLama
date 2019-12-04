package com.nasution.cardiacare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nasution.cardiacare.model.Risiko;

import org.w3c.dom.Text;

import static android.text.TextUtils.isEmpty;

public class CreateFormJantungActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DatabaseReference database;

    private RadioGroup rdDiabetes;
    private TextView tvKelamin;
    private RadioGroup rdRokok;
    private TextView tvUsia;
    private EditText etTensi;
    private Spinner spinKolesterol;
    private Button btHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_form_jantung);

        final Spinner spinner = findViewById(R.id.spinner_kolesterol);
        if (spinner != null){
            spinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.labels_kolesterol, android.R.layout.simple_spinner_item);
        //drop layout style
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setPrompt("Pilih jumlah kolesterolmu");
        if (spinner != null){
            spinner.setAdapter(adapter);
        }

        // inisialisasi
        rdDiabetes = (RadioGroup) findViewById(R.id.radio_diabetes);
        tvKelamin = (TextView) findViewById(R.id.text_kelamin);
        rdRokok = (RadioGroup) findViewById(R.id.radio_rokok);
        tvUsia = (TextView) findViewById(R.id.text_usia);
        etTensi = (EditText) findViewById(R.id.text_tensi);
        spinKolesterol = (Spinner) findViewById(R.id.spinner_kolesterol);
        btHasil = (Button) findViewById(R.id.button_hasil);

        // referensi ke firebase
        database = FirebaseDatabase.getInstance().getReference();

        // ketika tombol lihat hasil di klik
//        btHasil.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Jika masih ada data yang kosong
//                if (//!isEmpty(rdDiabetes.getCheckedRadioButtonId()) &&
//                        !isEmpty(tvKelamin.getText().toString()) &&
//                        //!isEmpty(rdRokok.getCheckedRadioButtonId()) &&
//                        !isEmpty(tvUsia.getText().toString()) &&
//                        !isEmpty(etTensi.getText().toString()) &&
//                        !isEmpty(spinKolesterol.getSelectedItem().toString()));
////                    submitRisiko(new Risiko(rdDiabetes.getCheckedRadioButtonId(),
////                            tvKelamin.getText().toString(),
////                            rdRokok.getCheckedRadioButtonId(),
////                            tvUsia.getText().toString(),
////                            etTensi.getText().toString(),
////                            spinKolesterol.getSelectedItem()));
//                //else
//                    //Snackbar.make(findViewById(R.id.button_hasil), "Tidak boleh ada data yang kosong", Snackbar.LENGTH_LONG).show();
//            }
//        });
    }

    //Radio Button
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.diabetesYes:
                break;
            case R.id.diabetesNo:
                break;
            default:
                break;
        }

        switch (view.getId()){
            case R.id.rokokYes:
                break;
            case R.id.rokokNo:
                break;
            default:
                break;
        }
    }

    //spinner ketika di item selected
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    //spinner ketika tidak ada item selected
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //Mengecek apakah ada data yang belum terisi
    private boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }

    private void submitRisiko(Risiko risiko){
        database.child("Risiko").push().setValue(risiko).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                //rdDiabetes;
                tvKelamin.setText("");
                //rdRokok;
                tvUsia.setText("");
                etTensi.setText("");
                //spinKolesterol;
                //Snackbar.make(findViewById(R.id.button_hasil), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public void showAlert(View view) {
        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(CreateFormJantungActivity.this);

        myAlertBuilder.setTitle("PERINGATAN");
        myAlertBuilder.setMessage("Apakah anda yakin data yang anda isikan sudah benar?");
        myAlertBuilder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Data diproses", Toast.LENGTH_SHORT).show();
            }
        });
        myAlertBuilder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Data batal diproses", Toast.LENGTH_SHORT).show();
            }
        });
        myAlertBuilder.show();
    }

    //Peringatan untuk meyakinkan apakah data yang diisi sudah benar

}
