package Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import softocardmaker.softoappa.softobook.com.Favourite;
import softocardmaker.softoappa.softobook.com.R;
import softocardmaker.softoappa.softobook.com.databinding.ActivityHomeBinding;
import softocardmaker.softoappa.softobook.com.homefrgmnt;

public class home extends AppCompatActivity {

    ActivityHomeBinding binding;
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());





        nav = (NavigationView) findViewById(R.id.navmenu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout,binding.toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        int color = ContextCompat.getColor(this, R.color.white);
        toggle.getDrawerArrowDrawable().setColor(color);
        toggle.syncState();



        getSupportFragmentManager().beginTransaction().replace(R.id.container,new homefrgmnt()).commit();
        nav.setCheckedItem(R.id.favorites);

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            Fragment temp;
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.favorites:

                      Intent intent2=new Intent(getApplicationContext(), Favourite.class);
                      startActivity(intent2);
                      finish();

                        break;

                    case R.id.share:
                        String content = "Check out this amazing app!";
                        shareContent(content);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;


                    case R.id.privacypolicy:
                        Intent intent=new Intent(home.this, privacypolicy.class);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(intent);
                        finish();
                        break;





                }

                return true;
            }
        });

    }

    private void shareContent(String content) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, content);
        // Verify that the intent can be resolved to avoid crashes
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, "Share via"));
        }
    }
}