package org.sang.utils;

import org.sang.bean.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by albert on 2019/12/19.
 */
public class Util {
    public static User getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
}
