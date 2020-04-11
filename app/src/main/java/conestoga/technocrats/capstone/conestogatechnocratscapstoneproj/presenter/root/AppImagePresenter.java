package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.root;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;

public class AppImagePresenter
{
    private int ic_panorama=R.drawable.ic_panorama;
    private int ic_broken_image=R.drawable.ic_broken_image;

    public void setIc_panorama(int ic_panorama) {
        this.ic_panorama = ic_panorama;
    }

    public void setIc_broken_image(int ic_broken_image) {
        this.ic_broken_image = ic_broken_image;
    }

    public void load(Context ctx, String url, ImageView view)
    {
        Glide
                .with(ctx)
                .load(url)
                //.skipMemoryCache(true)
                //.diskCacheStrategy(DiskCacheStrategy.NONE)
                //.centerCrop()
                .placeholder(ic_panorama)
                .error(ic_broken_image)
                .fallback(ic_broken_image)
                .into(view);
    }

    public void loadCircle(Context ctx, String url, ImageView view) {
        Glide
                .with(ctx)
                .load(url)
                //.centerCrop()
                //.skipMemoryCache(true)
                //.diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(ic_panorama)
                .error(ic_broken_image)
                .fallback(ic_broken_image)
                .optionalTransform(new CenterCrop())
                .into(view);
    }

}
