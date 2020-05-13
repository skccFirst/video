package youtube;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

@Entity
@Table(name="VideoService_table")
public class VideoService {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long videoId;
    private Date uploadTime;
    private Long clientId;
    private Long channelId;
    private int viewCount=0;

    // 강령현 추가
    private String adList =""; // 등록된 광고 리스트 , 로 광고id 이어붙이기

    @PreUpdate
    public void onPostEdited(){
        EditedVideo editedVideo = new EditedVideo();
        BeanUtils.copyProperties(this, editedVideo);
        editedVideo.publishAfterCommit();

        System.out.println(("**********동영상이 수정되었습니다**********"));
    }

    @PostPersist
    public void onPostUploaded(){
        UploadedVideo uploadedVideo = new UploadedVideo();
        BeanUtils.copyProperties(this, uploadedVideo);
        uploadedVideo.publishAfterCommit();

        System.out.println(("**********동영상이 업로드되었습니다**********"));
    }

    @PreRemove
    public void onPreRemove(){
        DeletedVideo deletedVideo = new DeletedVideo();
        BeanUtils.copyProperties(this, deletedVideo);
        deletedVideo.publishAfterCommit();

        System.out.println(("**********동영상이 삭제되었습니다**********"));
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
}
