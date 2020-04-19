package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.PropertyTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.root.AppImagePresenter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities.PropertyDetailsActivity;

public class MainPropertiesRecycleAdapter extends RecyclerView.Adapter<MainPropertiesRecycleAdapter.ViewHolder>
{
    private Context ctx;
    private List<PropertyTO> propertyTOS;
    private AppImagePresenter appImagePresenter;

    public MainPropertiesRecycleAdapter(Context ctx,List<PropertyTO> propertyTOS)
    {
        this.ctx=ctx;
        this.propertyTOS=propertyTOS;
        appImagePresenter=new AppImagePresenter();
        appImagePresenter.setIc_broken_image(R.drawable.default_property_image);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(ctx).inflate(R.layout.recycle_item1,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return propertyTOS!=null?propertyTOS.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.rootConstraint)
        public ConstraintLayout rootConstraint;
        @BindView(R.id.imgItem)
        public ImageView imgItem;
        @BindView(R.id.txtTitle)
        public TextView txtTitle;
        @BindView(R.id.txtSubTitle)
        public TextView txtSubTitle;
        @BindView(R.id.imgBtn2)
        public ImageButton imgBtn2;
        @BindView(R.id.imgBtn3)
        public ImageButton imgBtn3;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            imgBtn2.setVisibility(View.INVISIBLE);
            imgBtn3.setVisibility(View.INVISIBLE);
        }

        public void setData(int position)
        {
            PropertyTO propertyTO=propertyTOS.get(position);
            appImagePresenter.load(ctx,propertyTO.getSmallImagePath(),imgItem);
            txtTitle.setText(propertyTO.getTitle());
            txtSubTitle.setText(propertyTO.getShortDescription());
            rootConstraint.setTag(position);
        }
        @OnClick(R.id.rootConstraint)
        public void onClick(View view)
        {
            int position= Integer.parseInt(view.getTag().toString());
            Intent intent=new Intent(ctx, PropertyDetailsActivity.class);
            intent.putExtra("property",propertyTOS.get(position));
            ctx.startActivity(intent);
        }
    }

}
