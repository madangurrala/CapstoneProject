package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.root;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;

public class AppImagePresenter {
    public void load(Context ctx, String url, ImageView view) {
        Glide
                .with(ctx)
                .load(url)
                //.skipMemoryCache(true)
                //.diskCacheStrategy(DiskCacheStrategy.NONE)
                //.centerCrop()
                .placeholder(R.drawable.ic_panorama)
                .error(R.drawable.ic_broken_image)
                .fallback(R.drawable.ic_broken_image)
                .into(view);
    }

    public void loadCircle(Context ctx, String url, ImageView view) {
        Glide
                .with(ctx)
                .load(url)
                //.centerCrop()
                //.skipMemoryCache(true)
                //.diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.ic_panorama)
                .error(R.drawable.ic_broken_image)
                .fallback(R.drawable.ic_broken_image)
                .optionalTransform(new CenterCrop())
                .into(view);
    }

}
