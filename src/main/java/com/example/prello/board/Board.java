package com.example.prello.board;

import com.example.prello.common.BaseEntity;
import com.example.prello.workspace.Workspace;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "`board`")
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;

    private String title;

    private String bg_color;

    private String bg_image;
}
