package edu.mobile.digital.coordinatorlayout.model.data;

import java.io.Serializable;

/**
 * Created by wtf on 06/10/14.
 */
public class FilmeModelo implements Serializable {

    private String titulo;

    public FilmeModelo(String titulo) {
        this.titulo = titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }
}
