package om.example.android.androidproject.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import om.example.android.androidproject.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BaseLayoutFragment extends Fragment {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;

    public BaseLayoutFragment() {
        // Required empty public constructor
    }

    public static BaseLayoutFragment newInstance() {
        return new BaseLayoutFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_base_layout, container, false);

        toolbar = root.findViewById(R.id.my_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        drawerLayout = root.findViewById(R.id.drawerLayout);
        navigationView = root.findViewById(R.id.navigationView);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        setupDrawerContent(navigationView);
        return root;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.nav_allPoems_fragment:
                fragmentClass = AllPoemsFragment.class;
                break;
            case R.id.nav_profile_fragment:
                fragmentClass = ProfileFragment.class;
                break;
            case R.id.nav_logOut_fragment:
                FirebaseAuth.getInstance().signOut();
                fragmentClass = LoginFragment.class;
                break;
            default:
                fragmentClass = AllPoemsFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.base_frame_layout, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        getActivity().setTitle(menuItem.getTitle());
        // Close the navigation drawer
        drawerLayout.closeDrawers();


    }


}



