package com.example.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    private final static String SAVED_LAST_NUM = "last_num";
    private final static int START_SIZE = 100;

    private final static List<ListItem> items = new ArrayList<>();
    private int last_num = START_SIZE;
    private MyItemRecyclerViewAdapter adapter;
    private OnListFragmentInteractionListener mListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            last_num = savedInstanceState.getInt(SAVED_LAST_NUM);
        }

        for (int i = items.size() + 1; i <= last_num; i++) {
            items.add(createListItem(i));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.list_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), getResources().getInteger(R.integer.columns)));
        adapter = new MyItemRecyclerViewAdapter(items, mListener);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVED_LAST_NUM, last_num);
    }

    void addItem() {
        int size = adapter.getItemCount();
        items.add(createListItem(size + 1));
        adapter.notifyItemInserted(size);
    }

    private ListItem createListItem(int number) {
        int color = number % 2 == 0 ? R.color.colorEven : R.color.colorOdd;
        return new ListItem(Integer.toString(number), color);
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(ListItem item);
    }

    class ListItem {
        final String content;
        final int mColor;

        ListItem(String content, int color) {
            this.content = content;
            this.mColor = color;
        }
    }
}
