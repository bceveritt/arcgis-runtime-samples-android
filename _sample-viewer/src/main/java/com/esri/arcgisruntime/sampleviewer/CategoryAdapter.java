package com.esri.arcgisruntime.sampleviewer;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

  private Context mContext;
  private List<Category> mCategoryList;

  private OnCategoryClicked onCategoryClicked;

  public class CategoryViewHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public ImageView icon;
    public ImageView background;

    public CategoryViewHolder(View view) {
      super(view);
      title = view.findViewById(R.id.cardTitle);
      icon = view.findViewById(R.id.cardIcon);
      background = view.findViewById(R.id.cardBackground);
    }
  }

  public CategoryAdapter(Context context,  List<Category> categoryList) {
    this.mContext = context;
    this.mCategoryList = categoryList;
  }

  @Override
  public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card, parent, false);
    return new CategoryViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(CategoryViewHolder holder, int position) {
    Category category = mCategoryList.get(position);
    holder.itemView.setOnClickListener(view -> onCategoryClicked.onCategoryClicked(position));
    holder.title.setText(category.getTitle());
    holder.icon.setImageBitmap(category.getIcon());
    holder.background.setImageBitmap(category.getBackground());
  }

  @Override public int getItemCount() {
    return mCategoryList.size();
  }

  public void setOnCategoryClicked(OnCategoryClicked onCategoryClicked) {
    this.onCategoryClicked = onCategoryClicked;
  }

  public interface OnCategoryClicked {
    void onCategoryClicked(int position);
  }
}
