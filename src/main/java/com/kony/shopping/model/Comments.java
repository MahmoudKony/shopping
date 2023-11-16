package com.kony.shopping.model;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 *
 * CREATE TABLE Comments  -- Weak Entity
 * (
 *     creationTime DATE NOT NULL
 *     ,userid INT NOT NULL
 *     ,pid INT NOT NULL
 *     ,grade FLOAT
 *     ,content VARCHAR(500)
 *     ,PRIMARY KEY(creationTime,userid,pid)
 *     ,FOREIGN KEY(userid) REFERENCES Buyer(userid)
 *     ,FOREIGN KEY(pid) REFERENCES Product(pid)
 * );
 */

@Embeddable
record CommentsId(
        @Column(name = "user_id")
        Integer userId,
        @Column(name = "product_id")
        Integer productId,
        @Column(name = "creation_time")
        LocalDate creationTime
){}
@Entity
@Table(schema = "shopping",name = "Comments")
record Comments(
        @EmbeddedId
        CommentsId commentsId,

        String content


) {
}
