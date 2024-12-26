package com.example.prello.member;

import com.example.prello.common.BaseEntity;
import com.example.prello.user.User;
import com.example.prello.workspace.Workspace;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Table(name = "`member`")
@DynamicInsert
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="workspace_id", nullable=false)
    private Workspace workspace;

    @Column(nullable = false, columnDefinition = "varchar(20) default 'READ_ONLY'")
    @Enumerated(EnumType.STRING)
    private MemberAuth auth;

    public Member() {}

    public void updateMemberAuth(MemberAuth auth) throws IllegalAccessException {
        this.auth.validateAuthChange(auth);
        this.auth = auth;
    }
}
