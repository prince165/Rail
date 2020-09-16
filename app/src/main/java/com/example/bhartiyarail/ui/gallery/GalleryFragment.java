package com.example.bhartiyarail.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bhartiyarail.R;
import com.example.bhartiyarail.network.ErrorResponse;
import com.example.bhartiyarail.network.PnrStatusResponse;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getPnrResponse("4436");
        galleryViewModel.pnrResponse.observe(this, new Observer<PnrStatusResponse>() {
            @Override
            public void onChanged(PnrStatusResponse pnrStatusResponse) {
                textView.setText(pnrStatusResponse.getPnrStatus().get(0).getTrainName());
            }
        });
        galleryViewModel.errorResponseData.observe(this, new Observer<ErrorResponse>() {
            @Override
            public void onChanged(ErrorResponse errorResponse) {
                Toast.makeText(getContext(), errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}