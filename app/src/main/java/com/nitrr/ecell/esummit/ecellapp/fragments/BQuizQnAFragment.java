package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.nitrr.ecell.esummit.ecellapp.BuildConfig;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.BquizOptionsAdapter;
import com.nitrr.ecell.esummit.ecellapp.misc.SharedPref;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.bquiz.BquizAnswerModel;
import com.nitrr.ecell.esummit.ecellapp.models.bquiz.BquizResponseModel;
import com.nitrr.ecell.esummit.ecellapp.models.bquiz.QuestionDetailsModel;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;
import com.nitrr.ecell.esummit.ecellapp.rxsocket.WebSocket;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BQuizQnAFragment extends DialogFragment implements BquizOptionsAdapter.onClickListener {

    private WebSocket webSocket;
    private Gson gson;

    private ImageView bquizLogo;
    private TextView tvBquizQuestion, timeAllotted;

    private RecyclerView rvBquizOptions;
    private BquizOptionsAdapter bquizOptionsAdapter;

    private int timeGiven;
    private List<Integer> optionID;
    private int rightAnswerId, selectedAnswerId;
    private int answerId = 0, questionId = -1;
    private int baseScore = 0;
    private int timeAtWhichAnswerWasSelected = 0;

    private BottomSheetFragmentBquiz fragmentBquiz;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bquiz, container, false);

        setUpWebSocket();

        gson = new Gson();
        fragmentBquiz = BottomSheetFragmentBquiz.newInstance();
        fragmentBquiz.setCancelable(false);
        fragmentBquiz.show(getFragmentManager(), "Bquiz");

        initview(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    private void initview(View view) {
        bquizLogo = view.findViewById(R.id.iv_bquiz_logo);
        tvBquizQuestion = view.findViewById(R.id.tv_bquiz_question);
        timeAllotted = view.findViewById(R.id.bquiz_timer);
        rvBquizOptions = view.findViewById(R.id.rv_bquiz_option);
        rvBquizOptions.setLayoutManager(new LinearLayoutManager(getContext()));

        bquizOptionsAdapter = new BquizOptionsAdapter(getContext(), null);
        rvBquizOptions.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvBquizOptions.setNestedScrollingEnabled(true);
        rvBquizOptions.setAdapter(bquizOptionsAdapter);

        bquizOptionsAdapter.setOnClickListener(this);

    }

    private void timer(int time) {
        timeGiven = time;
        new CountDownTimer(timeGiven * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeAllotted.setText(timeGiven + "sec.");
                timeGiven--;
            }

            @Override
            public void onFinish() {
                //TODO: API CALL
                apiCall();
            }

        }.start();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (webSocket != null)
            webSocket.closeConnection()
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
    }

    @SuppressLint("CheckResult")
    private void setUpWebSocket() {
        webSocket = new WebSocket(BuildConfig.BQUIZ_URL);

        webSocket.onOpen()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(socketEventOpen -> Utils.showLongToast(getContext(), "B-Quiz is live."), Throwable::printStackTrace);

        webSocket.onClosed()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(socketEventClosed -> Utils.showLongToast(getContext(), "B-Quiz closed."), Throwable::printStackTrace);

        webSocket.onClosing()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(socketEventClosing -> Utils.showLongToast(getContext(), "B-Quiz is closing."), Throwable::printStackTrace);

        webSocket.onMessage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(socketEventMessage -> {
                    QuestionDetailsModel model = gson.fromJson(socketEventMessage.getMessage(), QuestionDetailsModel.class);

                    if (model.end) {
                        fragmentBquiz.setCancelable(true);
                        fragmentBquiz.dismiss();
                        onStop();
                    }

                    if (!model.show) {
                        if (!fragmentBquiz.isVisible() && getFragmentManager() != null)
                            fragmentBquiz.show(getFragmentManager(), "Bquiz");

                        Log.e("Socket", "IF REACHED");

                    } else {
                        Log.e("Socket1==", String.valueOf(model));
                        tvBquizQuestion.setText(model.question);

                        if (fragmentBquiz != null && fragmentBquiz.isVisible())
                            fragmentBquiz.dismiss();

                        rightAnswerId = model.rightAnswer;

                        bquizOptionsAdapter.setNewList(model.options);
                        optionID = model.optionId;

                        questionId = model.id;
                        baseScore = model.score;

                        timer(model.timeLimit);

                        if (!model.meta.equals("") && getActivity() != null) {
                            bquizLogo.setVisibility(View.VISIBLE);

                            CircularProgressDrawable progressDrawable = new CircularProgressDrawable(getActivity());
                            progressDrawable.setStrokeWidth(5f);
                            progressDrawable.setCenterRadius(30f);
                            progressDrawable.setBackgroundColor(R.color.colorWhite);
                            progressDrawable.start();

                            Glide.with(getActivity())
                                    .load(model.meta)
                                    .apply(new RequestOptions().transform(
                                            new CenterCrop(),
                                            new RoundedCorners(8)))
                                    .placeholder(progressDrawable)
                                    .into(bquizLogo);

                        } else {
                            bquizLogo.setVisibility(View.GONE);
                        }
                    }

                }, Throwable::printStackTrace);

        webSocket.onFailure()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(socketEventFailure -> Utils.showLongToast(getContext(), "Some exception occurred. Contact Technical TeamList."), Throwable::printStackTrace);

        webSocket.setupConnection();
    }

    @Override
    public void onClick(int position) {
        if (fragmentBquiz != null && !fragmentBquiz.isVisible() && getFragmentManager() != null) {
            fragmentBquiz.show(getFragmentManager(), "Bquiz");
            answerId = position;
            timeAtWhichAnswerWasSelected = timeGiven;
        }
    }

    private void apiCall() {
        APIServices apiServices = AppClient.getInstance().createService(APIServices.class);

        BquizAnswerModel answerModel = new BquizAnswerModel();
        answerModel.answerID = optionID == null || optionID.size() == 0 ? 0 : optionID.get(answerId);
        selectedAnswerId = answerModel.answerID;
        answerModel.questionID = questionId;
        answerModel.time = timeAtWhichAnswerWasSelected;

        if (fragmentBquiz != null && fragmentBquiz.isVisible()) {
            fragmentBquiz.setMessage(getAnswerSubmissionResponse(selectedAnswerId));
        }

        answerModel.score = (rightAnswerId == selectedAnswerId) ? getBonus(timeAtWhichAnswerWasSelected) + baseScore : 0;


        Call<BquizResponseModel> responseModelCall = apiServices.submitAnswer("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InJlcmVAZ21haWwuY29tIn0.a3HUpl8XWW4v-k-Sv2TNOg48nTWgPKZowVjTN6X15JY", answerModel);
        responseModelCall.enqueue(new Callback<BquizResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<BquizResponseModel> call, @NonNull Response<BquizResponseModel> response) {
            }

            @Override
            public void onFailure(@NonNull Call<BquizResponseModel> call, @NonNull Throwable t) {
            }
        });
    }

    private int getBonus(int time) {
        return time >= 5 ? time * 2 : 0;
    }

    private String getAnswerSubmissionResponse(int selectedAnswerId) {
        String response;
        if (rightAnswerId == selectedAnswerId) {
            if (getBonus(timeAtWhichAnswerWasSelected) > 0) {
                response = "Your Answer is correct and time bounus of " + getBonus(timeAtWhichAnswerWasSelected) + " points is given to you";
            } else {
                response = "Your Answer is correct";
            }
        } else {
            response = "Your Answer is incorrect";
        }

        return response;
    }
}
