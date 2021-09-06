package com.hp.entity;

public class Visit {
    private Integer id;
    private Integer user_id;
    private Integer cust_id;
    private String visit_desc;
    private String visit_time;
    private String create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getCust_id() {
        return cust_id;
    }

    public void setCust_id(Integer cust_id) {
        this.cust_id = cust_id;
    }

    public String getVisit_desc() {
        return visit_desc;
    }

    public void setVisit_desc(String visit_desc) {
        this.visit_desc = visit_desc;
    }

    public String getVisit_time() {
        return visit_time;
    }

    public void setVisit_time(String visit_time) {
        this.visit_time = visit_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", cust_id=" + cust_id +
                ", visit_desc='" + visit_desc + '\'' +
                ", visit_time='" + visit_time + '\'' +
                ", create_time='" + create_time + '\'' +
                '}';
    }
}
