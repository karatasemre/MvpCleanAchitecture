package emre.com.cleanarchitecturetutorial.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import emre.com.cleanarchitecturetutorial.R;
import emre.com.cleanarchitecturetutorial.data.model.Item;

/**
 * Created by Emre.Karatas on 10.10.2018.
 */

public class ItemsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.list_item_title_text_view)
    TextView titleTextView;
    @BindView(R.id.list_item_image_view)
    ImageView imageView;

    public ItemsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Item item) {
        titleTextView.setText(item.getName());

        Glide.with(imageView.getContext())
                .load(item.getImageUrl())
                .into(imageView);
    }
}
