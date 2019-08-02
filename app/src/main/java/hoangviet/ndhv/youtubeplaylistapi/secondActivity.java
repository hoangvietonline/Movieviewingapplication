package hoangviet.ndhv.youtubeplaylistapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class secondActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
YouTubePlayerView youTubePlayerView;
    int REQUEST_CODE = 1;
    String videoId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        youTubePlayerView = (YouTubePlayerView)findViewById(R.id.YoutubeVideo);
        youTubePlayerView.initialize(MainActivity.API_key,this);
        Intent intent = getIntent();
        videoId = intent.getStringExtra("videoId");

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.cueVideo(videoId);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(secondActivity.this,REQUEST_CODE);
        }else {
            Toast.makeText(this, "Lỗi rồi", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE){
            youTubePlayerView.initialize(MainActivity.API_key,secondActivity.this);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
