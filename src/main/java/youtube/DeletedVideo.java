package youtube;

import java.util.Date;

public class DeletedVideo extends AbstractEvent {

    private Long videoId;
    private Date uploadTime;
    private Long clientId;
    private Long channelId;
    private Long captionId;

    public DeletedVideo(){
        super();
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Long getCaptionId() {
        return captionId;
    }

    public void setCaptionId(Long captionId) {
        this.captionId = captionId;
    }
}
