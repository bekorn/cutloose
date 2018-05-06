package com.cutloose.cutloose.repository;

import com.cutloose.cutloose.model.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventRepository implements BaseRepository<Event> {

    @Override
    public ArrayList<Event> getAll() {

        int amount = (int) (Math.random() * 12 + 10);
        return RandomModelGenerator.getRandomModel( amount );
    }
}

class RandomModelGenerator {

    static private final List<String> mNames = Arrays.asList( "Brunch", "Catan", "Study", "420" );

    static Event getRandomModel() {

        return new Event(
                mNames.get( (int) (Math.random() * mNames.size()) ),
                "randomURL",
                (int) (Math.random() * 4) + 1,
                (int) (Math.random() * 12) + 5 );
    }

    static ArrayList<Event> getRandomModel( int amount ) {

        ArrayList<Event> result = new ArrayList<>();

        for( int i = 0; i < amount; i++ ) {
            result.add( getRandomModel() );
        }

        return result;
    }
}
