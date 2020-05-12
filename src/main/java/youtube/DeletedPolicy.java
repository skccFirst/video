package youtube;

public class DeletedPolicy extends AbstractEvent {

    private String policyId;
    private Long refundPolicy;
    private String deleteVideoName;
    private Integer violationCount;
    private Long deleteVideoId;

    public Long getDeleteVideoId() {
        return deleteVideoId;
    }

    public void setDeleteVideoId(Long deleteVideoId) {
        this.deleteVideoId = deleteVideoId;
    }
    public DeletedPolicy(){
        super();
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }
    public Long getRefundPolicy() {
        return refundPolicy;
    }

    public void setRefundPolicy(Long refundPolicy) {
        this.refundPolicy = refundPolicy;
    }
    public String getDeleteVideoName() {
        return deleteVideoName;
    }

    public void setDeleteVideoName(String deleteVideoName) {
        this.deleteVideoName = deleteVideoName;
    }
    public Integer getViolationCount() {
        return violationCount;
    }

    public void setViolationCount(Integer violationCount) {
        this.violationCount = violationCount;
    }
}