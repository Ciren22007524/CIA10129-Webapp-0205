package com.Entity;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "articlecollection")
public class ArticleCollection implements java.io.Serializable {

    @EmbeddedId
    private CompositeArticleCollection compositeArticleCollection;

    @ManyToOne
    @JoinColumn(name = "memno", referencedColumnName = "memno", insertable = false, updatable = false)
    private Member member;

    // private Integer memNo;

    @ManyToOne
    @JoinColumn(name = "artno", referencedColumnName = "artno", insertable = false, updatable = false)
    private ColumnArticle columnArticle;

    // private Integer artNo;


    public CompositeArticleCollection getCompositeArticleCollection() {
        return compositeArticleCollection;
    }

    public void setCompositeArticleCollection(CompositeArticleCollection compositeArticleCollection) {
        this.compositeArticleCollection = compositeArticleCollection;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public ColumnArticle getColumnArticle() {
        return columnArticle;
    }

    public void setColumnArticle(ColumnArticle columnArticle) {
        this.columnArticle = columnArticle;
    }

    @Embeddable
    public static class CompositeArticleCollection implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @Column(name = "memno")
        private Integer memNo;

        @Column(name = "artno")
        private Integer artNo;

        public CompositeArticleCollection() {
        }

        public CompositeArticleCollection(Integer memNo, Integer artNo) {
            this.memNo = memNo;
            this.artNo = artNo;
        }

        public Integer getMemNo() {
            return memNo;
        }

        public void setMemNo(Integer memNo) {
            this.memNo = memNo;
        }

        public Integer getArtNo() {
            return artNo;
        }

        public void setArtNo(Integer artNo) {
            this.artNo = artNo;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CompositeArticleCollection that)) return false;
            return Objects.equals(getMemNo(), that.getMemNo()) && Objects.equals(getArtNo(), that.getArtNo());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getMemNo(), getArtNo());
        }
    }
}
