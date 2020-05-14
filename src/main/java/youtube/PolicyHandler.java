package youtube;

import youtube.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Iterator;

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
    public void wheneverAdRegistered_EditedVideo(@Payload AdRegistered adRegistered) {
        System.out.println("##### wheneverAdRegistered_EditedVideo 들어옴");
        if(videoServiceRepository.count() == 0){
            System.out.println("##### 등록된 동영상이 없어 광고를 맵핑이 불가능합니다.");
        }
        if (adRegistered.isMe()) {
            if (adRegistered.getAdId() != null) {
                boolean isMapping = false; // 광고 맵핑 여부
                Iterable<VideoService> vsList = videoServiceRepository.findAll();
                Iterator it = vsList.iterator();
                while (it.hasNext()) {
                    if (isMapping)
                        break;

                    VideoService vs = (VideoService) it.next();
                    System.out.println("##### vs.getAdId()| " + vs.getAdId());
                    if ("".equals(vs.getAdId()) || null == vs.getAdId()) {
                        vs.setAdId(adRegistered.getAdId());
                        videoServiceRepository.save(vs);
                        isMapping = true;
                        System.out.println("##### 동영상에 광고가 등록되었습니다. 동영상id" +vs.getAdId() + ",  광고id: "+ adRegistered.getAdId());
                    }
                }
                System.out.println("##### listener AdRegistered : " + adRegistered.toJson());

            }
        }
    }
}
