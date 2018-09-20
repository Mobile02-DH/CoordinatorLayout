package edu.mobile.digital.coordinatorlayout.model.utils;

import java.util.ArrayList;
import java.util.List;

import edu.mobile.digital.api.remote.model.Product;
import edu.mobile.digital.coordinatorlayout.model.data.FilmeModelo;

/**
 * Created by wtf on 06/10/14.
 */
public class GeradorFilmes {

    public static ArrayList<FilmeModelo> getSampleData (int size) {

        ArrayList<FilmeModelo> sampleData = new ArrayList<FilmeModelo>(size);

        for (int i = 0; i < size; i++) {
            sampleData.add(new FilmeModelo("Filme "+i));
        }

        return sampleData;

    }

    public static ArrayList<FilmeModelo> getSampleData (int size, List<Product> items) {

        ArrayList<FilmeModelo> sampleData = new ArrayList<FilmeModelo>(size);

        for (int i = 0; i < size; i++) {
            sampleData.add(new FilmeModelo(items.get(i).name));
        }

        return sampleData;

    }

    public static ArrayList<FilmeModelo> getTabData (int size) {

        ArrayList<FilmeModelo> sampleData = new ArrayList<FilmeModelo>(size);

        for (int i = 0; i < size; i++) {
            sampleData.add(new FilmeModelo("Categoria "+i));
        }

        return sampleData;

    }
}
