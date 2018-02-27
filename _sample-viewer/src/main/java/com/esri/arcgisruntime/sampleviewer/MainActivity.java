package com.esri.arcgisruntime.sampleviewer;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

public class MainActivity extends AppCompatActivity implements CategoryAdapter.OnCategoryClicked {

  private RecyclerView mRecyclerView;
  private List<Category> mCategoryList;
  private CategoryAdapter mCategoryAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mRecyclerView = findViewById(R.id.recyclerView);
    mCategoryList = new ArrayList<>();
    mCategoryAdapter = new CategoryAdapter(this, mCategoryList);
    mCategoryAdapter.setOnCategoryClicked(this);

    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
    mRecyclerView.setLayoutManager(mLayoutManager);
    mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    mRecyclerView.setAdapter(mCategoryAdapter);

    mCategoryList.add(new Category("ANALYSIS", toBitmap(R.drawable.analysis_icon), toBitmap(R.drawable.analysis_background)));
    mCategoryList.add(new Category("CLOUD & PORTAL", toBitmap(R.drawable.cloud_and_portal_icon), toBitmap(R.drawable.layers_background)));
    mCategoryList.add(new Category("LAYERS", toBitmap(R.drawable.layers_icon), toBitmap(R.drawable.layers_background)));
    mCategoryList.add(new Category("MANAGE DATA", toBitmap(R.drawable.manage_data_icon), toBitmap(R.drawable.manage_data_background)));
    mCategoryList.add(new Category("MAPS & SCENES", toBitmap(R.drawable.maps_and_scenes_icon), toBitmap(R.drawable.maps_and_scenes_background)));
    mCategoryList.add(new Category("ROUTING & LOGISTICS", toBitmap(R.drawable.routing_and_logistics_icon), toBitmap(R.drawable.routing_and_logistics_background)));
    mCategoryList.add(new Category("SEARCH & QUERY", toBitmap(R.drawable.search_and_query_icon), toBitmap(R.drawable.search_and_query_background)));
    mCategoryList.add(new Category("VISUALIZATION", toBitmap(R.drawable.visualization_icon), toBitmap(R.drawable.visualization_background)));
    mCategoryAdapter.notifyDataSetChanged();

  }

  /**
   * Helper method to convert a drawable resource to a bitmap.
   */
  private Bitmap toBitmap(int drawable) {
    return BitmapFactory.decodeResource(getResources(), drawable);
  }

  @Override
  public void onCategoryClicked(int position) {
    Log.d("clicked", mCategoryList.get(position).getTitle());
    Intent runSampleIntent = new Intent();
    runSampleIntent.setClassName("com.esri.arcgisruntime.sample.analyzehotspots", "MainActivity");
    startActivity(runSampleIntent);
  }

  public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacing;
    private boolean includeEdge;

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
      this.spanCount = spanCount;
      this.spacing = spacing;
      this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
      int position = parent.getChildAdapterPosition(view); // item position
      int column = position % spanCount; // item column

      if (includeEdge) {
        outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
        outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

        if (position < spanCount) { // top edge
          outRect.top = spacing;
        }
        outRect.bottom = spacing; // item bottom
      } else {
        outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
        outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
        if (position >= spanCount) {
          outRect.top = spacing; // item top
        }
      }
    }
  }

  /**
   * Converting dp to pixel
   */
  private int dpToPx(int dp) {
    Resources r = getResources();
    return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
  }
}
