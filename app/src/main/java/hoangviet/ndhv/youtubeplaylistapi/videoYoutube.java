package hoangviet.ndhv.youtubeplaylistapi;

public class videoYoutube {
    private String Tittle;
    private String Thumbnails;
    private String VideoId;

    public videoYoutube(String tittle, String thumbnails, String videoId) {
        Tittle = tittle;
        Thumbnails = thumbnails;
        VideoId = videoId;
    }

    public String getTittle() {
        return Tittle;
    }

    public void setTittle(String tittle) {
        Tittle = tittle;
    }

    public String getThumbnails() {
        return Thumbnails;
    }

    public void setThumbnails(String thumbnails) {
        Thumbnails = thumbnails;
    }

    public String getVideoId() {
        return VideoId;
    }

    public void setVideoId(String videoId) {
        VideoId = videoId;
    }
}
