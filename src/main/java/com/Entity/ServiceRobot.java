package com.Entity;

import javax.persistence.*;

@Entity
@Table(name = "servicerobot")
public class ServiceRobot {
	@Id
    @Column(name = "keywordno")
	private Integer keywordNo;
    @Column(name = "keywordname")
    private String keywordName;
    @Column(name = "responsecontent")
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
