package com.example.lab11.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import com.example.lab11.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainFragment extends Fragment {

    NavController navController;
    TextInputEditText cityText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        cityText = view.findViewById(R.id.cityInput);
        cityText.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE){
                String city = Objects.requireNonNull(cityText.getText().toString());
                if(!TextUtils.isEmpty(city)){
                    cityText.setText("");
                    Bundle args = new Bundle();
                    args.putString("city", city);
                    if (getActivity() != null){
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                    navController.navigate(R.id.action_mainFragment_to_dayFragment, args);
                    return true;
                }
            }
            Snackbar.make(view.findViewById(R.id.constraint_main), getString(R.string.error), Snackbar.LENGTH_LONG).show();
            return false;
        });
    }
}