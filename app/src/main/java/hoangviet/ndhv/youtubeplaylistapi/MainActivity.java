package hoangviet.ndhv.youtubeplaylistapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ListView listView;
    VideoAdapter adapter;
    ArrayList<videoYoutube>youtubeArrayList;
    public static String API_key = "AIzaSyDMHaYunbwHjJK9I_SeDkZSJd5qHNgsMEI";
    String ID_youtube = "PL5rU_rpB9t2vj6fdRShud0bAIpwP1ABmd";
    String url_youtube = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=" +
        ID_youtube +"&key="+API_key+"&maxResults=50";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listViewItem);
        youtubeArrayList = new ArrayList<>();
        ReadJson(url_youtube);
        adapter = new VideoAdapter(MainActivity.this,R.layout.dong_hinh_anh,youtubeArrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,secondActivity.class);
                videoYoutube videoYoutube = youtubeArrayList.get(position);
                intent.putExtra("videoId",videoYoutube.getVideoId());
                startActivity(intent);
            }
        });
    }
    public void ReadJson(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String tittle = "";
                        String url = "";
                        String videoId = "";
                        try {
                            JSONArray jsonItems = response.getJSONArray("items");
                            for (int i = 0;i< jsonItems.length();i++){
                                JSONObject jsonItem = jsonItems.getJSONObject(i);
                                JSONObject jsonSnippet = jsonItem.getJSONObject("snippet");
                                //lấy ra tittle của video
                                tittle = jsonSnippet.getString("title");
                                //lấy ra url của video
                                JSONObject jsonThumbnails = jsonSnippet.getJSONObject("thumbnails");
                                JSONObject jsonMedium = jsonThumbnails.getJSONObject("medium");
                                url = jsonMedium.getString("url");
                                JSONObject jsonResourceId = jsonSnippet.getJSONObject("resourceId");
                                videoId = jsonResourceId.getString("videoId");
                                youtubeArrayList.add(new videoYoutube(tittle,url,videoId));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Lỗi thấy mẹ!", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(objectRequest);
    }


}
