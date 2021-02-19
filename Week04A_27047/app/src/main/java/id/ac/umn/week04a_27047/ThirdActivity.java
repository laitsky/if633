package id.ac.umn.week04a_27047;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment firstFragment = new FragmentFirst();
        fragmentTransaction.replace(R.id.third_activity_fragment_1, firstFragment);

        Fragment secondFragment = new FragmentSecond();
        fragmentTransaction.replace(R.id.third_activity_fragment_2, secondFragment);

        fragmentTransaction.commit();
    }
}