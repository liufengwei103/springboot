package com.qsh.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 七、@ConfigurationProperties 配置绑定
 * prefix = "mycar" 类里的属性与配置文件中前缀为mycar的属性一一绑定
 *
 * @Component 将组件加到容器中
 * 只有在容器中的组件，才会拥有springboot提供的强大功能
 * <p>
 * (1)第一种启用配置绑定的方法
 * @Component
 * @ConfigurationProperties(prefix = "mycar")
 */
//@Component
//@ConfigurationProperties(prefix = "mycar")

/**
 * （2）第二种启用配置绑定的方法，在configuration文件中加注解
 */
@ConfigurationProperties(prefix = "mycar")
public class Car {
    private String brand;
    private Float price;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }
}
