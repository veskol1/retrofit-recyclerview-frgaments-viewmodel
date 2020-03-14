package com.example.myapplication;

import java.io.Serializable;

public class Result implements Serializable {
    private String title;
    private String overview;

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }
}
