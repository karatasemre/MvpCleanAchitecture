package emre.com.cleanarchitecturetutorial.ui.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import emre.com.cleanarchitecturetutorial.R;
import emre.com.cleanarchitecturetutorial.data.model.Item;
import emre.com.cleanarchitecturetutorial.ui.base.BaseActivity;
import emre.com.cleanarchitecturetutorial.ui.base.Navigator;

public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.main_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.main_button_retry)
    View retyrButton;
    @BindView(R.id.main_layout_retry)
    View retryLayout;
    @BindView(R.id.main_progress_bar)
    View progressBar;

    @Inject
    MainPresenter mainPresenter;
    private ItemsAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getActivityComponent().inject(this);

        itemsAdapter = new ItemsAdapter();
        itemsAdapter.setOnItemClickListener(itemData -> Navigator.navigationToDetailPage(MainActivity.this, itemData));

        recyclerView.setAdapter(itemsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mainPresenter.bindView(this);
        mainPresenter.loadItems();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.unbindView();
    }

    @Override
    public void showLoadingProgress(boolean show) {
        retryLayout.setVisibility(View.GONE);
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showItems(List<Item> items) {
        itemsAdapter.setItems(items);
        itemsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showItemsLoadingError() {
        retryLayout.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.main_button_retry)
    public void retry() {
        mainPresenter.loadItems();
    }
}
