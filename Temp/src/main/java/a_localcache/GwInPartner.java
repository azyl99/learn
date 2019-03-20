package a_localcache;

import java.util.Date;

/**
 * @author guya on 2019/3/18
 */
public class GwInPartner implements CachedItem {

    private static final long serialVersionUID = -4061946559277623501L;

    /**
     * 自增ID
     */
    private Long id;

    /**
     * 接入方ID
     */
    private String partnerId;

    /**
     * partner别名
     */
    private String partnerAliasName;

    /**
     * partner是否可用
     */
    private Byte status;

    /**
     * 新建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerAliasName() {
        return partnerAliasName;
    }

    public void setPartnerAliasName(String partnerAliasName) {
        this.partnerAliasName = partnerAliasName;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "GwInPartner{" +
                "partnerId='" + partnerId + '\'' +
                ", partnerAliasName='" + partnerAliasName + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }


    @Override
    public String getCacheKey() {
        return partnerId;
    }
}
