package com.nitrr.ecell.esummit.ecellapp.restapi;

import com.nitrr.ecell.esummit.ecellapp.models.AppDetails;
import com.nitrr.ecell.esummit.ecellapp.models.OTPVerification;
import com.nitrr.ecell.esummit.ecellapp.models.verifyNumber.UserVerifiedModel;
import com.nitrr.ecell.esummit.ecellapp.models.VerifyOTP;
import com.nitrr.ecell.esummit.ecellapp.models.verifyNumber.ChangeNumber;
import com.nitrr.ecell.esummit.ecellapp.models.events.EventModel;
import com.nitrr.ecell.esummit.ecellapp.models.GenericMessage;
import com.nitrr.ecell.esummit.ecellapp.models.forgotPassword.ChangePassword;
import com.nitrr.ecell.esummit.ecellapp.models.forgotPassword.ForgotVerifyOTP;
import com.nitrr.ecell.esummit.ecellapp.models.team.TeamData;
import com.nitrr.ecell.esummit.ecellapp.models.auth.LoginDetails;
import com.nitrr.ecell.esummit.ecellapp.models.auth.RegisterDetails;
import com.nitrr.ecell.esummit.ecellapp.models.auth.AuthResponse;
import com.nitrr.ecell.esummit.ecellapp.models.forgotPassword.ForgotPassword;
import com.nitrr.ecell.esummit.ecellapp.models.speakers.ResponseSpeaker;
import com.nitrr.ecell.esummit.ecellapp.models.sponsors.SponsorsModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIServices {

    @GET("/sponsors/list/2019/")
    Call<SponsorsModel> getSponsData();

    @GET("events/list/2019/")
    Call<EventModel> getEventDetails();

    //Auth
    @POST("users/register/")
    Call<AuthResponse> postRegisterUser(@Body RegisterDetails registerDetails);

    @POST("users/login/")
    Call<AuthResponse> postLoginUser(@Body LoginDetails loginDetails);


    //ForgotPassword
    @POST("users/forgot_password/")
    Call<GenericMessage> postEmailVerify(@Header("Access") String access,@Body ForgotPassword password);

    @POST("users/check_otp/")
    Call<GenericMessage> postForgotOPTVerify(@Body ForgotVerifyOTP verifyOTP);

    @POST("users/change_password/")
    Call<GenericMessage> postPasswordChange(@Header("Access") String access, @Body ChangePassword password);


    //Change Number
    @POST("users/change_contact/")
    Call<GenericMessage> changeNumber(@Header("Access") String access, @Header("Authorization") String token, @Body ChangeNumber number);


    @GET("speakers/list/2018/")
    Call<ResponseSpeaker> getSpeakerList(@Header("Access") String access);

    @GET("team/list/")
    Call<TeamData> getTeamData();

    @GET("is_update_available/")
    Call<AppDetails> getIsUpdateAvailable();//200 available, 404 not

    @POST("users/verify_otp/")
    Call<OTPVerification> verifyOtp(@Header("Access") String access, @Header("Authorization") String token, @Body VerifyOTP verifyOTP);

    @GET("users/resend_otp/")
    Call<GenericMessage> resendOtp(@Header("Authorization") String auth, @Header("Access") String access);

    @GET("users/is_user_verified/")
    Call<UserVerifiedModel> isVerified(@Header("Access") String appAccessToken, @Header("Authorization") String userAccessToken); //only 200

    @POST("/events/register/{id}/")
    Call<GenericMessage> registerForEvent(@Header("Access") String access, @Header("Authorization") String token, @Path("id") String id);
}
