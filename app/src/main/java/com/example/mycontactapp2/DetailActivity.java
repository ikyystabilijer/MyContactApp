package com.example.mycontactapp2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private String nama, email, phone, address, jurusan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tvNama = findViewById(R.id.tvDetailNama);
        TextView tvJurusan = findViewById(R.id.tvDetailJurusan);
        TextView tvEmail = findViewById(R.id.tvDetailEmail);
        TextView tvPhone = findViewById(R.id.tvDetailPhone);
        TextView tvAddress = findViewById(R.id.tvDetailAddress);

        Button btnCall = findViewById(R.id.btnCall);
        Button btnEmail = findViewById(R.id.btnEmail);
        Button btnMaps = findViewById(R.id.btnMaps);

        // Menerima data dari Explicit Intent
        Intent intent = getIntent();
        if (intent != null) {
            nama = intent.getStringExtra(MainActivity.EXTRA_NAMA);
            jurusan = intent.getStringExtra(MainActivity.EXTRA_JURUSAN);
            email = intent.getStringExtra(MainActivity.EXTRA_EMAIL);
            phone = intent.getStringExtra(MainActivity.EXTRA_PHONE);
            address = intent.getStringExtra(MainActivity.EXTRA_ADDRESS);

            tvNama.setText(nama != null && !nama.isEmpty() ? nama : "-");
            tvJurusan.setText(jurusan != null && !jurusan.isEmpty() ? jurusan : "-");
            tvEmail.setText(email != null && !email.isEmpty() ? email : "-");
            tvPhone.setText(phone != null && !phone.isEmpty() ? phone : "-");
            tvAddress.setText(address != null && !address.isEmpty() ? address : "-");
        }

        // Implicit Intent: Menelpon nomor telepon
        btnCall.setOnClickListener(v -> {
            if (phone != null && !phone.isEmpty()) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:" + phone));
                startActivity(dialIntent);
            } else {
                Toast.makeText(DetailActivity.this, "Nomor telepon tidak valid!", Toast.LENGTH_SHORT).show();
            }
        });

        // Implicit Intent: Mengirim email
        btnEmail.setOnClickListener(v -> {
            if (email != null && !email.isEmpty()) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:" + email));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Pesan dari MyContactApp");
                try {
                    startActivity(emailIntent);
                } catch (Exception e) {
                    Toast.makeText(DetailActivity.this, R.string.msg_no_email_app, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(DetailActivity.this, "Email tidak valid!", Toast.LENGTH_SHORT).show();
            }
        });

        // Implicit Intent: Melihat alamat di Google Maps
        btnMaps.setOnClickListener(v -> {
            if (address != null && !address.isEmpty()) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(address));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                try {
                    startActivity(mapIntent);
                } catch (Exception e) {
                    Toast.makeText(DetailActivity.this, R.string.msg_no_maps_app, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(DetailActivity.this, "Alamat tidak valid!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
