package com.development.mycolive.adapter;

import android.util.JsonToken;

import com.development.mycolive.model.myCommunityModel.MyCommunityComment;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class ContentAdapter extends TypeAdapter<MyCommunityComment> {


    @Override
    public void write(JsonWriter out, MyCommunityComment value) throws IOException {

    }

    @Override
    public MyCommunityComment read(JsonReader in) throws IOException {
        return null;
    }


}
