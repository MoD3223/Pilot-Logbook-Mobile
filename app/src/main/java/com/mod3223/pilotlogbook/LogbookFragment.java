package com.mod3223.pilotlogbook;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogbookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogbookFragment extends Fragment {

    LinearLayout linearLayout;

    public LogbookFragment() {
        // Required empty public constructor
    }


    public static LogbookFragment newInstance() {
        LogbookFragment fragment = new LogbookFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);






    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_logbook, container, false);
    }

    @Override
    public void onViewCreated(View view,@Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        linearLayout = (LinearLayout) getView().findViewById(R.id.FragmentLogbookContainer);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,10,0,0);
        String login = MainTabbedPage.Login;
        Cursor cursor = MainActivity.db.rawQuery("SELECT Date FROM Logbooks WHERE pilotLogin = ?",new String[]{login});
        while (cursor.moveToNext()){
            String text = cursor.getString(cursor.getColumnIndex("Date"));
            Button button = new Button( getContext());
            button.setText(text);
            button.setLayoutParams(layoutParams);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO: Finish this, add navigation to proper Activity showing stats
                }
            });
        }
        cursor.close();

    }
}