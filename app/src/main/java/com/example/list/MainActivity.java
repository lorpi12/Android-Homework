package com.example.list;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity
        implements ListFragment.OnListFragmentInteractionListener {

    private static final String SAVED_STATE = "state";
    private static final String LIST_STATE = "list";
    private static final String SINGLE_NUM_STATE = "single";

    private String curr_state = LIST_STATE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            String savedState = savedInstanceState.getString(SAVED_STATE);
            if (savedState != null) {
                curr_state = savedState;
            }
        }

        if (curr_state.equals(LIST_STATE)) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentCont, new ListFragment()).commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        curr_state = LIST_STATE;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVED_STATE, curr_state);
    }

    @Override
    public void onListFragmentInteraction(ListFragment.ListItem item) {
        SingleItemFragment fragment = SingleItemFragment.newInstance(item.content, item.mColor);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentCont, fragment).addToBackStack(null).commit();
        curr_state = SINGLE_NUM_STATE;
    }

    public void onAddButtonClick(View view) {
        ListFragment fragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentCont);
        if (fragment != null) {
            fragment.addItem();
        }
    }
}
