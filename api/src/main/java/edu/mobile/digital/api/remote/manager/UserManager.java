package edu.mobile.digital.api.remote.manager;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import edu.mobile.digital.api.remote.ApiClient;
import edu.mobile.digital.api.remote.ApiService;
import edu.mobile.digital.api.remote.model.User;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;

public class UserManager {
	private static CompositeDisposable disposable = new CompositeDisposable();


	public static void registerUser(String uuid, final Context context){
//		ApiService apiService = ApiClient.getClient(context).create(ApiService.class);
//
//		disposable.add(
//				apiService
//						.register(uuid)
//						.subscribeOn(Schedulers.io())
//						.observeOn(Schedulers.newThread())
//						.subscribeWith(new DisposableSingleObserver<User>() {
//							@Override
//							public void onSuccess(User user) {
//								// Storing user API Key in preferences
//
//								Toast.makeText(context,
//										"Device is registered successfully! ApiKey: "+user.getApiKey(),
//										Toast.LENGTH_LONG).show();
//							}
//
//							@Override
//							public void onError(Throwable e) {
//								Log.e("Erro user", "onError: " + e.getMessage());
//							}
//						}));

	}

}
