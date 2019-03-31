package com.koitoer.work.refactorview;

import org.junit.Test;

/**
 * Created by mmena on 6/25/18.
 */
public class Main {

    @Test
    public void selectViewByOneCondition(){
        String site = "HAUS";
        BaseView baseView = ViewFactory.getView(site);

    }

}
