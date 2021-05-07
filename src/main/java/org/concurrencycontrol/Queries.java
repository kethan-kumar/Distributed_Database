package org.concurrencycontrol;

public class Queries {
    private String T1Update1_local = "UPDATE customers SET city='T1 CITY' WHERE zip_code=01151";
    private String T1Update2_local = "UPDATE geolocation SET city='Geo1' WHERE zip_code=13770";
    private String T1Read_remote = "SELECT * FROM orders";
    private String T1Insert_remote = "INSERT INTO name_translation (product_category_name, product_category_name_english) values ('NewVal-01','NewVal-001')";
    private String T1Update_remote = "UPDATE sellers SET seller_city='NewCity1' WHERE seller_zip_code=01152";

    private String T2Update1_local = "UPDATE customers SET city='T2 CITY' WHERE zip_code=01151";
    private String T2Update2_local = "UPDATE geolocation SET city='Geo2' WHERE zip_code=13770";
    private String T2Read_remote = "SELECT * FROM orders";
    private String T2Insert_remote = "INSERT INTO name_translation (product_category_name, product_category_name_english) values ('NewVal-02','NewVal-002')";
    private String T2Update_remote = "UPDATE sellers SET seller_city='NewCity2' WHERE seller_zip_code=01152";

    public String getT1Update1_local() {
        return T1Update1_local;
    }

    public String getT1Update2_local() {
        return T1Update2_local;
    }

    public String getT1Read_remote() {
        return T1Read_remote;
    }

    public String getT1Insert_remote() {
        return T1Insert_remote;
    }

    public String getT1Update_remote() {
        return T1Update_remote;
    }

    public String getT2Update1_local() {
        return T2Update1_local;
    }

    public String getT2Update2_local() {
        return T2Update2_local;
    }

    public String getT2Read_remote() {
        return T2Read_remote;
    }

    public String getT2Insert_remote() {
        return T2Insert_remote;
    }

    public String getT2Update_remote() {
        return T2Update_remote;
    }
}
