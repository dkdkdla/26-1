package com.example.a05_savesettings;

import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;

public class MySettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // 정의한 xml 리소스를 설정 화면으로 적용한다.
        setPreferencesFromResource(R.xml.frag_pref, rootKey);
    }
}