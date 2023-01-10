package com.sparta.springblog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;

    @Column(nullable = false, unique = true)
    private String name;

    @Max(value = 2, message = "카테고리 계층은 최대 3까지 가능합니다.")
    @Column(nullable = false)
    private int layer = 0;

    @Column
    private String parent = null;

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, int layer, String parent) {
        this.name = name;
        this.layer = layer;
        this.parent = parent;
    }

    public void updateCategoryName(String name) {
        this.name = name;
    }

    public void updateChildrenCategoryParent(String name) {
        this.parent = name;
    }
}
