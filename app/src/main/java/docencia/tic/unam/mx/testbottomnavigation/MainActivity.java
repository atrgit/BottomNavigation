package docencia.tic.unam.mx.testbottomnavigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MenuItem             menuItemProfile;
    private Fragment             currentFragment = null;
    private BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            resetIcon();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    currentFragment = new HomeFragment();
                    break;
                case R.id.navigation_dashboard:
                    currentFragment = new DashboardFragment();
                    break;
                case R.id.navigation_notifications:
                    currentFragment = new NotificationsFragment();
                    break;
                case R.id.navigation_profile:
                    currentFragment = new ProfileFragment();
                    item.setIcon( R.drawable.ic_person_pin_circle_black_24dp );
                    break;
            }
            return loadFragment(currentFragment);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //loading the default fragment
        loadFragment(new HomeFragment());

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        menuItemProfile = navigation.getMenu().findItem(R.id.navigation_profile);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    public void resetIcon() {
        if( menuItemProfile != null ) {
            menuItemProfile.setIcon(R.drawable.ic_profile_black_24dp);
        }
    }
}
