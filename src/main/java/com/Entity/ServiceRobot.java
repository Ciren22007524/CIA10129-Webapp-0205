package com.Entity;

import javax.persistence.*;

@Entity
@Table(name = "ServiceRobot")
public class ServiceRobot {
	@Id
    @Column(name = "keywordNo")
	private Integer keywordNo;
    @Column(name = "keywordName")
    private String keywordName;
    @Column(name = "responseContent")
    private String responseContent;

    public Integer getKeywordNo() {
        return keywordNo;
    }

    public void setKeywordNo(Integer keywordNo) {
        this.keywordNo = keywordNo;
    }

    public String getKeywordName() {
        return keywordName;
    }

    public void setKeywordName(String keywordName) {
        this.keywordName = keywordName;
    }

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }
}
