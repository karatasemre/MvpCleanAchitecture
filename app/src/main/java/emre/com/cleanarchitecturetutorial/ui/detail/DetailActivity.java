package emre.com.cleanarchitecturetutorial.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import emre.com.cleanarchitecturetutorial.R;
import emre.com.cleanarchitecturetutorial.data.model.Item;
import emre.com.cleanarchitecturetutorial.data.model.ItemDetails;
import emre.com.cleanarchitecturetutorial.ui.base.BaseActivity;

/**
 * Created by Emre.Karatas on 10.10.2018.
 */

public class DetailActivity extends BaseActivity implements DetailView {

    private static final String DETAIL_ITEM_DATA = "DETAIL_ITEM_DATA";

    @BindView(R.id.detail_title_text_view)
    TextView titleTextView;
    @BindView(R.id.detail_height_text_view)
    TextView heightTextView;
    @BindView(R.id.detail_weight_text_view)
    TextView weightTextView;
    @BindView(R.id.detail_type_text_view)
    TextView typeTextView;
    @BindView(R.id.detail_front_image_view)
    ImageView frontImageView;
    @BindView(R.id.detail_back_image_view)
    ImageView backImageView;
    @BindView(R.id.detail_progress_bar)
    View progressBar;
    @BindView(R.id.detail_info_layout)
    View infoLayout;

    @Inject
    DetailPresenter detailPresenter;

    public static Intent getCallingIntent(Context context, Item item) {
        Intent callingIntent = new Intent(context, DetailActivity.class);
        callingIntent.putExtra(DETAIL_ITEM_DATA, item);
        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        getActivityComponent().inject(this);

        detailPresenter.bindView(this);

        Item item = getIntent().getParcelableExtra(DETAIL_ITEM_DATA);
        detailPresenter.loadItemDetails(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detailPresenter.unbindView();
    }

    @Override
    public void showLoadingProgress(boolean show) {
        infoLayout.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showItemDetails(ItemDetails itemDetails) {
        titleTextView.setText(itemDetails.getName());
        heightTextView.setText(String.valueOf(itemDetails.getHeight()));
        weightTextView.setText(String.valueOf(itemDetails.getWeight()));
        typeTextView.setText(itemDetails.getType());

        Glide.with(this)
                .load(itemDetails.getSprites().getFrontImageUrl())
                .into(frontImageView);

        Glide.with(this)
                .load(itemDetails.getSprites().getBackImageUrl())
                .into(backImageView);
    }

    @Override
    public void showItemsLoadingError() {
        Toast.makeText(this, "Failed !", Toast.LENGTH_SHORT).show();
    }
}
