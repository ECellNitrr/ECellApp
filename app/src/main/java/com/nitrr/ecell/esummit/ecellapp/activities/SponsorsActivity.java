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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SponsorsActivity extends BaseActivity {

    private TabLayout tabLayout;
    private ViewPager pager;
    private Toolbar toolbar;
    private ImageView circle1, circle2, circle3, circle4, circle5;
    private Bundle[] bundle = new Bundle[5];
    private int[] index = {0,0,0,0,0};
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
                Log.e("onFailure called","error is "+t.toString());
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
        SponsRVData data;
        List<String> tab = new ArrayList<>();
        int[] tabtitle = new int[5];
        List<Bundle> bundleList = new ArrayList<>();
        List<Integer> indexList = new ArrayList<>();
        bundle[0] = new Bundle();
        bundle[1] = new Bundle();
        bundle[2] = new Bundle();
        bundle[3] = new Bundle();
        bundle[4] = new Bundle();

        for (int x = 0; x < list.size(); x++) {
            data = list.get(x);

            if (data.getType().contentEquals("ATS")) {

                    tabtitle[0]++;
                    index[0]++;
                bundle[0].putString("type" + index[0], "Associate Sponsors");
                bundle[0].putString("name" + index[0], data.getName());
                bundle[0].putString("image" + index[0], data.getImg());
                bundle[0].putString("id" + index[0], data.getId());
                bundle[0].putInt("year" + index[0], data.getYear());
                bundle[0].putString("website" + index[0], data.getWebsite());

            } else if (data.getType().contentEquals("PTS")) {

                tabtitle[1]++;
                index[1]++;
                bundle[1].putString("type" + index[1], "Platinum Sponsors");
                bundle[1].putString("name" + index[1], data.getName());
                bundle[1].putString("image" + index[1], data.getImg());
                bundle[1].putString("id" + index[1], data.getId());
                bundle[1].putInt("year" + index[1], data.getYear());
                bundle[1].putString("website" + index[1], data.getWebsite());

            } else if (data.getType().contentEquals("GDS")) {
                tabtitle[2]++;
                index[2]++;
                bundle[2].putString("type" + index[2], "Gold Sponsors");
                bundle[2].putString("name" + index[2], data.getName());
                bundle[2].putString("image" + index[2], data.getImg());
                bundle[2].putString("id" + index[2], data.getId());
                bundle[2].putInt("year" + index[2], data.getYear());
                bundle[2].putString("website" + index[2], data.getWebsite());

            } else if (data.getType().contentEquals("TLS")) {
                tabtitle[3]++;
                index[3]++;
                bundle[3].putString("type" + index[3], "Title Sponsors");
                bundle[3].putString("name" + index[3], data.getName());
                bundle[3].putString("image" + index[3], data.getImg());
                bundle[3].putString("id" + index[3], data.getId());
                bundle[3].putInt("year" + index[3], data.getYear());
                bundle[3].putString("website" + index[3], data.getWebsite());

            } else if (data.getType().contentEquals("PRS")) {
                tabtitle[4]++;
                index[4]++;
                bundle[4].putString("type" + index[4], "Partner Sponsors");
                bundle[4].putString("name" + index[4], data.getName());
                bundle[4].putString("image" + index[4], data.getImg());
                bundle[4].putString("id" + index[4], data.getId());
                bundle[4].putInt("year" + index[4], data.getYear());
                bundle[4].putString("website" + index[4], data.getWebsite());
            }
        }
        for(int x=0;x<tabtitle.length;x++){
            if(tabtitle[x]>0)
                switch (x){
                    case 0:
                        tab.add("Associate");
                        break;
                    case 1:
                        tab.add("Platinum");
                        break;
                    case 2:
                        tab.add("Gold");
                        break;
                    case 3:
                        tab.add("Title");
                        break;
                    case 4:
                        tab.add("Partner");
                        break;
                }
        }

        for (int x = 0; x < 5; x++) {
            if (!bundle[x].isEmpty())
                bundleList.add(bundle[x]);
            if (index[x] != 0)
                indexList.add(index[x]);
        }
        bundle = new Bundle[bundleList.size()];
        index = new int[indexList.size()];

        String[] tabName = new String[tab.size()];
        for (int x = 0; x < tab.size(); x++) {
            tabName[x] = tab.get(x);
            bundle[x] = bundleList.get(x);
            index[x] = indexList.get(x);
        }

        pager.setAdapter(new SponsViewPagerAdapter(getSupportFragmentManager(), bundle, tabName, index));
        tabLayout.setupWithViewPager(pager, false);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }
            @Override public void onTabUnselected(TabLayout.Tab tab) { }
            @Override public void onTabReselected(TabLayout.Tab tab) { }
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
                if (bundle.length == 5) {
                    if (position == 0) {
                        toolbar.setBackgroundColor(color(toolbarColor[0][0], toolbarColor[0][1], toolbarColor[0][2], toolbarColor[1][0], toolbarColor[1][1], toolbarColor[1][2], positionOffset));
                        circle1.setColorFilter(color(circle1Color[0][0], circle1Color[0][1], circle1Color[0][2], circle1Color[1][0], circle1Color[1][1], circle1Color[1][2], positionOffset));
                        circle2.setColorFilter(color(circle2Color[0][0], circle3Color[0][1], circle2Color[0][2], circle2Color[1][0], circle1Color[1][1], circle1Color[1][2], positionOffset));
                        circle3.setColorFilter(color(circle3Color[0][0], circle4Color[0][1], circle3Color[0][2], circle3Color[1][0], circle1Color[1][1], circle1Color[1][2], positionOffset));
                        circle4.setColorFilter(color(circle4Color[0][0], circle4Color[0][1], circle4Color[0][2], circle4Color[1][0], circle1Color[1][1], circle1Color[1][2], positionOffset));
                        circle5.setColorFilter(color(circle5Color[0][0], circle5Color[0][1], circle5Color[0][2], circle5Color[1][0], circle1Color[1][1], circle1Color[1][2], positionOffset));

                    } else if (position == 1) {
                        toolbar.setBackgroundColor(color(toolbarColor[1][0], toolbarColor[1][1], toolbarColor[1][2], toolbarColor[2][0], toolbarColor[2][1], toolbarColor[2][2], positionOffset));
                        circle1.setColorFilter(color(circle1Color[1][0], circle1Color[1][1], circle1Color[1][2], circle1Color[2][0], circle1Color[2][1], circle1Color[2][2], positionOffset));
                        circle2.setColorFilter(color(circle2Color[1][0], circle3Color[1][1], circle2Color[1][2], circle2Color[2][0], circle1Color[2][1], circle1Color[2][2], positionOffset));
                        circle3.setColorFilter(color(circle3Color[1][0], circle4Color[1][1], circle3Color[1][2], circle3Color[2][0], circle1Color[2][1], circle1Color[2][2], positionOffset));
                        circle4.setColorFilter(color(circle4Color[1][0], circle4Color[1][1], circle4Color[1][2], circle4Color[2][0], circle1Color[2][1], circle1Color[2][2], positionOffset));
                        circle5.setColorFilter(color(circle5Color[1][0], circle5Color[1][1], circle5Color[1][2], circle5Color[2][0], circle1Color[2][1], circle1Color[2][2], positionOffset));


                    } else if (position == 2) {
                        toolbar.setBackgroundColor(color(toolbarColor[2][0], toolbarColor[2][1], toolbarColor[2][2], toolbarColor[3][0], toolbarColor[3][1], toolbarColor[3][2], positionOffset));
                        circle1.setColorFilter(color(circle1Color[2][0], circle1Color[2][1], circle1Color[2][2], circle1Color[3][0], circle1Color[3][1], circle1Color[3][2], positionOffset));
                        circle2.setColorFilter(color(circle2Color[2][0], circle3Color[2][1], circle2Color[2][2], circle2Color[3][0], circle1Color[3][1], circle1Color[3][2], positionOffset));
                        circle3.setColorFilter(color(circle3Color[2][0], circle4Color[2][1], circle3Color[2][2], circle3Color[3][0], circle1Color[3][1], circle1Color[3][2], positionOffset));
                        circle4.setColorFilter(color(circle4Color[2][0], circle4Color[2][1], circle4Color[2][2], circle4Color[3][0], circle1Color[3][1], circle1Color[3][2], positionOffset));
                        circle5.setColorFilter(color(circle5Color[2][0], circle5Color[2][1], circle5Color[2][2], circle5Color[3][0], circle1Color[3][1], circle1Color[3][2], positionOffset));
                    } else if (position == 3) {
                        toolbar.setBackgroundColor(color(toolbarColor[3][0], toolbarColor[3][1], toolbarColor[3][2], toolbarColor[4][0], toolbarColor[4][1], toolbarColor[4][2], positionOffset));
                        circle1.setColorFilter(color(circle1Color[3][0], circle1Color[3][1], circle1Color[3][2], circle1Color[4][0], circle1Color[4][1], circle1Color[4][2], positionOffset));
                        circle2.setColorFilter(color(circle2Color[3][0], circle3Color[3][1], circle2Color[3][2], circle2Color[4][0], circle1Color[4][1], circle1Color[4][2], positionOffset));
                        circle3.setColorFilter(color(circle3Color[3][0], circle4Color[3][1], circle3Color[3][2], circle3Color[4][0], circle1Color[4][1], circle1Color[4][2], positionOffset));
                        circle4.setColorFilter(color(circle4Color[3][0], circle4Color[3][1], circle4Color[3][2], circle4Color[4][0], circle1Color[4][1], circle1Color[4][2], positionOffset));
                        circle5.setColorFilter(color(circle5Color[3][0], circle5Color[3][1], circle5Color[3][2], circle5Color[4][0], circle1Color[4][1], circle1Color[4][2], positionOffset));
                    }
                }
                else if (bundle.length == 4) {

                    if (position == 0) {
                        toolbar.setBackgroundColor(color(1, 12, 84, 130, 87, 0, positionOffset));
                        circle1.setColorFilter(color(203, 239, 255, 255, 228, 164, positionOffset));
                        circle2.setColorFilter(color(173, 218, 238, 255, 222, 129, positionOffset));
                        circle3.setColorFilter(color(147, 203, 227, 255, 212, 110, positionOffset));
                        circle4.setColorFilter(color(119, 196, 230, 255, 191, 109, positionOffset));
                        circle5.setColorFilter(color(90, 177, 216, 255, 174, 86, positionOffset));

                    } else if (position == 1) {
                        toolbar.setBackgroundColor(color(130, 87, 0, 213, 74, 74, positionOffset));
                        circle1.setColorFilter(color(255, 228, 164, 252, 216, 216, positionOffset));
                        circle2.setColorFilter(color(255, 222, 129, 251, 191, 191, positionOffset));
                        circle3.setColorFilter(color(255, 212, 110, 249, 170, 170, positionOffset));
                        circle4.setColorFilter(color(255, 191, 109, 251, 147, 147, positionOffset));
                        circle5.setColorFilter(color(255, 174, 86, 249, 116, 116, positionOffset));

                    } else if (position == 2) {
                        toolbar.setBackgroundColor(color(213, 74, 74, 58, 167, 163, positionOffset));
                        circle1.setColorFilter(color(252, 216, 216, 210, 255, 253, positionOffset));
                        circle2.setColorFilter(color(251, 191, 191, 166, 245, 241, positionOffset));
                        circle3.setColorFilter(color(249, 170, 170, 116, 241, 235, positionOffset));
                        circle4.setColorFilter(color(251, 147, 147, 103, 230, 225, positionOffset));
                        circle5.setColorFilter(color(249, 116, 116, 53, 220, 214, positionOffset));
                    }
                }
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        });

        dialog.dismiss();
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
