package br.com.accera.starwarscatalog.data.services;

import io.realm.Realm;

/**
 * Created by juliano.nardon on 23/01/18.
 */

public class BaseService {
    protected Realm realm = Realm.getDefaultInstance();
}
