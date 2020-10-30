package itis.Tyshenko.repositories.ads;

import itis.Tyshenko.entity.Ad;
import itis.Tyshenko.repositories.CrudRepository;

public interface AdRepository<T extends Ad> extends CrudRepository<T> {

}
