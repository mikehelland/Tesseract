package com.monadpad.tesseract;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SynthPreferences extends PreferenceActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.synth_prefs);
	}
}
