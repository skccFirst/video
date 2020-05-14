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
    public void wheneverCheckedPolicy_DeleteVideo(@Payload CheckedPolicy checkedPolicy){

        if(checkedPolicy.isMe()){
            System.out.println("##### listener DeleteVideo : " + checkedPolicy.toJson());
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDeletedPolicy_DeleteVideo(@Payload DeletedPolicy deletedPolicy){


        if(deletedPolicy.isMe()){
            if(deletedPolicy.getDeleteVideoId()!=null)
            {
                //videoServiceRepository.deleteById(deletedPolicy.getDeleteVideoId());
                System.out.println("##### 동영상이 삭제되었습니다. : " + deletedPolicy.getDeleteVideoId());
            }
            System.out.println("##### listener DeleteVideo : " + deletedPolicy.toJson());
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCheckedMembership_DeleteAd(@Payload CheckedMembership checkedMembership){

        if(checkedMembership.isMe()){
            System.out.println("#### 멤버십 확인되었습니다. 광고없이 동영상 시청이 가능합니다." + checkedMembership.toJson());
            System.out.println("##### listener DeleteVideo : " + checkedMembership.toJson());
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCreatedCaption_EditedVideo(@Payload CreatedCaption createdCaption){
        if(createdCaption.isMe()){
            System.out.println(("**********동영상에 자막이 추가되었습니다**********"));

        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverEditedCaption_EditedVideo(@Payload EditedCaption editedCaption){
        if(editedCaption.isMe()){
            System.out.println(("**********동영상에 자막이 수정되었습니다**********"));

        }
    }
}