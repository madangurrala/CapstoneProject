package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(int position)
        {
            PropertyTO propertyTO=propertyTOS.get(position);
            appImagePresenter.load(ctx,propertyTO.getSmallImagePath(),imgItem);
            txtTitle.setText(propertyTO.getTitle());
            txtSubTitle.setText(propertyTO.getShortDescription());
        }
        @OnClick(R.id.rootConstraint)
        public void onClick(View view)
        {
            Toast.makeText(ctx, "We still are working to complete this part", Toast.LENGTH_SHORT).show();
        }
    }

}
