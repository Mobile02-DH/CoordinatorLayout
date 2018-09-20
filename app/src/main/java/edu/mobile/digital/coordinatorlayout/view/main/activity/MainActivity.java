package edu.mobile.digital.coordinatorlayout.view.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import edu.mobile.digital.api.remote.manager.CategoryManager;
import edu.mobile.digital.api.remote.model.Category;
import edu.mobile.digital.coordinatorlayout.view.main.adapters.MyRecyclerAdapter;
import edu.mobile.digital.coordinatorlayout.view.main.viewinterfaces.MainViewInterface;
import edu.mobile.digital.coordinatorlayout.model.utils.interfaces.ClickListIntemInterface;
import edu.mobile.digital.coordinatorlayout.view.detail.activity.DetailActivity;
import edu.mobile.digital.coordinatorlayout.model.data.FilmeModelo;
import edu.mobile.digital.coordinatorlayout.view.main.adapters.MyViewPagerAdapter;
import edu.mobile.digital.coordinatorlayout.R;
import edu.mobile.digital.coordinatorlayout.controller.MainController;


public class MainActivity extends AppCompatActivity implements ClickListIntemInterface,
		NavigationView.OnNavigationItemSelectedListener, MainViewInterface{

	MainController controller;
	DrawerLayout drawer;
	ActionBarDrawerToggle toggle;
	ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		controller = new MainController(this);

		initLayout();

		setSubscriptions();
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		controller.getSubscriptions().unsubscribe();
		controller.getSubscriptions().clear();
	}

	@Override
	public void onBackPressed() {
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.navigation, menu);
		return true;
	}

	@Override
	public void onItemClick(FilmeModelo filme) {
		Intent intent = new Intent(this,DetailActivity.class);
		intent.putExtra("Filme", filme);
		startActivity(intent);
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
		// Handle navigation view item clicks here.
		int id = menuItem.getItemId();

		switch (id){
			case R.id.nav_camera:
				break;
			case R.id.nav_gallery:
				break;
			case R.id.nav_slideshow:
				break;
			case R.id.nav_manage:
				break;
			case R.id.nav_share:
				break;
			case R.id.nav_send:
				break;
			default:
				break;
		}

		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	public void initLayout(){

		//Inicializa ViewPager
		viewPager = findViewById(R.id.viewpager);
		viewPager.setVisibility(View.GONE);

		TabLayout tabs = findViewById(R.id.tabs);
		tabs.setupWithViewPager(viewPager);

		Toolbar myToolbar = findViewById(R.id.main_toolbar);
		setSupportActionBar(myToolbar);

		//NavigationView
		drawer = findViewById(R.id.drawer_layout);
		toggle = new ActionBarDrawerToggle(
				this, drawer, myToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

		drawer.addDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
	}


	public void clickFab(View view){
		Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
				.setAction("Action", null).show();
	}

	public void setSubscriptions(){
		controller.getSubscriptions().add(CategoryManager.getCategories(getApplicationContext())
				.subscribe(controller::successRequest, controller::erroRequest)
		);
	}


	@Override
	public void successRequest(Object obj) {

		Category cat = (Category) obj;
		ArrayList<FilmeModelo> data = controller.getData(cat.getProducts().size(), cat.getProducts());

		viewPager.setAdapter(new MyViewPagerAdapter(this, new MyRecyclerAdapter(data)));
		viewPager.setVisibility(View.VISIBLE);

		Toast.makeText(this, "Success!!", Toast.LENGTH_LONG).show();
	}

	@Override
	public void errorRequest(Throwable e) {
		Toast.makeText(this, "Error!!!"+ e.getCause(), Toast.LENGTH_LONG).show();
	}
}
