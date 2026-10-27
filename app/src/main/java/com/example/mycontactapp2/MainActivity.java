package com.example.mycontactapp2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNama, etEmail, etPhone, etAddress;
    private Spinner spJurusan;

    public static final String EXTRA_NAMA = "EXTRA_NAMA";
    public static final String EXTRA_EMAIL = "EXTRA_EMAIL";
    public static final String EXTRA_PHONE = "EXTRA_PHONE";
    public static final String EXTRA_ADDRESS = "EXTRA_ADDRESS";
    public static final String EXTRA_JURUSAN = "EXTRA_JURUSAN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = findViewById(R.id.etNama);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        spJurusan = findViewById(R.id.spJurusan);
        Button btnKirim = findViewById(R.id.btnKirim);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.jurusan_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJurusan.setAdapter(adapter);

        btnKirim.setOnClickListener(v -> sendData());
    }

    private void sendData() {
        String nama = etNama.getText() != null ? etNama.getText().toString().trim() : "";
        String email = etEmail.getText() != null ? etEmail.getText().toString().trim() : "";
        String phone = etPhone.getText() != null ? etPhone.getText().toString().trim() : "";
        String address = etAddress.getText() != null ? etAddress.getText().toString().trim() : "";
        String jurusan = spJurusan.getSelectedItem() != null ? spJurusan.getSelectedItem().toString() : "";

        boolean isError = false;

        if (TextUtils.isEmpty(nama)) {
            etNama.setError(getString(R.string.error_empty_field));
            isError = true;
        }

        if (TextUtils.isEmpty(email)) {
            etEmail.setError(getString(R.string.error_empty_field));
            isError = true;
        }

        if (TextUtils.isEmpty(phone)) {
            etPhone.setError(getString(R.string.error_empty_field));
            isError = true;
        }

        if (TextUtils.isEmpty(address)) {
            etAddress.setError(getString(R.string.error_empty_field));
            isError = true;
        }

        if (!isError) {
            // Explicit Intent untuk berpindah ke DetailActivity dengan membawa data
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra(EXTRA_NAMA, nama);
            intent.putExtra(EXTRA_EMAIL, email);
            intent.putExtra(EXTRA_PHONE, phone);
            intent.putExtra(EXTRA_ADDRESS, address);
            intent.putExtra(EXTRA_JURUSAN, jurusan);
            startActivity(intent);
        }
    }
}
