package ru.itis.tyshenko.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.tyshenko.entity.CmsPage;

import java.util.List;

public interface CmsRepository extends JpaRepository<CmsPage, Long> {
    @Query("SELECT page FROM CmsPage page JOIN FETCH page.owner")
    @NotNull
    List<CmsPage> findAll();
}
