package com.kony.shopping.model;

import com.kony.shopping.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CREATE TABLE Manage
 * (
 * userid INT NOT NULL
 * ,sid INT NOT NULL
 * ,SetUpTime DATE
 * ,PRIMARY KEY(userid,sid)
 * ,FOREIGN KEY(userid) REFERENCES Seller(userid)
 * ,FOREIGN KEY(sid) REFERENCES Store
 * );
 */
@Entity
@Data
@NoArgsConstructor
@Table(schema = "shopping", name = "manage")
final class Manage {
    @Id
    @Column(name = "manage_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer manageId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private  User user;
//    @OneToOne
//    @JoinColumn(name = "store_id")
//    private  List<Store> store;

    /**
     *
     */
    Manage(
            Integer manageId,
            User user
//            List<Store> store

    ) {
        this.manageId = manageId;
        this.user = user;
//        this.store = store;
    }

    @Id
    @Column(name = "manage_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer manageId() {
        return manageId;
    }

    @OneToOne
    public User user() {
        return user;
    }

//    @OneToMany
//    public List<Store> store() {
//        return store;
//    }



}
