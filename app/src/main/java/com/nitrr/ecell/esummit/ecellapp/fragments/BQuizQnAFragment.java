package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
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

import org.jetbrains.annotations.NotNull;

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
    private int answerIndex = -7, questionId = -1;
    private int baseScore = 0;
    private int timeAtWhichAnswerWasSelected = 0;

    private BottomSheetBehavior bottomSheet;
    private TextView message;
    private LottieAnimationView animationView;


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bquiz, container, false);

        gson = new Gson();

        bottomSheet = BottomSheetBehavior.from(view.findViewById(R.id.bquiz_bottom_sheet));
        message = view.findViewById(R.id.bquiz_bottom_sheet_message);
        animationView = view.findViewById(R.id.bquiz_animation);

        bottomSheet.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                if (i == BottomSheetBehavior.STATE_DRAGGING)
                    bottomSheet.setState(BottomSheetBehavior.STATE_EXPANDED);

                else if (i == BottomSheetBehavior.STATE_EXPANDED) {
                    bquizLogo.setAlpha(0.f);
                    tvBquizQuestion.setAlpha(0.f);
                    rvBquizOptions.setAlpha(0.f);

                } else if (i == BottomSheetBehavior.STATE_COLLAPSED) {
                    bquizLogo.setAlpha(1.f);
                    tvBquizQuestion.setAlpha(1.f);
                    rvBquizOptions.setAlpha(1.f);
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
            }
        });

        bottomSheet.setHideable(false);
        bottomSheet.setState(BottomSheetBehavior.STATE_EXPANDED);

        setUpWebSocket();
        initView(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    private void initView(View view) {
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

        bquizLogo.setAlpha(0.f);
        tvBquizQuestion.setAlpha(0.f);
        rvBquizOptions.setAlpha(0.f);
    }

    private void timer(int time) {
        timeGiven = time;
        new CountDownTimer(timeGiven * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeAllotted.setText(timeGiven + " sec.");
                timeGiven--;
            }

            @Override
            public void onFinish() {
                timeAllotted.setText("finished");
                apiCall();
            }

        }.start();

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
        webSocket = new WebSocket(BuildConfig.BQUIZ_SOCKET_URL);

        webSocket.onOpen()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(socketEventOpen -> Log.e("BQ", "Live."), Throwable::printStackTrace);

        webSocket.onClosed()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(socketEventClosed -> Log.e("BQ", "Closed."), Throwable::printStackTrace);

        webSocket.onClosing()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(socketEventClosing -> Log.e("BQ", "Closing"), Throwable::printStackTrace);

        webSocket.onMessage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(socketEventMessage -> {

                    if (bquizLogo != null && rvBquizOptions != null && tvBquizQuestion != null) {
                        answerIndex = -7;

                        QuestionDetailsModel model = gson.fromJson(socketEventMessage.getMessage(), QuestionDetailsModel.class);

                        if (model.end && getFragmentManager() != null) {
                            onStop();
                        }

                        if (!model.show) {
                            bottomSheet.setState(BottomSheetBehavior.STATE_EXPANDED);

                        } else {
                            message.setText("Please Wait..");

                            animationView.setAnimation(R.raw.timer);
                            animationView.playAnimation();

                            tvBquizQuestion.setText(model.question);

                            bottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);

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

                    }

                }, Throwable::printStackTrace);

        webSocket.onFailure()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(socketEventFailure -> Utils.showLongToast(getContext(), "Some exception occurred. Please restart B-Quiz."), Throwable::printStackTrace);

        webSocket.setupConnection();
    }

    @Override
    public void onClick(int position) {
        bottomSheet.setState(BottomSheetBehavior.STATE_EXPANDED);
        answerIndex = position;
        timeAtWhichAnswerWasSelected = timeGiven;

    }

    private void apiCall() {
        BquizAnswerModel answerModel = new BquizAnswerModel();
        answerModel.answerID = optionID == null || optionID.size() == 0 || answerIndex == -7 ? 0 : optionID.get(answerIndex);
        answerModel.questionID = questionId;
        answerModel.time = timeAtWhichAnswerWasSelected;

        selectedAnswerId = answerModel.answerID;

        message.setText(getAnswerSubmissionResponse(selectedAnswerId));

        answerModel.score = (rightAnswerId == selectedAnswerId) ? getBonus(timeAtWhichAnswerWasSelected) + baseScore : 0;

        APIServices apiServices = AppClient.getInstance().createService(APIServices.class);
        Call<BquizResponseModel> responseModelCall = apiServices.submitAnswer(new SharedPref().getAccessToken(getContext()), answerModel);

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
        return time >= 6 ? time * 2 : 0;
    }

    private String getAnswerSubmissionResponse(int selectedAnswerId) {
        String response;

        if (rightAnswerId == selectedAnswerId) {
            animationView.setAnimation(R.raw.right);
            animationView.playAnimation();

            response = (getBonus(timeAtWhichAnswerWasSelected) > 0) ? "Correct Answer (+"
                    + getBonus(timeAtWhichAnswerWasSelected) + " points)" : "Correct Answer.";
        } else {
            animationView.setAnimation(R.raw.wrong);
            animationView.playAnimation();

            response = (answerIndex != -7) ? "Incorrect Answer." : "No Option was chosen.";
        }

        return response;
    }
}
