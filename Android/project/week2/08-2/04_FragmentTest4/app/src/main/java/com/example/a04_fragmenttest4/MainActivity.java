package com.example.a04_fragmenttest4;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity
        implements WordsFragment.OnWordSelectedListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null)
                return;
            WordsFragment firstFragment = new WordsFragment();
            firstFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment , null)
                    .commit();
        }
    }

    public void onWordSelected(int position) {
        DefinitionFragment defFrag =
                (DefinitionFragment) getSupportFragmentManager().
                        findFragmentById(R.id.definition_fragment);
        if (defFrag != null) {
            defFrag.updateDefinitionView(position);
        } else {
            DefinitionFragment newFragment =
                    new DefinitionFragment();
            Bundle args = new Bundle();
            args.putInt(DefinitionFragment.ARG_POSITION,
                    position);
            newFragment.setArguments(args);
            FragmentTransaction transaction =
                    getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}