package com.example.testt00000;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;


public class Word {
    String name;
    String definition;
    String origins;
    String url;

    public Word(){}
    
    public Word(String name, String definition, String origins, String url)
    {
        this.name = name;
        this.definition = definition;
        this.origins = origins;
        this.url = url;
    }
    public String getName()
    {
        return name;
    }
    public String getDefinition()
    {
        return definition;
    }
    public String getOrigins()
    {
        return origins;
    }
    public String getUrl()
    {
        return url;
    }
}
