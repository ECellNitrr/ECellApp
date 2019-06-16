package com.nitrr.ecell.esummit.ecellapp.misc;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.nitrr.ecell.esummit.ecellapp.R;

public class BottomSheetFragment extends BottomSheetDialogFragment {

//    private ButtomSheetListener buttomSheetListener;


    public BottomSheetFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.hamburgerbottomsheet,container,false);
        LinearLayout aboutUs = v.findViewById(R.id.about_us);
        LinearLayout signOut = v.findViewById(R.id.sign_out);

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO(1) about us intent need to be added
            }
        });
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO(2) sign out action needs to be implemented
            }
        });

        return v;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new BottomSheetDialog(requireContext(),R.style.BottomSheetDialogTheme);
        return dialog;
        //return super.onCreateDialog(savedInstanceState);
    }

    //    public interface ButtomSheetListener{
//        void OnClicked(String string);
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        try{
//            buttomSheetListener = (ButtomSheetListener) context;
//        }
//        catch (ClassCastException e){
//            throw new ClassCastException(context.toString()+" must implement bottom sheet listener");
//        }
//    }
}
