package hoangviet.ndhv.youtubeplaylistapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoAdapter extends BaseAdapter {
    private Context mContext;
    private int mLayout;
    private List<videoYoutube>videoYoutubeList;

    public VideoAdapter(Context mContext, int mLayout, List<videoYoutube> videoYoutubeList) {
        this.mContext = mContext;
        this.mLayout = mLayout;
        this.videoYoutubeList = videoYoutubeList;
    }

    @Override
    public int getCount() {
        return videoYoutubeList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ViewHolder {
        ImageView imageViewThumbnails;
        TextView textViewtittle;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mLayout,null);
            holder.imageViewThumbnails = (ImageView)convertView.findViewById(R.id.imageThumbnails);
            holder.textViewtittle = (TextView)convertView.findViewById(R.id.txtTittle);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        // gán giá trị
        videoYoutube videoYoutube = videoYoutubeList.get(position);
        holder.textViewtittle.setText(videoYoutube.getTittle());
        Picasso.get().load(videoYoutube.getThumbnails()).into(holder.imageViewThumbnails);


        return convertView;
    }
}
