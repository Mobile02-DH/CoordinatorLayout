package edu.mobile.digital.api.remote.manager;

import android.content.Context;

import edu.mobile.digital.api.remote.ApiClient;
import edu.mobile.digital.api.remote.ApiService;
import edu.mobile.digital.api.remote.model.Category;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;



public class CategoryManager {

	private CategoryManager() {
	}


	public static Observable<Category> getCategories(Context context){
		ApiService apiService = ApiClient.getMockClient(context).create(ApiService.class);

		return apiService.getCategories().observeOn(AndroidSchedulers.mainThread());
	}
}
