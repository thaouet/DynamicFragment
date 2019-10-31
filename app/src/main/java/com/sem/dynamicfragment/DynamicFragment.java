package com.sem.dynamicfragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class DynamicFragment extends Fragment {

    // 1 - Declare our interface that will be implemented by any container activity
    public interface DynamicFragmentCallback{
        void onBtnClicked();
    }

    //2 - Declare callback
    DynamicFragmentCallback callback;


    private static final String TITLE = "TITLE";

    public static DynamicFragment newInstance(String title) {
        DynamicFragment fragment = new DynamicFragment();
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }


    // 3 - Create callback to parent activity
    private void createCallbackToParentActivity(){
        try {
            //Parent activity will automatically subscribe to callback
            callback = (DynamicFragmentCallback) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(e.toString()+ " must implement OnButtonClickedListener");
        }
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
// 4 - Call the method that creating callback after being attached to parent activity
        this.createCallbackToParentActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dynamicfragment, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // handle your fragment events here
       final TextView txtView = (TextView)view.findViewById(R.id.tv);

        if (getArguments() != null) {
            Bundle args = getArguments();
            if (args.containsKey(TITLE))
                txtView.setText(args.getString(TITLE));
        }




        Button btn = (Button)view.findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView.setText("Dynamic Fragment Test");
                // 5 - Spread the click to the parent activity
                callback.onBtnClicked();
            }
        });

    }
}
