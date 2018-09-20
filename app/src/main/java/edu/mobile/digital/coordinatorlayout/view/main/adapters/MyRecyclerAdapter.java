package edu.mobile.digital.coordinatorlayout.view.main.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import edu.mobile.digital.coordinatorlayout.model.utils.interfaces.ClickListIntemInterface;
import edu.mobile.digital.coordinatorlayout.model.data.FilmeModelo;
import edu.mobile.digital.coordinatorlayout.R;


public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private ArrayList<FilmeModelo> sampleData;
    private ClickListIntemInterface clickListener;

    public MyRecyclerAdapter(ArrayList<FilmeModelo> sampleData) {
        this.sampleData = sampleData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parentViewGroup, int i) {

        View rowView = LayoutInflater.from (parentViewGroup.getContext())
            .inflate(R.layout.filme_item, parentViewGroup, false);

        return new ViewHolder (rowView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        final FilmeModelo rowData = sampleData.get(position);
        viewHolder.bind(rowData, clickListener);
    }


    @Override
    public int getItemCount() {

        return sampleData.size();
    }

    public void setClickListener(ClickListIntemInterface clickListener) {
        this.clickListener = clickListener;
    }

    public void removeData (int position) {

        sampleData.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(int positionToAdd) {

        sampleData.add(positionToAdd, new FilmeModelo("New element"));
        notifyItemInserted(positionToAdd);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewSample;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewSample =  itemView.findViewById(
                R.id.txtTitulo);
        }

        public void bind(final FilmeModelo filme, final ClickListIntemInterface listener) {
            textViewSample.setText(filme.getTitulo());
            itemView.setTag(filme);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(filme);
                }
            });
        }
    }

}
