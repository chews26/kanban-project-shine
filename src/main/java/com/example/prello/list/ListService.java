package com.example.prello.list;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListService {

    private final ListRepository listRepository;

}
