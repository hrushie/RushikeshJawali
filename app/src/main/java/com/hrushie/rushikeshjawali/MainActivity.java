package com.hrushie.rushikeshjawali;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private static String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    Bundle bundle = new Bundle();
    WebView webView;
    private FragmentDrawer drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        // display the first navigation drawer view on app launch
        displayView(0);
        FloatingActionButton.LayoutParams params = new FloatingActionButton.LayoutParams(150, 150);

        ImageView icon = new ImageView(this); // Create an icon
        icon.setLayoutParams(params);
        icon.setImageResource(R.drawable.ic_facebook);
        icon.setScaleType(ImageView.ScaleType.FIT_XY);


        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .setTheme(getResources().getColor(R.color.colorAccent))
                .build();

        ImageView icon1 = new ImageView(this); // Create an icon
        icon1.setImageResource(R.drawable.insta);
        ImageView icon2 = new ImageView(this); // Create an icon
        icon2.setImageResource(R.drawable.face);
        ImageView icon3 = new ImageView(this); // Create an icon
        icon3.setImageResource(R.drawable.twitt);
        ImageView icon4 = new ImageView(this); // Create an icon
        icon4.setImageResource(R.drawable.linkdin);
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);


        itemBuilder.setLayoutParams(params);

        SubActionButton buttonInsta = itemBuilder.setContentView(icon1).build();
        SubActionButton buttonFacebook = itemBuilder.setContentView(icon2).build();
        SubActionButton buttonTwitter = itemBuilder.setContentView(icon3).build();
        SubActionButton buttonLinkedin = itemBuilder.setContentView(icon4).build();


        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(buttonInsta)
                .addSubActionView(buttonFacebook)
                .addSubActionView(buttonTwitter)
                .addSubActionView(buttonLinkedin)
                .attachTo(actionButton)
                .build();
        buttonInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("edttext", "From Activity");
                webView.loadUrl("www.instagram.com/hrushie");
                webView.setVisibility(View.VISIBLE);
            }
        });
        buttonFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/hrushie"));
                startActivity(browserIntent);
            }
        });
        buttonTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.twitter.com/hrushie"));
                startActivity(browserIntent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        if (id == R.id.action_github) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.github.com/hrushie"));
            startActivity(browserIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new AboutFragment();
                title = "About me";
                break;
            case 1:
                fragment = new FriendsFragment();
                title = "Professional";
                break;
            case 2:
                fragment = new MessagesFragment();
                title = "Summery";
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }
}
