package nl.caladus.hro.utils;

import java.util.List;
import java.util.function.BiPredicate;

public class PredicateUtils {


    private static BiPredicate<String, String> equalsPredicate = String::equals;

    public static boolean equals(List<String> profiles, String test) {

        for(String profile : profiles) {
            if (equalsPredicate.test(profile, test)) {
                return true;
            }
        }

        return false;
    }
}
