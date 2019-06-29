package com.nitrr.ecell.esummit.ecellapp.restapi;

import com.nitrr.ecell.esummit.ecellapp.models.EventData;
import com.nitrr.ecell.esummit.ecellapp.models.SponsRVData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIServices {

    @GET("/photos")
    Call<List<SponsRVData>> getAllPhotos();

    @GET("/todos")
    Call<List<EventData>> getEventDetails();

//    @POST(AppConstants.SIGN_UP_URL)
//    Call<AuthenticationResponse> sendRegisterDetails(@Body UserDetails userDetails);
//
//    @POST(AppConstants.SEND_OTP_URL)
//    Call<SendOtpResponse> sendMobileNo(@Body otpSendNumber otpSendNumber);
//
//    @POST(AppConstants.VERIFY_OTP_URL)
//    Call<VerifyOtp> sendOtpEntered(@Body sendOtp sendOtp);
//
//    @POST(AppConstants.FB_SIGN_UP_URL)
//    Call<AuthenticationResponse> sendFacebookRegistrationDetails(@Body FacebookSignInUserDetails details);
//
//    @GET(AppConstants.ABOUT_US_URL)
//    Call<AboutUsResponse> getAboutUsDetails();
//
//    @GET(AppConstants.SPEAKER_URL)
//    Call<SpeakerResponse> getSpeakerDetails();
//
//    @POST(AppConstants.MESSAGE_URL)
//    Call<GenericResponse> sendMessage(@Body MessageDetails details);
//
//    @GET(AppConstants.BQUIZ_STATUS)
//    Call<BQuizStatusResponse> getBquizStatus();
//
//    @GET(AppConstants.BQUIZ_LEADERBOARD)
//    Call<BQuizLeaderboardResponse> getBquizLeaderboard();
//
//    @GET(AppConstants.EVENTS_URL)
//    Call<EventsResponse> getEventsResponse();
//
//    @POST(AppConstants.SIGN_IN_URL)
//    Call<AuthenticationResponse>sendLoginDetails(@Body LoginDetails loginDetails);
//
//    @GET(AppConstants.SPONSOR_URL)
//    Call<SponsorsResponse> getSponsorsResponce();
//
//    @GET(AppConstants.BQUIZ_QUESTION)
//    Call<BQuizQuestionResponse> getQuestion(@Query("retryQuestion") Boolean retryQuestion);
//
//    @GET(AppConstants.SPLASHSCREEN_URL)
//    Call<SplashScreenResponse> getAppUpdate();
//
//    @POST(AppConstants.BQUIZ_SUBMIT_ANSWER)
//    Call<GenericResponse> submitAnswer(@Body Answer answer);
}
