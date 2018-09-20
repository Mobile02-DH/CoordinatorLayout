package edu.mobile.digital.coordinatorlayout.controller;

import java.util.ArrayList;
import java.util.List;

import edu.mobile.digital.api.remote.model.Category;
import edu.mobile.digital.api.remote.model.Product;
import edu.mobile.digital.coordinatorlayout.model.data.FilmeModelo;
import edu.mobile.digital.coordinatorlayout.view.main.viewinterfaces.MainViewInterface;
import edu.mobile.digital.coordinatorlayout.model.utils.GeradorFilmes;
import rx.subscriptions.CompositeSubscription;

public class MainController {

	private CompositeSubscription subscriptions = new CompositeSubscription();
	private MainViewInterface listener;

	public MainController(MainViewInterface viewlistener) {
		this.listener = viewlistener;
	}

	public CompositeSubscription getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(CompositeSubscription subscriptions) {
		this.subscriptions = subscriptions;
	}

	public void successRequest(Category categories){
		listener.successRequest(categories);
	}

	public void erroRequest(Throwable throwable){
		listener.errorRequest(throwable);
	}

	public ArrayList<FilmeModelo> getData(int size, List<Product> products){
		return GeradorFilmes.getSampleData(size,products);
	}
}
