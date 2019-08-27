package com.nitrr.ecell.esummit.ecellapp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.SponsViewPagerAdapter;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.sponsors.SponsRVData;
import com.nitrr.ecell.esummit.ecellapp.models.sponsors.SponsorsModel;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SponsorsActivity extends BaseActivity {

    private TabLayout tabLayout;
    private ViewPager pager;
    private Toolbar toolbar;
    private ImageView circle1, circle2, circle3, circle4, circle5;
    private Bundle[] bundle;
    private String[] tabName;
    private int[] index;
    private int[][] toolbarColor = {{1, 12, 84}, {129, 129, 129}, {130, 87, 0}, {213, 74, 74}, {58, 167, 163}};
    private int[][] circle1Color = {{203, 239, 255}, {250, 250, 250}, {255, 228, 164}, {252, 216, 216}, {210, 255, 253}};
    private int[][] circle2Color = {{173, 218, 238}, {239, 239, 239}, {255, 222, 129}, {251, 191, 191}, {166, 245, 241}};
    private int[][] circle3Color = {{147, 203, 227}, {225, 225, 225}, {255, 212, 110}, {249, 170, 170}, {116, 241, 235}};
    private int[][] circle4Color = {{119, 196, 230}, {209, 209, 209}, {255, 191, 109}, {251, 147, 147}, {103, 230, 225}};
    private int[][] circle5Color = {{90, 177, 216}, {188, 188, 188}, {255, 174, 86}, {249, 116, 116}, {53, 220, 214}};
    private DialogInterface.OnClickListener cancelListener = (dialog, which) -> {
        dialog.cancel();
        SponsorsActivity.this.finish();
    };
    private SponsorsModel model;
    private List<SponsRVData> list = new ArrayList<SponsRVData>();
    private AlertDialog dialog;
    private DialogInterface.OnClickListener refreshListener = (dialog, which) -> APICall2019();

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_sponsors;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
        APICall2018();
    }

    private void APICall2019() {

        APIServices service = AppClient.getInstance().createService(APIServices.class);
        Call<SponsorsModel> call = service.getSponsorsData("2019");
        call.enqueue(new Callback<SponsorsModel>() {
            @Override
            public void onResponse(Call<SponsorsModel> call, Response<SponsorsModel> response) {
                if (getApplicationContext() != null) {
                    Log.e("response", response.toString());
                    if (response.isSuccessful()) {
                        model = response.body();
                        if (model != null) {
                            list.addAll(model.getList());
                            setTabs();
                            dialog.cancel();

                        } else {
                            Log.e("response list empty", "response is empty and is: " + response.toString());
                            dialog.cancel();
                        }
                    } else {
                        Log.e("response failure", "response is " + response.toString());
                        Utils.showDialog(SponsorsActivity.this, null, false, "Something went wrong.", SponsorsActivity.this.getString(R.string.wasnt_able_to_load), "Retry", refreshListener, "Cancel", cancelListener);
                        dialog.cancel();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<SponsorsModel> call, @NonNull Throwable t) {
                dialog.cancel();
                Log.e("onFailure called", "error is " + t.toString());
                if (getApplicationContext() != null) {
                    Utils.showDialog(SponsorsActivity.this, null, false, "Something went wrong.", SponsorsActivity.this.getString(R.string.wasnt_able_to_load), "Retry", refreshListener, "Cancel", cancelListener);
                }
            }
        });
    }

    private void APICall2018() {

        dialog = Utils.showProgressBar(this, "Loading Sponsors..");

        APIServices service = AppClient.getInstance().createService(APIServices.class);
        Call<SponsorsModel> call = service.getSponsorsData("2018");
        call.enqueue(new Callback<SponsorsModel>() {
            @Override
            public void onResponse(Call<SponsorsModel> call, Response<SponsorsModel> response) {
                if (getApplicationContext() != null) {
                    Log.e("response", response.toString());
                    if (response.isSuccessful()) {
                        model = response.body();
                        if (model != null) {
                            list.addAll(model.getList());
                            APICall2019();
                        } else {
                            Log.e("response list empty", "response is empty and is: " + response.toString());
                            dialog.cancel();
                        }
                    } else {
                        Log.e("response failure", "response is " + response.toString());
                        Utils.showDialog(SponsorsActivity.this, null, false, "Something went wrong.", SponsorsActivity.this.getString(R.string.wasnt_able_to_load), "Retry", refreshListener, "Cancel", cancelListener);
                        dialog.cancel();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<SponsorsModel> call, @NonNull Throwable t) {
                dialog.cancel();

                if (getApplicationContext() != null) {
                    Utils.showDialog(SponsorsActivity.this, null, false, "Something went wrong.", SponsorsActivity.this.getString(R.string.wasnt_able_to_load), "Retry", refreshListener, "Cancel", cancelListener);
                }
            }
        });
    }

    private void setTabs() {
        reformatList();
        pager.setAdapter(new SponsViewPagerAdapter(getSupportFragmentManager(), bundle, tabName, index));
        tabLayout.setupWithViewPager(pager, false);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout) {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position % 5 == 0) {
                    toolbar.setBackgroundColor(color(toolbarColor[0][0], toolbarColor[0][1], toolbarColor[0][2], toolbarColor[1][0], toolbarColor[1][1], toolbarColor[1][2], positionOffset));
                    circle1.setColorFilter(color(circle1Color[0][0], circle1Color[0][1], circle1Color[0][2], circle1Color[1][0], circle1Color[1][1], circle1Color[1][2], positionOffset));
                    circle2.setColorFilter(color(circle2Color[0][0], circle3Color[0][1], circle2Color[0][2], circle2Color[1][0], circle2Color[1][1], circle2Color[1][2], positionOffset));
                    circle3.setColorFilter(color(circle3Color[0][0], circle4Color[0][1], circle3Color[0][2], circle3Color[1][0], circle3Color[1][1], circle3Color[1][2], positionOffset));
                    circle4.setColorFilter(color(circle4Color[0][0], circle4Color[0][1], circle4Color[0][2], circle4Color[1][0], circle4Color[1][1], circle4Color[1][2], positionOffset));
                    circle5.setColorFilter(color(circle5Color[0][0], circle5Color[0][1], circle5Color[0][2], circle5Color[1][0], circle5Color[1][1], circle5Color[1][2], positionOffset));

                } else if (position % 5 == 1) {
                    toolbar.setBackgroundColor(color(toolbarColor[1][0], toolbarColor[1][1], toolbarColor[1][2], toolbarColor[2][0], toolbarColor[2][1], toolbarColor[2][2], positionOffset));
                    circle1.setColorFilter(color(circle1Color[1][0], circle1Color[1][1], circle1Color[1][2], circle1Color[2][0], circle1Color[2][1], circle1Color[2][2], positionOffset));
                    circle2.setColorFilter(color(circle2Color[1][0], circle2Color[1][1], circle2Color[1][2], circle2Color[2][0], circle2Color[2][1], circle2Color[2][2], positionOffset));
                    circle3.setColorFilter(color(circle3Color[1][0], circle3Color[1][1], circle3Color[1][2], circle3Color[2][0], circle3Color[2][1], circle3Color[2][2], positionOffset));
                    circle4.setColorFilter(color(circle4Color[1][0], circle4Color[1][1], circle4Color[1][2], circle4Color[2][0], circle4Color[2][1], circle4Color[2][2], positionOffset));
                    circle5.setColorFilter(color(circle5Color[1][0], circle5Color[1][1], circle5Color[1][2], circle5Color[2][0], circle5Color[2][1], circle5Color[2][2], positionOffset));


                } else if (position % 5 == 2) {
                    toolbar.setBackgroundColor(color(toolbarColor[2][0], toolbarColor[2][1], toolbarColor[2][2], toolbarColor[3][0], toolbarColor[3][1], toolbarColor[3][2], positionOffset));
                    circle1.setColorFilter(color(circle1Color[2][0], circle1Color[2][1], circle1Color[2][2], circle1Color[3][0], circle1Color[3][1], circle1Color[3][2], positionOffset));
                    circle2.setColorFilter(color(circle2Color[2][0], circle2Color[2][1], circle2Color[2][2], circle2Color[3][0], circle2Color[3][1], circle2Color[3][2], positionOffset));
                    circle3.setColorFilter(color(circle3Color[2][0], circle3Color[2][1], circle3Color[2][2], circle3Color[3][0], circle3Color[3][1], circle3Color[3][2], positionOffset));
                    circle4.setColorFilter(color(circle4Color[2][0], circle4Color[2][1], circle4Color[2][2], circle4Color[3][0], circle4Color[3][1], circle4Color[3][2], positionOffset));
                    circle5.setColorFilter(color(circle5Color[2][0], circle5Color[2][1], circle5Color[2][2], circle5Color[3][0], circle5Color[3][1], circle5Color[3][2], positionOffset));
                } else if (position % 5 == 3) {
                    toolbar.setBackgroundColor(color(toolbarColor[3][0], toolbarColor[3][1], toolbarColor[3][2], toolbarColor[4][0], toolbarColor[4][1], toolbarColor[4][2], positionOffset));
                    circle1.setColorFilter(color(circle1Color[3][0], circle1Color[3][1], circle1Color[3][2], circle1Color[4][0], circle1Color[4][1], circle1Color[4][2], positionOffset));
                    circle2.setColorFilter(color(circle2Color[3][0], circle2Color[3][1], circle2Color[3][2], circle2Color[4][0], circle2Color[4][1], circle2Color[4][2], positionOffset));
                    circle3.setColorFilter(color(circle3Color[3][0], circle3Color[3][1], circle3Color[3][2], circle3Color[4][0], circle3Color[4][1], circle3Color[4][2], positionOffset));
                    circle4.setColorFilter(color(circle4Color[3][0], circle4Color[3][1], circle4Color[3][2], circle4Color[4][0], circle4Color[4][1], circle4Color[4][2], positionOffset));
                    circle5.setColorFilter(color(circle5Color[3][0], circle5Color[3][1], circle5Color[3][2], circle5Color[4][0], circle5Color[4][1], circle5Color[4][2], positionOffset));
                } else if (position % 5 == 4) {
                    toolbar.setBackgroundColor(color(toolbarColor[4][0], toolbarColor[4][1], toolbarColor[4][2], toolbarColor[0][0], toolbarColor[0][1], toolbarColor[0][2], positionOffset));
                    circle1.setColorFilter(color(circle1Color[4][0], circle1Color[4][1], circle1Color[4][2], circle1Color[0][0], circle1Color[0][1], circle1Color[0][2], positionOffset));
                    circle2.setColorFilter(color(circle2Color[4][0], circle2Color[4][1], circle2Color[4][2], circle2Color[0][0], circle2Color[0][1], circle2Color[0][2], positionOffset));
                    circle3.setColorFilter(color(circle3Color[4][0], circle3Color[4][1], circle3Color[4][2], circle3Color[0][0], circle3Color[0][1], circle3Color[0][2], positionOffset));
                    circle4.setColorFilter(color(circle4Color[4][0], circle4Color[4][1], circle4Color[4][2], circle4Color[0][0], circle4Color[0][1], circle4Color[0][2], positionOffset));
                    circle5.setColorFilter(color(circle5Color[4][0], circle5Color[4][1], circle5Color[4][2], circle5Color[0][0], circle5Color[0][1], circle5Color[0][2], positionOffset));
                }
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        });

        dialog.dismiss();
    }

    void reformatList() {
        Set<String> set = new HashSet<>();
        for (int x = 0; x < list.size(); x++) {
            set.add(list.get(x).getType());
        }

        tabName = new String[set.size()];
        int tabindex = 0;
        for (String str : set)
            tabName[tabindex++] = str;
        tabindex = 0;


        bundle = new Bundle[set.size()];
        index = new int[set.size()];

        for (int x = 0; x < set.size(); x++) {
            bundle[x] = new Bundle();
            index[x] = 0;
        }

        SponsRVData data;
        List<Bundle> bundleList = new ArrayList<>();
        List<Integer> indexList = new ArrayList<>();
        List<String> setlist = new ArrayList<>();

        setlist.addAll(set);

        Arrays.sort(tabName);
        Collections.sort(setlist);

        for (int y = 0; y < set.size(); y++) {
            for (int x = 0; x < list.size(); x++) {
                data = list.get(x);
                if (setlist.get(y).contentEquals(data.getType())) {
                    bundle[y].putString("name" + tabindex, data.getName());
                    bundle[y].putString("image" + tabindex, data.getImg());
                    bundle[y].putString("id" + tabindex, data.getId());
                    bundle[y].putInt("year" + tabindex, data.getYear());
                    bundle[y].putString("website" + tabindex, data.getWebsite());
                    tabindex++;
                }
            }
            index[y] = tabindex;
            tabindex = 0;
        }

        for (int x = 0; x < 5; x++) {
            if (!bundle[x].isEmpty())
                bundleList.add(bundle[x]);
            if (index[x] != 0)
                indexList.add(index[x]);
        }
    }

    private void initialize() {
        tabLayout = findViewById(R.id.tab);
        pager = findViewById(R.id.spons_view_pager);
        toolbar = findViewById(R.id.toolbar);
        circle1 = findViewById(R.id.spons_bgcircle_1);
        circle2 = findViewById(R.id.spons_bgcircle_2);
        circle3 = findViewById(R.id.spons_bgcircle_3);
        circle4 = findViewById(R.id.spons_bgcircle_4);
        circle5 = findViewById(R.id.spons_bgcircle_5);

        ImageView back = findViewById(R.id.sponsor_back);
        back.setOnClickListener(v -> finish());
    }

    int colorValue(int Initial, int Final, float pos) {
        return (int) (Final * pos + Initial * (1 - pos));
    }

    int color(int IR, int IG, int IB, int FR, int FG, int FB, float pos) {
        return Color.rgb(colorValue(IR, FR, pos), colorValue(IG, FG, pos), colorValue(IB, FB, pos));
    }

    @Override
    protected void onDestroy() {
        HomeActivity.setSelected(false);
        super.onDestroy();
    }

}
