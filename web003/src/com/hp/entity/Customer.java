package com.hp.entity;

public class Customer {


    private Integer id;
    private String cust_name;
    private String cust_company;
    private String cust_position;
    private String cust_phone;
    private String cust_birth;
    private Integer cust_sex;
    private String cust_desc;
    private Integer user_id;
    private String create_time;
    private String modify_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getCust_company() {
        return cust_company;
    }

    public void setCust_company(String cust_company) {
        this.cust_company = cust_company;
    }

    public String getCust_position() {
        return cust_position;
    }

    public void setCust_position(String cust_position) {
        this.cust_position = cust_position;
    }

    public String getCust_phone() {
        return cust_phone;
    }

    public void setCust_phone(String cust_phone) {
        this.cust_phone = cust_phone;
    }

    public String getCust_birth() {
        return cust_birth;
    }

    public void setCust_birth(String cust_birth) {
        this.cust_birth = cust_birth;
    }

    public Integer getCust_sex() {
        return cust_sex;
    }

    public void setCust_sex(Integer cust_sex) {
        this.cust_sex = cust_sex;
    }

    public String getCust_desc() {
        return cust_desc;
    }

    public void setCust_desc(String cust_desc) {
        this.cust_desc = cust_desc;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

    @Override
    public String toString() {
        return "T_Customer{" +
                "id=" + id +
                ", cust_name='" + cust_name + '\'' +
                ", cust_company='" + cust_company + '\'' +
                ", cust_position='" + cust_position + '\'' +
                ", cust_phone='" + cust_phone + '\'' +
                ", cust_birth='" + cust_birth + '\'' +
                ", cust_sex=" + cust_sex +
                ", cust_desc='" + cust_desc + '\'' +
                ", user_id=" + user_id +
                ", create_time='" + create_time + '\'' +
                ", modify_time='" + modify_time + '\'' +
                '}';
    }
}
