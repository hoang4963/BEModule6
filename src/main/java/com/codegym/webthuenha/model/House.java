package com.codegym.webthuenha.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "houses")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String houseName;
    private String houseAddress;
    private String bedrooms;
    private String bathrooms;
    private String description;
    private long rent;
    @OneToMany
    @JoinTable(name = "houses_image", joinColumns = {@JoinColumn(name = "house_id")},
            inverseJoinColumns = {@JoinColumn(name = "image_id")})
    private List<Image> image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "houses_status", joinColumns = {@JoinColumn(name = "houses_id")},
            inverseJoinColumns = {@JoinColumn(name = "status_id")})
    private Status status;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


}
