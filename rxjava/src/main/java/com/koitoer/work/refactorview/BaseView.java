package com.koitoer.work.refactorview;

import java.util.Locale;

import lombok.NoArgsConstructor;

/**
 * Created by mmena on 6/25/18.
 */
@NoArgsConstructor
public abstract class BaseView {

    BaseView(Locale locale){
        this.locale =  locale;
    }

    protected Locale locale;

    protected String site;

    protected String getSite(){
        return site;
    }

    protected void setLocale(Locale locale){
        this.locale= locale;
    }

}
