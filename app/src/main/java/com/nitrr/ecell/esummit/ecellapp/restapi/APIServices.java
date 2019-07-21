package com.nitrr.ecell.esummit.ecellapp.restapi;

import com.nitrr.ecell.esummit.ecellapp.models.AppDetails;
import com.nitrr.ecell.esummit.ecellapp.models.PhoneNumber;
import com.nitrr.ecell.esummit.ecellapp.models.events.EventModel;
import com.nitrr.ecell.esummit.ecellapp.models.GenericMessage;
import com.nitrr.ecell.esummit.ecellapp.models.team.TeamData;
import com.nitrr.ecell.esummit.ecellapp.models.auth.CAProfile.CADetails;
import com.nitrr.ecell.esummit.ecellapp.models.auth.LoginDetails;
import com.nitrr.ecell.esummit.ecellapp.models.auth.RegisterDetails;
import com.nitrr.ecell.esummit.ecellapp.models.auth.AuthResponse;
import com.nitrr.ecell.esummit.ecellapp.models.mentors.MentorDetails;
import com.nitrr.ecell.esummit.ecellapp.models.mentors.MentorResponse;
import com.nitrr.ecell.esummit.ecellapp.models.ForgotPassword.Forgot_Password;
import com.nitrr.ecell.esummit.ecellapp.models.speakers.SpeakerDetails;
import com.nitrr.ecell.esummit.ecellapp.models.speakers.ResponseSpeaker;
import com.nitrr.ecell.esummit.ecellapp.models.sponsors.SponsorsModel;
import com.nitrr.ecell.esummit.ecellapp.models.startUps.StartUpDetails;
import com.nitrr.ecell.esummit.ecellapp.models.startUps.StartUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIServices {

    @GET("sponsors/list/2019")
    Call<SponsorsModel> getSponsData();

    @GET("events/list/2019/")
    Call<EventModel> getEventDetails();


    //Auth
    @POST("users/register/")
    Call<AuthResponse> postRegisterUser(@Body RegisterDetails registerDetails);

    @POST("users/login/")
    Call<AuthResponse> postLoginUser(@Body LoginDetails loginDetails);


    //User
    @POST("/user/ca_profile/")
    Call<GenericMessage> postCAProfile(@Body CADetails caDetails);

    @GET("/user/send_otp/")
    Call<GenericMessage> getSendOTP();

    @POST("/user/verify_otp/")
    Call<GenericMessage> postSendOTP(@Header("token") String token, @Body Forgot_Password otpVerify);


    //Speakers
    @POST("/speaker/add_new/")
    Call<GenericMessage> postAddNewSpeaker(@Header("token") String token, @Body SpeakerDetails speakerDetails);

    @GET("speakers/list/{year}/")
    Call<ResponseSpeaker> getSpeakerList(@Path("year") int year);

    @GET("/speaker/generate_sheet/")
    Call getSpeakerSheet();


    //mentors
    @POST("/mentors/add_new/")
    Call<GenericMessage> postAddNewMentor(@Header("token") String token, @Body MentorDetails mentorDetails);

    @GET("/mentors/list/{year}/")
    Call<MentorResponse> getMentorList(@Path("year") int year);

    @GET("/mentors/generate_sheet/")
    Call getMentorSheet();


    //startUp
    @POST("/startup/add_new/")
    Call<GenericMessage> postAddNewStartup(@Header("token") String token, @Body StartUpDetails startUpDetails);

    @GET("/startup/list/{year}/")
    Call<StartUpResponse> getStartupList(@Path("year") int year);

    @GET("/startup/generate_sheet/")
    Call getStartupSheet();

    @GET("/spons")
    Call<TeamData> getTeamData();

    @GET("/is_update_available")
    Call<AppDetails> getAppdata();

    @GET("/users/change_contact")
    Call<PhoneNumber> changeNumber(@Body String string);

    @POST("/send_otp")
    Call<String> sendOTP(@Body String email);
}
