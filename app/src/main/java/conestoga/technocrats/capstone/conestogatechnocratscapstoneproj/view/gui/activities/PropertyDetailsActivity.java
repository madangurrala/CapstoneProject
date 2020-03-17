package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;

public class PropertyDetailsActivity extends AppCompatActivity
{
    @BindView(value = R.id.btnShowMap)
    public MaterialButton btnShowMap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_details);
        ButterKnife.bind(this);
    }

    @OnClick(value = {R.id.btnShowMap})
    public void doClick(View view)
    {
        Intent intent=new Intent(this,PropertyMapActivity.class);
        startActivity(intent);
    }
}
