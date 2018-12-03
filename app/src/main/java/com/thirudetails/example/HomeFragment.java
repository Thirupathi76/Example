package com.thirudetails.example;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class HomeFragment extends Fragment {


    public static TextView latlongView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {

        View mRootView;
        Context context;
        TextView tv_comp_name, tv_branch, tv_designation, tv_dept, tv_user_name, tv_email;

        mRootView = inflater.inflate(R.layout.home_fragment, container, false);

        context = getActivity();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        tv_comp_name = mRootView.findViewById(R.id.tv_comp_name);
        tv_branch = mRootView.findViewById(R.id.tv_branch);
        tv_designation = mRootView.findViewById(R.id.tv_designation);
        tv_dept = mRootView.findViewById(R.id.tv_dept);
        tv_user_name = mRootView.findViewById(R.id.tv_user_name);

        tv_email = mRootView.findViewById(R.id.tv_email);
        latlongView = mRootView.findViewById(R.id.latlngText);

        /*tv_comp_name.setText(Utilities.getPrefernc(context, Constants.COMP_NAME));
        tv_branch.setText(Utilities.getPrefernc(context, Constants.BRANCH));
        tv_designation.setText(Utilities.getPrefernc(context, Constants.DESIGNATION));
        tv_dept.setText(Utilities.getPrefernc(context, Constants.DEPT));
        tv_user_name.setText(Utilities.getPrefernc(context, Constants.USER_NAME));
        tv_email.setText(Utilities.getPrefernc(context, Constants.EMAIL));*/

        return mRootView;
    }


    @Override
    public void onResume() {
        Log.d("SurveyFragment", "onStart()called");
        super.onResume();
    }

}
