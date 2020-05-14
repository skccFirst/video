package youtube;

import java.util.Date;
import java.util.StringTokenizer;

public class EditedVideo extends AbstractEvent {

    private Long videoId;
    private Date uploadTime;
    private Long clientId;
    private Long channelId;
    private int viewCount=0;
    private Long captionId;

    // 강령현 추가
    private String adList =""; // 등록된 광고 리스트 , 로 광고id 이어붙이기


    public EditedVideo(){
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

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public String getAdList() {
        return adList;
    }

    public void setAdList(String adList) {
        this.adList = adList;
    }


    public void addAdList(String adId) {
        if("".equals(adId) || adId.isEmpty()){
            this.adList = adId;
        }else{
            StringBuilder sb = new StringBuilder(this.adList);
            sb.append(",");
            sb.append(adId);
            this.adList = sb.toString();
        }
    }

    public void minusAdList(String adId) {
        StringTokenizer st = new StringTokenizer(this.adList, ",");
        StringBuilder sb = new StringBuilder();
        int countTokens = st.countTokens();
        for (int i = 0; i < countTokens; i++) {
            String token = st.nextToken();
            if(!adId.equals(token)){
                if(0 < sb.length()){
                    sb.append(",");
                }
                sb.append(token);
            }
        }

        this.adList = sb.toString();
    }

    public Long getCaptionId() {
        return captionId;
    }

    public void setCaptionId(Long captionId) {
        this.captionId = captionId;
    }
}
