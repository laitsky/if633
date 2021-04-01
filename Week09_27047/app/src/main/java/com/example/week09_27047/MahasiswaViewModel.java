package com.example.week09_27047;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MahasiswaViewModel extends AndroidViewModel {
    private MahasiswaRepository mahasiswaRepository;
    private LiveData<List<Mahasiswa>> daftarMahasiswa;

    public MahasiswaViewModel(@NonNull Application application) {
        super(application);
        mahasiswaRepository = new MahasiswaRepository(application);
        daftarMahasiswa = mahasiswaRepository.getDaftarMahasiswa();
    }

    LiveData<List<Mahasiswa>> getDaftarMahasiswa() {
        return this.daftarMahasiswa;
    }

    public void insert(Mahasiswa mhs) {
        mahasiswaRepository.insert(mhs);
    }

    public void deleteAll() {
        mahasiswaRepository.deleteAll();
    }

    public void delete(Mahasiswa mhs) {
        mahasiswaRepository.delete(mhs);
    }

    public void update(Mahasiswa mhs) {
        mahasiswaRepository.update(mhs);
    }
}
