package com.cutloose.cutloose.utils;


import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class BindingUtils {

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        Context context = view.getContext();
        Glide.with(context).load(url).apply(RequestOptions.circleCropTransform()).into(view);
    }
}
