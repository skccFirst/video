package youtube;

import youtube.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{

    @Autowired
    VideoServiceRepository videoServiceRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDeletedChannel_DeleteVideo(@Payload DeletedChannel deletedChannel){

        if(deletedChannel.isMe()){
            System.out.println("##### listener DeleteVideo : " + deletedChannel.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCheckedPolicy_DeleteVideo(@Payload CheckedPolicy checkedPolicy){

        if(checkedPolicy.isMe()){
            System.out.println("##### listener DeleteVideo : " + checkedPolicy.toJson());
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDeletedPolicy_DeleteVideo(@Payload DeletedPolicy deletedPolicy){

        if(deletedPolicy.getDeleteVideoId()!=null)
        {
            System.out.println("##### 동영상이 삭제되었습니다. : " + deletedPolicy.getDeleteVideoId());

        }
//        if(deletedChannel.isMe()){
//            System.out.println("##### listener DeleteVideo : " + deletedChannel.toJson());
//        }
    }


}
