package application_managerstudentss.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import application_managerstudentss.Fragment.AddStudentsFragment;
import application_managerstudentss.Fragment.ListStudentsFragmet;
import application_managerstudentss.Object.Students;
import application_managerstudentss.Until;
import lac.hong.application_managerstudentss.R;
import lac.hong.application_managerstudentss.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ActivityMainBinding binding;
    public static ArrayList<Students> listStudents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //Demo data
        if(listStudents == null){
            listStudents = new ArrayList<>();
            listStudents.add(new Students("Thắng","Cẩm Phả","12/08/1998",10,10,10, Until.pointAverage(10,10,10),"516100"));
            listStudents.add(new Students("Thắng","Cẩm Phả","12/08/1998",10,10,10, Until.pointAverage(10,10,10),"516100"));
            listStudents.add(new Students("VĂN","Cẩm Phả","12/08/1998",10,10,10, Until.pointAverage(10,10,10),"516100"));
            Students students = new Students();
        }

        Actionbar();
        replaceFragment(new ListStudentsFragmet());
    }
    //SetUp toolbar
    private void Actionbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.managerStudents));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        binding.navView.setNavigationItemSelectedListener(this);

    }
    //Click menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.group_parentStudents:
                updateMenuItemParentHide();
                Menu menu = binding.navView.getMenu();
                menu.setGroupVisible(R.id.group_subStudents, true);
                break;
            case R.id.nav_back:
                updateMenuItemSubHide();
                updateMenuItemParentVisibly();
                break;
            case R.id.nav_listStudents:
                replaceFragment(new ListStudentsFragmet());
                updateMenuItemParentHide();
                binding.navView.setCheckedItem(binding.navView.getMenu().findItem(R.id.nav_listStudents));
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_add:
                replaceFragment(new AddStudentsFragment());
                updateMenuItemParentHide();
                binding.navView.setCheckedItem(binding.navView.getMenu().findItem(R.id.nav_add));
                getSupportActionBar().setTitle(getString(R.string.addStudents));
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                break;
        }
        return false;
    }

    FragmentTransaction ft;
    //load fragment
    private void replaceFragment(Fragment newFragment) {
        try {
            ft = getSupportFragmentManager().beginTransaction();
            ft.addToBackStack(null);
            ft.replace(R.id.fl_contain, newFragment)
                    .commit();

        } catch (Exception e) {
            //Toast.makeText(getApplicationContext(),"loidelau: "+e.toString(),Toast.LENGTH_LONG).show();
            Log.e("loi", e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void updateMenuItemParentHide() {
        binding.navView.getMenu().setGroupVisible(R.id.group_parent, false);
    }

    private void updateMenuItemSubHide() {
        Menu menu = binding.navView.getMenu();
        menu.setGroupVisible(R.id.group_subStudents, false);
    }

    private void updateMenuItemParentVisibly() {

        binding.navView.getMenu().setGroupVisible(R.id.group_parent, true);

    }


}
