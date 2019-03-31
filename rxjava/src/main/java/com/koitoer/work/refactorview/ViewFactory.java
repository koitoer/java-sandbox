package com.koitoer.work.refactorview;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

/**
 * Created by mmena on 6/25/18.
 */
public class ViewFactory {



    public static BaseView getView(String site) {

        Locale locale = getLocaleFromSite(site);
        List<? extends BaseView> viewClasses = Arrays.asList(new View1(Locale.CHINA), new View2(Locale.CHINA));
        BaseView selectedView = viewClasses.stream().filter((Predicate<BaseView>) baseView -> baseView.getSite().equals(site)).findFirst().get();
        selectedView.setLocale(locale);

        return selectedView;
    }

    private static Locale getLocaleFromSite(String site) {
        return site.equals("HAUS") ? Locale.ENGLISH : null;
    }
}
