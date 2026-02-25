package org.ionescu.david.projweb.product;

public class ProductWithStockDTO {
    private Integer id;
    private String name;
    private String description;
    private String img;
    private Integer cnt;
    private Double unitPrice;

    public ProductWithStockDTO(Integer id, String name, String description, String img, Integer cnt, Double unitPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.img = img;
        this.cnt = cnt;
        this.unitPrice = unitPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
