package com.nitrr.ecell.esummit.ecellapp.restapi;

import com.nitrr.ecell.esummit.ecellapp.models.Event.EventModel;
import com.nitrr.ecell.esummit.ecellapp.models.GenericMessage;
import com.nitrr.ecell.esummit.ecellapp.models.Team.TeamData;
import com.nitrr.ecell.esummit.ecellapp.models.auth.CAProfile.CADetails;
import com.nitrr.ecell.esummit.ecellapp.models.auth.LoginDetails;
import com.nitrr.ecell.esummit.ecellapp.models.auth.RegisterDetails;
import com.nitrr.ecell.esummit.ecellapp.models.auth.RegisterResponse;
import com.nitrr.ecell.esummit.ecellapp.models.mentors.MentorDetails;
import com.nitrr.ecell.esummit.ecellapp.models.mentors.MentorResponse;
import com.nitrr.ecell.esummit.ecellapp.models.otp.OTPVerify;
import com.nitrr.ecell.esummit.ecellapp.models.speakers.SpeakerDetails;
import com.nitrr.ecell.esummit.ecellapp.models.speakers.SpeakerResponse;
import com.nitrr.ecell.esummit.ecellapp.models.Sponsors.SponsorsModel;
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
    Call<RegisterResponse> postRegisterUser(@Body RegisterDetails registerDetails);

    @POST("/login/")
    Call<GenericMessage> postLoginUser(@Body LoginDetails loginDetails);


    //User
    @POST("/user/ca_profile/")
    Call<GenericMessage> postCAProfile(@Body CADetails caDetails);

    @GET("/user/send_otp/")
    Call<GenericMessage> getSendOTP();

    @POST("/user/verify_otp/")
    Call<GenericMessage> postSendOTP(@Header("token") String token, @Body OTPVerify otpVerify);


    //Speakers
    @POST("/speaker/add_new/")
    Call<GenericMessage> postAddNewSpeaker(@Header("token") String token, @Body SpeakerDetails speakerDetails);

    @GET("/speaker/list/{year}/")
    Call<SpeakerResponse> getSpeakerList(@Path("year") int year);

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
}
