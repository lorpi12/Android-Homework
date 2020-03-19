package com.example.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


public class SingleItemFragment extends Fragment {
    private static final String ARG_TEXT = "text_value";
    private static final String ARG_COLOR = "color_value";

    private String text;
    private int color;

    public SingleItemFragment() {
    }

    static SingleItemFragment newInstance(String text, int color) {
        SingleItemFragment fragment = new SingleItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        args.putInt(ARG_COLOR, color);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            text = getArguments().getString(ARG_TEXT);
            color = getArguments().getInt(ARG_COLOR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_blank, container, false);
        TextView textView = v.findViewById(R.id.fragmentText);
        textView.setText(text);
        textView.setTextColor(ContextCompat.getColor(inflater.getContext(), color));
        return v;
    }
}
