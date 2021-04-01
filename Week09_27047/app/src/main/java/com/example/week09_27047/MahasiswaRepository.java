package com.example.week09_27047;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class MahasiswaRepository {
    private MahasiswaDAO mahasiswaDAO;
    private LiveData<List<Mahasiswa>> daftarMahasiswa;

    MahasiswaRepository(Application app) {
        MahasiswaRoomDatabase db = MahasiswaRoomDatabase.getDatabase(app);
        mahasiswaDAO = db.mahasiswaDAO();
        daftarMahasiswa = mahasiswaDAO.getAllMahasiswa();
    }

    LiveData<List<Mahasiswa>> getDaftarMahasiswa() {
        return this.daftarMahasiswa;
    }

    public void insert(Mahasiswa mhs) {
        new insertAsyncTask(mahasiswaDAO).execute(mhs);
    }

    public void deleteAll() {
        new deleteAllAsyncTask(mahasiswaDAO).execute();
    }

    public void delete(Mahasiswa mhs) {
        new deleteAsyncTask(mahasiswaDAO).execute(mhs);
    }

    public void update(Mahasiswa mhs) {
        new updateAsyncTask(mahasiswaDAO).execute(mhs);
    }

    private static class insertAsyncTask extends AsyncTask<Mahasiswa, Void, Void> {
        private MahasiswaDAO asyncMahasiswaDAO;

        insertAsyncTask(MahasiswaDAO dao) {
            this.asyncMahasiswaDAO = dao;
        }

        @Override
        protected Void doInBackground(Mahasiswa... mahasiswas) {
            asyncMahasiswaDAO.insert(mahasiswas[0]);
            return null;
        }
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private MahasiswaDAO asyncMahasiswaDAO;
        deleteAllAsyncTask(MahasiswaDAO dao) {
            this.asyncMahasiswaDAO = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            asyncMahasiswaDAO.deleteAll();
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Mahasiswa, Void, Void> {
        private MahasiswaDAO asyncMahasiswaDAO;
        deleteAsyncTask(MahasiswaDAO dao) {
            this.asyncMahasiswaDAO = dao;
        }

        @Override
        protected Void doInBackground(Mahasiswa... mahasiswas) {
            asyncMahasiswaDAO.delete(mahasiswas[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Mahasiswa, Void, Void> {
        private MahasiswaDAO asyncMahasiswaDAO;
        updateAsyncTask(MahasiswaDAO dao) {
            this.asyncMahasiswaDAO = dao;
        }

        @Override
        protected Void doInBackground(Mahasiswa... mahasiswas) {
            asyncMahasiswaDAO.update(mahasiswas[0]);
            return null;
        }
    }
}
