package com.example.a04_fragmenttest4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class DefinitionFragment extends Fragment {
    final static String ARG_POSITION = "position";
    int mCurrentPosition = 0;
    View defView = null;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mCurrentPosition =
                    savedInstanceState.getInt(ARG_POSITION);
        }
        defView = inflater.inflate(R.layout.definition_view,
                container, false);
        return defView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            updateDefinitionView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            updateDefinitionView(mCurrentPosition);
        }
    }

    public void updateDefinitionView(int position) {
        ((TextView)defView).setText(Data.definitions[position]);
        mCurrentPosition = position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }
}