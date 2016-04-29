package com.example.pedroramos.testtab2.data.api;


import java.util.List;

import com.example.pedroramos.testtab2.data.api.model.VoiceMessage;
import com.example.pedroramos.testtab2.data.preferences.PreferencesManager;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * RestAPI for the BablApp service.
 *
 * @see <a href="https://redmine.corelogic.pl/projects/bablapp/wiki/API_REST_-_docs">Api documentation</a>
 */

public interface RestService {

    @POST("activation/register")
    Observable<RegistrationResponse> register(@Body RegistrationRequest request);

    @POST("contacts")
    Observable<ContactsResponse> postContacts(@Body ContactsRequest request);

    /**
     * Get blabs for user.
     *
     * @param appHash required, applicationId of the caller, this parameter is validated for security reasons (can be obtained in {@link PreferencesManager})
     * @param msisdns optional, comma separated values of phone numbers of the authors of the babls
     * @param groups  optional, comma separated values of group ids for which you want to retrieve babls
     * @param data    optional, set to true if you want to retrieve the voice message file (mp4)
     * @param avatar  optional, set to true if you want to receive the avatar file of the author for each babl
     * @return observable with list of blabs
     */
    @GET("voice-messages")
    Observable<List<VoiceMessage>> getVoiceMessages(@Query("app-id") String appHash, @Query("msisdns") String msisdns,
                                                    @Query("groups") String groups, @Query("data") Boolean data,
                                                    @Query("avatar") Boolean avatar);

    @GET("voice-messages/{id}")
    Observable<VoiceMessage> getVoiceMessage(@Path("id") Long voiceMessageId, @Query("app-id") String appHash);

    @GET("voice-messages")
    Observable<List<VoiceMessage>> getVoiceMessages(@Query("app-id") String appHash);
}