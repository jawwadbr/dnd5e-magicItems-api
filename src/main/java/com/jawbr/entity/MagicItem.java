package com.jawbr.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "magic_item")
public class MagicItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "index_name")
    private String indexName;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "top_descr")
    private String topDescr;

    @Column(name = "descr")
    private String descr;

    @Column(name = "rarity")
    private String rarity;

    @Column(name = "url")
    private String url;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "equipment_category_fk")
    private EquipmentCategory equipmentCategory;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "source_name_fk")
    private SourceBook sourceBook;

}
