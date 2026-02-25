package org.ionescu.david.projweb.cart;

public class CartFullDTO {
    private Long id;
    private Integer productId;
    private String name;
    private String description;
    private String img;
    private Integer cnt;
    private Double unitPrice;
    private Integer userId;
    private String firstName;
    private String lastName;

    public CartFullDTO(Long id, Integer productId, String name, String description, String img, Integer cnt, Double unitPrice, Integer userId, String firstName, String lastName) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.img = img;
        this.cnt = cnt;
        this.unitPrice = unitPrice;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
