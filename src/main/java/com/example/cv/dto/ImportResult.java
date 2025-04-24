package com.example.cv.dto;

import java.util.List;

import lombok.Value;

@Value
public class ImportResult{
    boolean success;
    String message;
    List<String> erreurs; 
}
