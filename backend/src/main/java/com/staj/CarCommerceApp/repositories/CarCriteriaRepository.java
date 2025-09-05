//package com.staj.CarCommerceApp.repositories;
//
//import com.staj.CarCommerceApp.entities.Car;
//import com.staj.CarCommerceApp.models.CarPage;
//import com.staj.CarCommerceApp.models.CarSearchCriteria;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.criteria.CriteriaBuilder;
//import jakarta.persistence.criteria.CriteriaQuery;
//import jakarta.persistence.criteria.Predicate;
//import jakarta.persistence.criteria.Root;
//import lombok.Data;
//import org.springframework.data.domain.Page;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
////
////@Repository
////@Data
////public class CarCriteriaRepository {
////
////    private final EntityManager entityManager;
////    private final CriteriaBuilder criteriaBuilder;
////
////    public CarCriteriaRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
////        this.entityManager = entityManager;
////        this.criteriaBuilder = entityManager.getCriteriaBuilder();
////    }
//
////    public Page<Car> findAllWithFilters(CarPage carPage, CarSearchCriteria carSearchCriteria) {
////        CriteriaQuery<Car> carCriteriaQuery = criteriaBuilder.createQuery(Car.class);
////        Root<Car> carRoot = carCriteriaQuery.from(Car.class);
////        Predicate predicate = getPredicate(carSearchCriteria, carRoot);
////    }
//
////    private Predicate getPredicate(CarSearchCriteria carSearchCriteria, Root<Car> root) {
////        List<Predicate> predicates = new ArrayList<>();
////        if (Objects.nonNull(carSearchCriteria.) {}
////    }
//
////}
