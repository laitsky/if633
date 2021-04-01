package com.example.week09_27047;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private MahasiswaViewModel mhsVM;
    private static final int REQUEST_TAMBAH = 1;
    private static final int REQUEST_EDIT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        rv = (RecyclerView) findViewById(R.id.rvMahasiswa);
        final MahasiswaListAdapter adapter = new MahasiswaListAdapter(this);
        rv.setAdapter(adapter);

        rv.setLayoutManager(new LinearLayoutManager(this));
        mhsVM = new ViewModelProvider(this).get(MahasiswaViewModel.class);
        mhsVM.getDaftarMahasiswa().observe(this, new Observer<List<Mahasiswa>>() {
            @Override
            public void onChanged(List<Mahasiswa> mahasiswas) {
                adapter.setDaftarMahasiswa(mahasiswas);
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int posisi = viewHolder.getAdapterPosition();
                        Mahasiswa mhs = adapter.getMahasiswaAtPosition(posisi);
                        if (direction == ItemTouchHelper.LEFT) {
                            Toast.makeText(MainActivity.this,
                                    "Mahasiswa dengan NIM = " + mhs.getNim() + " dihapus",
                                    Toast.LENGTH_LONG).show();
                            mhsVM.delete(mhs);
                        } else if (direction == ItemTouchHelper.RIGHT) {
                            Intent editIntent = new Intent(MainActivity.this, DetilActivity.class);
                            editIntent.putExtra("MAHASISWA", mhs);
                            startActivityForResult(editIntent, REQUEST_EDIT);
                        }
                    }
                });
        helper.attachToRecyclerView(rv);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Mahasiswa mhs = (Mahasiswa) data.getSerializableExtra("MAHASISWA");
            if (requestCode == REQUEST_TAMBAH) {
                mhsVM.insert(mhs);
            } else if (requestCode == REQUEST_EDIT) {
                mhsVM.update(mhs);
            }
        }
        rv.getAdapter().notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            mhsVM.deleteAll();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}