package com.example.dan.paymentapp.models;

import android.databinding.ObservableBoolean;

import java.util.Observable;

public class PaymentMethod
{
    private String id;
    private String name;
    private String thumbnail;
    public final ObservableBoolean isSelected = new ObservableBoolean();


    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getThumbnail()
    {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail)
    {
        this.thumbnail = thumbnail;
    }
}
