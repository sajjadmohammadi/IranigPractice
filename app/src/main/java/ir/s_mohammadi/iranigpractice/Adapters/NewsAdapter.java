package ir.s_mohammadi.iranigpractice.Adapters;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ir.s_mohammadi.iranigpractice.Pojo.NewsModel;
import ir.s_mohammadi.iranigpractice.R;
import ir.s_mohammadi.iranigpractice.Utilities.G;

/**
 * Created by Sajjad on 8/10/2017.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private List<NewsModel> newsModelList = new ArrayList<>();

    public NewsAdapter(List<NewsModel> newsModelList) {
        this.newsModelList = newsModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        NewsAdapter.MyViewHolder vh = new NewsAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv_title.setText(newsModelList.get(position).getTitle());
        holder.tv_description.setText(newsModelList.get(position).getDescription());
        //separate image path and image name
        String[] splitted = newsModelList.get(position).getImageUrl().split("/");
        String img_name = splitted[splitted.length - 1];
        String img_path = "";
        for (int i = 0; i < splitted.length - 1; i++) {
            img_path += splitted[i] + "/";
        }
        G.loadImage(img_path, img_name, holder.iv, false, 0);

        if (newsModelList.get(position).isDescription_visible()) {
            holder.ll_description.setVisibility(View.VISIBLE);
            holder.arrow_dotted_top.setVisibility(View.GONE);
            holder.arrow_dotted_bottom.setVisibility(View.VISIBLE);
            holder.view_dotted_top.setVisibility(View.GONE);
            holder.view_dotted_bttom.setVisibility(View.VISIBLE);
        } else {
            holder.ll_description.setVisibility(View.GONE);
            holder.arrow_dotted_top.setVisibility(View.VISIBLE);
            holder.arrow_dotted_bottom.setVisibility(View.GONE);
            holder.view_dotted_top.setVisibility(View.VISIBLE);
            holder.view_dotted_bttom.setVisibility(View.GONE);
        }

        holder.tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newsModelList.get(position).isDescription_visible()) {
                    holder.ll_description.setVisibility(View.GONE);
                    holder.arrow_dotted_top.setVisibility(View.VISIBLE);
                    holder.arrow_dotted_bottom.setVisibility(View.GONE);
                    holder.view_dotted_top.setVisibility(View.VISIBLE);
                    holder.view_dotted_bttom.setVisibility(View.GONE);
                    newsModelList.get(position).setDescription_visible(false);
                } else {
                    holder.ll_description.setVisibility(View.VISIBLE);
                    holder.arrow_dotted_top.setVisibility(View.GONE);
                    holder.arrow_dotted_bottom.setVisibility(View.VISIBLE);
                    holder.view_dotted_top.setVisibility(View.GONE);
                    holder.view_dotted_bttom.setVisibility(View.VISIBLE);
                    newsModelList.get(position).setDescription_visible(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_description;
        ImageView iv;
        LinearLayout ll_description;
        ImageView arrow_dotted_top, arrow_dotted_bottom;
        View view_dotted_top, view_dotted_bttom;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            ll_description = (LinearLayout) itemView.findViewById(R.id.ll_description);

            arrow_dotted_top = (ImageView) itemView.findViewById(R.id.arrow_dotted_top);
            arrow_dotted_bottom = (ImageView) itemView.findViewById(R.id.arrow_dotted_bottom);
            view_dotted_top = (View) itemView.findViewById(R.id.view_dotted_top);
            view_dotted_bttom = (View) itemView.findViewById(R.id.view_dotted_bttom);
        }
    }
}
