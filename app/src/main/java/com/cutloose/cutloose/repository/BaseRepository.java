package com.cutloose.cutloose.repository;

import java.util.List;

public interface BaseRepository<Model> {

    List<Model> getAll();
}
