package com.ac.yy.Entity;

import com.ac.yy.DTO.RegisterDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private int uid;

    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @Column(name = "user_pw")
    private String userPw;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_birth")
    private String userBirth;

    @Column(name = "user_addr")
    private String userAddr;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "reg_date")
    @CreationTimestamp
    private LocalDateTime regDate;

    @Column(name = "mod_date")
    @UpdateTimestamp
    private LocalDateTime modDate;

    public UserEntity(RegisterDTO dto) {
        this.userId = dto.getUserId();
        this.userPw = dto.getUserPw();
        this.userName = dto.getUserName();
        this.userBirth = dto.getUserBirth();
        this.userAddr = dto.getUserAddr();
        this.userPhone = dto.getUserPhone();
        this.userEmail = dto.getUserEmail();
    }
}
