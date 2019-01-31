package com.example.dan.paymentapp.models;

import android.databinding.ObservableBoolean;

public class PaymentBank
{
    private int id;
    private String name;
    private String thumbnail;
    public final ObservableBoolean isSelected = new ObservableBoolean();

    public int getId()
    {
        return id;
    }

    public void setId(int id)
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
