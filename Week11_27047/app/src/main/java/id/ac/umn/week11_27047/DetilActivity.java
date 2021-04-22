package id.ac.umn.week11_27047;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DetilActivity extends AppCompatActivity {
    private EditText etNim, etNama, etEmail, etNomorHP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil);
        etNomorHP = findViewById(R.id.etNomorHp);
        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.etNama);
        etEmail = findViewById(R.id.etEmail);

    }

    public void simpanData(View view) {

    }

    public void batal(View view) {
    }
}