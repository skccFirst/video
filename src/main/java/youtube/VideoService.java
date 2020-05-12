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
import java.util.Date;

@Entity
@Table(name="VideoService_table")
public class VideoService {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String videoId;
    private Date uploadTime;
    private String clientId;
    private String channelId;

    @PrePersist
    public void onPrePersist(){
        UploadedVideo uploadedVideo = new UploadedVideo();
        BeanUtils.copyProperties(this, uploadedVideo);
        uploadedVideo.publishAfterCommit();


        EditedVideo editedVideo = new EditedVideo();
        BeanUtils.copyProperties(this, editedVideo);
        editedVideo.publishAfterCommit();


        DeletedVideo deletedVideo = new DeletedVideo();
        BeanUtils.copyProperties(this, deletedVideo);
        deletedVideo.publishAfterCommit();


    }

    @PostPersist
    public void eventPublish(){
        DeletedVideo deletedVideo = new DeletedVideo();
        deletedVideo.setVideoId(this.getVideoId());
        deletedVideo.setUploadTime(this.getUploadTime());
        deletedVideo.setClientId(this.getClientId());
        deletedVideo.setChannelId(this.getChannelId());
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;

        try {
            json = objectMapper.writeValueAsString(deletedVideo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Processor processor = Application.applicationContext.getBean(Processor.class);
        MessageChannel outputChannel = processor.output();

        outputChannel.send(MessageBuilder
                .withPayload(json)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }




}
