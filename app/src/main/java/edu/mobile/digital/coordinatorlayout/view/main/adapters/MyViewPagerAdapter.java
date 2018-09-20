package edu.mobile.digital.coordinatorlayout.view.main.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.mobile.digital.coordinatorlayout.model.utils.interfaces.ClickListIntemInterface;
import edu.mobile.digital.coordinatorlayout.model.data.FilmeModelo;
import edu.mobile.digital.coordinatorlayout.model.utils.GeradorFilmes;
import edu.mobile.digital.coordinatorlayout.R;

/**
 * Created by anupamchugh on 26/12/15.
 */
public class MyViewPagerAdapter extends PagerAdapter {

    private Context mContext;
	private final ArrayList<FilmeModelo> sampleData = GeradorFilmes.getTabData(5);
	MyRecyclerAdapter myRecyclerAdapter ;
	LinearLayoutManager mLayoutManager;

    public MyViewPagerAdapter(Context context, MyRecyclerAdapter myRecyclerAdapter) {
        mContext = context;
        this.myRecyclerAdapter = myRecyclerAdapter;
		this.myRecyclerAdapter.setClickListener((ClickListIntemInterface) context);
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.view_list, collection, false);
		createRecyclerView(layout);
        collection.addView(layout);

        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return sampleData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
    	FilmeModelo filmeModelo = sampleData.get(position);

        return filmeModelo.getTitulo();
    }

	private void createRecyclerView(ViewGroup viewGroup){
		// RecyclerView
		final RecyclerView recyclerView = viewGroup.findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);

		// RecyclerView layout manager
		mLayoutManager = new LinearLayoutManager(mContext);
		recyclerView.setLayoutManager(mLayoutManager);

		// RecyclerView ItemDecoration (divider)
//		final RecyclerView.ItemDecoration itemDecoration = new DivisaoLista(mContext);
//		recyclerView.addItemDecoration(itemDecoration);

		// RecyclerView adapter
		recyclerView.setAdapter(myRecyclerAdapter);
	}


}
