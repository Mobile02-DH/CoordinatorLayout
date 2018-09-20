package edu.mobile.digital.api.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Category implements Parcelable {

	@Expose
	public List<Product> products;

	public Category() {
	}

	protected Category(Parcel in) {
		products = in.createTypedArrayList(Product.CREATOR);
	}

	public static final Creator<Category> CREATOR = new Creator<Category>() {
		@Override
		public Category createFromParcel(Parcel in) {
			return new Category(in);
		}

		@Override
		public Category[] newArray(int size) {
			return new Category[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeTypedList(products);
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
