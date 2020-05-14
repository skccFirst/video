package youtube;

public class AdRegistered extends AbstractEvent {

    private Long adId;
    private String adName;
    private Integer adMinute;
    private Integer adCnt;
    private String adStatus;

    public AdRegistered(){
        super();
    }

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }
    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }
    public Integer getAdMinute() {
        return adMinute;
    }

    public void setAdMinute(Integer adMinute) {
        this.adMinute = adMinute;
    }
    public Integer getAdCnt() {
        return adCnt;
    }

    public void setAdCnt(Integer adCnt) {
        this.adCnt = adCnt;
    }
    public String getAdStatus() {
        return adStatus;
    }

    public void setAdStatus(String adStatus) {
        this.adStatus = adStatus;
    }
}
