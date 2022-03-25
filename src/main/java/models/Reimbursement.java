package models;

import java.sql.Blob;
import java.util.Date;

public class Reimbursement {
    private Integer id;
    private Double amount;
    private Date submitted;
    private Date resolved;
    private String description;
    private Blob receipt;
    private Integer author_id;
    private Integer resolver_id;
    private Integer status_id;
    private Integer type_id;


    public Reimbursement() {
    }

    public Reimbursement(Integer resolver_id,Integer id) {
        this.resolver_id = resolver_id;
        this.id = id;
    }

    public Reimbursement(Double amount, Integer author_id, Integer type_id) {
        this.id = null;
        this.amount = amount;
        this.status_id = null;
        this.author_id = author_id;
        this.type_id = type_id;
        this.submitted = null;
        this.resolved = null;
        this.resolver_id = null;
        this.description = null;
    }

    public Reimbursement(Integer id, Double amount, Date submitted, Date resolved, String description, Blob receipt, Integer author_id, Integer resolver_id, Integer status_id, Integer type_id) {
        this.id = id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.receipt = receipt;
        this.author_id = author_id;
        this.resolver_id = resolver_id;
        this.status_id = status_id;
        this.type_id = type_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Integer status_id) {
        this.status_id = status_id;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public Date getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Date submitted) {
        this.submitted = submitted;
    }

    public Date getResolved() {
        return resolved;
    }

    public void setResolved(Date resolved) {
        this.resolved = resolved;
    }

    public Integer getResolver_id() {
        return resolver_id;
    }

    public void setResolver_id(Integer resolver_id) {
        this.resolver_id = resolver_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "id=" + id +
                ", amount=" + amount +
                ", status_id=" + status_id +
                ", author_id=" + author_id +
                ", type_is=" + type_id +
                ", submitted=" + submitted +
                ", resolved=" + resolved +
                ", resolver_id=" + resolver_id +
                ", description='" + description + '\'' +
                '}';
    }
}
