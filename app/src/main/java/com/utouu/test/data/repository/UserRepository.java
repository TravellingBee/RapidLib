package com.utouu.test.data.repository;

/**
 * Created by Marno on 2016/8/26/14:42.
 */
public class UserRepository extends BaseRepository {

    private static volatile UserRepository instance;

    private UserRepository() {
    }

    public static UserRepository getIns() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UserRepository();
                }
            }
        }
        return instance;
    }
}
