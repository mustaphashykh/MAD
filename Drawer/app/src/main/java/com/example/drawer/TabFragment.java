package com.example.drawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class TabFragment extends Fragment {

    public TabFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment layout
        View rootView = inflater.inflate(R.layout.fragment_tab, container, false);

        // Find TextView by ID and set content
        TextView textView = rootView.findViewById(R.id.tab_content);
        textView.setText("This is a fake tab content!");

        return rootView;
    }
}
