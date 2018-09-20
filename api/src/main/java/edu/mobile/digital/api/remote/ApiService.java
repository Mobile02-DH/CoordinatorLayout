package edu.mobile.digital.api.remote;

import edu.mobile.digital.api.remote.model.Category;
import edu.mobile.digital.api.remote.model.Note;
import edu.mobile.digital.api.remote.model.User;
import rx.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

	@GET("59b6a65a0f0000e90471257d")
	Observable<Category> getCategories();

	// Register new user
	@FormUrlEncoded
	@POST("notes/user/register")
	Observable<Single<User>> register(@Field("device_id") String deviceId);

	// Create note
	@FormUrlEncoded
	@POST("notes/new")
	Observable<Single<Note>> createNote(@Field("note") String note);

	// Fetch all notes
	@GET("notes/all")
	Observable<Single<List<Note>>> fetchAllNotes();

	// Update single note
	@FormUrlEncoded
	@PUT("notes/{id}")
	Observable<Completable> updateNote(@Path("id") int noteId, @Field("note") String note);

	// Delete note
	@DELETE("notes/{id}")
	Observable<Completable> deleteNote(@Path("id") int noteId);
}
