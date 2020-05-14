package youtube;

public class EditedCaption extends AbstractEvent {

    private Long captionId;
    private Long videoId;
    private String captionInfo;

    public EditedCaption(){
        super();
    }

    public Long getCaptionId() {
        return captionId;
    }

    public void setCaptionId(Long captionId) {
        this.captionId = captionId;
    }
    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }
    public String getCaptionInfo() {
        return captionInfo;
    }

    public void setCaptionInfo(String captionInfo) {
        this.captionInfo = captionInfo;
    }
}
