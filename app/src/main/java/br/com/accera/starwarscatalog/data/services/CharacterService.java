package br.com.accera.starwarscatalog.data.services;

import java.util.ArrayList;

import br.com.accera.starwarscatalog.data.models.CharacterModel;

/**
 * Created by juliano.nardon on 23/01/18.
 */

public class CharacterService extends BaseService {

    public static final CharacterService instance = new CharacterService();


    public ArrayList<CharacterModel> getAllCharacters(){
        return new ArrayList<>(realm.where(CharacterModel.class).findAll());
    }

    public void addCharacter(CharacterModel characterModel) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(characterModel );
        realm.commitTransaction();
    }

    public CharacterModel searchCharacter(String name) {
        return realm.where(CharacterModel.class).equalTo("name", name).findFirst();
    }
}
