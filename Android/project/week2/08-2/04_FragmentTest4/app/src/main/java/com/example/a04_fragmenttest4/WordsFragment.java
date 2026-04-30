package com.example.a04_fragmenttest4;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.fragment.app.Fragment;

public class WordsFragment extends Fragment {

    OnWordSelectedListener mCallback;
    View wordsView;
    ListView lv;

    public interface OnWordSelectedListener {
        public void onWordSelected(int position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        wordsView = inflater.inflate(R.layout.words_view,
                container, false);
        lv = wordsView.findViewById(R.id.listView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long l) {
                mCallback.onWordSelected(position);
                lv.setItemChecked(position, true);
            }
        });
        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB
                ? android.R.layout.simple_list_item_activated_1
                : android.R.layout.simple_list_item_1;
        lv.setAdapter(new ArrayAdapter<String>
                (getActivity(), layout, Data.words));
        return wordsView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.definition_fragment)
                != null)
        {
            lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onAttach(Context c) {
        super.onAttach(c);
        try {
            mCallback = (OnWordSelectedListener)getActivity() ;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnWordSelectedListener");
        }
    }
}