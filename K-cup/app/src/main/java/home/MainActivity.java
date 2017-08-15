package home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kcup.drinkgame.k_cup.R;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            showHomeFragment();
        }
    }

    private void showHomeFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(HomeMainFragment.class.getCanonicalName());
        if (fragment == null) {
            fragment = Fragment.instantiate(this, HomeMainFragment.class.getCanonicalName());
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment, HomeMainFragment.class.getCanonicalName());
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        getSupportFragmentManager().popBackStack();

        Fragment homeFragment = getSupportFragmentManager().findFragmentByTag(HomeMainFragment.class.getCanonicalName());
        if (homeFragment != null && homeFragment.isVisible()) {
            finish();
        }
    }
}
