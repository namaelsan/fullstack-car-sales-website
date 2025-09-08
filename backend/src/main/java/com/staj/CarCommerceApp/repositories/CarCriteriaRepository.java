package com.staj.CarCommerceApp.repositories;

import com.staj.CarCommerceApp.entities.Brand;
import com.staj.CarCommerceApp.entities.Car;
import com.staj.CarCommerceApp.models.CarPage;
import com.staj.CarCommerceApp.models.CarSearchCriteria;
import com.staj.CarCommerceApp.models.SearchModel;
import com.staj.CarCommerceApp.models.SearchRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.Data;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@Repository
@Data
public class CarCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public CarCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Car> findAllWithFilters(SearchModel<CarSearchCriteria> searchModel) {
        CriteriaQuery<Car> carCriteriaQuery = criteriaBuilder.createQuery(Car.class);
        Root<Car> carRoot = carCriteriaQuery.from(Car.class);
        SearchRequest carRequest = searchModel.getSearchRequest();

        Predicate predicate = getPredicate(searchModel.getModel(), carRoot);

        carCriteriaQuery.where(predicate);
        setOrder(searchModel.getSearchRequest(),carCriteriaQuery,carRoot);
        TypedQuery<Car> typedQuery = entityManager.createQuery(carCriteriaQuery);
        typedQuery.setFirstResult(carRequest.getPageIndex() * carRequest.getPageSize());
        typedQuery.setMaxResults(carRequest.getPageSize());

        Pageable pageable = getPageable(carRequest);

        long carCount = getCarCount(searchModel.getModel());

        return new PageImpl<>(typedQuery.getResultList(), pageable, carCount);
    }


    //TODO: ADD FILTERS FOR ALL VARIABLES
    private Predicate getPredicate(CarSearchCriteria carSearchCriteria, Root<Car> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(carSearchCriteria.getBrand())) {
            Join<Car, Brand> brandJoin = root.join("brand"); // assuming 'brand' is the field name in Car
            predicates.add(
                    criteriaBuilder.like(criteriaBuilder.lower(
                            brandJoin.get("bname")), "%" + carSearchCriteria.getBrand().toLowerCase() + "%")
            );
        }

        if (Objects.nonNull(carSearchCriteria.getSpecification())) {
            predicates.add(
                    criteriaBuilder.like(root.get("specification"), "%" + carSearchCriteria.getSpecification() + "%") //user doesnt have to give an exact description of the variable
            );
        }

        if (Objects.nonNull(carSearchCriteria.getLitre())) {
            predicates.add(
                    criteriaBuilder.between(root.get("litre"), carSearchCriteria.getLitre().get(0),carSearchCriteria.getLitre().get(1)
                    )
            );
        }

        if (Objects.nonNull(carSearchCriteria.getPrice())) {
            predicates.add(
                    criteriaBuilder.between(root.get("price"), carSearchCriteria.getPrice().get(0),carSearchCriteria.getPrice().get(1)
                    )
            );
        }

        if (Objects.nonNull(carSearchCriteria.getReleaseDateTime())) {
            predicates.add(
                    criteriaBuilder.between(root.get("releaseDateTime"), carSearchCriteria.getReleaseDateTime().get(0),carSearchCriteria.getReleaseDateTime().get(1)
                    )
            );
        }

        if (Objects.nonNull(carSearchCriteria.getReleaseDateTime())) {
            predicates.add(
                    criteriaBuilder.equal(root.get("used"), carSearchCriteria.getUsed())
            );
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(SearchRequest carRequest, CriteriaQuery<Car> carCriteriaQuery, Root<Car> carRoot) {
        if (carRequest.getSortDir().equals(Sort.Direction.ASC)) {
            carCriteriaQuery.orderBy(criteriaBuilder.asc(carRoot.get(carRequest.getSortName())));
        } else {
            carCriteriaQuery.orderBy(criteriaBuilder.desc(carRoot.get(carRequest.getSortName())));
        }
    }

    private Pageable getPageable(SearchRequest carRequest) {
        Sort sort = Sort.by(carRequest.getSortDir(), carRequest.getSortName());
        return PageRequest.of(carRequest.getPageIndex(), carRequest.getPageSize(), sort);
    }

    private long getCarCount(CarSearchCriteria carSearchCriteria) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Car> carRoot = countQuery.from(Car.class);

        Predicate predicate = getPredicate(carSearchCriteria, carRoot);

        countQuery.select(criteriaBuilder.count(carRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }


}
