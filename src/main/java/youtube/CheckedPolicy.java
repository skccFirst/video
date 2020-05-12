
package youtube;

public class CheckedPolicy extends AbstractEvent {

    private Long policyId;
    private Long refundPolicy;
    private String deleteVideoName;
    private Integer violationCount;

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
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
