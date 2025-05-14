package com.example.cv.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportResult{

    boolean success = false;
    String message = "";
    List<String> erreurs = List.of(); 
}
